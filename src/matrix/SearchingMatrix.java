package matrix;

//Given an n x n matrix and an integer x, find the position of x in the matrix if it is present.
// Otherwise, print “Element not found”. Every row and column of the matrix is sorted in increasing order.
// The designed algorithm should have linear time complexity

public class SearchingMatrix {
    static void search1(int[][] matrix, int len, int val) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (matrix[i][j] == val) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }
        System.out.println("Element not found");
    }

    static void search2(int[][] matrix, int len, int val) {
        int i = 0, j = len -1; // i -> for rows, j -> for columns
        while (i < len && j > 0) {
            if (matrix[i][j] == val) {
                System.out.println(i + " " + j);
                return;
            }
            if (matrix[i][j] > val) {
                j--; // decrease the col index -> all the values in the col is greater than (i, j)
            } else {
                i++; // increasing the row index -> all the values in row is smaller than (i, j)
            }
        }
    }

    public static void main (String[] args) {
        int mat[][] = { { 10, 20, 30, 40 },
                        { 15, 25, 35, 45 },
                        { 27, 29, 37, 48 },
                        { 32, 33, 39, 50 } };
//        search1(mat, 4, 29);
        search2(mat, 4, 48);
    }
}
