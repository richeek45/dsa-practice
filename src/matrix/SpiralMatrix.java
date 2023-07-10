package matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpiralMatrix {
    // Print a given matrix in spiral form
    static void printSpiralMatrix(int[][] matrix) {
        int rowLength = matrix.length, columnLength = matrix[0].length;
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[][] seen = new boolean[rowLength][columnLength]; // 2D boolean array to store the visited coordinate
        int[] dr = {0, 1, 0, -1}; // direction of movement of rows in all 4 directions
        int[] dc = {1, 0, -1, 0}; // direction of movement of columns in all 4 directions
        int x = 0, y = 0, di = 0; // x, y -> coordinates of matrix
        // di -> direction to move (0: right, 1:bottom, 2: left, 3: up) determined by dr, dc values
        for (int i = 0; i < rowLength * columnLength; i++) {
            // add it to the ans and make seen boolean true for x,y
            ans.add(matrix[x][y]);
            seen[x][y] = true;
            // cr, cc -> next (x,y) coordinates
            int cr = x + dr[di];
            int cc = y + dc[di];
            // if next position (cr,cc) is within array bounds and also not visited before
            // make the x, y to be in that direction
            if (cr >= 0 && cr < rowLength && cc >=0 && cc < columnLength && !seen[cr][cc]) {
                x = cr;
                y = cc;
            } else {
                // array is out of bounds or visited before in the spiral
                // meaning direction needs to be changed based on the previous direction
                di = (di + 1) % 4;
                x += dr[di];
                y += dc[di];
            }
        }
        System.out.println(ans);
    }

    // printing by dividing matrix into cycles or loops
    static void printSpiralMatrix2(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int rowLength = matrix.length, colLength = matrix[0].length;
        int rowIndex = 0, colIndex = 0;

        while (rowIndex < rowLength && colIndex < colLength) {
            // print the row line in right direction along each column
            for (int i = colIndex; i < colLength; i++) {
                res.add(matrix[rowIndex][i]);
            }
            rowIndex++;

            // print the column line in the bottom direction
            for (int i = rowIndex; i < rowLength; i++) {
                res.add(matrix[i][colLength - 1]);
            }
            colLength--;
            if (rowIndex < rowLength) {
                // print the row line in left direction
                for (int i = colLength - 1; i >= colIndex; i--) {
                    res.add(matrix[rowLength - 1][i]);
                }
                rowLength--;
            }

            if (colIndex < colLength) {
                for (int i = rowLength - 1; i >= rowIndex; i--) {
                    res.add(matrix[i][colIndex]);
                }
                colIndex++;
            }

        }
        System.out.println(res);
    }


    public static void main(String[] args) {
        int[][] matrix =   {{1,   2,   3,   4,  18},
                            {5,   6,   7,   8,  19},
                            {9,   10,  11,  12, 20},
                            {13,  14,  15,  16, 21}};
//        printSpiralMatrix(matrix);
        printSpiralMatrix2(matrix);
    }
}
