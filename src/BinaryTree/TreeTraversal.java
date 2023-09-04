package BinaryTree;

import java.util.ArrayList;
import java.util.List;
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
        List<Integer> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root); // added root node to the stack
        Node prev = null;

        while(!stack.isEmpty()) {
            // We will maintain two pointers for previous top node in the stack
            // and current top node in the stack
            // prev node means that node is last visited
            // if left node of prev node is curr meaning left node is already visited
            // keep pushing left node of curr in the stack until left node is null
            Node curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                // prev == null -> no node is last visited i.e. root node is pushed
                // prev.left == curr -> left node of prev node is last visited, so we will check if more nodes
                // are present in the left node of curr node, if not add the right node
                // prev.right == curr -> right node of prev is lastly visited, then we again try to add the left node
                // if present else try adding right node and if no node is present then pop and add it to the list
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    list.add(curr.data);
                }
            } else if (curr.left == prev) {
                // all the left subtree of curr is visited, add the right node to the stack
                if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    // no right node is present
                    stack.pop();
                    list.add(curr.data);
                }
            } else if (curr.right == prev) {
                // all the node in the left and right is already visited, hence pop the root i.e. curr
                stack.pop();
                list.add(curr.data);
            }

            // updating the prev node
            prev = curr;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    static void postOrderTraversal3(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while(true) {
            while(curr != null) {
                stack.push(curr);
                stack.push(curr);
                curr = curr.left;
            }

            if (stack.isEmpty()) return;

            curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                curr = curr.right;
            } else {
                System.out.print(curr.data + " ");
                curr = null;
            }
        }
    }

    static void postOrderTraversal4(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        boolean check = true; // check is added to give control to the node so that we can push all the left node
        // to the stack
        while(true) {
            while (curr != null && check) {
                stack.push(curr);
                curr = curr.left;
            }

            if (stack.isEmpty()) return;

            Node top = stack.peek();
            if (top.right != curr) {
                // to check if right node is already visited and present in the stack
                curr = top.right;
                check = true;
                continue;
            }

            curr = stack.pop();
            check = false;
            System.out.print(curr.data + " ");
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
//        postOrderTraversal2(root);
//        postOrderTraversal3(root);
        postOrderTraversal4(root);
    }
}
