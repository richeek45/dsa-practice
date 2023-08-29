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

    private static Node reverseLL(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }

    private static boolean compareLL(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.data != temp2. data) {
                return false;
            }
        }

        if (temp1 == null && temp2 == null) {
            return true;
        }

        return false;
    }

    static boolean isPalindromeLL3(Node head) {
        // get the middle node and reverse the second half of the linkedlist
        // check if the first half and the second half of the linkedlist
        // construct the original linkedlist by reversing the second half and attaching back to the linkedlist
        Node slow = head;
        Node fast = head;
        Node prevToSlow = head;
        Node middleNode = null;
        while(fast.next != null && fast.next.next != null) {
            prevToSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // we need to reverse prev to middle node for odd and middle node for even linkedlist nodes
        // fast = null the number of nodes are even else odd
        if (fast != null) { // odd number of nodes
            middleNode = slow;
            slow = slow.next; // slow points second half of the ll
        }

        Node secondHead = slow;
        prevToSlow.next = null;
        secondHead = reverseLL(secondHead);

        boolean res = compareLL(head, secondHead);
        secondHead = reverseLL(secondHead);

        if (middleNode != null) {
            // for odd nodes
            prevToSlow.next = middleNode;
            middleNode.next = secondHead;
        } else {
            prevToSlow.next = secondHead;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 3, 2, 1};
        Node head = null;
        for (int i = 0; i < arr.length; i++){
            head = insert(head, arr[i]);
        }
        printList(head);
        isPalindromeLL(head);
        boolean palindrome = isPalindromeLL3(head);
        System.out.println(palindrome);
    }
}
