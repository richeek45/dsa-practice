package LinkedList;


public class RemoveDuplicate {
    static Node head;
    class Node {
        int data;
        Node next;
        Node (int val) {
            data = val;
            next = null;
        }
    }

    public void push(int val) {
        Node temp = new Node(val);
        temp.next = head;
        head = temp;
    }

    static void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static void removeDuplicate() {
        // check if the temp.next.data = temp.data
        Node curr = head;
        Node temp = curr.next;
        while (curr != null) {
            while (temp != null && curr.data == temp.data) {
                temp = temp.next;
            }
            curr.next = temp;
            curr = temp;
        }
    }

    public static void main (String[] args) {
        // Remove duplicates from a sorted linked list
        RemoveDuplicate list = new RemoveDuplicate();
        int[] arr = { 20, 20, 13, 13, 13, 11, 11, 11, 9, 9};
        for (int i = 0; i < arr.length; i++) {
            list.push(arr[i]);
        }
        printList();
        removeDuplicate();
        printList();


    }
}
