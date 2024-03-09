package pl.mkan.game.engine;

import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.board.BoardOrientation;
import pl.mkan.game.engine.figures.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AI {
    public static Move getBestMove(Board board) {
        FigureColor computerColor = (board.getBoardOrientation() == BoardOrientation.WHITE_ON_TOP) ?
                FigureColor.WHITE : FigureColor.BLACK;
        List<MoveWithScore> allPossibleMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.getFigure(col, row).getColor() == computerColor){
                    addPossibleMoves(col, row, allPossibleMoves, board);
                }
            }
        }
        Integer best = allPossibleMoves.stream()
                .map(move -> (move.score().blackScore() - move.score().whiteScore()))
                .max(Comparator.comparingInt(dif -> dif))
                .get();
        return null;
    }

    private static void addPossibleMoves(int col, int row, List<MoveWithScore> allPossibleMoves, Board board) {
        for (FigureMove potentialMove : board.getFigure(col, row).getPossibleMoves()) {
            Board testBoard = board.deepCopy();
            Move moveToCheck = new Move(col, row, potentialMove.getColumn() + col, potentialMove.getRow() + row);
            if (testBoard.move(moveToCheck)){
                Score score = calcScore(testBoard);
                allPossibleMoves.add(new MoveWithScore(moveToCheck, score));
            }
        }
    }

    private static Score calcScore(Board board) {
        int whiteScore = 0;
        int blackScore = 0;
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Figure figure = board.getFigure(col, row);
                int score = calcScoreForFigure(figure);
                if (figure.getColor() == FigureColor.WHITE){
                    whiteScore += score;
                } else if (figure.getColor() == FigureColor.BLACK) {
                    blackScore += score;
                }
            }
        }
        return new Score(whiteScore, blackScore);
    }

    private static int calcScoreForFigure(Figure figure) {
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
