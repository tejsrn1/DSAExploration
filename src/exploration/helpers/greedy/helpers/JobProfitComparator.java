package exploration.helpers.greedy.helpers;

import java.util.ArrayList;
import java.util.Comparator;

public class JobProfitComparator implements Comparator<ArrayList<Integer>> {


    @Override
    public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        return list1.get(0)- list2.get(0);
    }


}
