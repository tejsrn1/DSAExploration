package exploration.helpers.sq;

public class QueueWithArray {
    /*
    Create Queue with Array
    Queue= First In First Out FIFO.

    4 goes IN first then 5 then 8
    when it time to out
    4 comes out then 5 then 8
    <----- Queue
    -------------
    4 5 8 2 12 6
    -------------
    Push/Enqueue i.e insert
    Pop/Dequeue i.e. remove first one
    peek or top i.e. just return first not remove
    size i.e. stack size.

    * */

    //setup
    int maxSize = 1000;
    int currSize = 0;
    int[] arr = new int[maxSize];
    int start = -1;
    int end = -1;

    public void push(int val) {
        if (currSize == maxSize) {
            System.out.println("Queue is full");
            System.exit(1);
        }

        if (end == -1) {
            start++;
            end++;
        } else {
            //just to get next index
            // with mod with maxSize
            // to make ure it fits in array.
            end = (end + 1) % maxSize;
        }
        arr[end] = val;
        currSize++;
    }

    public int pop() {

        if (start == -1) {
            System.out.println("Queue is Empty");
            System.exit(1);
        }

        int t = arr[start];
        //to make sure emptying Q.
        if (currSize == 1) {
            start = -1;
            end = -1;
        } else {
            start = (start + 1) % 2;
        }
        currSize--;
        return t;
    }


    public int peek() {
        if (start == -1) {
            System.out.println("Queue is Empty");
            System.exit(1);
        }

        int t = arr[start];
        return t;
    }


    public int size() {
        return currSize;
    }

}
