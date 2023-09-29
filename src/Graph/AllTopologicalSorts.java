package Graph;

import java.util.ArrayList;
import java.util.List;

public class AllTopologicalSorts {
    private int v;
    List<List<Integer>> adj;

    AllTopologicalSorts(int vertex) {
        v = vertex;
        adj = new ArrayList<>();
        for(int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int v1, int v2) {
        adj.get(v1).add(v2);
    }

    private void allTopologicalSortUtil(int[] inDegree, boolean[] visited, ArrayList<Integer> stack) {
        boolean flag = false;

        for(int i = 0; i < v; i++) {
            if(!visited[i] && inDegree[i] == 0) {
                stack.add(i);
                visited[i] = true;
                for(int vertex : adj.get(i)) {
                    inDegree[vertex]--;
                }
                allTopologicalSortUtil(inDegree, visited, stack);
                visited[i] = false;
                for(int vertex : adj.get(i)) {
                    inDegree[vertex]++;
                }
                stack.remove(stack.size()-1);
                flag = true;
            }

        }

        if (!flag) {
            stack.forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }

    public void allTopologicalSorts() {
        int[] inDegree = new int[v];
        boolean[] visited = new boolean[v];
        ArrayList<Integer> stack = new ArrayList<>();

        // Calculating the inDegree of the vertices
        for(int i = 0; i < v; i++) {
            for(int vertex : adj.get(i)) {
                inDegree[vertex]++;
            }
        }

        allTopologicalSortUtil(inDegree, visited, stack);
    }

    public static void main(String[] args) {
     //   5 -> 0 <- 4
     //   |         |
     //   2 -> 1 -> 1
        int vertex = 6;
        AllTopologicalSorts graph = new AllTopologicalSorts(6);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.allTopologicalSorts();

    }
}
