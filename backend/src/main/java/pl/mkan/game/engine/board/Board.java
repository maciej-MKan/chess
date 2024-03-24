package pl.mkan.game.engine.board;

import lombok.Getter;
import pl.mkan.game.engine.AI;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureFactory;
import pl.mkan.game.engine.Move;
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
        Figure figure = getFigure(move.getSourceCol(), move.getSourceRow());
        figure.setMoved();
        setFigure(move.getDestCol(), move.getDestRow(), figure);
        setFigure(move.getSourceCol(), move.getSourceRow(), new None());
        whoseMove = oppositeColor(whoseMove);
    }

    public void AIMove() {
        FigureColor computerColor = boardOrientation == BoardOrientation.WHITE_ON_TOP ? FigureColor.WHITE : FigureColor.BLACK;

        if (whoseMove != computerColor) throw new RuntimeException("Is not computer turn");
        Move bestMove = AI.getBestMove(this, computerColor);
        move(bestMove);
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
        return move.getDestCol() >= 0 && move.getDestCol() < 8 && move.getDestRow() >= 0 && move.getDestRow() < 8;
    }

    private boolean targetFieldIsEmptyOrEnemy(Move move) {
        return (getFigure(move.getDestCol(), move.getDestRow()) instanceof None) || (getFigure(move.getDestCol(), move.getDestRow()).getColor() != whoseMove);
    }

    private boolean isValidMove(Move move) {
        boolean isCapture = !(getFigure(move.getDestCol(), move.getDestRow()) instanceof None);
        boolean isFirstMove = getFigure(move.getSourceCol(), move.getSourceRow()).isFirstMove();
        int deltaCol = move.getDestCol() - move.getSourceCol();
        int deltaRow = move.getDestRow() - move.getSourceRow();
        boolean isMoveInColorDirection = checkMoveInColorDirection(move);
        return getFigure(move.getSourceCol(), move.getSourceRow()).getPossibleMoves().stream().filter(pm -> !pm.isHaveToBeFirstMove() || pm.isHaveToBeFirstMove() == isFirstMove).filter(pm -> pm.getColumn() == deltaCol).filter(pm -> pm.getRow() == deltaRow).filter(pm -> pm.isHaveToCapture() == isCapture).filter(pm -> pm.isCanJump() || isPathClear(move)).anyMatch(pm -> !pm.isOnlyInColorDirection() || isMoveInColorDirection);
    }

    private boolean isPathClear(Move move) {
        boolean result = true;
        int deltaCol = Integer.compare(move.getDestCol(), move.getSourceCol());
        int deltaRow = Integer.compare(move.getDestRow(), move.getSourceRow());
        int currentCol = move.getSourceCol() + deltaCol;
        int currentRow = move.getSourceRow() + deltaRow;

        while (currentCol != move.getDestCol() || currentRow != move.getDestRow()) {
            if (!(getFigure(currentCol, currentRow) instanceof None)) {
                result = false;
            }
            currentCol += deltaCol;
            currentRow += deltaRow;
        }
        return result;
    }


    private boolean checkMoveInColorDirection(Move move) {
        boolean isMoveInColorDirection;
        if (boardOrientation == BoardOrientation.WHITE_ON_TOP) {
            if (getFigure(move.getSourceCol(), move.getSourceRow()).getColor() == FigureColor.WHITE) {
                isMoveInColorDirection = move.getDestRow() > move.getSourceRow();
            } else {
                isMoveInColorDirection = move.getDestRow() < move.getSourceRow();
            }
        } else {
            if (getFigure(move.getSourceCol(), move.getSourceRow()).getColor() == FigureColor.WHITE) {
                isMoveInColorDirection = move.getDestRow() < move.getSourceRow();
            } else {
                isMoveInColorDirection = move.getDestRow() > move.getSourceRow();
            }
        }
        return isMoveInColorDirection;
    }

    private boolean checkFigureColor(Move move) {
        return getFigure(move.getSourceCol(), move.getSourceRow()).getColor() == whoseMove;
    }

    private boolean checkIfMovingFigure(Move move) {
        Figure figure = getFigure(move.getSourceCol(), move.getSourceRow());
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

}
