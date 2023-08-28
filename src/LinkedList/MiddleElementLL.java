package LinkedList;

public class MiddleElementLL {
    static class Node {
        int data;
        Node next;
        int flag;
        Node(int val) {
            data = val;
            next = null;
            flag = 0;
        }
    }

    private static Node insert(Node head, int val) {
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

    static void printList(Node head) {
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    static Node getMiddleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static Node getMiddleNode2(Node head) {
        int count = 0;
        Node mid = head;
        // increase the counter and shift the mid node when count is odd
        while (head != null) {
            if (count % 2 == 1) {
                mid = mid.next;
            }
            count++;
            head = head.next;
        }
        return mid;
    }

    public static void main (String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node head = null;
        // create a loop between 3 and 5
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        printList(head);
        Node middle = getMiddleNode2(head);
        System.out.println(middle.data);


    }
}
