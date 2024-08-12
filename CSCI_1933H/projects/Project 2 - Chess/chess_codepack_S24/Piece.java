// Written by Rohit Poduval, poduv006
import java.util.Scanner;

public class Piece {
    // Create Instance Variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;


    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public int getRow() { return this.row; }
    public int getCol() { return this.col; }
    public char getCharacter() { return this.character; }
    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    public static String getChessPieceInput() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Enter the name of the piece you want to promote the pawn to (besides the king): ");
            input = scanner.nextLine().toUpperCase().trim();

            if (isValidChessPiece(input)) {
                break;
            }
            else {
                System.out.println("Invalid input. Please enter a valid chess piece.");
            }
        }
        return input;
    }

    public static boolean isValidChessPiece(String input) {
        String[] validPieces = {"PAWN", "ROOK", "KNIGHT", "BISHOP", "QUEEN"};

        for (String piece : validPieces) {
            if (input.equals(piece)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handle promotion of a pawn.
     * @param row Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {
        // Prompt the player to enter the name of the piece they want to promote to
        Scanner scanner = new Scanner(System.in);
        String promoteTo = getChessPieceInput();

        switch (promoteTo) {
            case "PAWN":
                ;
            case "ROOK":
                if (this.isBlack) {
                    this.character = '\u265c';
                    break;
                }
                else {
                    this.character = '\u2656';
                    break;
                }
            case "BISHOP":
                if (this.isBlack) {
                    this.character = '\u265d';
                    break;
                }
                else {
                    this.character = '\u2657';
                    break;
                }
            case "KNIGHT":
                if (this.isBlack) {
                    this.character = '\u265e';
                    break;
                }
                else {
                    this.character = '\u2658';
                    break;
                }
            case "QUEEN":
                if (this.isBlack) {
                    this.character = '\u265b';
                    break;
                }
                else {
                    this.character = '\u2655';
                    break;
                }
        }
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return String.valueOf(this.character);
    }
}
