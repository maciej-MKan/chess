package pl.mkan.game.engine;

import lombok.extern.slf4j.Slf4j;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.mkan.game.engine.board.Utils.generatePossibleMoves;
import static pl.mkan.game.engine.board.Utils.oppositeColor;

@Slf4j
public class AI {

    private static final int MAX_DEPTH = 1;
    private static final int alpha = Integer.MIN_VALUE;
    private static final int beta = Integer.MAX_VALUE;

    private static int search(Board board, FigureColor movingColor, int depth, int alpha, int beta) {
        List<Move> possibleMoves = generatePossibleMoves(board, movingColor);

        if (possibleMoves.isEmpty() || depth == 0) {
            return evaluateBoard(board, movingColor);
        }

        for (Move move : possibleMoves) {
            board.move(move);
            int move_score = -search(board, movingColor, depth - 1, -beta, -alpha);
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
        List<Move> moves = generatePossibleMoves(board, color);

        for (Move move : moves) {
            Board testBoard = board.deepCopy();
            testBoard.move(move);
            int score = search(testBoard, oppositeColor(color), MAX_DEPTH, alpha, beta);
            moveScores.put(move, score);
        }

        log.debug(
                moveScores.entrySet().stream()
                        .map(moveEntry -> "move " + moveEntry.getKey() + " has score " + moveEntry.getValue())
                        .toString()
        );

        return moveScores.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("sth wrong"));
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
                Queen.class, 20,
                King.class, 100
        );
        return figureScores.getOrDefault(figure.getClass(), 0);
    }
}
