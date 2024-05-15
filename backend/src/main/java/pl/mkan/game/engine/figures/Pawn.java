package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.CoverOptions;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    private final FigureColor color;

    public Pawn(int id, FigureColor color) {
        super.id = id;
        super.isFirstMove = true;
        this.color = color;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        List<FigureMove> moves = new ArrayList<>();
        moves.add(new FigureMove(0, 1, true, CoverOptions.FALSE, false));
        moves.add(new FigureMove(0, 2, true, CoverOptions.FALSE, true));
        moves.add(new FigureMove(-1, 1, true, CoverOptions.TRUE, false));
        moves.add(new FigureMove(1, 1, true, CoverOptions.TRUE, false));
        moves.add(new FigureMove(0, -1, true, CoverOptions.FALSE, false));
        moves.add(new FigureMove(0, -2, true, CoverOptions.FALSE, true));
        moves.add(new FigureMove(-1, -1, true, CoverOptions.TRUE, false));
        moves.add(new FigureMove(1, -1, true, CoverOptions.TRUE, false));
        moves.add(new FigureMove(1, 0, false, CoverOptions.ENPASSANT, false));
        moves.add(new FigureMove(-1, 0, false, CoverOptions.ENPASSANT, false));
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



