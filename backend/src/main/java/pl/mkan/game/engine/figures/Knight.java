package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.CoverOptions;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.ArrayList;
import java.util.List;
public class Knight extends Figure {

    private final FigureColor color;

    public Knight(int id, FigureColor color) {
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
        addKnightMoves(moves, CoverOptions.TRUE);
        addKnightMoves(moves, CoverOptions.FALSE);
        return moves;
    }

    private static void addKnightMoves(List<FigureMove> moves, CoverOptions haveToCapture) {
        moves.add(new FigureMove(-1, -2, false, haveToCapture, false, true));
        moves.add(new FigureMove(1, -2, false, haveToCapture, false, true));
        moves.add(new FigureMove(2, -1, false, haveToCapture, false, true));
        moves.add(new FigureMove(2, 1, false, haveToCapture, false, true));
        moves.add(new FigureMove(1, 2, false, haveToCapture, false, true));
        moves.add(new FigureMove(-1, 2, false, haveToCapture, false, true));
        moves.add(new FigureMove(-2, 1, false, haveToCapture, false, true));
        moves.add(new FigureMove(-2, -1, false, haveToCapture, false, true));
    }

    @Override
    public String toString() {
        return getColorSymbol() + "k";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}