package BinaryTree;

public class DFS {
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

    static boolean depthFirstSearch(Node root, int val) {
        if (root == null) return false;

        if (root.data == val) return true;
        boolean leftData = depthFirstSearch(root.left, val);
        boolean rightData = depthFirstSearch(root.right, val);

        return leftData || rightData;
    }


    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.left = new Node(5);
        root.left.right = new Node(6);

        System.out.println(depthFirstSearch(root, 51));
    }
}
