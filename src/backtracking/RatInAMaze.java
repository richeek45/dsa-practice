package backtracking;

import java.util.*;

public class RatInAMaze {
    // Consider a rat placed at (0, 0) in a square matrix m[ ][ ] of order n and has to reach the destination at (n-1, n-1).
    // The task is to find a sorted array of strings denoting all the possible directions
    // which the rat can take to reach the destination at (n-1, n-1).
    // The directions in which the rat can move are ‘U'(up), ‘D'(down), ‘L’ (left), ‘R’ (right).
    static class Pair {
        int x;
        int y;
        int dir;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
            this.dir = 0;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }
    }

    public static void getAllPath2(int[][] matrix, int x, int y, int row, int col, List<String> result) {
        if (x < 0 || x >= row || y < 0 || y >= col || matrix[x][y] == 0) {
            return;
        }

        Map<Integer, String> map = new HashMap<>();
        ArrayList<String> move = new ArrayList<>(Arrays.asList("R", "D", "L", "U"));
        for(int i = 0; i < 4; i++) {
            map.put(i, move.get(i));
        }
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(x, y));
        int[][] visited = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                visited[i][j] = 0;
            }
        }
        int[] rowX = { 0, 1, 0, -1 };
        int[] colY = { 1, 0, -1, 0 };
        // DRRRRDDD DRDRURRDDD DDRRURRDDD DDRURRRDDD
        while(!stack.isEmpty()) {
            Pair coord = stack.pop();
            int dir = coord.dir;
            coord.setDir(coord.dir + 1);
            stack.push(coord);


            if (coord.x == row-1 && coord.y == col-1) {
                return;
            }

            // dir = 0 -> Right 1 -> Down 2 -> Left 3 -> Up
            if (dir >= 0 && dir < 4) {
                int xDir = coord.x + rowX[dir];
                int yDir = coord.y + colY[dir];
                if (xDir >= 0 && xDir < row && yDir >= 0 && yDir < col && matrix[xDir][yDir] == 1 && visited[xDir][yDir] == 0) {
                    visited[xDir][yDir] = 1;
                    stack.push(new Pair(xDir, yDir));
                    result.add(String.valueOf(map.get(dir)));
                }
            } else {
                visited[coord.x][coord.y] = 0;
                stack.pop();
            }
        }
    }

    public static void getAllPath(int[][] matrix, int x, int y, int row, int col, List<String> result, String dir) {
        if (x < 0 || x >= row || y < 0 || y >= col || matrix[x][y] == 0) {
            return;
        }

        if (x == row-1 && y == col-1) {
            result.add(dir);
            return;
        }

        int[] rowX = { 0, 1, 0, -1 };
        int[] colY = { 1, 0, -1, 0 };
        String[] move = {"R", "D", "L", "U" };
        // notion-db -> secret_ziaN2JF0D6jy99ifiQGTQxTJCWUaTZ0kvkNZ8lEaN6X
        // Making the position 0 so that it doesn't traverse again
        matrix[x][y] = 0;

        for(int i = 0; i < 4; i++) {
            int xDir = x + rowX[i];
            int yDir = y + colY[i];
            getAllPath(matrix, xDir, yDir, row, col, result, dir + move[i]);
        }

        // moving it back it 1 after backtracking to the loop,
        // so that it can be traversed by other loops
        matrix[x][y] = 1;
    }

    private static boolean isSafe(int[][] matrix, int x, int y, int row, int col) {
        if(x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] > 0) {
            return true;
        }
        return false;
    }

    // Solution for rat in a maze when multiple moves are allowed
    // The non-zero value of mat[i][j] indicates number of maximum jumps rat can make from cell mat[i][j].
    public static boolean getAllPath3(int[][] matrix, int x, int y, int row, int col, int[][] sol) {
        // destination is reached
        if (x == row-1 && y == col-1) {
            sol[x][y] = 1;
            return true;
        }

        if (!isSafe(matrix, x, y, row, col)) {
            return false;
        }

        // marking the x, y coordinates as part of the solution
        sol[x][y] = 1;
        // for the number of possible moves in matrix[x][y]
        int moves = matrix[x][y];
        for(int i = 1; i <= moves; i++) {
            // moving in horizontal y direction
            if(getAllPath3(matrix, x, y + i, row, col, sol)) {
                return true;
            }

            // moving in vertical x direction
            if(getAllPath3(matrix, x + i, y, row, col, sol)) {
                return true;
            }
        }

        // backtracking to previous sol to find other possible solutions
        sol[x][y] = 0;
        return false;
    }

    public static void printSol(int[][] sol) {
        for(int i = 0; i  < sol.length; i++) {
            for(int j = 0; j < sol[0].length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        int[][] matrix = {
//                { 1, 0, 1, 1, 1 },
//                { 1, 1, 1, 0, 1 },
//                { 1, 1, 0, 0, 1 },
//                { 0, 1, 1, 0, 1 },
//                { 0, 0, 1, 1, 1 } };
        int[][] matrix = {
                {2, 1, 0, 0},
                {3, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 0, 1}};
        int x = 0, y = 0;
        int row = matrix.length;
        int col = matrix[0].length;
//        List<String> result = new ArrayList<>();
//        getAllPath(matrix, 0 , 0, row, col, result, "");
//        getAllPath2(matrix, 0 , 0, row, col, result);
//        for(String str : result) {
//            System.out.print(str + " ");
//        }

        int[][] sol = new int[row][col];
        printSol(matrix);
        getAllPath3(matrix, 0, 0, row, col, sol);
        printSol(sol);

    }
}
