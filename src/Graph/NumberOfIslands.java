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

    static class DisjointSet {
        int[] parent;
        int[] rank;
        int[] size;
        DisjointSet(int v) {
            parent = new int[v];
            rank = new int[v];
            size = new int[v];
            for(int i = 0; i < v; i++) {
                parent[i] = i;
                size[i] = 1;
                rank[i] = 0;
            }
        }

        // find parent
        public int findParent(int vertex) {
            if(parent[vertex] != vertex) {
                parent[vertex] = findParent(parent[vertex]);
            }
            return parent[vertex];
        }

        // union by rank
        public void unionByRank(int v1, int v2) {
            int p1 = findParent(v1);
            int p2 = findParent(v2);

            // both the vertex are in the same set
            if (p1 == p2) {
                return;
            }

            if (rank[p1] < rank[p2]) {
                parent[p1] = p2;
            } else if (rank[p2] < rank[p1]) {
                parent[p2] = p1;
            } else {
                // if both the vertex have the same rank then increase the rank of the choosen parent
                parent[p1] = p2;
                rank[p2]++;
            }
        }

        // union by size
        public void unionBySize(int v1, int v2) {
            int p1 = findParent(v1);
            int p2 = findParent(v2);

            if (p1 == p2) {
                return;
            }
            if (size[p1] < size[p2]) {
                parent[p1] = p2;
                size[p2] += size[p1];
            } else if (size[p2] < size[p1]) {
                parent[p2] = p1;
                size[p1] += size[p2];
            } else {
                parent[p1] = p2;
                size[p2] += size[p1];
            }
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

    public static int findIsland3(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int v = row * col;
        int[] rowX = { 1, 1, 0, -1, -1, -1, 0, 1};
        int[] colY = { 0, 1, 1, 1, 0, -1, -1, -1};
        DisjointSet set = new DisjointSet(v);

        // union of all the (x, y) coordinates of value 1
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }

                // traverse all the 8 directions
                for (int pos = 0; pos < 8; pos++) {
                    int x = i + rowX[pos];
                    int y = j + colY[pos];
                    if(x >= 0 && x < row && y >= 0 && y < col) {
                        int coor1 = i * col + j;
                        int coor2 = x * col + y;
                        if (matrix[x][y] == 1) {
                            set.unionByRank(coor1, coor2);
                        }
                    }
                }
            }
        }

        int[] freq = new int[row * col];
        int islandCount = 0;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    int pos = set.findParent(i * col + j);
                    if (freq[pos] == 0) {
                        islandCount++;
                        freq[pos]++;
                    } else {
                        freq[pos]++;
                    }
                }
            }
        }

        for(int i = 0; i < freq.length; i++) {
            System.out.print(freq[i] + " ");
        }
        System.out.println();

        return islandCount;
    }

    public static void main(String[] args) {
        int M[][] = new int[][] {
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };
        int islandCount = findIsland3(M);
        System.out.println(islandCount);
    }
}
