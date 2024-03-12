package pl.mkan.game;

import pl.mkan.game.dialog.text.UserDialogs;
import pl.mkan.game.engine.AI;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.board.BoardOrientation;

public class TestApp {
    public static void main(String[] args) {
        Board board = new Board(BoardOrientation.BLACK_ON_TOP, true);
        board.init();
        while (true) {
            System.out.println(board);
            Move move = UserDialogs.getNextMove();
            if (board.checkMove(move)) {
                board.move(move);
                if (board.isGameWithComputer()) {
                    Move computenMove = AI.getBestMove(board, board.getWhoseMove());
                    System.out.println("Computer move: " + computenMove);
                    board.move(computenMove);
                }
            } else {
                System.out.println("bad move");
            }
        }

    }
}
