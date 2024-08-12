public class Matrix {
    private int rows; 
    private int cols;
    private int[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public Matrix(int[][] arr) {
        this.rows = arr.length;
        this.cols = arr[0].length;
        this.matrix = arr;
    }

    public int[][] getMatrix() { return this.matrix; }

    // returns a mirror image of your Matrix
    // The ith row of your matrix becomes the ith column of the return Matrix
    public Matrix transpose() {
        Matrix transposed_matrix = new Matrix(new int[this.cols][this.rows]);

        for (int row = 0; row < transposed_matrix.rows; row++) {
            for (int col = 0; col < transposed_matrix.cols; col++) {
                transposed_matrix.matrix[col][row] = this.matrix[row][col];  // set each entry in the new matrix to be the same entry in your matrix, 
                                                                             // but with the order of the indices flipped
            }
        }
        return transposed_matrix;
    }

    public String toString() {
        String toReturn = "";
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                toReturn = toReturn + this.matrix[row][col] + " ";
            }
            toReturn += '\n';
        }
        return toReturn;
    }

    // Milestone 4
    public static void main(String[] args) {
        int[][] t = {
            {1, 2, 3},
            {4, 5, 6}, 
            {7, 8, 9}
        };

        Matrix matrix = new Matrix(t);

        System.out.println(matrix);
        System.out.println(matrix.transpose());
    }
}
