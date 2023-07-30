package matrix;

public class Rotate90DegMatrix {
    static void printMatrix(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void rotate90Matrix(int[][] matrix) {
        int len = matrix.length;
        // here i index refers to the number of loops or cycles we need to traverse
        // the number of loops in N*N square matrix are always N/2
        // every matrix inside one full cycle is (N-2)*(N-2) matrix
        // a 6*6 matrix will have 4*4 and 2*2 matrix inside them
        for (int i = 0; i < len/2; i++) {
            for (int j = i; j < len-i-1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len-j-1][i];
                matrix[len-j-1][i] = matrix[len-i-1][len-j-1];
                matrix[len-i-1][len-j-1] = matrix[j][len-i-1];
                matrix[j][len-i-1] = temp;
            }
        }


        printMatrix(matrix);
    }

    static void rotate90Matrix2(int[][] matrix) {
        // before rotation the indices of the 3*3 matrix
        // 01 02 03
        // 10 11 12
        // 20 21 22
        // after rotation -> row became col, starting with col with last row -> first row printing
        // 20 10 01
        // 21 11 02
        // 22 12 03
        int rowLen = matrix.length, colLen = matrix[0].length;
        for (int j = 0; j < colLen; j++) {
            for (int i = rowLen-1; i >= 0; i--) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void diagonalSwap(int[][] matrix, int i, int j) {
        // swapping across diagonal
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    static void rotate90Matrix3(int[][] matrix) {
        //  Approach: The Approach is to rotate the given matrix two times,
        //  first time with respect to the Main diagonal, matrix[0][0] -> matrix[n-1][n-1]
        //  next time rotate the resultant matrix with respect to the middle column
        int N = matrix.length;
        // think of replacing values as blocks. First we exchange 0-th block
        // where we swap[(0, 1),(1, 0)], swap[(0,2)(2,0)], swap[(0,3)(3, 0)]
        // similarly we exchange in 1st-block as swap[(1,2)(2,1)], swap[(1,3)(3,1)]
        // with each block index swapping greater index than block index
        // diagonal is (0,0), (1,1), (2,2) so we are not swapping it.
        // in the last block (N-1)th block there is no index greater than (N-1) hence not swapping
        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                diagonalSwap(matrix, i, j);
            }
        }

        // In 2nd step, we need to rotate along the middle y-axis
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][N-1-j];
                matrix[i][N-1-j] = temp;
            }
        }
        printMatrix(matrix);
    }

    static void rotate90Matrix4(int[][] matrix) {
        // The only difference is that in first rotation we rotate about the Secondary Diagonal
        // and after that about the Middle row (x-axis).
        int N = matrix.length;

        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < N-1-i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N-1-j][N-1-i];
                matrix[N-1-j][N-1-i] = temp;
            }
        }

        for (int i = 0; i < N/2; i++) {
            for (int j = 0; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N-1-i][j];
                matrix[N-1-i][j] = temp;
            }
        }

        printMatrix(matrix);
    }

    public static void main(String[] args) {
        int arr[][] = { { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 } };
//        rotate90Matrix(arr);
//        rotate90Matrix2(arr);
//        rotate90Matrix3(arr);
        rotate90Matrix4(arr);
    }
}
