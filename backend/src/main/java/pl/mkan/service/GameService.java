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
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.mkan.game.engine.board.Utils.generatePossibleMoves;

@Slf4j
@Service
public class GameService {
    public BoardDTO getMove(BoardDTO board) {
        Board engineBoard = BoardDTOMapper.map(board.pieces());
        Move move = board.move() != null ?
                MoveDTOMapper.map(board.move()) :
                new Move(0, 0, 0, 0);
        if (engineBoard.isEnPassant(move)) {
            engineBoard.setEnPassantDestPosition(move);
        }
        log.info("Board state before move:\n{}", engineBoard);
        Move AImove = engineBoard.AIMove();
        log.info("Board state after AI move:\n{}", engineBoard);
        return new BoardDTO(BoardDTOMapper.map(engineBoard), MoveDTOMapper.map(AImove));
    }

    public BoardDTO makeNewBoard() {
        Board engineBoard = new Board();
        engineBoard.init();

        return new BoardDTO(BoardDTOMapper.map(engineBoard), null);
    }

    public AvailableMovesDTO calculateAvailableMoves(AvailableMovesRequestDTO gameState) {
        Board engineBoard = BoardDTOMapper.map(gameState.pieces());
        engineBoard.switchWhoseMove();
        List<Move> possibleMoves = generatePossibleMoves(engineBoard, engineBoard.getWhoseMove());

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

    public GameStateDTO checkGameState(BoardDTO board) {
        GameOverDTO gameOver = checkGameOver(board);
        Optional<PieceDTO> pawnToPromotion = board.pieces().stream().
                filter(p -> p.type() == PieceType.PAWN).
                filter(p -> p.position().row() == 0 || p.position().row() == 7).findAny();
        return new GameStateDTO(gameOver, new PawnPromotionDTO(pawnToPromotion.orElse(null), List.of()));
    }
}
