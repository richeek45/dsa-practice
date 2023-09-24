package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JobCompletion {
    // Given a Directed Acyclic Graph having V vertices and E edges, where each edge {U, V} represents the Jobs U and V
    // such that Job V can only be started only after completion of Job U.
    // The task is to determine the minimum time taken by each job to be completed
    // where each Job takes unit time to get completed.
    private int v;
    private List<List<Integer>> adj;

    JobCompletion(int vertex) {
        v = vertex;
        adj = new ArrayList<>();
        for(int i = 0; i < v+1; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int vertex1, int vertex2) {
        adj.get(vertex1).add(vertex2);
    }

    public void minimumJobCompletionTime() {
        int[] jobs = new int[v+1];
        int[] inDegree = new int[v+1];
        for(int i = 0; i < v; i++) {
            List<Integer> vertexList = adj.get(i);
            for(int vertex : vertexList) {
                inDegree[vertex]++;
            }
        }


        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= v; i++) {
            if(inDegree[i] == 0) {
                jobs[i] = 1;
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int currVertex = q.poll();
            List<Integer> vertexList = adj.get(currVertex);
            for(int adjVertex : vertexList) {
                inDegree[adjVertex]--;
                q.add(adjVertex);
                if (jobs[currVertex] + 1 > jobs[adjVertex]) {
                    jobs[adjVertex] = jobs[currVertex] + 1;
                }
            }
        }

        for(int i = 1; i <= v; i++) {
//            System.out.print(i + "->" + jobs[i] + ", ");
            System.out.print(jobs[i] + " ");
        }


    }


    public static void main(String[] args) {
        // 1 -> 2, 3, 4; 2 -> 3, 8, 9; 3 -> 6; 4 -> 6, 8; 5 -> 8; 6 -> 7; 7 -> 8; 8 -> 10;
        int vertex = 10;
        JobCompletion graph = new JobCompletion(vertex);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 8);
        graph.addEdge(2, 9);
        graph.addEdge(3, 6);
        graph.addEdge(4, 6);
        graph.addEdge(4, 8);
        graph.addEdge(5, 8);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 10);

        graph.minimumJobCompletionTime();

    }
}
