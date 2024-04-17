package pl.mkan.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public record AvailableMovesRequestDTO(@NotEmpty BoardDTO boardState, MoveDTO prevMove) {
}
