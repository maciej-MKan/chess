package pl.mkan.controller.dto.mapper;

import pl.mkan.controller.dto.PieceDTO;
import pl.mkan.controller.dto.PositionDTO;
import pl.mkan.game.engine.Move;
import pl.mkan.game.engine.figures.Figure;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovesDTOMapper {
    public static Map<PieceDTO, List<PositionDTO>> map(Map<Figure, List<Move>> figureMovesMap) {

        return figureMovesMap.entrySet().stream()
                .map(entry -> Map.entry(new PieceDTO(
                                PieceDTOMapper.mapPiece(entry.getKey()
                                ), PieceDTOMapper.mapColor(entry.getKey().getColor()), new PositionDTO(
                                entry.getValue().stream().findAny().orElseThrow().getSourceCol(),
                                entry.getValue().stream().findAny().orElseThrow().getSourceRow()

                        )),
                        entry.getValue().stream()
                                .map(move -> new PositionDTO(move.getDestCol(), move.getDestRow()))
                                .toList()
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
