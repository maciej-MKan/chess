package pl.mkan.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AvailableMovesRequestDTO(@NotEmpty List<PieceDTO> pieces, @JsonProperty("move") MoveDTO preMove) {
}
