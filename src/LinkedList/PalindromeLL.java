package LinkedList;

import java.util.Stack;

public class PalindromeLL {
    // check if a linked list is a palindrome or not
    static Node left = null;
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
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static void isPalindromeLL(Node head) {
        // push all the nodes to the stack and compare the data of the nodes with the popped nodes while traversing
        Node temp = head;
        Stack<Node> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        temp = head;
        while (temp != null && !stack.isEmpty()) {
            Node top = stack.pop();
            if (top.data != temp.data) {
                System.out.println(false);
                return;
            }
            temp = temp.next;
        }
        System.out.println(true);
    }

    static boolean isPalindromeLL2(Node head, Node right) {
        // recurse till the end of the list
        // compare the left node and end of the node
        // after last recusion stack gets completed, compare left.next with second last node
        left = head;
        if (right == null) {
            // reached to the last node of the linkedlist
            return true;
        }

        boolean isPalindrome = isPalindromeLL2(head, right.next);
        // if isPalindrome is return false to the previous recursion stack
        System.out.println(isPalindrome + " check" + left.data + " " + right.data);
        if (!isPalindrome) {
            return false;
        }

        boolean isPalindrome1 = left.data == right.data; // equidistant node from both ends of the linkedlist
        left = left.next; // changing the left node to the next node which is referenced outside
        return isPalindrome1;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 3, 2, 1};
        Node head = null;
        for (int i = 0; i < arr.length; i++){
            head = insert(head, arr[i]);
        }
        printList(head);
        isPalindromeLL(head);
        boolean palindrome = isPalindromeLL2(head, head);
        System.out.println(palindrome);
    }
}
