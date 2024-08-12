// Written by Rohit Poduval, poduv006
import java.util.Scanner;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public static void main(String[] args) {
        Game game = new Game();

        // default board
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", game.board);


        String currentTurn = "White";

         while (!game.board.isGameOver()){
            System.out.println("Board: ");
            System.out.println(game.board);

            // Get the player's move
            System.out.println("It is currently " + currentTurn + "'s turn to play.");
            Scanner scanner = new Scanner(System.in);
            System.out.print("What is your move? (format: [start row] [start col] [end row] [end col]): ");
            String move = scanner.nextLine();

            // Create an int array based on the format of the entered numbers
            // [start row, start col, end row, end col]
            String[] stringPositions = move.strip().split("\\s+");
            int[] positions = new int[stringPositions.length];
            for (int i = 0; i < stringPositions.length; i++) {
                positions[i] = Integer.parseInt(stringPositions[i]);
            }

            try {
                if (game.board.getPiece(positions[0], positions[1]).getIsBlack() == (currentTurn.equals("Black"))) {
                    // Is Legal is already called by movePiece so there is no need to check it again in the Game class
                    if (game.board.movePiece(positions[0], positions[1], positions[2], positions[3])) {

                        // if the piece is a pawn of any color
                        if (game.board.getPiece(positions[2], positions[3]).getCharacter() == '\u2659' ||
                                game.board.getPiece(positions[2], positions[3]).getCharacter() =='\u265f') {
                            // a black pawn has to reach row 7 to be promoted
                            if (game.board.getPiece(positions[2], positions[3]).getIsBlack() == true &&
                                    game.board.getPiece(positions[2], positions[3]).getRow() == 7) {
                                game.board.getPiece(positions[2], positions[3]).promotePawn(7, true);
                            }
                            // a white pawn has to reach row 0 to be promoted
                            else if (game.board.getPiece(positions[2], positions[3]).getIsBlack() == false &&
                                    game.board.getPiece(positions[2], positions[3]).getRow() == 0) {
                                game.board.getPiece(positions[2], positions[3]).promotePawn(0, false);
                            }
                        }

                        // Handle the alternating of turns
                        if (currentTurn.equals("White")) { // white just finished their turn,
                            currentTurn = "Black";         // so switch to black;
                        }
                        else {                             // black just finished their turn,
                            currentTurn = "White";         // so switch to white
                        }
                    }
                    // The move did not go through so give the player another chance
                    else {
                        System.out.println("Invalid move, try again");
                    }
                }
                // The player is trying to move the opposite color piece, so give them a second chance
                else {
                    System.out.println("Invalid move, try again");
                }
            }
            // the player is trying to move an empty square, so give them another chance
            catch (Exception NullPointerException) {
                System.out.println("No piece exists where you are trying to play, try again");
            }
        }
         if (currentTurn.equals("White")) {
             System.out.println("The game is over! Black won.");
         }
         else {
             System.out.println("The game is over! White won.");
         }
    }
}