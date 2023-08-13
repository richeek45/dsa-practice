package LinkedList;

import java.util.Stack;

public class ReverseLinkedList {
    static Node head; // instantiating a global head Node for access
    static class Node {
        int data;
        Node next;

        Node(int val) {
            data = val;
            next = null;
        }
    }

    static void insertList(int val) {
        if (head == null) {
            head = new Node(val);
            return;
        }

        Node node = head;
        while (node.next != null) {
            node = node.next;
        }

        node.next = new Node(val);
    }

    static void reverseList() {
        // Using iterative approach
        Node curr = head; // stores the current node
        Node prev = null;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    static Node reverseList2(Node curr) {
        // Using recursion
        if (curr.next == null) {
            head.next = null;
            head = curr;
            return head;
        }

        Node prev = reverseList2(curr.next);
        prev.next = curr;
        return curr;

    }

    static Node reverseList3(Node curr, Node prev) {
        // Using Tail Recursion
        if (head == null) {
            return head;
        }

        if (curr.next == null) {
            head = curr;
            curr.next = prev;
            return head;
        }

        Node next = curr.next;
        curr.next = prev;
        reverseList3(next, curr);
        return head;
    }

    static void reverseList4() {
        Stack<Node> stack = new Stack<>();
        Node node = head;
        while (node.next != null) {
            stack.add(node);
            node = node.next;
        }
        head = node; // update the head to the last element i.e. current position of node

        while (!stack.isEmpty()) {
            Node prev = stack.pop();
            node.next = prev;
            node = node.next;
        }
        node.next = null;
    }

    static void printList() {
        // printing the linked list
        Node node = head;
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.data + "->");
            } else {
                System.out.print(node.data);
            }
            node = node.next;
        }
        System.out.println();
    }

    public static void main (String[] args) {
        int[] arr = { 5, 23, 15, 45, 32, 72, 52};

        for (int i = 0; i < arr.length; i++) {
            insertList(arr[i]);
        }

//        printList();
//        reverseList();
//        reverseList2(head);
//        reverseList3(head, null);
        reverseList4();
        printList();
    }
}
