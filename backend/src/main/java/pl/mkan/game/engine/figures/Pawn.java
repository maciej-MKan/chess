package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;
import pl.mkan.game.engine.MoveType;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    private final FigureColor color;

    public Pawn(int id, FigureColor color) {
        super.id = id;
        super.isFirstMove = true;
        this.color = color;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        List<FigureMove> moves = new ArrayList<>();
        moves.add(new FigureMove(0, 1, true, MoveType.NONE, false));
        moves.add(new FigureMove(0, 2, true, MoveType.NONE, true));
        moves.add(new FigureMove(-1, 1, true, MoveType.CAPTURE, false));
        moves.add(new FigureMove(1, 1, true, MoveType.CAPTURE, false));
        moves.add(new FigureMove(0, -1, true, MoveType.NONE, false));
        moves.add(new FigureMove(0, -2, true, MoveType.NONE, true));
        moves.add(new FigureMove(-1, -1, true, MoveType.CAPTURE, false));
        moves.add(new FigureMove(1, -1, true, MoveType.CAPTURE, false));
        moves.add(new FigureMove(1, 0, false, MoveType.ENPASSANT, false));
        moves.add(new FigureMove(-1, 0, false, MoveType.ENPASSANT, false));
        return moves;
    }

    @Override
    public String toString() {
        return getColorSymbol() + "P";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}



