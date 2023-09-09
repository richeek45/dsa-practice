package Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFSGraph {
    private int v;
    LinkedList<Integer> adj[];

    // Constructor
    @SuppressWarnings("unchecked")
    BFSGraph(int vertex) {
        v = vertex;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }


    public void addEdge(int src, int dest) {
        // adding an edge from src to dest
        adj[src].add(dest);
        // adding an edge from dest to src
        adj[dest].add(src);
    }

    void BFS(int src) {
        // src is the edge from which all the nodes are visited
        boolean[] visited = new boolean[v]; // storing the boolean for vertices from 0...4
        Queue<Integer> queue = new LinkedList<>();
        visited[src] = true; // marking the src true for visited
        queue.add(src);

        while(!queue.isEmpty()) {
            Integer source = queue.poll();
            System.out.print(source + " ");
            Iterator<Integer> adjacentVertex = adj[source].listIterator();
            while(adjacentVertex.hasNext()) {
                int N = adjacentVertex.next();
                if (!visited[N]) {
                    visited[N] = true;
                    queue.add(N);
                }
            }
        }


    }

    public static void main(String[] args) {
        // 0 - 1 - 3
        //  \  |   |
        //     2 - 4
        int vertex = 5;
        BFSGraph graph = new BFSGraph(vertex);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.BFS(3);

    }
}
