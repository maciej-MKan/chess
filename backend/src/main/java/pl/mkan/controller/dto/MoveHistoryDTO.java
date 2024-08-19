package pl.mkan.controller.dto;

public record MoveHistoryDTO(String desc, BoardDTO boardState, MoveDTO move, String whoseMove) {
}
