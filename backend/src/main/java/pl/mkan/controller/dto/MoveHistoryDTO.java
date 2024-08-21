package pl.mkan.controller.dto;

public record MoveHistoryDTO(String desc, BoardDTO state, MoveDTO move, String whoseMove) {
}
