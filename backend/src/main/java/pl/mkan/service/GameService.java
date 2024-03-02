package pl.mkan.service;

import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.ChessBoardDTO;

import java.util.List;

@Service
public class GameService {
    public ChessBoardDTO move(ChessBoardDTO board) {
        return new ChessBoardDTO(newBoard());
    }

    private List<List<String>> newBoard() {
        return List.of(
                List.of("R", "N", "B", "Q", "K", "B", "N", "R"),
                List.of("P", "P", "P", "P", "P", "P", "P", "P"),
                List.of(" ", " ", " ", " ", " ", " ", " ", " "),
                List.of(" ", " ", " ", " ", " ", " ", " ", " "),
                List.of(" ", " ", " ", " ", " ", " ", " ", " "),
                List.of(" ", " ", " ", " ", " ", " ", " ", " "),
                List.of("p", "p", "p", "p", "p", "p", "p", "p"),
                List.of("r", "n", "b", "q", "k", "b", "n", "r")
        );
    }
}
