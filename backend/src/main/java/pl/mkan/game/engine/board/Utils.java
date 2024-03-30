package pl.mkan.game.engine.board;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;
import pl.mkan.game.engine.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
    public static FigureColor oppositeColor(FigureColor color) {
        return (color == FigureColor.WHITE) ? FigureColor.BLACK : FigureColor.WHITE;
    }

    public static List<Move> generatePossibleMoves(Board board, FigureColor activeColor) {
        List<Move> allPossibleMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.getFigure(col, row).getColor() == activeColor) {
                    addPossibleMoves(col, row, allPossibleMoves, board);
                }
            }
        }
        return allPossibleMoves;
    }

    private static void addPossibleMoves(int col, int row, List<Move> allPossibleMoves, Board board) {
        List<FigureMove> possibleMoves = board.getFigure(col, row).getPossibleMoves();
        Set<Move> moves = new HashSet<>();
        for (FigureMove potentialMove : possibleMoves) {
            Board testBoard = board.deepCopy();
            Move moveToCheck = new Move(col, row, potentialMove.getColumn() + col, potentialMove.getRow() + row);
            if (testBoard.checkMove(moveToCheck)) {
                moves.add(moveToCheck);
            }
        }
        allPossibleMoves.addAll(moves);
    }
}
