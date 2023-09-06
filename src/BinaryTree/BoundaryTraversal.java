package BinaryTree;

import java.util.Stack;

public class BoundaryTraversal {
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

    private static void printBoundaryLeaves(Node root) {
        if (root == null) {
            return;
        }
        printBoundaryLeaves(root.left);
        if (root.left == null && root.right == null) {
            System.out.print(root.data + " ");
        }
        printBoundaryLeaves(root.right);
    }

    private static void printBoundaryLeft(Node root) {
        if (root == null) {
            return;
        }

        // to ensure top down order, print the node before calling itself for left subtree
        if (root.left != null) {
            System.out.print(root.data + " ");
            printBoundaryLeft(root.left);
        } else if (root.right != null) {
            System.out.print(root.data + " ");
            System.out.print(root.right);
        }
        // do nothing if it is a leaf node, this way we avoid duplicates in output
    }

    private static void printBoundaryRight(Node root) {
        if (root == null) {
            return;
        }

        // to ensure bottom up order, first call for right subtree, then print this node
        if (root.right != null) {
            printBoundaryRight(root.right);
            System.out.print(root.data + " ");
        } else if (root.left != null) {
            printBoundaryRight(root.left);
            System.out.print(root.data + " ");
        }
        // do nothing if it is a leaf node, this way we avoid duplicates in output
    }

    private static void printBoundaryLeft2(Node root) {
        if (root == null) {
            return;
        }

        while(root != null) {
            if (root.left != null) {
                System.out.print(root.data + " ");
                root = root.left;
            } else if (root.right != null) {
                System.out.print(root.data + " ");
                root = root.right;
            } else {
                return;
            }
        }
    }

    private static void printBoundaryRight2(Node root) {
        Stack<Integer> stack = new Stack<>();
        if (root == null) {
            return;
        }

        while(root != null) {
            if (root.right != null) {
                stack.push(root.data);
                root = root.right;
            } else if (root.left != null) {
                stack.push(root.data);
                root = root.left;
            } else {
                break;
            }
        }

        while(!stack.isEmpty()) {
            int top = stack.pop();
            System.out.print(top + " ");
        }
    }

    private static void printBoundaryLeaves2(Node root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.print(root.data + " ");
        }

        printBoundaryLeaves2(root.left);
        printBoundaryLeaves2(root.right);
    }

    static void printBoundaryTraversal2(Node root) {
        // iterative approach of printing boundary traversal
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        printBoundaryLeft2(root.left);
        printBoundaryLeaves2(root.left);
        printBoundaryLeaves2(root.right);
        printBoundaryRight2(root.right);
    }

    static void printBoundaryTraversal(Node root) {
        // Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        printBoundaryLeft(root.left);
        printBoundaryLeaves(root.left);
        printBoundaryLeaves(root.right);
        printBoundaryRight(root.right);
    }

    public static void main(String[] args) {
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(4);
        root.right.right = new Node(25);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);

        printBoundaryTraversal(root);
        System.out.println();
        printBoundaryTraversal2(root);
    }

}
