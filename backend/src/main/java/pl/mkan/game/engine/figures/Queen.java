package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;
import pl.mkan.game.engine.MoveType;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Figure {

    private final FigureColor color;

    public Queen(int id, FigureColor color) {
        super.id = id;
        this.color = color;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        List<FigureMove> moves = new ArrayList<>();
        addQueenMoves(moves, MoveType.CAPTURE);
        addQueenMoves(moves, MoveType.NONE);
        return moves;
    }

    private void addQueenMoves(List<FigureMove> moves, MoveType haveToCapture) {

        for (int delta = -7; delta < 8; delta++) {
            if (delta == 0) continue;
            moves.add(new FigureMove(delta, delta, false, haveToCapture, false));
        }
        for (int delta = -7; delta < 8; delta++) {
            if (delta == 0) continue;
            moves.add(new FigureMove(delta, -delta, false, haveToCapture, false));
        }
        for (int col = -7; col < 8; col++) {
            if (col == 0) continue;
            moves.add(new FigureMove(col, 0, false, haveToCapture, false));
        }
        for (int row = -7; row < 8; row++) {
            if (row == 0) continue;
            moves.add(new FigureMove(0, row, false, haveToCapture, false));
        }
    }

    @Override
    public String toString() {
        return getColorSymbol() + "Q";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}