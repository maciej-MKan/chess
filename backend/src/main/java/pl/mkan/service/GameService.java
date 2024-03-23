package pl.mkan.service;

import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.AvailableMovesDTO;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.controller.dto.mapper.BoardDTOMapper;
import pl.mkan.controller.dto.mapper.MovesDTOMapper;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.Figure;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.mkan.game.engine.board.Utils.generatePossibleMoves;

@Service
public class GameService {
    public BoardDTO getMove(BoardDTO board) {
        Board engineBoard = BoardDTOMapper.map(board.pieces());
        System.out.println(engineBoard);
        engineBoard.AIMove();
        System.out.println(engineBoard);
        return new BoardDTO(BoardDTOMapper.map(engineBoard));
    }

    public BoardDTO makeNewBoard() {
        Board engineBoard = new Board();
        engineBoard.init();

        return new BoardDTO(BoardDTOMapper.map(engineBoard));
    }

    public AvailableMovesDTO calculateAvailableMoves(BoardDTO board) {
        Board engineBoard = BoardDTOMapper.map(board.pieces());
        List<Move> possibleMoves = generatePossibleMoves(engineBoard, engineBoard.getWhoseMove());

        Map<Figure, List<Move>> figureMovesMap = possibleMoves.stream()
                .collect(
                        Collectors.groupingBy(move -> engineBoard.getFigure(move.getSourceCol(), move.getSourceRow()))
                );
        return new AvailableMovesDTO(MovesDTOMapper.map(figureMovesMap));
    }
}
