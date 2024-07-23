package a.expo.helpers.llist.lru;

public class ListNodeCloned {
    int val;
    ListNodeCloned next;
    ListNodeCloned random;

    ListNodeCloned(int x) {
        val = x;
        next = null;
        random = null;
    }
}
