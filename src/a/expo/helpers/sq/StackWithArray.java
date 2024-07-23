package a.expo.helpers.sq;

public class StackWithArray {

    /*
    Create Stack using Array
    Stack  =  Last In First Out LIFO
    inserted 5 then 4 then 6 and out 6 then 4 then 5

    |  6|
    | 4 |
    |_5_|

    Push i.e insert
    Pop i.e. remove first one
    peek or top i.e. just return first not remove
    size i.e. stack size.
    * */

    //setup
    int size = 1000;
    int[] arr = new int[size];
    int top = -1;

    public void push(int val) {
        top++;
        arr[top] = val;
    }

    public int pop() {
        int t = arr[top];
        top--;
        return t;
    }

    public int peek() {
        int t = arr[top];
        return t;
    }


    public int size() {
        return top + 1;///top is index
    }


}
