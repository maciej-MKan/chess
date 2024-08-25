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
@NoArgsConstructor
public class MoveHistory {

    @Getter
    private String description;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String boardState;
    private String move;
    @Getter
    private String whoseMove;

    public MoveDTO getMove() {
        return MoveSerializer.deserializeMoveDTO(move);
    }

    public BoardDTO getBoard() {
        return BoardStateSerializer.deserializeBoardDTO(boardState);
    }

    public MoveHistory(String description, BoardDTO boardState, MoveDTO move, String whoseMove) {
        this.description = description;
        this.boardState = BoardStateSerializer.serializeBoardDTO(boardState);
        this.move = MoveSerializer.serializeMoveDTO(move);
        this.whoseMove = whoseMove;
    }
}
