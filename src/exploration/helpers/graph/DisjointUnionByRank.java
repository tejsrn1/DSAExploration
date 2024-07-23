package exploration.helpers.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointUnionByRank {
    List<Integer> rank = new ArrayList<Integer>();
    List<Integer> parent = new ArrayList<Integer>();

    public DisjointUnionByRank(int totalNodes) {
        for (int i = 0; i <= totalNodes; i++) {
            parent.add(i);
            rank.add(0);// need to initialize with 0.
        }
    }

    public void unionbyRank(int u, int v) {
        //gets ulimiate parent
        var ulimateParent_U = getUlimateParent(u);
        var ulimateParent_V = getUlimateParent(v);


        if (ulimateParent_U == ulimateParent_V) {
            return;
        } else {
            //check ranks
            var rank_U = rank.get(ulimateParent_U);
            var rank_V = rank.get(ulimateParent_V);

            if (rank_U == rank_V) {
                //connect and increase rank
                parent.set(ulimateParent_V, ulimateParent_U);//connect v -> U
                rank.set(ulimateParent_U, rank_U + 1);

            } else if (rank_V < rank_U) {
                parent.set(ulimateParent_V, ulimateParent_U);//connect v -> U
            } else {
                parent.set(ulimateParent_U, ulimateParent_V);//connect U -> V
            }
        }

    }

    public int getUlimateParent(int u) {

        //return when Node itself is its PARENT
        var maybeUltimpateParent = parent.get(u);
        if (u == maybeUltimpateParent) {
            return u;
        }

        //go look for root parent
        int ultimateParent = getUlimateParent(maybeUltimpateParent);
        //set root Parent to current node to avoid next time look up.
        parent.set(u, ultimateParent);
        return ultimateParent;

    }
}
