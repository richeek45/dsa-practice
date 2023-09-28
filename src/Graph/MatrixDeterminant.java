package Graph;

public class MatrixDeterminant {
    public static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int findDeterminant(int N, int[][] matrix) {
        if (N == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }

        int[][] newMatrix = new int[N-1][N-1];
        int ans = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        for(int k = 0; k < N; k++) {
            for(int i = 1; i < row; i++) {
                for(int j = 0; j < col; j++) {

                    if (j > k) {
                        newMatrix[i-1][j-1] = matrix[i][j];
                    } else if (j < k) {
                        newMatrix[i-1][j] = matrix[i][j];
                    }
                }
            }
            int sign = (int)Math.pow(-1,(1+1+k));
            ans += sign * matrix[0][k] * findDeterminant(N-1, newMatrix);
//            printMatrix(newMatrix);
            System.out.println(matrix[0][k] + ", sign = " +sign + " " + k);
            System.out.println(ans + ", ");
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] matrix = {
                { 2, -1, -1 },
                { -1, 3, -1 },
                { -1, -1, 3 }};
        int[][] matrix2 = {
                {1, 0, 0},
                {0, 2, -1},
                {0, -1, 2}
        };
        int[][] matrix3 = {
                {2, -4, 3, -1},
                {4, 5, -2, -3},
                {1, -5, 0, 2},
                {-5, 3, -4, 5}
            };
        int size = matrix3.length;
        int result = findDeterminant(size, matrix3);
        System.out.println(result);

    }
}
