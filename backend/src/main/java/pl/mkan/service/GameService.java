package pl.mkan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.*;
import pl.mkan.controller.dto.enums.PieceType;
import pl.mkan.controller.dto.mapper.BoardDTOMapper;
import pl.mkan.controller.dto.mapper.MoveDTOMapper;
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
        Move move = engineBoard.AIMove();
        log.info("Board state after move:\n{}", engineBoard);
        return new BoardDTO(BoardDTOMapper.map(engineBoard), MoveDTOMapper.map(move));
    }

    public BoardDTO makeNewBoard() {
        Board engineBoard = new Board();
        engineBoard.init();

        return new BoardDTO(BoardDTOMapper.map(engineBoard), null);
    }

    public AvailableMovesDTO calculateAvailableMoves(AvailableMovesRequestDTO gameState) {
        Board engineBoard = BoardDTOMapper.map(gameState.pieces());
        Move prevMove = gameState.prevMove() != null ?
                MoveDTOMapper.map(gameState.prevMove()) :
                new Move(0, 0, 0, 0);

        engineBoard.switchWhoseMove();

        List<Move> possibleMoves = generatePossibleMoves(engineBoard, engineBoard.getWhoseMove(), prevMove);

        Map<Figure, List<Move>> figureMovesMap = possibleMoves.stream()
                .collect(
                        Collectors.groupingBy(move -> engineBoard.getFigure(move.sourceCol(), move.sourceRow()))
                );
        return new AvailableMovesDTO(MovesDTOMapper.map(figureMovesMap));
    }

    public GameOverDTO checkGameOver(BoardDTO board) {
        List<PieceDTO> kingsOnBoard = board.pieces().stream()
                .filter(pieceDTO -> pieceDTO.type().equals(PieceType.KING))
                .toList();

        if (kingsOnBoard.size() == 1) {
            return new GameOverDTO(true, kingsOnBoard.get(0).color());
        }
        return new GameOverDTO(false, null);
    }
}
