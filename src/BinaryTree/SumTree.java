package BinaryTree;

public class SumTree {
    // Given a Binary Tree where each node has positive and negative values. Convert this to a tree where each node
    // contains the sum of the left and right sub trees in the original tree. The values of leaf nodes are changed to 0.
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

    static void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.data + " ");
            printInOrder(root.right);
        }
    }

    static int sumTree(Node root) {
        if (root == null) {
            return 0;
        }

        int oldValue = root.data;
        root.data = sumTree(root.left) + sumTree(root.right);
        return oldValue + root.data;
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(-2);
        root.right = new Node(6);
        root.left.left = new Node(8);
        root.left.right = new Node(-4);
        root.right.left = new Node(7);
        root.right.right = new Node(5);

        int val = sumTree(root);
//        System.out.println(val);
        printInOrder(root);
    }
}
