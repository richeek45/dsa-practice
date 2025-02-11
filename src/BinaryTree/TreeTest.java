package BinaryTree;

public class TreeTest {
    Node root;

    public TreeTest() {
        root = null;
    }

    public void inorder() {
        inorderRoot(root);
    }
    private void inorderRoot(Node root) {
        if (root != null) {
            inorderRoot(root.left);
            System.out.print(root.data + " ");
            inorderRoot(root.right);
        }
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }
    private Node insertNode(Node root, int data) {
        if (root == null) {
            root = new Node(data);
        }

        if (data > root.data) {
            root.right = insertNode(root.right, data);
        } else if (data < root.data) {
            root.left = insertNode(root.left, data);
        }

        return root;
    }

    public void search(int data) {
            boolean isFound = searchNode(root, data);
        System.out.println(isFound);
    }
    private boolean searchNode(Node root, int data) {
        if (root == null) return false;

        if (data == root.data) {
            return true;
        }
        if (data < root.data) {
            return searchNode(root.left, data);
        } else {
            return searchNode(root.right, data);
        }
    }

    public void findMin() {
        int min = findMinNode(root);
        System.out.println(min);
    }
    private int findMinNode(Node root) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        if (root.left == null) {
            return root.data;
        }

        return findMinNode(root.left);
    }

    public void findMax() {
        int max = findMaxNode(root);
        System.out.println(max);
    }
    private int findMaxNode(Node root) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty!");
        }

        if (root.right == null) {
            return root.data;
        }

        return findMaxNode(root.right);
    }

    public static void main(String[] args) {
        TreeTest tree = new TreeTest();
        tree.insert(10);
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        tree.search(100);

        tree.findMin();
        tree.findMax();

        tree.inorder();
    }
}

class Node {
    int data;
    Node left, right;
    Node(int d) {
        data = d;
        left = right = null;
    }
}