package exploration;

import java.util.Arrays;

public class BinarysearchDSA {

    /* 
   Binary search is a method of searching for a specific value within a sorted space. This technique involves
   dividing the space in half and then proceeding to the left or right, depending on the position of the value being
   searched for. The search concludes once the desired value is found.

It's important to note that binary search can only be performed on sorted spaces. This includes sorted arrays, sorted
 binary trees, or any other sorted structures.

In problems involving sorted spaces, a common pattern is the decision to move left or right, and upon finding the
desired value, returning it as the answer. However, the conditions for deciding when to move left or right, and what
constitutes the answer, vary from problem to problem. These conditions can only be determined by applying different
checks on the given space. Understanding where the answer lies within the sorted space can help determine whether to
move left or right, or whether the current position is the answer.

For instance, when searching for a single element in a sorted space, we typically start at the middle. From there, we
 determine whether we need to move left or right. The parameters that need to be checked to decide this direction
 will vary based on the specific problem. Once these parameters are determined, we proceed in the appropriate direction.

Additionally, it's worth noting the concepts of floor and ceiling in mathematics. The floor of a number is the
largest integer less than or equal to the number, while the ceiling is the smallest integer greater than or equal to
the number. For example, the floor of 2.31 is 2, and the ceiling of 2.31 is 3. These concepts are often used in
problems involving binary search and sorted spaces.

    * */


