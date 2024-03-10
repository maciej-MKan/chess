package pl.mkan.game.engine.board;

import pl.mkan.game.engine.FigureColor;

public class Utils {
    public static FigureColor oppositeColor(FigureColor color) {
        return (color == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }
}
