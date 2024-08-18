package pl.mkan.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import pl.mkan.controller.dto.validators.UniquePiecesPosition;

import java.util.List;

@UniquePiecesPosition
public record BoardDTO(@Valid @NotEmpty List<PieceDTO> pieces, MoveDTO move, String gameId) {
}
