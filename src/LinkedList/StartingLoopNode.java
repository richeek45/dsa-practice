package LinkedList;

public class StartingLoopNode {
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

    private static void createLoop(Node head, int val) {
        Node temp = head;
        Node endNode = null;
        while(temp.next != null) {
            if (temp.data == val) {
                endNode = temp;
            }
            temp = temp.next;
        }
        temp.next = endNode;
    }

    static Node findFirstLoopNode(Node head) {
        // https://www.geeksforgeeks.org/find-first-node-of-loop-in-a-linked-list/
        // Using Floyd's Cycle finding algorithm
        Node slow = head;
        Node fast = head;
        slow = slow.next;
        fast = fast.next.next;

        while (slow != null && fast.next != null) {
            if (fast == slow) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        if (slow != fast) {
            return null;
        }

        slow = head;
        while (fast != slow) {
            if (fast == null) {
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    static Node findfindFirstLoopNode2(Node head) {
        // Using a temporary node and pointing every node to the node while traversing so when the same node is
        // encountered in the loop it will point to temporary node
        Node temp = new Node(-1);

        while (head != null) {
            if (head.next == null) {
                return null;
            }

            if (head.next == temp) {
                return head;
            }

            Node next = head.next;
            head.next = temp;
            head = next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node head = null;
        // create a loop between 3 and 5
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        createLoop(head, arr[2]);
        Node first = findfindFirstLoopNode2(head);
        System.out.println(first.data);
    }
}
