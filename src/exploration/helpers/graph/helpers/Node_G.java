package exploration.helpers.graph.helpers;

import java.util.ArrayList;
import java.util.List;

public class Node_G {
    public int val;
    public List<Node_G> neighbours;

    public  Node_G() {
        val = 0;
        neighbours = new ArrayList<>();

    }

    public  Node_G(int val) {
        this.val = val;
        neighbours = new ArrayList<>();

    }

    public  Node_G(int val, ArrayList<Node_G> neighbours) {
        this.val = val;
        this.neighbours = neighbours;

    }
}
