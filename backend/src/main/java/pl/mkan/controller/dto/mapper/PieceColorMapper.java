package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.game.engine.FigureColor;

public class PieceColorMapper {

    public static PieceColor mapColor(FigureColor color) {
        return color == FigureColor.BLACK ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public static FigureColor mapColor(PieceColor color) {
        return color == PieceColor.BLACK ? FigureColor.BLACK : FigureColor.WHITE;
    }
}
