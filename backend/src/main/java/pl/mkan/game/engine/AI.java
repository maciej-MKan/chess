package pl.mkan.game.engine;

import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.board.BoardOrientation;

import java.util.ArrayList;
import java.util.List;

public class AI {
    public static Move getBestMove(Board board) {
        FigureColor computerColor = (board.getBoardOrientation() == BoardOrientation.WHITE_ON_TOP) ?
                FigureColor.WHITE : FigureColor.BLACK;
        List<Move> allPossibleMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.getFigure(col, row).getColor() == computerColor){
                    addPossibleMoves(col, row, allPossibleMoves, board);
                }
            }
        }
        return null;
    }

    private static void addPossibleMoves(int col, int row, List<Move> allPossibleMoves, Board board) {
        for (FigureMove potentialMove : board.getFigure(col, row).getPossibleMoves()) {
            Board testBoard = board.deepCopy();
            Move moveToCheck = new Move(col, row, potentialMove.getColumn() + col, potentialMove.getRow() + row);
            if (testBoard.move(moveToCheck)){
                allPossibleMoves.add(moveToCheck);
            }
        }
    }
}
