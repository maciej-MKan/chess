package pl.mkan.game.engine;

import pl.mkan.game.engine.figures.*;

public class FigureFactory {

    public static Figure createFigureCopy(Figure figure) {
        FigureColor color = figure.getColor();
        if (figure.getClass().equals(Pawn.class)) {
            return new Pawn(color);
        } else if (figure.getClass().equals(Rook.class)) {
            return new Rook(color);
        } else if (figure.getClass().equals(Knight.class)) {
            return new Knight(color);
        } else if (figure.getClass().equals(Bishop.class)) {
            return new Bishop(color);
        } else if (figure.getClass().equals(King.class)) {
            return new King(color);
        } else if (figure.getClass().equals(Queen.class)) {
            return new Queen(color);
        } else {
            return new None();
        }
    }
}
