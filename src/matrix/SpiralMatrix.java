package matrix;

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


    public static void main(String[] args) {
        int[][] matrix =   {{1,   2,   3,   4},
                            {5,   6,   7,   8},
                            {9,   10,  11,  12},
                            {13,  14,  15,  16}};
        printSpiralMatrix(matrix);
    }
}
