package pl.mkan.game.engine;

import lombok.Getter;

@Getter
public class FigureMove {
    private final int column;
    private final int row;
    private final boolean onlyInColorDirection;
    private final MoveType moveType;
    private final boolean haveToBeFirstMove;
    private final boolean canJump;

    public FigureMove(
            int column,
            int row,
            boolean onlyInColorDirection,
            MoveType moveType,
            boolean haveToBeFirstMove
    ) {
        this.column = column;
        this.row = row;
        this.onlyInColorDirection = onlyInColorDirection;
        this.moveType = moveType;
        this.haveToBeFirstMove = haveToBeFirstMove;
        canJump = false;
    }

    public FigureMove(
            int column,
            int row,
            boolean onlyInColorDirection,
            MoveType moveType,
            boolean haveToBeFirstMove,
            boolean canJump
    ) {
        this.column = column;
        this.row = row;
        this.onlyInColorDirection = onlyInColorDirection;
        this.moveType = moveType;
        this.haveToBeFirstMove = haveToBeFirstMove;
        this.canJump = canJump;
    }

    public MoveType getMoveType() {
        return this.moveType;
    }

    public boolean isHaveToBeFirstMove() {
        return haveToBeFirstMove;
    }
}
