package pl.mkan.game.engine;

import lombok.extern.slf4j.Slf4j;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.board.Position;
import pl.mkan.game.engine.figures.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.mkan.game.engine.board.Utils.generatePossibleMoves;
import static pl.mkan.game.engine.board.Utils.oppositeColor;

@Slf4j
public class AI {

    private static final int MAX_DEPTH = 3;
    private static final int ALPHA_INITIAL = Integer.MIN_VALUE;
    private static final int BETA_INITIAL = Integer.MAX_VALUE;
    private static final double DEPTH_PENALTY = 0.3;

    private static int search(Board board, FigureColor movingColor, int depth, int alpha, int beta) {
        List<Move> possibleMoves = generatePossibleMoves(board, movingColor);

        if (possibleMoves.isEmpty() || depth == 0) {
            return evaluateBoard(board, movingColor, depth);
        }

        for (Move move : possibleMoves) {
            board.move(move);
            int moveScore = -search(board, movingColor, depth - 1, -beta, -alpha);
            board.backMove();

            if (moveScore > alpha) {
                alpha = moveScore;
            }
            if (alpha >= beta) {
                break;
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
            int score = search(testBoard, oppositeColor(color), MAX_DEPTH, ALPHA_INITIAL, BETA_INITIAL);
            moveScores.put(move, score);
        }

        moveScores.forEach((move, score) -> log.info("Move: {} has score: {}", move, score));

        return moveScores.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No valid moves"));
    }

    private static int evaluateBoard(Board board, FigureColor activeColor, int depth) {
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
        int baseScore = playerScore - opponentScore;

        double depthFactor = Math.pow(DEPTH_PENALTY, depth + 1);
        return (int) (baseScore * depthFactor);
    }

    private static int getFigureValue(Figure figure) {
        Map<Class<? extends Figure>, Integer> figureScores = Map.of(
                Pawn.class, 10,
                Bishop.class, 50,
                Knight.class, 50,
                Rook.class, 100,
                Queen.class, 200,
                King.class, 1000
        );
        return figureScores.getOrDefault(figure.getClass(), 0);
    }

    public static void promotePawn(Board board, Pawn pawn) {
        Position promotePosition = board.findFigurePositionById(pawn.getId())
                .orElseThrow(() -> new RuntimeException("Pawn to promote doesn't exist"));

        board.setFigure(promotePosition.col(), promotePosition.row(), new Queen(pawn.getId(), pawn.getColor()));
    }
}
