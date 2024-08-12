import java.util.LinkedList;
import java.util.Queue;

public class PathExists {
    
    public static boolean doesPathExist(char[][] grid, int sourceRow, int sourceColumn) {
        /*
         * Start at the starting position (sourceRow, sourceColumn)
         * Call bfs/dfs to see whether a path exists to the end
         */
        int endRow = 0, endColumn = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'v' && r != sourceRow && c != sourceColumn) {
                    endRow = r;
                    endColumn = c;
                }
            }
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // return bfs(grid, sourceRow, sourceColumn, endRow, endColumn, visited);
        return dfs(grid, sourceRow, sourceColumn, endRow, endColumn, visited);
    }

    public static boolean dfs(char[][] grid, int row, int col, int endingRow, int endingColumn, boolean[][] visited) {
        // Out of bounds
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || grid[row][col] == 'x' || visited[row][col]) {  
            return false;
        }
        // Reached the end node
        else if (grid[row][col] == 'v' && row == endingRow && col == endingColumn) {
            return true;
        }
        else {
            visited[row][col] = true;
            // recursively call DFS() with each directional neighbor
            return dfs(grid, row, col+1, endingRow, endingColumn, visited) ||
                    dfs(grid, row-1, col, endingRow, endingColumn, visited) ||
                    dfs(grid, row, col-1, endingRow, endingColumn, visited) ||
                    dfs(grid, row+1, col, endingRow, endingColumn, visited);
            
        }
    }
    
    public static boolean bfs(char[][] grid, int row, int col, int endingRow, int endingCol, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();     // instantiate an empty queue of index pairs (integer arrays)
        queue.add(new int[]{row, col});

        while (! queue.isEmpty()) {
            int[] pair = queue.remove();                     // remove current indices from the front of the queue
            int r = pair[0];
            int c = pair[1];

            visited[r][c] = true;
            
            if (r-1 >= 0 && r-1 < grid.length && !visited[r-1][c] && grid[r-1][c] != 'x') {
                queue.add(new int[]{r-1, c});    // add left neighbor indices to queue
            }
            if (r+1 >= 0 && r+1 < grid.length && !visited[r+1][c] && grid[r+1][c] != 'x') {
                queue.add(new int[]{r+1, c});    // add right neighbor indices to queue
            }
            if (c-1 >= 0 && c-1 < grid[0].length && !visited[r][c-1] && grid[r][c-1] != 'x') {
                queue.add(new int[]{r, c-1});    // add bottom neighbor indices to queue
            }
            if (c+1 >= 0 && c+1 < grid[0].length && !visited[r][c+1] && grid[r][c+1] != 'x') {
                queue.add(new int[]{r, c+1});    // add top neighbor indices to queue
            }
        }
        return visited[endingRow][endingCol];
    }
}
