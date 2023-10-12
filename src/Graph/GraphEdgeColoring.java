package Graph;

import java.util.*;

public class GraphEdgeColoring {
    public static class GraphNode {
        int vertex;
        int edge;
        GraphNode(int v, int e) {
            vertex = v;
            edge = e;
        }
    }

    public static void addEdge(int v1, int v2, int edge, ArrayList<ArrayList<GraphNode>> graph) {
        graph.get(v1).add(new GraphNode(v2, edge));
        graph.get(v2).add(new GraphNode(v1, edge));
    }

    public static void colorEdges(int vertex, ArrayList<Integer> edgeColors, boolean[] visited, ArrayList<ArrayList<GraphNode>> graph) {
        int c = 0; // for assigning colors
        Set<Integer> colored =  new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        if (visited[vertex]) {
            return;
        }

        visited[vertex] = true;

        for(int i = 0; i < graph.get(vertex).size(); i++) {
            int edge = graph.get(vertex).get(i).edge;
            // if the edge adjacent to the graphNode is already colored, add the colors to the colored set
            int edgeColor = edgeColors.get(edge);
            if (edgeColor != -1) {
                colored.add(edgeColor);
            }
        }

        for(int i = 0; i < graph.get(vertex).size(); i++) {
            int edge = graph.get(vertex).get(i).edge;
            int ver = graph.get(vertex).get(i).vertex;
            if (!visited[edge]) {
                q.add(ver);
            }

            // if the edge is not colored
            if (edgeColors.get(edge) == -1) {
                while(colored.contains(c)) {
                    c++;
                }
                // Adding a new color
                edgeColors.set(edge, c);
                colored.add(c);
                c++;
            }
        }

        while(!q.isEmpty()){
            int tempVertex = q.poll();
            colorEdges(tempVertex, edgeColors, visited, graph);
        }

    }


    public static void main(String[] args) {
        int vertex = 4;
        int edge = 4;
        ArrayList<ArrayList<GraphNode>> graph = new ArrayList<>(vertex);
        for(int i = 0; i < vertex; i++) {
            graph.add(new ArrayList<>());
        }

        ArrayList<Integer> edgeColors = new ArrayList<>(Collections.nCopies(edge, -1));
        boolean[] isVisited = new boolean[100000];

        addEdge(0, 1, 0, graph);
        addEdge(1, 2, 1, graph);
        addEdge(2, 3, 2, graph);
        addEdge(0, 3, 3, graph);

        colorEdges(0, edgeColors, isVisited, graph);

        for(int i = 0; i < edge; i++) {
            System.out.println("Edge " + (i+1) + " is of color " + (edgeColors.get(i) + 1));
        }


    }
}
