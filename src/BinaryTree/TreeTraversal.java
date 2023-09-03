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
            // in every root node push all the left nodes of the left subtree
            // keep popping the left nodes and move to the right node of the left node
            // and add it to the stack
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

    static void preOrderTraversal2(Node root) {
        // using Morris Traversal Algorithm
        Node curr = root;
        while(curr != null) {
            if (curr.left == null) {
                System.out.print(curr.data + " ");
                curr = curr.right;
            } else {
                // curr.left is not null
                Node pre = curr.left;
                while(pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // first time traversing and adding thread of right pointing to the curr node
                    System.out.print(curr.data + " ");
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    // pre.right is pointing to the curr node
                    pre.right = null;
                    curr = curr.right;
                }


            }
        }
    }

    static void postOrderTraversal(Node root) {
        // iterative approach
        // traverse the left tree, right tree and then root
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node curr = root;
        stack1.push(root);
        while(!stack1.isEmpty()) {
            Node topNode = stack1.peek();
            stack1.pop();
            stack2.push(topNode);
            if (topNode.left != null) {
                stack1.push(topNode.left);
            }
            if (topNode.right != null) {
                stack1.push(topNode.right);
            }
        }
        while(!stack2.isEmpty()) {
            Node topNode = stack2.peek();
            stack2.pop();
            System.out.print(topNode.data + " ");
        }
    }

    static void postOrderTraversal2(Node root) {
        // Using 1 stack
        // 1.1 Create an empty stack
        // 2.1 Do following while root is not NULL
        //    a) Push root's right child and then root to stack.
        //    b) Set root as root's left child.
        // 2.2 Pop an item from stack and set it as root.
        //    a) If the popped item has a right child and the right child
        //       is at top of stack, then remove the right child from stack,
        //       push the root back and set root as root's right child.
        //    b) Else print root's data and set root as NULL.
        // 2.3 Repeat steps 2.1 and 2.2 while stack is not empty.
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        stack.push(root.right);
        stack.push(root);
        curr = curr.left;

        while(!stack.isEmpty()) {
            while(curr != null) {
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                stack.push(curr);
                curr = curr.left;
            }
            Node topNode = stack.peek();
            stack.pop();
            if (topNode.right == stack.peek()) {
                // current topmost node in the stack is right child of topNode
                Node rightNode = stack.peek();
                stack.pop();
                stack.push(topNode);
                curr = curr.right;
            } else {
                System.out.print(curr.data + " ");
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
        // 4 6 7 5 2 3 1 -> post-order

        inOrderTraversal(root);
//        preOrderTraversal(root);
        preOrderTraversal2(root);
        System.out.println();
//        postOrderTraversal(root);
        postOrderTraversal2(root);
    }
}
