package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetectCycleUnDirected {
    private int v;
    private List<List<Integer>> adj;

    DetectCycleUnDirected(int vertex) {
        v = vertex;
        adj = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new LinkedList<Integer>());
        }
    }

    public void addEdge(int src, int dest) {
        adj.get(src).add(dest);
        adj.get(dest).add(src);
    }

    private boolean isCycle(int src, boolean[] visited) {
        // Breadth First Search
        int[] parent = new int[v];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(parent, -1);
        queue.add(src);
        visited[src] = true;

        while(!queue.isEmpty()) {
            int top = queue.poll();
            List<Integer> adjVertices = adj.get(top);
            for (Integer adjVertex : adjVertices) {
                // if vertex is not visited, make visited = true, and make the vertex child of top vertex
                if (!visited[adjVertex]) {
                    visited[adjVertex] = true;
                    parent[adjVertex] = top;
                    queue.add(adjVertex);
                } else if (parent[adjVertex] != top) {
                    // if vertex is visited but it is not the child of top vertex from the queue
                    // then it means the vertex is visited and made child by other parent vertex before
                    // indicating presence of a cycle
                    return true;
                }
            }
        }
        return false;
    }

    public void detectCycleBFS() {
        boolean[] visited = new boolean[v];
        Arrays.fill(visited, false);

        for (int i = 0; i < v; i++) {
            if (!visited[i] && isCycle(i, visited)) {
                System.out.println(true + " " + i);
                return;
            }
        }

        System.out.println(false);
    }

    private boolean isCycle2(int src, int parent, boolean[] visited) {
        visited[src] = true;
        List<Integer> list = adj.get(src);
        for (Integer vertex : list) {
            if (!visited[vertex]) {
                if (isCycle2(vertex, src, visited)) {
                    return true;
                }
            } else if (vertex != parent) {
                // src has adjacent vertices, one of them is parent since this is undirected graph
                // if there are other vertices which are already visited and not same as parent
                // indicates there is a loop
                return true;
            }
        }
        return false;
    }

    public void detectCycleDFS() {
        boolean[] visited = new boolean[v];
        Arrays.fill(visited, false);
        for (int i = 0; i < v; i++) {
            if (!visited[i] && isCycle2(i, -1, visited)) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }


    public static void main(String[] args) {
        DetectCycleUnDirected graph = new DetectCycleUnDirected(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.detectCycleBFS();
    }
}
