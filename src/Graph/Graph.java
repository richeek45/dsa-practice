package Graph;
import java.util.LinkedList;

public class Graph {
    // A utility class made only for testing code
    private int v;
    private LinkedList<Integer> adj[];
    @SuppressWarnings("unchecked")
    Graph(int vertex) {
        v = vertex;
        adj = new LinkedList[v];
        // initializing array of linkedlist for every vertex
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adj[src].add(dest);
    }




    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);


    }

}
