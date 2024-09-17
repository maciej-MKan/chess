package pl.mkan.controller.dto;

import jakarta.annotation.Nullable;
import pl.mkan.controller.dto.enums.PieceColor;

import java.time.ZonedDateTime;
import java.util.List;

public record GameHistoryDTO(BoardDTO actualBoardState, List<MoveHistoryDTO> movesHistory,
                             PieceColor playerColor, ZonedDateTime gameStartDate, @Nullable GameOverDTO gameState) {
}
