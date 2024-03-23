package pl.mkan.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

public record AvailableMovesDTO(@Valid @NotEmpty Map<PieceDTO, List<PositionDTO>> availableMoves) {
}
