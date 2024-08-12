// Written by Rohit Poduval, poduv006
public class Board {

    // Instance variables
    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][8]; // a chess board is 8x8
    }

    // Accessor Methods

    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }

    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        if (this.board[row][col] != null) {                // if there exists a piece in the new location,
            this.board[row][col] = null;                   // remove it
        }
        this.board[piece.getRow()][piece.getCol()] = null; // remove the piece at the old location
        piece.setPosition(row, col);                       // and put the piece at the new location
        this.board[row][col] = piece;                      // and update the change on the board
    }

    // Game functionality methods

    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // ❗Your Game class should not directly call any other method of this class.❗
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        // if the move is legal,
        // set the position of the piece in question to be the new position and return true
        if (this.getPiece(startRow, startCol).isMoveLegal(this, endRow, endCol)) {
            this.setPiece(endRow, endCol, this.board[startRow][startCol]);
            return true;
        }
        // otherwise, the move was a failure so do nothing and return false
        else {
            return false;
        }
    }

    // Determines whether the game has been ended, i.e., if one player's King has been captured.
    public boolean isGameOver() {
        boolean whiteKingStillStanding = false;
        boolean blackKingStillStanding = false;
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board.length; c++) {
                try {
                    if (this.board[r][c].toString().equals("♚")) {
                        blackKingStillStanding = true;
                    }
                    else if (this.board[r][c].toString().equals("♔")) {
                        whiteKingStillStanding = true;
                    }
                }
                // Ignore any blank spaces on the board
                catch (Exception NullPointerException ) {
                    ;
                }
            }
        }
        // the game is over if the black king is not standing or
        // the white king is not standing
        return !whiteKingStillStanding || !blackKingStillStanding;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board[0].length; c++) {
                this.board[r][c] = null;
            }
        }
    }

    // Movement helper functions

    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // 'start' and 'end' fall within the array's bounds
        boolean withinBounds = startRow < 8 && startRow >= 0 &&
                startCol < 8 && startCol >= 0 &&
                endRow < 8 && endRow >= 0 &&
                endCol < 8 && endCol >= 0;
        if (withinBounds) {
            // 'start' contains a Piece object, i.e., not null
            boolean containsPiece = this.board[startRow][startCol] != null;

            if (containsPiece) {
                // Player's color and color of 'start' Piece match
                boolean playerMove = this.board[startRow][startCol].getIsBlack() == isBlack;

                // 'end' contains either no Piece or a Piece of the opposite color.
                boolean endCorrectContents = this.board[endRow][endCol] == null || this.board[endRow][endCol].getIsBlack() != isBlack;

                return playerMove && endCorrectContents;
            }
        }
        return false;
    }

    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        // Check if the positions are adjacent in the same row, column, or diagonally
        return Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1;
    }

    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // if the row changed, the move does not take place all on 1 row
        // meaning there is no need to check anything else since the move is already not horizontally valid
        if (startRow != endRow) {
            return false;
        }
        // if it does, we still need to check whether all spaces directly between 'start' and 'end' are empty
        else {
            boolean spacesBetweenEmpty = true;

            // Math.max and Math.min are used because startCol may not always be greater than endCol
            // like in the case of moving in the left direction
            for (int c = Math.min(startCol, endCol) + 1; c < Math.max(startCol, endCol); c++) {
                // all it takes is 1 piece to make it so that all spaces between 'start' and 'end' are not empty
                if (this.board[startRow][c] != null) {
                    spacesBetweenEmpty = false;
                    break;
                }
            }
            return spacesBetweenEmpty;
        }
    }

    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // uses similar logic to verifyHorizontal()
        if (startCol != endCol) {
            return false;
        }
        for (int r = Math.min(startRow, endRow) + 1; r < Math.max(startRow, endRow); r++) {
            if (this.board[r][startCol] != null) {
                return false;
            }
        }
        return true;
    }

    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        // Check if the move is diagonal (change in both row and col)
        int rowChange = Math.abs(endRow - startRow);
        int colChange = Math.abs(endCol - startCol);

        if (rowChange != colChange) {
            return false;
        }

        // Check if all spaces directly between 'start' and 'end' are empty
        // start at 1 because you are checking the diagonals in between and not including the current square
        // i < rowChange tells you how many squares need to be checked paired with the less than. If row change is 2,
        // only 1 square needs to be checked not including the start and end.
        for (int i = 1; i < rowChange; i++) {
            int currentRow = startRow + (i * Integer.signum(endRow - startRow));
            int currentCol = startCol + (i * Integer.signum(endCol - startCol));

            if (this.board[currentRow][currentCol] != null) {
                return false; // There is a non-empty space between 'start' and 'end'
            }
        }
        return true; // The move is a valid diagonal move
    }
}