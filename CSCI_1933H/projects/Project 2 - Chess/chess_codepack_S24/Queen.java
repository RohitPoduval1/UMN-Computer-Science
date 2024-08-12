// Written by Rohit Poduval, poduv006
public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // the Queen moves like a Rook and a Bishop
        // vertically, horizontally, and diagonally

        return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack) &&
                (board.verifyVertical(this.row, this.col, endRow, endCol) ||           // can move up and down
                        board.verifyHorizontal(this.row, this.col, endRow, endCol) ||  // can move side to side
                        board.verifyDiagonal(this.row, this.col, endRow, endCol));     // can move diagonally
    }
}
