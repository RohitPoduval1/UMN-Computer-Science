// Written by Rohit Poduval, poduv006
public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // either moves up or down one square vertically and over two squares horizontally
        // up or down two squares vertically and over one square horizontally
        // (remembered as an 'L' shape)

        return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) &&
                ((Math.abs(this.col - endCol) == 2 && Math.abs(this.row - endRow) == 1) || // vertical 2, horizontal 1
                (Math.abs(this.row - endRow) == 2 && Math.abs(this.col - endCol) == 1));   // horizontal 2, vertical 1
    }
}
