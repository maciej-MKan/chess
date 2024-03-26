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

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

    public boolean isOnlyInColorDirection() {
        return this.onlyInColorDirection;
    }

    public boolean isHaveToCapture() {
        return this.haveToCapture;
    }

    public boolean isHaveToBeFirstMove() {
        return this.haveToBeFirstMove;
    }

    public boolean isCanJump() {
        return this.canJump;
    }
}
