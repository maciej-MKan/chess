package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.controller.dto.enums.PieceType;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.figures.*;

import java.util.Map;

public class PieceDTOMapper {
    public static PieceColor mapColor(FigureColor color) {
        return color == FigureColor.BLACK ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public static PieceType mapPiece(Figure figure) {
        Map<Class<? extends Figure>, PieceType> figureMapper = Map.of(
                Pawn.class, PieceType.PAWN,
                Bishop.class, PieceType.BISHOP,
                Knight.class, PieceType.KNIGHT,
                Rook.class, PieceType.ROOK,
                Queen.class, PieceType.QUEEN,
                King.class, PieceType.KING
        );

        return figureMapper.get(figure.getClass());
    }

    public static Figure mapPiece(PieceDTO piece) {
        Figure figure;

        Map<PieceType, Class<? extends Figure>> figureMapper = Map.of(
                PieceType.PAWN, Pawn.class,
                PieceType.BISHOP, Bishop.class,
                PieceType.KNIGHT, Knight.class,
                PieceType.ROOK, Rook.class,
                PieceType.QUEEN, Queen.class,
                PieceType.KING, King.class
        );

        FigureColor color = mapColor(piece.color());
        int id = piece.id();
        boolean isFigureFirstMove = !piece.moved();
        Class<? extends Figure> figureClass = figureMapper.get(piece.type());
        try {
            figure = figureClass.getDeclaredConstructor(int.class, FigureColor.class).newInstance(id, color);
            if (!isFigureFirstMove) figure.setMoved();
        } catch (Exception e) {
            return new None();
        }
        return figure;
    }

    public static FigureColor mapColor(PieceColor color) {
        return color == PieceColor.BLACK ? FigureColor.BLACK : FigureColor.WHITE;
    }
}
