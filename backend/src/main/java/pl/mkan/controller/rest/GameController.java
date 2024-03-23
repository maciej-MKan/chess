package pl.mkan.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mkan.controller.dto.AvailableMovesDTO;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.service.GameService;

@RestController
@AllArgsConstructor
@RequestMapping(path = GameController.API_PATH)
public class GameController {
    public static final String API_PATH = "/api";
    private final GameService gameService;

    @PostMapping(value = "/game")
    public ResponseEntity<BoardDTO> getMove(@RequestBody @Valid BoardDTO board) {
        return ResponseEntity.ok(gameService.getMove(board));
    }

    @GetMapping(value = "/game")
    public ResponseEntity<BoardDTO> newGame() {
        return ResponseEntity.ok(gameService.makeNewBoard());
    }

    @PostMapping(value = "/game/available_moves")
    public ResponseEntity<AvailableMovesDTO> getAvailableMoves(@RequestBody @Valid BoardDTO bord) {
        return ResponseEntity.ok(gameService.calculateAvailableMoves(bord));
    }
}
