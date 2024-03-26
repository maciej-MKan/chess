package pl.mkan.game.engine.board;

import lombok.Getter;
import pl.mkan.game.engine.figures.Figure;
import pl.mkan.game.engine.figures.None;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardRow {
    private final List<Figure> cols = new ArrayList<>();

    public BoardRow() {
        for (int col = 0; col < 8; col++) {
            cols.add(new None());
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("|");
        for (int col = 0; col < 8; col++) {
            s.append(cols.get(col).toString()).append("|");
        }
        s.append("\n");
        return s.toString();
    }
}
