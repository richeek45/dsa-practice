package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    static class Coordinates {
        int x;
        int y;
        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isSafe(int[][] matrix, boolean[][] visited, int x, int y, int row, int col) {
        if(x >=0 && x < row && y >=0 && y < col && matrix[x][y] == 1 && !visited[x][y]) {
            return true;
        }
        return false;
    }

    public static void dfs(int[][] matrix, boolean[][] visited, int x, int y, int row, int col) {
        //  Searching in all 8 directions of (x, y) coordinates
        int[] rowX = { 1, 1, 0, -1, -1, -1, 0, 1};
        int[] colY = { 0, 1, 1, 1, 0, -1, -1, -1};
        int dir = rowX.length;
        visited[x][y] = true;

        for (int i = 0; i < dir; i++) {
            if (isSafe(matrix, visited, x + rowX[i], y + colY[i], row, col)) {
                dfs(matrix, visited, x + rowX[i], y + colY[i], row, col);
            }
        }
    }

    public static int findIsland(int[][] matrix) {
        int count = 0;

        int row  = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    dfs(matrix, visited, i, j, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    public static void bfs(int[][] matrix, boolean[][] visited, int x, int y, int row, int col) {
        int[] rowX = { 1, 1, 0, -1, -1, -1, 0, 1};
        int[] colY = { 0, 1, 1, 1, 0, -1, -1, -1};
        Queue<Coordinates> q = new LinkedList<>();
        q.add(new Coordinates(x, y));

        while(!q.isEmpty()) {
            Coordinates curr = q.poll();
            visited[curr.x][curr.y] = true;
            // 8 possible directions of an (x, y) coordinate
            for(int i = 0; i < 8; i++) {
                int newX = curr.x + rowX[i];
                int newY = curr.y + colY[i];
                if (isSafe(matrix, visited, newX, newY, row, col)) {
                    q.add(new Coordinates(newX, newY));
                }
            }
        }
    }

    public static int findIsland2(int[][] matrix) {
        // Using BFS approach
        int count = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 1 && !visited[i][j]) {
                    bfs(matrix, visited, i, j, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int M[][] = new int[][] {
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 0, 1, 1 },
                { 0, 1, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };
        int islandCount = findIsland2(M);
        System.out.println(islandCount);
    }
}
