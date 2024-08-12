// Written by Rohit Poduval, poduv006
import java.util.Random;


public class Minefield {
    /**
    Global Section
    */
    public static final String ANSI_WHITE = "\\u001b[0;37m\t";
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";

    // Class Variables Setion
    private int flags; 
    private boolean guessedMine = false;
    private Cell[][] field;

    /* Things to Note:
     * Please review ALL files given before attempting to write these functions.
     * Understand the Cell.java class to know what object our array contains and what methods you can utilize
     * Understand the StackGen.java class to know what type of stack you will be working with and methods you can utilize
     * Understand the QGen.java class to know what type of queue you will be working with and methods you can utilize
     */
    
    /**
     * Minefield
     * 
     * Build a 2-d Cell array representing your minefield.
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */
    public Minefield(int rows, int columns, int flags) {
        this.field = new Cell[rows][columns];
        this.flags = flags;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                this.field[r][c] = new Cell(false, "0");
            }
        }
    }

    public int getRows() {
        return this.field.length;
    }
    public int getCols() {
        return this.field[0].length;
    }
    public int getFlags() {
        return this.flags;
    }
    public Cell getCell(int x , int y) {
        return this.field[x][y];
    }

    /**
     * evaluateField
     * 
     *
     * @function:
     * Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     * 
     */
    public void evaluateField() {
        for (int r = 0; r < this.field.length; r++) {
            for (int c = 0; c < this.field[0].length; c++) {
                if (this.field[r][c].getStatus().equals("M")) {
                    this.incrementAdjacentCells(r, c);
                }
            }
        }
    }

    /**
     * @function
     * Increment adjacent cells around a target cell, the cell that you are looking for adjacent cells around. 
     * Helper method for evaluateField()
     * 
     * @param x   the x coordinate of the target cell. 
     * @param y   the y coordinate of the target cell. 
     */
    private void incrementAdjacentCells(int x, int y) {
        // Loop through adjacent cells
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // Skip the current cell itself
                // (if statement not required, but included for readability)
                if (i == x && j == y) {
                    continue;
                }
                // Check if the adjacent cell is in bounds
                if (isInBounds(i, j)) {
                    // Check the status of the adjacent cell
                    String status = field[i][j].getStatus();
                    if (!status.equals("M") && !status.equals("F")) {
                        int value;

                        if (status.equals("-")) {
                            value = 0;
                        }
                        else {
                            value = Integer.parseInt(status);
                        }  
                        
                        field[i][j].setStatus(String.valueOf(value + 1));
                    }
                }
            }
        }
    }



    /**
     * createMines
     * 
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell, set the cell to be a mine.
     * utilize rand.nextInt()
     * 
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    /* NOTE: Starting means where the player first clicks. It does NOT mean where you should start placing mines.
     * 
    */ 
    public void createMines(int x, int y, int mines) {
        int placedMines = 0;
        int randomX;
        int randomY;
        while (placedMines < mines) {
            Random rand = new Random();
            randomX = rand.nextInt(this.field.length);
            randomY = rand.nextInt(this.field[0].length);
            // Do not place a mine on the spot the player first guesses or where a mine is already placed.
            if (randomX == x && randomY == y || this.field[randomX][randomY].getStatus().equals("M")) {
                ;  // instead, just continue looping. 
            }
            // the spot randomly generated is valid so place a mine
            else {
                this.field[randomX][randomY].setStatus("M");
                placedMines++;
            }
        }
    }

    /**
     * guess
     * 
     * Assumes that the guessed cell is inbounds (done in the Main class). 
     * Either place a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0, call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     * 
     * 
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        boolean toReturn = false;
        Cell targetCell = this.field[x][y];
        // place a flag on the designated cell if the flag boolean is true
        if (flag) {  
            targetCell.setStatus("F");
            toReturn = false;
        }
        if (!flag) {
            toReturn = false;
        }
        // if the cell has a 0, reveal zeros
        if (targetCell.getStatus().equals("-") || targetCell.getStatus().equals("0")) {
            this.revealZeroes(x, y);
            toReturn = false;
        }
        // if the cell has a mine, the game is over
        if (targetCell.getStatus().equals("M")) {
            this.guessedMine = true;
            toReturn = true;  
            this.gameOver();
        }
        targetCell.setRevealed(true);
        return toReturn;
    }

    /**
     * gameOver
     * 
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     * 
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        if (this.guessedMine) {
            System.out.println("You activated a mine! 💥💥💥");
            return true;
        }
        else {
            // loop through each cell in the minefield
            for (int r = 0; r < this.field.length; r++) {
                for (int c = 0; c < this.field[0].length; c++) {
                    if (!this.field[r][c].getRevealed() && !this.field[r][c].getStatus().equals("M")) {
                        return false;  // it only takes one unguessed cell that's not a mine to keep the game going (AKA not be over)
                    }
                }
            }
            System.out.println("You swept the minefield! 🧹💣");
            return true;  // the loop made it through each cell in the field and
                        // all of them were revealed which means the game is over and the player won. 
        }
    }

    /**
     * Reveal the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilize a STACK to accomplish this.
     *
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */
    public void revealZeroes(int x, int y) {
        Stack1Gen<Integer[]> stack = new Stack1Gen<Integer[]>();
        stack.push(new Integer[]{x, y});

        while (! stack.isEmpty()) {
            Integer[] top = stack.pop();
            Cell cell = this.field[top[0]][top[1]];
            cell.setRevealed(true);
            
            // Push valid neighbors to the stack
            // left
            if (this.isInBounds(top[0]-1, top[1]) && !this.field[top[0]-1][top[1]].getRevealed() && (this.field[top[0]-1][top[1]].getStatus().equals("0") || this.field[top[0]-1][top[1]].getStatus().equals("-"))) {  // left
                stack.push(new Integer[]{top[0]-1, top[1]});
            }
            // right
            if (this.isInBounds(top[0]+1, top[1]) && !this.field[top[0]+1][top[1]].getRevealed() && (this.field[top[0]+1][top[1]].getStatus().equals("0") || this.field[top[0]+1][top[1]].getStatus().equals("-"))) {  // right
                stack.push(new Integer[]{top[0]+1, top[1]});
            }
            // bottom
            if (this.isInBounds(top[0], top[1]-1) && !this.field[top[0]][top[1]-1].getRevealed() && (this.field[top[0]][top[1]-1].getStatus().equals("0") || this.field[top[0]][top[1]-1].getStatus().equals("-"))) {  // down
                stack.push(new Integer[]{top[0], top[1]-1});
            }
            // above
            if (this.isInBounds(top[0], top[1]+1) && !this.field[top[0]][top[1]+1].getRevealed() && (this.field[top[0]][top[1]+1].getStatus().equals("0") || this.field[top[0]][top[1]+1].getStatus().equals("-"))) {  // up
                stack.push(new Integer[]{top[0], top[1]+1});
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return (x >= 0 && x < this.field.length && y >= 0 && y < this.field[0].length);
    }
    
    /**
     * revealStartingArea
     *
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.
     * 
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a queue be useful for this function?
     *
     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealStartingArea(int x, int y) {
        Q1Gen<Integer[]> queue = new Q1Gen<Integer[]>();
        queue.add(new Integer[]{x, y});

        while (queue.length() != 0) {
            Integer[] front = queue.remove();
            this.field[front[0]][front[1]].setRevealed(true);
            if (this.field[front[0]][front[1]].getStatus().equals("M")) {
                break;
            }
            else {
                // left
                if (this.isInBounds(front[0]-1, front[1]) && !this.field[front[0]-1][front[1]].getRevealed()) {  
                    queue.add(new Integer[]{front[0]-1, front[1]});
                }
                // right
                if (this.isInBounds(front[0]+1, front[1]) && !this.field[front[0]+1][front[1]].getRevealed()) {  
                    queue.add(new Integer[]{front[0]+1, front[1]});
                }
                // below
                if (this.isInBounds(front[0], front[1]-1) && !this.field[front[0]][front[1]-1].getRevealed()) {  
                    queue.add(new Integer[]{front[0], front[1]-1});
                }
                // above
                if (this.isInBounds(front[0], front[1]+1) && !this.field[front[0]][front[1]+1].getRevealed()) {  
                    queue.add(new Integer[]{front[0], front[1]+1});
                }
            }
        }
    }

    /**
     * For both printing methods utilize the ANSI colour codes provided! 
     * 
     * 
     * 
     * 
     * 
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void debug() {
        String toReturn = "  ";

        // display the column numbers on the very top row
        for (int i = 0; i < field.length; i++) {
            if (i < 10) {
                toReturn += "  " + i;
            }
            else {
                toReturn += " " + i;
            }
            
        }
        toReturn += "\n";

        for (int r = 0; r < this.field.length; r++) {
            // print the row numbering for each line
            if (r < 10) {
                toReturn += r + " ";
            }
            else {
                toReturn += r;
            }

            for (int c = 0; c < this.field[0].length; c++) {
                if (this.field[r][c].getStatus().equals("-") || this.field[r][c].getStatus().equals("0")) {
                    toReturn += (ANSI_GREEN + "  0" + ANSI_GREY_BACKGROUND);
                }
                else if (this.field[r][c].getStatus().equals("M")) {
                    toReturn += (ANSI_RED + "  M" + ANSI_GREY_BACKGROUND);
                }
                else if (this.field[r][c].getStatus().equals("F")) {
                    toReturn += (ANSI_BLUE + "  ⚑" + ANSI_GREY_BACKGROUND);
                }
                else {
                    toReturn += (ANSI_YELLOW + "  " + this.field[r][c].getStatus() + ANSI_GREY_BACKGROUND);
                }   
            }
            toReturn += "\n";
        }

        System.out.println(toReturn);
    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {
        String toReturn = "  ";

        // display the column numbers on the very top row
        for (int i = 0; i < field.length; i++) {
            if (i < 10) {  // if r is a single digit number, it needs padding to match the double digit numbers
                toReturn += "  " + i;
            }
            else {         // double digit numbers do not need padding
                toReturn += " " + i;
            }
        }
        toReturn += "\n";

        for (int r = 0; r < this.field.length; r++) {
            // print the row numbering for each line
            if (r < 10) {  // if r is a single digit number, it needs padding to match the double digit numbers
                toReturn += r + " ";
            }
            else {         // double digit numbers do not need padding
                toReturn += r;
            }
            
            for (int c = 0; c < this.field[0].length; c++) {
                if (this.field[r][c].getRevealed()) {
                    if (this.field[r][c].getStatus().equals("M")) {
                        // Make the mines red then reset the background
                        toReturn += (ANSI_RED + "  M" + ANSI_GREY_BACKGROUND);
                    }
                    else if (this.field[r][c].getStatus().equals("0")) {
                        toReturn += (ANSI_GREEN + "  0" + ANSI_GREY_BACKGROUND);
                    }
                    else if (this.field[r][c].getStatus().equals("F")) {
                        toReturn += (ANSI_BLUE + "  ⚑" + ANSI_GREY_BACKGROUND);
                    }
                    else {
                        toReturn += (ANSI_YELLOW + "  " + this.field[r][c].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                }
                // cells not revealed are filled with "-"
                else {
                    toReturn +=  "  -" + ANSI_GREY_BACKGROUND;
                }
            }
            toReturn += "\n";
        }
        System.out.println();
        return toReturn;
    }
}
