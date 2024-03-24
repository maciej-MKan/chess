package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure {

    private final FigureColor color;

    public Rook(int id, FigureColor color) {
        super.id = id;
        this.color = color;
    }

    private void addRookMoves(List<FigureMove> moves, boolean haveToCapture) {
        for (int col = -7; col < 8; col++) {
            if (col == 0) continue;
            moves.add(new FigureMove(col, 0, false, haveToCapture, false));
        }
        for (int row = -7; row < 8; row++) {
            if (row == 0) continue;
            moves.add(new FigureMove(0, row, false, haveToCapture, false));
        }
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        List<FigureMove> moves = new ArrayList<>();
        addRookMoves(moves, true);
        addRookMoves(moves, false);
        return moves;
    }

    @Override
    public String toString() {
        return getColorSymbol() + "R";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}