    /**
     * This method performs a binary search on a sorted array iteratively.
     *
     * @param  arr    The input sorted array.
     * @param  target The target value to search for.
     * @return The index of the target value if found, -1 otherwise.
     */
    public int binarySearch_Iterative(int[] arr, int target) {
        int start = 0, end = arr.length - 1;

        while (end >= start) {
            int mid = (end + start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    /**
     * This method performs a binary search on a sorted array recursively.
     *
     * @param  arr    The input sorted array.
     * @param  target The target value to search for.
     * @return The index of the target value if found, -1 otherwise.
     */
    public int binarySearch_Recursive(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        return binarySearch_Recursive_Method(arr, target, start, end);
    }

    private int binarySearch_Recursive_Method(int[] arr, int target, int start, int end) {
        // Base condition: if end is less than start, return -1
        if (end < start) return -1;

        int mid = (end + start) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (target < arr[mid]) {
            return binarySearch_Recursive_Method(arr, target, start, mid - 1);
        } else {
            return binarySearch_Recursive_Method(arr, target, mid + 1, end);
        }
    }

    /**
     * This method finds the lower bound of a target value in a sorted array.
     * The lower bound is the first or smallest index where the value at that index is greater than or equal to the
     * target.
     *
     * @param  arr    The input sorted array.
     * @param  target The target value.
     * @param  len    The length of the array.
     * @return The index of the lower bound.
     */
    public int lowerBound(int[] arr, int target, int len) {
        int start = 0, end = len - 1;
        int res = len;

        while (end >= start) {
            int mid = (end + start) / 2;

            if (arr[mid] >= target) {
                res = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }

    /**
     * This method finds the upper bound of a target value in a sorted array.
     * The upper bound is the first or smallest index where the value at that index is greater than the target.
     *
     * @param  arr    The input sorted array.
     * @param  target The target value.
     * @param  len    The length of the array.
     * @return The index of the upper bound.
     */
    public int upperBound(int[] arr, int target, int len) {
        int start = 0, end = len - 1;
        int res = len;

        while (end >= start) {
            int mid = (end + start) / 2;

            if (arr[mid] > target) {
                res = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }

    /**
     * This method finds the floor of a target value in a sorted array.
     * The floor is the largest element in the array that is less than or equal to the target.
     *
     * @param  arr    The input sorted array.
     * @param  target The target value.
     * @return The floor of the target value.
     */
    public int floor(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int res = -1;

        while (end >= start) {
            int mid = (end + start) / 2;

            if (arr[mid] <= target) {
                res = arr[mid];
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    /**
     * This method finds the ceiling of a target value in a sorted array.
     * The ceiling is the smallest element in the array that is greater than or equal to the target.
     *
     * @param  arr    The input sorted array.
     * @param  target The target value.
     * @return The ceiling of the target value.
     */
    public int ceiling(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int res = -1;

        while (end >= start) {
            int mid = (end + start) / 2;

            if (arr[mid] >= target) {
                res = arr[mid];
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }


    /**
     * This method finds the first occurrence of a target number in a sorted array.
     *
     * @param  arr    The sorted array.
     * @param  target The target number.
     * @return The index of the first occurrence of the target number.
     */
    public int first_occurrence(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int res = -1;

        while (end >= start) {
            int mid = (end + start) / 2;

            if (arr[mid] == target) {
                res = mid;
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    /**
     * This method finds the last occurrence of a target number in a sorted array.
     *
     * @param  arr    The sorted array.
     * @param  target The target number.
     * @return The index of the last occurrence of the target number.
     */
    public int last_occurrence(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int res = -1;

        while (end >= start) {
            int mid = (end + start) / 2;

            if (arr[mid] == target) {
                res = mid;
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }

    /**
     * This method counts the occurrence of a target number in a sorted array.
     *
     * @param  arr    The sorted array.
     * @param  target The target number.
     * @return The count of the target number in the array.
     */
    public int count_occurrence(int[] arr, int target) {
        int first = first_occurrence(arr, target);
        if (first == -1) {
            return first;
        }
        int last = last_occurrence(arr, target);
        return last - first + 1;
    }

    /**
     * This method finds the index where a target number would be if it were inserted in order in a sorted array.
     *
     * @param  arr    The sorted array.
     * @param  target The target number.
     * @return The index where the target number would be if it were inserted in order.
     */
    public int searchInsert(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int res = arr.length;

        while (end >= start) {
            int mid = (end + start) / 2;

            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                res = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }

    /**
     * This method finds the index of a target number in a sorted and rotated array.
     *
     * @param  arr    The sorted and rotated array.
     * @param  target The target number.
     * @return The index of the target number in the array.
     */
    public int sortedRotated_array_search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (high >= low) {
            int mid = (high + low) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            // Consider left portion and build logic for it.
            if (arr[low] <= arr[mid]) {
                if (target <= arr[mid] && target >= arr[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Else go to right part and build logic for it.
            else {
                if (target >= arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * This method searches for a target value in a sorted and rotated array that may contain duplicates.
     *
     * @param  arr    the array to be searched
     * @param  target the value to be found
     * @return true if the target is found, false otherwise
     * <p>
     * Example:
     * Input: arr = [2, 5, 6, 0, 0, 1, 2], target = 0
     * Output: true
     */
    public boolean sortedRotated_With_DuplicateNum_array_search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (high >= low) {
            int mid = (high + low) / 2;

            if (arr[mid] == target) {
                return true;
            }

            // If duplicates are found, move the pointers
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low = low + 1;
                high = high - 1;
                continue;
            }

            if (arr[low] <= arr[mid]) {
                if (target <= arr[mid] && target >= arr[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target >= arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * This method finds the minimum value in a sorted and rotated array.
     *
     * @param  arr the array to be searched
     * @return the minimum value in the array
     * <p>
     * Example:
     * Input: arr = [4, 5, 6, 7, 0, 1, 2]
     * Output: 0
     */
    public int sortedRotated_findMin(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;

        // If the array is not rotated
        if (arr[low] <= arr[high]) {
            return arr[low];
        }

        while (high >= low) {
            int mid = (high + low) / 2;

            // Try on the left and pick the smaller value as low
            if (arr[low] <= arr[mid]) {
                ans = Math.min(arr[low], ans);
                low = mid + 1;
            }
            // Try on the right and pick the smaller value as mid
            else {
                ans = Math.min(arr[mid], ans);
                high = mid - 1;
            }
        }
        return ans;
    }

    /**
     * This method finds how many times a sorted array has been rotated.
     *
     * @param  arr the rotated array
     * @return the number of times the array has been rotated
     * <p>
     * Example:
     * Input: arr = [4, 5, 6, 7, 0, 1, 2]
     * Output: 3
     */
    public int sortedRotated_howManyTimes(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ansIdx = arr.length - 1;
        int ansVal = Integer.MAX_VALUE;

        // If the array has been rotated its length - 1 times, it goes back to its original sorted position
        if (arr[low] <= arr[high]) {
            return 0; // index
        }

        while (high >= low) {
            int mid = (high + low) / 2;

            if (arr[low] <= arr[mid]) {
                if (arr[low] < ansVal) {
                    ansVal = arr[low];
                    ansIdx = low;
                }
                low = mid + 1;
            } else {
                if (arr[mid] < ansVal) {
                    ansVal = arr[mid];
                    ansIdx = mid;
                }
                high = mid - 1;
            }
        }
        return ansIdx;
    }

    /**
     * This method finds the single non-duplicate number in a sorted array where every element appears twice except
     * for one.
     *
     * @param  arr the array to be searched
     * @return the single non-duplicate number
     * <p>
     * Example:
     * Input: arr = [1, 1, 2, 3, 3, 4, 4, 8, 8]
     * Output: 2
     */
    public int singleNonDuplicate(int[] arr) {
        // Edge cases
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr[0] != arr[1]) {
            return arr[0];
        }
        if (arr[arr.length - 2] != arr[arr.length - 1]) {
            return arr[arr.length - 1];
        }

        // Start from 1 and end at n-2 as we did edge cases already
        int low = 1, high = arr.length - 2;
        int ans = -1;

        while (high >= low) {
            int mid = (high + low) / 2;

            // When the value does not match with its before and next
            if ((arr[mid] != arr[mid + 1]) && (arr[mid] != arr[mid - 1])) {
                return arr[mid];
            }

            // Based on where the answer lies, make checks to go in that direction
            if ((mid % 2 == 0 && arr[mid] == arr[mid + 1]) || (mid % 2 == 1 && arr[mid] == arr[mid - 1])) {
                low = mid + 1;
            }
            // Based on where the answer lies, make checks to go in that direction
            else {
                high = mid - 1;
            }
        }
        return ans;
    }

    /**
     * This method finds a peak element in an array.
     *
     * @param  arr the array to be searched
     * @return the index of a peak element
     * <p>
     * Example:
     * Input: arr = [1, 2, 3, 1]
     * Output: 2
     */
    public int findPeakElement(int[] arr) {
        // Edge cases
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[arr.length - 2] < arr[arr.length - 1]) {
            return arr.length - 1;
        }

        // Start from 1 and end at n-2 as we did edge cases already
        int low = 1, high = arr.length - 2;
        int ans = -1;

        while (high >= low) {
            int mid = (high + low) / 2;

            // When the value is bigger than its neighbour
            if ((arr[mid] > arr[mid + 1]) && (arr[mid] > arr[mid - 1])) {
                return mid;
            }

            // Simply based on checks that if current value is big then go right
            // maybe bigger than current value exists
            if (arr[mid] > arr[mid - 1]) {
                low = mid + 1;
            }
            // else ...
            else {
                high = mid - 1;
            }
        }
        return ans;
    }

    /**
     * This method finds the floor square root of a target number.
     *
     * @param  target the number to find the floor square root of
     * @return the floor square root of the target
     * <p>
     * Example:
     * Input: target = 37
     * Output: 6
     */
    public int floorSqrt(int target) {
        int start = 1, end = target;

        while (end >= start) {
            int mid = (end + start) / 2;
            int squareOfMid = mid * mid;

            // <= because for say 28 we get near by square root number
            // e.g. 5 x 5 => 25 < 28
            if (squareOfMid <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    /**
     * This method calculates the minimum eating speed required for Koko to eat all the bananas within the total hours.
     * Koko can decide her bananas-per-hour eating speed of the bananas pile. She will choose the smallest integer
     * such that she can eat all the bananas within the total hours.
     *
     * @param  pilesOfBanana The piles of bananas that Koko can eat.
     * @param  totalHours    The total hours within which Koko needs to eat all the bananas.
     * @return The minimum eating speed (bananas per hour) required for Koko to eat all the bananas within the total
     * hours.
     */
    public int minEatingSpeed_kokoBanana(int[] pilesOfBanana, int totalHours) {
        int maxBananas = Integer.MIN_VALUE;
        for (int b = 0; b < pilesOfBanana.length; b++) {
            maxBananas = Math.max(maxBananas, pilesOfBanana[b]);
        }

        int start = 0, end = maxBananas;

        while (end > start) {
            int hourSpent = 0;
            int bananasToEat = (end + start) / 2;

            for (int p = 0; p < pilesOfBanana.length; p++) {
                hourSpent += (int) Math.ceil((double) pilesOfBanana[p] / bananasToEat);
            }

            if (hourSpent <= totalHours) {
                end = bananasToEat;
            } else {
                start = bananasToEat + 1;
            }
        }
        return end;
    }

    /**
     * This method calculates the minimum number of days required to make the total bouquets using the total flowers.
     * Each bouquet requires totalFlowerToUse flowers to make.
     *
     * @param  bloomDay            The bloom day of each flower.
     * @param  totalBouquetsToMake The total bouquets to make.
     * @param  totalFlowerToUse    The total flowers to use for each bouquet.
     * @return The minimum number of days required to make the total bouquets using the total flowers.
     */
    public int minDays_toMake_Bouquets(int[] bloomDay, int totalBouquetsToMake, int totalFlowerToUse) {
        int maxDays = Integer.MIN_VALUE;
        int minDays = Integer.MAX_VALUE;

        for (int b = 0; b < bloomDay.length; b++) {
            maxDays = Math.max(maxDays, bloomDay[b]);
            minDays = Math.min(minDays, bloomDay[b]);
        }

        long totalFlowersNeeded = (long) totalFlowerToUse * totalBouquetsToMake;
        long totalFlowerAvailable = bloomDay.length;
        if (totalFlowerAvailable < totalFlowersNeeded) {
            return -1;
        }

        while (minDays <= maxDays) {
            int currentDay = (minDays + maxDays) / 2;
            int totalBqtsCanMakeOnCurrDay = getPossibleBouquets(bloomDay, currentDay, totalFlowerToUse);

            if (totalBqtsCanMakeOnCurrDay >= totalBouquetsToMake) {
                maxDays = currentDay - 1;
            } else {
                minDays = currentDay + 1;
            }
        }
        return minDays;
    }

    /**
     * This method calculates the possible bouquets that can be made on the current day.
     * Each bouquet requires totalFlowerToUse flowers to make.
     *
     * @param  bloomDay         The bloom day of each flower.
     * @param  currentDay       The current day.
     * @param  totalFlowerToUse The total flowers to use for each bouquet.
     * @return The possible bouquets that can be made on the current day.
     */
    private int getPossibleBouquets(int[] bloomDay, int currentDay, int totalFlowerToUse) {
        int possibleBquts = 0;
        int flowercollected = 0;

        for (int p = 0; p < bloomDay.length; p++) {
            if (currentDay >= bloomDay[p]) {
                flowercollected++;
            } else {
                flowercollected = 0;
            }
            if (flowercollected == totalFlowerToUse) {
                possibleBquts++;
                flowercollected = 0;
            }
        }
        return possibleBquts;
    }


    /**
     * This method finds the smallest divisor such that the sum of quotients of an array of numbers divided by the
     * divisor is less than or equal to a threshold.
     *
     * @param  numbers   The input array of numbers.
     * @param  threshold The threshold sum of quotients.
     * @return The smallest divisor.
     */
    public int smallestDivisor(int[] numbers, int threshold) {
        int maxDivisor = Integer.MIN_VALUE;
        int minDivisor = 1;

        for (int b = 0; b < numbers.length; b++) {
            maxDivisor = Math.max(maxDivisor, numbers[b]);
        }

        while (minDivisor < maxDivisor) {
            int currentDivNum = (minDivisor + maxDivisor) / 2;

            int thresholdForCurDivNum = 0;

            for (int b = 0; b < numbers.length; b++) {
                thresholdForCurDivNum += Math.ceil((double) numbers[b] / currentDivNum);
            }

            if (thresholdForCurDivNum <= threshold) {
                maxDivisor = currentDivNum;
            } else {
                minDivisor = currentDivNum + 1;
            }
        }

        return minDivisor;
    }

    /**
     * This method finds the minimum capacity of a ship such that all the weights can be shipped within the allowed
     * days.
     *
     * @param  weights     The input array of weights.
     * @param  allowedDays The number of allowed days.
     * @return The minimum capacity of the ship.
     */
    public int shipWithinDays(int[] weights, int allowedDays) {
        int totalWeight = 0;
        int maxWeight = Integer.MIN_VALUE;

        for (int b = 0; b < weights.length; b++) {
            totalWeight += weights[b];
            maxWeight = Math.max(maxWeight, weights[b]);
        }

        int lt = maxWeight;
        int rt = totalWeight;

        while (lt <= rt) {
            int currCapacity = (lt + rt) / 2;

            int currLoad = 0;
            int daysRequired = 1;

            for (int eachWt : weights) {
                currLoad += eachWt;
                if (currLoad > currCapacity) {
                    daysRequired++;
                    currLoad = eachWt;
                }
            }

            if (daysRequired <= allowedDays) {
                rt = currCapacity - 1;
            } else {
                lt = currCapacity + 1;
            }
        }

        return lt;
    }

    /**
     * This method finds the kth missing positive number in a sorted array.
     *
     * @param  arr The input sorted array.
     * @param  k   The kth missing positive number to find.
     * @return The kth missing positive number.
     */
    public int findKthPositive_Linear(int[] arr, int k) {
        int missingNoCounter = 0;
        int arrIdx = 0;

        while (k > 0) {
            missingNoCounter++;
            if (arrIdx < arr.length && arr[arrIdx] == missingNoCounter) {
                arrIdx++;
            } else {
                k--;
            }
        }
        return missingNoCounter;
    }

    /**
     * This method finds the maximum distance between cows in stalls such that the cows are placed as far apart as
     * possible.
     *
     * @param  stalls The input array of stalls.
     * @param  cows   The number of cows.
     * @return The maximum distance between cows.
     */
    public int maxDistance_aka_aggressiveCows_akaMagnetBucket(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int maxDist = stalls[stalls.length - 1] - stalls[0];

        for (int dist = 1; dist < maxDist; dist++) {
            if (!canFitCowInStall(stalls, cows, dist)) {
                return dist - 1;
            }
        }

        return maxDist;
    }


    /**
     * This method finds the maximum minimum distance between cows in stalls using binary search.
     *
     * @param  stalls The stalls where the cows are placed.
     * @param  cows   The number of cows.
     * @return The maximum minimum distance between cows.
     */
    public int maxDistance_aka_aggressiveCows_akaMagnetBucket_BinarySearch(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int high = stalls[stalls.length - 1] - stalls[0];
        int low = 1;
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canFitCowInStall(stalls, cows, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canFitCowInStall(int[] stalls, int cows, int minDist) {
        int lastStall = stalls[0];
        int cowPlaced = 1;

        for (int currStall = 1; currStall < stalls.length; currStall++) {
            int disBtwCurrNLast = stalls[currStall] - lastStall;

            if (disBtwCurrNLast >= minDist) {
                cowPlaced++;
                lastStall = stalls[currStall];
            }

            if (cowPlaced >= cows) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method counts the minimum number of pages that should be allocated to students such that the maximum
     * number is minimized.
     *
     * @param  pagesOfEachBook The number of pages in each book.
     * @param  students        The number of students.
     * @return The minimum number of pages that should be allocated to students.
     */
    public int countStudents_aka_allocateMinNoOfPages(int[] pagesOfEachBook, int students) {
        int totalPages = 0;
        int maxPages = Integer.MIN_VALUE;

        for (int b = 0; b < pagesOfEachBook.length; b++) {
            totalPages += pagesOfEachBook[b];
            maxPages = Math.max(maxPages, pagesOfEachBook[b]);
        }

        for (int pages = maxPages; pages < totalPages; pages++) {
            if (countStudents(pagesOfEachBook, pages) == students) {
                return pages;
            }
        }

        return maxPages;
    }

    public int countStudents_aka_allocateMinNoOfPages_BinarySearch(int[] pagesOfEachBook, int students) {
        int totalPages = 0;
        int maxPages = Integer.MIN_VALUE;

        for (int b = 0; b < pagesOfEachBook.length; b++) {
            totalPages += pagesOfEachBook[b];
            maxPages = Math.max(maxPages, pagesOfEachBook[b]);
        }

        int low = maxPages;
        int high = totalPages;

        while (low <= high) {
            int midPages = low + (high - low) / 2;
            int servedStudents = countStudents(pagesOfEachBook, midPages);

            if (servedStudents > students) {
                low = midPages + 1;
            } else {
                high = midPages - 1;
            }
        }

        return low;
    }


    /**
     * This method counts the number of students required to read all the books given a maximum number of pages a
     * student can read.
     *
     * @param  pagesOfEachBook an array representing the number of pages in each book
     * @param  mxPages         the maximum number of pages a student can read
     * @return the number of students required
     * <p>
     * Example:
     * Input: pagesOfEachBook = [100, 200, 300, 400], mxPages = 500
     * Output: 3
     */
    private int countStudents(int[] pagesOfEachBook, int mxPages) {
        int servedStudent = 1;
        int allocatedPages = 0;

        for (int book = 0; book < pagesOfEachBook.length; book++) {
            int pgs = allocatedPages + pagesOfEachBook[book];

            if (pgs <= mxPages) {
                allocatedPages = pgs;
            } else {
                servedStudent++;
                allocatedPages = pagesOfEachBook[book];
            }
        }

        return servedStudent;
    }

    /**
     * This method finds the minimum maximum number of items that can be allocated to each store.
     *
     * @param  pileOfProductsOrItems an array representing the number of items in each pile
     * @param  stores                the number of stores
     * @return the minimum maximum number of items that can be allocated to each store
     * <p>
     * Example:
     * Input: pileOfProductsOrItems = [30, 11, 23, 4, 20], stores = 5
     * Output: 30
     */
    public int minimizedMaximum_Similar_ToKokoBanana(int[] pileOfProductsOrItems, int stores) {
        int maxCountOfItems = Integer.MIN_VALUE;
        for (int b = 0; b < pileOfProductsOrItems.length; b++) {
            maxCountOfItems = Math.max(maxCountOfItems, pileOfProductsOrItems[b]);
        }

        int low = 1, high = maxCountOfItems;

        while (low < high) {
            int storesServerd = 0;
            int currentItemCount = (high + low) / 2;

            for (int p = 0; p < pileOfProductsOrItems.length; p++) {
                storesServerd += (int) Math.ceil((double) pileOfProductsOrItems[p] / currentItemCount);
            }

            if (storesServerd > stores) {
                low = currentItemCount + 1;
            } else {
                high = currentItemCount;
            }
        }
        return low;
    }

    /**
     * This method splits an array into a specified number of subarrays such that the maximum sum of any subarray is
     * minimized.
     *
     * @param  arr             the array to be split
     * @param  countOfSubArray the number of subarrays
     * @return the minimum possible maximum sum of any subarray
     * <p>
     * Example:
     * Input: arr = [7, 2, 5, 10, 8], countOfSubArray = 2
     * Output: 18
     */
    public int splitArray_aka_allocateMinNoOfPages_BinarySearch(int[] arr, int countOfSubArray) {
        int totalSum = 0;
        int maxVal = Integer.MIN_VALUE;

        for (int b = 0; b < arr.length; b++) {
            totalSum += arr[b];
            maxVal = Math.max(maxVal, arr[b]);
        }

        int low = maxVal;
        int high = totalSum;

        while (low <= high) {
            int midPages = low + (high - low) / 2;

            int servedSubArr = countSplitArray(arr, midPages);

            if (servedSubArr > countOfSubArray) {
                low = midPages + 1;
            } else {
                high = midPages - 1;
            }
        }

        return low;
    }


    /**
     * This method counts the number of subarrays that can be formed from the given array such that the sum of each
     * subarray is less than or equal to the maximum sum.
     *
     * @param  arr   The input array.
     * @param  mxSum The maximum sum of each subarray.
     * @return The number of subarrays that can be formed.
     */
    private int countSplitArray(int[] arr, int mxSum) {
        int splitArrayCount = 1;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            int curSum = sum + arr[i];

            if (curSum <= mxSum) {
                sum = curSum;
            } else {
                splitArrayCount++;
                sum = arr[i];
            }
        }

        return splitArrayCount;
    }

    /**
     * This method finds the kth smallest element in two sorted arrays using binary search.
     *
     * @param  arr1 The first sorted array.
     * @param  arr2 The second sorted array.
     * @param  k    The index of the element to find (0-based indexing).
     * @param  len1 The length of the first array.
     * @param  len2 The length of the second array.
     * @return The kth smallest element in the two sorted arrays.
     */
    public int kthelement_sortedArrays_BinarySearch(int[] arr1, int[] arr2, int k, int len1, int len2) {
        if (len1 > len2) {
            return kthelement_sortedArrays_BinarySearch(arr2, arr1, k, len2, len1);
        }

        int low = Math.max(0, k - len1);
        int high = Math.min(k, len2);

        while (low <= high) {
            int cut1 = (high + low) / 2;
            int cut2 = k - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int r1 = cut1 == len1 ? Integer.MAX_VALUE : arr1[cut1];

            int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r2 = cut2 == len2 ? Integer.MAX_VALUE : arr2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l2, l1);
            } else if (l1 > l2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return -1;
    }

    /**
     * This method finds the median of two sorted arrays.
     *
     * @param  arr1 The first sorted array.
     * @param  arr2 The second sorted array.
     * @return The median of the two sorted arrays.
     */
    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;

        if (len1 > len2) {
            return findMedianSortedArrays(arr2, arr1);
        }

        int low = 0;
        int high = len1;

        while (low <= high) {
            int cut1 = (high + low) / 2;
            int cut2 = (len1 + len2 + 1) / 2 - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int r1 = cut1 == len1 ? Integer.MAX_VALUE : arr1[cut1];

            int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r2 = cut2 == len2 ? Integer.MAX_VALUE : arr2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                if ((len1 + len2) % 2 == 1) {
                    return Math.max(l2, l1);
                } else {
                    int leftbig = Math.max(l1, l2);
                    int rightsmall = Math.min(r1, r2);
                    return (double) (leftbig + rightsmall) / 2.0;
                }
            } else if (l1 > l2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return -1;
    }

    /**
     * This method finds the row with the maximum number of ones in a binary matrix.
     * The matrix is sorted in non-increasing order.
     *
     * @param  mat The binary matrix.
     * @return An array of two integers. The first integer is the row with the maximum number of ones and the second
     * integer is the maximum number of ones.
     */
    public int[] rowAndMaximumOnes(int[][] mat) {
        int lenR = mat.length;
        int maxColumWOne = 0;
        int rowHasMaxCol = -1;

        for (int rw = 0; rw < lenR; rw++) {
            int low = 0, high = mat[rw].length - 1;

            int firstIdxOfOne = mat[rw].length;

            while (low <= high) {
                int midIdx = (high + low) / 2;
                if (mat[rw][midIdx] >= 1) {
                    firstIdxOfOne = midIdx;
                    high = midIdx - 1;
                } else {
                    low = midIdx + 1;
                }
            }

            int totalOneForthisRow = mat[rw].length - firstIdxOfOne;
            maxColumWOne = Math.max(maxColumWOne, totalOneForthisRow);
            if (maxColumWOne >= mat[rw].length) {
                return new int[]{rw, maxColumWOne};
            }
            rowHasMaxCol = rw;
        }
        return new int[]{rowHasMaxCol, maxColumWOne};
    }


    /**
     * This method performs a binary search on a 2D matrix.
     *
     * @param  mat    The input 2D matrix.
     * @param  target The target value to search for.
     * @return True if the target value is found, false otherwise.
     */
    public boolean searchMatrix(int[][] mat, int target) {
        int lenR = mat.length;
        int lenC = mat[0].length;

        int low = 0, high = (lenR * lenC) - 1;
        while (low <= high) {
            int mid = (high + low) / 2;
            int row = mid / lenC;
            int col = mid % lenC;

            int midVal = mat[row][col];
            if (midVal == target) {
                return true;
            } else if (midVal > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    /**
     * This method searches for a target value in a 2D matrix where each row and column is sorted separately.
     *
     * @param  mat    The input 2D matrix.
     * @param  target The target value to search for.
     * @return True if the target value is found, false otherwise.
     */
    public boolean searchMatrix_2_RowNColSortedSeparately(int[][] mat, int target) {
        int lenR = mat.length;
        int lenC = mat[0].length;

        int row = 0;
        int col = lenC - 1;

        while (row <= lenR - 1 && col >= 0) {
            int curVal = mat[row][col];
            if (curVal == target) {
                return true;
            } else if (curVal > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * This method finds the peak grid in a 2D matrix.
     * A peak grid is a grid that is greater than its four neighbors (top, bottom, left, and right).
     *
     * @param  mat The input 2D matrix.
     * @return The coordinates of the peak grid.
     */
    public int[] findPeakGrid(int[][] mat) {
        int lenR = mat.length;
        int lenC = mat[0].length;

        int low = 0;
        int high = lenC - 1;

        while (low <= high) {
            int midColIdx = (high + low) / 2;
            int maxRowIdx = 0;
            for (int r = 0; r < lenR; r++) {
                if (mat[r][midColIdx] > mat[maxRowIdx][midColIdx]) {
                    maxRowIdx = r;
                }
            }

            int left = midColIdx - 1 >= 0 ? mat[maxRowIdx][midColIdx - 1] : -1;
            int right = midColIdx + 1 < lenC ? mat[maxRowIdx][midColIdx + 1] : -1;

            if (mat[maxRowIdx][midColIdx] > left && mat[maxRowIdx][midColIdx] > right) {
                return new int[]{maxRowIdx, midColIdx};
            } else if (mat[maxRowIdx][midColIdx] < left) {
                high = midColIdx - 1;
            } else {
                low = midColIdx + 1;
            }
        }

        return new int[]{-1, -1};
    }


    /**
     * This method finds the row with the maximum number of ones in a binary matrix.
     *
     * @param  mat The binary matrix.
     * @return An array containing the row index and the maximum number of ones.
     */
    public int[] rowAndMaximumOnes_EXTRA(int[][] mat) {
        int lenR = mat.length;
        int maxColumWOne = 0;
        int rowHasMaxCol = -1;

        for (int rw = lenR - 1; rw >= 0; rw--) {
            int low = 0, high = mat[rw].length - 1;
            int firstIdxOfOne = mat[rw].length;

            while (low <= high) {
                int midIdx = (high + low) / 2;
                if (mat[rw][midIdx] >= 1) {
                    firstIdxOfOne = midIdx;
                    high = midIdx - 1;
                } else {
                    low = midIdx + 1;
                }
            }

            int totalOneForthisRow = mat[rw].length - firstIdxOfOne;
            maxColumWOne = Math.max(maxColumWOne, totalOneForthisRow);
            rowHasMaxCol = rw;
        }
        return new int[]{rowHasMaxCol, maxColumWOne};
    }

    /**
     * This method finds the kth smallest element in two sorted arrays.
     *
     * @param  arr1 The first sorted array.
     * @param  arr2 The second sorted array.
     * @param  k    The index of the element to find.
     * @return The kth smallest element in the two sorted arrays.
     */
    public int kthelement_sortedArrays(int[] arr1, int[] arr2, int k) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int p1 = 0, p2 = 0, counter = 0, res = 0;

        while (p1 < len1 && p2 < len2) {
            if (counter == k) {
                break;
            }
            if (arr1[p1] < arr2[p2]) {
                res = arr1[p1];
                p1++;
            } else {
                res = arr1[p2];
                p2++;
            }
            counter++;
        }

        if (counter != k) {
            if (p1 < len1) {
                res = arr1[k - counter];
            } else {
                res = arr2[k - counter];
            }
        }

        return res;
    }

    /**
     * This method finds the kth smallest element in two sorted arrays using binary search.
     *
     * @param  arr1 The first sorted array.
     * @param  arr2 The second sorted array.
     * @param  k    The index of the element to find.
     * @param  len1 The length of the first sorted array.
     * @param  len2 The length of the second sorted array.
     * @return The kth smallest element in the two sorted arrays.
     */
    public int kthelement_sortedArrays_BinarySearch_ORG_NOTES(int[] arr1, int[] arr2, int k, int len1, int len2) {
        if (len1 > len2) {
            return kthelement_sortedArrays_BinarySearch_ORG_NOTES(arr2, arr1, k, len2, len1);
        }

        int low = Math.max(0, k - len1);
        int high = Math.min(k, len2);

        while (low <= high) {
            int cut1 = (high + low) / 2;
            int cut2 = k - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int r1 = cut1 == len1 ? Integer.MAX_VALUE : arr1[cut1];

            int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r2 = cut2 == len2 ? Integer.MAX_VALUE : arr2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l2, l1);
            } else if (l1 > l2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return -1;
    }
}
