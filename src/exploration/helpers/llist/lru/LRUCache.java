package exploration.helpers.llist.lru;


import java.util.HashMap;

public class LRUCache {

    int cap = 1;

    HashMap<Integer, ListNode_LRU> store;
    ListNode_LRU head = new ListNode_LRU(-100, -100);
    ListNode_LRU tail = new ListNode_LRU(-200, -200);


    public LRUCache(int capacity) {
        cap = capacity;
        store = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    //add or update in Cache.
    public void put(int key, int value) {
        //fresh node
        var freshNode = new ListNode_LRU(key, value);

        //Add
        if (!store.containsKey(key)) {

            //add node to list
            addToList(freshNode);

            // add node to store.
            store.put(key, freshNode);

            //if cap reached then evict from back.
            if (store.size() > cap) {
                evictLastNode();
            }

        }
        //Update
        else {

            //get Node
            var existingNode = store.get(key);

            //remove from list old node
            removeFromList(existingNode);

            // remove from store
            store.remove(key);

            // add fresh one to list
            addToList(freshNode);

            // add fresh one to store.
            store.put(key, freshNode);

        }


    }

    public int get(int key) {

        if (!store.containsKey(key)) {
            return -1;
        }

        //get Node
        var existingNode = store.get(key);

        //remove from list old node
        removeFromList(existingNode);

        // remove from store
        store.remove(key);

        //create fresh node
        var freshNode = new ListNode_LRU(key, existingNode.value);

        // add fresh one to list
        addToList(freshNode);

        // add fresh one to store.
        store.put(key, freshNode);

        return existingNode.value;
    }

    private void addToList(ListNode_LRU freshNode) {
        //fresh node gets add in the front.
        //after Sentinel node i.e. after psudo head node.

        //Both use case : h -? - t or even h - ?- 1 -- 2 -- T

        freshNode.prev = head; //  H <- 1
        freshNode.next = head.next; // 1 -> T
        head.next = freshNode; // H -> 1
        freshNode.next.prev = freshNode;
    }

    private void evictLastNode() {
        var nodeToEvict = tail.prev;
        //remove from list
        removeFromList(nodeToEvict);
        //remove from store
        store.remove(nodeToEvict.key);
    }

    private void removeFromList(ListNode_LRU nodeToRemove) {
        //H -- 2 --- 3----4----x6x---- T or H -- 2 --- 3----x4x--- 6-----T

        nodeToRemove.prev.next = nodeToRemove.next;// 4 -> T
        nodeToRemove.next.prev = nodeToRemove.prev;// 4 <- T

        //No need as it is already alone and ready for GC
//        nodeToRemove.prev = null;
//        nodeToRemove.next = null;

    }


}

class ListNode_LRU {
    int value;
    int key;
    ListNode_LRU next;
    ListNode_LRU prev;

    public ListNode_LRU(int key, int value) {
        this.value = value;
        this.key = key;
        prev = null;
        next = null;
    }
}
