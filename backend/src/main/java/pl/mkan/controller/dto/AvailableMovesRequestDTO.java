package pl.mkan.controller.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AvailableMovesRequestDTO(@NotEmpty List<PieceDTO> pieces, MoveDTO prevMove) {
}
