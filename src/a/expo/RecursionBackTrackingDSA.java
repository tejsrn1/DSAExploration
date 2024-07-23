package a.expo;

import java.util.*;

public class RecursionBackTrackingDSA {


    /**
     * This method prints numbers from 1 to N recursively.
     *
     * @param inputNumber The number to start printing from, usually starts from 1.
     * @param N           The number till which we want to print.
     *                    <p>
     *                    Example:
     *                    PrintNumberTillN(1, 5) will print: 1 2 3 4 5
     */
    public void PrintNumberTillN(int inputNumber, int N) {
        // Base condition to stop recursion and prevent stack overflow
        if (inputNumber > N) {
            return;
        }
        System.out.println(inputNumber);
        inputNumber++;
        PrintNumberTillN(inputNumber, N); // Recursive call
    }

    /**
     * This method prints a name 'Tejpal' N times recursively.
     *
     * @param counter The counter to track the number of times the name has been printed.
     * @param N       The total number of times the name should be printed.
     *                <p>
     *                Example:
     *                PrintNames_N_Times(1, 3) will print: Tejpal Tejpal Tejpal
     */
    public void PrintNames_N_Times(int counter, int N) {
        // Base condition to stop recursion and prevent stack overflow
        if (counter > N) {
            return;
        }
        System.out.println("Tejpal");
        counter++;
        PrintNames_N_Times(counter, N); // Recursive call
    }

    /**
     * This method prints numbers from 1 to N recursively.
     *
     * @param start The number to start printing from, usually starts from 1.
     * @param N     The number till which we want to print.
     *              <p>
     *              Example:
     *              PrintNames_1_to_N(1, 3) will print: 1 2 3
     */
    public void PrintNames_1_to_N(int start, int N) {
        // Base condition to stop recursion and prevent stack overflow
        if (start > N) {
            return;
        }
        System.out.println(start);
        start++;
        PrintNames_1_to_N(start, N); // Recursive call
    }

    /**
     * This method prints numbers from N to 1 recursively.
     *
     * @param N   The number to start printing from.
     * @param end The number till which we want to print, usually ends at 1.
     *            <p>
     *            Example:
     *            PrintNames_N_to_1(3, 1) will print: 3 2 1
     */
    public void PrintNames_N_to_1(int N, int end) {
        // Base condition to stop recursion and prevent stack overflow
        if (end > N) {
            return;
        }
        System.out.println(N);
        N--;
        PrintNames_N_to_1(N, end); // Recursive call
    }

    /**
     * This method calculates the sum of first N natural numbers.
     *
     * @param N The number up to which the sum is to be calculated.
     * @return The sum of first N natural numbers.
     * <p>
     * Example:
     * Input: N = 5
     * Output: 15
     * Explanation: The sum of first 5 natural numbers is 1 + 2 + 3 + 4 + 5 = 15.
     */
    public int sumOf_N_Numbers(int N) {
        // Base condition to stop recursion and prevent stack overflow
        if (N == 1) {
            return 1;
        }
        int res = N + sumOf_N_Numbers(--N);
        return res;
    }

    /**
     * This method calculates the factorial of a number N.
     *
     * @param N The number for which the factorial is to be calculated.
     * @return The factorial of the number N.
     * <p>
     * Example:
     * Input: N = 5
     * Output: 120
     * Explanation: The factorial of 5 is 5 * 4 * 3 * 2 * 1 = 120.
     */
    public int factorial_N_Numbers(int N) {
        // Base condition to stop recursion and prevent stack overflow
        if (N == 1) {
            return 1;
        }
        int res = N * factorial_N_Numbers(--N);
        return res;
    }

    /**
     * This method reverses an array in a non-recursive manner.
     *
     * @param givenArray The array to be reversed.
     * @param len        The length of the array.
     *                   <p>
     *                   Example:
     *                   Input: givenArray = {5, 4, 3, 2, 1}, len = 5
     *                   Output: {1, 2, 3, 4, 5}
     */
    public void reverse_Array_Non_Recursive(int[] givenArray, int len) {
        printArray(givenArray);

        int start = 0;
        int end = len - 1;

        // Swap elements at start and end indices until start < end
        while (end > start) {
            int temp = givenArray[start];
            givenArray[start] = givenArray[end];
            givenArray[end] = temp;

            start++;
            end--;
        }

        printArray(givenArray);
    }

    /**
     * This method reverses an array in a recursive manner.
     *
     * @param givenArray The array to be reversed.
     * @param len        The length of the array.
     */
    public void reverse_Array_Recursive(int[] givenArray, int len) {
        printArray(givenArray);
        Reverse_Array_Recursive(givenArray, 0, len - 1);
        printArray(givenArray);
    }

