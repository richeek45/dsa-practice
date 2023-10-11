package Graph;

import java.util.Arrays;

public class GraphNodeColoring {
    private static int V;

    private static boolean isSafe(int[][] graph, int[] colors, int c, int v) {
        // for vertex v, checking the adjacent vertices color
        for(int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && c == colors[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean graphColorUtil(int[][] graph, int m, int[] colors, int v) {
        if (v == V) {
            return true;
        }

        // Trying to fill different set of colors
        for(int c = 1; c <= m; c++) {

            if(isSafe(graph, colors, c, v)) {
                colors[v] = c;

                // recur for the next vertex v
                if  (graphColorUtil(graph, m, colors, v+1)) {
                    return true;
                }

                // if sol is not found after recursion, then reset the color for backtracking
                colors[v] = 0;
            }
        }

        // cannot color the vertex
        return false;
    }

    public static void graphColoring(int[][] graph, int m) {
        // m -> no of colors, V -> no of vertices
        int[] colors = new int[V];
        Arrays.fill(colors, 0);

        if (graphColorUtil(graph, m, colors, 0)) {
            printColor(colors);
        }
    }

    private static void printColor(int[] colors) {
        for(int color: colors) {
            System.out.print(color + " ");
        }
    }


    public static void main(String[] args) {
        /* Create following graph and test
           whether it is 3 colorable
              (3)---(2)
               |   / |
               |  /  |
               | /   |
              (0)---(1)
        */
        int[][] graph = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 },
        };
        V = graph.length;

        int m = 3;
        graphColoring(graph, 3);

    }
}
