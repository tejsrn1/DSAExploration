package exploration;


import exploration.helpers.llist.lru.DoublyListNode;

public class DoubleLinkedListDSA {

    /** 
     * This method appends a new node to the end of a doubly linked list.
     *
     * @param  data The data to be stored in the new node.
     * @param  head The head node of the doubly linked list.
     * @return The head node of the updated doubly linked list.
     * <p>
     * Example:
     * Input: data = 4, head = [1,2,3]
     * Output: [1,2,3,4]
     */
    public DoublyListNode InsertNode_Append(int data, DoublyListNode head) {
        DoublyListNode appendNode = new DoublyListNode(data);
        if (head == null) {
            head = appendNode;
            return head;
        }
        DoublyListNode tail = head;
        while (tail.next != null) {
            tail = tail.next; // Traverse to the end of the list
        }
        tail.next = appendNode; // Append the new node
        appendNode.prev = tail; // Set the previous node of the new node
        tail = appendNode; // Update the tail

        return head;
    }

    /**
     * This method deletes the last node of a doubly linked list.
     *
     * @param  head The head node of the doubly linked list.
     * @return The head node of the updated doubly linked list.
     * <p>
     * Example:
     * Input: head = [1,2,3,4]
     * Output: [1,2,3]
     */
    public DoublyListNode Delete_Last(DoublyListNode head) {
        DoublyListNode cur = head;
        while (cur.next != null) {
            cur = cur.next; // Traverse to the end of the list
        }
        cur.prev.next = null; // Remove the last node
        cur.prev = null; // Clear the previous node of the last node

        return head;
    }

    /**
     * This method reverses a doubly linked list.
     *
     * @param  head The head node of the doubly linked list.
     * @return The head node of the reversed doubly linked list.
     * <p>
     * Example:
     * Input: head = [1,2,3,4]
     * Output: [4,3,2,1]
     */
    public DoublyListNode Reverse(DoublyListNode head) {
        DoublyListNode temp = null;
        DoublyListNode curr = head;

        while (curr != null) {
            temp = curr.prev; // Store the previous node
            curr.prev = curr.next; // Swap the previous and next nodes
            curr.next = temp; // Set the next node to the stored node
            curr = curr.prev; // Move to the next node
        }

        // If the list is not empty or has only one node, update the head
        if (temp != null)
            head = temp.prev;
        return head;
    }

    /**
     * This method reverses a doubly linked list.
     * This is an alternate implementation of the Reverse method.
     *
     * @param  head The head node of the doubly linked list.
     * @return The head node of the reversed doubly linked list.
     * <p>
     * Example:
     * Input: head = [1,2,3,4]
     * Output: [4,3,2,1]
     */
    public DoublyListNode Reverse_Alternate(DoublyListNode head) {
        DoublyListNode prevNode = null, next = null;
        DoublyListNode cur = head;

        while (cur != null) {
            next = cur.next; // Store the next node
            cur.next = prevNode; // Swap the next and previous nodes
            if (prevNode != null) {
                prevNode.prev = cur; // Set the previous node of the previous node to the current node
            }
            prevNode = cur; // Update the previous node
            cur = next; // Move to the next node
        }
        return prevNode;
    }
}
