package pl.mkan.persistence.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.mkan.controller.dto.BoardDTO;

public class BoardStateSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serializeBoardDTO(BoardDTO boardDTO) {
        try {
            return objectMapper.writeValueAsString(boardDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize BoardDTO", e);
        }
    }

    public static BoardDTO deserializeBoardDTO(String boardDTOJson) {
        try {
            return objectMapper.readValue(boardDTOJson, BoardDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize BoardDTO", e);
        }
    }
}
