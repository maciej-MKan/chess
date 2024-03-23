package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.PositionDTO;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.Figure;
import pl.mkan.game.engine.figures.None;

import java.util.ArrayList;
import java.util.List;

public class BoardDTOMapper {

    public static List<PieceDTO> map(Board engineBoard) {
        List<PieceDTO> pieces = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = engineBoard.getFigure(col, row);
                if (!(figure instanceof None)) {
                    pieces.add(
                            new PieceDTO(PieceDTOMapper.mapPiece(figure),
                                    PieceDTOMapper.mapColor(figure.getColor()),
                                    new PositionDTO(row, col))
                    );
                }
            }
        }
        return pieces;
    }


    public static Board map(List<PieceDTO> pieces) {
        Board board = new Board();
        for (PieceDTO piece : pieces) {
            board.setFigure(piece.position().column(), piece.position().row(), PieceDTOMapper.mapPiece(piece));
        }
        return board;
    }

}
