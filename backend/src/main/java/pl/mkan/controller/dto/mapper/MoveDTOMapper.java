package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.MoveDTO;
import pl.mkan.game.engine.Move;

public class MoveDTOMapper {

    public static Move map(MoveDTO moveDTO) {
        return new Move(moveDTO.srcColumn(), moveDTO.srcRow(), moveDTO.destColumn(), moveDTO.destRow());
    }

    public static MoveDTO map(Move move) {
        return new MoveDTO(move.sourceCol(), move.sourceRow(), move.destCol(), move.destRow());
    }

}
