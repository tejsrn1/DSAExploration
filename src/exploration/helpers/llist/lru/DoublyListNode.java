package exploration.helpers.llist.lru;

public class DoublyListNode {

    int data;
    public DoublyListNode next;
    public DoublyListNode prev;

    public DoublyListNode(int data) {
        this.data = data;
        prev = null;
        next = null;
    }
}
