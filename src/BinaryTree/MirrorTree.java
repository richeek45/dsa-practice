package BinaryTree;

public class MirrorTree {
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

    static void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }

    static void mirrorTree(Node root) {
        if (root != null) {
            Node leftChild = root.left;
            Node rightChild = root.right;
            root.left = rightChild;
            root.right = leftChild;
            mirrorTree(root.left);
            mirrorTree(root.right);
        }
    }

    static Node mirrorTree2(Node root) {
        if (root == null) {
            return null;
        }

        Node left = mirrorTree2(root.left);
        Node right = mirrorTree2(root.right);

        root.left = right;
        root.right = left;
        return root;
    }


    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(6);
        root.left.left = new Node(2);
        root.left.right = new Node(4);

        inorderTraversal(root);
        System.out.println();
//        mirrorTree(root);
        Node root1 = mirrorTree2(root);
        inorderTraversal(root1);
    }
}
