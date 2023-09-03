package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class LeftViewTree {
    static int maxLevel = 0;

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

    static void leftView(Node root, int level) {
        // Traverse recursively and if level of the encountered node is more than maxLevel than print
        // and make the maxLevel = level
        if (root == null) {
            return;
        }

        if (maxLevel < level) {
            System.out.print(root.data + " ");
            maxLevel = level;
        }
        leftView(root.left, level + 1);
        leftView(root.right, level + 1);

    }


    static void leftView2(Node root, int level) {
        // using iterative level order traversal using queue
        // and printing the first node of the level
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();

            for (int i = 1; i <= size; i++) {
                Node temp = q.poll();

                if (i == 1) {
                    System.out.print(temp.data + " ");
                }

                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }

        }
    }


    public static void main(String[] args) {
//        Node root = new Node(4);
//        root.left = new Node(5);
//        root.right = new Node(2);
//        root.right.left = new Node(3);
//        root.right.right = new Node(1);
//        root.right.left.left = new Node(6);
//        root.right.left.right = new Node(7);
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
        root.left.right.right.right = new Node(6);
        leftView2(root, 1);
    }
}
