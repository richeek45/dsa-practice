package LinkedList;

public class SortZeroOneTwoLL {
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
            return head;
        }
        Node temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        return head;
    }

    static void printList(Node head) {
        Node temp = head;
        while(temp.next != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.print(temp.data);
        System.out.println();
    }

    static Node sortValues(Node head) {
        int[] count = {0, 0, 0};
        Node temp = head;
        while(temp != null) {
            count[temp.data]++;
            temp = temp.next;
        }

        temp = head;
        int i = 0; // array index for count array
        while(temp != null) {
            if (count[i] == 0) {
                i++;
            } else {
                temp.data = i;
                count[i]--;
                temp = temp.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 2, 1, 1, 2, 0, 1, 0};
        Node head = null;
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        printList(head);
        Node newHead = sortValues(head);
        printList(newHead);

    }
}
