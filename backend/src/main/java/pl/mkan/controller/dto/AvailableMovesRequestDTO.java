package pl.mkan.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pl.mkan.controller.dto.enums.PieceColor;

import java.util.List;

public record AvailableMovesRequestDTO(
        @NotEmpty List<PieceDTO> pieces,
        @JsonProperty("move") MoveDTO preMove,
        @NotNull PieceColor playerColor
) {
}
