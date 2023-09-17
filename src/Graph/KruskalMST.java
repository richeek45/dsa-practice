package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KruskalMST {
    static class DisjointSet {
        int[] parent;
        int[] rank;
        int[] size;
        DisjointSet(int v) {
            parent = new int[v];
            rank = new int[v];
            size = new int[v];

            for(int i = 0; i < v; i++) {
                parent[i] = i;
                size[i] = 1;
                rank[i] = 0;
            }
        }

        public int findParent(int node) {
            if (parent[node] == node) {
                return node;
            }
            return parent[node] = findParent(parent[node]);
        }

        public void unionByRank(int node1, int node2) {
            int parent1 = findParent(node1);
            int parent2 = findParent(node2);
            if (parent1 == parent2) {
                return;
            }
            if (rank[node1] < rank[node2]) {
                parent[node1] = node2;
            } else if (rank[node2] < rank[node1]) {
                parent[node2] = node1;
            } else {
                parent[node1] = node2;
                rank[node2]++;
            }
        }

        public void unionBySize(int node1, int node2) {
            int parent1 = findParent(node1);
            int parent2 = findParent(node2);
            if (parent1 == parent2) {
                return;
            }
            if (size[node1] < size[node2]) {
                parent[node1] = node2;
                size[node2] += size[node1];
            } else if (size[node2] < size[node1]) {
                parent[node2] = node1;
                size[node1] += size[node2];
            } else {
                parent[node1] = node2;
                size[node2] += size[node1];
            }
        }
    }

    static class Edge {
        int src;
        int dest;
        int weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static void kruskal(int vertex, List<Edge> graphEdges) {
        // add a edge with source to dest by union method
        // if both nodes have same parent means the edge will create a cycle, hence do not include it.
        int edgeIndex = 0;
        // storing the edges in the final graph that doesn't form a cycle
        Edge[] results = new Edge[vertex];
        // no of edges in a graph with no cycle is vertex - 1, so edgeIndex should not exceed that.
        DisjointSet ds = new DisjointSet(vertex);

        int j = 0;
        while(edgeIndex < vertex - 1) {
            Edge edge = graphEdges.get(j);
            int node1 = edge.src;
            int node2 = edge.dest;
            int parent1 = ds.findParent(node1);
            int parent2 = ds.findParent(node2);

//          same parents indicates a cycle if edge is created hence avoiding that
            if (parent1 != parent2) {
                // union two nodes of an edge
                results[edgeIndex] = edge;
                ds.unionByRank(node1, node2);
                edgeIndex++;
            }
            j++;
        }

        // printing the result of the edges with minimum cost
        int minCost = 0;
        for(int i = 0; i < edgeIndex; i++) {
            int src = results[i].src;
            int dest = results[i].dest;
            int weight = results[i].weight;
            System.out.println(src + " -- " + dest + " == " + weight);
            minCost += weight;
        }
        System.out.println("minimum cost = " + minCost);
    }

    public static void main(String[] args) {
        int vertex = 4; // number of vertex
        List<Edge> graphEdges = new ArrayList<Edge>(Arrays.asList(
                new Edge(0, 1, 10), new Edge(0, 2, 6),
                new Edge(0, 3, 5), new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        ));

        graphEdges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                return edge1.weight - edge2.weight;
            }
        });

        kruskal(vertex, graphEdges);
    }
}