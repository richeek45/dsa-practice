package Graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class CreateGraph {
    HashMap<Integer, TreeSet<Integer>> graph;
    static int v; // No of vertex present

    public CreateGraph() {
        graph = new HashMap<>();
        // create a new treeset for all the vertex
        for (int i = 0; i < v; i++) {
            graph.put(i, new TreeSet<>());
        }
    }

    public void addEdge(int src, int dest) {
        // adding dest to the src vertex
        graph.get(src).add(dest);
        // adding src to the dest vertex
        graph.get(dest).add(src);
    }

    public void searchEdge(int src, int dest) {
        Iterator set = graph.get(src).iterator();
        if (graph.get(src).contains(dest)) {
            System.out.println(src + "is present in: " + dest);
        }
        System.out.println();
    }

    public void printGraph() {
        for (int i = 0; i < v; i++) {
            System.out.print("Adjacency list of vertex: " + i + " -> ");
            Iterator set = graph.get(i).iterator();
            while(set.hasNext()) {
                System.out.print(set.next() + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        // 0 - 1 \
        // | / |  2
        // 4 - 3 /
        // 0 -> [1, 4]
        // 1 -> [0, 2, 3, 4]
        // 2 -> [1, 3]
        // 3 -> [1, 2, 4]
        // 4 -> [0, 1, 3]
        v = 5;
        CreateGraph graph = new CreateGraph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 4);
        graph.printGraph();
    }
}
