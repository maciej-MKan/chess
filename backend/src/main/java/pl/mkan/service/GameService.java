package pl.mkan.service;

import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.controller.dto.mapper.BoardDTTOMapper;
import pl.mkan.game.engine.board.Board;
import pl.mkan.service.tools.BoardTools;

@Service
public class GameService {
    public BoardDTO move(BoardDTO board) {
        System.out.println(BoardDTTOMapper.map(board.pieces()));
        return null;
    }

    public BoardDTO newBoard() {
        Board engineBoard = new Board();
        engineBoard.init();

        return new BoardDTO(BoardDTTOMapper.map(engineBoard));
    }
}
