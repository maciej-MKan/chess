package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BoardConfigForPawnPromotion {
    public static Stream<Arguments> pawnPromotion() {
        return Stream.of(
                Arguments.of("""
                                {"pieces": [{
                                                "id": 11,
                                                "type": "PAWN",
                                                "color": "BLACK",
                                                "position": {
                                                    "row": 7,
                                                    "column": 3
                                                },
                                                "moved": true
                                            }],
                                "move": {
                                            "srcColumn": 3,
                                            "srcRow": 6,
                                            "destColumn": 3,
                                            "destRow": 7
                                        }
                                }""",
                        true)
        );
    }
}
