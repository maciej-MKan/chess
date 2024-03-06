package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.List;

public interface Figure {
    FigureColor getColor();
    List<FigureMove> getPossibleMoves();
}
