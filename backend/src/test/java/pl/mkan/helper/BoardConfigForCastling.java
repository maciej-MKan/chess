package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;
import pl.mkan.controller.dto.PositionDTO;

import java.util.stream.Stream;

public class BoardConfigForCastling {
    public static Stream<Arguments> playerCastling() {
        return Stream.of(
                Arguments.of("""
                                {"pieces": [{
                                                "id": 88,
                                                "type": "KING",
                                                "color": "BLACK",
                                                "position": {
                                                    "row": 7,
                                                    "column": 0
                                                },
                                                "moved": true
                                            },
                                            {
                                                "id": 99,
                                                "type": "KING",
                                                "color": "WHITE",
                                                "position": {
                                                    "row": 0,
                                                    "column": 5
                                                },
                                                "moved": false
                                            },
                                            {
                                                "id": 11,
                                                "type": "PAWN",
                                                "color": "WHITE",
                                                "position": {
                                                    "row": 3,
                                                    "column": 3
                                                },
                                                "moved": true
                                            },
                                            {
                                                "id": 23,
                                                "type": "BISHOP",
                                                "color": "BLACK",
                                                "position": {
                                                    "row": 0,
                                                    "column": 2
                                                },
                                                "moved": false
                                            }],
                                "move": {
                                            "srcColumn": 4,
                                            "srcRow": 7,
                                            "destColumn": 0,
                                            "destRow": 7
                                        },
                                "playerColor": "BLACK"
                                }""",
                        88,
                        new PositionDTO(7, 2))
        );
    }
}
