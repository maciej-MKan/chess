package pl.mkan.game.engine;

public record Move(int sourceCol, int sourceRow, int destCol, int destRow) {
    @Override
    public String toString() {
        return " " + sourceCol + sourceRow + destCol + destRow;
    }

}
