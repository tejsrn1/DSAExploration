package a.expo.helpers.sq;

import java.util.*;
public class StackUsingQueue {

    /*
     Basically need to use Queue DS to make Stack

     inserted 5 then 4 then 6
     but when out 6 then 4 then 5

    |  6|
    | 4 |
    |_5_|

    Now same with Queue inserted above 5  4 and 6
    when out 5 4 6 but we need stack out 6 4 5

    <----- Queue
    -------------
    5 4 6
    -----------

    idea is to pick from fron to Queue old inserted elements are put behind newly added elelments
    will do..


    * */

    Queue<Integer> javaQ = new LinkedList<>();

    public void push(int val) {
        javaQ.add(val);
        int sz =0;
        while ( sz < size()-1){ //except newly added i.e. - 1
            javaQ.add(javaQ.remove());// remove from front and put it back
            sz++;
        }
    }

    public int pop() {
        return  javaQ.remove();
    }

    public int peek() {
        return javaQ.peek();
    }


    public int size() {
        return javaQ.size();
    }
}
