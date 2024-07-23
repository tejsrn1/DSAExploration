package a.expo.helpers.llist.lru;

public class DoublyListNode {

    int data;
    DoublyListNode next;
    DoublyListNode prev;

    public DoublyListNode(int data) {
        this.data = data;
        prev = null;
        next = null;
    }
}
