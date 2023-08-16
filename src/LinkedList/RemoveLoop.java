package LinkedList;

public class RemoveLoop {
    // Write a function detectAndRemoveLoop() that checks whether a given Linked List contains a loop
    // and if the loop is present then remove the loop and return true.
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
    static boolean detectLoop(Node head) {
        // Floyd cycle algorithm
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

    static void deleteLoop(Node head) {
        Node temp1 = head;
        Node temp2 = head;
        while(temp1 != null && temp2 != null && temp2.next != null) {
            temp1 = temp1.next;
            temp2 = temp2.next.next;
            if (temp1 == temp2) {
//                removeLoop(temp1, head);
                removeLoop1(temp1, head);
                return;
            }
        }
    }

    static void removeLoop(Node loop, Node head) {
        Node ptr1 = loop;
        Node ptr2 = loop;
        Node prev = ptr2;
        int k = 1;
        // finding the count of nodes in the loop
        while (ptr1.next != ptr2) {
            ptr1 = ptr1.next;
            k++;
        }

        int count = 1;
        ptr1 = head;
        while(ptr1 != ptr2) {
            prev = ptr2;
            ptr2 = ptr2.next;
            count++;
            if (count > k && ptr1 != ptr2) {
                ptr1 = ptr1.next;
                count = 0;
            }
        }
        prev.next = null;
        System.out.println(prev.data);
    }
    static void removeLoop1(Node loop, Node head) {
        Node slow = head;
        Node fast = loop;
        if (slow != fast) {
            while(slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
            return;
        }
        // if fast and slow pointer are pointing to the same pointer
        while(fast.next != slow) {
            fast = fast.next;
        }
        fast.next = null;
    }

    static void printList(Node head) {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static Node insert(Node head, int val) {
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


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Node head = null;
        // create a loop between 3 and 5
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        createLoop(head, arr[2]);
        boolean isLoop = detectLoop(head);
        if (isLoop) {
            deleteLoop(head);
        }
        printList(head);

    }
}
