package pl.mkan.game.engine;

import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.mkan.game.engine.board.Utils.oppositeColor;

public class AI {

    private static final int MAX_DEPTH = 2;
    private static final int alpha = Integer.MIN_VALUE;
    private static final int beta = Integer.MAX_VALUE;

    private static int search(Board board, FigureColor movingColor, int depth, int alpha, int beta){
        int best_score = Integer.MIN_VALUE;
        int move_score;

        List<Move> possibleMoves = generatePossibleMoves(board, movingColor);

        for(Move move : possibleMoves){
            board.move(move);

            if (depth == 0){
                move_score = evaluateBoard(board, movingColor);
            } else {
                move_score = - search(board, oppositeColor(movingColor), depth - 1, -alpha, -beta);
            }

            board.backMove();

            if (move_score > best_score) best_score = move_score;
            if (best_score > alpha) alpha = best_score;
            if (alpha >= beta) return alpha;
        }
        return best_score;
    }

    public static Move getBestMove(Board board, FigureColor color) {
        int score = search(board, color, MAX_DEPTH, alpha, beta);
        System.out.println(score);
//        Move bestMove = null;
//        int alpha = Integer.MIN_VALUE;
//        int beta = Integer.MAX_VALUE;
//
//        for (int depth = 1; depth <= MAX_DEPTH; depth++) {
//            int maxEval = Integer.MIN_VALUE;
//            List<Move> possibleMoves = generatePossibleMoves(board, color);
//            Move localBestMove = null;
//
//            for (Move move : possibleMoves) {
//                Board testBoard = board.deepCopy();
//                testBoard.move(move);
//                int eval = minimax(testBoard, oppositeColor(color), depth - 1, alpha, beta, false);
//                if (eval > maxEval) {
//                    maxEval = eval;
//                    localBestMove = move;
//                }
//            }
//
//            if (localBestMove != null) {
//                bestMove = localBestMove;
//            }
//        }

        return null;
    }

    private static int minimax(Board board, FigureColor activeColor, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0) {
            return evaluateBoard(board, activeColor);
        }

        List<Move> possibleMoves = generatePossibleMoves(board, activeColor);

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                Board testBoard = board.deepCopy();
                testBoard.move(move);
                int eval = minimax(testBoard, oppositeColor(activeColor), depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {
                Board testBoard = board.deepCopy();
                testBoard.move(move);
                int eval = minimax(testBoard, oppositeColor(activeColor), depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

    private static List<Move> generatePossibleMoves(Board board, FigureColor activeColor) {
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
        for (FigureMove potentialMove : board.getFigure(col, row).getPossibleMoves()) {
            Board testBoard = board.deepCopy();
            Move moveToCheck = new Move(col, row, potentialMove.getColumn() + col, potentialMove.getRow() + row);
            if (testBoard.checkMove(moveToCheck)) {
                allPossibleMoves.add(moveToCheck);
            }
        }
    }

    private static int evaluateBoard(Board board, FigureColor activeColor) {
        int playerScore = 0;
        int opponentScore = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = board.getFigure(col, row);
                if (!(figure instanceof None)) {
                    int figureValue = getFigureValue(figure);
                    if (figure.getColor() == activeColor) {
                        playerScore += figureValue;
                    } else {
                        opponentScore += figureValue;
                    }
                }
            }
        }

        return opponentScore - playerScore;
    }

    private static int getFigureValue(Figure figure) {
        Map<Class<? extends Figure>, Integer> figureScores = Map.of(
                Pawn.class, 1,
                Bishop.class, 5,
                Knight.class, 5,
                Rook.class, 10,
                Queen.class, 20
        );
        return figureScores.getOrDefault(figure.getClass(), 0);
    }
}
