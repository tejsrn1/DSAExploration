package exploration;

import java.util.*;

public class StackQueue {

    /*
    Notes:

    Integer division is division in which the fractional part (remainder) is discarded
    is called integer division and is sometimes denoted \.

       Regular division :  10/3=3 and remainder 1,
       Integer division :  10\3=3 and remainder is discarded i.e 0.

    * */

    public boolean isValidParentheses(String inputStr) {

        Stack<Character> stack = new Stack<>();

        for (int x = 0; x < inputStr.length(); x++) {

            char ch = inputStr.charAt(x);

            if (ch == '[' || ch == '{' || ch == '(') {
                stack.push(ch);

            } else {

                //nothing to check against.
                if (stack.isEmpty()) {
                    return false;
                }

                char storedVal = stack.pop();

                if ((ch == ']' && storedVal == '[') || (ch == '}' && storedVal == '{') || (ch == ')' && storedVal == '(')) {
                    continue;
                } else {
                    return false;
                }
            }

        }

        return stack.isEmpty(); // should be empty to balance out all brackets.
    }


    public String IN_TO_POST_FIX(String inputStr) {

        /*
        Idea:  Use (p+q)*(m-n) to understand code

        In Fix i.e. traditional method  like (p+q)...

        Computer can not understand it so POST Fix
        pq+  as the name suggests has the operator placed right after the two operands

        postfix expression itself determines the precedence of operators
        i.e the operator which occurs first operates on the operand


        * */

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int c = 0; c < inputStr.length(); c++) {

            char ch = inputStr.charAt(c);

            //if its operand char or digit
            if (Character.isLetterOrDigit(ch)) {
                sb.append(ch);
            }
            //if bracket
            else if (ch == '(') {
                stack.push(ch);
            }
            //if closing bracket
            else if (ch == ')') {
                // remove all operators from stack until it hit ) bracket
                while (stack.peek() != '(' && !stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                stack.pop(); // to ensure ( also remvoed from stack
            } else {
                // when higher priority operators like ^ present lower priority like -
                // can not go on top so append higher priority operator to result and store
                // lower one instack.
                //e.g. a+b*(c^d-e) => abcd and when - goes it pop ^ and append to result and store lower instack
                // abcd^ and then moves for other chars.
                while (!stack.isEmpty() && getPriority(ch) <= getPriority(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            }

        }

        //empty remaining operators from stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "BAD EXPRESSION FOUND";
            }
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private int getPriority(char ch) {
        //higher no is higher priority.
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }

        return -1;
    }

    public int calculator_II_INtoPostBased(String inputStr) {

        /*
         Idea:
         Based on In Fix to Post Fix approach but here calculate numbers not prep string.
         Operator works one step behind in sequence of all operators.
         i.e. if / came now and prev operator was + then for now prev + operator applies
         and  for next time / will become operator.

        * */
        if (inputStr == null) {
            return 0;
        }


        Stack<Integer> stack = new Stack<>();
        int curNum = 0;
        char operator = '+'; // to start with

        for (int c = 0; c < inputStr.length(); c++) {

            char ch = inputStr.charAt(c);

            if (Character.isDigit(ch)) {
                curNum = curNum * 10 + ch - '0';
            }
            // we cant use else as mostly last char is always digit e.g. 2+3 * 5
            // and this 5 needs to be handled at this iteration only..
            if (!Character.isDigit(ch) && !Character.isWhitespace(ch) || c == inputStr.length() - 1) {

                // store +7
                if (operator == '+') {
                    stack.push(curNum);
                }

                //store -7
                if (operator == '-') {
                    stack.push(-curNum);
                }

                // -7 * 5
                if (operator == '*') {
                    int storedPrev = stack.pop();
                    int calculatedVal = storedPrev * curNum;
                    stack.push(calculatedVal);
                }
                // 4 / 2
                if (operator == '/') {
                    int storedPrev = stack.pop();
                    int calculatedVal = storedPrev / curNum;
                    stack.push(calculatedVal);
                }

                operator = ch;// for next operation current operator preps. see above notes
                curNum = 0; // reset as stack has calculated value.

            }

        }

        //empty stack by adding all elements to prep final result.
        // recall * and / already taken care
        // and now only positive and negative number will be added.
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    public int calculator_II_HARD(String inputStr) {

        /*
         Idea:
         Here parenthesis needs to be handled which is diff from basic calculator.
         Here sign apply is same as original cal only thing is it is handled by int which capture and apply for next time.

         Simple :::
         When hit { just wrap up calculation so far from result and store in stack along with sign caputred.
         when hit } just conclude on hand calculation AND add up what is stored so far in stack.
         prep final result and move on..

        * */

        if (inputStr == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int curNum = 0;
        int res = 0;
        int signCaptured = 1; // 1 for + and -1 for -

        for (int c = 0; c < inputStr.length(); c++) {

            char ch = inputStr.charAt(c);

            if (Character.isDigit(ch)) {
                curNum = curNum * 10 + ch - '0';
            }
            //when + or - comes it prep result using previously captured signed
            // reset curr num and assign current sign for next time. (exactly like regular calculator)
            else if (ch == '+') {
                int t = curNum * signCaptured;
                res = res + t;

                signCaptured = 1; //for next time
                curNum = 0;
            } else if (ch == '-') {
                int t = curNum * signCaptured;
                res = res + t;

                signCaptured = -1; //for next time
                curNum = 0;
            }
            // WHEN hit open bracket , conclude so far result and captured sign and store
            // as open bracket will start new equation.
            else if (ch == '(') {

                stack.push(res); //so far
                stack.push(signCaptured);//captured for nex time so store that too.

                //res to start again.
                res = 0;
                signCaptured = 1;

            }
            // WHEN hit close bracket , Its time to
            // 1. cacluate what is on hand and
            // 2. combine with what was stored in stack and prep
            // 3. prep final result at this point
            // at this point everything is calcualted and stored in RESUTL variable.

            else if (ch == ')') {

                //on hand items.
                int t = curNum * signCaptured;
                res = res + t;

                //previously calculated items
                int oldSignCaptured = stack.pop();
                int oldRes = stack.pop();

                //final result at this point.
                res = res * oldSignCaptured;
                res = res + oldRes;

                //reset for next time.
                curNum = 0;
            }
        }

        //Left out incase extra number was there e..g 1+(3+2) + 4
        // so far 6 and now sign is 1 and curNum = 4
        if (curNum != 0) {
            int t = curNum * signCaptured;
            res = res + t;
        }

        return res;
    }

    public int[] nextGreaterElements_1(int[] arr1, int[] arr2) {

          /*
        Idea:
        Start with BF and On2 and that is good.
        Better version is pep map of parent i.e second array
        item1 -> @ index0
        item2 -> @ index1

        then iterate array one and find index of that item in map and start from next index ---> len and
        if it finds big element collect it. See 2nd approach https://leetcode.com/problems/next-greater-element-i/editorial/

        Best approach is using Stack as below with ON
        see simulation if required @  https://leetcode.com/problems/next-greater-element-i/editorial/
        * */

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        //why arr2  bcs arr1 is just subset of 2
        for (int i = 0; i < arr2.length; i++) {

            //when nothing in stack.
            if (stack.isEmpty()) {
                stack.push(arr2[i]);
            } else {
                while (!stack.isEmpty()) {
                    int lastEle = stack.peek();
                    //when prev elements are smaller than curr elem is next ele for all of those.
                    if (arr2[i] > lastEle) {
                        //store next ele for each element of arr2
                        map.put(lastEle, arr2[i]);
                        stack.pop();//remove from stack
                    } else {
                        break;
                    }
                }
                //add curr ele in stack.
                stack.push(arr2[i]);
            }
        }

        // above should have prep all pairs of ele vs next ele
        // if still stack has ele i.e. they dont have any next ele
        // assign -1 to rest
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        //at this point all pairs are ready now just iterate over arr1 and assign it next.
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = map.get(arr1[i]);// e.g 4->6, 3-> -1 like that.
        }
        return res;
    }

    public int[] nextGreaterElements_2(int[] arr) {

        /*
        Idea:

        Since we need to consider it as circular array i.e. say 3>5>6>3>5>6 , its loop.
        we just need to go 2 x len logically to consider as circular
        after regular len it can go find index by i % len bascially  mod will fallin to original index

        0 1 2 then 3 % 3 =0 will put back to 0 and then 1 and 2 ...


        *****
        Monotonic stack: It is called when Stack element value in a way only either increase or decrease.
        See here our stack we keep only increase value in Stack.

        [1,1,1,2,3,4,5,6,7,8,8,9,9,9,10] Monotone Increasing stack

        [10,10,9,8,7,6,5,4,4,3,2,1,1,1] Monotone Decreasing stack

        ****
        More on Monotonic stack : A monotonic stack is a stack in which the elements are always sorted.
        A stack can be monotonically increasing (sorted ascending) or monotonically decreasing (sorted descending).

        Let's say we have a monotonic decreasing stack.
        If we want to push x, then all elements that are less than x are popped off first to maintain the sorted property

         if we have stack = [623, 532, 125] and we want to push 615
         then the 532 and 125 must be popped before the 615 is pushed, otherwise the stack will no longer be sorted.

        * */

        Stack<Integer> stack = new Stack<>();
        int orgLen = arr.length;
        int[] res = new int[orgLen];

        //to cover circular array we double the length and iterate.
        //below is the eg with double len and its final results.
        // 5  7  1  2  6  0 >>  5  7  1  2  6  0
        // 7 -1  2  6  7  5  >> 7  -1 2  6 -1 -1

        for (int curr = 2 * orgLen - 1; curr >= 0; curr--) {

            if (stack.isEmpty()) {
                stack.push(arr[curr % orgLen]); // add current items.

            } else {

                while (!stack.isEmpty()) {
                    // when smaller than current element found in stack
                    //remove it as we are traversing backwards and expects higher or bigger ele stored previously then current
                    if (arr[curr % orgLen] >= stack.peek()) {
                        stack.pop();
                    } else {
                        break; // all stored ele are bigger than curr.
                    }
                }

                //start capturing result when hit org len.
                if (curr < orgLen) {
                    if (!stack.isEmpty()) {
                        // next bigger ele is in stack top.
                        res[curr] = stack.peek();
                    } else {
                        res[curr] = -1;
                    }
                }

                stack.push(arr[curr % orgLen]); // add current items.
            }

        }

        return res;
    }

    public int nextGreaterElements_III_aka_NextPermutation(int number) {

        /*Idea
          REf Array nextPermutation(int[] arr)
               * */

        char[] charArr = Integer.toString(number).toCharArray();

        // Find Dip Index
        // 2 1 5 4 3 0 0 found 1
        int dipIdx = -1;
        for (int d = charArr.length - 2; d >= 0; d--) {
            if (charArr[d] < charArr[d + 1]) {// 1< 5
                dipIdx = d; // found index where dip happened.
                break;
            }
        }

        //Nothing found as requested send -1;
        if (dipIdx == -1) {
            return -1;
        }

        int grtIdx = -1;
        // find first greater element for given dip idx
        // 2 1 5 4 3 0 0 found 3 is > 1
        for (int g = charArr.length - 1; g > dipIdx; g--) {
            if (charArr[g] > charArr[dipIdx]) {
                grtIdx = g;
                break;
            }
        }

        //swap dip <> grt
        // 2 1 5 4 3 0 0  > 2 3 5 4 1 0 0
        char t = charArr[dipIdx];
        charArr[dipIdx] = charArr[grtIdx];
        charArr[grtIdx] = t;

        // now sort remaining from dipidx => last
        // 2 3 5 4 1 0 0 >  2 3 0 0 1 4 5
        Arrays.sort(charArr, dipIdx + 1, charArr.length);

        String resS = new String(charArr);

        try {
            return Integer.parseInt(resS);
        } catch (Exception e) {
            return -1;
        }
    }

    public int trap_rain_Water(int[] heightArr) {

        /*Idea ( Seems belongs to Array/String but like Next permutation sits here as it can be alos soved by Stack)

        BF :
        Do as directed in question : for each index starting 1
        1. go left and find max height
        2. go right and fid max height.
        3. find min of left vs right
        4. dedcut from current height and keep adding in to answer
        ref - approach 1  @ https://leetcode.com/problems/trapping-rain-water/editorial/

               * */

        int res = 0;
        int maxL = 0;
        int maxR = 0;
        int lt = 0;
        int rt = heightArr.length - 1;

        while (lt <= rt) {

            //left bar  is smaller than right bar
            // 0, 1, 1,2 ,,,,, 4 @ 1< 4
            if (heightArr[lt] <= heightArr[rt]) {

                if (heightArr[lt] >= maxL) {
                    // no calculation as left is higher now and it will drain all water on its left side
                    // e.g. 1 0 2 here @ 2 is max so nothing can hold on its left.
                    maxL = heightArr[lt];

                } else {
                    // counter water in block
                    // e.g. 0 2 1 and @ 1 here it look at left and found 2 was max L so it can only hold 2-1 = 1
                    res += maxL - heightArr[lt];

                }
                lt++;

            }
            //right bar is smaller than left bar
            // 0, 1, 1,2 ,,,,, 4 @ 4>1
            else {
                // no calculation as right is higher now and it will drain all water on its right side
                // e.g. 3,2,1 here @ 3 is max so nothing can hold on its right.
                if (heightArr[rt] >= maxR) {
                    maxR = heightArr[rt];
                } else {
                    // counter water in block
                    // e.g. 2 3 1 and @ 2 here it look at rigth and found 3 was max R so it can only hold 3-2 = 1
                    res += maxR - heightArr[rt];
                }
                rt--;
            }
        }

        return res;
    }

    public int sumSubarrayMiniums(int[] arr) {

        /*Idea
         To find sum of min of all sub array we need to do
         1. Find all sub arrays
         2. Find min from each sub array
         3. add up all min and done.

        This is not good as ON2 then space ON to store sub array.

        --------
        what is Monotonic increasing or decreasing stack: once more..

        Monotonic Increasing Stack i.e. stack bottom to up values are increasing
        e.g  3 5 6 10 23 etc

        Each new element added to the stack is greater than or equal to the one below it.
        If a new element is smaller, we remove elements from the top of the stack until we find one that is smaller or equal to the new element,
        or until the stack is empty
        ------------

        Basically On2 solution is the key to get direction and stack is storing values in increasing manner.
        The formula to get count is something unique to get total sub arrays in between ranges of two index.

        Do not fall : stack stores index but it actually enforces increasing values and it surely need index for fourmual
        if it was not array this could have been done differenly as we need both value-index where value are increasing.
        we could also use map to store this val - index...

        e.g.
        3 1 here 3 and 3 1 both sub array where 3 and 1 are min and sum is 4.

        1 4 here 1 and 1 4 both subarray and 1 is min in both..

        same concept take this solution futher where it stops when samller values comes in increasing stack where it keep pop
        till find it self big value or stack emplty . Standard montonic stack things. Only thing it uses formual to keep getting
        count and sum.. that is it......... done...

        one more thing it goes till Len where it had never run int above situation like 4, 5, 6 it never comes donw so at last it just
        pop everyting and again use formual to get sum....


        * */

        int MOD = 1000000007;
        long sumOfMin = 0;
        Stack<Integer> stack = new Stack<>();

        for (int curIdx = 0; curIdx <= arr.length; curIdx++) {

            // Either smaller value disturb increasing stack or reached at len.
            while ((!stack.isEmpty()) && (curIdx == arr.length || arr[curIdx] <= arr[stack.peek()])) {

                int midIdx = stack.pop();
                int leftB = stack.isEmpty() ? -1 : stack.peek();
                int rightB = curIdx;

                //apply formula now add in to result sum.
                long count = (long) (midIdx - leftB) * (rightB - midIdx) % MOD;
                sumOfMin += (count * arr[midIdx]) % MOD;
                sumOfMin %= MOD;

                //on white board do not do MOD and do below simple.
//                var count =  (midIdx - leftB) * (rightB - midIdx) ;
//                sumOfMin += count * arr[midIdx] ;

            }
            //add in to stack which is monotonic increasing in values.
            stack.push(curIdx);

        }

        return (int) sumOfMin;
    }

    public int[] asteroidCollision(int[] arr) {

        /*Idea
         Simple principal of math,
          1. smaller gets destroy , equals gets destroyed i.e. only bigger stays.
          2. Same dir never meet and opposite dir meet and collied.
          * */

        Stack<Integer> stack = new Stack<>();

        for (int asteroid : arr) {

            //always add in stack unless gets destroyed.
            boolean addToStack = true;

            //when opposite dir found then only collied.
            while (!stack.isEmpty() && (stack.peek() > 0 && asteroid < 0)) {

                //prev smaller gets destroyed.
                if (Math.abs(stack.peek()) < Math.abs(asteroid)) {
                    stack.pop();
                    continue;//go for next stored in stack.
                }

                //both destroyed i.e. prev one gets removed and not adding new one to stack
                if (Math.abs(stack.peek()) == Math.abs(asteroid)) {
                    stack.pop();
                }

                // basically (Math.abs(stack.peek()) > Math.abs(asteroid)
                // i.e. stack had 10 and asteroid has -5 so asteroid destroy
                addToStack = false;
                break; //asteroid destroyed  go for next one in for loop.
            }

            if (addToStack) {
                stack.push(asteroid);
            }
        }

        int[] remainingAsteroid = new int[stack.size()];

        for (int z = remainingAsteroid.length - 1; z >= 0; z--) {
            remainingAsteroid[z] = stack.peek();
            stack.pop();
        }
        return remainingAsteroid;
    }

    public int largestRectangleArea_3NSpace(int[] heights) {


        /*
        Idea:

         Note : area considering complete horizon not from the index starts for particular height.
         i.e. consider  <--- and ---> right both ways to find rectangular box.

        ******BF : see below and comments inside. ***********
        int maxArea = 0;

        for (int i = 0; i < length; i++) {

            int minHeight = Integer.MAX_VALUE;

            for (int j = i; j < length; j++) {

                //below line make sure left <-- is also covered for lower heights
                //e.g 2 1 4, 5 here for 1 <--2 is also covered with min height
                // IT also ensure that for upcoming lower heights e..g 2 1 5 6 2 3
                // say for 2 its left side 2, 1, 5,6 << 2 also covered when i @ 5 it had reached to j @ 2
                // it had then considered 2 as min height.

                minHeight = Math.min(minHeight, heights[j]);

                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }

         ********  Better ON

         So what is better approach?
         One thing is clear that we need to go  <-- left and right--> both direction till hit something lower than given value
         to get area.

         So for given index look at left and see how far it could go left by making sure all elements before it is bigger than its value.
         for that we use monotonic increasing stack which only helps us to store index /value in increasing manner and that will use to
         look back when in left.

         2  1  5  6  2  3  1  < Height

         0  0  2  3  2  5  0  < how left it could go back e.g. 5 only till index 2 which is its own but 1 could go back till 0 index another e.g is for 2 @4 idx could look back to 5@idx 2 that far it could go back
         _  _  _  _  _  _  _
         0  1  2  3  4  5  6  < Idx

         same for right how far it can go to right from given index --->

         2  1  5  6  2  3  1  < Height

         0  6  3  3  5  5  6 < how right one could go same as left here e.g. 1@idx 1 could go till 6th index
         _  _  _  _  _  _  _
         0  1  2  3  4  5  6  < Idx


        Once found left and right boundary based on how far left and right could find area
        (Rectangle area is  L * W and each array item is Length with Width is always 1.)

            Area of 2 @ 4 idx is
            (right - left + 1 ) * A[4]
            5 - 2 + 1 * 2 = 8

         2  1  5  6  2  3  1  < Height
         _  _  _  _  _  _  _

         0  1  2  3  4  5  6  < Idx

         0  0  2  3  2  5  0 < left
         0  6  3  3  5  5  6 > right


        ** Tip :  A histogram is a graph that shows the frequency of numerical data using rectangles.
        the one showed on leetcode picture on problem. bascially it is kind of width of 1 for each height.
        e.g.

              | |
        |_|   |_|
        2 h   3 h

        * */

        Stack<Integer> monotonicIncreasingStack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;

        int len = heights.length;
        int[] leftBoundary = new int[len];
        int[] rightBoundary = new int[len];

        // Left boundary
        for (int l = 0; l < len; l++) {

            // if violates increasing stack remove same as standard.
            while (!monotonicIncreasingStack.isEmpty() && heights[monotonicIncreasingStack.peek()] >= heights[l]) {
                monotonicIncreasingStack.pop();
            }

            // record index till left it could go
            if (monotonicIncreasingStack.isEmpty()) {
                leftBoundary[l] = 0;
            } else {
                //stack top value is < then curr value so STOP record boundary.
                //+1 bcs what is in stack is smaller so +1 is correct boundary starting.
                leftBoundary[l] = monotonicIncreasingStack.peek() + 1;
            }
            //just add if increasing  manner .
            monotonicIncreasingStack.push(l);
        }

        //reusing same stack after reset.
        monotonicIncreasingStack.clear();

        //now for Right same concept.
        for (int r = len - 1; r >= 0; r--) {

            // if violates increasing stack remove same as standard.
            while (!monotonicIncreasingStack.isEmpty() && heights[monotonicIncreasingStack.peek()] >= heights[r]) {
                monotonicIncreasingStack.pop();
            }

            if (monotonicIncreasingStack.isEmpty()) {
                rightBoundary[r] = len - 1;// bcs its right side indexes.
            } else {
                rightBoundary[r] = monotonicIncreasingStack.peek() - 1;
            }
            monotonicIncreasingStack.push(r);
        }

        //Now calc Area based on boundaries.
        for (int x = 0; x < len; x++) {
            //  Area =  (right - left + 1 ) * A[4]
            int calcualtedArea = (rightBoundary[x] - leftBoundary[x] + 1) * heights[x];
            maxArea = Math.max(calcualtedArea, maxArea);

        }

        return maxArea;

    }

    public int maximalRectangle(char[][] matrix) {

        /*
        Idea:

        This is like largest rectangle area problem there heights[] array is given
        and here it is matrix which is nothing but Array of heights[] array given.

        So treat one row at a time i.e one height[] array at a time like earlier example and keep recording max area..
        Done..

        One thing is important while doing it is when above row has height 1 and current row has height 0 then
        we need to consider 0 height as building are not base less i.e. rectange are not flying in air it needs 1 to stand

        e.g.

        1 1 1
        1 1 1
       -------
        2 2 2

        1 1 1
        1 1 1
        0 1 0
        _ _ _
        0 3 0

        * */


        int[] heights = new int[matrix[0].length];
        int maxArea = Integer.MIN_VALUE;

        //Prep height[] for each row.
        for (int rw = 0; rw < matrix.length; rw++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[rw][col] == '1') {
                    heights[col]++; // keep increasing as building block
                } else {
                    heights[col] = 0; // reset to 0 as no base less.
                }
            }

            // Now For each height[] do Exactly like rectangle problem.......

            Stack<Integer> monotonicIncreasingStack = new Stack<>();
            int len = heights.length;
            int[] leftSmaller = new int[len];
            int[] rightSmaller = new int[len];
            for (int l = 0; l < len; l++) {
                while (!monotonicIncreasingStack.isEmpty() && heights[monotonicIncreasingStack.peek()] >= heights[l]) {
                    monotonicIncreasingStack.pop();
                }
                if (monotonicIncreasingStack.isEmpty()) {
                    leftSmaller[l] = 0;
                } else {
                    leftSmaller[l] = monotonicIncreasingStack.peek() + 1;
                }
                monotonicIncreasingStack.push(l);
            }
            monotonicIncreasingStack.clear();
            for (int r = len - 1; r >= 0; r--) {
                while (!monotonicIncreasingStack.isEmpty() && heights[monotonicIncreasingStack.peek()] >= heights[r]) {
                    monotonicIncreasingStack.pop();
                }
                if (monotonicIncreasingStack.isEmpty()) {
                    rightSmaller[r] = len - 1;
                } else {
                    rightSmaller[r] = monotonicIncreasingStack.peek() - 1;
                }
                monotonicIncreasingStack.push(r);
            }
            for (int a = 0; a < len; a++) {
                int calcualtedArea = (rightSmaller[a] - leftSmaller[a] + 1) * heights[a];
                maxArea = Math.max(calcualtedArea, maxArea);
            }
        }

        return maxArea;

    }

