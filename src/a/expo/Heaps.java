package a.expo;

//https://takeuforward.org/strivers-a2z-dsa-course/strivers-a2z-dsa-course-sheet-2/
// Learning, Medium, Hard Problems

import java.util.*;

public class Heaps {

    public int kth_Largest_MaxHeap(int[] arr, int k) {

        //Here we use PriorityQueue for inbuilt Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);//notice comparing for descending.

        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]); //storing descending.
        }

        // now remove k-1 as 0 based index
        // 30 21 17 7 1 and k =3
        // x  x  kth < after 3-1 =2 removed
        int z = k - 1;
        // >0 so that top is kth element.
        while (z > 0) {
            maxHeap.remove();
            z--;
        }
        int res = maxHeap.peek();
        return res;

    }

    public int kth_Smallest_MinHeap(int[] arr, int k) {

        //Here we use PriorityQueue for inbuilt Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //notice goes deafult i.e ascending

        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i]); //storing ascending.
        }

        // now remove k-1 as 0 based index
        // 1 3  7   17 21 and k =3
        // x x  kth < after 3-1 =2 removed
        int z = k - 1;
        // >0 so that top is kth element.
        while (z > 0) {
            minHeap.remove();
            z--;
        }
        int res = minHeap.peek();
        return res;

    }

    public int[] arrayRankTransform2(int[] arr) {

        int[] res = new int[arr.length];
        int rank = 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });

        // storing descending
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        HashMap<Integer, Integer> mp = new HashMap<>();
        while (!pq.isEmpty()) {

            int item = pq.poll();
            if (!mp.containsKey(item)) {
                mp.put(item, rank);
                rank++;
            }

        }
        for (int i = 0; i < arr.length; i++) {
            int rk = mp.get(arr[i]);
            res[i] = rk;

        }

        return res;
    }

    public int[] topKFrequent(int[] arr, int k) {

        if (k > arr.length) return null;

        var myMap = new HashMap<Integer, Integer>();

        // ON
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            myMap.put(key, myMap.getOrDefault(key, 0) + 1);
        }

        //prep min heap based on values in MAP.Ascending.
        // see we are only going to add key but while its being added heap will compare its value
        PriorityQueue<Integer> pq =
                new PriorityQueue<>((a, b) -> myMap.get(a) - myMap.get(b));

        //ON for map key iteration.
        // add in to heap only K elements which is O log K < o log N
        // finally ON + OLog K
        for (int n : myMap.keySet()) {
            pq.add(n);

            // as soon as size > required K start deleting key and
            // that will be lower in value as PQ is based on lower -> higher values storing.
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] topK = new int[k];
        //now just get all keys left in PQ which is equal to k
        //OK + O log K (for poll)
        for (int i = k - 1; i >= 0; i--) {
            topK[i] = pq.poll();
        }

        return topK;
    }

    public int leastInterval_TaskScheduler_1(char[] task, int waiting) {

        /*
        Idea :
        Latest Understanding :

        e.g say we have "A","C","A","B","D","B" ,"A" , "A"
                   i.e  A=4 , C = 1, B =2 and D =1

            Now say waiting n  = 2 so for max freq letter and here it is A =4
            its max wait time is 4 x 2 no 4-1 x2 as last A does not have to wait for anything.
            total wait time is 6. Now??
            we can either wait for 6 for only A or we coudl do other task use this ideal time 6

            so we have C =1 so use it liek 6-1 = 5
            then D =1 ,5-1 =4 then
            B =2 so 4-2 = 2 left to idel.

            Now here we are done with other task in ideal time for A but those task it self count 1 for
            each of them to be done. so

            total task + left ideal time  =  6+2 =8 and this is min total time taken to finish all task.





        Every time task DONE need to wait till waiting time.

        'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
        there are 6 => A
        and 1 => B C D E F G

        LIKE below A has 2 idel time can be filled by other task B and C or G or F
        but when done nothing can be filled

        A _  _ B  C
        A _  _ D  E
        A _  _ G  F
        A _  _
        A _  _
        A      <  NO idle time for last

        A A A  B  B B , n= 2 i.e. total 8  3+3+2
         A _ _ B (1 occupied with B) 3
        A _  _  B (1 occupied with B) 3
        A  B  2  none to occupied but need to finish B as individual and since both are last ele NO idle time
        i.e. A _ _  A _ _ A there B fits like
             A B _  A B _ A B and B does not have separate waiting time has it is waiting via B _ A see n=2 waiting
             and A is already waiting _ _ and used 1 B to fill its work
             A can not use B B as its same letter and it has its own colling time but if there are C
             then A could do B C.
        * */

        //for A- Z
        int[] char_Freq = new int[26];
        //Count freq
        for (char tsk : task) {
            char_Freq[tsk - 'A']++;
        }
        Arrays.sort(char_Freq);

        int maxFreq_OfChar = char_Freq[25]; //last ele has large freq
        int maxFreq_OfChar_ForIdleTiming = maxFreq_OfChar - 1;// -1 bcs last element has not wait time
        int idleTime = maxFreq_OfChar_ForIdleTiming * waiting;

        //now subtract freq of other task to count against idle time.
        for (int i = 24; i >= 0; i--) {
            //say A A A B B B and n =2
            // 3 A and 3 B and there are 4 spots for idle time for A is 3-1 * n
            // if we do not do min then B =3 and A =2
            // then below fourmula takes B as max char and assume total idle time was counted for B instead of A
            // so when there is same freq of 2 char go for min

            idleTime -= Math.min(char_Freq[i], maxFreq_OfChar_ForIdleTiming);
            //can not simply deduct bcs as per my latest understanding.
            // if AAA and BBB is there then below simply take up all
            // ideal time by adjusting B =3 but twos A =2 can only fit
            // 2 B not the third one so for that third B need to add waiting time
            // for the same reason Math.min used to dedcut lesser ideal time and
            // leave left time here third B to get waited and counted towareds final anser.
            //idleTime -= char_Freq[i];

        }

        //taks len is the count for unit of work for each task
        // max of idel time is when say idel time was 0 and during substraciton it become -ve to handle it.
        int res = task.length + Math.max(idleTime, 0);
        return res;
    }

    public void add_find_Median(int[] arr) {

        /*
        Idea :
            Say we have numbers like 41, 35, 62, 5, 97, 108
            after sorting 5 35 41 62 97 108 and then we find median
            here we maintain two heap max and min
            max where left or lo side where middle number is the parent  which is largest.
            min where right or hi side where middle number is the parent which is smallest
            just calculate median using top tow parent based on even or odd size.
    Note : Balancing for two heap is key here to maintain tow parents on top .
        * */

        PriorityQueue<Integer> lo_mx = new PriorityQueue<>((a, b) -> b - a);// Max heap where parent is largest 5>3>2>1
        PriorityQueue<Integer> hi_min = new PriorityQueue<>(); // Min heap where parent is smallest 6>7>9>11

        for (int p = 0; p < arr.length; p++) {
            int number = arr[p];

            // keep add number in lo or hi PQ
            if (lo_mx.isEmpty() || number < lo_mx.peek()) {
                lo_mx.add(number);
            } else {
                hi_min.add(number);
            }
            //keep balancing

            if (lo_mx.size() > hi_min.size()) {
                int val = lo_mx.poll(); // Vimp : rememeber it polls biggest from Max heap and send to Min heap
                hi_min.add(val);
            }
            if (lo_mx.size() < hi_min.size()) {
                int val = hi_min.poll();// Vimp : rememeber . it polls smallest from Min Heap and send to Max heap
                lo_mx.add(val);
            }

            // calc median

            if (lo_mx.size() != hi_min.size()) {//i.e. ODD AND N/2 is median
                System.out.println("Median is " + lo_mx.peek());
            } else {
                //i.e. Even i.e n-1/2 + n/2 is the median\
                // for PQ both parents/2 goes for anser
                var d = (double) lo_mx.peek() + (double) hi_min.peek();
                var v = d / 2;
                System.out.println("Median is " + v);

            }
        }

    }

    public boolean HandOfStraights(int[] arr, int groupSize) {

        // if not having enough cards.
        if (arr.length % groupSize != 0) return false;

        PriorityQueue<Integer> sortedCard = new PriorityQueue<>();// ascending sorted heap w.o worry about min / max

        //sort cards.
        for (int p = 0; p < arr.length; p++) {
            int card = arr[p];
            sortedCard.add(card);
        }

        while (!sortedCard.isEmpty()) {
            int firstCardToStart = sortedCard.peek();
            //now find next consecutive cards of group size
            // say we have car 193 and groupsize = 4 then
            // we need 193, 194, 195, 196
            //for below it do the job as it < 193 + 4 = 197
            for (int card = firstCardToStart; card < firstCardToStart + groupSize; card++) {
                //now we keep remove cards from heap assuming we gets group created for those card
                // if we can  not find then its false. we do not have consecutive card.
                //keep doing till sorted card is empty.
                if (!sortedCard.remove(card)) {
                    return false;
                }
            }
        }
        return true;
    }


    ///----- NOT used any more.-------------

    public int[] arrayRankTransform(int[] arr) {

        int[] dummySortedArray = new int[arr.length];

        //fill dummy
        for (int i = 0; i < arr.length; i++) {
            dummySortedArray[i] = arr[i];
        }
        // sort it
        Arrays.sort(dummySortedArray);

        //get ranks of dummy
        var myMap = new HashMap<Integer, Integer>();
        int count = 1;
        for (int i = 0; i < dummySortedArray.length; i++) {
            if (!myMap.containsKey(dummySortedArray[i])) {
                myMap.put(dummySortedArray[i], count);
                count++;
            }
        }

        // overwrite org array with its rank
        for (int i = 0; i < arr.length; i++) {
            arr[i] = myMap.get(arr[i]);
        }
        return arr;
    }
}
