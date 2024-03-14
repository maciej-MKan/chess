package pl.mkan.game.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Move {
    private final int sourceCol;
    private final int sourceRow;
    private final int destCol;
    private final int destRow;
    
    @Override
    public String toString() {
        return " " + sourceCol + sourceRow + destCol + destRow;
    }

}
