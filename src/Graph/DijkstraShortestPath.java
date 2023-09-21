package Graph;

import java.util.*;

public class DijkstraShortestPath {
    private static List<List<AdjacentNode>> adj;

    DijkstraShortestPath(int v) {
        adj = new ArrayList<>();
        for(int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    static class AdjacentNode {
        int vertex;
        int weight;
        AdjacentNode(int v, int w) {
            vertex = v;
            weight = w;
        }

        public int getWeight() {
            return weight;
        }
    }

    void addEdge(int vertex1, int vertex2, int weight) {
        adj.get(vertex1).add(new AdjacentNode(vertex2, weight));
        adj.get(vertex2).add(new AdjacentNode(vertex1, weight));
    }

    public static void dijkstra2(int src, int vertex) {
        // O(E * logV)
        PriorityQueue<AdjacentNode> pq = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
        int[] distance = new int[vertex];
        boolean[] visited = new boolean[vertex];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        pq.add(new AdjacentNode(src, 0));
        distance[src] = 0;

        while(!pq.isEmpty()) {
            int baseVertex = pq.poll().vertex;
            System.out.println(baseVertex);

            // Each baseVertex has arraylist of node with edge weights
            // each adjNode has vertex and weight from the baseVertex
            for(AdjacentNode adjNode: adj.get(baseVertex)) {
                int adjWeight = adjNode.getWeight();
                int adjVertex = adjNode.vertex;
                if (adjWeight + distance[baseVertex] < distance[adjVertex]) {
                    distance[adjVertex] = adjWeight + distance[baseVertex];
                    pq.add(new AdjacentNode(adjVertex, distance[adjVertex]));
                }
            }
        }

        printSolution(distance, vertex);
    }



    // Method 1
    static int minDistance(boolean[] visited, int[] distance, int v) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for(int i = 0; i < v; i++) {
            if (visited[i] == false && distance[i] <= min) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void printSolution(int[] distance, int v) {
        System.out.println( "Vertex \t\t Distance from Source");
        for(int i = 0; i < v; i++) {
            System.out.println(i + " \t\t " + distance[i]);
        }
    }

    static void dijkstraShortestPath(int[][] graph, int src, int v) {
        // 1. distance array that stores the distance from the vertex src.
        // 2. visited boolean array for storing to check if the vertex is visited

        int[] distance = new int[v];
        boolean[] visited = new boolean[v];

        for(int i = 0; i < v; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // distance from the source vertex is always 0
        distance[src] = 0;

        // traversing through each vertex
        for(int i = 0; i < v; i++) {

            int baseVertex = minDistance(visited, distance, v);
            visited[baseVertex] = true;
            System.out.print (baseVertex + " -> ");
            // calculating the adjacent vertex (j-index) from the base vertex
            for(int j = 0; j < v; j++) {
                if (!visited[j] && graph[baseVertex][j] != 0 &&
                        distance[baseVertex] != Integer.MAX_VALUE &&
                        distance[j] > distance[baseVertex] + graph[baseVertex][j]) {
                    distance[j] = distance[baseVertex] + graph[baseVertex][j];
                }
            }
        }
        printSolution(distance, v);
    }

    public static void main(String[] args) {
        //   1 - 2 -- 3
        //  /|   |\   | \
        // 0 |   8 \  |  4
        //  \| / |  \ | /
        //   7 - 6 -- 5
        // 0 - 1 -> 4, 0 - 7 -> 8, 1 - 7 -> 11, 1 - 2 -> 8, 7 - 8 -> 7, 7 - 6 -> 1,
        // 6 - 8 -> 6, 2 - 8 -> 2, 2 - 3 -> 7, 2 - 5 -> 4, 3 - 5 -> 14, 3 - 4 -> 9, 4 - 5 -> 10
        int[][] graph = {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };

        int vertex = graph.length;

//        dijkstraShortestPath(graph, 0, vertex);

        DijkstraShortestPath graph2 = new DijkstraShortestPath(vertex);
        graph2.addEdge(0, 1, 4);
        graph2.addEdge(0,7,8);
        graph2.addEdge(1,2,8);
        graph2.addEdge(1,7,11);
        graph2.addEdge(6,7,1);
        graph2.addEdge(6,8,6);
        graph2.addEdge(7,8,7);
        graph2.addEdge(2,8,2);
        graph2.addEdge(2,3,7);
        graph2.addEdge(2,5,4);
        graph2.addEdge(3,5,14);
        graph2.addEdge(5,6,2);
        graph2.addEdge(3,4,9);
        graph2.addEdge(4,5,10);

        int src = 0;
        graph2.dijkstra2(src, vertex);

    }
}
