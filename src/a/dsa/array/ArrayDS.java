package a.dsa.array;

import java.util.*;


public class ArrayDS {


    /**
     * This method finds and returns the largest element in an array.
     *
     * @param inputArray An array of integers.
     * @return The largest integer in the array.
     * <p>
     * Example:
     * int[] array = {1, 2, 3, 4, 5};
     * int largest = findLargestElement(array);
     * System.out.println(largest); // Prints: 5
     */
    public int findLargestElement(int[] inputArray) {
        int maxVal = Integer.MIN_VALUE; // Initialize with the smallest possible integer
        for (int i = 0; i < inputArray.length; i++) {
            maxVal = Math.max(maxVal, inputArray[i]); // Update maxVal if a larger value is found
        }
        return maxVal;
    }

    /**
     * This method finds and returns the second largest or second smallest element in an array.
     *
     * @param inputArray  An array of integers.
     * @param returnSmall A boolean flag to decide whether to return the second smallest (if true) or second largest (if false) element.
     * @return The second smallest or second largest integer in the array. Returns -1 if the array has less than 2 elements.
     * <p>
     * Example:
     * int[] array = {1, 2, 3, 4, 5};
     * int secondLargest = findSecondLargestElement(array, false);
     * System.out.println(secondLargest); // Prints: 4
     * <p>
     * int secondSmallest = findSecondLargestElement(array, true);
     * System.out.println(secondSmallest); // Prints: 2
     */
    public int findSecondLargestElement(int[] inputArray, boolean returnSmall) {
        if (inputArray.length < 2) {
            return -1; // Array must have at least 2 numbers
        }
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < inputArray.length; i++) {
            // Update smallest and second smallest
            if (inputArray[i] < smallest) {
                secondSmallest = smallest; // Update second smallest before updating smallest
                smallest = inputArray[i];
            } else if (inputArray[i] < secondSmallest) {
                secondSmallest = inputArray[i];
            }

            // Update largest and second largest
            if (inputArray[i] > largest) {
                secondLargest = largest; // Update second largest before updating largest
                largest = inputArray[i];
            } else if (inputArray[i] > secondLargest && inputArray[i] != largest) {
                secondLargest = inputArray[i];
            }
        }
        return returnSmall ? secondSmallest : secondLargest;
    }


    /**
     * Checks if the given array is sorted and then rotated.
     * A sorted array is one that is in increasing order. To determine if it's sorted and then rotated,
     * we allow for only one violation of the increasing order.
     *
     * @param arr The input array to check.
     * @return true if the array is sorted and rotated, false otherwise.
     * <p>
     * Example 1:
     * Input: [4, 5, 6, 7, 1, 2, 3]
     * Output: true
     * Explanation: The array is sorted and rotated at index 3.
     * <p>
     * Example 2:
     * Input: [4, 5, 7, 6, 1, 2, 3]
     * Output: false
     * Explanation: The array has two violations of the increasing order at indices 2 and 3.
     */
    public boolean isSortedAndRotated(int[] arr) {
        int violationCount = 0;
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            // When index goes beyond len, mod will restart from 0
            int nextIndex = (i + 1) % len;
            // When ascending rule breaks
            if (arr[i] > arr[nextIndex]) {
                violationCount++;
            }
        }

        // If there are more than one violations, the array is not sorted and rotated
        return violationCount > 1 ? false : true;
    }


    /**
     * Removes duplicates in a sorted array and returns the new length.
     *
     * @param arr The input array from which duplicates are to be removed.
     * @return The length of the array after removing duplicates.
     * <p>
     * Example:
     * Input: [1, 1, 2]
     * Output: 2
     * Explanation: After removing the duplicate "1", the array becomes [1, 2] with length 2.
     */
    public int removeDuplicatesInSortedArray(int[] arr) {
        int uniqueElementIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[uniqueElementIndex]) {
                uniqueElementIndex++;
                arr[uniqueElementIndex] = arr[i];
            }
        }
        return uniqueElementIndex + 1; // We need to send length until good array.
    }

    /**
     * Rotates an array to the left and right by one position.
     * <p>
     * Example:
     * Input: [1, 2, 3, 4, 5]
     * Output (Left rotation): [2, 3, 4, 5, 1]
     * Output (Right rotation): [5, 1, 2, 3, 4]
     */
    public void rotateArrayRegularWay() {
        int[] arr = {1, 2, 3, 4, 5};

        // Left rotation
        int firstElement = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = firstElement;

        // Right rotation
        int[] arr2 = {1, 2, 3, 4, 5};
        int lastElement = arr2[arr2.length - 1];
        for (int i = arr2.length - 1; i > 0; i--) {
            arr2[i] = arr2[i - 1];
        }
        arr2[0] = lastElement;
    }


    /**
     * Rotates an array to the left and right by a given order.
     *
     * @param rotationOrder The number of positions to rotate the array.
     *                      <p>
     *                      Example:
     *                      Input: [1, 2, 3, 4, 5, 6, 7], order = 2
     *                      Output (Left rotation): [3, 4, 5, 6, 7, 1, 2]
     *                      Output (Right rotation): [6, 7, 1, 2, 3, 4, 5]
     */
    public void rotateArrayReversalWay(int rotationOrder) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        rotationOrder = rotationOrder % arr.length; // This is to make sure when order > len then order resets to 1

        // Left rotation: Rotate array 3 times.
        reverseArray(arr, 0, rotationOrder - 1);
        reverseArray(arr, rotationOrder, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);

        // Right rotation
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        reverseArray(arr2, arr2.length - rotationOrder, arr2.length - 1);
        reverseArray(arr2, 0, arr2.length - rotationOrder - 1);
        reverseArray(arr2, 0, arr2.length - 1);
    }

    /**
     * Reverses the elements in an array from a start index to an end index.
     *
     * @param arr   The input array to reverse.
     * @param start The start index for the reversal.
     * @param end   The end index for the reversal.
     *              <p>
     *              Example:
     *              Input: [1, 2, 3, 4, 5], start = 1, end = 3
     *              Output: [1, 4, 3, 2, 5]
     *              Explanation: The elements at indices 1 to 3 are reversed.
     */
    private static void reverseArray(int[] arr, int start, int end) {
        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }


    public void zerosToEnd(int[] inputArray) {
        if (inputArray.length <= 1) { // at least 2 element required.
            return;
        }

        int ZPointer = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] != 0) {
                inputArray[ZPointer] = inputArray[i];
                ZPointer++;
            }
        }
        while (ZPointer < inputArray.length) {
            inputArray[ZPointer] = 0;
            ZPointer++;
        }

    }

    public int[] FindUnion_Sorted(int[] arr1, int[] arr2, int n, int m) {

        /*Idea : Union means common and distinct elements in the two arrays
        arr1[] = {1,2,3,4,5}
        arr2[] = {2,3,4,4,5}

        commons are 2 3 4 5
        distinct is 1

        we can use ArrayList to avoid trailing zero incase there are duplicates.
         only works for ordered one. not for unordered.
         for unorderd we can use Set*/

        var first = 0;
        var second = 0;
        var resArray = new int[m + n];
        var resP = 0;
        while (first < n && second < m) {

            //resArray[resP - 1] != arr1[first] to handle duplicate in same array
            // eg. arr2 has  1,2,2,2,3,3,3,3,4
            if (arr1[first] == arr2[second]) {
                if (resP == 0 || resArray[resP - 1] != arr1[first]) {
                    resArray[resP] = arr1[first];
                    resP++;
                }
                first++;
                second++;

            } else if (arr1[first] < arr2[second]) {
                if (resP == 0 || resArray[resP - 1] != arr1[first]) {
                    resArray[resP] = arr1[first];
                    resP++;
                }
                first++;

            } else if (arr1[first] > arr2[second]) {
                if (resP == 0 || resArray[resP - 1] != arr2[second]) {
                    resArray[resP] = arr2[second];
                    resP++;
                }
                second++;
            }

        }

        while (first < n) {
            resArray[resP] = arr1[first];
            first++;
            resP++;
        }
        while (second < m) {
            resArray[resP] = arr2[second];
            second++;
            resP++;
        }
        return resArray;
    }

    public ArrayList<Integer> FindUnion_UNSorted(int[] arr1, int[] arr2, int n, int m) {

        /*
        Idea :
        Condition is , elements within it array [] should be sorted despite duplicates
        Set will add all unique from first [] then second [] and that would do .
        */

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> reslist = new ArrayList<>();

        var first = 0;

        while (first < n) {
            set.add(arr1[first]);
            first++;
        }

        var second = 0;
        while (second < m) {
            set.add(arr2[second]);
            second++;
        }

        var setIterator = set.iterator();
        while (setIterator.hasNext()) {
            reslist.add(setIterator.next());
        }

        return reslist;
    }

    public int[] FindUnion_Intersection(int[] arr1, int[] arr2, int n, int m) {

        //Idea : Only pick which are common in both array and also duplicate allowed.

        var first = 0;
        var second = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        var resLen = n <= m ? n : m;
        var resArray = new int[resLen];
        var resP = 0;

        while (first < n && second < m) {

            if (arr1[first] == arr2[second]) {
                if (resP == 0 || resArray[resP - 1] != arr1[first]) {
                    resArray[resP] = arr1[first];
                    resP++;
                }
                first++;
                second++;

            } else if (arr1[first] < arr2[second]) {
                first++;

            } else if (arr1[first] > arr2[second]) {
                second++;
            }

        }
        return resArray;
    }

    public int find_missing_ONLY_BF(int[] arr) {

        /* Idea : Other solution (find_missing_repeating_NOT_Work_For_MajorityCases)
            may not work for e..g like 3,0, 1 and for all kind of numbers
            below works.*/

        Arrays.sort(arr);//sort first

        int len = arr.length;

        //Ensure first number i 0 and last is = len of array
        if (arr[0] != 0) {
            return 0;
        }
        if (arr[len - 1] != len) {
            return len;
        }
        // now search in between mssing

        for (int p = 1; p < len; p++) {
            int expectedNum = arr[p - 1] + 1;
            if (arr[p] != expectedNum) {
                return expectedNum;
            }
        }
        return -1;

    }

    public int find_missing_ONLY_Optimized(int[] arr) {

        /* Idea : Add space to avoid sorting*/

        int len = arr.length;
        var set = new HashSet<Integer>();

        //first add in Set so no sorting and O 1 look up
        for (int p = 0; p < len; p++) {
            set.add(arr[p]);
        }

        //now look for 0 to Len and find missing
        for (int p = 0; p <= len; p++) {
            if (!set.contains(p)) {
                return p;
            }
        }
        return -1;

    }

    public int findMaxConsecutiveOnes(int[] arr) {

        int len = arr.length;
        int maxOne = 0;
        int counter = 0;

        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                counter = 0;
            } else {
                counter++;
                maxOne = Math.max(maxOne, counter);
            }
        }
        return maxOne;
    }

    public int Find_Single_Element(int[] arr) {

        var mp = new HashMap<Integer, Integer>();
        for (int p = 0; p < arr.length; p++) {

            if (!mp.containsKey(arr[p])) {
                mp.put(arr[p], 1);
            } else {
                var val = mp.get(arr[p]);
                mp.put(arr[p], ++val);
            }
        }

        for (Map.Entry<Integer, Integer> kv : mp.entrySet()) {
            if (kv.getValue() == 1) {
                return kv.getKey();
            }
        }

        return -1;
    }


    public int longestSubArrWithSumK_BF(int[] arr, int K) {

        /* Idea : There are 2 solutions one here is best when 0, +ve and -ve values are present
         but not best when only +ve and 0 presents then 2 pointer is perfect sol.*/

        int maxLen = 0;
        int sum = 0;
        var map = new HashMap<Integer, Integer>();

        for (int p = 0; p < arr.length; p++) {

            sum = sum + arr[p];

            //keep updating max lend when sum matched.
            if (sum == K) {
                var l = p + 1;
                maxLen = Math.max(l, maxLen);
            }

            // to get more possible len when more than one combination makes K
            var remSum = sum - K;

            // say 20 - 10 = 10 above then
            // 10 was prestored @ idx 2 will be found
            // that will give r = 4-2= 2 length.
            if (map.containsKey(remSum)) {
                var remSumIdx = map.get(remSum);
                var r = p - remSumIdx;

                maxLen = Math.max(r, maxLen);
            }

            if (!map.containsKey(sum)) {
                map.put(sum, p);
                // store running sum and index
                // 2        = > 0
                // 2+3 = 5  = > 1
                // 5+5 =10  = > 2
                //10+1 =11  = > 3
                //11+9 =20  = > 4
            }
        }
        for (Map.Entry mp : map.entrySet()) {
            System.out.println(mp.getKey() + " => " + mp.getValue());
        }

        return maxLen;
    }

    public int longestSubArrWithSum_K_TwoPointer(int[] arr, int K) {

         /* Idea : There are 2 solutions one here is best when 0, and + values are present
         but not best when all +, - and 0 presents.*/


        int maxLen = 0;
        int sum = arr[0];// start with first ele

        int left = 0;
        int right = 0;

        while (right < arr.length) {

            while (sum > K && left <= right) { // move left to right by deducting left elem.
                sum = sum - arr[left];
                left++;
            }

            if (sum == K) {
                maxLen = Math.max(maxLen, right - left + 1);// +1 bcs we look for length
            }

            right++;
            if (right < arr.length) { // to avoid index out bound
                sum = sum + arr[right];
            }
        }

        return maxLen;
    }

    public boolean continuous_Good_SubArrWithSum_K(int[] arr, int K) {

        /* Idea : Same as Continuous Sum but with multiplier of K
         * i.e. Remainder is 0 when any number is  divided by K  called that number is multiplier of K
         * e.g. k = 6 and num = 42 bcs 42/6 =7 i.e. 6 * 7 =  42
         * */

        int runningSum = 0;
        var map = new HashMap<Integer, Integer>();

        for (int p = 0; p < arr.length; p++) {

            runningSum = runningSum + arr[p];

            //apply formula to find multiplier or not
            // assign as running sum bcs there is no such target instead there is mulitplier
            runningSum = runningSum % K;

            //this when len is at least 2 and sum after mod % found multiplier of k.
            if (runningSum == 0 && p > 0) {
                return true;
            }

            /*
                A [] 23 2 6 4
                %6    Running sum
                23    5
                7     1
                7     1 <- Here it matches 1 len ref array it has 6
                5     5 <- Here it matches >1 len ref array 0 index to last index
                Above shows when storing % as running sum and if it occures again it shows elements between them
                has sum which is mulitplier of K

            * */
            //when found previously stored running sum just check len
            if (map.containsKey(runningSum)) {
                var remSumIdx = map.get(runningSum);
                var len = p - remSumIdx;
                if (len > 1) { //need at least 2 len
                    return true;
                }
            }

            // store running sum
            if (!map.containsKey(runningSum)) {
                map.put(runningSum, p);
            }
        }

        return false;
    }

    public int MINSubArrWithSum_K_TwoPointer(int[] arr, int K) {

        /* Idea : Solutions one here is best when 0, and + values are present
         but not best when all +, - and 0 presents.*/

        int minLen = Integer.MAX_VALUE;
        int sum = arr[0];// start with first ele

        int left = 0;
        int right = 0;

        while (right < arr.length) {

            //just combined  sum ==k and sum > k unlike finding max len. its same thing no change in logic.
            while (sum >= K && left <= right) {
                minLen = Math.min(minLen, right - left + 1);// +1 bcs we look for length
                sum = sum - arr[left];
                left++;
            }

            right++;
            if (right < arr.length) { // to avoid index out bound
                sum = sum + arr[right];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public int[] Find_Two_Sum(int[] arr, int target) {

      /*Idea : We could use hashmap and find diff and see if it exists in map and if exists then
       return its index and current index. else keep storing values and index

         Target sum 12, ==> 2, 6, 5, 8, 10
          // store value  and index
                // 2        = > 0 // Diff 10 but not exists yet in map
                // 6        = > 1 // Diff 6
                // 5        = > 2 // Diff 7
                // 8        = > 3 // Diff 4
                // 10       = > 4 // Diff 2 ..YES found @ 0 idx
                return 4 and 0 index.

        .....Below solution is based on 2 Pointer.....
            Using 2 pointer for O1 space complexity.
            first sort and then use pointer
      */


        Arrays.sort(arr);

        var resA = new int[2];

        int left = 0, right = arr.length - 1;
        int sum = 0;

        while (left < right) {

            sum = arr[left] + arr[right];
            if (sum == target) {
                resA[0] = left;
                resA[1] = right;
                return resA;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }

        }

        return resA;

    }

    public int[] Find_Two_Sum_HashMap(int[] arr, int target) {

      /*Idea : We could use hashmap and find diff and see if it exists in map and if exists then
       return its index and current index. else keep storing values and index

         Target sum 12, ==> 2, 6, 5, 8, 10
          // store value  and index
                // 2        = > 0 // Diff 10 but not exists yet in map
                // 6        = > 1 // Diff 6
                // 5        = > 2 // Diff 7
                // 8        = > 3 // Diff 4
                // 10       = > 4 // Diff 2 ..YES found @ 0 idx
                return 4 and 2 index.

        Note : Use Map when position of index for particular sum or value needs to know during
        traversal and since it is look up Map is useful as we do not have any other pointer to look back so  map is useful.

        Also when some kind of continuity require like sub array sum then store running sum or value in each index as it present something continue.

      */

        var resA = new int[2];

        int left = 0, right = arr.length - 1;
        var hash = new HashMap<Integer, Integer>();

        //this is same as for loop used for another e.g. where running sum stored in map.
        while (left <= right) {

            int diff = target - arr[left];

            if (!hash.containsKey(diff)) {
                hash.put(arr[left], left);
            } else {
                int diffIdx = hash.get(diff);
                return new int[]{diffIdx, left};
            }
            left++;

        }

        return resA;

    }

    public void sortColors(int[] nums) {
/*
        Idea: count each occurrence of 0 1 and 2 digits since its fixed
         0, 1, and 2 to represent the color red, white, and blue,*/

        int red = 0, white = 0, blue = 0;

        for (int x = 0; x < nums.length; x++) {
            if (nums[x] == 0) {
                red++;
            }
            if (nums[x] == 1) {
                white++;
            }
            if (nums[x] == 2) {
                blue++;
            }
        }
        int counter = 0;
        while (red > 0) {
            nums[counter] = 0;
            counter++;
            red--;
        }
        while (white > 0) {
            nums[counter] = 1;
            counter++;
            white--;
        }
        while (blue > 0) {
            nums[counter] = 2;
            counter++;
            blue--;
        }
    }

    public void sortColors_DutchFlag_algo(int[] nums) {

        /*Idea: there are 3 pointers as 3 colors in Dutch flag
         Low , Mid and High pointers.
         arr[0 .... Low-1] contains 0
         arr[Low ... Mid-1] contains 1.

         arr[Mid .. High]  contains unsorted portion

         arr[High+1 ... Len -1 ] contains 2

         Idea to swap all values when mid hit 0, 1 and 2 and leave unsorted portion empty
         i.e. all sorted.

        ****In Simple term , Mid can only have 1 if it found 0 swap with low and if 2 then swap with right***

         /
         */


        int low = 0, mid = 0, high = nums.length - 1;

        for (int x = 0; x < nums.length; x++) {
            if (nums[mid] == 0) {

                //swap low and mid and increase
                var temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;


            } else if (nums[mid] == 1) {
                // no swap as 1 is at right place in middle just increase
                mid++;
            } else { // for nums[mid] == 2
                //swap mid and high and send greater no to right or high side and decrease only high
                var temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

    public int majorityElement_N2(int[] arr) {

        var leN = arr.length;
        var myMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < leN; i++) {
            var ele = arr[i];
            if (!myMap.containsKey(ele)) {
                myMap.put(ele, 1); // add count for appearance
            } else {
                myMap.put(ele, myMap.get(ele) + 1);// increment of counter.
            }
        }

        for (var d : myMap.entrySet()) {
            if (d.getValue() > leN / 2) {
                return d.getKey();
            }
        }

        return -1;
    }

    public int majorityElement_N2_MooreVoting(int[] arr) {

        /*Idea

         **Remember : here not majority count of element but count of element > (GRT THN) len of Array

         Use Moore algo and the way it works is
         Traverse array and keep counter and idea is equal appears elements
         will cancel each other and last element present in array is the
         majority element.

         If array can have other element in lasp position and our major element is
         somewhere inside then we just need to re iterate and get that


        * */
        var leN = arr.length;
        var counter = 0;
        var element = -1;

        for (int i = 0; i < leN; i++) {
            if (counter == 0) { // for first index and when ele cancel each other on the way
                element = arr[i];
                counter++;

            } else if (arr[i] == element) {
                counter++;
            } else {
                counter--;
            }

        }

        //when last ele is not our Majority ele e.g. {2, 2, 1, 1, 1, 3, 3};
        // else above loop guarantee result.

        int ct1 = 0;

        for (int i = 0; i < leN; i++) {
            if (arr[i] == element) {
                ct1++;
            }
        }
        // claimed elem must be > hal of len else -1
        if (ct1 > (leN / 2)) {
            return element;
        }

        return -1;
    }

    public int Max_SubArray_SUM(int[] arr) {

        /*Idea:
        1. Do 2 loop and find max and also stores index start / end for print.
            Run - O N 2 and space ON

        2. Use Kadanes Algorithm
         - Iterate array and only consider starting sub array when sum is 0
         - ending when max elment found
         - reset sum = 0 when sum < 0 i.e. coutner -ve values.
         */

        var maxSum = Integer.MIN_VALUE;
        var runningSum = 0;
        var startingPoint = 0;
        int started = -1, ended = -1; // -1 bcs may be none > 0 in array

        for (int i = 0; i < arr.length; i++) {

            if (runningSum == 0) {
                startingPoint = i; //get start point
            }
            runningSum = runningSum + arr[i]; //keep adding

            //when hit max capture started as starting point from whre it actually started
            // ended when it reached max.
            if (runningSum > maxSum) {
                maxSum = runningSum;
                started = startingPoint; // bcs we need start keep going incase more max on the subarray
                ended = i;
            }

            if (runningSum < 0) {
                runningSum = 0; //reset to zero
            }
        }

        if (maxSum < 0) { // when none +ve in Array
            return 0;
        }

        //Print max sub array
        System.out.println("Sub Array Len " + (ended - started + 1));
        for (int x = started; x <= ended; x++) {
            System.out.print(arr[x] + " ");
        }
        System.out.println();
        return maxSum;

    }

    public int[] rearrangeArray(int[] arr) {

        /*Idea:
        1. Do not work for odd no of digits i.e. must be even + and - digits
        2. Iterate and maintain 2 pointer for +and - and keep checking array for > < 0 and
        pre new array
         */

        int[] resAr = new int[arr.length];

        int posIdx = 0;
        int negIdx = 1; //bcs first is + and then alternate

        for (int p = 0; p < arr.length; p++) {
            if (arr[p] > 0) {
                resAr[posIdx] = arr[p];
                posIdx = posIdx + 2;

            } else {
                resAr[negIdx] = arr[p];
                negIdx = negIdx + 2;
            }
        }

        return resAr;
    }

    public int longest_Successive_Elements(int[] arr) {

        int maxLen = 1;
        int count = 1;
        int arrLen = arr.length;

        //first sort
        Arrays.sort(arr);
        //now iterate over
        for (int i = 0; i < arrLen; i++) {
            // basically check if next element is in seq or not if not rest counter.
            if (i + 1 < arrLen && arr[i] + 1 == arr[i + 1]) {
                count++;
                maxLen = Math.max(maxLen, count);
            } else {
                count = 1;
            }
        }
        return maxLen;
    }

    public int longest_Successive_Elements_With_HashSet(int[] arr) {

        int maxLen = 1;
        int count = 1;
        int arrLen = arr.length;
        HashSet<Integer> mySet = new HashSet<>();

        //first add all in hash set.
        for (int i = 0; i < arrLen; i++) {
            mySet.add(arr[i]);

        }

        //second see if next sequence is present or not for given item and count max.
        for (int item : mySet) {
            // to avoid re counting when prev value present as it will come back when reach to tha index.
            if (!mySet.contains(item - 1)) {
                while (mySet.contains(item + 1)) {
                    item = item + 1;
                    count = count + 1;
                    maxLen = Math.max(maxLen, count);
                }
                count = 1;
            }
        }

        return maxLen;
    }

    public int[] find_Leader(int[] arr) {

/*        Idea: Leader right side i.e. after Leader all num are smaller/
         i.e from right -> left any larger num that leader is also leader.*/

        int[] res = new int[arr.length];
        int resCounter = 0;

        int leader = arr[arr.length - 1]; // last ele always leader
        res[resCounter] = leader;
        resCounter++;

        for (int x = arr.length - 2; x >= 0; x--) {
            if (arr[x] > leader) {
                leader = arr[x];
                res[resCounter] = leader;
                resCounter++;
            }
        }

        return res;
    }

    public int Count_Total_SubArr_WithSum_K_HashMap(int arr[], int k) {

        /*Idea is to prep map with running sum and get count

         3  1  2  4 (A[])
         _  _  _  _
         0  1  2  3 (IDX)

           Runsum  - ctofSum - Idx
            0 -     1
            3 -     1         0
            4 -     1         1
            6 -     1         2  ( Diff 6-6= 0 found) so total counter will be 0 +1=1
            10-     1         3  ( Diff 10-4= 6 found) so counter 1+1 = 2;

        Note : It is still with original idea to use Map when working for some contiguous items like sub array
         and need to jump back. Here continuous sum with its count as we need count of total sub array with sum K.
         */


        Map<Integer, Integer> map = new HashMap<>();

        int runingSum = 0, runningCounter = 0;

        //whole idea is to work off diff so first encounter of K will diff 0
        // so that add 0 for that..
        map.put(0, 1);

        for (int i = 0; i < arr.length; i++) {

            runingSum += arr[i];

            int diff = runingSum - k;

            // if diff found get its count and add in to running counter.
            if (map.containsKey(diff)) {
                runningCounter += map.get(diff);
            }

            //check if previously stored any count get that too.
            int soFarCount = 0;
            if (map.containsKey(runingSum)) {
                soFarCount = map.get(runingSum);
            }

            //add running sum and its count
            //this count is so far i.e. if previously running sum was stored with some count
            // those count gets increased by + 1
            map.put(runingSum, soFarCount + 1);
        }
        return runningCounter;
    }


    public int[] majorityElements_N3(int[] arr) {

        var leN = arr.length;
        var myMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < leN; i++) {
            var ele = arr[i];
            if (!myMap.containsKey(ele)) {
                myMap.put(ele, 1); // add count for appearance
            } else {
                myMap.put(ele, myMap.get(ele) + 1);// increment of counter.
            }
        }

        var minOccurance = leN / 3 + 1; //+1 bcs dividing by odd value so consider ceiling
        //e.g 5/3 = 1 orrcurence ? no 1+1 = 2 min occurance.\

        var resArr = new int[leN / 3];
        var resC = 0;

        for (var d : myMap.entrySet()) {
            if (d.getValue() >= minOccurance) {
                resArr[resC] = d.getKey();
                resC++;
            }
        }

        return resArr;
    }

    public int[] majorityElement_N3_MooreVoting(int[] arr) {

          /*Idea
         Use Moore algo and the way it works is
         Traverse array and keep counter and idea is equal appears elements
         will cancel each other  BUT for N/3 Elements

         we will track 2 elements and return > N/ 3 occurrence.
         also when we hit either or ele  counter ++ else counter --

        * */
        var leN = arr.length;


        var ct1 = 0;
        var ele1 = Integer.MIN_VALUE;

        var ct2 = 0;
        var ele2 = Integer.MIN_VALUE;

        for (int i = 0; i < leN; i++) {
            if (ct1 == 0 && arr[i] != ele2) {// first ele here.
                ele1 = arr[i];
                ct1++;

            } else if (ct2 == 0 && arr[i] != ele1) {// second ele here.)
                ele2 = arr[i];
                ct2++;
            } else if (arr[i] == ele1) { // increase
                ct1++;
            } else if (arr[i] == ele2) { // increase
                ct2++;
            } else { // both reduced.
                ct1--;
                ct2--;
            }

        }


        // why one more bcs earlier we found ele1 and ele2 potential elements
        // but since array ended with other than ele1 and ele2 we just go over
        // and confirm the >  N/3
        var lenMinOccurrence = leN / 3 + 1; // odd divider
        var resArr = new int[leN / 3];
        var resC = 0;

        int finalCt1 = 0, finalCt2 = 0;
        for (int x = 0; x < leN; x++) {
            if (arr[x] == ele1) {
                finalCt1++;
            }
            if (arr[x] == ele2) {
                finalCt2++;
            }
        }

        if (finalCt1 >= lenMinOccurrence) {
            resArr[resC] = ele1;
            resC++;
        }
        if (finalCt2 >= lenMinOccurrence) {
            resArr[resC] = ele2;
            resC++;
        }

        return resArr;
    }

    public List<List<Integer>> triplet_3_Sum_to_Zer0_HashSet(int[] arr) {

        /*Idea:
         Use 2 hash set , one for storing 3 digit and second for storing triples combination
         and since its set it will avoid duplicate in both scenario.

        **/

        var resSet = new HashSet<List<Integer>>();

        for (int p = 0; p < arr.length; p++) {
            var setThird = new HashSet<Integer>(); // MUST as need fresh set for each new P.
            for (int q = p + 1; q < arr.length; q++) {

                var r = -(arr[p] + arr[q]); // -VE() to make subtraction = 0 e.g. -1 +2  = 1 but due to-Ve -1 as remeaning part to make 0

                if (setThird.contains(r)) { // found it
                    var tempList = new ArrayList<Integer>();
                    tempList.add(r);
                    tempList.add(arr[p]);
                    tempList.add(arr[q]);
                    tempList.sort(null);// null to pass no comparator.
                    // sorted list to avoid duplicate result in Set.
                    resSet.add(tempList);
                }
                // remember we dont have 3rd one
                // we just add second so that it can be used in future as thrid one.
                setThird.add(arr[q]);
            }

        }

        return new ArrayList<>(resSet);
    }

    public List<List<Integer>> triplet_3_Sum_to_Zer0_3_Pointer(int[] arr) {

        /*Idea:

         Sort Array first and then use 3 pointer for sum finding .
         to avoid duplicate chek prev element to see if its same and if yes skip it
         do this for all 3 pointers.
        **/

        Arrays.sort(arr);
        var resList = new ArrayList<List<Integer>>();
        var len = arr.length;

        for (int p = 0; p < arr.length; p++) {

            // skip duplicates for p
            if (p != 0 && arr[p] == arr[p - 1]) {
                continue;
            }

            int q = p + 1;
            int r = len - 1;

            while (q < r) {
                int sum = arr[p] + arr[q] + arr[r];

                if (sum < 0) {
                    q++;
                } else if (sum > 0) {
                    r--;
                } else {
                    //sum ==0
                    var templist = Arrays.asList(arr[p], arr[q], arr[r]);
                    resList.add(templist);
                    q++;
                    r--;

                    //now before q and r goes for checking new values
                    // skip duplicates

                    while (arr[q] == arr[q - 1] && q < r) {
                        q++;
                    }
                    while (arr[r] == arr[r + 1] && q < r) {
                        r--;
                    }
                }


            }

        }

        return resList;
    }

    public List<List<Integer>> Quads_4_Sum_to_Zero_4_Pointer(int[] arr, int target) {

  /*Idea:

        1. First way : run 3 loop  i j an k and use hashset to find diff instead of 4th loop
            Run ON3  and ON space

        2. Use 4 pointer similar to 3 sum
            i.e. 2 loop outer p, q and inside while loop for r, w
         Sort Array first and then use 4 pointer for sum finding .
         to avoid duplicate chek prev element to see if its same and if yes skip it
         do this for all 4 pointers.
        **/

        var resList = new ArrayList<List<Integer>>();
        Arrays.sort(arr);

        var len = arr.length;

        for (int w = 0; w < arr.length; w++) {

            // skip duplicates for w
            if (w != 0 && arr[w] == arr[w - 1]) {
                continue;
            }

            for (int p = w + 1; p < arr.length; p++) {

                // skip duplicates for p
                if (p > w + 1 && arr[p] == arr[p - 1]) {
                    continue;
                }

                int q = p + 1;
                int r = len - 1;

                while (q < r) {
                    int sum = arr[w] + arr[p] + arr[q] + arr[r];

                    if (sum < target) {
                        q++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        //sum ==0
                        var templist = Arrays.asList(arr[w], arr[p], arr[q], arr[r]);
                        resList.add(templist);
                        q++;
                        r--;

                        //now before q and r goes for checking new values
                        // skip duplicates

                        while (arr[q] == arr[q - 1] && q < r) {
                            q++;
                        }
                        while (arr[r] == arr[r + 1] && q < r) {
                            r--;
                        }
                    }


                }

            }
        }


        return resList;
    }

    public int MaxSubArrWithSum_Zero_Hashmap(int[] arr) {

        int maxLen = 0;
        int runningSum = 0;
        var map = new HashMap<Integer, Integer>();

        for (int p = 0; p < arr.length; p++) {

            runningSum = runningSum + arr[p];

            //keep updating max lend when sum matched.
            if (runningSum == 0) {
                var l = p + 1;
                maxLen = Math.max(l, maxLen);
            }

            if (map.containsKey(runningSum)) {
                var prevFoundSumIdx = map.get(runningSum);
                var r = p - prevFoundSumIdx;

                maxLen = Math.max(r, maxLen);
            } else {
                map.put(runningSum, p);
            }

        }
        for (Map.Entry mp : map.entrySet()) {
            System.out.println(mp.getKey() + " => " + mp.getValue());
        }

        return maxLen;
    }

    public void Merge_TwoSorted_Array_wO_extraSpace(int[] arr1, int[] arr2) {

       /* Idea: compare left and right array and start from last element of left with first element of right
         if left > right then swap
         and keep move both pointer
         last just sort two array.

         other way is create new array and fill back original array but this needs extra space.*/

        var left = arr1.length - 1;
        var right = 0;
        while (left >= 0 && right < arr2.length) {

            if (arr1[left] > arr2[right]) {

                //swap
                var t = arr1[left];
                arr1[left] = arr2[right];
                arr2[right] = t;
                left--;
                right++;
            } else {
                break; // bcs both array are sorted and once we do not find any large ele in left we are done.
            }

        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    public List<List<Integer>> mergeOverlappingIntervals_aka_MeetingRoom(int[][] arr) {

    /*    Arrays.sort(arr,new Comparator<int[]>()
        {
            public int compare(int []a , int []b){

                var diff = a[0] - b[0];
                return diff;
            }
        });*/

        // Lambda way

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        List<List<Integer>> resList = new ArrayList<>();

        for (int idx = 0; idx < arr.length; idx++) {

            if (resList.isEmpty()) {
                // 0 and 1 is fixed as its [] [0] and [] [1] regardless which index of main array
                var tlist = Arrays.asList(arr[idx][0], arr[idx][1]);

                resList.add(tlist);
            } else {
                var resSize = resList.size();
                var valList = resList.get(resSize - 1);
                var valueTocompare = valList.get(1); // as its ending value e.g. 1-3

                if (arr[idx][0] < valueTocompare) {
                    // i.e for 2-6 and 1-3 . 2 < 3 needs merging.
                    int newValtoSet = arr[idx][1]; //6
                    valList.set(1, newValtoSet);// updating ending value 1-3 => 1- 6
                } else {
                    var tlist = Arrays.asList(arr[idx][0], arr[idx][1]);
                    resList.add(tlist);
                }
            }
        }
        int[][] resA = new int[resList.size()][2];
        int ct = 0;
        for (var d : resList) {
            int[] dd = d.stream().mapToInt(c -> c).toArray();
            resA[ct][0] = dd[0];
            resA[ct][1] = dd[1];
            ct++;
        }
        return resList;

    }

    public int[] findMissingRepeatingNumbers(int[] arr) {
        var missed = -1;
        var repeat = -1;
        for (int idx = 0; idx < arr.length; idx++) {

            var lenVal = Math.abs(arr[idx]); // to avoid negative
            int targetIdx = lenVal - 1;


            if (arr[targetIdx] > 0) {
                arr[targetIdx] = -arr[targetIdx]; // make -ve
            } else {
                repeat = targetIdx + 1;
                break;
            }

        }
        for (int idx = 0; idx < arr.length; idx++) {

            if (arr[idx] > 0) {
                missed = idx + 1;
                break;
            }
        }
        int[] ans = {repeat, missed};
        return ans;


    }

    public int maxProduct_SubArray_Regular_BF(int[] arr) {

        int maxProduct = arr[0];

        for (int outer = 0; outer < arr.length; outer++) {
            int runningProduct = arr[outer];
            for (int inner = outer + 1; inner < arr.length; inner++) {

                //first check if prev max remain max result before
                // multiplying  next inner digit
                maxProduct = Math.max(maxProduct, runningProduct);
                runningProduct = runningProduct * arr[inner];

            }
            //check again before go for next outer element.
            maxProduct = Math.max(maxProduct, runningProduct);
        }

        return maxProduct;
    }

    public int maxProduct_SubArray_Regular_Algo(int[] arr) {

        /*Idea

        Go L -> R  and R -> L at same time and call pre and suff
        keep mulitiply pre digits and suff digits and max is the
        answer.

        if we encounter 0 then make pre or suff =1 and consider starting point
        of new array inside same array basicially reset pre or suff and keep going
        as explaind above.

        maintain outside max result when we need to rest pre or suff when encouter 0.

        * */

        int maxProduct = Integer.MIN_VALUE;
        int pref = 1, suff = 1;
        var len = arr.length;

        for (int k = 0; k < len; k++) {
            // reset so then rest subarray can start with non zero value else rest of all multiplication remain 0s.
            if (pref == 0) pref = 1;
            if (suff == 0) suff = 1;

            int pv = arr[k];// L - R
            int sv = arr[len - k - 1]; // R - L

            pref = pref * pv;
            suff = suff * sv;

            var maxPS = Math.max(pref, suff);
            maxProduct = Math.max(maxPS, maxProduct);

        }

        return maxProduct;
    }


    // Count inversion start .........................................................
    public int count_inversion(int[] givenArray) {

        /* Idea

        we need to pair like 5>4, 5>3 ,5>2 then 4>3 etc.
        easy way is two loop and simply check i > j and keep counter increase O N 2

        but for ON , Idea is say we have two sorted array

        1,6,9,10 .. 2,3,5
        here when 6> 2 i.e. all after 6 in array will be > 2 that makes total 3 pairs.

        but we do not have 2 array so lets divide and make it sort
        by using merge sorted array tech and while we merge when we see i > j just grab count
        and keep merging/sorting.
*/
        return MergeSort(givenArray, 0, givenArray.length - 1);
    }

    public int MergeSort(int[] givenArray, int start, int end) {

        int count = 0;
        //base to stop
        if (end <= start) {
            return count;
        }
        var mid = (start + end) / 2;
        count = count + MergeSort(givenArray, start, mid); //left
        count = count + MergeSort(givenArray, mid + 1, end); //right
        count = count + mergeArray(givenArray, start, end, mid);
        return count;
    }

    int mergeArray(int[] givenArray, int start, int end, int mid) {
        // temp array
        int[] tempA = new int[end - start + 1];
        var tempPointer = 0;

        //go left and right and compare and fill smaller value in temp Array
        var leftPointer = start;
        var rightPointer = mid + 1;

        int count = 0;
        while (leftPointer <= mid && rightPointer <= end) {

            if (givenArray[leftPointer] <= givenArray[rightPointer]) {
                tempA[tempPointer] = givenArray[leftPointer];
                tempPointer++;
                leftPointer++;
            } else {
                tempA[tempPointer] = givenArray[rightPointer];
                tempPointer++;
                rightPointer++;
                //just grab count now
                count += mid - leftPointer + 1;// left i > right j so calculating total remaining len as count
            }
        }

        //when left is not yet done
        while (leftPointer <= mid) {
            tempA[tempPointer] = givenArray[leftPointer];
            tempPointer++;
            leftPointer++;
        }

        //when right is not yet done
        while (rightPointer <= end) {
            tempA[tempPointer] = givenArray[rightPointer];
            tempPointer++;
            rightPointer++;
        }

        //now transfer sorted array in to original given array.

        for (int x = start; x <= end; x++) {
            // bcs it is based on given array index and it can be starting at say 9,10,11 etc.
            givenArray[x] = tempA[x - start];
        }

        return count; // just return it

    }


    // Count inversion end .........................................................


    // Count Reverse pair start .........................................................
    public int count_reversePair(int[] givenArray) {

        /* Idea

        we need to pair like 7>3, 6>2 etc.
        easy way is two loop and simply check i > 2* j and keep counter increase O N 2

        but for ON , Idea is say we have two sorted array

        1,6,9,10 .. 2,3,5
        here when 6> 2*3 i.e. all before 3 in array will be > 2 * j

        but we do not have 2 array so lets divide and make it sort
        by using merge sorted array tech but bdore we merge 2 arrays
        lets count reverse pari i < 2 j and gets count and then keep moving with sorting.

        NOTE : when hit large number > Max then just go for long casting e.g. below

        arr[left] > 2L * arr[right])
*/
        return MergeSort_count_reversePair(givenArray, 0, givenArray.length - 1);
    }

    public int MergeSort_count_reversePair(int[] givenArray, int start, int end) {

        int count = 0;
        //base to stop
        if (end <= start) {
            return count;
        }
        var mid = (start + end) / 2;
        count = count + MergeSort_count_reversePair(givenArray, start, mid); //left
        count = count + MergeSort_count_reversePair(givenArray, mid + 1, end); //right
        //calculate reverse count first
        count = count + countPairs(givenArray, start, end, mid);
        //keep doint regular
        mergeArray_count_reversePair(givenArray, start, end, mid);
        return count;
    }

    public int countPairs(int[] arr, int low, int high, int mid) {

   /*     int right = mid + 1;
        int cnt = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && arr[i] > 2 * arr[right]) right++;
            cnt += (right - (mid + 1));
        }
        return cnt;

        // Java loop tips :

        while(iterator.hasNext())
        System.out.println("next: " + iterator.next()); // executed in loop
        System.out.println("second line"); // executed after loop

        */


        int count = 0;
        int right = mid + 1;

        //left => 6, 13 ,15  mid & from mid+1 => 1, 2, 3, 4  to end is right

        for (int left = low; left <= mid; left++) {
            while (right <= high && arr[left] > 2L * arr[right]) {
                right++;
            }
            count += right - (mid + 1);// to get 6>1 =1 in while loop then 6>2 is 2 and keep.
        }
        return count;
    }

    void mergeArray_count_reversePair(int[] givenArray, int start, int end, int mid) {
        // temp array
        int[] tempA = new int[end - start + 1];
        var tempPointer = 0;

        //go left and right and compare and fill smaller value in temp Array
        var leftPointer = start;
        var rightPointer = mid + 1;

        while (leftPointer <= mid && rightPointer <= end) {

            if (givenArray[leftPointer] <= givenArray[rightPointer]) {
                tempA[tempPointer] = givenArray[leftPointer];
                tempPointer++;
                leftPointer++;
            } else {
                tempA[tempPointer] = givenArray[rightPointer];
                tempPointer++;
                rightPointer++;
            }
        }

        //when left is not yet done
        while (leftPointer <= mid) {
            tempA[tempPointer] = givenArray[leftPointer];
            tempPointer++;
            leftPointer++;
        }

        //when right is not yet done
        while (rightPointer <= end) {
            tempA[tempPointer] = givenArray[rightPointer];
            tempPointer++;
            rightPointer++;
        }

        //now transfer sorted array in to original given array.

        for (int x = start; x <= end; x++) {
            // bcs it is based on given array index and it can be starting at say 9,10,11 etc.
            givenArray[x] = tempA[x - start];
        }
    }


    // Count inversion end .........................................................
    public void zeroMatrix(int[][] givenMatrix, int rowLen, int colLen) {

        /*Idea
        make 2 array and mark row and col = 1 where we hit 0 in matrix
        that tells that whenever that row # or col# hit in matrix traversal we need to mark it 0.


         *
         * */

        int[] rowMark = new int[rowLen];
        int[] colMark = new int[colLen];

        // Mark
        for (int rw = 0; rw < givenMatrix.length; rw++) {
            for (int cl = 0; cl < givenMatrix[0].length; cl++) {
                if (givenMatrix[rw][cl] == 0) {
                    rowMark[rw] = 1;
                    colMark[cl] = 1;
                }
            }
        }

        // make zero
        for (int rw = 0; rw < givenMatrix.length; rw++) {
            for (int cl = 0; cl < givenMatrix[0].length; cl++) {

                if (colMark[cl] == 1 || rowMark[rw] == 1) {
                    givenMatrix[rw][cl] = 0;
                }
            }
        }
    }

    public void Rotate_90d_Matrix(int[][] givenMatrix) {

        /*Idea
        Transpose row to col i.e. make row to col and col to row
        then reverse each raw will do ..
        cl starts with rw must in the second loop for transform


        in Transpose its like o- - -
                              |
                              |
                              |
                then matrix goes from 3 x 3 => 2 x 2 for flip

                            0 - -
                            |
                            |
         *
         * */

        // Transpose or swap R <-> C
        for (int rw = 0; rw < givenMatrix.length; rw++) {
            for (int cl = rw; cl < givenMatrix[0].length; cl++) { // cl starts with rw must

                var temp = givenMatrix[rw][cl];
                givenMatrix[rw][cl] = givenMatrix[cl][rw];
                givenMatrix[cl][rw] = temp;
            }
        }

        // reverse row of matrix

        for (int rw = 0; rw < givenMatrix.length; rw++) {
            //just go till half and swap last <-> first element
            for (int cl = 0; cl < givenMatrix[0].length / 2; cl++) {

                var temp = givenMatrix[rw][cl];
                // here cl bcs we need to take 0 <-> 4 then 1<-> 3 col swap
                var lastindex = givenMatrix[0].length - 1 - cl;


                givenMatrix[rw][cl] = givenMatrix[rw][lastindex];
                givenMatrix[rw][lastindex] = temp;
            }
        }


    }


    public List<Integer> printSpiralMatrix(int[][] givenMatrix) {

        /*Idea
       Define Top , Bottom , Left and Right of matrix

       then Go L to R then T to bottom then R to L then Bottom to Top for sprial

        (T) 1  2  3 (R)   123 69 87  4  5
            4  5  6
        (L) 7  8  9 (B)

         * */

        var res = new ArrayList<Integer>();
        var top = 0;
        var left = 0;

        var right = givenMatrix[0].length - 1; // col len
        var bottom = givenMatrix.length - 1; // row len


        while (left <= right && top <= bottom) {

            // L R

            for (int i = left; i <= right; i++) {
                res.add(givenMatrix[top][i]);
            }
            top++; // finished given level.

            // T B

            for (int i = top; i <= bottom; i++) {
                res.add(givenMatrix[i][right]);
            }
            right--; // finished given level.

            // R L

            if (top <= bottom) { // make sure print happens with in matrix as matrix goes smaller
                for (int i = right; i >= left; i--) {
                    res.add(givenMatrix[bottom][i]);
                }
                bottom--; // finished given level.
            }

            // B T

            if (left <= right) { // make sure print happens with in matrix as matrix goes smaller
                for (int i = bottom; i >= top; i--) {
                    res.add(givenMatrix[i][left]);
                }
                left++; // finished given level.
            }
        }
        return res;
    }

    public void pascalTriangle() {

        /*Idea
        There are 3 variation being asked.

        1. Given Row and Col print whole pascal i.e. recall our pattern with new math

        2. Given Row and Col just give that ixj place answer

        3. Given Row just print that row within pascale triangle.

         * */

        int row = 5, colum = 5;
        int printrow = 5;
        int valrow = 5, valcol = 3;

        int[] preRes = new int[row + 1];
        int prC = 1;
        preRes[prC] = 1; // initialize

        int[] curRes = new int[row + 1];
        int curC = 1;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= i; j++) {

                int v = preRes[prC] + preRes[prC - 1];
                curRes[curC] = v;

                //variation 2
                if (i == valrow && j == valcol) {
                    System.out.println("V2 => " + curRes[curC]);
                }

                //move next
                prC++;
                curC++;
            }

            for (int p = 1; p < preRes.length; p++) {
                preRes[p] = curRes[p];

                //V1
                if (curRes[p] != 0) {
                    System.out.print(curRes[p] + " ");
                }

                //variation 3
//                if (i == printrow ) {
//                    System.out.print("V3 => ");
//                    System.out.print(curRes[p] + " ");
//                }
            }

            System.out.println();
            prC = 1;
            curC = 1;
        }

    }

    public List<List<Integer>> generate_Pascal_LeetCode(int row) {
        //this is same as above but tailored to leetcode.

        List<List<Integer>> reslist = new ArrayList<List<Integer>>();
        int[] preRes = new int[row + 1];
        int prC = 1;
        preRes[prC] = 1; // initialize

        int[] curRes = new int[row + 1];
        int curC = 1;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= i; j++) {

                int v = preRes[prC] + preRes[prC - 1];
                curRes[curC] = v;

                //move next
                prC++;
                curC++;
            }

            var lt = new ArrayList<Integer>();
            for (int p = 1; p < preRes.length; p++) {
                if (curRes[p] != 0) {
                    lt.add(curRes[p]);
                    preRes[p] = curRes[p];
                }
            }
            reslist.add(lt);
            prC = 1;
            curC = 1;
        }

        return reslist;
    }

    public int[] nextPermutation(int[] arr) {

        /*Idea
          Permutation formula is n!
          e..g 123 , n = 3 so P =3! = 1*2*3 = 6
          123
          132
          213
          231
          312
          321
        Next permutation 213 is 231 and say for 321 is 123( this call lowest permutation)

        we can generate all and just look for next index but it wil be O N! too high.

        E..g below is 7! very high

        2, 1, 5, 4, 3, 0, 0

        1. find dip i..e first lowest number from right excluding last index as there is no base before it.
        2. find first > from right for given dip index
        3. swap large index <> dip index
        4. from large idx +1 -> len sort
        this will give next permuatation is ON

         * */

        // Find Dip Index
        // 2 1 5 4 3 0 0 found 1
        int dipIdx = -1;
        for (int d = arr.length - 2; d >= 0; d--) {
            if (arr[d] < arr[d + 1]) {// 1< 5
                dipIdx = d; // found index where dip happened.
                break;
            }
        }

        //Nothing found as
        //this was last element so revese array will bring it back to first element
        // 321 -> back to 123
        if (dipIdx == -1) {
            reverseArray(arr, 0, arr.length - 1);
            return arr;
        }

        int grtIdx = -1;
        // find first greater element for given dip idx
        // 2 1 5 4 3 0 0 found 3 is > 1
        for (int g = arr.length - 1; g > dipIdx; g--) {
            if (arr[g] > arr[dipIdx]) {
                grtIdx = g;
                break;
            }
        }

        //swap dip <> grt
        // 2 1 5 4 3 0 0  > 2 3 5 4 1 0 0
        int t = arr[dipIdx];
        arr[dipIdx] = arr[grtIdx];
        arr[grtIdx] = t;

        // now sort remaining from dipidx => last
        // 2 3 5 4 1 0 0 >  2 3 0 0 1 4 5
        Arrays.sort(arr, dipIdx + 1, arr.length);

        return arr;

    }

//------------- Private helper Methods..//------------- //------------- //------------- //-------------

    private void printArray(int[] arr) {
        System.out.println();
        for (var d : arr) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    private void printMatrix(int[][] matrix) {
        System.out.println();
        for (int rw = 0; rw < matrix.length; rw++) {
            for (int cl = 0; cl < matrix[0].length; cl++) {
                System.out.print(matrix[rw][cl] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    //---- NOT USED--------------------------

    public void find_missing_repeating_NOT_Work_For_MajorityCases_NOT_USED(int[] arr) {

       /*  Idea : for each index i find abs value
         and consider that value say 3 is index i.e 3-1 =2 then
         go to that index say 2 and whatever value present there mark it -1 if not already marked.
         during this if found already marked that index i th value say 3 is the repeating #.*/

        int size = arr.length;

        for (int i = 0; i < size; i++) {

            int abs_val = Math.abs(arr[i]);

            if (arr[abs_val - 1] > 0)// check if not already marked.
                arr[abs_val - 1] = -arr[abs_val - 1];
            else System.out.println("The repeating element is " + abs_val);
        }


        // Idea whichever index value is not - ie. > 0 that number i.e ind+1 is missing.
        System.out.print("and the missing element is ");


        for (int i = 0; i < size; i++) {
            if (arr[i] > 0) System.out.println(i + 1);
        }

    }

    public int Count_SubArr_WithSum_K_HashMap_Known_Way_NOT_USED(int[] arr, int K) {
        //Note: Seems not ideal for count of subarray. May not work for all use case

        // Idea : Its  perfect solutions when 0, + and - values present
        // but not when only + and 0 presents then 2 pointer is perfect sol.
        int counter = 0;
        int sum = 0;
        var map = new HashMap<Integer, Integer>();

        for (int p = 0; p < arr.length; p++) {

            sum = sum + arr[p];

            //keep updating max lend when sum matched.
            if (sum == K) {
                counter++;

            }

            // to get more possible len when more than one combination makes K
            var remSum = sum - K;

            // say 20 - 10 = 10 above then
            // 10 was prestored @ idx 2 will be found
            // that will give r = 4-2= 2 length.
            if (map.containsKey(remSum)) {
                var remSumIdx = map.get(remSum);
                var r = p - remSumIdx;
                counter++;
            }


            if (!map.containsKey(sum)) {
                map.put(sum, p);
                // store running sum and index
                // 2        = > 0
                // 2+3 = 5  = > 1
                // 5+5 =10  = > 2
                //10+1 =11  = > 3
                //11+9 =20  = > 4
            }

        }
        return counter;
    }

    public int Count_Total_SubArr_WithSum_K_TwoPointer_NOT_USED(int[] arr, int K) {

        //Note : Seems not ideal for count of subarray. May not work for all use case

        /* Idea : Its  perfect solutions when 0, + and - values present
         but not when only +ve and 0 presents then 2 pointer is perfect sol.

         fyi sub array i.e continuous elements 2,4 or 3,1,2 no break in between.
*/
        int maxSubArrayCount = 0;
        int sum = arr[0];// start with first ele

        int left = 0;
        int right = 0;

        while (right < arr.length) {

            while (sum > K && left <= right) { // move left to right by deducting left elem.
                sum = sum - arr[left];
                left++;
            }

            if (sum == K) {
                maxSubArrayCount++;
            }

            right++;
            if (right < arr.length) { // to avoid index out bound
                sum = sum + arr[right];
            }
        }

        return maxSubArrayCount;
    }

}



