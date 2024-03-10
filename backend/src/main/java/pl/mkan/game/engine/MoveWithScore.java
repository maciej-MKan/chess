package pl.mkan.game.engine;

import java.util.Map;

public record MoveWithScore(Move move, Map<FigureColor, Integer> score) {
}
