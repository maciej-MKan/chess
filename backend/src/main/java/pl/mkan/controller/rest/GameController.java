package pl.mkan.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mkan.controller.dto.*;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.service.GameService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = GameController.API_PATH)
public class GameController {
    public static final String API_PATH = "/api";
    private final GameService gameService;

    @PostMapping(value = "/game")
    public ResponseEntity<BoardDTO> getMove(@RequestBody @Valid MoveRequestDTO request) {
        log.info("Handle POST request at '/game' with object [{}]", request);
        return ResponseEntity.ok(gameService.getMove(request));
    }

    @GetMapping(value = "/game")
    public ResponseEntity<BoardDTO> newGame(
            @RequestParam(value = "playerColor") final PieceColor playerColor
    ) {
        log.info("Handle GET request at '/game'");
        log.info("Player color: '{}'", playerColor);
        return ResponseEntity.ok(gameService.makeNewBoard(playerColor));
    }

    @GetMapping(value = "/game/test")
    public ResponseEntity<BoardDTO> testGame() {
        return ResponseEntity.ok(gameService.makeTestBoard());
    }

    @PostMapping(value = "/game/available_moves")
    public ResponseEntity<AvailableMovesDTO> getAvailableMoves(@RequestBody @Valid AvailableMovesRequestDTO request) {
        log.info("Handle POST request at '/game/available_moves' with object [{}]", request);
        return ResponseEntity.ok(gameService.calculateAvailableMoves(request));
    }


    @PostMapping(value = "/game/game_over")
    public ResponseEntity<GameOverDTO> checkGameOver(@RequestBody @Valid BoardDTO board) {
        log.info("Handle game over request with state [{}]", board);
        return ResponseEntity.ok(gameService.checkGameOver(board));
    }

    @PostMapping(value = "/game/state")
    public ResponseEntity<GameStateDTO> checkState(@RequestBody @Valid BoardDTO board) {
        log.info("Handle game state request with state [{}]", board);
        return ResponseEntity.ok(gameService.checkGameState(board));
    }

    @PostMapping(value = "/game/history")
    public void saveGameStateHistory(@RequestBody @Valid BoardDTO board) {
        log.info("Handle game history request with state [{}]", board);
        gameService.saveGame(board);
    }
}
