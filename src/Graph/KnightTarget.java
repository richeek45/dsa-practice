package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class KnightTarget {
    static class Cell {
        int x;
        int y;
        int dist;
        Cell(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static boolean withinBounds(int x, int y, int N) {
        return (x >= 0 && x <= N && y >=0 && y <= N);
    }

    static int minStepsToKnightPos(int[] knightPos, int[] targetPos, int N) {
        int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] dy = {-1, -2, 2, 1, 1, 2, -2, -1};
        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<Cell> q = new LinkedList<>();
        // first time initializing with knightPos[x,y]  and distance as 0
        q.add(new Cell(knightPos[0], knightPos[1], 0));
        visited[knightPos[0]][knightPos[1]] = true;

        while(!q.isEmpty()) {
            Cell top = q.poll();
            System.out.println(top.dist + " " + top.x + " " + top.y);
            if (top.x == targetPos[0] && top.y == targetPos[1]) {
                return top.dist;
            }

            // we are adding all the 8 possible moves of the knight to the queue
            for (int i = 0; i < 8; i++) {
                int x = top.x + dx[i];
                int y = top.y + dy[i];
                int dist = top.dist + 1;
                if (withinBounds(x, y, N) && !visited[x][y]) {
                    visited[x][y] = true;
                    q.add(new Cell(x, y, dist));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int N = 30;
        int[] knightPos = {1, 1};
        int[] targetPos = {30, 30};
        int ans = minStepsToKnightPos(knightPos, targetPos, N);
        System.out.println(ans);
    }
}
