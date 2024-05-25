package pl.mkan.game.engine;

import lombok.Getter;

@Getter
public class FigureMove {
    private final int column;
    private final int row;
    private final boolean onlyInColorDirection;
    private final CaptureOptions haveToCapture;
    private final boolean haveToBeFirstMove;
    private final boolean canJump;

    public FigureMove(
            int column,
            int row,
            boolean onlyInColorDirection,
            CaptureOptions haveToCapture,
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
            CaptureOptions haveToCapture,
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

    public CaptureOptions isHaveToCapture() {
        return this.haveToCapture;
    }

    public boolean isHaveToBeFirstMove() {
        return haveToBeFirstMove;
    }
}
