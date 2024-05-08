package pl.mkan.game.engine.board;

import lombok.Getter;
import pl.mkan.game.engine.*;
import pl.mkan.game.engine.figures.*;

import java.util.ArrayList;
import java.util.List;

import static pl.mkan.game.engine.board.Utils.oppositeColor;

public class Board {

    private final List<BoardRow> rows = new ArrayList<>();
    @Getter
    private BoardOrientation boardOrientation = BoardOrientation.WHITE_ON_TOP;
    @Getter
    private FigureColor whoseMove = FigureColor.WHITE;
    @Getter
    private boolean gameWithComputer;
    private Board prevBoard;

    public Board() {
        for (int row = 0; row < 8; row++) {
            rows.add(new BoardRow());
        }
    }

    public Board(BoardOrientation boardOrientation, boolean gameWithComputer) {
        this();
        this.boardOrientation = boardOrientation;
        this.whoseMove = boardOrientation == BoardOrientation.WHITE_ON_TOP ? FigureColor.WHITE : FigureColor.BLACK;
        this.gameWithComputer = gameWithComputer;
    }

    public void init() {
        FigureColor color = (boardOrientation == BoardOrientation.WHITE_ON_TOP) ? FigureColor.WHITE : FigureColor.BLACK;
        setBeginningRoofFigures(0, color);
        setPawns(color, 1);
        color = oppositeColor(color);
        setBeginningRoofFigures(7, color);
        setPawns(color, 6);

    }

    private void setPawns(FigureColor color, int row) {
        for (int col = 0; col < 8; col++) {
            setFigure(col, row, new Pawn(((row + 1) * 10) + col + 1, color));
        }
    }

    private void setBeginningRoofFigures(int row, FigureColor color) {
        setFigure(0, row, new Rook(((row + 1) * 10) + 1, color));
        setFigure(1, row, new Knight(((row + 1) * 10) + 2, color));
        setFigure(2, row, new Bishop(((row + 1) * 10) + 3, color));
        if (boardOrientation == BoardOrientation.BLACK_ON_TOP) {
            setFigure(3, row, new Queen(((row + 1) * 10) + 4, color));
            setFigure(4, row, new King(((row + 1) * 10) + 5, color));
        } else {
            setFigure(3, row, new King(((row + 1) * 10) + 4, color));
            setFigure(4, row, new Queen(((row + 1) * 10) + 5, color));
        }
        setFigure(5, row, new Bishop(((row + 1) * 10) + 6, color));
        setFigure(6, row, new Knight(((row + 1) * 10) + 7, color));
        setFigure(7, row, new Rook(((row + 1) * 10) + 8, color));
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().set(col, figure);
    }

    public void move(Move move) {
        prevBoard = deepCopy();
        Figure figure = getFigure(move.sourceCol(), move.sourceRow());
        figure.setMoved();
        setFigure(move.destCol(), move.destRow(), figure);
        setFigure(move.sourceCol(), move.sourceRow(), new None());
        switchWhoseMove();
    }

    public void switchWhoseMove() {
        whoseMove = oppositeColor(whoseMove);
    }

    public boolean isEnPassant(Move move) {
        Figure moveingFigure = getFigure(move.destCol(), move.destRow());
        int deltaColumn = move.sourceCol() - move.destCol();
        int deltaRow = move.sourceRow() - move.destRow();
        return (
                (moveingFigure instanceof Pawn) &&
                        (deltaColumn != 0) &&
                        (deltaRow == 0)
        );
    }

    public Move AIMove() {
        FigureColor computerColor = boardOrientation == BoardOrientation.WHITE_ON_TOP ? FigureColor.WHITE : FigureColor.BLACK;

        if (whoseMove != computerColor) throw new RuntimeException("Is not computer turn");
        Move bestMove = AI.getBestMove(this, computerColor);
        move(bestMove);
        return bestMove;
    }

    public void backMove() {
        recoverBoard(prevBoard);
    }

    public boolean checkMove(Move move) {
        boolean result;
        result = isTargetOnBoard(move);
        result = result && checkIfMovingFigure(move);
        result = result && checkFigureColor(move);
        result = result && targetFieldIsEmptyOrEnemy(move);
        result = result && isValidMove(move);
        return result;
    }


    private boolean isTargetOnBoard(Move move) {
        return move.destCol() >= 0 && move.destCol() < 8 && move.destRow() >= 0 && move.destRow() < 8;
    }

