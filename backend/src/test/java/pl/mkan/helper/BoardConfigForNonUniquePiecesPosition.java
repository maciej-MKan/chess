package pl.mkan.helper;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BoardConfigForNonUniquePiecesPosition {

    public static Stream<Arguments> boardWithCoverPosition() {
        return Stream.of(
                Arguments.of(
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KING\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 12, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 3, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"KING\", \"color\": \"WHITE\", \"position\": {\"row\": 3, \"column\": 1}, \"moved\": true}]}",
                        "Bad Request")
        );
    }
}
