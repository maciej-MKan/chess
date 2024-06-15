package pl.mkan.controller.dto.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.PositionDTO;

import java.util.HashSet;
import java.util.Set;

public class UniquePiecesPositionValidator implements
        ConstraintValidator<UniquePiecesPosition, BoardDTO> {

    @Override
    public boolean isValid(BoardDTO boardDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (boardDTO == null || boardDTO.pieces() == null) {
            return true;
        }

        Set<PositionDTO> positions = new HashSet<>();
        for (PieceDTO piece : boardDTO.pieces()) {
            if (!positions.add(piece.position())) {
                return false;
            }
        }
        return true;
    }

}