    public int[] maxSlidingWindow(int[] arr, int k) {

        /*

        Idea:
        BF for On2 iterate and keep recording max number but when reached travel len = k break second loop
        and move to next index in outer loop or first loop.

        ** Better approach ??

         Keep Monotonic decreasing stack i.e. incoming values must be smaller than top value on stack.
          5 , 3, 1, 0, -2  -4  << see bottom of stack has max and top has smallest.

         Now using this stack add K values as per sliding window size and when size reached remove from bottom
         the max value , then add more and again gets max when reached k window size.

         Now adding and removing from both end on stack would require couple of stack or someother way instead of
         that use DeQueue as stack which allows to add/remove from both end.

         Note  : Again like other e.g storing index and resolving value from A[idx] as we need to also keep K len
         only elements in stack so index helps to do it.

        e.g. K = 3

        A[] 1, 3, -1, -3, 5
            0  1   2   3  4

       DeQ :
        ________________
         1x 3 -1
         0x 1 2
        ________________
        Max 3 and x as it violated decreasing stack.

        @idx 4 it first removes index 1 ele to maintain k -3 by currIdx - k 4-3= 1 whic is present in stack so remvoes it
        and then do business as usal.
        ________________
         3x -1 -3
         1x  2  3
        ________________

        * */


        var resList = new ArrayList<Integer>();


        //monotonic decreasing Dequeue as stack
        Deque<Integer> dq = new ArrayDeque<>();

        //take care first K
        for (int idx = 0; idx < k; idx++) {
            //if violates  as per standard
            while (!dq.isEmpty() && arr[idx] >= arr[dq.peekLast()]) {
                dq.pollLast(); //remove like stack from top.
            }
            dq.offerLast(idx); // add like adding on top of stack
        }
        //bottom ele i.e./ first in Queue is MAX in K window
        resList.add(arr[dq.peekFirst()]);


        //starts from K till rest..
        for (int idx = k; idx < arr.length; idx++) {

            //when Stack size reached to K remove from bottom as it is decreasing stack
            if (dq.peekFirst() == idx - k) {
                dq.pollFirst();
            }
            //if violates as per standard.
            while (!dq.isEmpty() && arr[idx] >= arr[dq.peekLast()]) {
                dq.pollLast(); //remove like stack from top.
            }

            dq.offerLast(idx); // add like adding on top of stack
            //bottom ele i.e./ first in Queue is MAX in K window

            resList.add(arr[dq.peekFirst()]);
        }

        var resA = resList.stream().mapToInt(value -> value).toArray();
        return resA;
    }


