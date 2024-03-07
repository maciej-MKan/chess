package pl.mkan.game.engine.board;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.figures.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<BoardRow> rows = new ArrayList<>();
    private BoardOrientation boardOrientation = BoardOrientation.WHITE_ON_TOP;
    private FigureColor whoseMove = FigureColor.WHITE;

    public Board() {
        for (int row = 0; row < 8; row++) {
            rows.add(new BoardRow());
        }
    }

    public Board(BoardOrientation boardOrientation) {
        this();
        this.boardOrientation = boardOrientation;
    }

    public void init(){
        FigureColor color = (boardOrientation == BoardOrientation.WHITE_ON_TOP) ? FigureColor.WHITE : FigureColor.BLACK;
        setBeginningRoofFigures(0, color);
        setPawns(color, 1);
        color = oppositeColor(color);
        setBeginningRoofFigures(7, color);
        setPawns(color, 6);

    }

    private void setPawns(FigureColor color, int row) {
        for (int col = 0; col < 8; col++) {
            setFigure(col, row, new Pawn(color));
        }
    }

    private FigureColor oppositeColor(FigureColor color) {
        return (color == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }

    private void setBeginningRoofFigures(int row, FigureColor color) {
        setFigure(0, row, new Rook(color));
        setFigure(1, row, new Knight(color));
        setFigure(2, row, new Bishop(color));
        setFigure(3, row, new Queen(color));
        setFigure(4, row, new King(color));
        setFigure(5, row, new Bishop(color));
        setFigure(6, row, new Knight(color));
        setFigure(7, row, new Rook(color));
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().set(col, figure);
    }

    public boolean move(Move move) {
        boolean result = true;
        result = result && checkIfMovingFigure(move);
        result = result && checkFigureColor(move);
        result = result && targetFieldIsEmptyOrEnemy(move);
        result = result && isValidMove(move);
        if (result) {
            Figure figure = getFigure(move.getSourceCol(), move.getSourceRow());
            setFigure(move.getDestCol(), move.getDestRow(), figure);
            setFigure(move.getSourceCol(), move.getSourceRow(), new None());
            whoseMove = oppositeColor(whoseMove);
        }
        return result;
    }

    private boolean targetFieldIsEmptyOrEnemy(Move move) {
        return (getFigure(move.getDestCol(), move.getDestRow()) instanceof None) ||
                (getFigure(move.getDestCol(), move.getDestRow()).getColor() != whoseMove);
    }

    private boolean isValidMove(Move move) {
        boolean isCapture = !(getFigure(move.getDestCol(), move.getDestRow()) instanceof None);
        int deltaCol = move.getDestCol() - move.getSourceCol();
        int deltaRow = move.getDestRow() - move.getSourceRow();
        boolean isMoveInColorDirection = checkMoveInColorDirection(move);
        return getFigure(move.getSourceCol(), move.getSourceRow()).getPossibleMoves().stream()
                .filter(pm -> pm.getColumn() == deltaCol)
                .filter(pm -> pm.getRow() == deltaRow)
                .filter(pm -> pm.isHaveToCapture() == isCapture)
                .filter(pm -> !pm.isOnlyInColorDirection() || isMoveInColorDirection)
                .findFirst()
                .isPresent();
    }

    private boolean checkMoveInColorDirection(Move move) {
        boolean isMoveInColorDirection;
        if (boardOrientation == BoardOrientation.WHITE_ON_TOP){
            if (getFigure(move.getSourceCol(), move.getSourceRow()).getColor() == FigureColor.WHITE){
                isMoveInColorDirection = move.getDestRow() > move.getSourceRow();
            } else {
                isMoveInColorDirection = move.getDestRow() < move.getSourceRow();
            }
        } else {
            if (getFigure(move.getSourceCol(), move.getSourceRow()).getColor() == FigureColor.WHITE){
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
        return !(getFigure(move.getSourceCol(), move.getSourceRow()) instanceof None);
    }

    @Override
    public String toString() {
        String s = "|--|--|--|--|--|--|--|--|\n";
        for (int row = 0; row < 8; row++) {
            s += rows.get(row).toString();
        }
        s += "|--|--|--|--|--|--|--|--|\n";
        s += "Next move: " + whoseMove;
        return s;
    }
}
