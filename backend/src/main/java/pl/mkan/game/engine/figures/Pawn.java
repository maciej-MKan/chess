package pl.mkan.game.engine.figures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mkan.game.engine.Figure;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.List;
@AllArgsConstructor
public class Pawn implements Figure {

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
        return getColorSymbol() + "P";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}
