package exploration.helpers.sq;

import java.util.Stack;

public class StockSpanner {

    /*
    Idea :
     BF :  maintains some array and keep traversing back to get <= today price to calculate span.

    Better ON : Using monotonic decreasing stack i.e. bottom is high --> low @top

    Each day Price   -> 100  80  60  70  60  75  85
    span as of today ->  1   1    1  2   3   4    6

    2 bcs <= 70 and like wise 6 bcs <=85 which is == 80 60  70  60  75  85 == 6 span.

    * */

    //Monotonic increasing span with $ value decreasing and also stores day (span for that price) for
    //future counting.
    Stack<int[]> stk;

    public StockSpanner() {
        stk = new Stack<>();
    }

    public int next(int price) {
        int span = 1; // 1 bcs today span..

        //if violates stack
        while (!stk.isEmpty() && stk.peek()[0] <= price) {

            int oldDaySpan = stk.pop()[1];
            //add in to today one.
            span += oldDaySpan;
        }

        //collect and push in to stack
        int[] res = new int[]{price, span};
        stk.push(res);

        System.out.println(span);

        return span;

    }
}
