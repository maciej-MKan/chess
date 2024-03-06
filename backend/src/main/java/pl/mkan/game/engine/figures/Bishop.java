package pl.mkan.game.engine.figures;

import lombok.AllArgsConstructor;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.List;
@AllArgsConstructor
public class Bishop implements Figure {

    private final FigureColor color;
    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        return null;
    }

    @Override
    public String toString() {
        return getColorSymbol() + "B";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}