    /**
     * This method reverses an array in-place using recursion.
     *
     * @param givenArray the array to be reversed
     * @param start      the starting index for the reversal
     * @param end        the ending index for the reversal
     *                   <p>
     *                   Example:
     *                   Input: [1, 2, 3, 4, 5], start = 0, end = 4
     *                   Output: [5, 4, 3, 2, 1]
     */
    private void Reverse_Array_Recursive(int[] givenArray, int start, int end) {
        // Base case: if end is smaller than start, stop the recursion
        if (end < start) {
            return;
        }

        // Swap the elements at the start and end indices
        int temp = givenArray[start];
        givenArray[start] = givenArray[end];
        givenArray[end] = temp;

        // Recursively call the method for the next set of elements
        Reverse_Array_Recursive(givenArray, ++start, --end);
    }

    /**
     * This method checks if a given string is a palindrome.
     *
     * @param givenString the string to be checked
     * @param len         the length of the string
     *                    <p>
     *                    Example:
     *                    Input: "ABC D CBA", len = 9
     *                    Output: "Is_Palindrome_String true"
     */
    public void Is_Palindrome_String(String givenString, int len) {
        boolean res = Is_Palindrome_String(givenString, 0, len - 1);
        System.out.println("Is_Palindrome_String " + res);
    }

    /**
     * This helper method checks if a substring of a given string is a palindrome.
     *
     * @param givenString the string to be checked
     * @param start       the starting index of the substring
     * @param end         the ending index of the substring
     * @return true if the substring is a palindrome, false otherwise
     * <p>
     * Example:
     * Input: "ABC D CBA", start = 0, end = 8
     * Output: true
     */
    public boolean Is_Palindrome_String(String givenString, int start, int end) {
        // Base case: if end is smaller than start, the substring is a palindrome
        if (end < start) {
            return true;
        }

        // Check if the characters at the start and end indices are the same
        // and recursively check the next set of characters
        return givenString.charAt(start) == givenString.charAt(end)
                && Is_Palindrome_String(givenString, ++start, --end);
    }

    /**
     * This method prints the Fibonacci series up to the Nth term.
     * The Fibonacci series is a series of numbers in which each number is the sum of the two preceding ones, usually starting with 0 and 1.
     *
     * @param N The term up to which the Fibonacci series is to be printed. (0-based indexing)
     *          <p>
     *          Example:
     *          Input: N = 5
     *          Output: 0 1 1 2 3 5
     *          Explanation: 0 1 1 2 3 5 is the Fibonacci series up to the 5th term. (0-based indexing)
     */
    public void Fibonacci_Series_UpTo_N_Numbers(int N) {
        int[] fibArr = new int[N + 1]; // Array to store Fibonacci numbers

        // Fibonacci series starts with 0 and 1
        fibArr[0] = 0;
        fibArr[1] = 1;

        // Generate the Fibonacci series
        for (int x = 2; x <= N; x++) {
            int sum = fibArr[x - 1] + fibArr[x - 2]; // Sum of last and second last
            fibArr[x] = sum;
        }

        printArray(fibArr); // Print the Fibonacci series

        // O(1) space optimization
        int last = 1;
        int secondLast = 0;
        System.out.print(secondLast);
        System.out.print(last);

        for (int x = 2; x <= N; x++) {
            int curSum = last + secondLast; // Sum of last and second last
            secondLast = last; // Move the pointer
            last = curSum;
            System.out.print(curSum);
        }
    }

    /**
     * This method returns the Nth term of the Fibonacci series using recursion.
     * The Fibonacci series is a series of numbers in which each number is the sum of the two preceding ones, usually starting with 0 and 1.
     *
     * @param N The term of the Fibonacci series to be returned. (0-based indexing)
     * @return The Nth term of the Fibonacci series.
     */
    public int Fibonacci_Series_UpTo_N_Numbers_Recursive(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        int last = Fibonacci_Series_UpTo_N_Numbers_Recursive(N - 1);
        int secondLast = Fibonacci_Series_UpTo_N_Numbers_Recursive(N - 2);
        int sum = last + secondLast;
        return sum;
    }

    /**
     * This method calculates the power of a number.
     * It uses the binary exponentiation method to reduce the time complexity to O(log N).
     *
     * @param num   The base number.
     * @param power The exponent.
     * @return The result of num raised to the power.
     */
    public double myPow(double num, int power) {
        if (power < 0) {
            double tempRes = calculatePower(num, (long) -1 * power); // Convert negative power to positive
            double res = 1 / tempRes;
            return res;
        } else {
            return calculatePower(num, (long) power);
        }
    }

    /**
     * This method calculates the power of a number using binary exponentiation.
     * Binary exponentiation (also known as exponentiation by squaring) reduces the complexity from O(N) to O(log N) by halving the exponent in each step.
     *
     * @param num The base number.
     * @param N   The exponent.
     * @return The result of num raised to the power N.
     */
    private double calculatePower(double num, long N) {
        if (N == 0) {
            return 1;
        }

        // Handle when N becomes odd
        if (N % 2 == 1) {
            double r = num * calculatePower(num * num, (N - 1) / 2);
            return r;
        } else {
            double p = calculatePower(num * num, N / 2);
            return p;
        }
    }


