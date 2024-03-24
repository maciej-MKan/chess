package pl.mkan.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mkan.controller.dto.AvailableMovesDTO;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.service.GameService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = GameController.API_PATH)
public class GameController {
    public static final String API_PATH = "/api";
    private final GameService gameService;

    @PostMapping(value = "/game")
    public ResponseEntity<BoardDTO> getMove(@RequestBody @Valid BoardDTO board) {
        log.info("Handle POST request at '/game' with object [{}]", board);
        return ResponseEntity.ok(gameService.getMove(board));
    }

    @GetMapping(value = "/game")
    public ResponseEntity<BoardDTO> newGame() {
        log.info("Handle GET request at '/game'");
        return ResponseEntity.ok(gameService.makeNewBoard());
    }

    @PostMapping(value = "/game/available_moves")
    public ResponseEntity<AvailableMovesDTO> getAvailableMoves(@RequestBody @Valid BoardDTO board) {
        log.info("Handle POST request at '/game/available_moves' with object [{}]", board);
        return ResponseEntity.ok(gameService.calculateAvailableMoves(board));
    }
}
