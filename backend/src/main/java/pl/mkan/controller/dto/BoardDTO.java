package pl.mkan.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record BoardDTO(@Valid @NotEmpty List<PieceDTO> pieces) {
}
