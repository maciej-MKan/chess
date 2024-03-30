package pl.mkan.helper;

import java.util.stream.Stream;

public class BoardConfigurationsForAvailableMoves {
    public static Stream<Object[]> boardPawnConfig() {
        return Stream.of(
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"ROOK\", \"color\": \"BLACK\", \"position\": {\"row\": 0, \"column\": 0}, \"moved\": true}]}",
                        14},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"KNIGHT\", \"color\": \"BLACK\", \"position\": {\"row\": 2, \"column\": 5}, \"moved\": false}]}",
                        8}
        );
    }
}
