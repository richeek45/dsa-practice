package matrix;

public class SpecificPairMatrix {
    //Given an n x n matrix mat[n][n] of integers,
    // find the maximum value of mat(c, d) – mat(a, b) over all choices of indexes such that both c > a and d > b.
    static void findSpecificPair(int[][] matrix) {
        int N = matrix.length;
        int[][] maxArr = new int[N][N];
        // last element will be same because it cannot be compared with larger (i, j) index.
        maxArr[N-1][N-1] = matrix[N-1][N-1];
        // preprocess last row and setting maxVal
        int maxVal = matrix[N-1][N-1];
        for (int j = N-2; j >= 0; j--) {
            if (matrix[N-1][j] > maxVal) {
                maxVal = matrix[N-1][j];
            }
            maxArr[N-1][j] = maxVal;
        }
        // preprocessing last col and setting maxVal
        maxVal = matrix[N-1][N-1];
        for (int i = N-2; i >= 0; i--) {
            if (matrix[i][N-1] > maxVal) {
                maxVal = matrix[i][N-1];
            }
            maxArr[i][N-1] = maxVal;
        }

        // preprocess rest of the matrix from bottom to max
        for (int i = N-2; i >= 0; i--) {
            for (int j = N-2; j >= 0; j--) {
                if (matrix[i+1][j+1] - matrix[i][j] > maxVal) {
                    maxVal = matrix[i+1][j+1] - matrix[i][j];
                }
                maxArr[i][j] = Math.max(matrix[i][j], Math.max(matrix[i+1][j], matrix[i][j+1]));
            }
        }
        System.out.println(maxVal);
    }

    public static void main(String[] args) {
        int[][] mat = { { 1, 2, -1, -4, -20 },
                        { -8, -3, 4, 2, 1 },
                        { 3, 8, 6, 1, 3 },
                        { -4, -1, 1, 7, -6 },
                        { 0, -4, 10, -5, 1 }};
        findSpecificPair(mat);
    }
}
