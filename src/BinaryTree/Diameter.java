package BinaryTree;

public class Diameter {
    // The diameter/width of a tree is defined as the number of nodes on the longest path between two end nodes.
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

    static class Height {
        int h;
    }

    static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    static int getDiameter(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        int leftDiameter = getDiameter(root.left);
        int rightDiameter = getDiameter(root.right);

        // finding the height of the left node and right node and adding +1 for the root node
        // throught which the diameter is going to be formed
        return Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));

    }

    static int getDiameter2(Node root, Height height) {
        // optimzed version of first method without calculating height of nodes
        Height leftHeight = new Height();
        Height rightHeight = new Height();
        if (root == null) {
            height.h = 0;
            return 0;
        }

        int leftDiameter = getDiameter2(root.left, leftHeight);
        int rightDiameter = getDiameter2(root.right, rightHeight);
        height.h = Math.max(leftHeight.h, rightHeight.h) + 1;

        return Math.max(leftHeight.h + rightHeight.h + 1, Math.max(leftDiameter, rightDiameter));
    }

    public static void main(String[] args) {

        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        int diameter = getDiameter2(tree, new Height());
        System.out.println(diameter);

    }
}
