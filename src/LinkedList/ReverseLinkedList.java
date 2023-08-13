package LinkedList;

public class ReverseLinkedList {
    static Node head; // instantiating a global head Node for access
    static class Node {
        int data;
        Node next;

        Node(int val) {
            data = val;
            next = null;
        }
    }

    static void insertList(int val) {
        if (head == null) {
            head = new Node(val);
            return;
        }

        Node node = head;
        while (node.next != null) {
            node = node.next;
        }

        node.next = new Node(val);
    }

    static void reverseList() {
        // Using iterative approach
        Node curr = head; // stores the current node
        Node prev = null;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    static Node reverseList2(Node curr) {
        // Using recursion
        if (curr.next == null) {
            head.next = null;
            head = curr;
            return head;
        }

        Node prev = reverseList2(curr.next);
        prev.next = curr;
        return curr;

    }

    static void printList() {
        // printing the linked list
        Node node = head;
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.data + "->");
            } else {
                System.out.print(node.data);
            }
            node = node.next;
        }
        System.out.println();
    }

    public static void main (String[] args) {
        int[] arr = { 5, 23, 15, 45, 32, 72, 52};

        for (int i = 0; i < arr.length; i++) {
            insertList(arr[i]);
        }

//        printList();
//        reverseList();
        reverseList2(head);
        printList();
    }
}
