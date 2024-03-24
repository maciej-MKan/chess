package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.PositionDTO;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.figures.Figure;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovesDTOMapper {
    public static Map<Integer, List<PositionDTO>> map(Map<Figure, List<Move>> figureMovesMap) {

        return figureMovesMap.entrySet().stream()
                .map(entry -> Map.entry(new PieceDTO(
                                entry.getKey().getId(),
                                PieceDTOMapper.mapPiece(entry.getKey()),
                                PieceDTOMapper.mapColor(entry.getKey().getColor()),
                                new PositionDTO(
                                        entry.getValue().stream().findAny().orElseThrow().getSourceCol(),
                                        entry.getValue().stream().findAny().orElseThrow().getSourceRow()

                                ),
                                !entry.getKey().isFirstMove()),
                        entry.getValue().stream()
                                .map(move -> new PositionDTO(move.getDestCol(), move.getDestRow()))
                                .toList()
                ))
                .collect(Collectors.toMap((entry -> entry.getKey().id()), (Map.Entry::getValue)));
    }
}
