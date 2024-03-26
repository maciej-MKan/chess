package pl.mkan.game.engine.figures;

import lombok.Getter;
import pl.mkan.game.engine.FigureColor;
import pl.mkan.game.engine.FigureMove;

import java.util.List;

@Getter
public abstract class Figure {
    int id;
    boolean firstMove;
    public abstract FigureColor getColor();
    public abstract List<FigureMove> getPossibleMoves();
    public void setMoved(){
        firstMove = false;
    }
}
