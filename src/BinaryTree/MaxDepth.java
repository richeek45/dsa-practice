package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth {
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

    static int getDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSubtree = getDepth(root.left);
        int rightSubtree = getDepth(root.right);

        return Math.max(leftSubtree, rightSubtree) + 1;
    }

    static int getDepth2(Node root) {
        // Performing level order traversal and adding nodes to the queue
        // adding a null after every level
        // while popping from the queue if node=null is encountered then increment the depth
        int depth = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null); // null is added after every level

        while(!q.isEmpty()) {
            Node temp = q.poll();
            if (temp == null) {
                // a new level is ended in the queue
                depth++;
            }

            if (temp != null) {
                // if the popped node is not null then add left and right subtree to the queue
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            } else if (!q.isEmpty()) {
                // add null for ending a level is elements are present in the tree
                q.add(null);
            }
        }

        return depth;
    }

    static int getDepth3(Node root) {
        // using level order traversal adding nodes to the queue
        Queue<Node> q = new LinkedList<>();
        int depth = 0;
        q.add(root);
        while(!q.isEmpty()) {
            for (int i = 0; i < q.size(); i++) {
                Node temp = q.poll();
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            depth++; // increment the depth after every level
        }

        return depth;
    }


    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        int depth = getDepth3(tree);
        System.out.println(depth);
    }
}
