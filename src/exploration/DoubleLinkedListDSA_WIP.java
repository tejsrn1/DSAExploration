package exploration;


import exploration.helpers.llist.lru.DoublyListNode;

public class DoubleLinkedListDSA_WIP {

    //Doubly LinkedList Problems


    public DoublyListNode InsertNode_Append(int data, DoublyListNode head) {
        DoublyListNode appendNode = new DoublyListNode(data);
        if (head == null) {
            head = appendNode;
            return head;
        }
        DoublyListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = appendNode;
        appendNode.prev = tail;
        tail = appendNode;

        return head;
    }

    public DoublyListNode Delete_Last(DoublyListNode head) {

        DoublyListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.prev.next = null;
        cur.prev = null;


        return head;
    }

    public DoublyListNode Reverse(DoublyListNode head) {


        DoublyListNode temp = null;
        DoublyListNode curr = head;

        /*
         * Idea :
         *   X  1 2
         *   2  1 X  (swap X and 2)
         *   move next via cur = cur.prev as 2 is now prev of 1 instead next since its got swapped.
         * */
        while (curr != null) {
            temp = curr.prev; // temp is say null for first node
            curr.prev = curr.next; // swap null with 2
            curr.next = temp; // swap 2 with null
            curr = curr.prev; // move next NO NO move prev as 2 becomes prev of 1 since its swapped now.
        }


        //Edge case if our linked list is empty Or list with only one node
        if (temp != null)
            head = temp.prev;
        return head;
    }
    //-----------------------helpers----------------------------

    //-----------------------alternate----------------------------

    //This seems working but not clean
    public DoublyListNode Reverse_Alternate(DoublyListNode head) {

        DoublyListNode prevNode = null, next = null;
        DoublyListNode cur = head;

        while (cur != null) {

            next = cur.next;
            cur.next = prevNode;
            if (prevNode != null) {
                prevNode.prev = cur;
            }
            prevNode = cur;
            cur = next;
        }
        return prevNode;
    }
}
