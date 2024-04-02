package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BoardConfigurationForGameOver {

    public static Stream<Arguments> boardWithGameOver() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}]}",
                        true, "BLACK"),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}]}",
                        true, "BLACK"),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}]}",
                        true, "WHITE")
        );
    }

    public static Stream<Arguments> boardWithOutGameOver() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}]}",
                        false),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}]}",
                        false),
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 12, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": true}]}",
                        false)
        );
    }
}
