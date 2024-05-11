package pl.mkan.controller.dto;

import java.util.List;

public record PawnPromotionDTO(PieceDTO pawn, List<PieceDTO> figuresToPromote) {
}
