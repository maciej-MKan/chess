package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BoardConfigForEnPassant {
    public static Stream<Arguments> enPassant() {
        return Stream.of(
                Arguments.of("""
                                {"pieces": [{
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
                                        }
                                }""",
                        22)
        );
    }
}
