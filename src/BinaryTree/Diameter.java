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

    static int getDiameter3(Node root) {
        // Using Morris Traversal Algorithm
        // Define a struct or class for a binary tree node that contains data, a pointer to its left child, and a pointer to its right child.
        // Initialize the current node as the root of the binary tree.
        // While the current node is not NULL, do the following:
        // a. If the left child of the current node is NULL, move to the right child.
        // b. If the left child of the current node is not NULL, find the rightmost node in the left subtree of the current node.
        // c. If the right child of the rightmost node is NULL, set its right child to the current node, and move to the left child of the current node.
        // d. If the right child of the rightmost node is not NULL, set its right child back to NULL, visit the current node, and move to its right child.
        // e. For each visited node, calculate its left and right subtree heights using the maximum function
        // and update the diameter as the maximum of the sum of their heights and the current diameter.
        // Return the diameter of the binary tree.


        Node curr = root;
        int ans = 0;

        if (curr != null) {
            if (curr.left == null) {
                curr = curr.right;
            } else {
                // left child is not null
                // find the rightmost node of the left-subtree
                Node pre = curr.left;
                while(pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    // pre.right is not null
                    pre.right = null;
                    int leftHeight = 0, rightHeight = 0;
                    Node temp = curr.left; // finding the height of the left-subtree and the righ-tsubtree
                    while (temp != null) {
                        leftHeight++;
                        temp = temp.right;
                    }
                    temp = curr.right;
                    while(temp != null) {
                        rightHeight++;
                        temp = temp.left;
                    }
                    ans = Math.max(ans, leftHeight + rightHeight + 1);
                    curr = curr.right;
                }
            }
        }

        return ans;
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
