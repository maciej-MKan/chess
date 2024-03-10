package pl.mkan.game.engine.figures;

import lombok.AllArgsConstructor;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class King extends Figure {

    private final FigureColor color;
    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        List<FigureMove> moves = new ArrayList<>();
        addKingMoves(moves, true);
        addKingMoves(moves, false);
        return moves;
    }

    private void addKingMoves(List<FigureMove> moves, boolean haveToCapture) {
        moves.add(new FigureMove(-1, -1, false, haveToCapture, false));
        moves.add(new FigureMove(-1, 1, false, haveToCapture, false));
        moves.add(new FigureMove(1, -1, false, haveToCapture, false));
        moves.add(new FigureMove(0, 1, false, haveToCapture, false));
        moves.add(new FigureMove(1, 0, false, haveToCapture, false));
        moves.add(new FigureMove(0, -1, false, haveToCapture, false));
        moves.add(new FigureMove(-1, 0, false, haveToCapture, false));
    }

    @Override
    public String toString() {
        return getColorSymbol() + "K";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}