package BinaryTree;

public class MorrisTraversal {
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

    static void treeTraversal(Node root) {
        // Using Morris Traversal Algorithm
        // in-order traversal of nodes
        // Initialize the current node as the root of the binary tree.
        // While the current node is not NULL, do the following:
        // a. If the left child of the current node is NULL, move to the right child.
        // b. If the left child of the current node is not NULL, find the rightmost node in the left subtree of the current node.
        // c. If the right child of the rightmost node is NULL, point the right child to the current node, and move to the left child of the current node.
        // d. If the right child of the rightmost node is not NULL, point the right child back to NULL, visit the current node, and move to its right child.
        // e. For each visited node, calculate its left and right subtree heights using the maximum function
        // and update the diameter as the maximum of the sum of their heights and the current diameter.
        // Return the diameter of the binary tree.

        Node curr = root;
        while(curr != null) {
            if (curr.left == null) {
                System.out.print(curr.data + " ");
                curr = curr.right;
                if (curr != null) {
                    System.out.println(curr.data + "data");
                }
            } else {
                Node pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    System.out.print(curr.data + " ");
                    pre.right = null;
                    curr = curr.right;
                }
            }
        }
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        treeTraversal(root);
    }
}
