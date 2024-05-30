package pl.mkan.game.engine.figures;

import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;
import pl.mkan.game.engine.MoveType;

import java.util.ArrayList;
import java.util.List;
public class King extends Figure {

    private final FigureColor color;

    public King(int id, FigureColor color) {
        super.id = id;
        this.color = color;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<FigureMove> getPossibleMoves() {
        List<FigureMove> moves = new ArrayList<>();
        addKingMoves(moves, MoveType.CAPTURE);
        addKingMoves(moves, MoveType.NONE);
        addCastling(moves);
        return moves;
    }

    private void addKingMoves(List<FigureMove> moves, MoveType haveToCapture) {
        moves.add(new FigureMove(-1, -1, false, haveToCapture, false));
        moves.add(new FigureMove(-1, 1, false, haveToCapture, false));
        moves.add(new FigureMove(1, 1, false, haveToCapture, false));
        moves.add(new FigureMove(1, -1, false, haveToCapture, false));
        moves.add(new FigureMove(0, 1, false, haveToCapture, false));
        moves.add(new FigureMove(1, 0, false, haveToCapture, false));
        moves.add(new FigureMove(0, -1, false, haveToCapture, false));
        moves.add(new FigureMove(-1, 0, false, haveToCapture, false));
    }

    private void addCastling(List<FigureMove> moves) {
        moves.add(new FigureMove(3, 0, false, MoveType.CASTLING, true));
        moves.add(new FigureMove(4, 0, false, MoveType.CASTLING, true));
        moves.add(new FigureMove(-3, 0, false, MoveType.CASTLING, true));
        moves.add(new FigureMove(-4, 0, false, MoveType.CASTLING, true));
    }

    @Override
    public String toString() {
        return getColorSymbol() + "K";
    }

    private String getColorSymbol() {
        return (color == FigureColor.WHITE) ? "W" : "B";
    }
}