package pl.mkan.game.engine;

import pl.mkan.game.engine.figures.*;

public class FigureFactory {

    public static Figure createFigureCopy(Figure figure) {
        FigureColor color = figure.getColor();
        int id = figure.getId();
        if (figure.getClass().equals(Pawn.class)) {
            return new Pawn(id, color);
        } else if (figure.getClass().equals(Rook.class)) {
            return new Rook(id, color);
        } else if (figure.getClass().equals(Knight.class)) {
            return new Knight(id, color);
        } else if (figure.getClass().equals(Bishop.class)) {
            return new Bishop(id, color);
        } else if (figure.getClass().equals(King.class)) {
            return new King(id, color);
        } else if (figure.getClass().equals(Queen.class)) {
            return new Queen(id, color);
        } else {
            return new None();
        }
    }
}
