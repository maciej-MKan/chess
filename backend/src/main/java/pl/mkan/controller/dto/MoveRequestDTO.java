package pl.mkan.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.controller.dto.validators.UniquePiecesPosition;

import java.util.List;

@UniquePiecesPosition
public record MoveRequestDTO(@Valid @NotEmpty List<PieceDTO> pieces, MoveDTO move, @NotNull PieceColor playerColor) {
}
