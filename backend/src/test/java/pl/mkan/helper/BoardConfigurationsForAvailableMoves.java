package pl.mkan.helper;

import java.util.stream.Stream;

public class BoardConfigurationsForAvailableMoves {
    public static Stream<Object[]> boardPawnConfig() {
        return Stream.of(
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}]}",
                        2},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}]}",
                        1},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}]}",
                        3},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 1}, \"moved\": false}]}",
                        2},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 1}, \"moved\": false}]}",
                        1},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        2},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        3},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}]}",
                        2},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}]}",
                        1},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 1}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        2} //
                ,
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 5, \"column\": 1}, \"moved\": false}]}",
                        2},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        0},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": false}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 4, \"column\": 0}, \"moved\": false}]}",
                        1},
                new Object[]{
                        "{\"pieces\": [{\"id\": 11, \"type\": \"PAWN\", \"color\": \"BLACK\", \"position\": {\"row\": 6, \"column\": 0}, \"moved\": true}," +
                                "{\"id\": 22, \"type\": \"PAWN\", \"color\": \"WHITE\", \"position\": {\"row\": 5, \"column\": 0}, \"moved\": false}]}",
                        0}
        );
    }
}
