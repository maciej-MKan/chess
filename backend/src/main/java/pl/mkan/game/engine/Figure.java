package pl.mkan.game.engine;

import java.util.List;

public interface Figure {
    FigureColor getColor();
    List<FigureMove> getPossibleMoves();
}
