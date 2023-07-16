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

    public static void main(String[] args) {
        int arr[][] = { { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 } };
//        rotate90Matrix(arr);
        rotate90Matrix2(arr);
    }
}