    /**
     * This method sorts a stack in ascending order using recursion.
     *
     * @param stack The stack of integers to be sorted.
     *              <p>
     *              Example:
     *              Given stack: [3, 1, 2]
     *              sort_stack_recursion(stack) will make the stack: [1, 2, 3]
     */
    public void sort_stack_recursion(Stack<Integer> stack) {
        // Base condition to stop recursion and prevent stack overflow
        if (!stack.isEmpty()) {
            // Remove and store locally
            int localStackItem = stack.pop();

            // Recurse to the next level
            sort_stack_recursion(stack);

            // Sort at each level while going bottom to top via recursion
            sort_stack(stack, localStackItem);
        }
    }

    private void sort_stack(Stack<Integer> stack, int localStackItem) {
        // If the stack is empty or the top element of the stack is less than the local item, push the local item
        if (stack.isEmpty() || stack.peek() < localStackItem) {
            stack.push(localStackItem);
            return;
        }

        // Get the current larger element than the given local stack item
        int temp = stack.pop();

        // Try to fit the local stack item in the next round given the current element which was greater is out
        // This will keep going until the local item becomes less than the stack's popped current element or the stack is empty
        sort_stack(stack, localStackItem);

        // Just add the current element given the stack is sorted already at this point
        stack.push(temp);
    }

    /**
     * This method reverses a stack using recursion.
     *
     * @param stack The stack of integers to be reversed.
     *              <p>
     *              Example:
     *              Given stack: [1, 2, 3]
     *              reverse_stack_recursion(stack) will make the stack: [3, 2, 1]
     */
    public void reverse_stack_recursion(Stack<Integer> stack) {
        // Base condition to stop recursion and prevent stack overflow
        if (!stack.isEmpty()) {
            // Remove and store locally
            int localStackItem = stack.peek();
            stack.pop();

            // Recurse to the next level
            reverse_stack_recursion(stack);

            // Insert at the bottom of the stack at each level while going bottom to top via recursion
            insertAtBottom(stack, localStackItem);
        }
    }

    private void insertAtBottom(Stack<Integer> stack, int localStackItem) {
        // Just add if it's empty, this is the last element of the previous stack order
        if (stack.isEmpty()) {
            stack.push(localStackItem);
        } else {
            // To get reverse, remove and store the current element and
            // send the next round against the local stack element

            int curr = stack.peek();
            stack.pop();
            insertAtBottom(stack, localStackItem);

            // Just push the current element as the stack is already in reverse
            stack.push(curr);
        }
    }

    /**
     * This method generates all binary strings of a given length.
     *
     * @param arr The array to store the binary strings.
     * @param len The length of the binary strings.
     *            <p>
     *            Example:
     *            Input: len = 2
     *            Output: 00, 01, 10, 11
     */
    public void generateAllBinaryStrings_Of_N_Len(int[] arr, int len) {
        // Start with 0 index
        generateAllBinaryStrings(arr, len, 0);
    }

    private void generateAllBinaryStrings(int[] arr, int len, int index) {
        // Print solution when reached to len
        if (index == len) {
            printArray(arr);
            return;
        }

        // Pick 0 and go to next level by + index
        arr[index] = 0;
        generateAllBinaryStrings(arr, len, index + 1);

        // Pick 1 and go to next level by + index
        arr[index] = 1;
        generateAllBinaryStrings(arr, len, index + 1);
    }

    /**
     * This method generates all valid combinations of n pairs of parentheses.
     *
     * @param n The number of pairs of parentheses.
     * @return A list of all valid combinations of n pairs of parentheses.
     * <p>
     * Example:
     * Input: n = 3
     * Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
     */
    public List<String> generateParenthesis(int n) {
        var ans = new ArrayList<String>();

        // Start with empty
        var curStr = new StringBuilder();
        // To ensure only valid parentheses are generated and added while backtracking process.
        int leftCount = 0;
        int rightCount = 0;

        generateParenthesisViaBackTracking(n, curStr, leftCount, rightCount, ans);

        return ans;
    }

    private void generateParenthesisViaBackTracking(int n, StringBuilder curStr, int leftCount, int rightCount, ArrayList<String> ans) {
        // Stop or base condition is when curStr is empty and left == 0 and right == 0.
        if (curStr.length() == 2 * n) {
            ans.add(curStr.toString());
            return;
        }

        if (leftCount < n) {
            // Add left bracket
            curStr.append('(');
            // Go to next round with increased left count
            generateParenthesisViaBackTracking(n, curStr, leftCount + 1, rightCount, ans);
            // Now remove last element as it went up to its final result and now new combination will be generated
            // aka Back Tracking
            curStr.deleteCharAt(curStr.length() - 1);
        }

        // If right bracket is less then we need to add to make valid bracket.
        if (rightCount < leftCount) {
            // Add right bracket
            curStr.append(')');
            // Go to next round with increased right count
            generateParenthesisViaBackTracking(n, curStr, leftCount, rightCount + 1, ans);
            // Now remove last element as it went up to its final result and now new combination will be generated
            // aka Back Tracking
            curStr.deleteCharAt(curStr.length() - 1);
        }
    }

