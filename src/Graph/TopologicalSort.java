package Graph;

import java.util.*;

public class TopologicalSort {
    // Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices
    // such that for every directed edge u v, vertex u comes before v in the ordering.
    // 1. Identify a node with no incoming edges.
    // 2. Add that node to the ordering.
    // 3. Remove it from the graph.
    // 4. Repeat.
    private int v;
    private List<List<Integer>> adj;
    TopologicalSort(int vertex) {
        v = vertex;
        adj = new ArrayList<>();
        for(int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void topologicalSort2() {
        // solving using Kahn's algorithm
        // Reference: https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
        int[] inDegree = new int[v];

        for(int i = 0; i < v; i++) {
            for(int adjVertex : adj.get(i)) {
                inDegree[adjVertex]++;
            }
        }

        // adding inDegree of 0 in the queue
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < v; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;
        Vector<Integer> topOrder = new Vector<>();
        while(!q.isEmpty()) {
            int curr = q.poll();
            topOrder.add(curr);
            for(int adjVertex : adj.get(curr)) {
                if(--inDegree[adjVertex] == 0) {
                    q.add(adjVertex);
                }
            }
            count++;
        }

        if(count != v) {
            System.out.println("There exists a cycle in the graph!");
            return;
        }

        for(int i : topOrder) {
            System.out.print(i + " ");
        }
    }

    public void addEdge(int vertex1, int vertex2) {
        adj.get(vertex1).add(vertex2);
    }

    public void topologicalSortUtil(int vertex, Stack<Integer> stack, boolean[] visited) {

        visited[vertex] = true;

        Iterator<Integer> itr = adj.get(vertex).iterator();
        while(itr.hasNext()) {
            int adjVertex = itr.next();
            if(!visited[adjVertex]) {
                topologicalSortUtil(adjVertex, stack, visited);
            }
        }

        stack.push(vertex);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        Arrays.fill(visited, false);

        for(int i = 0; i < v; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, stack, visited);
            }
        }
        while(!stack.isEmpty()) {
            int currVertex = stack.pop();
            System.out.print(currVertex + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int vertex = 6;
        TopologicalSort graph = new TopologicalSort(vertex);
        // 5 4 2 3 1 0
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.topologicalSort();
        graph.topologicalSort2();

    }
}
