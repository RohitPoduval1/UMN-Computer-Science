// Written by Rohit Poduval, poduv006
public class Rook {
    private int row;
    private int col;
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) &&
                (board.verifyVertical(this.row, this.col, endRow, endCol) ||          // can move up and down
                        board.verifyHorizontal(this.row, this.col, endRow, endCol));  // as well as side to side
    }
}