    /**
     * This method generates all unique subsets of a given array using backtracking.
     *
     * @param arr the array for which subsets are to be generated
     * @return a list of all unique subsets
     * <p>
     * Example:
     * Input: [1, 2, 3]
     * Output: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
     */
    public List<List<Integer>> powerSet_AllSubsets(int[] arr) {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();

        // Generate subsets of all lengths
        for (int z = 0; z <= arr.length; z++) {
            backTrack_PowerSubSet(arr, 0, resList, currList, z);
        }

        return resList;
    }

    /**
     * This helper method uses backtracking to generate all unique subsets of a given length.
     *
     * @param arr        the array for which subsets are to be generated
     * @param startIndex the starting index for the subset
     * @param resList    the list to store the generated subsets
     * @param currList   the current subset being generated
     * @param limit      the desired length of the subsets
     */
    private void backTrack_PowerSubSet(int[] arr, int startIndex, List<List<Integer>> resList, List<Integer> currList, int limit) {
        // If the current subset has reached the desired length, add it to the result list
        if (currList.size() == limit) {
            resList.add(new ArrayList<>(currList));
            return;
        }

        // Generate subsets by including each element in the current subset
        for (int x = startIndex; x < arr.length; x++) {
            currList.add(arr[x]); // Add the current element to the subset

            // Generate subsets that include the current element
            backTrack_PowerSubSet(arr, x + 1, resList, currList, limit);

            // Remove the current element from the subset (backtrack)
            currList.remove(currList.size() - 1);
        }
    }

    /**
     * This method generates all unique subsets of a given array using backtracking, handling duplicates.
     *
     * @param arr the array for which subsets are to be generated
     * @return a list of all unique subsets
     * <p>
     * Example:
     * Input: [1, 2, 2]
     * Output: [[], [1], [2], [1, 2], [2, 2], [1, 2, 2]]
     */
    public List<List<Integer>> powerSet_AllSubsets_WithDuplicate(int[] arr) {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();

        Arrays.sort(arr); // Sort the array to handle duplicates

        // Generate subsets of all lengths
        for (int z = 0; z <= arr.length; z++) {
            powerSet_AllSubsets_WithDuplicate(arr, 0, resList, currList, z);
        }

        return resList;
    }

    /**
     * This helper method uses backtracking to generate all unique subsets of a given length, handling duplicates.
     *
     * @param arr        the array for which subsets are to be generated
     * @param startIndex the starting index for the subset
     * @param resList    the list to store the generated subsets
     * @param currList   the current subset being generated
     * @param limit      the desired length of the subsets
     */
    private void powerSet_AllSubsets_WithDuplicate(int[] arr, int startIndex, List<List<Integer>> resList, List<Integer> currList, int limit) {
        // If the current subset has reached the desired length, add it to the result list
        if (currList.size() == limit) {
            resList.add(new ArrayList<>(currList));
            return;
        }

        // Generate subsets by including each element in the current subset
        for (int x = startIndex; x < arr.length; x++) {
            // Skip duplicates
            if (x > startIndex && arr[x] == arr[x - 1]) {
                continue;
            }

            currList.add(arr[x]); // Add the current element to the subset

            // Generate subsets that include the current element
            powerSet_AllSubsets_WithDuplicate(arr, x + 1, resList, currList, limit);

            // Remove the current element from the subset (backtrack)
            currList.remove(currList.size() - 1);
        }
    }

    /**
     * This method generates all subsets of a given string using backtracking.
     * The approach is based on increasing the index and picking the character, and then increasing the index and not picking the character.
     *
     * @param index    The current index in the input string.
     * @param inputStr The input string.
     * @param output   The current subset.
     *                 <p>
     *                 Example:
     *                 Input: index = 0, inputStr = "abc", output = ""
     *                 Output: "", "a", "ab", "abc", "ac", "b", "bc", "c"
     */
    public void powerSet_AllSubsets_approach_2(int index, String inputStr, String output) {
        // Base case: if the index has reached the length of the input string, print the output
        if (index == inputStr.length()) {
            System.out.println(output + " ");
            return;
        }

        // Recursive case: pick the character at the current index and increase the index
        String picked = output + inputStr.charAt(index);
        powerSet_AllSubsets_approach_2(index + 1, inputStr, picked);

        // Recursive case: do not pick the character at the current index and increase the index
        powerSet_AllSubsets_approach_2(index + 1, inputStr, output);
    }

