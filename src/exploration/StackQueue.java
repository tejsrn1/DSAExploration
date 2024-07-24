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


    /**
     * This method checks if the given string of parentheses is valid.
     *
     * @param inputStr The string of parentheses.
     * @return true if the string is valid, false otherwise.
     * <p>
     * Example:
     * String inputStr = "{[()]}";
     * boolean result = isValidParentheses(inputStr); // returns true
     */
    public boolean isValidParentheses(String inputStr) {
        Stack<Character> stack = new Stack<>();

        for (int x = 0; x < inputStr.length(); x++) {
            char ch = inputStr.charAt(x);

            // If the character is an opening bracket, push it to the stack
            if (ch == '[' || ch == '{' || ch == '(') {
                stack.push(ch);
            } else {
                // If the stack is empty, there's nothing to check against, so the string is invalid
                if (stack.isEmpty()) {
                    return false;
                }

                char storedVal = stack.pop();

                // If the closing bracket doesn't match the opening bracket, the string is invalid
                if ((ch == ']' && storedVal != '[') || (ch == '}' && storedVal != '{') || (ch == ')' && storedVal != '(')) {
                    return false;
                }
            }
        }

        // If the stack is not empty after checking all characters, the string is invalid
        return stack.isEmpty();
    }

    /**
     * This method converts an infix expression to a postfix expression.
     *
     * @param inputStr The infix expression.
     * @return The postfix expression.
     *
     * Example:
     * String inputStr = "(p+q)*(m-n)";
     * String result = IN_TO_POST_FIX(inputStr); // returns "pq+mn-*"
     */
    public String IN_TO_POST_FIX(String inputStr) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int c = 0; c < inputStr.length(); c++) {
            char ch = inputStr.charAt(c);

            // If it's an operand or digit, append it to the result
            if (Character.isLetterOrDigit(ch)) {
                sb.append(ch);
            }
            // If it's an opening bracket, push it to the stack
            else if (ch == '(') {
                stack.push(ch);
            }
            // If it's a closing bracket, pop all operators from the stack until an opening bracket is found
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                // Ensure the opening bracket is also removed from the stack
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // If the operator has lower or equal priority than the operator on the top of the stack,
                // pop and append the operator on the top of the stack to the result
                while (!stack.isEmpty() && getPriority(ch) <= getPriority(stack.peek())) {
                    sb.append(stack.pop());
                }
                // Push the current operator to the stack
                stack.push(ch);
            }
        }

        // Append any remaining operators in the stack to the result
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid expression";
            }
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    /**
     * This method returns the priority of an operator.
     *
     * @param ch The operator.
     * @return The priority of the operator. Higher number means higher priority.
     */
    private int getPriority(char ch) {
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

    /**
     * This method calculates the result of a given arithmetic expression.
     * The expression is based on Infix to Postfix conversion approach.
     * The operator works one step behind in the sequence of all operators.
     *
     * @param inputStr The arithmetic expression in string format.
     * @return The result of the arithmetic expression.
     */
    public int calculator_II_INtoPostBased(String inputStr) {
        // If the input string is null, return 0
        if (inputStr == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int curNum = 0;
        char operator = '+'; // Initialize the operator to '+'

        // Traverse the input string
        for (int c = 0; c < inputStr.length(); c++) {
            char ch = inputStr.charAt(c);

            // If the character is a digit, update 'curNum'
            if (Character.isDigit(ch)) {
                curNum = curNum * 10 + ch - '0';
            }

            // If the character is not a digit and not a whitespace, or it's the last character of the string
            if (!Character.isDigit(ch) && !Character.isWhitespace(ch) || c == inputStr.length() - 1) {
                // If the operator is '+', push 'curNum' into the stack
                if (operator == '+') {
                    stack.push(curNum);
                }

                // If the operator is '-', push '-curNum' into the stack
                if (operator == '-') {
                    stack.push(-curNum);
                }

                // If the operator is '*', pop the top element from the stack, multiply it with 'curNum', and push the result into the stack
                if (operator == '*') {
                    int storedPrev = stack.pop();
                    int calculatedVal = storedPrev * curNum;
                    stack.push(calculatedVal);
                }

                // If the operator is '/', pop the top element from the stack, divide it by 'curNum', and push the result into the stack
                if (operator == '/') {
                    int storedPrev = stack.pop();
                    int calculatedVal = storedPrev / curNum;
                    stack.push(calculatedVal);
                }

                // Update the operator and reset 'curNum'
                operator = ch;
                curNum = 0;
            }
        }

        // Calculate the final result by adding all elements in the stack
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        // Return the result
        return res;
    }

    /**
     * This method calculates the result of a given arithmetic expression.
     * The expression can contain parentheses.
     * The sign is handled by an integer which captures and applies for the next time.
     *
     * @param inputStr The arithmetic expression in string format.
     * @return The result of the arithmetic expression.
     */
    public int calculator_II_HARD(String inputStr) {
        // If the input string is null, return 0
        if (inputStr == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int curNum = 0;
        int res = 0;
        int signCaptured = 1; // Initialize the sign to 1 for '+'

        // Traverse the input string
        for (int c = 0; c < inputStr.length(); c++) {
            char ch = inputStr.charAt(c);

            // If the character is a digit, update 'curNum'
            if (Character.isDigit(ch)) {
                curNum = curNum * 10 + ch - '0';
            }

            // If the character is '+', calculate the current result, update the sign, and reset 'curNum'
            else if (ch == '+') {
                int t = curNum * signCaptured;
                res = res + t;

                signCaptured = 1; // Update the sign for the next time
                curNum = 0;
            }

            // If the character is '-', calculate the current result, update the sign, and reset 'curNum'
            else if (ch == '-') {
                int t = curNum * signCaptured;
                res = res + t;

                signCaptured = -1; // Update the sign for the next time
                curNum = 0;
            }

            // If the character is '(', push the current result and the captured sign into the stack, and reset 'res' and 'signCaptured'
            else if (ch == '(') {
                stack.push(res); // Push the current result into the stack
                stack.push(signCaptured); // Push the captured sign into the stack

                // Reset 'res' and 'signCaptured'
                res = 0;
                signCaptured = 1;
            }

            // If the character is ')', calculate the current result, combine it with the stored result in the stack, and reset 'curNum'
            else if (ch == ')') {
                // Calculate the current result
                int t = curNum * signCaptured;
                res = res + t;

                // Combine the current result with the stored result in the stack
                int oldSignCaptured = stack.pop();
                int oldRes = stack.pop();

                // Update the final result at this point
                res = res * oldSignCaptured;
                res = res + oldRes;

                // Reset 'curNum'
                curNum = 0;
            }
        }

        // If there is an extra number, calculate the current result and update the final result
        if (curNum != 0) {
            int t = curNum * signCaptured;
            res = res + t;
        }

        // Return the result
        return res;
    }

    /**
     * This method finds the next greater elements for each element in the first array.
     * The next greater element for an element 'x' is the first greater element on the right side of 'x' in the second array.
     * If no such element exists, the output is -1 for this element.
     *
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @return An array of the next greater elements for each element in the first array.
     */
    public int[] nextGreaterElements_1(int[] arr1, int[] arr2) {
        // Initialize a stack and a map
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse the second array
        for (int i = 0; i < arr2.length; i++) {
            // If the stack is empty, push the current element into the stack
            if (stack.isEmpty()) {
                stack.push(arr2[i]);
            } else {
                // While the stack is not empty and the current element is greater than the top element of the stack
                while (!stack.isEmpty() && arr2[i] > stack.peek()) {
                    // Store the next greater element for the top element of the stack
                    map.put(stack.pop(), arr2[i]);
                }
                // Push the current element into the stack
                stack.push(arr2[i]);
            }
        }

        // If there are still elements in the stack, assign -1 as the next greater element for these elements
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        // Initialize the result array
        int[] res = new int[arr1.length];

        // Assign the next greater element for each element in the first array
        for (int i = 0; i < arr1.length; i++) {
            res[i] = map.get(arr1[i]);
        }

        // Return the result array
        return res;
    }


    /*
     * This method uses a monotonic stack to find the next greater elements in the array.
     * A monotonic stack is a stack in which the elements are always sorted. In this case,
     *  we maintain a monotonically decreasing stack. When we want to push an element onto
     * the stack, we first pop all elements that are less than the current element. This ensures th
     * at the top of the stack always contains the next greater element for the current element. Si
     * nce the array is circular, we iterate over the array twice to ensure that we find the next
     * greater element for every element in the array. The time complexity of this method is O(n),
     *  where n is the length of the array. The space complexity is also O(n), due to the additio
     * nal space required for the stack and the result array. This method can be useful in proble
     * ms where we need to find the next greater element in a circular array. It can also be extend
     * ed to find the next smaller element, previous greater element, previous smaller element, etc
     * . by modifying the conditions and the direction of traversal. The key idea is to maintain th
     * e monotonic property of the stack to efficiently find the desired element. This method is a go
     * od example of how data structures like stacks can be used to solve complex problems in an eff
     * icient manner. It also demonstrates the power of the monotonic stack concept, which can be ap
     * plied in a variety of problems. The use of the modulo operator to handle the circular nature
     *  of the array is also a clever trick that can be used in other problems involving circular a
     * rrays. Overall, this method is a good demonstration of several important concepts in algori
     * thm design and problem solving
     * */

    /**
     * This method finds the next greater elements in a circular array using a monotonic stack.
     *
     * @param arr The input array.
     * @return An array of the next greater elements.
     *
     * Example:
     * Input: [1, 2, 1]
     * Output: [2, -1, 2]
     *
     * Explanation:
     * The first 1's next greater number is 2;
     * The number 2 can't find next greater number;
     * The second 1's next greater number needs to search circularly, which is also 2.
     */
    public int[] nextGreaterElements_2(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int orgLen = arr.length;
        int[] res = new int[orgLen];

        // To cover the circular array, we double the length and iterate
        for (int curr = 2 * orgLen - 1; curr >= 0; curr--) {
            // When the stack is empty, add the current item
            if (stack.isEmpty()) {
                stack.push(arr[curr % orgLen]);
            } else {
                // When the current element is greater than or equal to the top of the stack, pop the stack
                while (!stack.isEmpty() && arr[curr % orgLen] >= stack.peek()) {
                    stack.pop();
                }

                // Start capturing the result when hit the original length
                if (curr < orgLen) {
                    if (!stack.isEmpty()) {
                        // The next greater element is at the top of the stack
                        res[curr] = stack.peek();
                    } else {
                        res[curr] = -1;
                    }
                }

                // Add the current item to the stack
                stack.push(arr[curr % orgLen]);
            }
        }

        return res;
    }


    /**
     * This method finds the next greater permutation of a number.
     *
     * @param number The input number.
     * @return The next greater permutation of the number. If no such permutation exists, returns -1.
     * <p>
     * Example:
     * number: 2154300
     * The next greater permutation is: 2300145
     */
    public int nextGreaterElementsIIIakaNextPermutation(int number) {
        // Convert the number to a character array.
        char[] charArr = Integer.toString(number).toCharArray();

        // Find the index where the number dips.
        int dipIdx = -1;
        for (int d = charArr.length - 2; d >= 0; d--) {
            if (charArr[d] < charArr[d + 1]) {
                dipIdx = d; // Found index where dip happened.
                break;
            }
        }

        // If no dip was found, return -1.
        if (dipIdx == -1) {
            return -1;
        }

        // Find the first greater element for the given dip index.
        int grtIdx = -1;
        for (int g = charArr.length - 1; g > dipIdx; g--) {
            if (charArr[g] > charArr[dipIdx]) {
                grtIdx = g;
                break;
            }
        }

        // Swap the dip and greater elements.
        char t = charArr[dipIdx];
        charArr[dipIdx] = charArr[grtIdx];
        charArr[grtIdx] = t;

        // Sort the remaining elements from dipIdx to the end.
        Arrays.sort(charArr, dipIdx + 1, charArr.length);

        // Convert the character array back to a number.
        String resS = new String(charArr);
        try {
            return Integer.parseInt(resS);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * This method calculates the amount of rain water that can be trapped between the bars.
     *
     * @param heightArr The array representing the height of the bars.
     * @return The total amount of rain water that can be trapped.
     * <p>
     * Example:
     * heightArr: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
     * The total amount of rain water that can be trapped is 6.
     */
    public int trapRainWater(int[] heightArr) {
        int res = 0;
        int maxL = 0;
        int maxR = 0;
        int lt = 0;
        int rt = heightArr.length - 1;

        while (lt <= rt) {
            // Left bar is smaller than right bar.
            if (heightArr[lt] <= heightArr[rt]) {
                if (heightArr[lt] >= maxL) {
                    // No calculation as left is higher now and it will drain all water on its left side.
                    maxL = heightArr[lt];
                } else {
                    // Counter water in block.
                    res += maxL - heightArr[lt];
                }
                lt++;
            }
            // Right bar is smaller than left bar.
            else {
                // No calculation as right is higher now and it will drain all water on its right side.
                if (heightArr[rt] >= maxR) {
                    maxR = heightArr[rt];
                } else {
                    // Counter water in block.
                    res += maxR - heightArr[rt];
                }
                rt--;
            }
        }

        return res;
    }


    /**
     * This method calculates the sum of minimum values of all subarrays of the given array.
     *
     * @param arr The input array.
     * @return The sum of minimum values of all subarrays modulo 10^9 + 7.
     *
     * Example:
     * int[] arr = {3, 1, 2, 4};
     * int result = sumSubarrayMiniums(arr); // returns 17
     */
    public int sumSubarrayMiniums(int[] arr) {
        int MOD = 1000000007;
        long sumOfMin = 0;
        Stack<Integer> stack = new Stack<>();

        for (int curIdx = 0; curIdx <= arr.length; curIdx++) {
            while ((!stack.isEmpty()) && (curIdx == arr.length || arr[curIdx] <= arr[stack.peek()])) {
                int midIdx = stack.pop();
                int leftB = stack.isEmpty() ? -1 : stack.peek();
                int rightB = curIdx;

                long count = (long) (midIdx - leftB) * (rightB - midIdx) % MOD;
                sumOfMin += (count * arr[midIdx]) % MOD;
                sumOfMin %= MOD;
            }
            stack.push(curIdx);
        }

        return (int) sumOfMin;
    }

    /**
     * This method simulates the collision of asteroids.
     *
     * @param arr The input array representing the asteroids.
     * @return The state of the asteroids after all collisions.
     *
     * Example:
     * int[] arr = {5, 10, -5};
     * int[] result = asteroidCollision(arr); // returns {5, 10}
     */
    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : arr) {
            boolean addToStack = true;

            while (!stack.isEmpty() && (stack.peek() > 0 && asteroid < 0)) {
                if (Math.abs(stack.peek()) < Math.abs(asteroid)) {
                    stack.pop();
                    continue;
                }

                if (Math.abs(stack.peek()) == Math.abs(asteroid)) {
                    stack.pop();
                }

                addToStack = false;
                break;
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


    /**
     * This method finds the largest rectangle area in a histogram using a monotonic stack.
     *
     * @param heights The heights of the bars in the histogram.
     * @return The area of the largest rectangle.
     *
     * Example:
     * Input: [2, 1, 5, 6, 2, 3]
     * Output: 10
     *
     * Explanation:
     * The largest rectangle is shown in the shaded area, which has area = 10 unit.
     */
    public int largestRectangleArea_3NSpace(int[] heights) {
        Stack<Integer> monotonicIncreasingStack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;

        int len = heights.length;
        int[] leftBoundary = new int[len];
        int[] rightBoundary = new int[len];

        // Left boundary
        for (int l = 0; l < len; l++) {
            // If violates increasing stack remove same as standard.
            while (!monotonicIncreasingStack.isEmpty() && heights[monotonicIncreasingStack.peek()] >= heights[l]) {
                monotonicIncreasingStack.pop();
            }

            // Record index till left it could go
            if (monotonicIncreasingStack.isEmpty()) {
                leftBoundary[l] = 0;
            } else {
                // Stack top value is < then curr value so STOP record boundary.
                // +1 because what is in stack is smaller so +1 is correct boundary starting.
                leftBoundary[l] = monotonicIncreasingStack.peek() + 1;
            }
            // Just add if increasing manner.
            monotonicIncreasingStack.push(l);
        }

        // Reusing same stack after reset.
        monotonicIncreasingStack.clear();

        // Now for Right same concept.
        for (int r = len - 1; r >= 0; r--) {
            // If violates increasing stack remove same as standard.
            while (!monotonicIncreasingStack.isEmpty() && heights[monotonicIncreasingStack.peek()] >= heights[r]) {
                monotonicIncreasingStack.pop();
            }

            if (monotonicIncreasingStack.isEmpty()) {
                rightBoundary[r] = len - 1; // Because its right side indexes.
            } else {
                rightBoundary[r] = monotonicIncreasingStack.peek() - 1;
            }
            monotonicIncreasingStack.push(r);
        }

        // Now calculate Area based on boundaries.
        for (int x = 0; x < len; x++) {
            // Area = (right - left + 1) * A[4]
            int calculatedArea = (rightBoundary[x] - leftBoundary[x] + 1) * heights[x];
            maxArea = Math.max(calculatedArea, maxArea);
        }

        return maxArea;
    }

    /**
     * This method finds the maximal rectangle in a binary matrix.
     *
     * @param matrix The binary matrix.
     * @return The area of the maximal rectangle.
     *
     * Example:
     * Input:
     * [
     *   ["1","0","1","0","0"],
     *   ["1","0","1","1","1"],
     *   ["1","1","1","1","1"],
     *   ["1","0","0","1","0"]
     * ]
     * Output: 6
     */
    public int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int maxArea = Integer.MIN_VALUE;

        // Prepare height[] for each row.
        for (int rw = 0; rw < matrix.length; rw++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[rw][col] == '1') {
                    heights[col]++; // Keep increasing as building block
                } else {
                    heights[col] = 0; // Reset to 0 as no base less.
                }
            }

            // Now for each height[] do exactly like rectangle problem.
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
                int calculatedArea = (rightSmaller[a] - leftSmaller[a] + 1) * heights[a];
                maxArea = Math.max(calculatedArea, maxArea);
            }
        }

        return maxArea;
    }

    /**
     * This method finds the maximum value in each sliding window of size k in the array.
     *
     * @param arr The input array.
     * @param k   The size of the sliding window.
     * @return An array of maximum values in each sliding window.
     *
     * Example:
     * arr: [1, 3, -1, -3, 5]
     * k: 3
     * The maximum values in each sliding window are: [3, 3, 5]
     */
    public int[] maxSlidingWindow(int[] arr, int k) {
        // Create a list to store the maximum values in each sliding window.
        var resList = new ArrayList<Integer>();

        // Create a monotonic decreasing Dequeue to act as a stack.
        Deque<Integer> dq = new ArrayDeque<>();

        // Take care of the first k elements.
        for (int idx = 0; idx < k; idx++) {
            // If the current element violates the monotonic decreasing property, remove elements from the top of the stack.
            while (!dq.isEmpty() && arr[idx] >= arr[dq.peekLast()]) {
                dq.pollLast();
            }
            // Add the current element to the top of the stack.
            dq.offerLast(idx);
        }
        // The bottom element (i.e., the first in the queue) is the maximum in the first k window.
        resList.add(arr[dq.peekFirst()]);

        // Start from the kth element till the rest of the array.
        for (int idx = k; idx < arr.length; idx++) {
            // When the stack size reaches k, remove the bottom element as it is out of the current window.
            if (dq.peekFirst() == idx - k) {
                dq.pollFirst();
            }
            // If the current element violates the monotonic decreasing property, remove elements from the top of the stack.
            while (!dq.isEmpty() && arr[idx] >= arr[dq.peekLast()]) {
                dq.pollLast();
            }
            // Add the current element to the top of the stack.
            dq.offerLast(idx);
            // The bottom element (i.e., the first in the queue) is the maximum in the current k window.
            resList.add(arr[dq.peekFirst()]);
        }

        // Convert the list to an array.
        var resA = resList.stream().mapToInt(value -> value).toArray();
        return resA;
    }


    /**
     * This method finds the next greater element for each element in the first array in the second array.
     *
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @return An array of the next greater elements.
     *
     * Example:
     * int[] arr1 = {4, 1, 2};
     * int[] arr2 = {1, 3, 4, 2};
     * int[] result = nextGreaterElements_1_EXTRA(arr1, arr2); // returns {-1, 3, -1}
     */
    public int[] nextGreaterElements_1_EXTRA(int[] arr1, int[] arr2) {
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
            stack.push(arr2[x]);
        }

        for (int y = 0; y < arr1.length; y++) {
            int v = stgMap.get(arr1[y]);
            res[y] = v;
        }

        return res;
    }

    /**
     * This method calculates the largest rectangle in the histogram.
     *
     * @param heights The heights of the bars in the histogram.
     * @return The area of the largest rectangle.
     *
     * Example:
     * int[] heights = {2, 1, 5, 6, 2, 3};
     * int result = largestRectangleArea_ONSpace(heights); // returns 10
     */
    public int largestRectangleArea_ONSpace(int[] heights) {
        Stack<Integer> monoloticIncreasingStack = new Stack<>();

        int maxArea = 0;

        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            while (!monoloticIncreasingStack.isEmpty() && (i == n || heights[monoloticIncreasingStack.peek()] >= heights[i])) {
                int height = heights[monoloticIncreasingStack.pop()];
                int width = monoloticIncreasingStack.isEmpty() ? i : i - monoloticIncreasingStack.peek() - 1;
                maxArea = Math.max(maxArea, width * height);
            }
            monoloticIncreasingStack.push(i);
        }
        return maxArea;
    }
}
