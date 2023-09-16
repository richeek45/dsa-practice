package Graph;

import java.util.Stack;

public class RatInAMaze {
    private static int v;
    RatInAMaze(int vertex) {
        v = vertex;
    }

    static void printSolution(int[][] sol) {
        System.out.println("Solution matrix");
        int len1 = sol.length;
        int len2 = sol[0].length;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    // To check the boundary of the current position
    static boolean safeMove(int[][] maze, int x, int y) {
        boolean correctMove = x >= 0 && x < v && y >= 0 && y < v && maze[x][y] == 1;
        if (correctMove) {
            return true;
        }
        return false;
    }

    static boolean solveMaze(int[][] maze, int[][] sol, int x, int y) {
        // if end goal is reached
        if (x == v-1 && y == v-1 && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }

        if (safeMove(maze, x, y)) {
            System.out.println(x + " " + y);
            // If sol[x][y] is already part of the solution matrix
            if (sol[x][y] == 1) {
                return false;
            }

            sol[x][y] = 1;

            // check for forward direction in a recursive call
            if (solveMaze(maze, sol, x+1, y)) {
                return true;
            }

            // check for downward direction in a recursive call
            if (solveMaze(maze, sol, x, y+1)) {
                return true;
            }

            // If the travelled forward and downward direction is not generating solution path,
            // mark the [x, y] coordinates as 0 (no solution found)
            sol[x][y] = 0;
        }

        return false;
    }


    static void findPath(int[][] matrix) {
        // Time Complexity: O(2^(n^2)).
        // The recursion can run upper-bound 2^(n^2) times.
        // Auxiliary Space: O(n^2).
        int[][] sol = new int[v][v];

        if (!solveMaze(matrix, sol, 0, 0)) {
            System.out.print("solution does not exist!!");
            return;
        }

        printSolution(sol);
    }

    public static void main(String[] args) {
        RatInAMaze graph = new RatInAMaze(4);
        int[][] matrix = {
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {0, 1, 1, 1}};
        findPath(matrix);

    }
}
