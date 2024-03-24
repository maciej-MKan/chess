package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.PositionDTO;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.figures.Figure;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovesDTOMapper {
    public static Map<Integer, List<PositionDTO>> map(Map<Figure, List<Move>> figureMovesMap) {

        return figureMovesMap.entrySet().stream()
                .map(entry -> Map.entry(
                        entry.getKey().getId(),
                        entry.getValue().stream()
                                .map(move -> new PositionDTO(move.getDestRow(), move.getDestCol()))
                                .toList()
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
