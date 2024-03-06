package pl.mkan.game.engine;

public class FigureMove {
    private final int column;
    private final int row;
    private final boolean onlyInColorDirection;
    private final boolean haveToCapture;
    private final boolean haveToBeFirstMove;
    private final boolean canJump;

    public FigureMove(
            int column,
            int row,
            boolean onlyInColorDirection,
            boolean haveToCapture,
            boolean haveToBeFirstMove
    ) {
        this.column = column;
        this.row = row;
        this.onlyInColorDirection = onlyInColorDirection;
        this.haveToCapture = haveToCapture;
        this.haveToBeFirstMove = haveToBeFirstMove;
        canJump = false;
    }

    public FigureMove(
            int column,
            int row,
            boolean onlyInColorDirection,
            boolean haveToCapture,
            boolean haveToBeFirstMove,
            boolean canJump
    ) {
        this.column = column;
        this.row = row;
        this.onlyInColorDirection = onlyInColorDirection;
        this.haveToCapture = haveToCapture;
        this.haveToBeFirstMove = haveToBeFirstMove;
        this.canJump = canJump;
    }

    public boolean isCanJump() {
        return canJump;
    }

    public boolean isOnlyInColorDirection() {
        return onlyInColorDirection;
    }

    public boolean isHaveToCapture() {
        return haveToCapture;
    }

    public boolean isHaveToBeFirstMove() {
        return haveToBeFirstMove;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
