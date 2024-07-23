package exploration.helpers.llist.lru;

public class ListNodeCloned {
    public int val;
    public ListNodeCloned next;
    public ListNodeCloned random;

    public ListNodeCloned(int x) {
        val = x;
        next = null;
        random = null;
    }
}
