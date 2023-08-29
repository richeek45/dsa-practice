package LinkedList;

public class LastElementShift {
    static class Node {
        int data;
        Node next;

        Node(int val) {
            this.data = val;
            this.next = null;
        }
    }

    public static Node insert(Node head, int val) {
        if (head == null) {
            return new Node(val);
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(val);
        return head;
    }

    public static void printList(Node head) {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static Node moveLastElement(Node head) {
        Node temp = head;
        Node prev = head;
        while(temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        temp.next = head;
        head = temp;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5 };
        Node head = null;
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        printList(head);
        Node newHead = moveLastElement(head);
        printList(newHead);
    }
}
