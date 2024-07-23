package a.expo.helpers.graph.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WLaderTwo {

    public String str;
    public int count;

    public void setPrevs(List<String> prevs) {
        this.prevs = prevs;
        this.used.addAll(prevs);
    }

    public List<String> prevs;

    public HashSet<String> getUsed() {
        return used;
    }

    public HashSet<String> used;

    public List<String> getPrevs() {
        return prevs;
    }


    public WLaderTwo(String str, int count) {
        this.str = str;
        this.count = count;
        prevs = new ArrayList<>();
        used= new HashSet<>();
    }

}
