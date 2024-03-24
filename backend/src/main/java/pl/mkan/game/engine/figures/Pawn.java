package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    private final FigureColor color;

    public Pawn(int id, FigureColor color) {
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
        moves.add(new FigureMove(0, 1, true, false, false));
        moves.add(new FigureMove(0, 2, true, false, true));
        moves.add(new FigureMove(-1, 1, true, true, false));
        moves.add(new FigureMove(1, 1, true, true, false));
        moves.add(new FigureMove(0, -1, true, false, false));
        moves.add(new FigureMove(0, -2, true, false, true));
        moves.add(new FigureMove(-1, -1, true, true, false));
        moves.add(new FigureMove(1, -1, true, true, false));
        return moves;
    }

    @Override
    public String toString() {
        return getColorSymbol() + "P";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}



