package LinkedList;

public class MergeSortLinkedList {
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
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        return head;
    }

    static void printList(Node head) {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private static Node sortedMerge(Node left, Node right) {
        // left and right are the head of two lists
        Node result = null;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.data <= right.data) {
            result = left;
            result.next = sortedMerge(left.next, right);
        } else {
            result = right;
            result.next = sortedMerge(left, right.next);
        }
        return result;
    }

    private static Node sortedMerge2(Node left, Node right) {
        Node merged = new Node(-1); // points to the first node
        Node temp = merged;

        while (left != null && right != null) {
            if (left.data <= right.data) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }

        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }

        while (right != null) {
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }

        return merged.next;
    }

    static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // find the middle node
        Node middle = getMiddle(head);
        Node nextToMiddle = middle.next;
        middle.next = null; //  cut the link between two lists

        // sort the left and right lists
        Node left = mergeSort(head);
        Node right = mergeSort(nextToMiddle);

//        Node sortedList = sortedMerge(left, right);
        Node sortedList = sortedMerge2(left, right);
        return sortedList;
    }

    public static void main(String[] args) {
        int[] arr = { 15, 10, 5, 20, 3, 2};
        Node head = null;
        for (int i = 0; i < arr.length; i++) {
            head = insert(head, arr[i]);
        }
        printList(head);
        Node newHead = mergeSort(head);
        printList(newHead);

    }
}
