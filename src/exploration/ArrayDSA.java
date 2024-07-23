package exploration;

import java.util.*;


public class ArrayDSA {


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


    /**
     * Moves all zeros in an array to the end.
     *
     * @param arr The input array to modify.
     *            <p>
     *            Example:
     *            Input: [0, 1, 0, 3, 12]
     *            Output: [1, 3, 12, 0, 0]
     */
    public void moveZerosToEnd(int[] arr) {
        if (arr.length <= 1) { // At least 2 elements required.
            return;
        }

        int zeroPointer = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[zeroPointer] = arr[i];
                zeroPointer++;
            }
        }
        while (zeroPointer < arr.length) {
            arr[zeroPointer] = 0;
            zeroPointer++;
        }
    }

    /**
     * Finds the union of two sorted arrays.
     *
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @param n    The length of the first array.
     * @param m    The length of the second array.
     * @return The union of the two arrays.
     * <p>
     * Example:
     * Input: arr1 = [1, 2, 3, 4, 5], arr2 = [2, 3, 4, 4, 5]
     * Output: [1, 2, 3, 4, 5]
     */
    public int[] findUnionSorted(int[] arr1, int[] arr2, int n, int m) {
        int first = 0;
        int second = 0;
        int[] resArray = new int[m + n];
        int resP = 0;
        while (first < n && second < m) {
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

    /**
     * Finds the union of two unsorted arrays.
     *
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @param n    The length of the first array.
     * @param m    The length of the second array.
     * @return The union of the two arrays.
     * <p>
     * Example:
     * Input: arr1 = [1, 2, 3, 4, 5], arr2 = [2, 3, 4, 4, 5]
     * Output: [1, 2, 3, 4, 5]
     */
    public ArrayList<Integer> findUnionUnsorted(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> resList = new ArrayList<>();

        int first = 0;
        while (first < n) {
            set.add(arr1[first]);
            first++;
        }

        int second = 0;
        while (second < m) {
            set.add(arr2[second]);
            second++;
        }

        var setIterator = set.iterator();
        while (setIterator.hasNext()) {
            resList.add(setIterator.next());
        }

        return resList;
    }

    /**
     * Finds the intersection of two arrays.
     *
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @param n    The length of the first array.
     * @param m    The length of the second array.
     * @return The intersection of the two arrays.
     * <p>
     * Example:
     * Input: arr1 = [1, 2, 2, 1], arr2 = [2, 2]
     * Output: [2, 2]
     */
    public int[] findIntersection(int[] arr1, int[] arr2, int n, int m) {
        int first = 0;
        int second = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int resLen = n <= m ? n : m;
        int[] resArray = new int[resLen];
        int resP = 0;

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

    /**
     * Finds the missing number in an array.
     *
     * @param arr The input array.
     * @return The missing number.
     * <p>
     * Example:
     * Input: [3, 0, 1]
     * Output: 2
     */
    public int findMissingNumber(int[] arr) {
        Arrays.sort(arr); // Sort first

        int len = arr.length;

        // Ensure first number is 0 and last is equal to the length of the array
        if (arr[0] != 0) {
            return 0;
        }
        if (arr[len - 1] != len) {
            return len;
        }
        // Now search for the missing number in between
        for (int p = 1; p < len; p++) {
            int expectedNum = arr[p - 1] + 1;
            if (arr[p] != expectedNum) {
                return expectedNum;
            }
        }
        return -1;
    }

    /**
     * Finds the missing number in an array in an optimized way.
     *
     * @param arr The input array.
     * @return The missing number.
     * <p>
     * Example:
     * Input: [3, 0, 1]
     * Output: 2
     */
    public int findMissingNumberOptimized(int[] arr) {
        int len = arr.length;
        var set = new HashSet<Integer>();

        // First add in Set so no sorting and O(1) look up
        for (int p = 0; p < len; p++) {
            set.add(arr[p]);
        }

        // Now look for 0 to len and find missing
        for (int p = 0; p <= len; p++) {
            if (!set.contains(p)) {
                return p;
            }
        }
        return -1;
    }

    /**
     * Finds the maximum number of consecutive ones in an array.
     *
     * @param arr The input array.
     * @return The maximum number of consecutive ones.
     * <p>
     * Example:
     * Input: [1, 1, 0, 1, 1, 1]
     * Output: 3
     */
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

    /**
     * Finds the single element in an array.
     *
     * @param arr The input array.
     * @return The single element.
     * <p>
     * Example:
     * Input: [4, 1, 2, 1, 2]
     * Output: 4
     */
    public int findSingleElement(int[] arr) {
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


    /**
     * Finds the length of the longest subarray with sum equal to K using brute force.
     *
     * @param arr The input array.
     * @param K   The target sum.
     * @return The length of the longest subarray with sum equal to K.
     */
    public int longestSubArrayWithSumKBruteForce(int[] arr, int K) {
        int maxLen = 0;
        int sum = 0;
        var map = new HashMap<Integer, Integer>();

        for (int p = 0; p < arr.length; p++) {
            sum = sum + arr[p];

            if (sum == K) {
                var len = p + 1;
                maxLen = Math.max(len, maxLen);
            }

            var remSum = sum - K;
            if (map.containsKey(remSum)) {
                var remSumIdx = map.get(remSum);
                var len = p - remSumIdx;
                maxLen = Math.max(len, maxLen);
            }

            if (!map.containsKey(sum)) {
                map.put(sum, p);
            }
        }

        return maxLen;
    }

    /**
     * Finds the length of the longest subarray with sum equal to K using two pointers.
     *
     * @param arr The input array.
     * @param K   The target sum.
     * @return The length of the longest subarray with sum equal to K.
     */
    public int longestSubArrayWithSumKTwoPointer(int[] arr, int K) {
        int maxLen = 0;
        int sum = arr[0];

        int left = 0;
        int right = 0;

        while (right < arr.length) {
            while (sum > K && left <= right) {
                sum = sum - arr[left];
                left++;
            }

            if (sum == K) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
            if (right < arr.length) {
                sum = sum + arr[right];
            }
        }

        return maxLen;
    }

    /**
     * Checks if there is a continuous subarray with sum equal to a multiple of K.
     *
     * @param arr The input array.
     * @param K   The target multiple.
     * @return true if there is a continuous subarray with sum equal to a multiple of K, false otherwise.
     */
    public boolean continuousGoodSubArrayWithSumK(int[] arr, int K) {
        int runningSum = 0;
        var map = new HashMap<Integer, Integer>();

        for (int p = 0; p < arr.length; p++) {
            runningSum = runningSum + arr[p];
            runningSum = runningSum % K;

            if (runningSum == 0 && p > 0) {
                return true;
            }

            if (map.containsKey(runningSum)) {
                var remSumIdx = map.get(runningSum);
                var len = p - remSumIdx;
                if (len > 1) {
                    return true;
                }
            }

            if (!map.containsKey(runningSum)) {
                map.put(runningSum, p);
            }
        }

        return false;
    }

    /**
     * Finds the length of the smallest subarray with sum equal to K using two pointers.
     *
     * @param arr The input array.
     * @param K   The target sum.
     * @return The length of the smallest subarray with sum equal to K.
     */
    public int minSubArrayWithSumKTwoPointer(int[] arr, int K) {
        int minLen = Integer.MAX_VALUE;
        int sum = arr[0];

        int left = 0;
        int right = 0;

        while (right < arr.length) {
            while (sum >= K && left <= right) {
                minLen = Math.min(minLen, right - left + 1);
                sum = sum - arr[left];
                left++;
            }

            right++;
            if (right < arr.length) {
                sum = sum + arr[right];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * Finds two numbers in an array that add up to a target sum using two pointers.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return An array of two indices whose elements add up to the target sum.
     */
    public int[] findTwoSum(int[] arr, int target) {
        Arrays.sort(arr);

        int[] result = new int[2];
        int left = 0, right = arr.length - 1;
        int sum;

        while (left < right) {
            sum = arr[left] + arr[right];
            if (sum == target) {
                result[0] = left;
                result[1] = right;
                return result;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    /**
     * Finds two numbers in an array that add up to a target sum using a HashMap.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return An array of two indices whose elements add up to the target sum.
     */
    public int[] findTwoSumHashMap(int[] arr, int target) {
        int[] result = new int[2];
        int left = 0, right = arr.length - 1;
        var map = new HashMap<Integer, Integer>();

        while (left <= right) {
            int diff = target - arr[left];

            if (!map.containsKey(diff)) {
                map.put(arr[left], left);
            } else {
                int diffIdx = map.get(diff);
                return new int[]{diffIdx, left};
            }
            left++;
        }

        return result;
    }

    /**
     * Sorts an array of colors represented by 0s, 1s, and 2s.
     *
     * @param nums The input array.
     */
    public void sortColors(int[] nums) {
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

    /**
     * Sorts an array of colors represented by 0s, 1s, and 2s using the Dutch National Flag algorithm.
     *
     * @param nums The input array.
     */
    public void sortColorsDutchFlagAlgo(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

    /**
     * Finds the majority element in an array.
     *
     * @param arr The input array.
     * @return The majority element.
     */
    public int majorityElement(int[] arr) {
        int len = arr.length;
        var map = new HashMap<Integer, Integer>();

        for (int i = 0; i < len; i++) {
            int ele = arr[i];
            if (!map.containsKey(ele)) {
                map.put(ele, 1);
            } else {
                map.put(ele, map.get(ele) + 1);
            }
        }

        for (var entry : map.entrySet()) {
            if (entry.getValue() > len / 2) {
                return entry.getKey();
            }
        }

        return -1;
    }


    /**
     * Finds the majority element in an array using Moore's Voting Algorithm.
     *
     * @param arr The input array.
     * @return The majority element.
     */
    public int majorityElementMooreVoting(int[] arr) {
        int len = arr.length;
        int counter = 0;
        int element = -1;

        for (int i = 0; i < len; i++) {
            if (counter == 0) {
                element = arr[i];
                counter++;
            } else if (arr[i] == element) {
                counter++;
            } else {
                counter--;
            }
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == element) {
                count++;
            }
        }

        if (count > (len / 2)) {
            return element;
        }

        return -1;
    }

    /**
     * Finds the maximum sum of a subarray in an array.
     *
     * @param arr The input array.
     * @return The maximum sum of a subarray.
     */
    public int maxSubArraySum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int runningSum = 0;
        int startingPoint = 0;
        int started = -1, ended = -1;

        for (int i = 0; i < arr.length; i++) {
            if (runningSum == 0) {
                startingPoint = i;
            }
            runningSum = runningSum + arr[i];

            if (runningSum > maxSum) {
                maxSum = runningSum;
                started = startingPoint;
                ended = i;
            }

            if (runningSum < 0) {
                runningSum = 0;
            }
        }

        if (maxSum < 0) {
            return 0;
        }

        return maxSum;
    }

    /**
     * Rearranges an array so that positive and negative numbers alternate.
     *
     * @param arr The input array.
     * @return The rearranged array.
     */
    public int[] rearrangeArray(int[] arr) {
        int[] resArr = new int[arr.length];

        int posIdx = 0;
        int negIdx = 1;

        for (int p = 0; p < arr.length; p++) {
            if (arr[p] > 0) {
                resArr[posIdx] = arr[p];
                posIdx = posIdx + 2;
            } else {
                resArr[negIdx] = arr[p];
                negIdx = negIdx + 2;
            }
        }

        return resArr;
    }

    /**
     * Finds the length of the longest sequence of consecutive elements in an array.
     *
     * @param arr The input array.
     * @return The length of the longest sequence of consecutive elements.
     */
    public int longestConsecutiveElements(int[] arr) {
        int maxLen = 1;
        int count = 1;
        int arrLen = arr.length;

        Arrays.sort(arr);

        for (int i = 0; i < arrLen; i++) {
            if (i + 1 < arrLen && arr[i] + 1 == arr[i + 1]) {
                count++;
                maxLen = Math.max(maxLen, count);
            } else {
                count = 1;
            }
        }
        return maxLen;
    }

    /**
     * Finds the length of the longest sequence of consecutive elements in an array using a HashSet.
     *
     * @param arr The input array.
     * @return The length of the longest sequence of consecutive elements.
     */
    public int longestConsecutiveElementsHashSet(int[] arr) {
        int maxLen = 1;
        int count = 1;
        int arrLen = arr.length;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arrLen; i++) {
            set.add(arr[i]);
        }

        for (int item : set) {
            if (!set.contains(item - 1)) {
                while (set.contains(item + 1)) {
                    item = item + 1;
                    count = count + 1;
                    maxLen = Math.max(maxLen, count);
                }
                count = 1;
            }
        }

        return maxLen;
    }

    /**
     * Finds the leaders in an array.
     *
     * @param arr The input array.
     * @return An array of leaders.
     */
    public int[] findLeaders(int[] arr) {
        int[] res = new int[arr.length];
        int resCounter = 0;

        int leader = arr[arr.length - 1];
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


    /**
     * This method counts the total number of sub-arrays in a given array whose sum equals to a given integer k.
     * It uses a HashMap to store the running sum of elements and their count.
     *
     * @param arr The input array.
     * @param k   The target sum.
     * @return The total number of sub-arrays whose sum equals to k.
     * <p>
     * Example:
     * Input: arr = [3, 1, 2, 4], k = 6
     * Output: 2
     * Explanation: There are two sub-arrays [3, 1, 2] and [2, 4] whose sum equals to 6.
     */
    public int Count_Total_SubArr_WithSum_K_HashMap(int arr[], int k) {
        // Initialize a map to store the running sum and its count
        Map<Integer, Integer> map = new HashMap<>();

        int runningSum = 0, runningCounter = 0;

        // Add 0 to the map to handle the case when the running sum equals to k
        map.put(0, 1);

        for (int i = 0; i < arr.length; i++) {
            // Calculate the running sum
            runningSum += arr[i];

            // Calculate the difference between the running sum and k
            int diff = runningSum - k;

            // If the difference is found in the map, add its count to the running counter
            if (map.containsKey(diff)) {
                runningCounter += map.get(diff);
            }

            // Get the count of the running sum so far
            int soFarCount = map.getOrDefault(runningSum, 0);

            // Update the count of the running sum in the map
            map.put(runningSum, soFarCount + 1);
        }

        // Return the total number of sub-arrays whose sum equals to k
        return runningCounter;
    }

    /**
     * This method finds all the majority elements in a given array.
     * A majority element is an element that appears more than n/3 times in the array, where n is the length of the array.
     *
     * @param arr The input array.
     * @return An array of all the majority elements.
     * <p>
     * Example:
     * Input: arr = [3, 2, 3]
     * Output: [3]
     * Explanation: The number 3 appears more than 3/3 = 1 times in the array.
     */
    public int[] majorityElements_N3(int[] arr) {
        int len = arr.length;
        Map<Integer, Integer> map = new HashMap<>();

        // Count the occurrence of each element in the array
        for (int i = 0; i < len; i++) {
            int ele = arr[i];
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        // Calculate the minimum occurrence of a majority element
        int minOccurrence = len / 3 + 1;

        // Initialize an array to store the majority elements
        int[] resArr = new int[len / 3];
        int resCount = 0;

        // Find all the majority elements
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= minOccurrence) {
                resArr[resCount] = entry.getKey();
                resCount++;
            }
        }

        // Return the majority elements
        return resArr;
    }


    /**
     * This method finds all the majority elements in a given array using Moore's Voting Algorithm.
     * A majority element is an element that appears more than n/3 times in the array, where n is the length of the array.
     *
     * @param arr The input array.
     * @return An array of all the majority elements.
     * <p>
     * Example:
     * Input: arr = [3, 2, 3]
     * Output: [3]
     * Explanation: The number 3 appears more than 3/3 = 1 times in the array.
     */
    public int[] majorityElement_N3_MooreVoting(int[] arr) {
        int len = arr.length;

        int count1 = 0, candidate1 = Integer.MIN_VALUE;
        int count2 = 0, candidate2 = Integer.MIN_VALUE;

        // First pass: find potential majority elements
        for (int i = 0; i < len; i++) {
            if (count1 == 0 && arr[i] != candidate2) {
                candidate1 = arr[i];
                count1++;
            } else if (count2 == 0 && arr[i] != candidate1) {
                candidate2 = arr[i];
                count2++;
            } else if (arr[i] == candidate1) {
                count1++;
            } else if (arr[i] == candidate2) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        // Second pass: confirm the majority elements
        int finalCount1 = 0, finalCount2 = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == candidate1) {
                finalCount1++;
            }
            if (arr[i] == candidate2) {
                finalCount2++;
            }
        }

        // Prepare the result array
        int[] resArr = new int[len / 3];
        int resCount = 0;

        // Add the majority elements to the result array
        if (finalCount1 > len / 3) {
            resArr[resCount] = candidate1;
            resCount++;
        }
        if (finalCount2 > len / 3) {
            resArr[resCount] = candidate2;
            resCount++;
        }

        return resArr;
    }

    /**
     * This method finds all the triplets in a given array whose sum equals to zero.
     * It uses a HashSet to store the triplets and avoid duplicates.
     *
     * @param arr The input array.
     * @return A list of all the triplets whose sum equals to zero.
     * <p>
     * Example:
     * Input: arr = [-1, 0, 1, 2, -1, -4]
     * Output: [[-1, -1, 2], [-1, 0, 1]]
     * Explanation: The triplets [-1, -1, 2] and [-1, 0, 1] have a sum of zero.
     */
    public List<List<Integer>> triplet_3_Sum_to_Zer0_HashSet(int[] arr) {
        Set<List<Integer>> resSet = new HashSet<>();

        for (int p = 0; p < arr.length; p++) {
            Set<Integer> setThird = new HashSet<>();
            for (int q = p + 1; q < arr.length; q++) {
                int r = -(arr[p] + arr[q]);

                if (setThird.contains(r)) {
                    List<Integer> tempList = Arrays.asList(r, arr[p], arr[q]);
                    tempList.sort(null);
                    resSet.add(tempList);
                }

                setThird.add(arr[q]);
            }
        }

        return new ArrayList<>(resSet);
    }

    /**
     * This method finds all the triplets in a given array whose sum equals to zero.
     * It sorts the array first and then uses three pointers to find the triplets.
     *
     * @param arr The input array.
     * @return A list of all the triplets whose sum equals to zero.
     * <p>
     * Example:
     * Input: arr = [-1, 0, 1, 2, -1, -4]
     * Output: [[-1, -1, 2], [-1, 0, 1]]
     * Explanation: The triplets [-1, -1, 2] and [-1, 0, 1] have a sum of zero.
     */
    public List<List<Integer>> triplet_3_Sum_to_Zer0_3_Pointer(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> resList = new ArrayList<>();
        int len = arr.length;

        for (int p = 0; p < len; p++) {
            if (p != 0 && arr[p] == arr[p - 1]) {
                continue;
            }

            int q = p + 1, r = len - 1;

            while (q < r) {
                int sum = arr[p] + arr[q] + arr[r];

                if (sum < 0) {
                    q++;
                } else if (sum > 0) {
                    r--;
                } else {
                    resList.add(Arrays.asList(arr[p], arr[q], arr[r]));
                    q++;
                    r--;

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


    /**
     * This method finds all the quadruplets in a given array whose sum equals to a target.
     * It sorts the array first and then uses four pointers to find the quadruplets.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return A list of all the quadruplets whose sum equals to the target.
     * <p>
     * Example:
     * Input: arr = [1, 0, -1, 0, -2, 2], target = 0
     * Output: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
     * Explanation: The quadruplets [-2, -1, 1, 2], [-2, 0, 0, 2] and [-1, 0, 0, 1] have a sum of zero.
     */
    public List<List<Integer>> Quads_4_Sum_to_Zero_4_Pointer(int[] arr, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(arr);

        int len = arr.length;

        for (int w = 0; w < len; w++) {
            if (w != 0 && arr[w] == arr[w - 1]) {
                continue;
            }

            for (int p = w + 1; p < len; p++) {
                if (p > w + 1 && arr[p] == arr[p - 1]) {
                    continue;
                }

                int q = p + 1, r = len - 1;

                while (q < r) {
                    int sum = arr[w] + arr[p] + arr[q] + arr[r];

                    if (sum < target) {
                        q++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        resList.add(Arrays.asList(arr[w], arr[p], arr[q], arr[r]));
                        q++;
                        r--;

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

    /**
     * This method finds the maximum length of a sub-array in a given array whose sum equals to zero.
     * It uses a HashMap to store the running sum and its index.
     *
     * @param arr The input array.
     * @return The maximum length of a sub-array whose sum equals to zero.
     * <p>
     * Example:
     * Input: arr = [15, -2, 2, -8, 1, 7, 10, 23]
     * Output: 5
     * Explanation: The longest sub-array with sum 0 is [-2, 2, -8, 1, 7].
     */
    public int MaxSubArrWithSum_Zero_Hashmap(int[] arr) {
        int maxLen = 0;
        int runningSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int p = 0; p < arr.length; p++) {
            runningSum += arr[p];

            if (runningSum == 0) {
                maxLen = Math.max(p + 1, maxLen);
            }

            if (map.containsKey(runningSum)) {
                maxLen = Math.max(p - map.get(runningSum), maxLen);
            } else {
                map.put(runningSum, p);
            }
        }

        return maxLen;
    }

    /**
     * This method merges two sorted arrays into one sorted array without using extra space.
     * It compares the last element of the first array with the first element of the second array and swaps them if necessary.
     *
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     *             <p>
     *             Example:
     *             Input: arr1 = [1, 5, 9], arr2 = [2, 4, 6]
     *             Output: arr1 = [1, 2, 4], arr2 = [5, 6, 9]
     *             Explanation: The arrays are merged and sorted without using extra space.
     */
    public void Merge_TwoSorted_Array_wO_extraSpace(int[] arr1, int[] arr2) {
        int left = arr1.length - 1;
        int right = 0;

        while (left >= 0 && right < arr2.length) {
            if (arr1[left] > arr2[right]) {
                int temp = arr1[left];
                arr1[left] = arr2[right];
                arr2[right] = temp;
                left--;
                right++;
            } else {
                break;
            }
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    /**
     * This method merges all the overlapping intervals in a given array of intervals.
     * It sorts the array first and then merges the overlapping intervals.
     *
     * @param arr The input array of intervals.
     * @return A list of merged intervals.
     * <p>
     * Example:
     * Input: arr = [[1, 3], [2, 6], [8, 10], [15, 18]]
     * Output: [[1, 6], [8, 10], [15, 18]]
     * Explanation: The intervals [1, 3] and [2, 6] overlap, so they are merged into [1, 6].
     */
    public List<List<Integer>> mergeOverlappingIntervals_aka_MeetingRoom(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        List<List<Integer>> resList = new ArrayList<>();

        for (int idx = 0; idx < arr.length; idx++) {
            if (resList.isEmpty()) {
                resList.add(Arrays.asList(arr[idx][0], arr[idx][1]));
            } else {
                List<Integer> lastInterval = resList.get(resList.size() - 1);

                if (arr[idx][0] <= lastInterval.get(1)) {
                    lastInterval.set(1, Math.max(lastInterval.get(1), arr[idx][1]));
                } else {
                    resList.add(Arrays.asList(arr[idx][0], arr[idx][1]));
                }
            }
        }

        return resList;
    }


    /**
     * This method finds the missing and repeating numbers in a given array.
     * It uses the absolute values of the elements as indices and marks the corresponding elements in the array as negative.
     *
     * @param arr The input array.
     * @return An array of two integers where the first integer is the repeating number and the second integer is the missing number.
     * <p>
     * Example:
     * Input: arr = [3, 1, 3]
     * Output: [3, 2]
     * Explanation: The number 3 appears twice and the number 2 is missing.
     */
    public int[] findMissingRepeatingNumbers(int[] arr) {
        int missed = -1;
        int repeat = -1;

        for (int idx = 0; idx < arr.length; idx++) {
            int targetIdx = Math.abs(arr[idx]) - 1;

            if (arr[targetIdx] > 0) {
                arr[targetIdx] = -arr[targetIdx];
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

        return new int[]{repeat, missed};
    }

    /**
     * This method finds the maximum product of a sub-array in a given array.
     * It uses a brute force approach by checking all possible sub-arrays.
     *
     * @param arr The input array.
     * @return The maximum product of a sub-array.
     * <p>
     * Example:
     * Input: arr = [2, 3, -2, 4]
     * Output: 6
     * Explanation: The sub-array [2, 3] has the maximum product 6.
     */
    public int maxProduct_SubArray_Regular_BF(int[] arr) {
        int maxProduct = arr[0];

        for (int outer = 0; outer < arr.length; outer++) {
            int runningProduct = arr[outer];
            for (int inner = outer + 1; inner < arr.length; inner++) {
                maxProduct = Math.max(maxProduct, runningProduct);
                runningProduct *= arr[inner];
            }
            maxProduct = Math.max(maxProduct, runningProduct);
        }

        return maxProduct;
    }

    /**
     * This method finds the maximum product of a sub-array in a given array.
     * It uses a two-pass approach by calculating the product from left to right and from right to left.
     *
     * @param arr The input array.
     * @return The maximum product of a sub-array.
     * <p>
     * Example:
     * Input: arr = [2, 3, -2, 4]
     * Output: 6
     * Explanation: The sub-array [2, 3] has the maximum product 6.
     */
    public int maxProduct_SubArray_Regular_Algo(int[] arr) {
        int maxProduct = Integer.MIN_VALUE;
        int pref = 1, suff = 1;
        int len = arr.length;

        for (int k = 0; k < len; k++) {
            if (pref == 0) pref = 1;
            if (suff == 0) suff = 1;

            int pv = arr[k];
            int sv = arr[len - k - 1];

            pref *= pv;
            suff *= sv;

            int maxPS = Math.max(pref, suff);
            maxProduct = Math.max(maxPS, maxProduct);
        }

        return maxProduct;
    }

    /**
     * This method counts the number of inversions in a given array.
     * An inversion is a pair of indices (i, j) such that i < j and arr[i] > arr[j].
     * It uses a modified version of merge sort to count the inversions.
     *
     * @param givenArray The input array.
     * @return The number of inversions in the array.
     * <p>
     * Example:
     * Input: givenArray = [2, 4, 1, 3, 5]
     * Output: 3
     * Explanation: There are three inversions in the array: (2, 1), (4, 1), (4, 3).
     */
    public int count_inversion(int[] givenArray) {
        return MergeSort(givenArray, 0, givenArray.length - 1);
    }

    public int MergeSort(int[] givenArray, int start, int end) {
        int count = 0;

        if (end > start) {
            int mid = (start + end) / 2;
            count += MergeSort(givenArray, start, mid);
            count += MergeSort(givenArray, mid + 1, end);
            count += mergeArray(givenArray, start, end, mid);
        }

        return count;
    }

    int mergeArray(int[] givenArray, int start, int end, int mid) {
        int[] tempA = new int[end - start + 1];
        int tempPointer = 0;

        int leftPointer = start;
        int rightPointer = mid + 1;

        int count = 0;
        while (leftPointer <= mid && rightPointer <= end) {
            if (givenArray[leftPointer] <= givenArray[rightPointer]) {
                tempA[tempPointer++] = givenArray[leftPointer++];
            } else {
                tempA[tempPointer++] = givenArray[rightPointer++];
                count += mid - leftPointer + 1;
            }
        }

        while (leftPointer <= mid) {
            tempA[tempPointer++] = givenArray[leftPointer++];
        }

        while (rightPointer <= end) {
            tempA[tempPointer++] = givenArray[rightPointer++];
        }

        for (int x = start; x <= end; x++) {
            givenArray[x] = tempA[x - start];
        }

        return count;
    }


    /**
     * This method counts the number of reverse pairs in a given array.
     * A reverse pair is a pair of indices (i, j) such that i < j and arr[i] > 2 * arr[j].
     * It uses a modified version of merge sort to count the reverse pairs.
     *
     * @param givenArray The input array.
     * @return The number of reverse pairs in the array.
     * <p>
     * Example:
     * Input: givenArray = [1, 3, 2, 3, 1]
     * Output: 2
     * Explanation: There are two reverse pairs in the array: (1, 3) and (2, 3).
     */
    public int count_reversePair(int[] givenArray) {
        return MergeSort_count_reversePair(givenArray, 0, givenArray.length - 1);
    }

    public int MergeSort_count_reversePair(int[] givenArray, int start, int end) {
        int count = 0;

        if (end > start) {
            int mid = (start + end) / 2;
            count += MergeSort_count_reversePair(givenArray, start, mid);
            count += MergeSort_count_reversePair(givenArray, mid + 1, end);
            count += countPairs(givenArray, start, end, mid);
            mergeArray_count_reversePair(givenArray, start, end, mid);
        }

        return count;
    }

    int countPairs(int[] arr, int low, int high, int mid) {
        int count = 0;
        int right = mid + 1;

        for (int left = low; left <= mid; left++) {
            while (right <= high && arr[left] > 2L * arr[right]) {
                right++;
            }
            count += right - (mid + 1);
        }

        return count;
    }

    void mergeArray_count_reversePair(int[] givenArray, int start, int end, int mid) {
        int[] tempA = new int[end - start + 1];
        int tempPointer = 0;

        int leftPointer = start;
        int rightPointer = mid + 1;

        while (leftPointer <= mid && rightPointer <= end) {
            if (givenArray[leftPointer] <= givenArray[rightPointer]) {
                tempA[tempPointer++] = givenArray[leftPointer++];
            } else {
                tempA[tempPointer++] = givenArray[rightPointer++];
            }
        }

        while (leftPointer <= mid) {
            tempA[tempPointer++] = givenArray[leftPointer++];
        }

        while (rightPointer <= end) {
            tempA[tempPointer++] = givenArray[rightPointer++];
        }

        for (int x = start; x <= end; x++) {
            givenArray[x] = tempA[x - start];
        }
    }

    /**
     * This method sets the entire row and column to zero if an element in a given matrix is zero.
     *
     * @param givenMatrix The input matrix.
     * @param rowLen      The number of rows in the matrix.
     * @param colLen      The number of columns in the matrix.
     *                    <p>
     *                    Example:
     *                    Input: givenMatrix = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
     *                    Output: [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
     *                    Explanation: The element at the second row and the second column is zero, so the entire second row and the second column are set to zero.
     */
    public void zeroMatrix(int[][] givenMatrix, int rowLen, int colLen) {
        int[] rowMark = new int[rowLen];
        int[] colMark = new int[colLen];

        for (int rw = 0; rw < givenMatrix.length; rw++) {
            for (int cl = 0; cl < givenMatrix[0].length; cl++) {
                if (givenMatrix[rw][cl] == 0) {
                    rowMark[rw] = 1;
                    colMark[cl] = 1;
                }
            }
        }

        for (int rw = 0; rw < givenMatrix.length; rw++) {
            for (int cl = 0; cl < givenMatrix[0].length; cl++) {
                if (colMark[cl] == 1 || rowMark[rw] == 1) {
                    givenMatrix[rw][cl] = 0;
                }
            }
        }
    }

    /**
     * This method rotates a given matrix by 90 degrees clockwise.
     *
     * @param givenMatrix The input matrix.
     *                    <p>
     *                    Example:
     *                    Input: givenMatrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
     *                    Output: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
     *                    Explanation: The matrix is rotated by 90 degrees clockwise.
     */
    public void Rotate_90d_Matrix(int[][] givenMatrix) {
        for (int rw = 0; rw < givenMatrix.length; rw++) {
            for (int cl = rw; cl < givenMatrix[0].length; cl++) {
                int temp = givenMatrix[rw][cl];
                givenMatrix[rw][cl] = givenMatrix[cl][rw];
                givenMatrix[cl][rw] = temp;
            }
        }

        for (int rw = 0; rw < givenMatrix.length; rw++) {
            for (int cl = 0; cl < givenMatrix[0].length / 2; cl++) {
                int temp = givenMatrix[rw][cl];
                int lastIndex = givenMatrix[0].length - 1 - cl;

                givenMatrix[rw][cl] = givenMatrix[rw][lastIndex];
                givenMatrix[rw][lastIndex] = temp;
            }
        }
    }

    /**
     * This method prints a given matrix in a spiral order.
     * It defines the top, bottom, left, and right boundaries of the matrix and iterates over the elements in a spiral order.
     *
     * @param givenMatrix The input matrix.
     * @return A list of elements in the matrix in a spiral order.
     * <p>
     * Example:
     * Input: givenMatrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
     * Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
     * Explanation: The elements are printed in a spiral order.
     */
    public List<Integer> printSpiralMatrix(int[][] givenMatrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0, left = 0;
        int right = givenMatrix[0].length - 1, bottom = givenMatrix.length - 1;

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res.add(givenMatrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                res.add(givenMatrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(givenMatrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(givenMatrix[i][left]);
                }
                left++;
            }
        }

        return res;
    }


    public void pascalTriangle() {
        int row = 5;
        int[] preRes = new int[row + 1];
        preRes[1] = 1;

        int[] curRes = new int[row + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= i; j++) {
                curRes[j] = preRes[j] + preRes[j - 1];
            }

            for (int p = 1; p <= i; p++) {
                preRes[p] = curRes[p];
                System.out.print(curRes[p] + " ");
            }

            System.out.println();
        }
    }

    /**
     * This method generates the Pascal's triangle up to a given row.
     * It uses two arrays to store the current row and the previous row of the triangle.
     *
     * @param row The number of rows to generate.
     * @return A list of lists representing the Pascal's triangle.
     * <p>
     * Example:
     * Input: row = 5
     * Output: [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
     * Explanation: The first five rows of the Pascal's triangle are generated.
     */
    public List<List<Integer>> generate_Pascal_LeetCode(int row) {
        List<List<Integer>> resList = new ArrayList<>();
        int[] preRes = new int[row + 1];
        preRes[1] = 1;

        int[] curRes = new int[row + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= i; j++) {
                curRes[j] = preRes[j] + preRes[j - 1];
            }

            List<Integer> tempList = new ArrayList<>();
            for (int p = 1; p <= i; p++) {
                preRes[p] = curRes[p];
                tempList.add(curRes[p]);
            }

            resList.add(tempList);
        }

        return resList;
    }

    /**
     * This method rearranges a given array to its next lexicographically greater permutation.
     * If the array is already the greatest permutation, it rearranges the array to the smallest permutation.
     *
     * @param arr The input array.
     * @return The array rearranged to its next permutation.
     * <p>
     * Example:
     * Input: arr = [1, 2, 3]
     * Output: [1, 3, 2]
     * Explanation: The next permutation of [1, 2, 3] is [1, 3, 2].
     */
    public int[] nextPermutation(int[] arr) {
        int dipIdx = -1;
        for (int d = arr.length - 2; d >= 0; d--) {
            if (arr[d] < arr[d + 1]) {
                dipIdx = d;
                break;
            }
        }

        if (dipIdx == -1) {
            reverseArray(arr, 0, arr.length - 1);
            return arr;
        }

        int grtIdx = -1;
        for (int g = arr.length - 1; g > dipIdx; g--) {
            if (arr[g] > arr[dipIdx]) {
                grtIdx = g;
                break;
            }
        }

        int temp = arr[dipIdx];
        arr[dipIdx] = arr[grtIdx];
        arr[grtIdx] = temp;

        Arrays.sort(arr, dipIdx + 1, arr.length);

        return arr;
    }

    /**
     * This method prints a given array.
     *
     * @param arr The input array.
     */
    private void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * This method prints a given matrix.
     *
     * @param matrix The input matrix.
     */
    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method finds the missing and repeating numbers in a given array.
     * It uses the absolute values of the elements as indices and marks the corresponding elements in the array as negative.
     * Note: This method may not work for all cases, especially when the array contains majority elements.
     *
     * @param arr The input array.
     *            <p>
     *            Example:
     *            Input: arr = [3, 1, 3]
     *            Output: The repeating element is 3 and the missing element is 2.
     *            Explanation: The number 3 appears twice and the number 2 is missing.
     */
    public void find_missing_repeating_NOT_Work_For_MajorityCases_NOT_USED(int[] arr) {
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            int abs_val = Math.abs(arr[i]);

            if (arr[abs_val - 1] > 0)
                arr[abs_val - 1] = -arr[abs_val - 1];
            else
                System.out.println("The repeating element is " + abs_val);
        }

        System.out.print("and the missing element is ");

        for (int i = 0; i < size; i++) {
            if (arr[i] > 0)
                System.out.println(i + 1);
        }
    }

    /**
     * This method counts the total number of sub-arrays in a given array whose sum equals to a given integer K.
     * It uses a HashMap to store the running sum of elements and their count.
     * Note: This method may not work for all cases, especially when the array contains only positive numbers and zero.
     *
     * @param arr The input array.
     * @param K   The target sum.
     * @return The total number of sub-arrays whose sum equals to K.
     * <p>
     * Example:
     * Input: arr = [1, 1, 1], K = 2
     * Output: 2
     * Explanation: There are two sub-arrays [1, 1] whose sum equals to 2.
     */
    public int Count_SubArr_WithSum_K_HashMap_Known_Way_NOT_USED(int[] arr, int K) {
        int counter = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int p = 0; p < arr.length; p++) {
            sum += arr[p];

            if (sum == K) {
                counter++;
            }

            int remSum = sum - K;

            if (map.containsKey(remSum)) {
                int remSumIdx = map.get(remSum);
                int r = p - remSumIdx;
                counter++;
            }

            if (!map.containsKey(sum)) {
                map.put(sum, p);
            }
        }

        return counter;
    }

    /**
     * This method counts the total number of sub-arrays in a given array whose sum equals to a given integer K.
     * It uses two pointers to keep track of the running sum of elements.
     * Note: This method may not work for all cases, especially when the array contains negative numbers.
     *
     * @param arr The input array.
     * @param K   The target sum.
     * @return The total number of sub-arrays whose sum equals to K.
     * <p>
     * Example:
     * Input: arr = [1, 1, 1], K = 2
     * Output: 2
     * Explanation: There are two sub-arrays [1, 1] whose sum equals to 2.
     */
    public int Count_Total_SubArr_WithSum_K_TwoPointer_NOT_USED(int[] arr, int K) {
        int maxSubArrayCount = 0;
        int sum = arr[0];

        int left = 0;
        int right = 0;

        while (right < arr.length) {
            while (sum > K && left <= right) {
                sum -= arr[left];
                left++;
            }

            if (sum == K) {
                maxSubArrayCount++;
            }

            right++;
            if (right < arr.length) {
                sum += arr[right];
            }
        }

        return maxSubArrayCount;
    }


}



