package a.expo.helpers.heaps;

import java.util.List;

public class BinaryMinHeap {

    /*
   Notes:

  ** Binary Heap is a Binary tree which satisfy below 2 condition and
  If any of the two conditions are violated then we should make necessary modifications in a heap
  to satisfy the two conditions.

               1. Complete Binary Tree i.e. All levels are complete except last level and it is complele as much
               as from left --> Right

                            1
                          /  \
                         2    3
                        / \
                       4   5

               2. Satisfy Min or Max heap conditions.
               - Min Heap i.e. Parent < both child left and Right
               - Max Heap i..e Parent > both child left and Right.

                       Min Heap
                            1
                          /  \
                         2    3
                        / \
                       4   5


                       Max Heap
                            15
                          /    \
                         6      8
                        / \    /  \
                       4   5  7    3

    ** Heap can be implemented by Array
              i = parent
              2i+1 left child
              2i+2 right child

            if i is given then Parent = i -1 / 2;

    ** Restoring Heap by fixing violation is called Heapify.
        - Consider subtree of voilated index is Valid.
        - How to fix?
            1. Find min from all 3 nodes i.e. from violated parent or  left or right.
            2. if left is min then swap voilated parent with it else for right.
            3. keep doing til parent is no longer violated node or reached size of tree.

   * */


    int capacity;
    int size;
    int[] arr;

    List<Integer> list;

    BinaryMinHeap(int capacity) {
        this.capacity = capacity;
        size = 0;
        arr = new int[this.capacity];
    }

    int getParentIdx(int idx) {
        int p = (idx - 1) / 2;
        return p;
    }

    int getLeftChildIdx(int idx) {
        int l = 2 * idx + 1;
        return l;
    }

    int getRightChildIdx(int idx) {
        int r = 2 * idx + 2;
        return r;
    }

    int getMin() {
        return arr[0];// parent is min
    }


    public void insert(int val) {
        if (size == capacity) {
            System.out.println("Heap is Full");
            return;
        }
        //add at sz idx and increase it
        arr[size] = val;
        int k = size;// capture idx before size increased.
        size++;

        //now fix min heap if it is not valid after adding new value.
        // swap with parent if its smaller then parent.

        //do swap until parent > val and idx is not reached to 0
        while (k != 0 && arr[getParentIdx(k)] > arr[k]) {
            //swap
            int temp = arr[getParentIdx(k)];
            arr[getParentIdx(k)] = arr[k];
            arr[k] = temp;
            //now capture parent index as k for next iteration.
            k = getParentIdx(k);
        }

    }

    public void heapify(int idx) {
        int lt = getLeftChildIdx(idx);
        int rt = getRightChildIdx(idx);
        int smallestIdx = idx;// assume violated node index is the samllest for start.

        //if left under size or len
        if (lt < size && arr[lt] < arr[smallestIdx]) {
            smallestIdx = lt;
        }
        //if right under size or len
        if (rt < size && arr[rt] < arr[smallestIdx]) {
            smallestIdx = rt;
        }

        //if parent is the smallest index then stop
        // as either its valid now OR
        // SIZE of array is reached
        while (smallestIdx != idx) {
            //swap

            int temp = arr[smallestIdx];
            arr[smallestIdx] = arr[idx];
            arr[idx] = temp;
            heapify(smallestIdx);
        }

    }

    // Add new value at index
    // after adding fix min heap if its violated
    public void decreaseKey(int new_val, int idx) {

        arr[idx] = new_val;

        //now fix min heap if it is not valid after adding new value.
        // swap with parent if its smaller then parent.

        //do swap until parent > val and idx is not reached to 0
        while (idx != 0 && arr[getParentIdx(idx)] > arr[idx]) {
            //swap
            int temp = arr[getParentIdx(idx)];
            arr[getParentIdx(idx)] = arr[idx];
            arr[idx] = temp;
            //now capture parent index as k for next iteration.
            idx = getParentIdx(idx);
        }

    }


}
