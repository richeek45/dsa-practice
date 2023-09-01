package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseLevelOrderTraversal {
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
        if(root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    static void printCurrentLevel(Node root, int level) {
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

    static void printReverseLevelOrder(Node root) {
        int height = getHeight(root);
        for (int i = height; i >= 0; i--) {
            printCurrentLevel(root, i);
        }
    }

    static void printReverseLevelOrder2(Node root) {
        // Using queue and stack
        Stack<Node> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // adding right nodes to the queue then left nodes
            // for adding in the stack in the reverse order sequence
            // when popping from the stack left - right order the nodes will return in a level
            Node topNode = queue.peek();
            queue.remove();
            stack.add(topNode);

            if (topNode.right != null) {
                queue.add(topNode.right);
            }

            if (topNode.left != null) {
                queue.add(topNode.left);
            }

        }

        while(!stack.isEmpty()) {
            Node node = stack.peek();
            stack.pop();
            System.out.print(node.data + " ");
        }

    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);

        printReverseLevelOrder2(tree);
    }
}
