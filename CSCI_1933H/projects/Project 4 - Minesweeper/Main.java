// Written by Rohit Poduval
import java.util.Scanner;

/* Provided in this class is the neccessary code to get started with your game's implementation
 * You will find a while loop that should take your minefield's gameOver() method as its conditional
 * Then you will prompt the user with input and manipulate the data as before in project 2
 * 
 * Things to Note:
 * 1. Think back to project 1 when we asked our user to give a shape. In this project we will be asking the user to provide a mode. Then create a minefield accordingly
 * 2. You must implement a way to check if we are playing in debug mode or not.
 * 3. When working inside your while loop think about what happens each turn. We get input, user our methods, check their return values. repeat.
 * 4. Once while loop is complete figure out how to determine if the user won or lost. Print appropriate statement.
 */

public class Main {

    public static void main(String[] args) {
        /*
         * 1. When a game is started, the main class should instantiate a new Minefield based on user input,
         * as well as whether the game should be played in debug mode.
         */
        String level = Main.getLevel();
        boolean inDebugMode = Main.getYesOrNo("Do you want to be in debug mode? (yes or no): ");
        Minefield minefield = null; 

        if (level.equals("easy") || level.equals("e")) {
            minefield = new Minefield(5, 5, 5);
        }
        else if (level.equals("medium") || level.equals("m")) {
            minefield = new Minefield(9, 9, 12);
        }
        else if (level.equals("hard") || level.equals("h")) {
            minefield = new Minefield(20, 20, 40);
        }
        else {
            throw new IllegalArgumentException("Invalid entry for the level" + level);
        }
        
        // the opening coordinates the player chooses 
        int[] startingCoords = Main.getCoordinates(minefield.getRows(), minefield.getCols());

        /*
         * 2. After this, the game now has an empty field with which it should place mines on.
         * This is done through the createMines() method
         */
        minefield.createMines(startingCoords[0], startingCoords[1], minefield.getFlags());

        /*
         * 3. Our field now has mines placed on it but does not have any useful information for the user.
         * To help the user, we can change the statuses of each cell to reflect how many mines surround it.
         * This is done through the evaluateField() method.
         */
        minefield.evaluateField();

        /*
         * 4. We are almost ready to begin playing, except our field does not have any of its cells revealed to the user yet.
         * To do this, we must call the revealStartingArea() method.
         */
        minefield.revealStartingArea(startingCoords[0], startingCoords[1]);
        minefield.guess(startingCoords[0], startingCoords[1], false);
        System.out.println(minefield);
        if (inDebugMode) {
            System.out.println("Debug Field: ");
            minefield.debug();
        }
        
        // NOTE: Flags are permanently placed
        boolean placeFlag;
        while(! minefield.gameOver()) {
            int[] guessedCoords = Main.getCoordinates(minefield.getRows(), minefield.getCols());
            placeFlag = Main.getYesOrNo("Do you want to place a flag? (yes or no): ");
            
            // if the guess hits a mine (guess() returns true)
            if (minefield.guess(guessedCoords[0], guessedCoords[1], placeFlag)) {
                break;
            }
            // the game continues
            else {
                System.out.println(minefield);
                if (inDebugMode) {
                    System.out.println("Debug Field: ");
                    minefield.debug();
                }
            }
        }
    }


    /**
     * 
     * @return "easy", "medium", or "hard"
     */
    public static String getLevel() {
        // Create a Scanner object to read input from the console
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play in easy, medium, or hard mode?: ");
        String level = scanner.nextLine();
        
        while (! (level.toLowerCase().equals("easy") ||
                level.toLowerCase().equals("e") ||
                level.toLowerCase().equals("medium") ||
                level.toLowerCase().equals("m") ||
                level.toLowerCase().equals("hard") ||
                level.toLowerCase().equals("h"))) {
            System.out.println("Invalid input. Please enter easy, medium, or hard: ");
            level = scanner.nextLine();
        }
        
        return level.toLowerCase().trim();
    }

    /**
     * 
     * @param prompt  The prompt to display to the user (should be a yes or no question)
     * @return True for yes, false for no
     */
    public static boolean getYesOrNo(String prompt) {
        // Create a Scanner object to read input from the console
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String yesOrNo = scanner.nextLine().toLowerCase();
        
        while (! (yesOrNo.equals("yes") ||
                yesOrNo.equals("y") ||
                yesOrNo.equals("no") ||
                yesOrNo.equals("n"))) {
            System.out.println("Invalid input. Please enter Yes or No: ");
            yesOrNo = scanner.nextLine();
        }

        return yesOrNo.equals("yes") || yesOrNo.equals("y");
    }   

    /**
     * 
     * @param rowMax The maximum x coordinate that is valid 
     * @param colMax The maximum y coordinate that is valid
     * @return An array of length 2 with the x coordinate being the element at position 0 and y coordinate being the element at position 1 [x, y]
     */ 
    public static int[] getCoordinates(int rowMax, int colMax) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        
        int chosenRow = -1;
        int chosenCol = -1;

        while (true) {
            try {
                System.out.println("Enter coordinates [x] [y]: ");
                String input = scanner.nextLine();

                String[] inputs = input.strip().split("\\s+");
                int[] row_col = new int[]{Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])};

                // Check if the chosen row and column are within the specified ranges
                if (row_col[0] >= 0 && row_col[0] < rowMax && row_col[1] >= 0 && row_col[1] < colMax) {
                    chosenRow = row_col[0];
                    chosenCol = row_col[1];
                    break;
                }
                else {
                    System.out.println("Coordinates must be within 0 and " + (rowMax - 1) + " for rows and 0 and " + (colMax - 1) + " for columns. Please try again:");
                }
            }
            catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Please enter 2 numbers");
            }
        }
        return new int[]{chosenRow, chosenCol};
    }
}
