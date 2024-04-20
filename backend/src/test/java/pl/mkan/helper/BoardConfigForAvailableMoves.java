package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BoardConfigForAvailableMoves {
    public static Stream<Arguments> boardPawnConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}]}",
                        2),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}]}",
                        1),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}]}",
                        3),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 1}, \"moved\": false}]}",
                        2),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 1}, \"moved\": false}]}",
                        1),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        2),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        3),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}]}",
                        2),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}]}",
                        1),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        2) //
                ,
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}]}",
                        2),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        0),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}]}",
                        1),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": true}]}",
                        0),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        0)
        );
    }

    public static Stream<Arguments> boardRookConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}]}",
                        14),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}]}",
                        14),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}]}",
                        10),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}]}",
                        11)
        );
    }

    public static Stream<Arguments> boardKnightConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}]}",
                        8),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}]}",
                        8),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}]}",
                        2),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}]}",
                        2),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 2}, \"moved\": false}]}",
                        7),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 2}, \"moved\": false}]}",
                        8),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}]}",
                        8),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 4}, \"moved\": false}]}",
                        8)
        );
    }

    public static Stream<Arguments> boardBishopConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}]}",
                        13),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}]}",
                        13),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}]}",
                        7),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}]}",
                        7),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}]}",
                        9),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"BISHOP\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}]}",
                        10)
        );
    }

    public static Stream<Arguments> boardQueenConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}]}",
                        27),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}]}",
                        27),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}]}",
                        21),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}]}",
                        21),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}]}",
                        23),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}]}",
                        24),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}]}",
                        23),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"QUEEN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}]}",
                        24)
        );
    }

    public static Stream<Arguments> boardKingConfig() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}]}",
                        8),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": true}]}",
                        8),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": false}]}",
                        3),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 7, \"column\": 7}, \"moved\": true}]}",
                        3),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}]}",
                        7),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 3}, \"moved\": false}]}",
                        8),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}]}",
                        7),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 4}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 3}, \"moved\": false}]}",
                        8)
        );
    }
}
