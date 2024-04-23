package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BoardConfigForComputerMove {

    //arguments in stream:
    //String: board configuration
    //int: id of expected moved piece
    //int: expected new row
    //int: expected new column

    public static Stream<Arguments> coverInFirstMove() {
        return Stream.of(
                Arguments.of("""
                                {"pieces": [{
                                            "id": 11,
                                            "type": "ROOK",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 2,
                                                "column": 0
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 12,
                                            "type": "KNIGHT",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 0,
                                                "column": 1
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 13,
                                            "type": "BISHOP",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 0,
                                                "column": 2
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 14,
                                            "type": "KING",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 0,
                                                "column": 3
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 15,
                                            "type": "QUEEN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 0,
                                                "column": 4
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 16,
                                            "type": "BISHOP",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 0,
                                                "column": 5
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 17,
                                            "type": "KNIGHT",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 0,
                                                "column": 6
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 18,
                                            "type": "ROOK",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 0,
                                                "column": 7
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 21,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 0
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 22,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 1
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 23,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 2
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 24,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 3
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 25,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 4
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 26,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 5
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 27,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 6
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 28,
                                            "type": "PAWN",
                                            "color": "WHITE",
                                            "position": {
                                                "row": 1,
                                                "column": 7
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 71,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 6,
                                                "column": 0
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 72,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 6,
                                                "column": 1
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 73,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 6,
                                                "column": 2
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 74,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 6,
                                                "column": 3
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 75,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 6,
                                                "column": 4
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 76,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 6,
                                                "column": 5
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 77,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 5,
                                                "column": 6
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 78,
                                            "type": "PAWN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 6,
                                                "column": 7
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 81,
                                            "type": "ROOK",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 0
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 82,
                                            "type": "KNIGHT",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 1
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 83,
                                            "type": "BISHOP",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 2
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 84,
                                            "type": "KING",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 3
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 85,
                                            "type": "QUEEN",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 4
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 86,
                                            "type": "BISHOP",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 5
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 87,
                                            "type": "KNIGHT",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 6
                                            },
                                            "moved": false
                                        },
                                        {
                                            "id": 88,
                                            "type": "ROOK",
                                            "color": "BLACK",
                                            "position": {
                                                "row": 7,
                                                "column": 7
                                            },
                                            "moved": false
                                        }
                                    ],
                                "move": {
                                        "srcColumn": 6,
                                        "srcRow": 6,
                                        "destColumn": 6,
                                        "destRow": 5
                                    }
                                }""",
                        11, 6, 0)
        );
    }
}
