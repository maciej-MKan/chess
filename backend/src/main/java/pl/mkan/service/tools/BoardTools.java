package pl.mkan.service.tools;

import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.PositionDTO;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.controller.dto.enums.PieceType;

import java.util.ArrayList;
import java.util.List;

public class BoardTools {
    public static List<PieceDTO> generateStartingPosition() {
        List<PieceDTO> pieces = new ArrayList<>();

        // Białe figury
        pieces.add(new PieceDTO(PieceType.ROOK, PieceColor.WHITE, new PositionDTO(0, 0)));
        pieces.add(new PieceDTO(PieceType.KNIGHT, PieceColor.WHITE, new PositionDTO(0, 1)));
        pieces.add(new PieceDTO(PieceType.BISHOP, PieceColor.WHITE, new PositionDTO(0, 2)));
        pieces.add(new PieceDTO(PieceType.QUEEN, PieceColor.WHITE, new PositionDTO(0, 3)));
        pieces.add(new PieceDTO(PieceType.KING, PieceColor.WHITE, new PositionDTO(0, 4)));
        pieces.add(new PieceDTO(PieceType.BISHOP, PieceColor.WHITE, new PositionDTO(0, 5)));
        pieces.add(new PieceDTO(PieceType.KNIGHT, PieceColor.WHITE, new PositionDTO(0, 6)));
        pieces.add(new PieceDTO(PieceType.ROOK, PieceColor.WHITE, new PositionDTO(0, 7)));

        for (int i = 0; i < 8; i++) {
            pieces.add(new PieceDTO(PieceType.PAWN, PieceColor.WHITE, new PositionDTO(1, i)));
        }

        // Czarne figury
        pieces.add(new PieceDTO(PieceType.ROOK, PieceColor.BLACK, new PositionDTO(7, 0)));
        pieces.add(new PieceDTO(PieceType.KNIGHT, PieceColor.BLACK, new PositionDTO(7, 1)));
        pieces.add(new PieceDTO(PieceType.BISHOP, PieceColor.BLACK, new PositionDTO(7, 2)));
        pieces.add(new PieceDTO(PieceType.QUEEN, PieceColor.BLACK, new PositionDTO(7, 3)));
        pieces.add(new PieceDTO(PieceType.KING, PieceColor.BLACK, new PositionDTO(7, 4)));
        pieces.add(new PieceDTO(PieceType.BISHOP, PieceColor.BLACK, new PositionDTO(7, 5)));
        pieces.add(new PieceDTO(PieceType.KNIGHT, PieceColor.BLACK, new PositionDTO(7, 6)));
        pieces.add(new PieceDTO(PieceType.ROOK, PieceColor.BLACK, new PositionDTO(7, 7)));

        for (int i = 0; i < 8; i++) {
            pieces.add(new PieceDTO(PieceType.PAWN, PieceColor.BLACK, new PositionDTO(6, i)));
        }

        return pieces;
    }
}
