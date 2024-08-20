package pl.mkan.persistence.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.persistence.serializer.BoardStateSerializer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@EqualsAndHashCode(of = "gameId")
@NoArgsConstructor
@Entity
@Table(name = "game_history")
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String gameId;

    private String playerColor;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String boardState;

    @ElementCollection
    @CollectionTable(name = "move_histories", joinColumns = @JoinColumn(name = "game_history_id"))
    private List<MoveHistory> movesHistory = new ArrayList<>();

    public GameHistory(String playerColor, BoardDTO boardState) {
        this.gameId = boardState.gameId();
        this.playerColor = playerColor;
        this.boardState = BoardStateSerializer.serializeBoardDTO(boardState);
    }
}