    /**
     * This method generates all subsets of a given string using backtracking, handling duplicates.
     * The approach is based on increasing the index and picking the character, and then increasing the index and not picking the character.
     * If the current character is the same as the next character, it is not picked to avoid generating duplicate subsets.
     *
     * @param index    The current index in the input string.
     * @param inputStr The input string.
     * @param output   The current subset.
     *                 <p>
     *                 Example:
     *                 Input: index = 0, inputStr = "aab", output = ""
     *                 Output: "", "a", "aa", "aab", "ab", "b"
     */
    public void powerSet_AllSubsets_approach_ForDuplicate(int index, String inputStr, String output) {
        // Base case: if the index has reached the length of the input string, print the output
        if (index == inputStr.length()) {
            System.out.println(output + " ");
            return;
        }

        // Recursive case: pick the character at the current index and increase the index
        String picked = output + inputStr.charAt(index);
        powerSet_AllSubsets_approach_ForDuplicate(index + 1, inputStr, picked);

        // If the current character is not the same as the next character, do not pick the character and increase the index
        if (index == 0 || inputStr.charAt(index) != inputStr.charAt(index + 1)) {
            powerSet_AllSubsets_approach_ForDuplicate(index + 1, inputStr, output);
        }
    }


    /**
     * This method finds all combinations of array elements that sum up to a target value.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return A list of lists where each list is a combination of numbers that sum up to the target.
     * <p>
     * Example:
     * combinationSum([2, 3, 6, 7], 7) will return: [[2, 2, 3], [7]]
     */
    public List<List<Integer>> combinationSum(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(arr, 0, target, res, new ArrayList<Integer>());
        return res;
    }

    private void combinationSum(int[] arr, int index, int target, List<List<Integer>> res, List<Integer> ds) {
        // Base condition: when reached array limit, check if target is matched
        if (index == arr.length) {
            // If target is 0 (meaning we've found a combination that sums up to the target), add it to the result
            if (target == 0) {
                res.add(new ArrayList<>(ds));
            }
            return; // Length reached but not target.
        }

        // Pick the current element and do NOT increase index
        int pk = arr[index];
        // Only pick when target is still achievable
        if (target >= pk) {
            ds.add(pk);
            int targetAfterPick = target - pk;
            combinationSum(arr, index, targetAfterPick, res, ds);
            // Remove picked one before moving on
            ds.remove(ds.size() - 1);
        }

        // Do NOT pick but increase index and with original target as no pick
        combinationSum(arr, index + 1, target, res, ds);
    }

    /**
     * This method finds all unique combinations of array elements that sum up to a target value.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return A list of lists where each list is a unique combination of numbers that sum up to the target.
     * <p>
     * Example:
     * combinationSum_WithDuplicate_Unique([2, 3, 2, 6, 7], 7) will return: [[2, 2, 3], [7]]
     */
    public List<List<Integer>> combinationSum_WithDuplicate_Unique(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        combinationSum_WithDuplicate_Unique(arr, 0, target, res, new ArrayList<Integer>());
        return res;
    }

    private void combinationSum_WithDuplicate_Unique(int[] arr, int index, int target, List<List<Integer>> res, List<Integer> ds) {
        // Base condition: when reached array limit, check if target is matched
        if (index == arr.length) {
            // If target is 0 (meaning we've found a combination that sums up to the target), add it to the result
            if (target == 0) {
                res.add(new ArrayList<>(ds));
            }
            return; // Length reached but not target.
        }

        // Pick the current element and do NOT increase index
        int pk = arr[index];
        // Only pick when target is still achievable
        if (target >= pk) {
            ds.add(pk);
            int targetAfterPick = target - pk;
            combinationSum(arr, index + 1, targetAfterPick, res, ds);
            // Remove picked one before moving on
            ds.remove(ds.size() - 1);
        }

        // Skip when next element is same as current
        while (index + 1 < arr.length && arr[index + 1] == arr[index]) {
            index++;
        }
        // Do NOT pick but increase index and with original target as no pick
        combinationSum(arr, index + 1, target, res, ds);
    }

    /**
     * This method calculates the sum of all subsets of the given array.
     *
     * @param arr The input array.
     * @return A sorted list of sums of all subsets.
     * <p>
     * Example:
     * Sum_All_Subsets([1, 2, 3]) will return: [0, 1, 2, 3, 3, 4, 5, 6]
     */
    public List<Integer> Sum_All_Subsets(int[] arr) {
        List<Integer> res = new ArrayList<>();
        backtracking_Sum_All_Subsets(arr, 0, 0, res);
        Collections.sort(res);
        return res;
    }


