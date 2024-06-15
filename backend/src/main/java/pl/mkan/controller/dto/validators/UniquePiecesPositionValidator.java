package pl.mkan.controller.dto.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.controller.dto.MoveRequestDTO;
import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.PositionDTO;

import java.util.HashSet;
import java.util.Set;

public class UniquePiecesPositionValidator implements
        ConstraintValidator<UniquePiecesPosition, Object> {

    @Override
    public boolean isValid(Object board, ConstraintValidatorContext constraintValidatorContext) {
        if (board == null) {
            return true;
        }

        Set<PositionDTO> positions = new HashSet<>();

        try {
            return validateBoard((BoardDTO) board, positions);
        } catch (ClassCastException e) {
            try {
                return validateBoard((MoveRequestDTO) board, positions);
            } catch (ClassCastException e2) {
                return false;
            }
        }
    }

    private boolean validateBoard(BoardDTO boardDTO, Set<PositionDTO> positions) {
        for (PieceDTO piece : boardDTO.pieces()) {
            if (!positions.add(piece.position())) {
                return false;
            }
        }
        return true;
    }

    private boolean validateBoard(MoveRequestDTO boardDTO, Set<PositionDTO> positions) {
        for (PieceDTO piece : boardDTO.pieces()) {
            if (!positions.add(piece.position())) {
                return false;
            }
        }
        return true;
    }

}
