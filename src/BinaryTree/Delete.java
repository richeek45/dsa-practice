package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class Delete {
    static class Node {
        int data;
        Node left;
        Node right;
        Node(int val) {
            data = val;
            left = null;
            right = null;
        }
    }

    static Node delete(Node root, int val) {
        if (root == null) return null;

        // Finding the target that needs to be deleted
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node target = null;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.data == val) {
                target = curr;
                break;
            }
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        if (target == null) return root;

        // replacing the target node data with the last node and making the last node null.
        Node lastNode = null;
        Node lastParent = null;
        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> parentQueue = new LinkedList<>();
        q1.add(root);
        parentQueue.add(null);

        while (!q1.isEmpty()) {
            Node curr = q1.poll();
            Node parent = parentQueue.poll();

            lastNode = curr;
            lastParent = parent;

            if (curr.left != null) {
                q1.add(curr.left);
                parentQueue.add(curr);
            }
            if (curr.right != null) {
                q1.add(curr.right);
                parentQueue.add(curr);
            }
        }

        target.data = lastNode.data;
        if (lastParent != null) {
            if (lastParent.left == lastNode) {
                lastParent.left = null;
            } else {
                lastParent.right = null;
            }
        } else {
            return null;
        }

        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.left = new Node(5);
        root.left.right = new Node(6);

        root = delete(root, 4);
        System.out.println(root.right.data);
    }
}
