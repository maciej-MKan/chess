package pl.mkan.game;

import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.figures.Rook;
import pl.mkan.game.engine.figures.Pawn;

public class TestApp {
    public static void main(String[] args) {
        Board board = new Board();
        board.setFigure(0, 0, new Rook(FigureColor.BLACK));
        board.setFigure(0, 6, new Pawn(FigureColor.WHITE));
        System.out.println(board);
        board.move(new Move(0, 6, 0, 5));
        System.out.println(board);
    }
}
