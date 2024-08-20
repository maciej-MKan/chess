package pl.mkan.persistence.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.mkan.controller.dto.MoveDTO;

public class MoveSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serializeMoveDTO(MoveDTO moveDTO) {
        try {
            return objectMapper.writeValueAsString(moveDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize MoveDTO", e);
        }
    }

    public static MoveDTO deserializeMoveDTO(String moveDTOJson) {
        try {
            return objectMapper.readValue(moveDTOJson, MoveDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize MoveDTO", e);
        }
    }
}
