package matrix;

public class MaxSumDiagonalMatrix {
    static void getMaxSumDiagonal(int[][] matrix) {
        int maxDiagonalSum = Integer.MIN_VALUE;
        int N = matrix.length;

        // rotate the rows and check for maximum diagonal sum
        for (int i = 0; i < N; i++) {
            int diagonalSum = 0;
            for (int j = 0; j < N; j++) {
                diagonalSum += matrix[(i + j) % N][j];
            }

            maxDiagonalSum = Math.max(diagonalSum, maxDiagonalSum);
        }

        // rotate the cols and check for max diagonal sum
        for (int j = 0; j < N; j++) {
            int diagonalSum = 0;
            for (int i = 0; i < N; i++) {
                diagonalSum += matrix[i][(i + j) % N];
            }
            maxDiagonalSum = Math.max(diagonalSum, maxDiagonalSum);
        }

        System.out.println(maxDiagonalSum);

    }

    public static void main(String[] args) {
        int[][] mat = { { 1, 1, 2 },
                        { 2, 1, 2 },
                        { 1, 2, 2 } };
        int[][] mat2 = { { -1, 2 }, { -1, 3 } };
        getMaxSumDiagonal(mat2);
    }
}
