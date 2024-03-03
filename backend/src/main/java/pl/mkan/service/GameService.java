package pl.mkan.service;

import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.service.tools.BoardTools;

import java.util.List;

@Service
public class GameService {
    public BoardDTO move(BoardDTO board) {
        return null;
    }

    public BoardDTO newBoard() {
        return new BoardDTO(BoardTools.generateStartingPosition());
    }
}
