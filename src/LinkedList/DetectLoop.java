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

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        Node head = null;
        // create a loop between 3 and 5
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        createLoop(head, arr[1]);
        boolean loop = detectLoop2(head);
        System.out.println(loop);
    }
}
