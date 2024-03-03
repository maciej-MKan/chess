package pl.mkan.controller.dto;

import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.controller.dto.enums.PieceType;

public record PieceDTO(PieceType type, PieceColor color, PositionDTO position) {
}
