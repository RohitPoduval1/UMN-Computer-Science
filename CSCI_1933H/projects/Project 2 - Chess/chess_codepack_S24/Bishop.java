// Written by Rohit Poduval, poduv006
public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // a Bishop can move diagonally
        return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) &&
                board.verifyDiagonal(this.row, this.col, endRow, endCol);
    }
}
