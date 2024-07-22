package commonPatterns;

public class ReverseLinkedList {
    public static LinkedList reverseList(LinkedList list) {
        //  1 -> 2 -> 3 -> 4 -> 5
        LinkedList.Node currentNode = list.head;
        LinkedList.Node previousNode = null;
        LinkedList.Node nextCurrent = null;

        while(currentNode != null) {
            nextCurrent = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextCurrent;
        }
        list.head = previousNode;

        return list;
    }


    public static void main(String[] args) {
        int[] values = {1,2,3,4,5,6};
        LinkedList list = new LinkedList();
        for(int val: values) {
            list = LinkedList.insert(list, val);
        }

        LinkedList.printList(list);
        list = reverseList(list);
        LinkedList.printList(list);
    }
}
