package pl.mkan.controller.dto;

import pl.mkan.controller.dto.enums.PieceColor;

import java.util.List;

public record GameHistoryDTO(BoardDTO actualBoardState, List<MoveHistoryDTO> movesHistory,
                             PieceColor playerColor) {
}
