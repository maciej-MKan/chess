package pl.mkan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.*;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.controller.dto.enums.PieceType;
import pl.mkan.controller.dto.mapper.*;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.mkan.game.engine.board.Utils.generatePossibleMoves;

@Slf4j
@Service
public class GameService {
    public BoardDTO getMove(MoveRequestDTO request) {
        GameOverDTO gameOver = checkGameOver(MoveRequestDTOMapper.map(request));
        PieceColor playerColor = request.playerColor();
        if (gameOver.isGameOver()) {
            return MoveRequestDTOMapper.map(request);
        }
        Board engineBoard = BoardDTOMapper.map(request.pieces(), playerColor);
        Move move = request.move() != null ?
                MoveDTOMapper.map(request.move()) :
                new Move(0, 0, 0, 0);

        if (engineBoard.isEnPassant(move)) {
            log.info("EnPassant move detected");
            engineBoard.setEnPassantDestPosition(move);
        }
        if (engineBoard.isCastling(move)) {
            log.info("Castling move detected");
            engineBoard.setCastlingDestPosition(move);
        }
        log.info("Board state before move:\n{}", engineBoard);
        Move AIMove = engineBoard.AIMove();
        log.info("Board state after AI move:\n{}", engineBoard);
        return new BoardDTO(BoardDTOMapper.map(engineBoard), MoveDTOMapper.map(AIMove));
    }

    public BoardDTO makeNewBoard(PieceColor playerColor) {
        Board engineBoard = new Board(PieceColorMapper.mapColor(playerColor));
        engineBoard.init();

        return new BoardDTO(BoardDTOMapper.map(engineBoard), null);
    }

    public BoardDTO makeTestBoard() {
        Board engineBoard = new Board();
        engineBoard.testInit();

        return new BoardDTO(BoardDTOMapper.map(engineBoard), null);
    }

    public AvailableMovesDTO calculateAvailableMoves(AvailableMovesRequestDTO gameState) {
        Board engineBoard = BoardDTOMapper.map(gameState.pieces(), gameState.playerColor());
        log.info("Board view: \n{}", engineBoard);
        engineBoard.switchWhoseMove();
        try {
            engineBoard.setPreMove(MoveDTOMapper.map(gameState.preMove()));
        } catch (NullPointerException e) {
            log.info("Game state has no pre move");
        }
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
        PieceDTO pawnToPromotion = board.pieces().stream().
                filter(p -> p.type() == PieceType.PAWN).
                filter(
                        p -> (p.position().row() == 0 &&
                                p.position().column() == board.move().destColumn()) ||
                                (p.position().row() == 7 &&
                                        p.position().column() == board.move().destColumn())
                )
                .findAny()
                .orElse(null);

        List<PieceDTO> figuresToPromote = new ArrayList<>();
        if (pawnToPromotion != null) figuresToPromote.addAll(generateFiguresToPromote(pawnToPromotion));
        return new GameStateDTO(gameOver, new PawnPromotionDTO(pawnToPromotion, figuresToPromote));
    }

    private Collection<PieceDTO> generateFiguresToPromote(PieceDTO pawnToPromotion) {
        int id = pawnToPromotion.id();
        PieceColor color = pawnToPromotion.color();
        PositionDTO position = pawnToPromotion.position();
        Boolean moved = pawnToPromotion.moved();
        List<String> figuresNames = List.of("QUEEN", "ROOK", "BISHOP", "KNIGHT");
        List<PieceDTO> figuresToPromote = new ArrayList<>();

        for (String figureName : figuresNames) {
            figuresToPromote.add(new PieceDTO(id, PieceType.valueOf(figureName), color, position, moved));
        }

        return figuresToPromote;
    }
}
