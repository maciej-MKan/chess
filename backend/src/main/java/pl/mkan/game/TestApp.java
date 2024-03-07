package pl.mkan.game;

import pl.mkan.game.dialog.text.UserDialogs;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.board.BoardOrientation;

public class TestApp {
    public static void main(String[] args) {
        Board board = new Board(BoardOrientation.WHITE_ON_TOP);
        board.init();
        while(true){
            System.out.println(board);
            Move move = UserDialogs.getNextMove();
            if (board.move(move)){

            }
        }

    }
}
