package pl.mkan.game.engine;

public class FigureMove {
    private final int column;
    private final int row;

    public FigureMove(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
