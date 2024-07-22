package commonPatterns;

public class LinkedList {
    Node head;

    static class Node {
        int data;
        Node next;
        Node (int val) {
            data = val;
            next = null;
        }
    }

    public static LinkedList insert(LinkedList list, int val) {
        Node node = new Node(val);
        if (list.head == null) {
            list.head = node;
            return list;
        }

        Node temp = list.head;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        return list;
    }

    public static void printList(LinkedList list) {
        Node temp = list.head;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list = insert(list, 1);
        list = insert(list, 2);
        list = insert(list, 3);
        list = insert(list, 4);
        list = insert(list, 5);
        list = insert(list, 6);

        printList(list);
    }
}
