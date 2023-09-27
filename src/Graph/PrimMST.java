package Graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PrimMST {
    // Step 1: Determine an arbitrary vertex as the starting vertex of the MST.
    // Step 2: Follow steps 3 to 5 till there are vertices that are not included in the MST (known as fringe vertex).
    // Step 3: Find edges connecting any tree vertex with the fringe vertices.
    // Step 4: Find the minimum among these edges.
    // Step 5: Add the chosen edge to the MST if it does not form any cycle.
    // Step 6: Return the MST and exit
    private static int v;

    static class Pair {
        int vertex;
        int weight;
        Pair(int v, int w) {
            vertex = v;
            weight = w;
        }
    }

    public static void printMST(int[] parent, int[][] matrix) {
        for(int i = 0; i < v; i++) {
            System.out.println("Edge from " + parent[i] + " - " + i + " = " + matrix[i][parent[i]]);
        }
        System.out.println();
    }

    public static int minVertex(int[] edge, boolean[] visited) {
        int minIndex = -1;
        int minEdge = Integer.MAX_VALUE;

        for(int i = 0; i < v; i++) {
            if (edge[i] < minEdge && visited[i] == false) {
                minEdge = edge[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static void primMST2(int[][] matrix, int src) {
        // O(E * logV)
        int[] edge = new int[v];
        int[] parent = new int[v];
        boolean[] visited = new boolean[v];

        for(int i = 0; i < v; i++) {
            edge[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        PriorityQueue<Pair> q = new PriorityQueue<>(v, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.weight - p2.weight;
            }
        });
        edge[src] = 0;
        q.add(new Pair(src, 0));

        while(!q.isEmpty()) {
            Pair curr = q.poll();
            int currVertex = curr.vertex;

            if (visited[currVertex]) {
                continue;
            }

            visited[currVertex] = true;
            for(int i = 0; i < v; i++) {
                if (matrix[currVertex][i] != 0 && visited[i] == false && matrix[currVertex][i] < edge[i]) {
                    parent[i] = currVertex;
                    edge[i] = matrix[currVertex][i];
                    q.add(new Pair(i, edge[i]));
                }
            }
        }

        printMST(parent, matrix);
    }

    public static void primMST(int[][] matrix, int src) {
        int[] parent = new int[v];
        int[] edge = new int[v];
        boolean[] visited = new boolean[v];

        for(int i = 0; i < v; i++) {
            edge[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        edge[src] = 0;

        // calculating for each vertex
        for (int i = 0; i < v-1; i++) {
            // Pick the vertex with minimum edge which is not yet visited
            int vertex = minVertex(edge, visited);
            System.out.print(vertex + " ");
            visited[vertex] = true;

            // checking all the vertices attached to vertex if the edge length is smaller than previously
            // calculated edge length
            for(int j = 0; j < v; j++) {
                if(matrix[vertex][j] != 0 && visited[j] == false && matrix[vertex][j] < edge[j]) {
                    parent[j] = vertex;
                    edge[j] = matrix[vertex][j];
                }
            }
        }

        printMST(parent, matrix);
    }

    public static void main(String[] args) {
        int[] vertex_0 = {0, 4, 0, 0, 0, 0, 0, 8, 0};
        int[] vertex_1 = {4, 0, 8, 0, 0, 0, 0, 11, 0};
        int[] vertex_2 = {0, 8, 0, 7, 0, 4, 0, 0, 2};
        int[] vertex_3 = {0, 0, 7, 0, 9, 14, 0, 0, 0};
        int[] vertex_4 = {0, 0, 0, 9, 0, 10, 0, 0, 0};
        int[] vertex_5 = {0, 0, 4, 14, 10, 0, 2, 0, 0};
        int[] vertex_6 = {0, 0, 0, 0, 0, 2, 0, 1, 6};
        int[] vertex_7 = {8, 11, 0, 0, 0, 0, 1, 0, 7};
        int[] vertex_8 = {0, 0, 2, 0, 0, 0, 6, 7, 0};

        int[][] matrix = { vertex_0, vertex_1, vertex_2, vertex_3, vertex_4, vertex_5, vertex_6, vertex_7, vertex_8 };
        v = matrix.length;

//        primMST(matrix, 0);
        primMST2(matrix, 0);


    }
}
