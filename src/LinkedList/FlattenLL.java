package LinkedList;

public class FlattenLL {
    static class Node {
        int data;
        Node right;
        Node down;
        Node(int val) {
            data = val;
            right = null;
            down = null;
        }
    }

    static Node insert(Node head, int[] arr, int row) {
        int val = arr[0];
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while(temp.right != null) {
                temp = temp.right;
            }
            temp.right = newNode; // adding the first val of arr to the right of the horizontal ll
        }

        // adding all the elements of an array to the downHead
        Node downHead = newNode;
        for (int i = 1; i < arr.length; i++) {
            Node downNode = new Node(arr[i]);
            newNode.down = downNode;
            newNode = newNode.down;
        }
        return head;
    }

    static void printList(Node head) {
        Node temp = head;
        while(temp != null) {
            Node downNode = temp;
            while (downNode != null) {
                System.out.print(downNode.data);
                System.out.print('|');
                downNode = downNode.down;
            }
            System.out.print("->");
            temp = temp.right;
        }
    }

    private static Node merge(Node left, Node right) {
        // compare two vertical linkedlist with left and right as two head of the linkedlist
        // and create a sorted linked list

        // while comparing and calling the down node of the linkedlists if any one of the nodes are null
        // it should return the node of the other ll
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node result = null;
        if (left.data <= right.data) {
            result = left;
            result.down = merge(left.down, right);
        } else {
            result = right;
            result.down = merge(left, right.down);
        }
        // making the right pointer to null because left and right nodes have right pointer attached to it
        result.right = null;

        return result;
    }

    static Node flattenLL(Node root) {
        // call the right node recursively till it hit the reaches the end of the list
        // call merge on the two vertical nodes and compare recusively and create a 1D linkedlist
        // with down as the reference to the next node
        if (root == null || root.right == null) {
            return root;
        }

        // recur for list on the right
        root.right = flattenLL(root.right);

        // create a 1D linked list with two right adjacent nodes with down linked list
        root = merge(root, root.right);

        // sorted linkedlist between two right heads is returned to the previous right head
        return root;
    }

    public static void main(String[] args) {
        int[][] arr = {{5, 7, 8, 30}, {10, 20}, {19, 22, 15}, {28, 35, 40, 45}};
        int row = arr[0].length;
        int col = arr.length;
        Node head = null;
        for (int i = 0; i < row; i++) {
            head = insert(head, arr[i], i);
        }
        printList(head);
        System.out.println();
        Node newHead = flattenLL(head);
        printList(newHead);
    }
}
