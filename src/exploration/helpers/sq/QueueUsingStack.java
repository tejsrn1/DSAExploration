package exploration.helpers.sq;

import java.util.Stack;

public class QueueUsingStack {

    /*
     to create Q from Stack we need 2 stack

     Que - In 6 4 5 then out is also  6 4 5

     Idea: keep adding in first stack and when pop first all S1 items in to S2 stack and keep pop from there
     when its empty agian fill it back fro S1 i.e O 1 for pop Amortize like ArrayList.

       s1              s2   and when pop from s2 6 4 5 like Q.
      |  5|          |  6|
      | 4 |          |  4|
      |_6_|          |  5|



    * */

    Stack<Integer> staging = new Stack<>();
    Stack<Integer> actual = new Stack<>();

    public void push(int x) {
        staging.push(x);
    }

    public int pop() {
        if (actual.isEmpty()) {
            while (!staging.isEmpty()) {
                actual.push(staging.pop());
            }
        }
        return actual.pop();
    }

    public int peek() {
        if (actual.isEmpty()) {
            while (!staging.isEmpty()) {
                actual.push(staging.pop());
            }
        }
        return actual.peek();
    }

    public boolean empty() {
        if (actual.isEmpty() && staging.isEmpty()) {
            return true;
        }
        return false;
    }

    public int size() {
        return staging.size() + actual.size();
    }
}
