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
                        true),
                Arguments.of("""
                                {"pieces": [{
                                                "id": 11,
                                                "type": "PAWN",
                                                "color": "BLACK",
                                                "position": {
                                                    "row": 6,
                                                    "column": 3
                                                },
                                                "moved": true
                                            }],
                                "move": {
                                            "srcColumn": 3,
                                            "srcRow": 5,
                                            "destColumn": 3,
                                            "destRow": 6
                                        }
                                }""",
                        false),
                Arguments.of("""
                                {"pieces": [{
                                                "id": 11,
                                                "type": "ROOK",
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
                        false),
                Arguments.of("""
                                {"pieces": [{
                                                "id": 11,
                                                "type": "PAWN",
                                                "color": "WHITE",
                                                "position": {
                                                    "row": 0,
                                                    "column": 3
                                                },
                                                "moved": true
                                            }],
                                "move": {
                                            "srcColumn": 3,
                                            "srcRow": 1,
                                            "destColumn": 3,
                                            "destRow": 0
                                        }
                                }""",
                        true),
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
                                            },
                                            {
                                                "id": 12,
                                                "type": "PAWN",
                                                "color": "BLACK",
                                                "position": {
                                                    "row": 7,
                                                    "column": 4
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
