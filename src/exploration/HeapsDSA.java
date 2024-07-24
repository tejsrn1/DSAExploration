package exploration;


import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HeapsDSA {


    /**
     * This method finds the kth largest element in an array using a max heap.
     *
     * @param  arr The input array.
     * @param  k   The index to find.
     * @return The kth largest element in the array.
     * <p>
     * Example:
     * Input: arr = [3,2,1,5,6,4], k = 2
     * Output: 5
     */
    public int kth_Largest_MaxHeap(int[] arr, int k) {
        // Use a priority queue to implement a max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all elements to the max heap
        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
        }

        // Remove the top k-1 elements from the heap
        int z = k - 1;
        while (z > 0) {
            maxHeap.remove();
            z--;
        }

        // The top element of the heap is the kth largest element
        int res = maxHeap.peek();
        return res;
    }

    /**
     * This method finds the kth smallest element in an array using a min heap.
     *
     * @param  arr The input array.
     * @param  k   The index to find.
     * @return The kth smallest element in the array.
     * <p>
     * Example:
     * Input: arr = [3,2,1,5,6,4], k = 2
     * Output: 2
     */
    public int kth_Smallest_MinHeap(int[] arr, int k) {
        // Use a priority queue to implement a min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all elements to the min heap
        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i]);
        }

        // Remove the top k-1 elements from the heap
        int z = k - 1;
        while (z > 0) {
            minHeap.remove();
            z--;
        }

        // The top element of the heap is the kth smallest element
        int res = minHeap.peek();
        return res;
    }

    /**
     * This method assigns a rank to each unique element in an array.
     * The rank is the position the element would have if the array were sorted.
     *
     * @param  arr The input array.
     * @return The array of ranks.
     * <p>
     * Example:
     * Input: arr = [40,10,20,30]
     * Output: [4,1,2,3]
     */
    public int[] arrayRankTransform2(int[] arr) {
        int[] res = new int[arr.length];
        int rank = 1;

        // Use a priority queue to sort the elements
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Add all elements to the priority queue
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        // Assign a rank to each unique element
        HashMap<Integer, Integer> mp = new HashMap<>();
        while (!pq.isEmpty()) {
            int item = pq.poll();
            if (!mp.containsKey(item)) {
                mp.put(item, rank);
                rank++;
            }
        }

        // Replace each element in the array with its rank
        for (int i = 0; i < arr.length; i++) {
            int rk = mp.get(arr[i]);
            res[i] = rk;
        }

        return res;
    }

    /**
     * This method returns the k most frequent elements in an array.
     *
     * @param  arr The input array.
     * @param  k   The number of elements to return.
     * @return The k most frequent elements in the array.
     * <p>
     * Example:
     * Input: arr = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     */
    public int[] topKFrequent(int[] arr, int k) {
        if (k > arr.length) return null;

        // Count the frequency of each element
        var myMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            myMap.put(key, myMap.getOrDefault(key, 0) + 1);
        }

        // Use a priority queue to implement a min heap based on the frequencies
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> myMap.get(a) - myMap.get(b));

        // Add each element to the heap
        for (int n : myMap.keySet()) {
            pq.add(n);

            // If the size of the heap is greater than k, remove the least frequent element
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // The remaining elements in the heap are the k most frequent elements
        int[] topK = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            topK[i] = pq.poll();
        }

        return topK;
    }


    /**
     * This method calculates the least interval time to finish all tasks.
     *
     * @param  task    Array of tasks represented as characters.
     * @param  waiting The waiting time for each task.
     * @return The minimum total time to finish all tasks.
     * <p>
     * Example:
     * Input: task = ['A','C','A','B','D','B','A','A'], waiting = 2
     * Output: 8
     * Explanation: The tasks can be scheduled as follows - A C B A D B A A.
     * The total time taken to finish all tasks is 8 units.
     */
    public int leastInterval_TaskScheduler_1(char[] task, int waiting) {
        int[] char_Freq = new int[26];
        for (char tsk : task) {
            char_Freq[tsk - 'A']++;
        }
        Arrays.sort(char_Freq);

        int maxFreq_OfChar = char_Freq[25];
        int maxFreq_OfChar_ForIdleTiming = maxFreq_OfChar - 1;
        int idleTime = maxFreq_OfChar_ForIdleTiming * waiting;

        for (int i = 24; i >= 0; i--) {
            idleTime -= Math.min(char_Freq[i], maxFreq_OfChar_ForIdleTiming);
        }

        int res = task.length + Math.max(idleTime, 0);
        return res;
    }

    /**
     * This method calculates the median of an array of integers.
     *
     * @param  arr Array of integers.
     *            <p>
     *            Example:
     *            Input: arr = [41, 35, 62, 5, 97, 108]
     *            Output: Prints the median of the array to the console.
     */
    public void add_find_Median(int[] arr) {
        PriorityQueue<Integer> lo_mx = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> hi_min = new PriorityQueue<>();

        for (int p = 0; p < arr.length; p++) {
            int number = arr[p];

            if (lo_mx.isEmpty() || number < lo_mx.peek()) {
                lo_mx.add(number);
            } else {
                hi_min.add(number);
            }

            if (lo_mx.size() > hi_min.size()) {
                int val = lo_mx.poll();
                hi_min.add(val);
            }
            if (lo_mx.size() < hi_min.size()) {
                int val = hi_min.poll();
                lo_mx.add(val);
            }

            if (lo_mx.size() != hi_min.size()) {
                System.out.println("Median is " + lo_mx.peek());
            } else {
                var d = (double) lo_mx.peek() + (double) hi_min.peek();
                var v = d / 2;
                System.out.println("Median is " + v);
            }
        }
    }

    /**
     * This method checks if it's possible to rearrange the deck such that each group of size groupSize is consecutive.
     *
     * @param  arr       Array of integers representing the deck of cards.
     * @param  groupSize The size of each group of cards.
     * @return True if it's possible to rearrange the deck, false otherwise.
     * <p>
     * Example:
     * Input: arr = [1,2,3,6,2,3,4,7,8], groupSize = 3
     * Output: True
     * Explanation : The deck can be rearranged as [1,2,3],[2,3,4],[6,7,8].
     */
    public boolean HandOfStraights(int[] arr, int groupSize) {
        if (arr.length % groupSize != 0) return false;

        PriorityQueue<Integer> sortedCard = new PriorityQueue<>();

        for (int p = 0; p < arr.length; p++) {
            int card = arr[p];
            sortedCard.add(card);
        }

        while (!sortedCard.isEmpty()) {
            int firstCardToStart = sortedCard.peek();
            for (int card = firstCardToStart; card < firstCardToStart + groupSize; card++) {
                if (!sortedCard.remove(card)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * This method assigns a rank to each unique element in an array.
     * The rank is the position the element would have if the array were sorted.
     *
     * @param  arr The input array.
     * @return The array of ranks.
     * <p>
     * Example:
     * Input: arr = [40,10,20,30]
     * Output: [4,1,2,3]
     */
    public int[] arrayRankTransform(int[] arr) {
        int[] dummySortedArray = new int[arr.length];

        // Copy the original array to a dummy array
        for (int i = 0; i < arr.length; i++) {
            dummySortedArray[i] = arr[i];
        }

        // Sort the dummy array
        Arrays.sort(dummySortedArray);

        // Assign a rank to each unique element in the dummy array
        HashMap<Integer, Integer> myMap = new HashMap<>();
        int count = 1;
        for (int i = 0; i < dummySortedArray.length; i++) {
            if (!myMap.containsKey(dummySortedArray[i])) {
                myMap.put(dummySortedArray[i], count);
                count++;
            }
        }

        // Replace each element in the original array with its rank
        for (int i = 0; i < arr.length; i++) {
            arr[i] = myMap.get(arr[i]);
        }

        return arr;
    }
}
