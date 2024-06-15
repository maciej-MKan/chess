package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;
import pl.mkan.controller.dto.PositionDTO;

import java.util.stream.Stream;

public class BoardConfigForEnPassant {
    public static Stream<Arguments> enPassant() {
        return Stream.of(
                Arguments.of("""
                                {"pieces": [{
                                                "id": 88,
                                                "type": "KING",
                                                "color": "WHITE",
                                                "position": {
                                                    "row": 7,
                                                    "column": 5
                                                },
                                                "moved": false
                                            },
                                            {
                                                "id": 99,
                                                "type": "KING",
                                                "color": "BLACK",
                                                "position": {
                                                    "row": 0,
                                                    "column": 5
                                                },
                                                "moved": false
                                            },
                                            {
                                                "id": 11,
                                                "type": "PAWN",
                                                "color": "BLACK",
                                                "position": {
                                                    "row": 3,
                                                    "column": 3
                                                },
                                                "moved": true
                                            },
                                            {
                                                "id": 23,
                                                "type": "BISHOP",
                                                "color": "WHITE",
                                                "position": {
                                                    "row": 0,
                                                    "column": 2
                                                },
                                                "moved": false
                                            }],
                                "move": {
                                            "srcColumn": 4,
                                            "srcRow": 3,
                                            "destColumn": 3,
                                            "destRow": 3
                                        },
                                "playerColor": "BLACK"
                                }""",
                        11,
                        new PositionDTO(2, 3))
        );
    }
}
