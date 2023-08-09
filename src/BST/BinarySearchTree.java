package BST;

public class BinarySearchTree {
    private static class Node {
        int key;
        Node left;
        Node right;
    }

    private static Node newNode(int item) {
        Node temp = new Node();
        temp.key = item;
        temp.left = temp.right = null;
        return temp;
    }

    static Node insert(Node node, int key) {
        if (node == null) {
            return newNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    static void inorder(Node root) {
        // Inorder traversal: In case of binary search trees (BST),
        // Inorder traversal gives nodes in non-decreasing order.
        // We visit the left child first, then the root, and then the right child.
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + ", ");
            inorder(root.right);
        }
    }

    static void preOrder(Node root) {
        // root -left - right
        if (root != null) {
            System.out.print(root.key + ", ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.key + ", ");
        }
    }

    static int height (Node node) {
        // to find the height of a tree, search for the depth of left-sub-tree and right-sub-tree
        // return maximum of left and right sub-tree + 1 (for current root node)
        if (node == null) {
            return 0;
        }

        int lDepth = height(node.left);
        int rDepth = height(node.right);

        return Math.max(lDepth, rDepth) + 1;

    }

    private static void printLevel(Node root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(" " + root.key);
            return;
        }

        printLevel(root.left, level - 1);
        printLevel(root.right, level - 1);

    }

    static void levelOrder(Node root) {
        if (root != null) {
            int height = height(root);
            for (int i = 1; i <= height; i++) {
                printLevel(root, i);
                System.out.println();
            }
//            // printing node at a given level
//            printLevel(root, 2); // 2 -> level
//            // printing leaf nodes
//            printLevel(root, height);

        }
    }

    static void printLeafNodes(Node root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.print(" " +  root.key);
        }

        if (root.left != null) {
            printLeafNodes(root.left);
        }

        if (root.right != null) {
            printLeafNodes(root.right);
        }

    }

    static void printNonLeafNodes(Node root) {
        if (root == null || root.left == null || root.right == null) {
            return;
        }

        System.out.print(" " + root.key);
        printNonLeafNodes(root.left);
        printNonLeafNodes(root.right);
    }

    static void rightViewTree(Node root) {
        if (root != null) {
            System.out.print(" " + root.key);
            rightViewTree(root.right);
        }
    }

    private static Node minValueNode(Node node) {
        Node current = node;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    static Node deleteNode(Node root, int key) {
        // Different scenarios for deleting the node:
        //Node to be deleted is the leaf node : Its simple you can just null it out.
        //Node to be deleted has one child : You can just replace the node with the child node.
        //Node to be deleted has two child :
        if (root == null) {
            return root;
        }

        if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else {
            // key == root.key
            // for one children
            if (root.left == null) {
                Node temp = root.right;
                return temp;
            }
            if (root.right == null) {
                Node temp = root.left;
                return temp;
            }
            // for 2 children
            // we need to find the smallest value in the right sub-tree to replace the root node
            // so that all the elements in the right sub-tree will be greater than root node
            Node temp = minValueNode(root.right);
            root.key = temp.key;
            root.right = deleteNode(root.right, temp.key);
        }
        return root;
    }

    static int nodeCount(Node node) {
        if (node == null) {
            return 0;
        }
        return nodeCount(node.left) + nodeCount(node.right) + 1;
    }

    public static void main(String[] args) {
        /* Let us create following BST
                    50
                 /     \
                30      70
               /  \    /  \
             20   40  60   80
        */
        Node root = null;
        root = insert(root, 50);
        insert(root, 30);
        insert(root, 20);
        insert(root, 40);
        insert(root, 70);
        insert(root, 60);
        insert(root, 80);
        insert(root, 65);

        // print the BST
//        inorder(root);
//        preOrder(root);
//        postOrder(root);
//        levelOrder(root);
//        printLeafNodes(root);
//        printNonLeafNodes(root);
//        rightViewTree(root);
//        deleteNode(root, 70);
        int count = nodeCount(root);
        System.out.println(count);
        levelOrder(root);
    }
}
