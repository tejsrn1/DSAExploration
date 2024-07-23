package exploration.helpers.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointUnionBySize {
    public List<Integer> size = new ArrayList<Integer>();
    List<Integer> parent = new ArrayList<Integer>();

    public DisjointUnionBySize(int totalNodes) {
        for (int i = 0; i <= totalNodes; i++) {
            parent.add(i);
            size.add(1);// - Each node size is 1.
        }
    }

    public void unionbySize(int u, int v) {
        //gets ultimate parent
        var ultPt_u = getUlimateParent(u);
        var ultPt_v = getUlimateParent(v);

        //when ultimate parents are same.
        if (ultPt_u == ultPt_v) {
            return;
        } else {
            //check size of ultimate parents
            var size_ultPt_U = size.get(ultPt_u);
            var size_ultPt_V = size.get(ultPt_v);

            //when both ult pt size same.
            if (size_ultPt_U == size_ultPt_V) {
                //connect and increase rank
                parent.set(ultPt_v, ultPt_u);//connect v -> U
                //adding size of V Node structure to U structure;
                size.set(ultPt_u, size_ultPt_U + size_ultPt_V);


            } else if (size_ultPt_V < size_ultPt_U) {
                parent.set(ultPt_v, ultPt_u);//connect v -> U
                size.set(ultPt_u, size_ultPt_U + size_ultPt_V);//adding size of V Node structure to U structure;
            } else {
                parent.set(ultPt_u, ultPt_v);//connect U -> V
                size.set(ultPt_v, size_ultPt_V + size_ultPt_U); //adding size of U Node structure to V structure;
            }
        }

    }

    public int getUlimateParent(int u) {

        //return when Node itself is its PARENT
        if (u == parent.get(u)) {
            return u;
        }

        //go look for root parent
        int ultPrtn = getUlimateParent(parent.get(u));
        //set root Parent to current node to avoid next time look up.
        parent.set(u, ultPrtn);
        return ultPrtn;

    }
}
