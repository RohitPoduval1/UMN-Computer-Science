// Written by Rohit Poduval, poduv006
public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // a King can move to any directly adjacent spot
        return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) &&
                board.verifyAdjacent(this.row, this.col, endRow, endCol);
    }
}
