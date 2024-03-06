package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.List;

public class None implements Figure {
    @Override
    public FigureColor getColor() {
        return FigureColor.NONE;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        return null;
    }

    @Override
    public String toString() {
        return "  ";
    }

}
