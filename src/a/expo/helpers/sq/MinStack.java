package a.expo.helpers.sq;

import java.util.Stack;

public class MinStack {
    /*
    Idea:
    Regular stack and Min stack :
        all goes in Regular stack like normal
        but if values <= min stack peek() it goes there too.

    While pop it check if vale == main stack vale then pop from both stack else go normal pop
    peek and get Min works normal from Regular stack.

    * */

    Stack<Integer> regularStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public MinStack() {

    }

    public void push(int val) {
        // only min values then stored in min stack goes to min stack
        if (minStack.isEmpty() ||  val <=minStack.peek()) {
            minStack.push(val);
        }
        // all goes to Main stack.
        regularStack.push(val);
    }

    public void pop() {
        if (minStack.peek().equals(regularStack.peek())) {
            minStack.pop();
        }
        regularStack.pop();
    }

    public int top() {
        return regularStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
