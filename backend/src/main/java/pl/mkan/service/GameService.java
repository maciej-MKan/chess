package pl.mkan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.*;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.controller.dto.enums.PieceType;
import pl.mkan.controller.dto.mapper.*;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.board.Board;
import pl.mkan.game.engine.figures.Figure;
import pl.mkan.persistence.model.GameHistory;
import pl.mkan.persistence.model.MoveHistory;
import pl.mkan.persistence.model.User;
import pl.mkan.persistence.repository.GameRepository;
import pl.mkan.persistence.repository.UserRepository;
import pl.mkan.service.tools.UserIdFactory;

import java.util.*;
import java.util.stream.Collectors;

import static pl.mkan.game.engine.board.Utils.generatePossibleMoves;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

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
        String gameId = request.gameId() != null ? request.gameId() : "empty";
        return new BoardDTO(BoardDTOMapper.map(engineBoard), MoveDTOMapper.map(AIMove), gameId);
    }

    public BoardDTO makeNewBoard(PieceColor playerColor) {
        Board engineBoard = new Board(PieceColorMapper.mapColor(playerColor));
        engineBoard.init();
        String gameId = UUID.randomUUID().toString();

        return new BoardDTO(BoardDTOMapper.map(engineBoard), null, gameId);
    }

    public BoardDTO makeTestBoard() {
        Board engineBoard = new Board();
        engineBoard.testInit();

        return new BoardDTO(BoardDTOMapper.map(engineBoard), null, "test_game");
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

    public void saveGame(GameHistoryDTO gameHistory) {
        User user = userRepository.findByUserId(UserIdFactory.generateId().getUserId());
        if (user != null) {
            String gameId = gameHistory.actualBoardState().gameId();
            log.info("Saving game history with game ID [{}]", gameId);
            GameHistory history = gameRepository.findByGameId(gameId);
            if (history == null) {
                log.info("Game history not found");
                history = new GameHistory(
                        gameId,
                        user,
                        gameHistory.playerColor().toString()
                );
            }
            history.setBoardState(gameHistory.actualBoardState());
            List<MoveHistory> movesHistory = history.getMovesHistory();
            movesHistory.clear();
            List<MoveHistoryDTO> moveHistoryDTOS = gameHistory.movesHistory();
            if (moveHistoryDTOS != null) {
                for (MoveHistoryDTO moveHistoryDTO : moveHistoryDTOS) {
                    log.info("Found historical move [{}]", moveHistoryDTO);
                    movesHistory.add(new MoveHistory(
                            moveHistoryDTO.desc(),
                            moveHistoryDTO.state(),
                            moveHistoryDTO.move(),
                            moveHistoryDTO.whoseMove()
                    ));
                }
            }
            gameRepository.save(history);
        } else {
            log.info("User not found. Cannot save game history");
        }
    }

    public List<GameHistoryDTO> getUserHistoricalGames() {
        User user = userRepository.findByUserId(UserIdFactory.generateId().getUserId());
        Optional<List<GameHistory>> allGamesByUser = Optional.empty();
        if (user != null) {
            allGamesByUser = gameRepository.findAllByUser(user);
        }
        log.info(allGamesByUser.orElse(List.of()).toString());

        return allGamesByUser.map(gameHistories -> {
            List<GameHistoryDTO> gameHistoryDTOS = new ArrayList<>();
            for (GameHistory gameHistory : gameHistories) {
                List<MoveHistoryDTO> moveHistoryDTOS = new ArrayList<>();
                for (MoveHistory moveHistory : gameHistory.getMovesHistory()) {
                    moveHistoryDTOS.add(new MoveHistoryDTO(
                            moveHistory.getDescription(),
                            moveHistory.getBoard(),
                            moveHistory.getMove(),
                            moveHistory.getWhoseMove()
                    ));
                }
                gameHistoryDTOS.add(new GameHistoryDTO(
                        gameHistory.getBoardState(),
                        moveHistoryDTOS,
                        PieceColor.valueOf(gameHistory.getPlayerColor()),
                        gameHistory.getTimestamp()));
            }
            return gameHistoryDTOS;
        }).orElse(List.of());
    }
}
