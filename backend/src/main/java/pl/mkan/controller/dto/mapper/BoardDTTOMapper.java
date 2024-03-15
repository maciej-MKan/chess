package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.PositionDTO;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.controller.dto.enums.PieceType;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDTTOMapper {

    public static List<PieceDTO> map(Board engineBoard) {
        List<PieceDTO> pieces = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = engineBoard.getFigure(col, row);
                if (!(figure instanceof None)) {
                    PieceType type = mapPiece(figure);
                    PieceColor color = mapColor(figure.getColor());
                    PositionDTO position = new PositionDTO(row, col);
                    pieces.add(new PieceDTO(type, color, position));
                }
            }
        }
        return pieces;
    }

    private static PieceColor mapColor(FigureColor color) {
        if (color == FigureColor.BLACK) {
            return PieceColor.BLACK;
        } else {
            return PieceColor.WHITE;
        }
    }

    private static PieceType mapPiece(Figure figure) {
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
}
