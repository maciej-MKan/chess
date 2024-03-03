package pl.mkan.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.service.GameService;

@RestController
@AllArgsConstructor
@RequestMapping(path = GameController.API_PATH)
public class GameController {
    public static final String API_PATH = "/api";
    private final GameService gameService;

    @PostMapping(value = "/game")
    public ResponseEntity<BoardDTO> game(BoardDTO board) {
        return ResponseEntity.ok(gameService.move(board));
    }

    @GetMapping(value = "/game")
    public ResponseEntity<BoardDTO> newGame() {
        return ResponseEntity.ok(gameService.newBoard());
    }
}
