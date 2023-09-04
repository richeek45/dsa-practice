package BinaryTree;

public class BalancedTree {
    static class Pair {
        boolean isBalanced;
        int height;
        Pair(boolean val, int h) {
            isBalanced = val;
            height = h;
        }
    }

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
        // O(N)
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

    static Pair isBalancedTree3(Node root) {
        // Using a Pair to store a boolean if balanced and second parameters as a height
        if (root == null) {
            Pair p = new Pair(true, 0);
            return p;
        }

        Pair left = isBalancedTree3(root.left);
        Pair right = isBalancedTree3(root.right);

        boolean leftAns = left.isBalanced;
        boolean rightAns = right.isBalanced;

        boolean checkBalanced = Math.abs(left.height - right.height) <= 1;

        // initializing ans with false and height
        Pair ans = new Pair(false, Math.max(left.height, right.height) + 1);

        if (leftAns && rightAns && checkBalanced) {
            ans = new Pair(true, Math.max(left.height, right.height) + 1);
        }

        return ans;
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
        boolean isBalanced2 = isBalancedTree3(root).isBalanced;
        System.out.println(isBalanced2);
    }
}
