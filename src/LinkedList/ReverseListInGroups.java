package LinkedList;

public class ReverseListInGroups {
    // Given a linked list, write a function to reverse every k nodes (where k is an input to the function).
    //Input: 1->2->3->4->5->6->7->8->NULL, K = 3
    //Output: 3->2->1->6->5->4->8->7->NULL
    //Input: 1->2->3->4->5->6->7->8->NULL, K = 5
    //Output: 5->4->3->2->1->8->7->6->NULL
    static Node head;

    static class Node {
        int data;
        Node next;
        Node (int val) {
            data = val;
            next = null;
        }
    }
    static void insert(int val) {
        if (head == null) {
            head = new Node(val);
            return;
        }

        Node temp = new Node(val);
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = temp;
    }

    static void printList(Node head) {
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    static Node reverseList(Node head, int k) {
        // Auxiliary Space -> O(n/k) + 1 (no of recursions)

        if (head == null) {
            return null;
        }
        Node curr = head;
        int i = 0;
        Node prev = null;
        Node next = null;
        while (i < k && curr != null) {
            // curr creates a link with previous node and hop to the next node
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }

        if (next != null) {
            head.next = reverseList(next, k);
        }
        // Recursion Tree:
        // 1) 3 2 1 -> null, head = 1, prev = 3, next = 4
        // 2) 6 5 4 -> null, head = 4, prev = 6, next = 7,
        // 3) 8 7 -> null, head = 7, prev = 8, next = null
        return prev;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
        printList(head);
        head = reverseList(head, 5);
        printList(head);
    }
}
