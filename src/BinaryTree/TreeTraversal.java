package BinaryTree;

import java.util.Stack;

public class TreeTraversal {
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

    static void inOrderTraversal(Node root) {
        // iterative approach
        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.peek();
            stack.pop();
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    static void preOrderTraversal(Node root) {
        // iterative approach
        // traverse node and then traverse left tree and then right node
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        stack.push(curr);
        while(!stack.isEmpty()) {
            // pop the root from the stack and print
            Node top = stack.peek();
            stack.pop();
            System.out.print(top.data + " ");
            // push the right node and then push the left node in the stack
            // so that the top node is left node in the stack
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);
        root.left.right.right = new Node(7);
        // 4 2 6 5 7 1 3 -> in-order
        // 1 2 4 5 6 7 3 -> pre-order

        inOrderTraversal(root);
        preOrderTraversal(root);
    }
}
