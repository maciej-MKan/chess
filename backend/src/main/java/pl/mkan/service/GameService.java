package pl.mkan.service;

import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.controller.dto.mapper.BoardDTOMapper;
import pl.mkan.game.engine.board.Board;

@Service
public class GameService {
    public BoardDTO move(BoardDTO board) {
        Board engineBoard = BoardDTOMapper.map(board.pieces());
        System.out.println(engineBoard);
        engineBoard.AIMove();
        System.out.println(engineBoard);
        return new BoardDTO(BoardDTOMapper.map(engineBoard));
    }

    public BoardDTO newBoard() {
        Board engineBoard = new Board();
        engineBoard.init();

        return new BoardDTO(BoardDTOMapper.map(engineBoard));
    }
}
