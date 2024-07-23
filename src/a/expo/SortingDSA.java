package a.expo;

public class SortingDSA {


    /**
     * This method sorts an array using the Selection Sort algorithm.
     *
     * @param inputArr The input array to be sorted.
     */
    public void SelectionSort(int[] inputArr) {
        for (int outerIdx = 0; outerIdx < inputArr.length; outerIdx++) {
            int minIdx = outerIdx;

            // Find the minimum element in the unsorted part of the array
            for (int inrIdx = outerIdx + 1; inrIdx < inputArr.length; inrIdx++) {
                if (inputArr[inrIdx] < inputArr[minIdx]) {
                    minIdx = inrIdx;
                }
            }

            // Swap the found minimum element with the first element of the unsorted part
            int temp = inputArr[outerIdx];
            inputArr[outerIdx] = inputArr[minIdx];
            inputArr[minIdx] = temp;
        }
    }

    /**
     * This method sorts an array using the Bubble Sort algorithm.
     *
     * @param inputArr The input array to be sorted.
     */
    public void BubbleSort(int[] inputArr) {
        for (int outIdx = inputArr.length - 1; outIdx >= 0; outIdx--) {
            for (int inrIdx = 0; inrIdx <= outIdx - 1; inrIdx++) {
                // If the current element is greater than the next element, swap them
                if (inputArr[inrIdx] > inputArr[inrIdx + 1]) {
                    int temp = inputArr[inrIdx];
                    inputArr[inrIdx] = inputArr[inrIdx + 1];
                    inputArr[inrIdx + 1] = temp;
                }
            }
        }
    }

    /**
     * This method sorts an array using the Insertion Sort algorithm.
     *
     * @param inputArr The input array to be sorted.
     */
    public void InsertionSort(int[] inputArr) {
        for (int outIdx = 1; outIdx < inputArr.length; outIdx++) {
            int bkwIdx = outIdx;

            // Move elements of inputArr[0..outIdx-1], that are greater than key, to one position ahead of their current position
            while (bkwIdx > 0 && inputArr[bkwIdx - 1] > inputArr[bkwIdx]) {
                int temp = inputArr[bkwIdx];
                inputArr[bkwIdx] = inputArr[bkwIdx - 1];
                inputArr[bkwIdx - 1] = temp;

                bkwIdx--;
            }
        }
    }


    /**
     * This method sorts an array using the MergeSort algorithm.
     *
     * @param inputArr The array to be sorted.
     * @param start    The starting index of the portion of the array to be sorted.
     * @param end      The ending index of the portion of the array to be sorted.
     *                 <p>
     *                 The MergeSort algorithm works as follows:
     *                 1. Divide the array into two halves.
     *                 2. Recursively sort both halves.
     *                 3. Merge the two halves together, comparing values to ensure order.
     *                 <p>
     *                 Example:
     *                 Given an array [5, 4, 3, 2, 1], MergeSort will first divide it into [5, 4, 3] and [2, 1].
     *                 After sorting both halves, we get [3, 4, 5] and [1, 2].
     *                 Merging these together gives us the final sorted array [1, 2, 3, 4, 5].
     */
    public void MergeSort(int[] inputArr, int start, int end) {
        // Base case: if the portion of the array to be sorted contains one or zero elements, it is already sorted.
        if (end <= start) {
            return;
        }

        // Calculate the middle index of the portion of the array to be sorted.
        int mid = (start + end) / 2;

        // Recursively sort the left half of the array.
        MergeSort(inputArr, start, mid);

        // Recursively sort the right half of the array.
        MergeSort(inputArr, mid + 1, end);

        // Merge the two sorted halves together.
        mergeArray(inputArr, start, end, mid);
    }


    /**
     * This method merges two sorted sub-arrays of a given array into a single sorted sub-array.
     * The two sub-arrays are defined by their start, mid, and end indices.
     * <p>
     * This method is part of the Merge Sort algorithm, which is a divide-and-conquer
     * algorithm for sorting an array. It divides the array into two halves, recursively sorts them, and then merges them. This method is responsible for the merging step. The time complexity of this method is O(n), where n is the number of elements in the sub-array defined by start and end. The space complexity is also O(n) due to the use of a temporary array. The overall time complexity of the Merge Sort algorithm is O(n log n).
     *
     * @param inputArr The original array which contains the sub-arrays to be merged.
     * @param start    The starting index of the first sub-array.
     * @param mid      The ending index of the first sub-array and mid+1 is the starting index of the second sub-array.
     * @param end      The ending index of the second sub-array.
     */
    void mergeArray(int[] inputArr, int start, int end, int mid) {
        // Create a temporary array to hold the merged result
        int[] tempA = new int[end - start + 1];
        int tempPointer = 0;

        // Initialize pointers for both sub-arrays
        int leftPointer = start;
        int rightPointer = mid + 1;

        // Merge smaller elements from each sub-arrays until one of them is empty
        while (leftPointer <= mid && rightPointer <= end) {
            if (inputArr[leftPointer] <= inputArr[rightPointer]) {
                tempA[tempPointer] = inputArr[leftPointer];
                tempPointer++;
                leftPointer++;
            } else {
                tempA[tempPointer] = inputArr[rightPointer];
                tempPointer++;
                rightPointer++;
            }
        }

        // If there are remaining elements in the left sub-array, add them to the merged array
        while (leftPointer <= mid) {
            tempA[tempPointer] = inputArr[leftPointer];
            tempPointer++;
            leftPointer++;
        }

        // If there are remaining elements in the right sub-array, add them to the merged array
        while (rightPointer <= end) {
            tempA[tempPointer] = inputArr[rightPointer];
            tempPointer++;
            rightPointer++;
        }

        // Copy the sorted sub-array back to the original array
        for (int x = start; x <= end; x++) {
            // The index in the temporary array corresponds to the index in the original array minus the start index
            inputArr[x] = tempA[x - start];
        }
    }


    /**
     * This method sorts an array using the QuickSort algorithm.
     * It finds a partition index by making all smaller values to the left of the partition and all larger values to the right.
     * It then recursively sorts the left and right parts from the partition index.
     *
     * @param inputArr The input array.
     * @param low      The starting index for the sort.
     * @param high     The ending index for the sort.
     */
    public void QuickSort(int[] inputArr, int low, int high) {
        if (low > high) {
            return;
        }
        var partitionIdx = partitions(inputArr, low, high);
        QuickSort(inputArr, low, partitionIdx - 1);
        QuickSort(inputArr, partitionIdx + 1, high);
    }

    /**
     * This method partitions the array for the QuickSort algorithm.
     * It moves all smaller values to the left of the partition and all larger values to the right.
     * It then swaps the low value with the y value to get the new pivot index.
     *
     * @param givenArray The array to be partitioned.
     * @param low        The starting index for the partition.
     * @param high       The ending index for the partition.
     * @return The partition index.
     */
    private int partitions(int[] givenArray, int low, int high) {
        int pivotP = givenArray[low];
        int x = low;
        int y = high;

        while (x < y) {
            while (givenArray[x] <= pivotP && x <= high - 1) {
                x++;
            }
            while (givenArray[y] > pivotP && y >= low + 1) {
                y--;
            }

            if (x < y) {
                int temp = givenArray[x];
                givenArray[x] = givenArray[y];
                givenArray[y] = temp;
            }
        }

        var temp = givenArray[low];
        givenArray[low] = givenArray[y];
        givenArray[y] = temp;

        return y;
    }


}
