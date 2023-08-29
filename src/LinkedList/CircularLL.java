package LinkedList;

public class CircularLL {
    static class Node {
        int data;
        Node next;
        Node(int val) {
            data = val;
            next = null;
        }
    }

    static Node insert(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            newNode.next = head;
            return head;
        }

        Node temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.next = head;
        return head;
    }

    static void printList(Node head) {
        Node temp = head;
        while(temp.next != head) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println(temp.data);
        System.out.println();
    }

    static boolean isCircularLL(Node head) {
        if (head == null) {
            return false;
        }

        Node temp = head.next;
        while (temp != null && temp != head) {
            temp = temp.next;
        }

        return (temp == head);
    }

    static boolean isCircularLL2(Node head) {
        // using slow and fast pointers
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Node head = null;
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        printList(head);
        boolean circular = isCircularLL2(head);
        System.out.println(circular);
    }
}
