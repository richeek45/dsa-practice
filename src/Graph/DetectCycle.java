package Graph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DetectCycle {
    // Given the root of a Directed graph, The task is to check whether the graph contains a cycle or not.
    private static int v;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    DetectCycle(int vertex) {
        v = vertex;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adj[src].add(dest);
    }

    public boolean detectCycle(int src, boolean[] visited, boolean[] recurStack) {
        if (recurStack[src]) {
            return true;
        }

        if (visited[src]) {
            return false;
        }

        visited[src] = true;
        recurStack[src] = true;
        Iterator<Integer> itr = adj[src].listIterator();
        while (itr.hasNext()) {
            int next = itr.next();
            System.out.println(src + "-" + recurStack[src] + " " +next + "-" + recurStack[next]);
            if (detectCycle(next, visited, recurStack)) {
                return true;
            }
        }
        recurStack[src] = false;
        return false;
    }

    public boolean isCycle() {
        boolean[] visited = new boolean[v];
        boolean[] recurStack = new boolean[v];
        // checking the loop starting from every vertex
        for (int i = 0; i < v; i++) {
            if(detectCycle(i, visited, recurStack)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DetectCycle graph = new DetectCycle(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 0);
//        graph.addEdge(3, 3);
        boolean cycleDetection = graph.isCycle();
        System.out.println(cycleDetection);
    }
}
