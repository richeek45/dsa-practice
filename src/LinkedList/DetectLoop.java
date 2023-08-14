package LinkedList;
import java.util.HashSet;

public class DetectLoop {
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

    static boolean detectLoop(Node head) {
        HashSet<Node> set = new HashSet<>();
        Node temp = head;
        while (temp.next != null) {
            if (set.contains(temp)) {
                return true;
            }
            set.add(temp);
            temp = temp.next;
            // if set contains Node then it is a loop
        }
        return false;
    }

    static boolean detectLoop2(Node head) {
        // marking flag for visited node
        Node temp = head;
        while (temp.next != null) {
            if (temp.flag == 1) {
                return true;
            }
            temp.flag = 1;
            temp = temp.next;
        }
        return false;
    }

    static boolean detectLoop3(Node head) {
        // Floyd’s Cycle-Finding Algorithm:
        // This algorithm is used to find a loop in a linked list.
        // It uses two pointers one moving twice as fast as the other one.
        // The faster one is called the faster pointer and the other one is called the slow pointer.
        Node ptr1 = head;
        Node ptr2 = head;
        while (ptr1 != null && ptr2 != null && ptr2.next != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next.next;
            if (ptr1 == ptr2) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        Node head = null;
        // create a loop between 3 and 5
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        createLoop(head, arr[1]);
        boolean loop = detectLoop3(head);
        System.out.println(loop);
    }
}