    /**
     * This method calculates the sum of all subsets of an array using backtracking.
     *
     * @param arr   The array for which the sum of subsets is to be calculated.
     * @param index The current index in the array.
     * @param sum   The current sum.
     * @param res   The result list to store the sum of all subsets.
     */
    void backtracking_Sum_All_Subsets(int[] arr, int index, int sum, List<Integer> res) {
        // Base condition: when reached array limit, add sum for that subset in result
        if (index == arr.length) {
            res.add(sum);
            return;
        }

        // Picking and adding value in sum
        backtracking_Sum_All_Subsets(arr, index + 1, sum + arr[index], res);

        // Not picking and not adding anything to sum
        backtracking_Sum_All_Subsets(arr, index + 1, sum, res);
    }

    /**
     * This method generates all possible letter combinations that the number could represent on a phone keypad.
     *
     * @param digits The digits entered on the phone keypad.
     * @return A list of all possible letter combinations.
     */
    public List<String> letterCombinations(String digits) {
        var res = new ArrayList<String>();
        if (digits.length() <= 0) return res;

        // Map for char to letter for phone pad.
        var map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        StringBuilder currCombination = new StringBuilder();

        letterCombinations_backtrack(digits, 0, currCombination, res, map);
        return res;
    }

    private void letterCombinations_backtrack(String digits, int index, StringBuilder currCombination, ArrayList<String> res, HashMap<Character, String> map) {
        // Base case: when all digits are covered
        if (index == digits.length()) {
            res.add(currCombination.toString());
            return;
        }

        // Get the letters corresponding to the current digit
        char digitCh = digits.charAt(index);
        String letters = map.get(digitCh);

        // For each letter, build combination with next digit char/letters
        for (int i = 0; i < letters.toCharArray().length; i++) {
            // Pick and move next index
            currCombination.append(letters.charAt(i));
            letterCombinations_backtrack(digits, index + 1, currCombination, res, map);

            // Remove current char from combination, which is equal to not pick and then call backtrack via next item in for loop.
            currCombination.deleteCharAt(currCombination.length() - 1);
        }
    }

    /**
     * This method generates all possible palindrome subsequences of a string.
     *
     * @param inputStr The input string.
     * @return A list of all possible palindrome subsequences.
     */
    public List<List<String>> palindrome_subsequence(String inputStr) {
        List<List<String>> output = new ArrayList<>();
        palindrome_subsequence_backTrack(inputStr, 0, output, new ArrayList<String>());
        return output;
    }

    private void palindrome_subsequence_backTrack(String inputStr, int index, List<List<String>> output, List<String> curList) {
        // Base condition: collect when reached to len
        if (index == inputStr.length()) {
            output.add(new ArrayList<>(curList));
            return;
        }

        // Start from index and keep moving to next char in string
        for (int x = index; x < inputStr.length(); x++) {
            // Check if the substring is a palindrome
            if (isPalindrome(inputStr, index, x)) {
                // Pick: add into bucket and move next char in string
                curList.add(inputStr.substring(index, x + 1));
                palindrome_subsequence_backTrack(inputStr, x + 1, output, curList);
                // Remove: not pick and move next index here via loop.
                curList.remove(curList.size() - 1);
            }
        }
    }


    /**
     * This method checks if a substring of a given string is a palindrome.
     *
     * @param s     the string to be checked
     * @param start the starting index of the substring
     * @param end   the ending index of the substring
     * @return true if the substring is a palindrome, false otherwise
     * <p>
     * Example:
     * Input: "ABC D CBA", start = 0, end = 8
     * Output: true
     */
    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if a given word can be found in a 2D character grid.
     *
     * @param board the 2D character grid
     * @param word  the word to be found
     * @return true if the word can be found, false otherwise
     * <p>
     * Example:
     * Input: board = [['A', 'B', 'C'], ['D', 'E', 'F'], ['G', 'H', 'I']], word = "BEH"
     * Output: true
     */
    public boolean Word_Search(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && Word_Search_backTracking(board, word, i, j, board.length, board[0].length, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This helper method uses backtracking to check if a given word can be found in a 2D character grid.
     *
     * @param board  the 2D character grid
     * @param word   the word to be found
     * @param rw     the current row index
     * @param col    the current column index
     * @param rowLen the number of rows in the grid
     * @param colLen the number of columns in the grid
     * @param index  the current index in the word
     * @return true if the word can be found, false otherwise
     */
    private boolean Word_Search_backTracking(char[][] board, String word, int rw, int col, int rowLen, int colLen, int index) {
        if (word.length() == index) {
            return true;
        }

        if (rw < 0 || col < 0 || rw == rowLen || col == colLen || word.charAt(index) != board[rw][col] || board[rw][col] == '#') {
            return false;
        }

        char curLetter = board[rw][col];
        board[rw][col] = '#';

        boolean found = Word_Search_backTracking(board, word, rw, col + 1, rowLen, colLen, index + 1)
                || Word_Search_backTracking(board, word, rw, col - 1, rowLen, colLen, index + 1)
                || Word_Search_backTracking(board, word, rw - 1, col, rowLen, colLen, index + 1)
                || Word_Search_backTracking(board, word, rw + 1, col, rowLen, colLen, index + 1);

        board[rw][col] = curLetter;

        return found;
    }

    /**
     * This method solves the N-Queens problem.
     *
     * @param n the number of queens and the size of the chessboard
     * @return a list of all possible solutions
     * <p>
     * Example:
     * Input: 4
     * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     */
    public List<List<String>> N_Queens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        var res = new ArrayList<List<String>>();
        solveQueen_backtracking(board, res, 0);

        return res;
    }

    /**
     * This helper method uses backtracking to solve the N-Queens problem.
     *
     * @param board the chessboard
     * @param res   the list to store the solutions
     * @param row   the current row index
     */
    private void solveQueen_backtracking(char[][] board, ArrayList<List<String>> res, int row) {
        if (row == board.length) {
            res.add(prepResultList(board));
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafeToPutQueen(board, row, col)) {
                board[row][col] = 'Q';
                solveQueen_backtracking(board, res, row + 1);
                board[row][col] = '.';
            }
        }
    }


