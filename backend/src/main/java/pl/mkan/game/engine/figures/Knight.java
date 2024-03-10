package pl.mkan.game.engine.figures;

import lombok.AllArgsConstructor;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class Knight extends Figure {

    private final FigureColor color;
    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        List<FigureMove> moves = new ArrayList<>();
        addKnightMoves(moves, true);
        addKnightMoves(moves, false);
        return moves;
    }

    private static void addKnightMoves(List<FigureMove> moves, boolean haveToCapture) {
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