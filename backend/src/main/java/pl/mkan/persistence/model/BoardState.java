package pl.mkan.persistence.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@EqualsAndHashCode(of = "gameId")
@NoArgsConstructor
@Entity
@Table(name = "game")
public class BoardState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String gameId;

    public BoardState(String gameId) {
        this.gameId = gameId;
    }
}
