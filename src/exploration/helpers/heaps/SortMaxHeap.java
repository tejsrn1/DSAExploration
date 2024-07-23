package exploration.helpers.heaps;

public class SortMaxHeap {

    /*
    -  Sort Asc or Desc
    -  Similar to Selection Sort i.e. go over all array and find min value and swap with outer index
    -  Max Heap is the way to go as there parent is > from both left and right
    -  so swap max root node with last small node then delete last node


    * */

    //Just Create Max Heap and keep heapify and
    // DO NOT do any sorting here.
    public void createMaxHeap(int[] arr, int len) {
        // create and heapify sametime

        //why go only half bcs in heapify we operate on childs of this half parent will cover whole array.
        int mid = (len / 2) - 1;
        for (int i = mid; i >= 0; i--) {
            heapify(i, arr, len);
        }

    }


    // First Create Max heap
    // then comes here for Sort where it swap bigger to smallest and then heapify to bring largest on top
    // keep doing this will make ascending sort
    // e.g.
    // 15 7 3 2 1 <- came as heapify
    // 1 7 3 2 15 <- 1st iteration
    // 7 1 3 2 15 <- after heapify
    // 2 1 3 7 15 <- 2nd iteration and keep going
    // Idea is swap and then fix max heap and then swap and again heapify()
    public void sortMaxHeap(int[] arr, int len) {


        //>0  as when reached at 1 idx it can be called sorted.
        for (int i = len - 1; i > 0; i--) {
            //swap large < > samll
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // len keep reducing as i +1 is already sorted. and send 0 for swap always.
            heapify(0, arr, i);
        }

    }

    public void heapify(int idx, int[] arr, int len) {

        int lt = 2 * idx + 1;
        int rt = 2 * idx + 2;
        int largestIdx = idx;// assume incoming parent is the largest

        //if left under size or len
        if (lt < len && arr[lt] > arr[largestIdx]) {
            largestIdx = lt;
        }
        //if right under size or len
        if (rt < len && arr[rt] > arr[largestIdx]) {
            largestIdx = rt;
        }
        //if parent is the largest index then stop
        // as either its valid now OR
        // SIZE of array is reached
        if (largestIdx != idx) {
            //swap
            int temps = arr[idx];
            arr[idx] = arr[largestIdx];
            arr[largestIdx] = temps;
            heapify(largestIdx, arr, len);  //send further largest to fix or hapify
        }
    }
}