    private boolean targetFieldIsEmptyOrEnemy(Move move) {
        return (getFigure(move.destCol(), move.destRow()) instanceof None) || (getFigure(move.destCol(), move.destRow()).getColor() != whoseMove);
    }

    private boolean isValidMove(Move move) {
        boolean isFirstMove = getFigure(move.sourceCol(), move.sourceRow()).isFirstMove();
        int deltaCol = move.destCol() - move.sourceCol();
        int deltaRow = move.destRow() - move.sourceRow();
        boolean isMoveInColorDirection = checkMoveInColorDirection(move);
        return getFigure(move.sourceCol(), move.sourceRow())
                .getPossibleMoves().stream()
                .filter(pm -> !pm.isHaveToBeFirstMove() || (pm.isHaveToBeFirstMove() == isFirstMove))
                .filter(pm -> pm.getColumn() == deltaCol)
                .filter(pm -> pm.getRow() == deltaRow)
                .filter(pm -> pm.isHaveToCapture() == isMoveWithCapture(move))
                .filter(pm -> pm.isCanJump() || isPathClear(move))
                .anyMatch(pm -> !pm.isOnlyInColorDirection() || isMoveInColorDirection);
    }

    private CoverOptions isMoveWithCapture(Move move) {
        boolean destSquareEmpty = getFigure(move.destCol(), move.destRow()) instanceof None;
        return destSquareEmpty ? CoverOptions.FALSE : CoverOptions.TRUE;
    }

    private boolean isPathClear(Move move) {
        boolean result = true;
        int deltaCol = Integer.compare(move.destCol(), move.sourceCol());
        int deltaRow = Integer.compare(move.destRow(), move.sourceRow());
        int currentCol = move.sourceCol() + deltaCol;
        int currentRow = move.sourceRow() + deltaRow;

        while (currentCol != move.destCol() || currentRow != move.destRow()) {
            if (!(getFigure(currentCol, currentRow) instanceof None)) {
                result = false;
            }
            currentCol += deltaCol;
            currentRow += deltaRow;
        }
        return result;
    }


    private boolean checkMoveInColorDirection(Move move) {
        if (move.sourceRow() == move.destRow()) return true;
        boolean isMoveInColorDirection;
        if (boardOrientation == BoardOrientation.WHITE_ON_TOP) {
            if (getFigure(move.sourceCol(), move.sourceRow()).getColor() == FigureColor.WHITE) {
                isMoveInColorDirection = move.destRow() > move.sourceRow();
            } else {
                isMoveInColorDirection = move.destRow() < move.sourceRow();
            }
        } else {
            if (getFigure(move.sourceCol(), move.sourceRow()).getColor() == FigureColor.WHITE) {
                isMoveInColorDirection = move.destRow() < move.sourceRow();
            } else {
                isMoveInColorDirection = move.destRow() > move.sourceRow();
            }
        }
        return isMoveInColorDirection;
    }

    private boolean checkFigureColor(Move move) {
        return getFigure(move.sourceCol(), move.sourceRow()).getColor() == whoseMove;
    }

    private boolean checkIfMovingFigure(Move move) {
        Figure figure = getFigure(move.sourceCol(), move.sourceRow());
        return !(figure instanceof None);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("|--|--|--|--|--|--|--|--|\n");
        for (int row = 0; row < 8; row++) {
            s.append(rows.get(row).toString());
        }
        s.append("|--|--|--|--|--|--|--|--|\n");
        s.append("Next move: ").append(whoseMove);
        return s.toString();
    }

    public Board deepCopy() {
        Board newBoard = new Board(boardOrientation, gameWithComputer);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = getFigure(col, row);
                if (!(figure instanceof None)) {
                    Figure newFigure = FigureFactory.createFigureCopy(figure);
                    if (!figure.isFirstMove()) newFigure.setMoved();
                    newBoard.setFigure(col, row, newFigure);
                }
            }
        }
        newBoard.whoseMove = whoseMove;
        return newBoard;
    }

    private void recoverBoard(Board board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                setFigure(col, row, board.getFigure(col, row));
            }
        }
    }

    public void setEnPassantDestPosition(Move move) {
        Figure movingFigure = getFigure(move.destCol(), move.destRow());
        int newRow = checkMoveInColorDirection(
                new Move(move.sourceCol(), move.sourceRow(), move.destCol(), move.destRow() + 1)
        ) ? 1 : -1;
        setFigure(move.destCol(), move.destRow(), new None());
        setFigure(move.destCol(), move.destRow() + newRow, movingFigure);
    }
}
