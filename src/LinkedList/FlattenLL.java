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

    public static void main(String[] args) {
        int[][] arr = {{5, 7, 8, 30}, {10, 20}, {19, 22, 15}, {28, 35, 40, 45}};
        int row = arr[0].length;
        int col = arr.length;
        Node head = null;
        for (int i = 0; i < row; i++) {
            head = insert(head, arr[i], i);
        }
        printList(head);


    }
}
