package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BoardConfigForAvailableMoves {
    public static Stream<Arguments> boardPawnConfig() {
        return Stream.of(
                Arguments.of(
                        "{" +
                                "\"pieces\": [" +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        1),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        3),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 1}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 1}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        1),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        3),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        1),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2) //
                ,
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        0),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        1),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        0),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        0),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}], " +
                                "\"move\": {\"srcColumn\": 0, \"srcRow\": 7, \"destColumn\": 0, \"destRow\": 6}," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 2}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 1}, \"moved\": true}], " +
                                "\"move\": {\"srcColumn\": 1, \"srcRow\": 1, \"destColumn\": 1, \"destRow\": 3}," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2)
        );
    }

    public static Stream<Arguments> boardRookConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        14),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        14),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        10),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        11)
        );
    }

    public static Stream<Arguments> boardKnightConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        2),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 2}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        7),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 2}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8)
        );
    }

    public static Stream<Arguments> boardBishopConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        13),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        13),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        7),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        7),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        9),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        10)
        );
    }

    public static Stream<Arguments> boardQueenConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        27),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        27),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        21),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        21),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        23),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        24),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        23),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 99, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        24)
        );
    }

    public static Stream<Arguments> boardKingConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        3),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        3),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        7),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        7),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 7, \"column\": 5}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        8),
                Arguments.of(
                        "{\"pieces\": [" +
                                "{\"id\": 88, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 0, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 3}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 0}, \"moved\": false}" +
                                "]," +
                                "\"playerColor\": \"BLACK\"" +
                                "}",
                        6)
        );
    }
}
