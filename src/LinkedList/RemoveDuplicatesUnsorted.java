package LinkedList;

public class RemoveDuplicatesUnsorted {
    static Node head;
    static class Node {
        int data;
        Node next;
        Node(int val) {
            data = val;
            next = null;
        }
    }

    public void push(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static Node getMiddle(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node sortedMerge(Node left, Node right) {
        Node mergedHead = new Node(-1); // points to the head of the sortedlist
        Node temp = mergedHead; // used for traversing through left and right list

        while (left != null && right != null) {
            // compare two list until one of the list is null
            if (left.data <= right.data) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }

        // if left list is remaining
        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }

        // if right list is remaining
        while (right != null) {
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }

        return mergedHead; // returns the head of the sortedlist
    }

    static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddle(head);
        Node nextToMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(nextToMiddle);

        Node sortedList = sortedMerge(left, right);
        return sortedList;
    }

    static Node removeDuplicate() {
        Node sortedList = mergeSort(head);
        Node curr = sortedList;
//        printList(sortedList);
        Node temp = sortedList;
        while (temp.next != null) {
            while (temp.data == curr.data) {
                temp = temp.next;
            }

            curr.next = temp;
            curr = curr.next;
            temp = temp.next;
        }
        return sortedList.next;
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 12, 21, 41, 43, 21, 9, 15, 9, 23, 11 };
        RemoveDuplicatesUnsorted list = new RemoveDuplicatesUnsorted();
        for (int i = 0; i < arr.length; i++) {
            list.push(arr[i]);
        }
        printList(head);
        Node newHead = removeDuplicate();
        printList(newHead);
    }
}