    /**
     * This method converts each row of a 2D character array into a string and adds it to a list.
     *
     * @param board The 2D character array.
     * @return A list of strings, each representing a row of the 2D character array.
     */
    public List<String> prepResultList(char[][] board) {
        List<String> rowAsStringList = new LinkedListDSA_wpi<>();
        // Iterate over each row and convert it into a string
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            rowAsStringList.add(s);
        }
        return rowAsStringList;
    }

    /**
     * This method checks if it is safe to place a queen at a given position in a chessboard.
     * It checks the vertical row, left diagonal, and right diagonal for any existing queens.
     *
     * @param board The chessboard represented as a 2D character array.
     * @param row   The row index of the position.
     * @param col   The column index of the position.
     * @return True if it is safe to place a queen at the position, false otherwise.
     */
    public boolean isSafeToPutQueen(char[][] board, int row, int col) {
        // Check the vertical row
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check the left diagonal
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[row - i][col - i] == 'Q') {
                return false;
            }
        }

        // Check the right diagonal
        int maxRight = Math.min(row, board.length - 1 - col);
        for (int i = 1; i <= maxRight; i++) {
            if (board[row - i][col + i] == 'Q') {
                return false;
            }
        }

        return true;
    }

    /**
     * This method finds all possible paths for an animal to reach the end of a maze.
     * The animal can move in four directions: up, down, left, and right.
     * The animal cannot move to a blocked cell or revisit a cell.
     *
     * @param maze The maze represented as a 2D integer array. 0 represents a blocked cell and 1 represents a free cell.
     * @param size The size of the maze.
     * @return A list of strings, each representing a possible path.
     */
    public List<String> Maze_Animal(int[][] maze, int size) {
        List<String> res = new ArrayList<String>();
        // If the start is blocked, return an empty list
        if (maze[0][0] == 0) {
            return res;
        }
        Maze_Animal_backTracking(maze, size, 0, 0, new int[size][size], res, "");
        return res;
    }

    private void Maze_Animal_backTracking(int[][] maze, int mazeSize, int rw, int col, int[][] visitedNode, List<String> res, String direction) {
        // Base case: if the animal has reached the destination, add the path to the result
        if (rw == mazeSize - 1 && col == mazeSize - 1) {
            res.add(direction);
            return;
        }

        // Mark the current cell as visited
        visitedNode[rw][col] = 1;

        // Try to move down
        if (rw + 1 < mazeSize && maze[rw + 1][col] == 1 && visitedNode[rw + 1][col] == 0) {
            Maze_Animal_backTracking(maze, mazeSize, rw + 1, col, visitedNode, res, direction + 'D');
        }

        // Try to move left
        if (col - 1 >= 0 && maze[rw][col - 1] == 1 && visitedNode[rw][col - 1] == 0) {
            Maze_Animal_backTracking(maze, mazeSize, rw, col - 1, visitedNode, res, direction + 'L');
        }

        // Try to move right
        if (col + 1 < mazeSize && maze[rw][col + 1] == 1 && visitedNode[rw][col + 1] == 0) {
            Maze_Animal_backTracking(maze, mazeSize, rw, col + 1, visitedNode, res, direction + 'R');
        }

        // Try to move up
        if (rw - 1 >= 0 && maze[rw - 1][col] == 1 && visitedNode[rw - 1][col] == 0) {
            Maze_Animal_backTracking(maze, mazeSize, rw - 1, col, visitedNode, res, direction + 'U');
        }

        // Unmark the current cell so that it can be reused for a different route
        visitedNode[rw][col] = 0;
    }

    /**
     * This method checks if a ball can reach the destination in a maze by rolling in any of the four directions until it hits a wall.
     * The ball cannot roll through a wall or revisit a cell.
     *
     * @param maze        The maze represented as a 2D integer array. 0 represents a wall and 1 represents a free cell.
     * @param start       The starting position of the ball.
     * @param destination The destination position.
     * @return True if the ball can reach the destination, false otherwise.
     */
    public boolean Maze_RollingBall(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return Maze_RollingBall_backTrack(maze, start[0], start[1], destination, visited);
    }

    private boolean Maze_RollingBall_backTrack(int[][] maze, int row, int col, int[] destination, boolean[][] visited) {
        // Base case: if the ball has reached the destination, return true
        if (row == destination[0] && col == destination[1]) {
            return true;
        }

        // If the cell has been visited, return false
        if (visited[row][col]) {
            return false;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Try to move in all four directions
        boolean d = RollingNthenBackTrack(maze, row, col, 1, 0, destination, visited);
        boolean r = RollingNthenBackTrack(maze, row, col, 0, 1, destination, visited);
        boolean l = RollingNthenBackTrack(maze, row, col, 0, -1, destination, visited);
        boolean u = RollingNthenBackTrack(maze, row, col, -1, 0, destination, visited);

        return d || r || l || u;
    }

    /**
     * This method rolls a ball in a maze from a given position in a given direction until it hits a wall.
     * Then it backtracks to find a path to the destination.
     *
     * @param maze         The 2D array representing the maze.
     * @param row          The starting row position of the ball.
     * @param col          The starting column position of the ball.
     * @param rowDirection The direction of the ball in terms of rows.
     * @param colDirection The direction of the ball in terms of columns.
     * @param destination  The destination coordinates in the maze.
     * @param visited      A 2D boolean array to keep track of visited cells in the maze.
     * @return True if a path to the destination is found, false otherwise.
     */
    private boolean RollingNthenBackTrack(int[][] maze, int row, int col, int rowDirection, int colDirection, int[] destination, boolean[][] visited) {
        // Need local variables to add up and pass to backtrack method
        int rw = row;
        int cl = col;

        // Roll till hit wall then send new row/col pos back to backtrack
        while (rw + rowDirection >= 0 && rw + rowDirection <= maze.length - 1 && cl + colDirection >= 0 && cl + colDirection <= maze[0].length - 1 && maze[rw + rowDirection][cl + colDirection] == 0) {
            rw = rw + rowDirection;
            cl = cl + colDirection;
        }

        return Maze_RollingBall_backTrack(maze, rw, cl, destination, visited);
    }

    /**
     * This method solves the m-coloring problem using backtracking.
     * It colors a graph with m colors such that no two adjacent vertices have the same color.
     *
     * @param n The number of vertices in the graph.
     * @param m The number of colors.
     * @return True if the graph can be colored with m colors, false otherwise.
     */
    public boolean M_Coloring_Graph(int n, int m) {
        // Prepare graph data structure using n nodes
        List<Integer>[] Graph = new ArrayList[n];

        // Initialize each list in array
        for (int i = 0; i < n; i++) {
            Graph[i] = new ArrayList<>();
        }

        // Create graph
        Graph[0].add(1);
        Graph[1].add(0);
        Graph[1].add(2);
        Graph[2].add(1);
        Graph[2].add(3);
        Graph[3].add(2);
        Graph[3].add(0);
        Graph[0].add(3);
        Graph[0].add(2);
        Graph[2].add(0);

        // Track color for each node n
        int[] nodeColors = new int[n];

        return graphColoring_backTrack(Graph, nodeColors, 0, n, m);
    }

    private boolean graphColoring_backTrack(List<Integer>[] graph, int[] nodeColors, int curNode, int nodeLen, int color_m) {
        // Base condition: if current node is equal to the number of nodes, all nodes are colored
        if (curNode == nodeLen) {
            return true;
        }

        // For each color, try to put on each node
        for (int clr = 1; clr <= color_m; clr++) {
            // Apply constraint
            if (safeToColor(graph, nodeColors, curNode, nodeLen, clr)) {
                // Apply color and move next
                nodeColors[curNode] = clr;
                var ableToColorNodes = graphColoring_backTrack(graph, nodeColors, curNode + 1, nodeLen, color_m);
                // We were asked to stop when found answer and do not try other options
                if (ableToColorNodes) {
                    return true;
                }
                // Else drop color and try next color for this node
                nodeColors[curNode] = 0;
            }
        }

        return false;
    }

    private boolean safeToColor(List<Integer>[] graph, int[] nodeColors, int curNode, int nodeLen, int clr) {
        // Check if adjacent vertex does not have same color
        for (int adjNode : graph[curNode]) {
            if (nodeColors[adjNode] == clr) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method prints an array.
     *
     * @param inputA The input array.
     */
    private void printArray(int[] inputA) {
        for (int x = 0; x < inputA.length; x++) {
            System.out.print(inputA[x]);
        }
        System.out.println();
    }


}
