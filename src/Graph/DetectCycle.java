package Graph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DetectCycle {
    // Given the root of a Directed graph, The task is to check whether the graph contains a cycle or not.
    private static int v;
    private LinkedList<Integer> adj[];
    private int WHITE = 0, GREY = 1, BLACK = 2; // assigning numbers for indicating each color

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

    public boolean isCycle2() {
        // Using Kahn's Algorithm
        // Kahn’s algorithm is a well-known approach for topological sorting.
        // The algorithm is explained in detail below:
        // Calculate the in-degree (number of incoming edges)
        // for each vertex in the DAG and set the starting value of the visited nodes count to 0.
        // Select all of the vertices with in-degree values of 0, then enqueue them all.
        // Add one more visited node to the count after removing a vertex from the queue (Dequeue operation).
        // For each of its neighbouring nodes or vertices, reduce in-degree by 1.
        // Add a node or vertex to the queue if the in-degree of any nearby nodes or vertices is lowered to zero.
        // Up until the queue is empty, repeat step 3.
        // The topological sort is not possible for the provided graph if the number of visited nodes is not equal to the number of graph vertices.
        int[] inDegree = new int[v];
        Queue<Integer> queue = new LinkedList<>();
        int visited = 0;

        // calculate the in-degree of each vertex
        // in-degree -> incoming edge of each vertex
        for (int i = 0; i < v; i++) {
            Iterator<Integer> itr = adj[i].listIterator();
            while(itr.hasNext()) {
                int val = itr.next();
                inDegree[val]++;
            }
        }

        // if in-degree of vertex is 0 i.e. no incoming edges then add it to the queue
        for(int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int top = queue.poll();
            System.out.println(top + "top");
            visited++;

            // while removing a vertex from the queue decrease the inDegree of the adjacent vertex by 1
            // meaning removing the edge from the adjacent vertices
            Iterator<Integer> itr = adj[top].listIterator();
            while(itr.hasNext()) {
                int adjVertex = itr.next();
                inDegree[adjVertex]--;
                // if no edges are incoming add it to the queue
                if (inDegree[adjVertex] == 0) {
                    queue.add(adjVertex);
                }
            }
        }

        for(int i = 0; i < v; i++) {
            System.out.print(inDegree[i] + " ");
        }
        // if not all vertices are visited then there is a cycle
        return visited != v;
    }

    private boolean detectCycle2(int src, int[] color) {
        // WHITE : Vertex is not processed yet. Initially, all vertices are WHITE.
        // GRAY: Vertex is being processed (DFS for this vertex has started, but not finished
        // which means that all descendants (in DFS tree) of this vertex are not processed yet
        // (or this vertex is in the function call stack)
        // BLACK : Vertex and all its descendants are processed.
        // While doing DFS, if an edge is encountered from current vertex to a GRAY vertex,
        // then this edge is back edge and hence there is a cycle.
        // Algorithm:
        // Create a recursive function that takes the edge and color array (this can be also kept as a global variable)
        // Mark the current node as GREY.
        // Traverse all the adjacent nodes and if any node is marked GREY then return true as a loop is bound to exist.
        // If any adjacent vertex is WHITE then call the recursive function for that node.
        // If the function returns true. Return true.
        // If no adjacent node is grey or has not returned true then mark the current Node as BLACK and return false.

        color[src] = GREY;

        Iterator<Integer> itr = adj[src].listIterator();
        while(itr.hasNext()) {
            int adjVertex = itr.next();
            // If the vertex is already GREY meaning already processing, hence we found a loop
            if (color[adjVertex] == GREY) {
                return true;
            }
            // If v is not already processed then
            if (color[adjVertex] == WHITE && detectCycle2(adjVertex, color) == true) {
                return true;
            }
        }
        // Mark this vertex as processed
        color[src] = BLACK;
        return false;
    }

    public boolean isCycle3() {
        // detecting cycle using color -> similar to 1st method
        int[] color = new int[v];
        // Initializing all the vertex to the color WHITE
        for (int i = 0; i < v; i++) {
            color[i] = WHITE;
        }

        for (int i = 0; i < v; i++) {
            if (color[i] == WHITE) {
                if (detectCycle2(i, color)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        DetectCycle graph = new DetectCycle(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 0);
//        graph.addEdge(3, 3);
        boolean cycleDetection = graph.isCycle2();
        System.out.println(cycleDetection);
    }
}
