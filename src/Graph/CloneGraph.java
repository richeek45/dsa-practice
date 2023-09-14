package Graph;

import java.util.*;

public class CloneGraph {
    public GraphNode buildGraph () {
        // 1 - 2
        // |   |
        // 3 - 4
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        Vector<GraphNode> v = new Vector<GraphNode>();
        // for node1 -> node2, node3 are neighbours
        v.add(node2);
        v.add(node3);
        node1.neighbours = v;
        // for node2 -> node 1, node4 are neighbours
        v = new Vector<GraphNode>();
        v.add(node1);
        v.add(node4);
        node2.neighbours = v;
        // for node3 -> node1, node4 are neighbours
        v = new Vector<GraphNode>();
        v.add(node1);
        v.add(node4);
        node3.neighbours = v;
        // for node4 ->
        v = new Vector<GraphNode>();
        v.add(node2);
        v.add(node3);
        node4.neighbours = v;
        return node1;
    }

    public void bfs(GraphNode source) {
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        HashMap<GraphNode, Boolean> visited = new HashMap<GraphNode, Boolean>();
        queue.add(source);
        visited.put(source, true); // adding source as visited

        while(!queue.isEmpty()) {
            GraphNode top = queue.poll();
            System.out.print(top.val + " ");

            if (top.neighbours != null) {
                Vector<GraphNode> neighbours = top.neighbours;
                for (GraphNode node : neighbours) {
                    if (visited.get(node) == null) {
                        queue.add(node);
                        visited.put(node, true);
                    }
                }
            }
        }
        System.out.println();
    }

    public GraphNode cloneGraph(GraphNode source) {
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(source);
        // each original node is mapped to its corresponding cloned node
        HashMap<GraphNode, GraphNode> hashmap = new HashMap<>();
        // map source node with a new node with source val
        hashmap.put(source, new GraphNode(source.val));

        while(!queue.isEmpty()) {
            GraphNode topNode = queue.poll();
            Vector<GraphNode> neighbours = topNode.neighbours;
            GraphNode clonedNode = hashmap.get(topNode); // getting the cloned node
            if (neighbours != null) {
                for (GraphNode nodeAdj : neighbours) {
                    // getting the adjacent cloned node from the hashmap if present
                    GraphNode clonedNodeAdj = hashmap.get(nodeAdj);
                    if (clonedNodeAdj == null) {
                        queue.add(nodeAdj);
                        clonedNodeAdj = new GraphNode(nodeAdj.val);
                        hashmap.put(nodeAdj, clonedNodeAdj);
                    }
                    clonedNode.neighbours.add(clonedNodeAdj);
                }
            }
        }

        return hashmap.get(source);

    }

    public static void main(String[] args) {
        CloneGraph graph = new CloneGraph();
        GraphNode source = graph.buildGraph();
        graph.bfs(source);
        source = graph.cloneGraph(source);
        graph.bfs(source);
    }

}

class GraphNode {
    int val;
    Vector<GraphNode> neighbours;
    GraphNode(int val) {
        this.val = val;
        neighbours = new Vector<GraphNode>();
    }
}
