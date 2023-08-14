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

    static Node reverseList2(Node head, int k) {
        if (head == null || head.next == null ) {
            return head;
        }

        Node temp = new Node(-1);
        temp.next = head;
        Node prev = temp;
        Node curr = temp;
        Node next = temp;

        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        // value of count = length of list + 1

        while (next != null) {
            curr = prev.next;
            next = curr.next;

            int itr = count > k ? k : count - 1;
            for (int i = 1; i < itr; i++) {
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }
            // In every iteration prev pointer is storing the head of the kth node
            prev = curr;
            count -= k;
        }

        // temp is pointing to the curr head at the end of the loop
        return temp.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
        printList(head);
//        head = reverseList(head, 5);
        head = reverseList2(head, 3);
        printList(head);
    }
}
