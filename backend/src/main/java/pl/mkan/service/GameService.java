package pl.mkan.service;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class GameService {
    public BoardDTO getMove(BoardDTO board) {
        Board engineBoard = BoardDTOMapper.map(board.pieces());
        log.info("Board state before move:\n{}", engineBoard);
        engineBoard.AIMove();
        log.info("Board state after move:\n{}", engineBoard);
        return new BoardDTO(BoardDTOMapper.map(engineBoard));
    }

    public BoardDTO makeNewBoard() {
        Board engineBoard = new Board();
        engineBoard.init();

        return new BoardDTO(BoardDTOMapper.map(engineBoard));
    }

    public AvailableMovesDTO calculateAvailableMoves(BoardDTO board) {
        Board engineBoard = BoardDTOMapper.map(board.pieces());
        engineBoard.switchWhoseMove();
        List<Move> possibleMoves = generatePossibleMoves(engineBoard, engineBoard.getWhoseMove());

        Map<Figure, List<Move>> figureMovesMap = possibleMoves.stream()
                .collect(
                        Collectors.groupingBy(move -> engineBoard.getFigure(move.sourceCol(), move.sourceRow()))
                );
        return new AvailableMovesDTO(MovesDTOMapper.map(figureMovesMap));
    }
}
