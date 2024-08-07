package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;
import pl.mkan.game.engine.MoveType;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Figure {

    private final FigureColor color;

    public Bishop(int id, FigureColor color) {
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
        addBishopMoves(moves, MoveType.CAPTURE);
        addBishopMoves(moves, MoveType.NONE);
        return moves;
    }

    private void addBishopMoves(List<FigureMove> moves, MoveType haveToCapture) {
        for (int delta = -7; delta < 8; delta++) {
            if (delta == 0) continue;
            moves.add(new FigureMove(delta, delta, false, haveToCapture, false));
        }
        for (int delta = -7; delta < 8; delta++) {
            if (delta == 0) continue;
            moves.add(new FigureMove(delta, -delta, false, haveToCapture, false));
        }
    }

    @Override
    public String toString() {
        return getColorSymbol() + "B";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}