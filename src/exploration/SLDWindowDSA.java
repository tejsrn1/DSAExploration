package exploration;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SLDWindowDSA {

    /*
    Note:
    1. Two pointer problems are not the same as Sliding window problems.

    2. Sliding window problems can be categorized into:
        - Fixed length: where window length is fixed. For example, finding the maximum sum of a subarray of size 3.
        - Dynamic length: where window size changes, similar to a caterpillar that grows and then shrinks. The length of the window plays a role in the answer. For example, finding the smallest sum that is greater than or equal to S.
        - Dynamic length with Auxiliary storage: Same as Dynamic window length but here a hashmap, hashset, or any array is used to store ongoing values while the window grows and shrinks. For example, finding the longest substring with no more than K distinct characters.

    3. What is Prefix Sum?
        Basically, it's a pre-calculated sum of a given array. It is used where a subarray needs some previously calculated sum of an array.

    4. Prefix Max and Suffix Max:
        Prefix Max and Suffix Max are techniques used to calculate the maximum value up to a certain index in an array from the beginning (prefix) or from the end (suffix).

    Common mistakes:
        1. Do not jump left directly to right via ignoring the in-between subarray or substring.
        2. When using an index array or map, rely on it to see where to move left next.
        3. When not using #2 then just move left ++.

    Tips:
        When finding length, if right ++ move before then right-left and if later then right-left+1.
*/


    /**
     * This method finds the length of the longest substring without repeating characters.
     * It assumes that the input string contains only 128 ASCII characters.
     *
     * @param inputString The string from which to find the longest substring without repeating characters.
     * @return The length of the longest substring without repeating characters.
     * <p>
     * Example:
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     */
    public int lengthOfLongestSubstringWithoutRepeatingChar(String inputString) {
        int left = 0, right = 0;
        int maxLength = 0;
        Integer[] position = new Integer[128];

        while (right < inputString.length()) {
            char character = inputString.charAt(right);
            Integer previousPosition = position[character];

            // If the character has been seen before and its previous position is within the current window
            if (previousPosition != null && previousPosition >= left && previousPosition < right) {
                // Move the left pointer to the next character from the previous seen index of the current character
                left = previousPosition + 1;
            }

            position[character] = right; // Store the index of the current character

            int currentLength = right - left + 1;
            maxLength = Math.max(maxLength, currentLength); // Record the maximum length so far

            right++;
        }
        return maxLength;
    }


    /**
     * This method calculates the number of subarrays with a given sum.
     * It uses a helper method to calculate the number of subarrays with a sum less than or equal to the target,
     * and the number of subarrays with a sum less than the target. The difference between these two values gives the result.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return The number of subarrays with a sum equal to the target.
     * <p>
     * Example:
     * Input: arr = [1, 0, 1, 0, 1], target = 2
     * Output: 4
     * Explanation: The binary subarrays that sum to 2 are [1, 0, 1], [1, 0, 1], [0, 1, 0, 1] and [1, 0, 1]
     */
    public int numOfSubarraysWithGivenSum(int[] arr, int target) {
        return numOfSubarraysWithSumHelper(arr, target) - numOfSubarraysWithSumHelper(arr, target - 1);
    }

    /**
     * This helper method calculates the number of subarrays with a sum less than or equal to the target.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return The number of subarrays with a sum less than or equal to the target.
     */
    public int numOfSubarraysWithSumHelper(int[] arr, int target) {
        int left = 0;
        int right = 0;
        int runningSum = 0;
        int totalCount = 0;

        while (right < arr.length) {
            // Add the current element to the running sum
            runningSum += arr[right];

            // If the running sum is greater than the target, move the left pointer to the right
            while (left <= right && runningSum > target) {
                runningSum -= arr[left];
                left++;
            }

            // Add the length of the current subarray to the total count
            totalCount += right - left + 1;

            right++;
        }

        return totalCount;
    }

    /**
     * This method calculates the number of nice subarrays.
     * A nice subarray is an array where the number of odd numbers is exactly equal to the target.
     *
     * @param arr    The input array.
     * @param target The target number of odd numbers.
     * @return The number of nice subarrays.
     * <p>
     * Example:
     * Input: arr = [1, 1, 2, 1, 1], target = 3
     * Output: 2
     * Explanation: The nice subarrays are [1, 1, 2, 1] and [1, 2, 1, 1]
     */
    public int numberOfNiceSubarrays(int[] arr, int target) {
        return numberOfNiceSubarraysHelper(arr, target) - numberOfNiceSubarraysHelper(arr, target - 1);
    }

    /**
     * This helper method calculates the number of subarrays where the number of odd numbers is less than or equal to the target.
     *
     * @param arr    The input array.
     * @param target The target number of odd numbers.
     * @return The number of subarrays where the number of odd numbers is less than or equal to the target.
     */
    public int numberOfNiceSubarraysHelper(int[] arr, int target) {
        int left = 0;
        int right = 0;
        int runningCount = 0;
        int totalCount = 0;

        while (right < arr.length) {
            // If the current element is odd, increment the running count
            if (arr[right] % 2 == 1) {
                runningCount++;
            }

            // If the running count is greater than the target, move the left pointer to the right
            while (left <= right && runningCount > target) {
                if (arr[left] % 2 == 1) {
                    runningCount--;
                }
                left++;
            }

            // Add the length of the current subarray to the total count
            totalCount += right - left + 1;

            right++;
        }

        return totalCount;
    }


    /**
     * This method calculates the number of substrings in the input string that contain all characters 'a', 'b', and 'c'.
     *
     * @param inpStr The input string.
     * @return The count of substrings that contain all characters 'a', 'b', and 'c'.
     * <p>
     * Example:
     * Input: "bbabcaa"
     * Output: 3
     * Explanation: The substrings that satisfy the condition are "bbabc", "bbabca", and "bbabcaa".
     */
    public int numberOfSubstringsContainsAllKChar(String inpStr) {
        int count = 0;
        int start = 0;
        int len = inpStr.length();
        int[] arr = new int[3];

        for (int end = 0; end < len; end++) {
            arr[inpStr.charAt(end) - 'a']++;

            // When condition is satisfied
            while (arr[0] > 0 && arr[1] > 0 && arr[2] > 0) {
                // This is key to get count.
                count += len - end;
                // Since it's a sliding window, remove from front and reduce string and check
                // condition again and keep counting
                arr[inpStr.charAt(start) - 'a']--;
                start++;
            }
        }

        return count;
    }

    /**
     * This method calculates the maximum score that can be obtained from a card game.
     *
     * @param cardPoints The points of each card.
     * @param k          The number of cards that can be taken from the beginning or end.
     * @return The maximum score that can be obtained.
     * <p>
     * Example:
     * Input: cardPoints = [1, 2, 3, 4, 5, 6, 1], k = 3
     * Output: 12
     * Explanation: Take three cards from the end, getting a score of 12.
     */
    public int maxScoreObtainFromCard(int[] cardPoints, int k) {
        int lt = 0;
        int totalSum = 0;
        int windowLenForSubArr = cardPoints.length - k;
        int runningSum = 0;

        // Find total sum
        for (int z = 0; z < cardPoints.length; z++) {
            totalSum += cardPoints[z];
        }
        // When k = len just return all.
        if (k == cardPoints.length) {
            return totalSum; // When all cards must be selected.
        }

        int minSum = totalSum; // Starting purpose.

        for (int rt = 0; rt < cardPoints.length; rt++) {
            runningSum += cardPoints[rt];

            // When window size reached get min and move window to left
            if (windowLenForSubArr == rt - lt + 1) {
                minSum = Math.min(minSum, runningSum);

                runningSum = runningSum - cardPoints[lt];
                lt++;
            }
        }
        return totalSum - minSum; // Will give max score/sum.
    }

    /**
     * This method calculates the length of the longest substring with at most k distinct characters.
     *
     * @param inpStr The input string.
     * @param k      The maximum number of distinct characters.
     * @return The length of the longest substring with at most k distinct characters.
     * <p>
     * Example:
     * Input: inpStr = "eceba", k = 2
     * Output: 3
     * Explanation: The substring "ece" has 2 distinct characters and length 3.
     */
    public int lengthOfLongestSubstringKDistinct(String inpStr, int k) {
        int lt = 0;
        int maxLen = 0;
        HashMap<Character, Integer> myBucket = new HashMap<>();

        for (int rt = 0; rt < inpStr.length(); rt++) {
            myBucket.put(inpStr.charAt(rt), myBucket.getOrDefault(inpStr.charAt(rt), 0) + 1);

            while (myBucket.size() > k) {
                char chToRemove = inpStr.charAt(lt);
                int curVal = myBucket.get(chToRemove);
                myBucket.put(chToRemove, curVal - 1);
                if (myBucket.get(chToRemove) == 0) {
                    myBucket.remove(chToRemove);
                }
                lt++;
            }

            // Counting length and not waiting to become = K because it was asking at most K so <= K is allowed to consider.
            maxLen = Math.max(rt - lt + 1, maxLen);
        }
        return maxLen;
    }

    /**
     * This method calculates the number of subarrays with exactly k distinct numbers.
     *
     * @param arr The input array.
     * @param k   The number of distinct numbers.
     * @return The number of subarrays with exactly k distinct numbers.
     * <p>
     * Example:
     * Input: arr = [1, 2, 1, 2, 3], k = 2
     * Output: 7
     * Explanation: The subarrays with exactly 2 distinct numbers are [1, 2], [2, 1], [1, 2], [2, 1, 2], [1, 2, 1], [2, 1, 2, 1], and [1, 2, 1, 2].
     */
    public int subarraysWithKDistinctUnderstableWay(int[] arr, int k) {
        return subarraysWithKDistinct_Understable_Way_helper(arr, k) -
                subarraysWithKDistinct_Understable_Way_helper(arr, k - 1);
    }


    /**
     * This method calculates the total count of subarrays with exactly K distinct elements.
     * It uses a sliding window approach to keep track of the distinct elements in the current window (subarray).
     *
     * @param arr The input array
     * @param k   The number of distinct elements
     * @return The total count of subarrays with K distinct elements
     * <p>
     * Example:
     * Input: arr = [1,2,1,2,3], K = 2
     * Output: 7
     * Explanation: Subarrays formed with exactly 2 distinct elements: [1,2], [2,1], [1,2], [2,1,2], [1,2,1], [2,1,2,1], [1,2,1,2].
     */
    public int subarraysWithKDistinct_Understable_Way_helper(int[] arr, int k) {
        int lt = 0;
        int rt = 0;
        int totalCount = 0;
        Map<Integer, Integer> bucket = new HashMap<>();

        while (rt < arr.length) {
            // Add the current element into the bucket
            bucket.put(arr[rt], bucket.getOrDefault(arr[rt], 0) + 1);

            // When the bucket is full (more than K distinct elements)
            while (bucket.size() > k) {
                // Reduce the count of the leftmost element
                int val = bucket.get(arr[lt]);
                bucket.put(arr[lt], val - 1);
                // If there are no more instances of the leftmost element, remove it from the bucket
                if (bucket.get(arr[lt]) == 0) {
                    bucket.remove(arr[lt]);
                }
                lt++;
            }

            // Count the current valid subarray
            totalCount += rt - lt + 1;
            rt++;
        }

        return totalCount;
    }

    /**
     * This method finds the minimum window substring from string 's' which contains all the characters from string 't'.
     * It uses a sliding window approach to keep track of the characters in the current window (substring).
     *
     * @param s The input string
     * @param t The target string
     * @return The minimum window substring
     * <p>
     * Example:
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     */
    public String minWindowSubString(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        // Count each character frequency of string t
        Map<Character, Integer> charFreqMap = new HashMap<>();
        for (int i = 0; i < tLen; i++) {
            charFreqMap.put(t.charAt(i), charFreqMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int lt = 0;
        int rt = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;
        int matchedCounter = 0;

        // Find t in s with min len
        while (rt < sLen) {
            // Increase len as t char is being used
            if (charFreqMap.getOrDefault(s.charAt(rt), 0) > 0) {
                matchedCounter++;
            }

            // Take out char or use it.
            charFreqMap.put(s.charAt(rt), charFreqMap.getOrDefault(s.charAt(rt), 0) - 1);

            // Check len vs counter and if matched.
            while (matchedCounter == tLen) {
                int tempLen = rt - lt + 1;
                // Only when found small len then update and set start index.
                if (tempLen < minLen) {
                    minLen = tempLen;
                    startIndex = lt;
                }

                // Shrink and try get smaller window
                // Also give it back
                charFreqMap.put(s.charAt(lt), charFreqMap.get(s.charAt(lt)) + 1);
                // Check if too much gives back and if yes reduce count
                if (charFreqMap.get(s.charAt(lt)) > 0) {
                    matchedCounter--;
                }
                lt++;
            }
            rt++;
        }

        // When no match found.
        if (startIndex == -1) return "";

        // Must rely on startIndex+minLen instead of rt as rt could have move more for other len which may not be min len.
        String minStr = s.substring(startIndex, startIndex + minLen);
        return minStr;
    }

    /**
     * This method calculates the frequency of the most frequent element in the array after performing at most 'k' operations.
     * In one operation, we can increase any element by one.
     *
     * @param arr The input array
     * @param k   The maximum number of operations
     * @return The frequency of the most frequent element after 'k' operations
     * <p>
     * Example:
     * Input: arr = [1,2,4], k = 5
     * Output: 3
     * Explanation: After 5 operations, all elements become 4. So the frequency of 4 is 3.
     */
    public int freq_Of_MaxFrequentElement(int[] arr, int k) {
        // Must be sorted to work on.
        Arrays.sort(arr);

        int lt = 0;
        int rt = 0;
        long currSum = 0;
        int target = -1;
        int maxFreq = Integer.MIN_VALUE;

        while (rt < arr.length) {
            // Set target
            target = arr[rt];
            // Calculate sum including target value
            currSum = currSum + arr[rt];
            // Required sum will be including len
            long reqSum = (long) target * (rt - lt + 1);
            // Short value
            long shortSum = reqSum - currSum;

            // When operations are not enough to fill gap
            // Shrink window and look for smaller required sum
            while (shortSum > k) {
                currSum = currSum - arr[lt];
                lt++;

                // Reevaluate short sum
                reqSum = (long) target * (rt - lt + 1);
                shortSum = reqSum - currSum;
            }

            // i.e. enough operation left to fulfill shorted sum.
            // i.e.  (k >= shortSum) {
            maxFreq = Math.max(rt - lt + 1, maxFreq);

            // Move next to find more len
            rt++;
        }

        return maxFreq;
    }

    /**
     * This method calculates the length of the longest substring without repeating characters.
     * It uses a sliding window approach to keep track of the characters in the current window (substring).
     *
     * @param inStr The input string
     * @return The length of the longest substring without repeating characters
     * <p>
     * Example:
     * Input: inStr = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     */
    public int lengthOfLongestSubstring_More_Optimized(String inStr) {
        Map<Character, Integer> mymap = new HashMap<>();

        int maxLen = 0;
        int lt = 0;

        for (int rt = 0; rt < inStr.length(); rt++) {
            var ch = inStr.charAt(rt);
            if (mymap.containsKey(ch)) {
                // If seen before..
                var ltval = mymap.get(ch); // Get next position/index that make sure previously seen char ignored in len.
                lt = Math.max(lt, ltval);
                // Find max to avoid situation where left already ahead and this map value try to bring it back
                // e.g. abba where on last a , lt is at 2 but map lt value was 1.
            }

            int len = rt - lt + 1; // Find len
            maxLen = Math.max(len, maxLen);

            mymap.put(ch, rt + 1); // Store next index or position after this car appears
        }
        return maxLen;
    }


    /**
     * This method calculates the maximum number of fruits that can be collected from a tree.
     *
     * @param fruits The types of fruits in the tree.
     * @return The maximum number of fruits that can be collected.
     * <p>
     * Example:
     * Input: fruits = [1, 2, 1]
     * Output: 3
     * Explanation: We can collect [1, 2, 1].
     */
    public int totalFruitMapSlow(int[] fruits) {
        HashMap<Integer, Integer> myMap = new HashMap<>();
        int maxLen = 0;
        int lt = 0, rt = 0;

        for (rt = 0; rt < fruits.length; ++rt) {
            var rtFruitType = fruits[rt];
            var val = myMap.getOrDefault(rtFruitType, 0);
            myMap.put(rtFruitType, val + 1); // Add fruit and its count in bucket

            if (myMap.size() > 2) { // When 2 types are already got then
                var outGoingFruitType = fruits[lt];
                var outgoingFruitVal = myMap.get(outGoingFruitType);
                myMap.put(outGoingFruitType, outgoingFruitVal - 1); // Reduce one count from bucket

                if (myMap.get(outGoingFruitType) == 0) {
                    myMap.remove(outGoingFruitType);
                }

                lt++; // Move on
            }
            maxLen = Math.max(rt - lt + 1, maxLen); // Keep getting max len for final answer
        }
        return maxLen;
    }

    /**
     * This method calculates the number of subarrays with a sum equal to a given target.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return The number of subarrays with a sum equal to the target.
     * <p>
     * Example:
     * Input: arr = [1, 1, 1], target = 2
     * Output: 2
     * Explanation: The subarrays with a sum equal to 2 are [1, 1] and [1, 1].
     */
    public int numOfSubarraysWithSumGivenGoal(int[] arr, int target) {
        int maxSubArrayCount = 0;
        int sum = arr[0]; // Start with first element

        int excludingLeft = 0;
        int zeroLeft = 0;
        int right = 0;

        while (right < arr.length) {
            while (sum > target && excludingLeft <= right) { // Move left to right by deducting left element.
                sum = sum - arr[excludingLeft];
                excludingLeft++;
            }
            while ((arr[zeroLeft] == 0 || zeroLeft < excludingLeft) && zeroLeft < right) {
                zeroLeft++;
            }
            if (sum == target) {
                maxSubArrayCount += zeroLeft - excludingLeft + 1; // Find diff len to get count
            }

            right++;
            if (right < arr.length) { // To avoid index out of bound
                sum = sum + arr[right];
            }
        }

        return maxSubArrayCount;
    }

    /**
     * This method calculates the number of subarrays with exactly k distinct numbers.
     *
     * @param arr The input array.
     * @param k   The number of distinct numbers.
     * @return The number of subarrays with exactly k distinct numbers.
     * <p>
     * Example:
     * Input: arr = [1, 2, 1, 2, 3], k = 2
     * Output: 7
     * Explanation: The subarrays with exactly 2 distinct numbers are [1, 2], [2, 1], [1, 2], [2, 1, 2], [1, 2, 1], [2, 1, 2, 1], and [1, 2, 1, 2].
     */
    public int subarraysWithKDistinct(int[] arr, int k) {
        int res = 0;
        int lt = 0;

        HashMap<Integer, Integer> myBucket = new HashMap<>();

        for (int rt = 0; rt < arr.length; rt++) {
            myBucket.put(arr[rt], myBucket.getOrDefault(arr[rt], 0) + 1);

            while (myBucket.size() > k) {
                var chToRemove = arr[lt];
                int curVal = myBucket.get(chToRemove);
                myBucket.put(chToRemove, curVal - 1);
                if (myBucket.get(chToRemove) == 0) {
                    myBucket.remove(chToRemove);
                }
                lt++;
            }

            res = res + rt - lt + 1;
        }

        // Reset bucket and lt
        lt = 0;
        myBucket.clear();
        k = k - 1;
        int resForOneLessK = 0;

        for (int rt = 0; rt < arr.length; rt++) {
            myBucket.put(arr[rt], myBucket.getOrDefault(arr[rt], 0) + 1);

            while (myBucket.size() > k) {
                var chToRemove = arr[lt];
                int curVal = myBucket.get(chToRemove);
                myBucket.put(chToRemove, curVal - 1);
                if (myBucket.get(chToRemove) == 0) {
                    myBucket.remove(chToRemove);
                }
                lt++;
            }

            resForOneLessK = resForOneLessK + rt - lt + 1;
        }

        return res - resForOneLessK;
    }


    /**
     * This method finds the longest subarray that contains only 1's by flipping at most K 0's.
     *
     * @param numArray The input array.
     * @param K        The maximum number of 0's that can be flipped.
     * @return The length of the longest subarray that contains only 1's.
     * <p>
     * Example:
     * Input: numArray = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * Output: 6
     * Explanation: The longest subarray that contains only 1's is [1,1,1,1,1,1].
     */
    public int longestOneFlip(int[] numArray, int K) {
        int zeroConsume = 0, left = 0, right = 0;
        int maxLength = 0;

        while (right < numArray.length) {
            if (numArray[right] == 0) {
                zeroConsume++; // Using it.
            }

            right++; // Moving next already

            if (zeroConsume > K) { // Over used then
                if (numArray[left] == 0) {
                    zeroConsume--; // Throwing on left and considering making available.
                }
                left++; // Always increase
            }

            maxLength = Math.max(maxLength, right - left); // Right - left because already increased right
        }
        return maxLength;
    }

    /**
     * This method replaces some characters in the input string to make the length of the longest repeating character substring as long as possible.
     *
     * @param inputString The input string.
     * @param K           The maximum number of characters that can be replaced.
     * @return The length of the longest repeating character substring.
     * <p>
     * Example:
     * Input: inputString = "ABAB", K = 2
     * Output: 4
     * Explanation: Replace the two 'B's with 'A's to get the longest repeating character substring "AAAA".
     */
    public int characterReplacement(String inputString, int K) {
        int maxFreq = 0, left = 0, right = 0, maxLength = 0;
        int[] charFreq = new int[26]; // Stores char frequency

        while (right < inputString.length()) {
            int charVal = inputString.charAt(right) - 'A';
            int tempFreqVal = charFreq[charVal];
            charFreq[charVal] = tempFreqVal + 1; // Increase frequency
            maxFreq = Math.max(maxFreq, tempFreqVal + 1); // Keep updates max frequency

            boolean isValid = (right - left + 1 - maxFreq <= K);

            if (!isValid) {
                int outgoingChar = inputString.charAt(left);
                int outgoingCharVal = outgoingChar - 'A';
                int freqOfOutChar = charFreq[outgoingCharVal];
                charFreq[outgoingCharVal] = freqOfOutChar - 1; // Decrease frequency
                left++; // Move left
            }

            maxLength = Math.max(right - left + 1, maxLength);

            right++;
        }
        return maxLength;
    }

    /**
     * This method finds the maximum number of fruits that can be collected by only collecting two types of fruits.
     *
     * @param fruits The types of fruits in the tree.
     * @return The maximum number of fruits that can be collected.
     * <p>
     * Example:
     * Input: fruits = [1,2,1]
     * Output: 3
     * Explanation: We can collect [1, 2, 1].
     */
    public int totalFruitFast(int[] fruits) {
        Map<Integer, Integer> typeCountMap = new HashMap<>();

        int maxLength = 0;
        int left = 0, right = 0;

        for (right = 0; right < fruits.length; ++right) {
            int type = fruits[right];
            typeCountMap.put(type, typeCountMap.getOrDefault(type, 0) + 1);

            while (typeCountMap.size() > 2) {
                int fruitToRemove = fruits[left];

                int fruitCount = typeCountMap.get(fruitToRemove);
                typeCountMap.put(fruitToRemove, fruitCount - 1);

                if (typeCountMap.get(fruitToRemove) == 0) {
                    typeCountMap.remove(fruitToRemove);
                }
                left++;
            }

            maxLength = Math.max(right - left + 1, maxLength);
        }
        return maxLength;
    }

}

