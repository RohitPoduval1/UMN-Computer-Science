import java.util.*;

/*
TODO: Show Milestone 1
 * BOTH: Search algorithms for graphs or trees (typically)
 * BFS
 *   • Breadth-First Search (side to side)
 *   • Starts from the start and thoroughly searches each level
 *   • By the time it reaches the end, it has traversed the entire structure 
 * DFS
 *   • Depth-First Search (top to bottom)
 *   • Starts from the start and travels as deep as possible in one direction
 *       then it backs up until it can go down again in a different direction
 */
public class ColorPath {
    // Update the given index (sourceRow, sourceCol) in the color array (image) to the newColor
    // AND all reachable neighbors that are same color as index to newColor. 
    public static int[][] colorPath(int[][] image, int sourceRow, int sourceCol, int newColor) {
        if (image[sourceRow][sourceCol] != newColor) {
            // dfs(image, sourceRow, sourceCol, newColor, image[sourceRow][sourceCol]);
            bfs(image, sourceRow, sourceCol, newColor, image[sourceRow][sourceCol]);
        }
        return image;
    }
    
    public static void dfs(int[][] arr, int row, int col, int newColor, int currentColor) {
        // if row/column are out of bounds or vertex is visited then stop recursion
        if (row >= arr.length || row < 0 || col >= arr[0].length || col < 0 || arr[row][col] != currentColor) {  
            return;
        }
        arr[row][col] = newColor;
        // recursively call DFS() with each directional neighbor
        dfs(arr, row, col-1, newColor, currentColor);  // up
        dfs(arr, row, col+1, newColor, currentColor);  // down
        dfs(arr, row-1, col, newColor, currentColor);  // left
        dfs(arr, row+1, col, newColor, currentColor);  // right
    }

    public static void bfs(int[][] arr, int row, int col, int newColor, int currentColor) {
        Queue<int[]> queue = new LinkedList<>();     // instantiate an empty queue of index pairs (integer arrays)

        queue.add(new int[]{row, col});  // add the starting (row, col) pair as an integer array to the queue.

        while (! queue.isEmpty()) {
            int[] pair = queue.remove();                     // remove current indices from the front of the queue

            int r = pair[0];
            int c = pair[1];
            arr[r][c] = newColor;
            if (r-1 >= 0 && r-1 < arr.length && arr[r-1][c] == currentColor) {   // if left indices are in bounds and not visited
                queue.add(new int[]{r-1, c});    // add left neighbor indices to queue
                
            }
            if (r+1 >= 0 && r+1 < arr.length && arr[r+1][c] == currentColor) {    // if right indices are in bounds and not visited
                queue.add(new int[]{r+1, c});       // add right neighbor indices to queue
                
            }
            if (c-1 >= 0 && c-1 < arr[0].length && arr[r][c-1] == currentColor) {   // if below indices are in bounds and not visited
                queue.add(new int[]{r, c-1});       // add below neighbor indices to queue
            }
            if (c+1 >= 0 && c+1 < arr[0].length && arr[r][c+1] == currentColor) {   // if below indices are in bounds and not visited
                queue.add(new int[]{r, c+1});       // add below neighbor indices to queue
            }
        }
    }
}