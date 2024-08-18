package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.controller.dto.MoveRequestDTO;

public class MoveRequestDTOMapper {

    public static BoardDTO map(MoveRequestDTO requestDTO) {
        return new BoardDTO(requestDTO.pieces(), requestDTO.move(), requestDTO.gameId());
    }
}
