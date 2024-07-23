package commonPatterns;

public class ReorderList {

    public static LinkedList.Node reorderList(LinkedList.Node head) {
        LinkedList.Node fast = head.next;
        LinkedList.Node slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        LinkedList.Node first = head;
        LinkedList.Node second = slow.next;
        LinkedList.Node prev = slow.next = null, next = null;

        while(second != null) {
            next = second.next;
            second.next = prev;
            prev = second;
            second = next;
        }
        second = prev;

        while(second != null) {
            LinkedList.Node firstNext = first.next;
            LinkedList.Node secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }

        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        LinkedList list = new LinkedList();
        for(int val: arr) {
            LinkedList.insert(list, val);
        }
        list.head = reorderList(list.head);
        LinkedList.printList(list);
    }
}
