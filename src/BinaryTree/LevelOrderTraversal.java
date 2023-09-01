package BinaryTree;

public class LevelOrderTraversal {
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

    static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static void printCurrentLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.data + " ");
            return;
        }
        printCurrentLevel(root.left, level-1);
        printCurrentLevel(root.right, level-1);
    }

    static void printLevelOrderTraversal(Node root) {
        int height = getHeight(root);

        for (int i = 1; i <= height; i++) {
            printCurrentLevel(root, i);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5};
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);

        printLevelOrderTraversal(tree);


    }
}
