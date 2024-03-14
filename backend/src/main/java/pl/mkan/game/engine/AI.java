package pl.mkan.game.engine;

import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.*;

import java.util.*;

import static pl.mkan.game.engine.board.Utils.oppositeColor;

public class AI {

    private static final int MAX_DEPTH = 2;
    private static final int alpha = Integer.MIN_VALUE;
    private static final int beta = Integer.MAX_VALUE;

    private static int search(Board board, FigureColor movingColor, int depth, int alpha, int beta) {
        List<Move> possibleMoves = generatePossibleMoves(board, movingColor);

        if (possibleMoves.isEmpty() || depth == 0) {
            return evaluateBoard(board, movingColor);
        }

        for (Move move : possibleMoves) {
            board.move(move);
            int move_score = search(board, movingColor, depth - 1, -beta, -alpha) * depth;
            board.backMove();

            if (move_score > alpha) {
                alpha = move_score;
            }
            if (alpha >= beta) {
                return alpha;
            }
        }
        return alpha;
    }

    public static Move getBestMove(Board board, FigureColor color) {
        Map<Move, Integer> moveScores = new HashMap<>();

        for (Move move : generatePossibleMoves(board, color)) {
            Board testBoard = board.deepCopy();
            testBoard.move(move);
            int score = search(testBoard, oppositeColor(color), MAX_DEPTH, alpha, beta);
            moveScores.put(move, score);
        }

        return moveScores.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("sth wrong"));
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

        return playerScore - opponentScore;
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
