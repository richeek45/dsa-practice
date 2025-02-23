package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static void breadthFirstSearch(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.data);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.left = new Node(5);
        root.right.left = new Node(7);
        root.left.right = new Node(6);

        breadthFirstSearch(root);
    }
}

class Node {
    int data;
    Node left;
    Node right;
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
}