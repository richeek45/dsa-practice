package BinaryTree;

public class BalancedTree {
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

    private static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    static boolean isBalancedTree(Node root) {
        // O(N^2) -> height recursion and isbalanced recursion
        int leftHeight;
        int rightHeight;

        if (root == null) {
            return true;
        }

        leftHeight = getHeight(root.left);
        rightHeight = getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalancedTree(root.left) && isBalancedTree(root.right)) {
            return true;
        }

        return false;
    }

    static int isBalancedTree2(Node root) {
        // calculating height on the same recursion tree
        if (root == null) {
            return 0;
        }

        int leftHeight = isBalancedTree2(root.left);
        int rightHeight = isBalancedTree2(root.right);

        if (Math.abs(leftHeight - rightHeight) <= 1) {
            return Math.max(leftHeight, rightHeight) + 1;
        }

        return -1;
    }


    public static void main(String[] args) {
        // A height balanced binary tree is a binary tree in which the height of the
        // left subtree and right subtree of any node does not differ by more than 1
        // and both the left and right subtree are also height balanced.
        // 1. height balanced tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);

        // 2. Height not balanced tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.left.right = new Node(5);

        boolean isBalanced = isBalancedTree2(root) != -1 ? true : false;
        System.out.println(isBalanced);
    }
}
