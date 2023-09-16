package Graph;

import java.util.*;

public class CloneGraph2 {
    // A Graph contains list of all the nodes associated with it
    class Graph {
        private List<GraphNode> nodes;

        public Graph() {
            this.nodes = new ArrayList<GraphNode>();
        }

        public Graph(List<GraphNode> nodes) {
            this.nodes = nodes;
            this.nodes = new ArrayList<GraphNode>();
        }

        public void addNode(GraphNode node) {
            this.nodes.add(node);
        }

        public List<GraphNode> getNodes() {
            return this.nodes;
        }
    }

    // A GraphNode has the node and the list of the adjacent nodes associated with it
    class GraphNode {
        private int data;
        private List<GraphNode> children;
        GraphNode(int data) {
            this.data = data;
            this.children = new ArrayList<GraphNode>();
        }

        public void addChild(GraphNode node) {
            this.children.add(node);
        }

        public List<GraphNode> getChildren() {
            return children;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getData() {
            return this.data;
        }
    }

    public Graph buildGraph() {
        Graph g = new Graph();
        // Creating a new graphNodes and adding to the graph list
        GraphNode g1 = new GraphNode(1);
        GraphNode g2 = new GraphNode(2);
        GraphNode g3 = new GraphNode(3);
        GraphNode g4 = new GraphNode(4);
        GraphNode g5 = new GraphNode(5);
        GraphNode g6 = new GraphNode(6);
        g.addNode(g1);
        g.addNode(g2);
        g.addNode(g3);
        g.addNode(g4);
        g.addNode(g5);
        g.addNode(g6);

        // adding children to the corresponding graphNodes
        g1.addChild(g2);
        g1.addChild(g3);
        g2.addChild(g1);
        g2.addChild(g4);
        g3.addChild(g1);
        g3.addChild(g4);
        g4.addChild(g2);
        g4.addChild(g3);
        g5.addChild(g6);
        g6.addChild(g5);

        return g;
    }

    public void printConnectedGraph(GraphNode node, Set<GraphNode> visited) {
        // if node is already visited
        if (visited.contains(node)) {
            return;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            GraphNode topNode = queue.poll();
            if (visited.contains(topNode)) {
                continue;
            }
            visited.add(topNode);
            // prints the graph node and prints the nodes adjacent to it
            System.out.print(topNode.data + " ");
            for(GraphNode adjNode: topNode.getChildren()) {
                System.out.print("Child=>" + adjNode.getData() + ", ");
                queue.add(adjNode);
            }
            System.out.println();
        }
    }

    public Graph cloneGraph(Graph graph) {
        Map<GraphNode, GraphNode> hashMap = new HashMap<>();
        // 1. For each node in the graph clone all the connected nodes of all the nodes
        for (GraphNode node : graph.getNodes()) {
            if (!hashMap.containsKey(node)) {
                cloneConnectedNode(node, hashMap);
            }
        }

        // 2. add all the connected nodes to the graph
        Graph cloned = new Graph();
        for(GraphNode node : hashMap.values()) {
            cloned.addNode(node);
        }

        return cloned;
    }

    public void cloneConnectedNode(GraphNode node, Map<GraphNode, GraphNode> map) {
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            GraphNode currNode = queue.poll();
            GraphNode clonedNode = null; // initializing cloned node
            if (map.containsKey(currNode)) {
                clonedNode = map.get(currNode);
            } else {
                // create a new clone graphnode with the current node popped from the queue
                clonedNode = new GraphNode(currNode.getData());
                map.put(currNode, clonedNode);
            }
            // For every child of the graphNode create a new graphnode and add it to the cloned node
            for(GraphNode child : currNode.getChildren()) {
                if (map.containsKey(child)) {
                    clonedNode.addChild(map.get(child));
                } else {
                    GraphNode childCloneNode = new GraphNode(child.getData());
                    map.put(child, childCloneNode);
                    clonedNode.addChild(childCloneNode);
                    queue.add(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        CloneGraph2 g = new CloneGraph2();
        Graph graph = g.buildGraph();

        System.out.println("INITIAL GRAPH:");
        Set<GraphNode> visited = new HashSet<>();
        for (GraphNode node : graph.getNodes()) {
            g.printConnectedGraph(node, visited);
        }

        System.out.println();
        System.out.println("CLONED GRAPH:");
        Graph clonedGraph = g.cloneGraph(graph);
        visited = new HashSet<>();
        for (GraphNode node : clonedGraph.getNodes()) {
            g.printConnectedGraph(node, visited);
        }
    }
}