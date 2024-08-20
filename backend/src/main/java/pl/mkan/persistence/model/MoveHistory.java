package pl.mkan.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mkan.controller.dto.BoardDTO;
import pl.mkan.controller.dto.MoveDTO;
import pl.mkan.persistence.serializer.BoardStateSerializer;
import pl.mkan.persistence.serializer.MoveSerializer;

@Embeddable
@Getter
@NoArgsConstructor
public class MoveHistory {

    private String description;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String boardState;
    private String move;
    private String whoseMove;

    public MoveHistory(String description, BoardDTO boardState, MoveDTO move, String whoseMove) {
        this.description = description;
        this.boardState = BoardStateSerializer.serializeBoardDTO(boardState);
        this.move = MoveSerializer.serializeMoveDTO(move);
        this.whoseMove = whoseMove;
    }
}
