package Graph;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class WiredConnections {
    static class DisjointSet {
        int[] parent;
        int[] size;
        int[] rank;

        // n -> number of nodes
        DisjointSet(int n) {
            parent = new int[n+1];
            size = new int[n+1];
            rank = new int[n+1];

            for(int i = 0; i < n; i++) {
                parent[i] = i; // Make the node parent of itself
                size[i] = 1; //  give size = 1 for each node
            }
        }

        public int findParent(int node) {
            if (parent[node] == node) {
                return node;
            }
            // Path compression technique:
            // if parent of node is not found in the first try,
            // find the parent node by recursively  calling the function
            // until we find the node which is parent of itself
            // and assigning the found parent to the  initial node
            return parent[node] = findParent(parent[node]);
        }

        public void unionByRank(int node1, int node2) {
            int parent1 = findParent(node1);
            int parent2 = findParent(node2);

            // node1 and node2 are the parent of the same tree
            if (parent1 == parent2) {
                return;
            }
            // Make node with higher rank as the parent
            if (rank[node1] < rank[node2]) {
                parent[node1] = node2;
            } else if (rank[node2] < rank[node1]) {
                parent[node2] = node1;
            } else {
                // if both the nodes have the same rank,
                // here we decided to make node1 parent of node2
                // hence we need to increase the rank of parent i.e. node2
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
                // if size is same for both node1 and node2
                // make any one of them as a parent
                parent[node2] = node1;
                size[node1] += size[node2];
            }
        }
    }

    // Given an integer N, denoting the number of computers connected by cables forming a network
    // and a 2D array connections[][], with each row (i, j) representing a connection between ith and jth computer,
    // the task is to connect all the computers either directly or indirectly by removing any of the given connections
    // and connecting two disconnected computers If it’s not possible to connect all the computers, print -1.
    // Otherwise, print the minimum number of such operations required.
    // Input: N = 4, connections[][] = {{0, 1}, {0, 2}, {1, 2}}, Output: 1
    // Explanation: Remove the connection between computers 1 and 2 and connect the computers 1 and 3.
    // Input: N = 5, connections[][] = {{0, 1}, {0, 2}, {3, 4}, {2, 3}}, Output: 0
    public ArrayList<Integer>[] initializeVertexList(int N) {
        ArrayList<Integer>[] adj= new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        return adj;
    }

    public void addEdges(ArrayList<int[]> connections, ArrayList<Integer>[] vertex) {
        for (int i = 0; i < connections.size(); i++) {
            int src = connections.get(i)[0];
            int dest = connections.get(i)[1];
            vertex[src].add(dest);
            vertex[dest].add(src);
        }
    }

    public int findExtraConnections(DisjointSet disjointSet, ArrayList<int[]> connections) {
        // find the redundant connections in the union which is not required if already connected to each other
        int extraConnections = 0;
        for(int i = 0; i < connections.size(); i++) {
            int node1 = connections.get(i)[0];
            int node2 = connections.get(i)[1];
            int parent1 = disjointSet.findParent(node1);
            int parent2 = disjointSet.findParent(node2);
            // if both the nodes are connected to a same parent then the union of these nodes are not necessary
            // or it will create a cycle
            if (parent1 == parent2) {
                extraConnections++;
            }
            disjointSet.unionBySize(node1, node2);
        }
        return extraConnections;
    }

    public void dfs(int start, ArrayList<Integer>[] vertices, boolean[] visited) {
        if(visited[start]) {
            return;
        }

        visited[start] = true;
        // searching for the adjacent vertices present in the start vertex
        for (Integer adjVertex : vertices[start]) {
            dfs(adjVertex, vertices, visited);
        }
    }

    public static void main(String[] args) {
        int N = 6; // total number of computers(nodes) present
        // list of connections present -> ArrayList of arrays(each connection)
        ArrayList<int[]> connections = new ArrayList<int[]>(Arrays.asList(
                new int[] {0, 1}, new int[] {0, 2}, new int[]{0, 3},
                new int[] {1, 2}, new int[] {1, 3}
        ));
        WiredConnections graph = new WiredConnections();
        // creating an array of arraylist for every node to store the adjacent nodes
        ArrayList<Integer>[] vertices = graph.initializeVertexList(N);
        graph.addEdges(connections, vertices);

        DisjointSet ds = new DisjointSet(N);

        // we need to find the extra nodes that are not connected to an edge
        int extraConnections = graph.findExtraConnections(ds, connections);
        // depth first search traversal through the graph
        boolean[] visited = new boolean[N];
        graph.dfs(0, vertices, visited);
        int disconnectedNodes = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                disconnectedNodes++;
                graph.dfs(i, vertices, visited);
            }
        }

        System.out.println(extraConnections);
        if (disconnectedNodes <= extraConnections) {
            System.out.println(disconnectedNodes);
        } else {
            // Number of disconnected nodes is more than extraConnections,
            // connection of every disconnected node is not possible
            System.out.println(-1);
        }

    }
}

