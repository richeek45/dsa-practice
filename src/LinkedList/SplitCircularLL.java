package LinkedList;

public class SplitCircularLL {
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

    private static Node getMiddle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast.next != head && fast.next.next != head) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static void printList(Node head) {
        Node temp = head;
        while(temp.next != null && temp.next != head) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.print(temp.data);
        System.out.println();
    }

    static Node[] splitCircularLL(Node head) {
        Node middleNode = getMiddle(head);
        Node nextToMiddle = middleNode.next;
        Node temp = nextToMiddle;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = nextToMiddle;
        middleNode.next = head;
        Node head2 = nextToMiddle;
        Node[] heads = {head, head2};
        return heads;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node head = null;
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }

        Node[] circularLL = splitCircularLL(head);
        printList(circularLL[0]);
        printList(circularLL[1]);

    }
}
