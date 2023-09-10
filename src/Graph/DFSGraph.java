package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DFSGraph {
    private int v;
    private LinkedList<Integer> adj[];
    boolean[] visited;


    @SuppressWarnings("unchecked")
    DFSGraph(int vertex) {
        v = vertex;
        visited = new boolean[v];
        adj = new LinkedList[v];
        for(int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adj[src].add(dest);
        adj[dest].add(src);
    }

    public void DFS(int src) {

        visited[src] = true;
        System.out.print(src + " ");
        Iterator<Integer> itr = adj[src].listIterator();
        while(itr.hasNext()) {
            int nextVertex = itr.next();
            if (!visited[nextVertex]) {
                DFS(nextVertex);
            }
        }
    }


    public static void main(String[] args) {
        int vertex = 5;
        DFSGraph graph = new DFSGraph(vertex);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 4);
        graph.DFS(0);

    }
}
