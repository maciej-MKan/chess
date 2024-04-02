package pl.mkan.controller.dto;

import pl.mkan.controller.dto.enums.PieceColor;

public record GameOverDTO(Boolean isGameOver, PieceColor winner) {
}