    //------ Extra or  not use anymore-----------------------

    public int[] nextGreaterElements_1_EXTRA(int[] arr1, int[] arr2) {

        /*
        Idea:
        BASED ON # 2 but i had good version for # 1


        * */


        Stack<Integer> stack = new Stack<>();
        int len2 = arr2.length;
        HashMap<Integer, Integer> stgMap = new HashMap<>();

        int[] res = new int[arr1.length];


        for (int x = len2 - 1; x >= 0; x--) {

            while (!stack.isEmpty() && stack.peek() <= arr2[x % len2]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                stgMap.put(arr2[x], stack.peek());

            } else {
                stgMap.put(arr2[x], -1);
            }
            stack.push(arr2[x]); // add current items.
        }

        //now find result from map for array 1 and prep result.
        for (int y = 0; y < arr1.length; y++) {
            int v = stgMap.get(arr1[y]);
            res[y] = v;
        }

        return res;
    }

    public int largestRectangleArea_ONSpace(int[] heights) {

        /*
        Idea: it is a single pass increasing stack when hit lower just calculate previously stored larger elements areas
        and keep removing it until it becomes increasing stack

        2,4,6,8, now 3 comes so =? 2,x,x,x,3 on stack as needs to maintian inreasing

        * */
        Stack<Integer> monoloticIncreasingStack = new Stack<>();

        int maxArea = 0;

        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            //only stores value increasing manner
            // when found value>= i it  will remove and look for removed `s previous index to caluclate its area.

            while (!monoloticIncreasingStack.isEmpty() && (i == n || heights[monoloticIncreasingStack.peek()] >= heights[i])) {

                int height = heights[monoloticIncreasingStack.pop()]; // removed larger
                // for removed previous index to calcualte area why  ?
                // bcs its hologram rectangle and alll are joint so to get Width need to go to its previous index value
                int width = monoloticIncreasingStack.isEmpty() ? i : i - monoloticIncreasingStack.peek() - 1;// why -1 bcs boundary ends with removed index not the current index

                maxArea = Math.max(maxArea, width * height); // find aread.
            }
            monoloticIncreasingStack.push(i); // add current index
        }
        return maxArea;
    }

}
