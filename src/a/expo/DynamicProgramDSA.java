package a.expo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicProgramDSA {

    /*

1. **Dynamic Programming vs Greedy Method**

    - Both Dynamic Programming (DP) and the Greedy Method are used for optimization, i.e., finding the optimal solution.
    - The Greedy Method already knows the optimal strategy (e.g., find minimum value or maximum weight), so it picks that strategy and works on it.
    - DP, on the other hand, does not know the optimal strategy in advance. It explores all possible solutions to find the optimal one.

2. **Methods Adopted by Dynamic Programming**

    DP adopts two methods: Memoization and Tabulation. However, DP mainly uses the Tabulation method.

    - **Memoization Method**: This method works with a top-to-bottom approach, i.e., recursion.

        For example, consider the Fibonacci series function which starts from the top by solving for 5 but before that goes down and solves for 4, 3, and so on (i.e., top --> down).

        ```
        fib(5)
            /             \
        fib(3)             fib(4)
          / \                / \
      fib(1) fib(2)     fib(2)    fib(3)
                                  / \
                              fib(1) fib(2)
        ```

        Without memoization, it would take `f(n-2) + f(n-1)` which is `O(2^N)`. If we add an array and store the result of the previous function, this exponential time complexity would become polynomial, and that too linear `O(N)`, as it would skip recalling the same function (e.g., `fib(3)`) as it was already counted and stored.

    - **Tabulation Method**: This method works based on a bottom-to-up approach, i.e., iterative.

        For example:

        ```
        fib(5)
        {
        Array [] stores result
        n==1  then 1
        n==0  then 0

          for (n = 2 -> 5)
          {
              fib(n-2) + fib(n-1)
          }
        }
        ```

        For the above, it starts with 2 then 3 and reaches up to 5 (i.e., bottom ---> up) and stores the result to avoid recounting. However, it always goes in `O(N)` due to the loop and requires storing the result for already calculated functions.

3. **Identifying a Dynamic Programming Problem**

    If the problem statement asks for the following, it might be a DP problem:
    - Count the total number of ways.
    - Given multiple ways of doing a task, which way will give the minimum or the maximum output.

    To solve a DP problem:
    - Try all possible choices/ways at every index according to the problem statement.
    - For example:
        1. If asked to count all the ways, then return the sum of all choices or ways.
        2. If asked to find the minimum or maximum, then return only the ways/choices which are minimum or maximum.
* */


    /**
     * This method calculates the nth term of the Fibonacci sequence using dynamic programming with memoization.
     *
     * @param term   The nth term to calculate.
     * @param memory The memory array used for memoization.
     * @return The nth term of the Fibonacci sequence.
     */
    public int DP_Memoization_Learn_Via_FibFunction(int term, int[] memory) {
        // Base case: if term is 1 or 0, return term
        if (term == 1 || term == 0) return term;

        // If term is found in memory, return it
        if (memory[term] != -1) {
            return memory[term];
        }

        // Calculate term recursively
        int termRes = DP_Memoization_Learn_Via_FibFunction(term - 2, memory) + DP_Memoization_Learn_Via_FibFunction(term - 1, memory);

        // Store term in memory
        memory[term] = termRes;
        return termRes;
    }

    /**
     * This method calculates the nth term of the Fibonacci sequence using dynamic programming with tabulation.
     *
     * @param term The nth term to calculate.
     * @return The nth term of the Fibonacci sequence.
     */
    public int DP_Tabulation_Learn_Via_FibFunction(int term) {
        // Base case: if term is 1 or 0, return term
        if (term == 1 || term == 0) return term;

        // Initialize memory array
        int[] memory = new int[term + 1];
        memory[0] = 0;
        memory[1] = 1;

        // Calculate each term iteratively
        for (int trm = 2; trm <= term; trm++) {
            memory[trm] = memory[trm - 2] + memory[trm - 1];
        }

        return memory[term];
    }

    /**
     * This method calculates the number of distinct ways to climb a staircase with n steps.
     * At each step, you can either climb 1 or 2 steps.
     *
     * @param steps The number of steps in the staircase.
     * @return The number of distinct ways to climb the staircase.
     */
    public int climbStairs_wo_Tabulation(int steps) {
        // Base case: if steps is 1 or 0, return 1
        if (steps == 1 || steps == 0) return 1;

        // Calculate the number of ways recursively
        var oneJumpAtGivenstep = climbStairs_wo_Tabulation(steps - 1);
        var twoJumpsAtGivenstep = climbStairs_wo_Tabulation(steps - 2);
        return oneJumpAtGivenstep + twoJumpsAtGivenstep;
    }

    /**
     * This method calculates the number of distinct ways to climb a staircase with n steps using dynamic programming with tabulation.
     * At each step, you can either climb 1 or 2 steps.
     *
     * @param steps The number of steps in the staircase.
     * @return The number of distinct ways to climb the staircase.
     */
    public int climbStairs_Tabulation(int steps) {
        // Base case: if steps is 1 or 0, return 1
        if (steps == 1 || steps == 0) return 1;

        // Initialize memory array
        int[] memory = new int[steps + 1];
        memory[0] = 1;
        memory[1] = 1;

        // Calculate the number of ways iteratively
        for (int st = 2; st <= steps; st++) {
            memory[st] = memory[st - 1] + memory[st - 2];
        }

        return memory[steps];
    }

    /**
     * This method calculates the minimum energy required for a frog to reach the top of the steps using memoization.
     *
     * @param steps   The number of steps.
     * @param heights The heights of each step.
     * @param energy  The energy array to store the minimum energy required to reach each step.
     * @return The minimum energy required to reach the top of the steps.
     */
    public int FrogJump_min_Energy_Memoziation(int steps, int[] heights, int[] energy) {
        if (steps == 0) {
            return 0;
        }

        if (energy[steps] != -1) {
            return energy[steps];
        }

        int calcualteEnergyForThisJump = Math.abs(heights[steps] - heights[steps - 1]);
        var calculatedEnergyForPrevStepToAddHere = FrogJump_min_Energy_Memoziation(steps - 1, heights, energy);
        int totalEnergyForJump1 = calcualteEnergyForThisJump + calculatedEnergyForPrevStepToAddHere;

        int totalEnergyForJump2 = Integer.MAX_VALUE;
        if (steps > 1) {
            int calcualteEnergyForThisTwoJumps = Math.abs(heights[steps] - heights[steps - 2]);
            var calculatedEnergyForPrevStepToAddHereForTwoJump = FrogJump_min_Energy_Memoziation(steps - 2, heights, energy);
            totalEnergyForJump2 = calcualteEnergyForThisTwoJumps + calculatedEnergyForPrevStepToAddHereForTwoJump;
        }

        int minEnergyFromTheseJumps = Math.min(totalEnergyForJump1, totalEnergyForJump2);
        energy[steps] = minEnergyFromTheseJumps;

        return energy[steps];
    }

    /**
     * This method calculates the minimum energy required for a frog to reach the top of the steps using tabulation.
     *
     * @param steps   The number of steps.
     * @param heights The heights of each step.
     * @return The minimum energy required to reach the top of the steps.
     */
    public int FrogJump_MIN_Energy_Tabulation(int steps, int[] heights) {
        if (steps == 0) {
            return 0;
        }

        int[] energy = new int[steps];
        Arrays.fill(energy, -1);

        energy[0] = 0;

        for (int st = 1; st < steps; st++) {
            int calcualteEnergyForThisJump = Math.abs(heights[st] - heights[st - 1]);
            var prevCalculatedEnergyToAdd = energy[st - 1];
            int totalEnergyForJump1 = calcualteEnergyForThisJump + prevCalculatedEnergyToAdd;

            int totalEnergyForJump2 = Integer.MAX_VALUE;
            if (st > 1) {
                int calcualteEnergyForThisTwoJumps = Math.abs(heights[st] - heights[st - 2]);
                var prevCalculatedEnergyToAddInToThisTwoJumps = energy[st - 2];
                totalEnergyForJump2 = calcualteEnergyForThisTwoJumps + prevCalculatedEnergyToAddInToThisTwoJumps;
            }

            int minEnergyFromTheseJumps = Math.min(totalEnergyForJump1, totalEnergyForJump2);
            energy[st] = minEnergyFromTheseJumps;
        }

        return energy[steps - 1];
    }

    /**
     * This method calculates the minimum energy required for a frog to reach the top of the steps using memoization. The frog can jump up to K steps.
     *
     * @param steps   The number of steps.
     * @param K       The maximum number of steps the frog can jump.
     * @param heights The heights of each step.
     * @param energy  The energy array to store the minimum energy required to reach each step.
     * @return The minimum energy required to reach the top of the steps.
     */
    public int FrogJump_min_Energy_Memoziation_K_Jumps(int steps, int K, int[] heights, int[] energy) {
        if (steps == 0) {
            return 0;
        }

        if (energy[steps] != -1) {
            return energy[steps];
        }

        int minEnergyFromTheseJumps = Integer.MAX_VALUE;

        for (int jmp = 1; jmp <= K; jmp++) {
            if (steps - jmp >= 0) {
                int calcualteEnergyForThisJump = Math.abs(heights[steps] - heights[steps - jmp]);
                var calculatedEnergyForPrevStepToAddHere = FrogJump_min_Energy_Memoziation_K_Jumps(steps - jmp, K, heights, energy);
                int totalEnergyForJump = calcualteEnergyForThisJump + calculatedEnergyForPrevStepToAddHere;
                minEnergyFromTheseJumps = Math.min(totalEnergyForJump, minEnergyFromTheseJumps);
            }
        }

        energy[steps] = minEnergyFromTheseJumps;

        return energy[steps];
    }

    /**
     * This method calculates the minimum energy required for a frog to reach the top of the steps using tabulation. The frog can jump up to K steps.
     *
     * @param steps   The number of steps.
     * @param K       The maximum number of steps the frog can jump.
     * @param heights The heights of each step.
     * @return The minimum energy required to reach the top of the steps.
     */
    public int FrogJump_MIN_Energy_Tabulation_K_Jumps(int steps, int K, int[] heights) {
        if (steps == 0) {
            return 0;
        }

        int[] energy = new int[steps];
        Arrays.fill(energy, -1);

        energy[0] = 0;

        for (int st = 1; st < steps; st++) {
            int totalEnergyFortheseJumps = Integer.MAX_VALUE;

            for (int jmp = 1; jmp <= K; jmp++) {
                if (st - jmp >= 0) {
                    int calcualteEnergyForThisJump = Math.abs(heights[st] - heights[st - jmp]);
                    var prevCalculatedEnergyToAdd = energy[st - jmp];
                    int totalEnergyForJump = calcualteEnergyForThisJump + prevCalculatedEnergyToAdd;
                    totalEnergyFortheseJumps = Math.min(totalEnergyForJump, totalEnergyFortheseJumps);
                }
            }

            energy[st] = totalEnergyFortheseJumps;
        }

        return energy[steps - 1];
    }


    /**
     * This method calculates the maximum sum of a subsequence with the constraint that no two numbers in the sequence should be adjacent in the array.
     * It uses memoization to optimize the computation.
     *
     * @param arr    the array of integers
     * @param index  the current index in the array
     * @param memory the array used for memoization
     * @return the maximum sum of a non-adjacent subsequence
     * <p>
     * Example:
     * Input: arr = [5, 5, 10, 40, 50, 35], index = 0, memory = new int[arr.length]
     * Output: 80
     */
    public int MaxSum_NonAdjacent_SubSequence_aka_Rob_Security_MEMOIZATION(int[] arr, int index, int[] memory) {
        if (index < 0) {
            return 0;
        }
        if (index == 0) {
            return arr[index];
        }

        if (memory[index] != -1) {
            return memory[index];
        }

        int res1 = arr[index] + MaxSum_NonAdjacent_SubSequence_aka_Rob_Security_MEMOIZATION(arr, index - 2, memory);
        int res2 = MaxSum_NonAdjacent_SubSequence_aka_Rob_Security_MEMOIZATION(arr, index - 1, memory);

        int res = Math.max(res1, res2);
        memory[index] = res;
        return res;
    }

    /**
     * This method calculates the maximum sum of a subsequence with the constraint that no two numbers in the sequence should be adjacent in the array.
     * It uses tabulation to optimize the computation.
     *
     * @param arr the array of integers
     * @return the maximum sum of a non-adjacent subsequence
     * <p>
     * Example:
     * Input: arr = [5, 5, 10, 40, 50, 35]
     * Output: 80
     */
    public int MaxSum_NonAdjacent_SubSequence_aka_Rob_Security_Tabulation(int[] arr) {
        int[] memory = new int[arr.length];
        memory[0] = arr[0];

        for (int n = 1; n < memory.length; n++) {
            int pick = arr[n];
            if (n > 1) {
                pick = pick + memory[n - 2];
            }

            int notPick = memory[n - 1];

            memory[n] = Math.max(pick, notPick);
        }

        return memory[arr.length - 1];
    }

    /**
     * This method calculates the maximum sum of a subsequence with the constraint that no two numbers in the sequence should be adjacent in the array.
     * It considers the case where the array is circular.
     *
     * @param arr the array of integers
     * @return the maximum sum of a non-adjacent subsequence
     * <p>
     * Example:
     * Input: arr = [10, 20, 30, 40, 50, 60, 70, 80, 90]
     * Output: 260
     */
    public int MaxSum_NonAdjacent_SubSequence_aka_Rob_2_Security_Tabulation(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        int[] arrFirst = new int[arr.length - 1];
        for (int i = 0; i < arrFirst.length; i++) {
            arrFirst[i] = arr[i];
        }

        int[] arrSecond = new int[arr.length - 1];
        for (int i = 0; i < arrSecond.length; i++) {
            arrSecond[i] = arr[i + 1];
        }

        int maxWithFirstIn = rob_2_security(arrFirst);
        int maxWithLastIn = rob_2_security(arrSecond);
        return Math.max(maxWithFirstIn, maxWithLastIn);
    }

    /**
     * This helper method calculates the maximum sum of a subsequence with the constraint that no two numbers in the sequence should be adjacent in the array.
     *
     * @param arr the array of integers
     * @return the maximum sum of a non-adjacent subsequence
     */
    int rob_2_security(int[] arr) {
        int[] memory = new int[arr.length];
        memory[0] = arr[0];

        for (int n = 1; n < memory.length; n++) {
            int pick = arr[n];
            if (n > 1) {
                pick = pick + memory[n - 2];
            }

            int notPick = memory[n - 1];

            memory[n] = Math.max(pick, notPick);
        }

        return memory[arr.length - 1];
    }

    /**
     * This method calculates the maximum points that can be obtained by a ninja during training.
     * The ninja can perform one task per day and cannot perform the same task on consecutive days.
     * The method uses memoization to optimize the calculation.
     *
     * @param points The points that can be obtained from each task on each day.
     * @return The maximum points that can be obtained.
     */
    public int NinjaTraining_Memoiazation(int[][] points) {
        int totalDays = points.length;
        int totalTasks = points[0].length + 1; // The total tasks include an additional task where nothing is performed.

        int[][] memory = new int[totalDays][totalTasks];
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        // Start from the last day with the task where nothing is performed.
        return NinjaTraining_Memoiazation(points, totalDays - 1, totalTasks - 1, memory);
    }

    private int NinjaTraining_Memoiazation(int[][] points, int dayIndex, int prevDayTask, int[][] memory) {
        // Return from memory if already found.
        if (memory[dayIndex][prevDayTask] != -1) {
            return memory[dayIndex][prevDayTask];
        }

        // Base case: when hit the first day, just return the maximum points.
        if (dayIndex == 0) {
            int maxPoints = 0;
            for (int tsk = 0; tsk < points[0].length; tsk++) {
                if (tsk != prevDayTask) {
                    int curDayPoint = points[dayIndex][tsk];
                    maxPoints = Math.max(maxPoints, curDayPoint);
                }
            }
            memory[dayIndex][prevDayTask] = maxPoints;
            return maxPoints;
        }

        // Perform each task excluding the previous day task and also call the previous day task to get the maximum of their values.
        int maxPoints = 0;
        for (int tsk = 0; tsk < points[0].length; tsk++) {
            if (tsk != prevDayTask) {
                int curDayPoint = points[dayIndex][tsk];
                int prevDayMaxPoint = NinjaTraining_Memoiazation(points, dayIndex - 1, tsk, memory);
                int totalPoints = curDayPoint + prevDayMaxPoint;
                maxPoints = Math.max(maxPoints, totalPoints);
            }
        }
        memory[dayIndex][prevDayTask] = maxPoints;
        return maxPoints;
    }

    /**
     * This method calculates the maximum points that can be obtained by a ninja during training.
     * The ninja can perform one task per day and cannot perform the same task on consecutive days.
     * The method uses tabulation to optimize the calculation.
     *
     * @param points The points that can be obtained from each task on each day.
     * @return The maximum points that can be obtained.
     */
    public int NinjaTraining_Tabulation(int[][] points) {
        int totalDays = points.length;
        int totalTasks = points[0].length + 1; // The total tasks include an additional task where nothing is performed.
        int[][] memory = new int[totalDays][totalTasks];

        // Prepare the maximum points for the first day for each task performed.
        memory[0][0] = Math.max(points[0][1], points[0][2]);
        memory[0][1] = Math.max(points[0][0], points[0][2]);
        memory[0][2] = Math.max(points[0][0], points[0][1]);
        memory[0][3] = Math.max(Math.max(points[0][0], points[0][1]), points[0][2]);

        // For each day, perform each task excluding the previous day task.
        for (int day = 1; day < totalDays; day++) {
            for (int prevDayTask = 0; prevDayTask < totalTasks; prevDayTask++) {
                memory[day][prevDayTask] = 0; // Store the maximum value of the previous day/task.
                for (int tsk = 0; tsk < points[0].length; tsk++) {
                    if (tsk != prevDayTask) {
                        int curDayPoint = points[day][tsk];
                        int prevDayMaxPoint = memory[day - 1][tsk];
                        int totalPoints = curDayPoint + prevDayMaxPoint;
                        memory[day][prevDayTask] = Math.max(totalPoints, memory[day][prevDayTask]);
                    }
                }
            }
        }

        return memory[totalDays - 1][totalTasks - 1]; // Return the maximum points collected on the last day.
    }

    /**
     * This method calculates the number of unique paths from the top-left corner to the bottom-right corner of a m x n grid.
     * The robot can only move either down or right at any point in time.
     * The method uses memoization to optimize the calculation.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths.
     */
    public int Robot_grid_uniquePaths_Memoization(int m, int n) {
        int[][] memory = new int[m][n];
        for (var row : memory) {
            Arrays.fill(row, -1);
        }
        return Robot_grid_uniquePaths_Memoization(m - 1, n - 1, memory);
    }

    public int Robot_grid_uniquePaths_Memoization(int row, int col, int[][] memory) {
        // Base case: reached the starting point.
        if (row == 0 && col == 0) {
            return 1;
        }
        // To keep inside boundaries.
        if (row < 0 || col < 0) {
            return 0;
        }

        // Direct from memory.
        if (memory[row][col] != -1) {
            return memory[row][col];
        }

        int left = Robot_grid_uniquePaths_Memoization(row, col - 1, memory);
        int up = Robot_grid_uniquePaths_Memoization(row - 1, col, memory);

        int res = left + up;
        memory[row][col] = res;

        return res;
    }

    /**
     * This method calculates the number of unique paths a robot can take in a grid.
     * The robot starts at the top left corner (0,0) and moves only in two directions: right and down.
     * The grid is represented by a 2D array with 'm' rows and 'n' columns.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths the robot can take.
     */
    public int Robot_grid_uniquePaths_Tabulation(int m, int n) {
        int[][] memory = new int[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // The starting point of the grid has only one path.
                if (row == 0 && col == 0) {
                    memory[row][col] = 1;
                    continue;
                }

                int up = 0, left = 0;

                // If there is a cell above the current cell, get its value.
                if (row > 0) {
                    up = memory[row - 1][col];
                }

                // If there is a cell to the left of the current cell, get its value.
                if (col > 0) {
                    left = memory[row][col - 1];
                }

                // The number of paths to the current cell is the sum of the paths to the cells above and to the left.
                int res = up + left;
                memory[row][col] = res;
            }
        }

        // The value in the bottom right corner of the grid is the total number of unique paths.
        return memory[m - 1][n - 1];
    }

    /**
     * This method calculates the number of unique paths a robot can take in a grid with obstacles.
     * The robot starts at the top left corner (0,0) and moves only in two directions: right and down.
     * The grid is represented by a 2D array where 0 represents an open cell and 1 represents an obstacle.
     *
     * @param obstacleGrid The grid with obstacles, where 0 is an open cell and 1 is an obstacle.
     * @return The number of unique paths the robot can take considering the obstacles.
     */
    public int Robot_grid_uniquePaths_2_Obstacles_Tabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] memory = new int[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // If the current cell is an obstacle, there are no paths through it.
                if (obstacleGrid[row][col] == 1) {
                    memory[row][col] = 0;
                    continue;
                }

                // The starting point of the grid has only one path.
                if (row == 0 && col == 0) {
                    memory[row][col] = 1;
                    continue;
                }

                int up = 0, left = 0;

                // If there is a cell above the current cell, get its value.
                if (row > 0) {
                    up = memory[row - 1][col];
                }

                // If there is a cell to the left of the current cell, get its value.
                if (col > 0) {
                    left = memory[row][col - 1];
                }

                // The number of paths to the current cell is the sum of the paths to the cells above and to the left.
                int res = up + left;
                memory[row][col] = res;
            }
        }

        // The value in the bottom right corner of the grid is the total number of unique paths.
        return memory[m - 1][n - 1];
    }

    /**
     * This method calculates the minimum path sum in a grid from top left to bottom right.
     * The robot can only move either down or right at any point in time.
     * The grid is represented by a 2D array where each cell contains a non-negative integer representing the cost of the path through that cell.
     *
     * @param matrix The grid represented as a 2D array of non-negative integers.
     * @return The minimum path sum from top left to bottom right.
     */
    public int min_path_sum_Memoization(int[][] matrix) {
        int[][] memory = new int[matrix.length][matrix[0].length];

        for (var row : memory) {
            Arrays.fill(row, -1);
        }

        return min_path_sum_Memoization(matrix, matrix.length - 1, matrix[0].length - 1, memory);
    }

    private int min_path_sum_Memoization(int[][] matrix, int row, int col, int[][] memory) {
        // The cost of the path through the starting cell is just the value of that cell.
        if (row == 0 && col == 0) {
            return matrix[0][0];
        }

        // If the cell is outside the grid, return a large number.
        if (row < 0 || col < 0) {
            return (int) Math.pow(10, 9);
        }

        // If the minimum path sum through this cell has already been calculated, return it.
        if (memory[row][col] != -1) {
            return memory[row][col];
        }

        // Calculate the minimum path sum through the cells to the left and above the current cell.
        int left = matrix[row][col] + min_path_sum_Memoization(matrix, row, col - 1, memory);
        int up = matrix[row][col] + min_path_sum_Memoization(matrix, row - 1, col, memory);

        // The minimum path sum through the current cell is the smaller of the two sums.
        int minSum = Math.min(left, up);
        memory[row][col] = minSum;

        return minSum;
    }

    /**
     * This method calculates the minimum path sum in a grid from top left to bottom right using tabulation.
     * The robot can only move either down or right at any point in time.
     * The grid is represented by a 2D array where each cell contains a non-negative integer representing the cost of the path through that cell.
     *
     * @param matrix The grid represented as a 2D array of non-negative integers.
     * @return The minimum path sum from top left to bottom right.
     */
    public int min_path_sum_Memoization_Tabulation(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] memory = new int[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // The cost of the path through the starting cell is just the value of that cell.
                if (row == 0 && col == 0) {
                    memory[row][col] = matrix[row][col];
                    continue;
                }

                int up = matrix[row][col]; // The cost of the path through the current cell.
                int left = matrix[row][col]; // The cost of the path through the current cell.

                // If there is a cell above the current cell, add its minimum path sum to the cost of the current cell.
                if (row > 0) {
                    up += memory[row - 1][col];
                } else {
                    up += Math.pow(10, 9); // If there is no cell above, add a large number.
                }

                // If there is a cell to the left of the current cell, add its minimum path sum to the cost of the current cell.
                if (col > 0) {
                    left += memory[row][col - 1];
                } else {
                    left += Math.pow(10, 9); // If there is no cell to the left, add a large number.
                }

                // The minimum path sum through the current cell is the smaller of the two sums.
                int res = Math.min(left, up);
                memory[row][col] = res;
            }
        }

        // The value in the bottom right corner of the grid is the minimum path sum.
        return memory[m - 1][n - 1];
    }


    /**
     * This method calculates the minimum path sum in a triangle using memoization.
     * The triangle is represented as a 2D matrix.
     *
     * @param matrix The input 2D matrix representing the triangle.
     * @return The minimum path sum from the top to the bottom of the triangle.
     */
    public int min_path_sum_Triangular_Memoization(int[][] matrix) {
        int len = matrix.length;
        int[][] memory = new int[len][len];

        // Initialize the memory matrix with -1
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        // Start the recursive function from the top of the triangle (0, 0)
        return min_path_sum_Triangular_Memoization(matrix, 0, 0, memory);
    }

    /**
     * This is a helper method for the min_path_sum_Triangular_Memoization method.
     * It uses recursion and memoization to calculate the minimum path sum.
     *
     * @param triangle The input 2D matrix representing the triangle.
     * @param row      The current row in the triangle.
     * @param col      The current column in the triangle.
     * @param memory   The 2D memory matrix used for memoization.
     * @return The minimum path sum from the current position to the bottom of the triangle.
     */
    int min_path_sum_Triangular_Memoization(int[][] triangle, int row, int col, int[][] memory) {
        // Base case: if we have reached the end of the row length i.e., at the bottom, stop and return the value
        if (row == triangle.length - 1) {
            return triangle[row][col];
        }

        // If the value is already computed, return it directly from memory
        if (memory[row][col] != -1) {
            return memory[row][col];
        }

        // Calculate the sum of the current position and the minimum path sum of the next row in the same column (down)
        int down = triangle[row][col] + min_path_sum_Triangular_Memoization(triangle, row + 1, col, memory);

        // Calculate the sum of the current position and the minimum path sum of the next row in the next column (down diagonal)
        int downDiagonal = triangle[row][col] + min_path_sum_Triangular_Memoization(triangle, row + 1, col + 1, memory);

        // Find the minimum sum between down and downDiagonal
        int minSum = Math.min(down, downDiagonal);

        // Store the minimum sum in the memory matrix for future reference
        memory[row][col] = minSum;

        // Return the minimum sum
        return minSum;
    }


    /**
     * This method calculates the minimum path sum in a triangle using tabulation.
     * The triangle is represented as a 2D matrix.
     *
     * @param triangle The input 2D matrix representing the triangle.
     * @return The minimum path sum from the top to the bottom of the triangle.
     */
    public int min_path_sum_Triangular_Tabulation(int[][] triangle) {
        int len = triangle.length;
        int[][] memory = new int[len][len];

        // Fill the last row with the values from the triangle
        for (int c = 0; c < len; c++) {
            memory[len - 1][c] = triangle[len - 1][c];
        }

        // Start from the second last row and go up to the top
        for (int row = len - 2; row >= 0; row--) {
            for (int col = row; col >= 0; col--) {
                // Calculate the sum of the current position and the minimum path sum of the next row in the same column (up)
                int up = triangle[row][col] + memory[row + 1][col];
                // Calculate the sum of the current position and the minimum path sum of the next row in the next column (up diagonal)
                int upDiagonal = triangle[row][col] + memory[row + 1][col + 1];

                // Store the minimum sum in the memory matrix
                memory[row][col] = Math.min(up, upDiagonal);
            }
        }

        // The first position has the final minimum result from bottom to top
        return memory[0][0];
    }

    /**
     * This method calculates the maximum falling sum from any cell of the first row using memoization.
     * The input is represented as a 2D matrix.
     *
     * @param matrix The input 2D matrix.
     * @return The maximum falling sum from any cell of the first row.
     */
    public int Max_Falling_sum_FromAnyCellOfFirstRow_Memoization(int[][] matrix) {
        int len = matrix.length;
        int[][] memory = new int[len][len];

        // Initialize the memory matrix with -1
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        int maxTotal = Integer.MIN_VALUE;
        // Try different columns from the first row
        for (int col = 0; col < matrix[0].length; col++) {
            // It is recursive from the last row to the first, so we send the last rows
            // Else it is like going from the first row to the last row
            int thisCellTotal = Max_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, len - 1, col, memory);
            // Update the maximum total if the current total is greater
            maxTotal = Math.max(maxTotal, thisCellTotal);
        }

        // Return the maximum total
        return maxTotal;
    }


    /**
     * This method calculates the maximum falling sum from any cell of the first row using memoization.
     *
     * @param matrix The input matrix.
     * @param row    The current row.
     * @param col    The current column.
     * @param memory The memory array to store the maximum falling sum for each cell.
     * @return The maximum falling sum from the current cell.
     */
    public int Max_Falling_sum_FromAnyCellOfFirstRow_Memoization(int[][] matrix, int row, int col, int[][] memory) {
        if (col < 0 || col >= matrix[0].length) {
            return Integer.MIN_VALUE;
        }

        if (row == 0) {
            return matrix[row][col];
        }

        if (memory[row][col] != -1) {
            return memory[row][col];
        }

        int up = matrix[row][col] + Max_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, row - 1, col, memory);
        int upleft = matrix[row][col] + Max_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, row - 1, col - 1, memory);
        int upright = matrix[row][col] + Max_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, row - 1, col + 1, memory);

        return memory[row][col] = Math.max(Math.max(up, upleft), upright);
    }

    /**
     * This method calculates the minimum falling sum from any cell of the first row using memoization.
     *
     * @param matrix The input matrix.
     * @return The minimum falling sum from any cell of the first row.
     */
    public int MIN_Falling_sum_FromAnyCellOfFirstRow_Memoization(int[][] matrix) {
        int len = matrix.length;
        int[][] memory = new int[len][len];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        int minTotal = Integer.MAX_VALUE;
        for (int col = 0; col < matrix[0].length; col++) {
            int thisCellTotal = MIN_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, len - 1, col, memory);
            minTotal = Math.min(minTotal, thisCellTotal);
        }
        return minTotal;
    }

    private int MIN_Falling_sum_FromAnyCellOfFirstRow_Memoization(int[][] matrix, int row, int col, int[][] memory) {
        if (col < 0 || col >= matrix[0].length) {
            return (int) Math.pow(10, 9);
        }

        if (row == 0) {
            return matrix[row][col];
        }

        if (memory[row][col] != -1) {
            return memory[row][col];
        }

        int up = matrix[row][col] + MIN_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, row - 1, col, memory);
        int upleft = matrix[row][col] + MIN_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, row - 1, col - 1, memory);
        int upright = matrix[row][col] + MIN_Falling_sum_FromAnyCellOfFirstRow_Memoization(matrix, row - 1, col + 1, memory);

        return memory[row][col] = Math.min(Math.min(up, upleft), upright);
    }

    /**
     * This method calculates the minimum falling sum from any cell of the first row using tabulation.
     *
     * @param matrix The input matrix.
     * @return The minimum falling sum from any cell of the first row.
     */
    public int MIN_Falling_sum_FromAnyCellOfFirstRow_Tabulation(int[][] matrix) {
        int len = matrix.length;
        int[][] memory = new int[len][len];

        for (int col = 0; col < matrix[0].length; col++) {
            memory[0][col] = matrix[0][col];
        }

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                int up = matrix[row][col] + memory[row - 1][col];

                int upLeft = matrix[row][col];
                if (col - 1 >= 0) {
                    upLeft = upLeft + memory[row - 1][col - 1];
                } else {
                    upLeft = (int) (upLeft + Math.pow(10, 9));
                }

                int upRight = matrix[row][col];
                if (col + 1 < matrix[0].length) {
                    upRight = upRight + memory[row - 1][col + 1];
                } else {
                    upRight = (int) (upRight + Math.pow(10, 9));
                }

                memory[row][col] = Math.min(Math.min(up, upLeft), upRight);
            }
        }

        int minTotal = Integer.MAX_VALUE;
        for (int col = 0; col < matrix[0].length; col++) {
            minTotal = Math.min(memory[matrix.length - 1][col], minTotal);
        }
        return minTotal;
    }

    /**
     * This method calculates the minimum falling sum from any cell of the first row with non-zero shifts using memoization.
     *
     * @param matrix The input matrix.
     * @return The minimum falling sum from any cell of the first row with non-zero shifts.
     */
    public int MIN_Falling_sum_non_zero_shifts_Memoization(int[][] matrix) {
        int len = matrix.length;
        int[][] memory = new int[len][len];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        int minTotal = Integer.MAX_VALUE;

        for (int col = 0; col < matrix[0].length; col++) {
            int thisCellTotal = MIN_Falling_sum_non_zero_shifts_Memoization(matrix, len - 1, col, memory);
            minTotal = Math.min(minTotal, thisCellTotal);
        }
        return minTotal;
    }


    /**
     * This method calculates the minimum falling sum in a matrix with non-zero shifts using memoization.
     *
     * @param matrix the input matrix
     * @param row    the current row index
     * @param col    the current column index
     * @param memory the array used for memoization
     * @return the minimum falling sum
     * <p>
     * Example:
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]], row = 0, col = 0, memory = new int[matrix.length][matrix[0].length]
     * Output: 13
     */
    public int MIN_Falling_sum_non_zero_shifts_Memoization(int[][] matrix, int row, int col, int[][] memory) {
        if (col < 0 || col >= matrix[0].length) {
            return (int) Math.pow(10, 9);
        }

        if (row == 0) {
            return matrix[row][col];
        }

        if (memory[row][col] != -1) {
            return memory[row][col];
        }

        int minTotal = Integer.MAX_VALUE;
        for (int c = 0; c < matrix[0].length; c++) {
            if (c != col) {
                int up = matrix[row][col] + MIN_Falling_sum_non_zero_shifts_Memoization(matrix, row - 1, c, memory);
                minTotal = Math.min(up, minTotal);
            }
        }

        return memory[row][col] = minTotal;
    }

    /**
     * This method checks if there is a subset of the given array that sums up to the target value using memoization.
     *
     * @param arr    the array of integers
     * @param target the target sum
     * @return true if there is a subset that sums up to the target, false otherwise
     * <p>
     * Example:
     * Input: arr = [3, 34, 4, 12, 5, 2], target = 9
     * Output: true
     */
    public boolean subsetSumToK_Memoization(int[] arr, int target) {
        int[][] memory = new int[arr.length][target + 1];

        for (int row[] : memory) {
            Arrays.fill(row, -1);
        }

        return subsetSumToK_Memoization(arr, arr.length - 1, target, memory);
    }

    /**
     * This helper method checks if there is a subset of the given array that sums up to the target value using memoization.
     *
     * @param arr    the array of integers
     * @param idx    the current index in the array
     * @param target the target sum
     * @param memory the array used for memoization
     * @return true if there is a subset that sums up to the target, false otherwise
     */
    boolean subsetSumToK_Memoization(int[] arr, int idx, int target, int[][] memory) {
        if (target == 0) return true;

        if (idx == 0) {
            return arr[idx] == target;
        }

        if (memory[idx][target] != -1) {
            return memory[idx][target] == 1 ? true : false;
        }

        boolean pk = false;
        if (arr[idx] <= target) {
            int leftTargetValue = target - arr[idx];
            pk = subsetSumToK_Memoization(arr, idx - 1, leftTargetValue, memory);
        }

        boolean ntpk = subsetSumToK_Memoization(arr, idx - 1, target, memory);

        memory[idx][target] = pk || ntpk ? 1 : 0;
        return pk || ntpk;
    }

    /**
     * This method checks if there is a subset of the given array that sums up to the target value using tabulation.
     *
     * @param arr    the array of integers
     * @param target the target sum
     * @return true if there is a subset that sums up to the target, false otherwise
     * <p>
     * Example:
     * Input: arr = [3, 34, 4, 12, 5, 2], target = 9
     * Output: true
     */
    public boolean subsetSumToK_Tabulation(int[] arr, int target) {
        boolean memory[][] = new boolean[arr.length][target + 1];

        for (int rw = 0; rw < arr.length; rw++) {
            memory[rw][0] = true;
        }

        int firstIndexValue = arr[0];
        if (firstIndexValue <= target) {
            memory[0][firstIndexValue] = true;
        }

        for (int idx = 1; idx < arr.length; idx++) {
            for (int tg = 1; tg <= target; tg++) {
                boolean notTaken = memory[idx - 1][tg];

                boolean taken = false;
                if (arr[idx] <= tg) {
                    var newTarget = tg - arr[idx];
                    taken = memory[idx - 1][newTarget];
                }
                memory[idx][tg] = notTaken || taken;
            }
        }

        var res = memory[arr.length - 1][target];

        return res;
    }

    /**
     * This method checks if there is a subset of the given array that sums up to the target value using tabulation and space optimization.
     *
     * @param sumArray the array of integers
     * @param k        the target sum
     * @return true if there is a subset that sums up to the target, false otherwise
     * <p>
     * Example:
     * Input: sumArray = [3, 34, 4, 12, 5, 2], k = 9
     * Output: true
     */
    public boolean subsetSumToK_Tabulation_SpaceOptimized(int[] sumArray, int k) {
        var prev = new boolean[k + 1];
        var arraylen = sumArray.length;
        prev[0] = true;

        if (sumArray[0] <= k) {
            prev[sumArray[0]] = true;
        }

        for (int idx = 1; idx < arraylen; idx++) {
            var curr = new boolean[k + 1];
            curr[0] = true;

            for (int target = 1; target <= k; target++) {
                var ntkn = prev[target];

                var tkn = false;
                if (sumArray[idx] <= target) {
                    var newTarget = target - sumArray[idx];
                    tkn = prev[newTarget];
                }
                curr[target] = ntkn || tkn;
            }
            prev = curr.clone();
        }

        return prev[k];
    }

    /**
     * This method checks if the given array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
     *
     * @param arr the array of integers
     * @return true if the array can be partitioned, false otherwise
     * <p>
     * Example:
     * Input: arr = [1, 5, 11, 5]
     * Output: true
     */
    public boolean Can_Partition_aka_EqualSubsetSum_Memoization(int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }

        if (totalSum % 2 == 1) {
            return false;
        }

        int target = totalSum / 2;

        return subsetSumToK_Memoization(arr, target);
    }

    /**
     * This method partitions an array into two subsets such that the absolute difference between their sums is minimized.
     * It uses memoization to optimize the calculation.
     *
     * @param arr The input array.
     * @return The minimum absolute difference between the sums of the two subsets.
     */
    public int PartitionEqualSubsetWithMinAbsolute_Memoization(int[] arr) {
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }
        return PartitionEqualSubsetWithMinAbsolute_Memoization(arr, arr.length - 1, totalSum, 0);
    }

    private int PartitionEqualSubsetWithMinAbsolute_Memoization(int[] arr, int idx, int totalSum, int calculatedSum) {
        // Base case: when reach the last element of the array
        if (idx == 0) {
            int remainingSum = Math.abs(totalSum - calculatedSum);
            int diff = Math.abs(calculatedSum - remainingSum);
            return diff;
        }

        // Pick and move to the next
        int pickedSum = calculatedSum + arr[idx];
        int pk = PartitionEqualSubsetWithMinAbsolute_Memoization(arr, idx - 1, totalSum, pickedSum);

        // Not pick and move to the next
        int ntpk = PartitionEqualSubsetWithMinAbsolute_Memoization(arr, idx - 1, totalSum, calculatedSum);

        // Pick the minimum difference
        return Math.min(ntpk, pk);
    }

    /**
     * This method counts the number of subsets of an array that sum up to a target value.
     * It uses memoization to optimize the calculation.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return The number of subsets that sum up to the target.
     */
    public int CountSubsets_equalTo_Sum_K_Memoization(int[] arr, int target) {
        int[][] memory = new int[arr.length][target + 1];
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }
        return CountSubsets_equalTo_Sum_K_Memoization(arr, arr.length - 1, target, memory);
    }

    public int CountSubsets_equalTo_Sum_K_Memoization(int[] arr, int idx, int target, int[][] memory) {
        // Base case: target achieved already
        if (target == 0) {
            return 1;
        }

        // Reached to the final element
        // At this point, target is still not achieved
        if (idx == 0) {
            return arr[idx] == target ? 1 : 0; // If the last index value equals the target, then return 1
        }

        // Direct from memory
        if (memory[idx][target] != -1) {
            return memory[idx][target];
        }

        // Pick
        int picked = 0;
        if (target >= arr[idx]) {
            int leftTarget = target - arr[idx];
            picked = CountSubsets_equalTo_Sum_K_Memoization(arr, idx - 1, leftTarget, memory);
        }

        // Not pick
        int notPicked = CountSubsets_equalTo_Sum_K_Memoization(arr, idx - 1, target, memory);

        memory[idx][target] = picked + notPicked;
        return memory[idx][target];
    }

    /**
     * This method counts the number of subsets of an array that sum up to a target value.
     * It uses tabulation to optimize the calculation.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return The number of subsets that sum up to the target.
     */
    public int CountSubsets_equalTo_Sum_K_Tabulation(int[] arr, int target) {
        int[][] memory = new int[arr.length][target + 1];

        // When target is 0, all indices can do so set all rows for the first column to true
        for (int rw = 0; rw < arr.length; rw++) {
            memory[rw][0] = 1;
        }

        // If the first value is equal or lesser than the target, then set count to 1
        if (arr[0] <= target) {
            memory[0][arr[0]] = 1;
        }

        // Start for other indices
        for (int idx = 1; idx < arr.length; idx++) {
            // Start from target 1 to K
            for (int tg = 1; tg <= target; tg++) {
                // Pick
                int picked = 0;
                if (tg >= arr[idx]) {
                    int leftTarget = tg - arr[idx];
                    picked = memory[idx - 1][leftTarget];
                }

                // Not pick
                int notPicked = memory[idx - 1][tg];

                memory[idx][tg] = picked + notPicked;
            }
        }

        return memory[arr.length - 1][target];
    }


    /**
     * This method counts the number of subsets in an array that sum up to a given target.
     * It uses memoization to store intermediate results for efficiency.
     *
     * @param arr    The input array.
     * @param target The target sum.
     * @return The number of subsets that sum up to the target.
     */
    public int CountSubsets_equalTo_Sum_K_Memoization_EdgeCases(int[] arr, int target) {
        int[][] memory = new int[arr.length][target + 1];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        return CountSubsets_equalTo_Sum_K_Memoization_EdgeCases(arr, arr.length - 1, target, memory);
    }

    private int CountSubsets_equalTo_Sum_K_Memoization_EdgeCases(int[] arr, int idx, int target, int[][] memory) {
        // If we have reached the first element of the array.
        if (idx == 0) {
            // If the target is zero and the first element is also zero, there are two subsets: {0} and {}.
            if (target == 0 && arr[0] == 0) {
                return 2;
            }

            // If the first element is equal to the target, or the target is zero, there is one subset.
            if (arr[idx] == target || target == 0) {
                return 1;
            }

            // If the first element is not equal to the target, there are no subsets.
            return 0;
        }

        // If the result has been computed before, return it directly from memory.
        if (memory[idx][target] != -1) {
            return memory[idx][target];
        }

        int picked = 0;
        // If the current element is less than or equal to the target, we can pick it.
        if (target >= arr[idx]) {
            int leftTarget = target - arr[idx];
            picked = CountSubsets_equalTo_Sum_K_Memoization_EdgeCases(arr, idx - 1, leftTarget, memory);
        }

        // Calculate the number of subsets when the current element is not picked.
        int notPicked = CountSubsets_equalTo_Sum_K_Memoization_EdgeCases(arr, idx - 1, target, memory);

        // The total number of subsets is the sum of the subsets when the current element is picked and not picked.
        memory[idx][target] = picked + notPicked;
        return memory[idx][target];
    }

    /**
     * This method counts the number of subsets in an array that have a sum equal to a given difference.
     * It first calculates the target sum, then calls the CountSubsets_equalTo_Sum_K_Memoization_EdgeCases method.
     *
     * @param arr  The input array.
     * @param diff The target difference.
     * @return The number of subsets that have a sum equal to the difference.
     */
    public int CountSubsets_equalTo_DIFF_K_Memoization(int[] arr, int diff) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        int revisedTotal = total - diff;
        int target = revisedTotal / 2;

        if (target < 0 || revisedTotal % 2 == 1) return -1; // We cannot proceed with a negative or decimal target.

        int[][] memory = new int[arr.length][target + 1];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        return CountSubsets_equalTo_Sum_K_Memoization_EdgeCases(arr, arr.length - 1, target, memory);
    }

    /**
     * This method solves the 0/1 Knapsack problem using memoization.
     * It calculates the maximum value that can be put in a knapsack of capacity WCapacity.
     *
     * @param wt        The array of weights.
     * @param val       The array of values.
     * @param WCapacity The capacity of the knapsack.
     * @return The maximum value that can be put in the knapsack.
     */
    public int knapsack_01_limited_Memoization(int[] wt, int[] val, int WCapacity) {
        int[][] memory = new int[wt.length][WCapacity + 1];
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        return knapsack_01_limited_Memoization(wt, val, WCapacity, wt.length - 1, memory);
    }

    private int knapsack_01_limited_Memoization(int[] wt, int[] val, int WCapacity, int idx, int[][] memory) {
        // If we have reached the first element of the array.
        if (idx == 0) {
            // If the weight of the first element is less than or equal to the capacity, we can pick it.
            if (WCapacity >= wt[idx]) {
                return val[idx];
            }

            // If the weight of the first element is greater than the capacity, we cannot pick it.
            return 0;
        }

        // If the result has been computed before, return it directly from memory.
        if (memory[idx][WCapacity] != -1) {
            return memory[idx][WCapacity];
        }

        int tkVal = Integer.MIN_VALUE;
        // If the current element's weight is less than or equal to the capacity, we can pick it.
        if (WCapacity >= wt[idx]) {
            int currValue = val[idx];
            int currWeight = wt[idx];
            int capacityLeft = WCapacity - currWeight;

            tkVal = currValue + knapsack_01_limited_Memoization(wt, val, capacityLeft, idx - 1, memory);
        }

        // Calculate the maximum value when the current element is not picked.
        int ntVal = 0 + knapsack_01_limited_Memoization(wt, val, WCapacity, idx - 1, memory);

        // The maximum value is the maximum of the values when the current element is picked and not picked.
        int finalVal = Math.max(tkVal, ntVal);
        memory[idx][WCapacity] = finalVal;
        return finalVal;
    }


    /**
     * This method solves the 0/1 Knapsack problem using tabulation (bottom-up approach).
     *
     * @param wt        The array of weights.
     * @param val       The array of values corresponding to each weight.
     * @param WCapacity The total capacity of the knapsack.
     * @return The maximum value that can be obtained by picking items without exceeding the knapsack capacity.
     * <p>
     * Example:
     * Suppose we have weights [1, 3, 4, 5] and their corresponding values [1, 4, 5, 7] and the knapsack capacity is 7.
     * The maximum value that can be obtained is 9 (by picking weights 3 and 4).
     */
    public int knapsack_01_limited_Tabulation(int[] wt, int[] val, int WCapacity) {
        int[][] memory = new int[wt.length][WCapacity + 1];

        // Bootstrap with first weight and fill in memory till its capacity.
        int firstWeight = wt[0];
        for (int x = firstWeight; x <= WCapacity; x++) {
            memory[0][x] = val[0];
        }

        // From the second given weight
        for (int wtIdx = 1; wtIdx < wt.length; wtIdx++) {
            // Fill till capacity
            for (int cap = 0; cap <= WCapacity; cap++) {
                int taken = Integer.MIN_VALUE;

                // Pick the current item if it doesn't exceed the current capacity
                if (cap >= wt[wtIdx]) {
                    taken = val[wtIdx] + memory[wtIdx - 1][cap - wt[wtIdx]];
                }

                // Not pick the current item
                int notTaken = memory[wtIdx - 1][cap];

                // Store the maximum value obtained by either picking or not picking the current item
                memory[wtIdx][cap] = Math.max(notTaken, taken);
            }
        }
        return memory[wt.length - 1][WCapacity];
    }

    /**
     * This method finds the minimum number of coins needed to make a given amount.
     * It uses memoization (top-down approach) and assumes an unlimited supply of coins of each denomination.
     *
     * @param arr       The array of coin denominations.
     * @param targetSum The target sum.
     * @return The minimum number of coins needed to make the target sum. If it's not possible, return -1.
     * <p>
     * Example:
     * Suppose we have coin denominations [1, 2, 5] and the target sum is 11.
     * The minimum number of coins needed is 3 (by using one 5-coin and three 2-coins).
     */
    public int MinimumCoins_UnlimitedCoins_aka_coinChange_Memoization(int[] arr, int targetSum) {
        int[][] memory = new int[arr.length][targetSum + 1];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        int ans = MinimumCoins_UnlimitedCoins_aka_coinChange_Memoization(arr, arr.length - 1, targetSum, memory);
        if (ans >= Math.pow(10, 9)) {
            return -1;
        }
        return ans;
    }

    private int MinimumCoins_UnlimitedCoins_aka_coinChange_Memoization(int[] coinArr, int coinIdx, int targetSum, int[][] memory) {
        // Base case: if we're at the last coin, check if we can find an exact match
        if (coinIdx == 0) {
            if (targetSum % coinArr[coinIdx] == 0) {
                return targetSum / coinArr[coinIdx];  // If we can find an exact match, return the number of coins
            }
            return (int) Math.pow(10, 9);  // If we can't achieve the target with the last coin, return a large value
        }

        // If we've already computed the result for this coin and target sum, return it directly
        if (memory[coinIdx][targetSum] != -1) {
            return memory[coinIdx][targetSum];
        }

        int pickedVal = (int) Math.pow(10, 9);  // Initialize the picked value to a large number
        if (targetSum >= coinArr[coinIdx]) {
            int leftTarget = targetSum - coinArr[coinIdx];
            pickedVal = 1 + MinimumCoins_UnlimitedCoins_aka_coinChange_Memoization(coinArr, coinIdx, leftTarget, memory);
        }

        int notPickedVal = MinimumCoins_UnlimitedCoins_aka_coinChange_Memoization(coinArr, coinIdx - 1, targetSum, memory);

        // We need the minimum number of coins, so take the minimum of pickedVal and notPickedVal
        int res = Math.min(notPickedVal, pickedVal);
        memory[coinIdx][targetSum] = res;
        return res;
    }

    /**
     * This method calculates the minimum number of coins needed to make a target sum.
     * It uses a dynamic programming approach with tabulation.
     *
     * @param coinArr   Array of available coin denominations.
     * @param targetSum The target sum to be achieved.
     * @return The minimum number of coins needed to achieve the target sum.
     */
    public int MinimumCoins_UnlimitedCoins_aka_coinChange_Tabulation(int[] coinArr, int targetSum) {
        int[][] memory = new int[coinArr.length][targetSum + 1];

        // Initialize memory for the first coin
        // If the coin can make the target, count the number of coins and set it in memory
        // Otherwise, set the value to Integer.MAX_VALUE to be ignored by Math.min
        int firstCoin = coinArr[0];
        for (int ts = 0; ts <= targetSum; ts++) {
            if (ts % firstCoin == 0) {
                int totalCoins = ts / firstCoin;
                memory[0][ts] = totalCoins;
            } else {
                memory[0][ts] = Integer.MAX_VALUE;
            }
        }

        // Start from the second coin in the array
        for (int coinIdx = 1; coinIdx < coinArr.length; coinIdx++) {
            // Try to achieve the target sum from 0 to targetSum
            for (int ts = 0; ts <= targetSum; ts++) {
                int pickedVal = Integer.MAX_VALUE;
                // If the target sum is greater than or equal to the current coin value
                if (ts >= coinArr[coinIdx]) {
                    int leftTarget = ts - coinArr[coinIdx];
                    // We have unlimited coins so we don't reduce the index, rather use the same coin again
                    // As we have included the coin, we add 1 to the value in memory
                    pickedVal = 1 + memory[coinIdx][leftTarget];
                }

                // If we don't pick the coin, we add 0 to the value in memory
                int notPickedVal = memory[coinIdx - 1][ts];

                // We need the minimum number of coins to achieve the target
                memory[coinIdx][ts] = Math.min(notPickedVal, pickedVal);
            }
        }
        return memory[coinArr.length - 1][targetSum];
    }

    /**
     * This method finds the number of ways to achieve a target sum by adding or subtracting elements in an array.
     * It uses a dynamic programming approach with memoization.
     *
     * @param arr    The input array.
     * @param target The target sum to be achieved.
     * @return The number of ways to achieve the target sum.
     */
    public int findTargetSumWays_aka_CountSubsets_equalTo_DIFF_K_Memoization(int[] arr, int target) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        // Edge case: if the total minus the target is less than 0, return 0
        if (total - target < 0) return 0;

        int revisedTotal = total - target;
        int s2 = revisedTotal / 2;

        // If s2 is less than 0 or if the revised total is odd, return 0
        if (s2 < 0 || revisedTotal % 2 == 1) return 0;

        int[][] memory = new int[arr.length][s2 + 1];
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        return CountSubsets_equalTo_Sum_K_Memoization_EdgeCases(arr, arr.length - 1, s2, memory);
    }

    /**
     * This method calculates the number of ways to make a target sum using an array of coin denominations.
     * It uses a dynamic programming approach with memoization.
     *
     * @param arr    Array of available coin denominations.
     * @param target The target sum to be achieved.
     * @return The number of ways to achieve the target sum.
     */
    public int NoOfWays_CoinChange_UnlimitedCoins_Memoization(int[] arr, int target) {
        int[][] memory = new int[arr.length][target + 1];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        return NoOfWays_CoinChange_UnlimitedCoins_Memoization(arr, arr.length - 1, target, memory);
    }


    /**
     * This method calculates the number of ways to make change for a given target amount using an array of coins.
     * The coins can be used unlimited times. This method uses memoization to avoid redundant calculations.
     *
     * @param coinArr The array of coins.
     * @param coinIdx The index of the current coin being considered.
     * @param target  The target amount.
     * @param memory  The 2D array used for memoization.
     * @return The number of ways to make change for the target amount.
     */
    public int NoOfWays_CoinChange_UnlimitedCoins_Memoization(int[] coinArr, int coinIdx, int target, int[][] memory) {
        // Base case: If there are no coins and the target is greater than 0, then no solution exists.
        if (coinIdx == 0) {
            if (target % coinArr[coinIdx] == 0) {
                return 1; // If target is divisible by the coin value, one solution exists.
            }
            return 0; // No way to get target by given coin or target is already achieved.
        }

        // If the subproblem has been solved, return the stored value.
        if (memory[coinIdx][target] != -1) {
            return memory[coinIdx][target];
        }

        // Initialize the number of ways to pick the coin to 0.
        var picked = 0;
        // Check if the coin can be picked (i.e., it is less than or equal to the target).
        if (target >= coinArr[coinIdx]) {
            var leftTarget = target - coinArr[coinIdx];
            picked = NoOfWays_CoinChange_UnlimitedCoins_Memoization(coinArr, coinIdx, leftTarget, memory);
        }

        // Calculate the number of ways if the coin is not picked.
        var notPicked = NoOfWays_CoinChange_UnlimitedCoins_Memoization(coinArr, coinIdx - 1, target, memory);

        // The total number of ways is the sum of the ways when the coin is picked and not picked.
        var res = notPicked + picked;
        memory[coinIdx][target] = res; // Store the result in the memory array for future reference.
        return res;
    }

    /**
     * This method calculates the number of ways to make change for a given target amount using an array of coins.
     * The coins can be used unlimited times. This method uses tabulation (bottom-up approach) for the calculation.
     *
     * @param coinArr The array of coins.
     * @param target  The target amount.
     * @return The number of ways to make change for the target amount.
     */
    public int NoOfWays_CoinChange_UnlimitedCoins_Tabulation(int[] coinArr, int target) {
        var memory = new int[coinArr.length][target + 1];

        // Bootstrap using first coin to achieve target.
        int firstCoin = coinArr[0];
        for (int ts = 0; ts <= target; ts++) {
            // If target achievable by using first coin.
            if (ts % firstCoin == 0) {
                memory[0][ts] = 1; // Set count 1 to that combination.
            }
        }

        // From second coin
        for (int coinIdx = 1; coinIdx < coinArr.length; coinIdx++) {
            // Achieve target from -> final target
            for (int ts = 0; ts <= target; ts++) {
                // Pick
                var picked = 0;
                if (ts >= coinArr[coinIdx]) {
                    var leftTarget = ts - coinArr[coinIdx];
                    picked = memory[coinIdx][leftTarget];
                }

                // Not pick
                var notPicked = memory[coinIdx - 1][ts];

                memory[coinIdx][ts] = picked + notPicked;
            }
        }
        return memory[coinArr.length - 1][target];
    }

    /**
     * This method calculates the maximum value that can be put in a knapsack of capacity WCapacity.
     * Each item in the knapsack has a certain value and weight, and an unlimited number of each item can be used.
     * This method uses memoization to avoid redundant calculations.
     *
     * @param wt        The array of weights of the items.
     * @param val       The array of values of the items.
     * @param WCapacity The capacity of the knapsack.
     * @return The maximum value that can be put in the knapsack.
     */
    public int knapsack_Unlimited_Memoization(int[] wt, int[] val, int WCapacity) {
        int[][] memory = new int[wt.length][WCapacity + 1];

        for (int row[] : memory) {
            Arrays.fill(row, -1);
        }
        return knapsack_Unlimited_Memoization(wt, val, WCapacity, wt.length - 1, memory);
    }


    /**
     * This method solves the unlimited knapsack problem using memoization.
     *
     * @param wtArr     The array of weights of the items.
     * @param valArr    The array of values of the items.
     * @param WCapacity The total capacity of the knapsack.
     * @param wtIdx     The current index in the weight array.
     * @param memory    The memoization table.
     * @return The maximum value that can be obtained.
     */
    int knapsack_Unlimited_Memoization(int[] wtArr, int[] valArr, int WCapacity, int wtIdx, int[][] memory) {
        // Base case: if we're at the last element
        if (wtIdx == 0) {
            // If no capacity left, it turns all in 0
            // If capacity left, then it finds out total item count to be picked up
            var itemCount = WCapacity / wtArr[wtIdx];
            // Based on it, find values can be earned.
            return valArr[wtIdx] * itemCount;
        }

        // If the value is already computed, return it directly from memory for given idx/WCapacity
        if (memory[wtIdx][WCapacity] != -1) {
            return memory[wtIdx][WCapacity];
        }

        // Initialize the picked value as the lowest possible integer value.
        int picked = Integer.MIN_VALUE;

        // Proceed only when knapsack bag has capacity >= current weight at given index.
        if (WCapacity >= wtArr[wtIdx]) {
            int leftWtCapacity = WCapacity - wtArr[wtIdx];
            // Not moving as unlimited count
            picked = valArr[wtIdx] + knapsack_Unlimited_Memoization(wtArr, valArr, leftWtCapacity, wtIdx, memory);
        }

        // Compute the value when the current item is not picked
        int notPicked = knapsack_Unlimited_Memoization(wtArr, valArr, WCapacity, wtIdx - 1, memory);

        // Get the maximum value between picked and not picked
        int finalVal = Math.max(picked, notPicked);
        memory[wtIdx][WCapacity] = finalVal;
        return finalVal;
    }

    /**
     * This method solves the unlimited knapsack problem using tabulation.
     *
     * @param wtArr     The array of weights of the items.
     * @param valArr    The array of values of the items.
     * @param WCapacity The total capacity of the knapsack.
     * @return The maximum value that can be obtained.
     */
    public int knapsack_Unlimited_Tabulation(int[] wtArr, int[] valArr, int WCapacity) {
        int[][] memory = new int[wtArr.length][WCapacity + 1];

        // Bootstrap
        int firstWeight = wtArr[0];
        int valueAtFirstWeightIdx = valArr[0];
        for (int x = firstWeight; x <= WCapacity; x++) {
            var itemCount = x / firstWeight;
            int valueForItemCount = valueAtFirstWeightIdx * itemCount;
            memory[0][x] = valueForItemCount;
        }

        for (int wtIdx = 1; wtIdx < wtArr.length; wtIdx++) {
            for (int cap = 0; cap <= WCapacity; cap++) {
                // Initialize the picked value as the lowest possible integer value.
                int picked = Integer.MIN_VALUE;

                // Proceed only when knapsack bag has capacity >= current weight at given index.
                if (cap >= wtArr[wtIdx]) {
                    int leftWtCapacity = cap - wtArr[wtIdx];
                    picked = valArr[wtIdx] + memory[wtIdx][leftWtCapacity];
                }

                // Compute the value when the current item is not picked
                int notPicked = memory[wtIdx - 1][cap];

                memory[wtIdx][cap] = Math.max(picked, notPicked);
            }
        }
        return memory[wtArr.length - 1][WCapacity];
    }

    /**
     * This method solves the road cutting problem using memoization.
     *
     * @param roadLen The length of the road.
     * @param val     The array of values for each length.
     * @return The maximum value that can be obtained.
     */
    public int RoadCutting_Unlimited_Memoization(int roadLen, int[] val) {
        // Here, Road Len is given and allowed to cut road till its len
        // Each len has $ value.
        // We can consider this as Knapsack limited problem

        // WCapacity = Len N
        // Val[] = Val []
        // Weight[] = prep array for N size with 1, 2 ,3 4.. N i.e. rdArr[]

        int[] rdArr = new int[roadLen];
        for (int i = 0; i < rdArr.length; i++) {
            rdArr[i] = i + 1;
        }

        int[][] memory = new int[rdArr.length][roadLen + 1];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }
        return knapsack_01_limited_Memoization(rdArr, val, roadLen, rdArr.length - 1, memory);
    }


    public void printSubSequenceString(int idx, int[] inputList, List<Integer> resList) {
        if (idx == inputList.length) {
            for (int i : resList) {
                System.out.println(i);
            }
            System.out.printf("---Sub Sequence----");
            System.out.println('\n');
            return;
        }

        resList.add(inputList[idx]);
        printSubSequenceString(idx + 1, inputList, resList);

        resList.remove(resList.size() - 1);
        printSubSequenceString(idx + 1, inputList, resList);
    }

    /**
     * This method finds the length of the longest common subsequence of two strings using memoization.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the length of the longest common subsequence
     * <p>
     * Example:
     * Input: s1 = "abcde", s2 = "ace"
     * Output: 3
     */
    public int longest_Common_SubSequence_Memoization(String s1, String s2) {
        int[][] memory = new int[s1.length()][s2.length()];

        for (int rows[] : memory) {
            Arrays.fill(rows, -1);
        }

        return longest_Common_SubSequence_Memoization(s1, s2, s1.length() - 1, s2.length() - 1, memory);
    }

    /**
     * This helper method finds the length of the longest common subsequence of two strings using memoization.
     *
     * @param s1     the first string
     * @param s2     the second string
     * @param s1Idx  the current index in the first string
     * @param s2Idx  the current index in the second string
     * @param memory the array used for memoization
     * @return the length of the longest common subsequence
     */
    public int longest_Common_SubSequence_Memoization(String s1, String s2, int s1Idx, int s2Idx, int[][] memory) {
        if (s1Idx < 0 || s2Idx < 0) {
            return 0;
        }

        if (memory[s1Idx][s2Idx] != -1) {
            return memory[s1Idx][s2Idx];
        }

        if (s1.charAt(s1Idx) == s2.charAt(s2Idx)) {
            int res = 1 + longest_Common_SubSequence_Memoization(s1, s2, s1Idx - 1, s2Idx - 1, memory);
            memory[s1Idx][s2Idx] = res;
            return res;
        } else {
            int left = longest_Common_SubSequence_Memoization(s1, s2, s1Idx - 1, s2Idx, memory);
            int right = longest_Common_SubSequence_Memoization(s1, s2, s1Idx, s2Idx - 1, memory);
            int res = Math.max(left, right);
            memory[s1Idx][s2Idx] = res;
            return res;
        }
    }

    /**
     * This method finds the length of the longest common subsequence of two strings using tabulation.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the length of the longest common subsequence
     * <p>
     * Example:
     * Input: s1 = "abcde", s2 = "ace"
     * Output: 3
     */
    public int longest_Common_SubSequence_Tabulation(String s1, String s2) {
        int[][] memory = new int[s1.length() + 1][s2.length() + 1];

        for (int rows[] : memory) {
            Arrays.fill(rows, -1);
        }

        for (int i = 0; i <= s1.length(); i++) {
            memory[i][0] = 0;
        }
        for (int j = 0; j <= s2.length(); j++) {
            memory[0][j] = 0;
        }

        for (int s1Idx = 1; s1Idx <= s1.length(); s1Idx++) {
            for (int s2Idx = 1; s2Idx <= s2.length(); s2Idx++) {
                if (s1.charAt(s1Idx - 1) == s2.charAt(s2Idx - 1)) {
                    memory[s1Idx][s2Idx] = 1 + memory[s1Idx - 1][s2Idx - 1];
                } else {
                    memory[s1Idx][s2Idx] = Math.max(memory[s1Idx - 1][s2Idx], memory[s1Idx][s2Idx - 1]);
                }
            }
        }
        return memory[s1.length()][s2.length()];
    }

    /**
     * This method prints the longest common subsequence of two strings.
     *
     * @param s1 the first string
     * @param s2 the second string
     *           <p>
     *           Example:
     *           Input: s1 = "abcde", s2 = "ace"
     *           Output: Prints "ace"
     */
    public void print_longest_Common_SubSequence_Tabulation(String s1, String s2) {
        int[][] memory = new int[s1.length() + 1][s2.length() + 1];

        for (int rows[] : memory) {
            Arrays.fill(rows, -1);
        }

        for (int i = 0; i <= s1.length(); i++) {
            memory[i][0] = 0;
        }
        for (int j = 0; j <= s2.length(); j++) {
            memory[0][j] = 0;
        }

        for (int s1Idx = 1; s1Idx <= s1.length(); s1Idx++) {
            for (int s2Idx = 1; s2Idx <= s2.length(); s2Idx++) {
                if (s1.charAt(s1Idx - 1) == s2.charAt(s2Idx - 1)) {
                    memory[s1Idx][s2Idx] = 1 + memory[s1Idx - 1][s2Idx - 1];
                } else {
                    memory[s1Idx][s2Idx] = Math.max(memory[s1Idx - 1][s2Idx], memory[s1Idx][s2Idx - 1]);
                }
            }
        }

        int lcsLen = memory[s1.length()][s2.length()];
        int resIdx = lcsLen - 1;
        String resString = "";

        for (int x = 0; x < lcsLen; x++) {
            resString += "#";
        }

        StringBuilder res = new StringBuilder(resString);

        var s1Idx = s1.length();
        var s2Idx = s2.length();

        while (s1Idx > 0 && s2Idx > 0) {
            if (s1.charAt(s1Idx - 1) == s2.charAt(s2Idx - 1)) {
                res.setCharAt(resIdx, s1.charAt(s1Idx - 1));
                s1Idx--;
                s2Idx--;
                resIdx--;
            } else if (s1.charAt(s1Idx - 1) > s2.charAt(s2Idx - 1)) {
                s1Idx--;
            } else {
                s2Idx--;
            }
        }

        System.out.println("print_longest_Common_SubSequence_Tabulation  " + res);
    }

    /**
     * This method finds the length of the longest common substring of two strings using tabulation.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the length of the longest common substring
     * <p>
     * Example:
     * Input: s1 = "abcde", s2 = "ace"
     * Output: 2
     */
    public int longest_Common_SubString_Length_Tabulation(String s1, String s2) {
        int[][] memory = new int[s1.length() + 1][s2.length() + 1];

        for (int rows[] : memory) {
            Arrays.fill(rows, -1);
        }

        for (int i = 0; i <= s1.length(); i++) {
            memory[i][0] = 0;
        }
        for (int j = 0; j <= s2.length(); j++) {
            memory[0][j] = 0;
        }

        int maxLen = -1;

        for (int s1Idx = 1; s1Idx <= s1.length(); s1Idx++) {
            for (int s2Idx = 1; s2Idx <= s2.length(); s2Idx++) {
                if (s1.charAt(s1Idx - 1) == s2.charAt(s2Idx - 1)) {
                    int len = 1 + memory[s1Idx - 1][s2Idx - 1];
                    memory[s1Idx][s2Idx] = len;
                    maxLen = Math.max(maxLen, len);
                } else {
                    memory[s1Idx][s2Idx] = 0;
                }
            }
        }

        return maxLen;
    }

    /**
     * This method calculates the length of the longest palindromic subsequence in a string.
     * A palindromic string is a string that remains the same when its characters are reversed.
     * The method uses tabulation to optimize the calculation.
     *
     * @param s1 The input string.
     * @return The length of the longest palindromic subsequence.
     */
    public int longest_Palindromic_SubSequence_Tabulation(String s1) {
        String s2 = new StringBuilder(s1).reverse().toString();
        return longest_Common_SubSequence_Tabulation(s1, s2);
    }

    /**
     * This method calculates the minimum number of insertions required to make a string palindrome.
     * A palindrome is a word, phrase, number, or other sequence of characters that reads the same forward and backward.
     * The method uses tabulation to optimize the calculation.
     *
     * @param s1 The input string.
     * @return The minimum number of insertions required to make the string palindrome.
     */
    public int min_Insertions_make_string_Palindrome_Tabulation(String s1) {
        int lcsOfPalindrome = longest_Palindromic_SubSequence_Tabulation(s1);
        int minInsertionNeed = s1.length() - lcsOfPalindrome;
        return minInsertionNeed;
    }

    /**
     * This method calculates the minimum number of insertions and deletions required to transform one string into another.
     * The method uses tabulation to optimize the calculation.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The minimum number of insertions and deletions required to transform the first string into the second string.
     */
    public int min_InsertDelete_aka_min_Distance_Tabulation(String s1, String s2) {
        int lcs = longest_Common_SubSequence_Tabulation(s1, s2);
        int res = s1.length() - lcs + s2.length() - lcs;
        return res;
    }

    /**
     * This method finds the shortest supersequence of two strings.
     * A supersequence of two strings is a string that contains both strings as subsequences.
     * The method uses tabulation to optimize the calculation.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The shortest supersequence of the two strings.
     */
    public String shortest_Common_Supersequence_Tabulation(String s1, String s2) {
        int[][] memory = new int[s1.length() + 1][s2.length() + 1];

        for (int rows[] : memory) {
            Arrays.fill(rows, -1);
        }

        for (int i = 0; i <= s1.length(); i++) {
            memory[i][0] = 0;
        }
        for (int j = 0; j <= s2.length(); j++) {
            memory[0][j] = 0;
        }

        for (int s1Idx = 1; s1Idx <= s1.length(); s1Idx++) {
            for (int s2Idx = 1; s2Idx <= s2.length(); s2Idx++) {
                if (s1.charAt(s1Idx - 1) == s2.charAt(s2Idx - 1)) {
                    memory[s1Idx][s2Idx] = 1 + memory[s1Idx - 1][s2Idx - 1];
                } else {
                    memory[s1Idx][s2Idx] = 0 + Math.max(memory[s1Idx - 1][s2Idx], memory[s1Idx][s2Idx - 1]);
                }
            }
        }

        int lcsLen = memory[s1.length()][s2.length()];
        int totalLen = s1.length() + s2.length();
        int superSeqLen = totalLen - lcsLen;

        int resIdx = superSeqLen - 1;
        String resString = "";

        for (int x = 0; x < superSeqLen; x++) {
            resString += "#";
        }

        StringBuilder res = new StringBuilder(resString);

        var s1Idx = s1.length();
        var s2Idx = s2.length();

        while (s1Idx > 0 && s2Idx > 0) {
            if (s1.charAt(s1Idx - 1) == s2.charAt(s2Idx - 1)) {
                res.setCharAt(resIdx, s1.charAt(s1Idx - 1));
                s1Idx--;
                s2Idx--;
                resIdx--;
            } else if (memory[s1Idx - 1][s2Idx] > memory[s1Idx][s2Idx - 1]) {
                res.setCharAt(resIdx, s1.charAt(s1Idx - 1));
                s1Idx--;
                resIdx--;
            } else {
                res.setCharAt(resIdx, s2.charAt(s2Idx - 1));
                s2Idx--;
                resIdx--;
            }
        }

        while (s1Idx > 0) {
            res.setCharAt(resIdx, s1.charAt(s1Idx - 1));
            s1Idx--;
            resIdx--;
        }

        while (s2Idx > 0) {
            res.setCharAt(resIdx, s2.charAt(s2Idx - 1));
            s2Idx--;
            resIdx--;
        }

        return res.toString();
    }

    /**
     * This method calculates the number of distinct subsequences of a string that equal another string.
     * It uses memoization to optimize the calculation.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The number of distinct subsequences of the first string that equal the second string.
     */
    public int DistinctSubsequences_Memoization(String s1, String s2) {
        int memory[][] = new int[s1.length()][s2.length()];

        for (int rows[] : memory) {
            Arrays.fill(rows, -1);
        }
        return DistinctSubsequences_Memoization(s1, s2, s1.length() - 1, s2.length() - 1, memory);
    }

    /**
     * This method counts the number of distinct subsequences of s1 that equal s2.
     * It uses memoization to store intermediate results for efficiency.
     *
     * @param s1     The first string.
     * @param s2     The second string.
     * @param s1Idx  The current index in s1.
     * @param s2Idx  The current index in s2.
     * @param memory The 2D array used for memoization.
     * @return The number of distinct subsequences of s1 that equal s2.
     */
    public int DistinctSubsequences_Memoization(String s1, String s2, int s1Idx, int s2Idx, int[][] memory) {
        // If s2 is empty, there is one match (the empty string).
        if (s2Idx < 0) {
            return 1;
        }

        // If s1 is empty but s2 is not, there are no matches.
        if (s1Idx < 0) {
            return 0;
        }

        // If the result has been computed before, return it directly from memory.
        if (memory[s1Idx][s2Idx] != -1) {
            return memory[s1Idx][s2Idx];
        }

        // If the current characters in s1 and s2 match.
        if (s1.charAt(s1Idx) == s2.charAt(s2Idx)) {
            // Count the number of distinct subsequences by considering two cases:
            // 1. The current character in s1 is included in the subsequences.
            // 2. The current character in s1 is not included in the subsequences.
            int matchedBoth = DistinctSubsequences_Memoization(s1, s2, s1Idx - 1, s2Idx - 1, memory);
            int matchMoreOnS1 = DistinctSubsequences_Memoization(s1, s2, s1Idx - 1, s2Idx, memory);

            int res = matchedBoth + matchMoreOnS1;
            memory[s1Idx][s2Idx] = res;
            return res;
        } else {
            // If the current characters in s1 and s2 do not match, ignore the current character in s1.
            int matchMoreOnS1 = DistinctSubsequences_Memoization(s1, s2, s1Idx - 1, s2Idx, memory);
            memory[s1Idx][s2Idx] = matchMoreOnS1;
            return matchMoreOnS1;
        }
    }

    /**
     * This method calculates the minimum number of operations required to convert s1 to s2.
     * The operations allowed are insert, delete, and replace.
     * It uses memoization to store intermediate results for efficiency.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The minimum number of operations required to convert s1 to s2.
     */
    public int editDistance_AKA_minDistance_Memoization(String s1, String s2) {
        int[][] memory = new int[s1.length()][s2.length()];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        return editDistance_AKA_minDistance_Memoization(s1, s2, s1.length() - 1, s2.length() - 1, memory);
    }

    private int editDistance_AKA_minDistance_Memoization(String s1, String s2, int s1Idx, int s2Idx, int[][] memory) {
        // If s1 is empty, all characters of s2 need to be inserted into s1.
        if (s1Idx < 0) {
            return s2Idx + 1;
        }

        // If s2 is empty, all characters of s1 need to be deleted.
        if (s2Idx < 0) {
            return s1Idx + 1;
        }

        // If the result has been computed before, return it directly from memory.
        if (memory[s1Idx][s2Idx] != -1) {
            return memory[s1Idx][s2Idx];
        }

        // If the current characters in s1 and s2 match, move to the next characters.
        if (s1.charAt(s1Idx) == s2.charAt(s2Idx)) {
            int res = 0 + editDistance_AKA_minDistance_Memoization(s1, s2, s1Idx - 1, s2Idx - 1, memory);
            memory[s1Idx][s2Idx] = res;
            return res;
        } else {
            // If the current characters in s1 and s2 do not match, consider three cases:
            // 1. Insert the current character from s2 into s1.
            // 2. Delete the current character from s1.
            // 3. Replace the current character in s1 with the current character in s2.
            int insert = editDistance_AKA_minDistance_Memoization(s1, s2, s1Idx, s2Idx - 1, memory);
            int delete = editDistance_AKA_minDistance_Memoization(s1, s2, s1Idx - 1, s2Idx, memory);
            int replace = editDistance_AKA_minDistance_Memoization(s1, s2, s1Idx - 1, s2Idx - 1, memory);

            int minOfAll = Math.min(Math.min(insert, delete), replace);

            int res = 1 + minOfAll; // Add 1 for the current operation.
            memory[s1Idx][s2Idx] = res;
            return res;
        }
    }

    /**
     * This method checks if the input string can be matched by the pattern.
     * The pattern can include normal characters and two special characters:
     * '.' matches any single character.
     * '*' matches zero or more of the preceding element.
     *
     * @param pattern     The pattern string.
     * @param inputString The input string.
     * @return true if the input string can be matched by the pattern, false otherwise.
     */
    public boolean wildcard_matching_Memoization(String pattern, String inputString) {
        int[][] memory = new int[pattern.length()][inputString.length()];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        return wildcard_matching_Memoization(pattern, inputString, pattern.length() - 1, inputString.length() - 1, memory) == 1;
    }

    private int wildcard_matching_Memoization(String pat, String str, int patIdx, int strIdx, int[][] memory) {
        // If both strings are empty, they match.
        if (patIdx < 0 && strIdx < 0) {
            return 1;
        }

        // If the pattern is empty but the string is not, they do not match.
        if (patIdx < 0 && strIdx >= 0) {
            return 0;
        }

        // If the string is empty, the pattern must be all '*' to match.
        if (patIdx >= 0 && strIdx < 0) {
            for (int pt = 0; pt <= patIdx; pt++) {
                if (pat.charAt(pt) != '*') {
                    return 0;
                }
            }
            return 1;
        }

        // If the result has been computed before, return it directly from memory.
        if (memory[patIdx][strIdx] != -1) {
            return memory[patIdx][strIdx];
        }

        // If the current characters in the pattern and the string match, or the current character in the pattern is '?',
        // move to the next characters.
        if (pat.charAt(patIdx) == str.charAt(strIdx) || pat.charAt(patIdx) == '?') {
            memory[patIdx][strIdx] = wildcard_matching_Memoization(pat, str, patIdx - 1, strIdx - 1, memory);
            return memory[patIdx][strIdx];
        } else {
            // If the current characters do not match.
            if (pat.charAt(patIdx) == '*') {
                // If the current character in the pattern is '*', consider two cases:
                // 1. The '*' matches the current character in the string.
                // 2. The '*' matches no characters in the string.
                int singleMatch = wildcard_matching_Memoization(pat, str, patIdx - 1, strIdx, memory);
                int allMatch = wildcard_matching_Memoization(pat, str, patIdx, strIdx - 1, memory);

                memory[patIdx][strIdx] = singleMatch == 1 || allMatch == 1 ? 1 : 0;
                return memory[patIdx][strIdx];
            } else {
                // If the current character in the pattern is not '*' and does not match the current character in the string,
                // the strings do not match.
                memory[patIdx][strIdx] = 0;
                return 0;
            }
        }
    }


    /**
     * This method checks if a given string matches a pattern that includes wildcards.
     * It uses a tabulation (bottom-up) approach.
     *
     * @param pat The pattern string, which may include '?' (matches any single character) and '*' (matches any sequence of characters).
     * @param str The string to be matched.
     * @return True if the string matches the pattern, false otherwise.
     * <p>
     * Example:
     * Suppose the pattern is "a*b" and the string is "aaab".
     * The method will return true because the string matches the pattern.
     */
    public boolean wildcard_matching_Tabulation(String pat, String str) {
        boolean[][] memory = new boolean[pat.length() + 1][str.length() + 1];

        memory[0][0] = true;

        for (int j = 1; j <= str.length(); j++) {
            memory[0][j] = false;
        }

        for (int pt = 1; pt <= pat.length(); pt++) {
            boolean allStars = true;
            for (int x = 1; x <= pt; x++) {
                if (pat.charAt(x - 1) != '*') {
                    allStars = false;
                    break;
                }
            }
            memory[pt][0] = allStars;
        }

        for (int ptIdx = 1; ptIdx <= pat.length(); ptIdx++) {
            for (int strIdx = 1; strIdx <= str.length(); strIdx++) {
                if (pat.charAt(ptIdx - 1) == str.charAt(strIdx - 1) || pat.charAt(ptIdx - 1) == '?') {
                    memory[ptIdx][strIdx] = memory[ptIdx - 1][strIdx - 1];
                } else {
                    if (pat.charAt(ptIdx - 1) == '*') {
                        boolean singleMatch = memory[ptIdx - 1][strIdx];
                        boolean allMatch = memory[ptIdx][strIdx - 1];
                        memory[ptIdx][strIdx] = singleMatch || allMatch;
                    } else {
                        memory[ptIdx][strIdx] = false;
                    }
                }
            }
        }
        return memory[pat.length()][str.length()];
    }

    /**
     * This method finds the length of the longest increasing subsequence in a given array.
     * It uses memoization (top-down approach).
     *
     * @param arr The input array.
     * @return The length of the longest increasing subsequence.
     * <p>
     * Example:
     * Suppose the input array is [10, 9, 2, 5, 3, 7, 101, 18].
     * The length of the longest increasing subsequence is 4 (the subsequence is [2, 5, 7, 101]).
     */
    public int longest_Increasing_Subsequence_AKA_lengthOfLIS_Memoization(int[] arr) {
        int[][] memory = new int[arr.length][arr.length + 1];

        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }
        return longest_Increasing_Subsequence_AKA_lengthOfLIS_Memoization(arr, 0, -1, memory);
    }

    private int longest_Increasing_Subsequence_AKA_lengthOfLIS_Memoization(int[] arr, int curIdx, int prevIdx, int[][] memory) {
        if (arr.length == curIdx) {
            return 0;
        }

        if (memory[curIdx][prevIdx + 1] != -1) {
            return memory[curIdx][prevIdx + 1];
        }

        int take = 0;
        if (prevIdx == -1 || arr[curIdx] > arr[prevIdx]) {
            take = 1 + longest_Increasing_Subsequence_AKA_lengthOfLIS_Memoization(arr, curIdx + 1, curIdx, memory);
        }

        int notTake = longest_Increasing_Subsequence_AKA_lengthOfLIS_Memoization(arr, curIdx + 1, prevIdx, memory);

        memory[curIdx][prevIdx + 1] = Math.max(notTake, take);

        return memory[curIdx][prevIdx + 1];
    }

    /**
     * This method finds the length of the longest increasing subsequence in a given array.
     * It uses tabulation (bottom-up approach) and assumes that the array is weakly increasing.
     *
     * @param arr The input array.
     * @return The length of the longest increasing subsequence.
     * <p>
     * Example:
     * Suppose the input array is [10, 9, 2, 5, 3, 7, 101, 18].
     * The length of the longest increasing subsequence is 4 (the subsequence is [2, 5, 7, 101]).
     */
    int longest_Increasing_Subsequence_AKA_lengthOfLIS_Tabulation_Weak(int[] arr) {
        int[][] memory = new int[arr.length + 1][arr.length + 1];

        for (int[] rw : memory) {
            Arrays.fill(rw, -1);
        }

        for (int c = 0; c <= arr.length; c++) {
            memory[arr.length][c] = 0;
        }

        for (int curIdx = arr.length - 1; curIdx >= 0; curIdx--) {
            for (int prevIdx = curIdx - 1; prevIdx >= -1; prevIdx--) {
                int take = 0;
                if (prevIdx == -1 || arr[curIdx] > arr[prevIdx]) {
                    take = 1 + memory[curIdx + 1][curIdx + 1];
                }

                int notTake = memory[curIdx + 1][prevIdx + 1];

                memory[curIdx][prevIdx + 1] = Math.max(notTake, take);
            }
        }

        return memory[0][-1 + 1];
    }

    /**
     * This method finds the length of the longest increasing subsequence in a given array.
     * It uses tabulation (bottom-up approach) and assumes that the array is strongly increasing.
     *
     * @param arr The input array.
     * @return The length of the longest increasing subsequence.
     * <p>
     * Example:
     * Suppose the input array is [10, 9, 2, 5, 3, 7, 101, 18].
     * The length of the longest increasing subsequence is 4 (the subsequence is [2, 5, 7, 101]).
     */
    int longest_Increasing_Subsequence_AKA_lengthOfLIS_Tabulation_Strong(int[] arr) {
        int[] memory = new int[arr.length];

        Arrays.fill(memory, 1);

        for (int curIdx = 0; curIdx < arr.length; curIdx++) {
            for (int prevIdx = 0; prevIdx < curIdx; prevIdx++) {
                if (arr[prevIdx] < arr[curIdx]) {
                    int lcsAtPrevIdx = memory[prevIdx] + 1;
                    int lcsAtCurrIdx = memory[curIdx];
                    memory[curIdx] = Math.max(lcsAtPrevIdx, lcsAtCurrIdx);
                }
            }
        }

        int maxlcs = -1;
        for (int x = 0; x < memory.length; x++) {
            maxlcs = Math.max(maxlcs, memory[x]);
        }
        return maxlcs;
    }


    /**
     * This method prints the longest increasing subsequence in an array.
     * It uses a dynamic programming approach with strong tabulation.
     *
     * @param arr The input array.
     */
    public void Print_longest_Increasing_Subsequence_AKA_lengthOfLIS_Tabulation_Strong(int[] arr) {
        int[] memory = new int[arr.length];
        Arrays.fill(memory, 1);

        int[] pointerMemory = new int[arr.length];
        Arrays.fill(pointerMemory, 1);

        for (int curIdx = 0; curIdx < arr.length; curIdx++) {
            pointerMemory[curIdx] = curIdx;

            for (int prevIdx = 0; prevIdx < curIdx; prevIdx++) {
                if (arr[prevIdx] < arr[curIdx]) {
                    int lcsAtPrevIdx = memory[prevIdx] + 1;
                    int lcsAtCurrIdx = memory[curIdx];
                    if (lcsAtPrevIdx > lcsAtCurrIdx) {
                        memory[curIdx] = lcsAtPrevIdx;
                        pointerMemory[curIdx] = prevIdx;
                    }
                }
            }
        }

        int maxLIS = -1;
        int maxLisIndex = -1;
        for (int x = 0; x < memory.length; x++) {
            if (memory[x] > maxLIS) {
                maxLIS = memory[x];
                maxLisIndex = x;
            }
        }

        var res = new ArrayList<Integer>();
        res.add(arr[maxLisIndex]);

        while (maxLisIndex != pointerMemory[maxLisIndex]) {
            maxLisIndex = pointerMemory[maxLisIndex];
            res.add(arr[maxLisIndex]);
        }

        var resSorted = res.stream().sorted((a, b) -> a - b).collect(Collectors.toList());
        printList(resSorted);
    }

    /**
     * This method finds the largest divisible subset in an array.
     * It uses a dynamic programming approach with tabulation.
     *
     * @param arr The input array.
     * @return The largest divisible subset in the array.
     */
    public List<Integer> largest_Divisible_Subset_Tabulation(int[] arr) {
        Arrays.sort(arr);

        int[] memory = new int[arr.length];
        Arrays.fill(memory, 1);

        int[] pointerMemory = new int[arr.length];

        for (int curIdx = 0; curIdx < arr.length; curIdx++) {
            pointerMemory[curIdx] = curIdx;

            for (int prevIdx = 0; prevIdx < curIdx; prevIdx++) {
                if (arr[curIdx] % arr[prevIdx] == 0) {
                    int lcsAtPrevIdx = memory[prevIdx] + 1;
                    int lcsAtCurrIdx = memory[curIdx];
                    if (lcsAtPrevIdx > lcsAtCurrIdx) {
                        memory[curIdx] = lcsAtPrevIdx;
                        pointerMemory[curIdx] = prevIdx;
                    }
                }
            }
        }

        int maxLIS = -1;
        int maxLisIndex = -1;
        for (int x = 0; x < memory.length; x++) {
            if (memory[x] > maxLIS) {
                maxLIS = memory[x];
                maxLisIndex = x;
            }
        }

        var res = new ArrayList<Integer>();
        res.add(arr[maxLisIndex]);

        while (maxLisIndex != pointerMemory[maxLisIndex]) {
            maxLisIndex = pointerMemory[maxLisIndex];
            res.add(arr[maxLisIndex]);
        }

        return res.stream().sorted((a, b) -> a - b).collect(Collectors.toList());
    }

    /**
     * This method finds the longest string chain in an array of words.
     * It uses a dynamic programming approach with tabulation.
     *
     * @param words The input array of words.
     * @return The length of the longest string chain.
     */
    public int longest_String_Chain_Tabulation(String[] words) {
        Arrays.sort(words, ((a, b) -> a.length() - b.length()));

        int[] memory = new int[words.length];
        Arrays.fill(memory, 1);

        for (int curIdx = 0; curIdx < words.length; curIdx++) {
            for (int prevIdx = 0; prevIdx < curIdx; prevIdx++) {
                if (compareWords(words[curIdx], words[prevIdx])) {
                    int lcsAtPrevIdx = memory[prevIdx] + 1;
                    int lcsAtCurrIdx = memory[curIdx];
                    if (lcsAtPrevIdx > lcsAtCurrIdx) {
                        memory[curIdx] = lcsAtPrevIdx;
                    }
                }
            }
        }

        int maxLIS = -1;
        for (int x = 0; x < memory.length; x++) {
            if (memory[x] > maxLIS) {
                maxLIS = memory[x];
            }
        }

        return maxLIS;
    }

    /**
     * This method compares two strings to check if they are one character apart.
     * It checks if the length of the first string is one more than the second string,
     * and if the characters in the same positions are the same.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return True if the strings are one character apart, false otherwise.
     */
    private boolean compareWords(String s1, String s2) {
        // The length of the first string must be one more than the length of the second string.
        if (s1.length() != s2.length() + 1) {
            return false;
        }

        int p1 = 0; // Pointer for the first string.
        int p2 = 0; // Pointer for the second string.

        // Iterate through the characters in the first string.
        while (p1 < s1.length()) {
            // If the characters at the current positions are the same, move both pointers forward.
            if (p2 < s2.length() && s1.charAt(p1) == s2.charAt(p2)) {
                p1++;
                p2++;
            } else {
                // If the characters are not the same, only move the pointer for the first string forward.
                p1++;
            }
        }

        // Both pointers must have reached the end of their respective strings.
        return p1 == s1.length() && p2 == s2.length();
    }

    /**
     * This method finds the number of longest increasing subsequences (LIS) in an array.
     * It uses tabulation (bottom-up approach) for the calculation.
     *
     * @param arr The input array.
     * @return The number of longest increasing subsequences in the array.
     */
    public int find_Number_Of_LIS_Tabulation(int[] arr) {
        // Initialize the memory array for storing the lengths of the LIS ending at each index.
        var memory = new int[arr.length];
        Arrays.fill(memory, 1);

        // Initialize the counter array for storing the counts of the LIS ending at each index.
        var counter = new int[arr.length];
        Arrays.fill(counter, 1);

        // Iterate over the array from left to right.
        for (var curIdx = 0; curIdx < arr.length; curIdx++) {
            // For each element, iterate over all the previous elements.
            for (var prevIdx = 0; prevIdx <= curIdx - 1; prevIdx++) {
                // If the current element is greater than the previous element, update the memory and counter arrays.
                if (arr[prevIdx] < arr[curIdx]) {
                    int lcsAtPrevIdx = memory[prevIdx] + 1; // The length of the LIS ending at the previous index plus one.
                    int lcsAtCurrIdx = memory[curIdx]; // The length of the LIS ending at the current index.

                    // If the new length is greater than the current length, update the memory and counter arrays.
                    if (lcsAtPrevIdx > lcsAtCurrIdx) {
                        memory[curIdx] = lcsAtPrevIdx;
                        counter[curIdx] = counter[prevIdx];
                    }
                    // If the new length is equal to the current length, add the count of the LIS ending at the previous index to the count of the LIS ending at the current index.
                    else if (lcsAtPrevIdx == lcsAtCurrIdx) {
                        counter[curIdx] = counter[curIdx] + counter[prevIdx];
                    }
                }
            }
        }

        // Find the maximum length of the LIS.
        int maxLIS = -1;
        for (int x = 0; x < memory.length; x++) {
            if (memory[x] > maxLIS) {
                maxLIS = memory[x];
            }
        }

        // Find the total number of LIS of maximum length.
        int totalLIS = 0;
        for (int i = 0; i <= arr.length - 1; i++) {
            // If the length of the LIS ending at the current index is equal to the maximum length, add its count to the total count.
            if (maxLIS == memory[i]) {
                totalLIS = totalLIS + counter[i];
            }
        }

        return totalLIS;
    }

    /**
     * This method calculates the maximum profit that can be made from buying and selling stocks.
     *
     * @param priceArr The array of stock prices.
     * @return The maximum profit that can be made.
     */
    public int StockBuyNSell_aka_maxProfit(int[] priceArr) {
        var maxProfit = 0;
        var minPrice = Integer.MAX_VALUE;

        for (int p = 0; p < priceArr.length; p++) {
            // Buy if the price is lower
            if (priceArr[p] < minPrice) {
                minPrice = priceArr[p];
            }
            // Sell if it's profitable
            else {
                int potentialProfit = priceArr[p] - minPrice;
                if (potentialProfit > maxProfit) {
                    maxProfit = potentialProfit;
                }
            }
        }

        return maxProfit;
    }

    /**
     * This method calculates the maximum profit that can be made from buying and selling stocks.
     * It does not use dynamic programming.
     *
     * @param prices The array of stock prices.
     * @return The maximum profit that can be made.
     */
    public int StockBuyNSell_2_aka_maxProfit_NON_DP(int[] prices) {
        if (prices.length <= 0) return 0;
        int profit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            // If the price of the next day is higher, sell the stock
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }

        return profit;
    }

    /**
     * This method calculates the maximum profit that can be made from buying and selling stocks.
     * It uses memoization to optimize the calculation.
     *
     * @param priceArr The array of stock prices.
     * @return The maximum profit that can be made.
     */
    public int StockBuyNSell_2_aka_maxProfit_Memoization(int[] priceArr) {
        var memory = new int[priceArr.length][2]; // 2 because either buy or sell i.e two
        for (int[] rows : memory) {
            Arrays.fill(rows, -1);
        }
        // Start with 0 as need to buy first.
        return StockBuyNSell_2_aka_maxProfit_Memoization(priceArr, 0, 0, memory);
    }

    /**
     * This method calculates the maximum profit that can be made from buying and selling stocks.
     * It uses memoization to optimize the calculation.
     *
     * @param priceArr The array of stock prices.
     * @param pIdx     The current index in the price array.
     * @param canBuy   A flag indicating whether we can buy a stock.
     * @param memory   The memoization table.
     * @return The maximum profit that can be made.
     */
    public int StockBuyNSell_2_aka_maxProfit_Memoization(int[] priceArr, int pIdx, int canBuy, int[][] memory) {
        // Base case: all done.
        if (pIdx == priceArr.length) {
            return 0;
        }

        // If the value is already computed, return it directly from memory.
        if (memory[pIdx][canBuy] != -1) {
            return memory[pIdx][canBuy];
        }

        var profit = 0;

        // Buy or do nothing
        if (canBuy == 0) {
            int spent = priceArr[pIdx];
            var bought = -spent + StockBuyNSell_2_aka_maxProfit_Memoization(priceArr, pIdx + 1, 1, memory);
            var skip = StockBuyNSell_2_aka_maxProfit_Memoization(priceArr, pIdx + 1, canBuy, memory);
            profit = Math.max(bought, skip);
        }

        // Sell or do nothing
        if (canBuy == 1) {
            var earned = priceArr[pIdx];
            var sold = earned + StockBuyNSell_2_aka_maxProfit_Memoization(priceArr, pIdx + 1, 0, memory);
            var skip = StockBuyNSell_2_aka_maxProfit_Memoization(priceArr, pIdx + 1, canBuy, memory);
            profit = Math.max(sold, skip);
        }

        memory[pIdx][canBuy] = profit;
        return profit;
    }

    /**
     * This method calculates the maximum profit that can be made from buying and selling stocks.
     * It uses tabulation to optimize the calculation.
     *
     * @param priceArr The array of stock prices.
     * @return The maximum profit that can be made.
     */
    public int StockBuyNSell_2_aka_maxProfit_Tabulation(int[] priceArr) {
        // 2 because either buy or sell
        // len+1 because we need to reach till len, recall base condition.
        var memory = new int[priceArr.length + 1][2];
        for (int[] rows : memory) {
            Arrays.fill(rows, -1);
        }

        // Base value setup
        // Both 0 when buy or sell at len = max.
        memory[priceArr.length][0] = 0;
        memory[priceArr.length][1] = 0;

        var profit = 0;

        // Each day price
        for (int pIdx = priceArr.length - 1; pIdx >= 0; pIdx--) {
            // Try both buy or sell
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                // Buy or do nothing
                if (canBuy == 0) {
                    int spent = priceArr[pIdx];
                    var bought = -spent + memory[pIdx + 1][1];
                    var skip = memory[pIdx + 1][canBuy];
                    profit = Math.max(bought, skip);
                }
                // Sell or do nothing
                if (canBuy == 1) {
                    var earned = priceArr[pIdx];
                    var sold = earned + memory[pIdx + 1][0];
                    var skip = memory[pIdx + 1][canBuy];
                    profit = Math.max(sold, skip);
                }

                memory[pIdx][canBuy] = profit;
            }
        }
        return memory[0][0];
    }


    /**
     * This method calculates the maximum profit that can be achieved from performing two transactions on a given stock price array.
     * It uses a dynamic programming approach with memoization.
     *
     * @param priceArr The input array representing the stock prices.
     * @return The maximum profit that can be achieved.
     */
    public int StockBuyNSell_3_TwoTransaction_aka_maxProfit_Memoization(int[] priceArr) {
        int maxTrans = 2;
        int[][][] memory = new int[priceArr.length][2][maxTrans + 1];
        for (int[][] rows : memory) {
            for (int[] innerRow : rows) {
                Arrays.fill(innerRow, -1);
            }
        }
        return StockBuyNSell_3_TwoTransaction_aka_maxProfit_Memoization(priceArr, 0, 0, 2, memory);
    }

    /**
     * This method calculates the maximum profit that can be achieved from performing two transactions on a given stock price array.
     * It uses a dynamic programming approach with memoization.
     *
     * @param priceArr The input array representing the stock prices.
     * @param pIdx     The current price index.
     * @param canBuy   A flag indicating whether a buy operation can be performed.
     * @param maxTrans The maximum number of transactions allowed.
     * @param memory   The memoization table.
     * @return The maximum profit that can be achieved.
     */
    public int StockBuyNSell_3_TwoTransaction_aka_maxProfit_Memoization(int[] priceArr, int pIdx, int canBuy, int maxTrans, int[][][] memory) {
        if (pIdx == priceArr.length || maxTrans == 0) {
            return 0;
        }

        if (memory[pIdx][canBuy][maxTrans] != -1) {
            return memory[pIdx][canBuy][maxTrans];
        }

        int profit = 0;

        if (canBuy == 0) {
            int spent = priceArr[pIdx];
            int bought = -spent + StockBuyNSell_3_TwoTransaction_aka_maxProfit_Memoization(priceArr, pIdx + 1, 1, maxTrans, memory);
            int skip = StockBuyNSell_3_TwoTransaction_aka_maxProfit_Memoization(priceArr, pIdx + 1, canBuy, maxTrans, memory);
            profit = Math.max(bought, skip);
        }

        if (canBuy == 1) {
            int earned = priceArr[pIdx];
            int sold = earned + StockBuyNSell_3_TwoTransaction_aka_maxProfit_Memoization(priceArr, pIdx + 1, 0, maxTrans - 1, memory);
            int skip = StockBuyNSell_3_TwoTransaction_aka_maxProfit_Memoization(priceArr, pIdx + 1, canBuy, maxTrans, memory);
            profit = Math.max(sold, skip);
        }

        memory[pIdx][canBuy][maxTrans] = profit;
        return profit;
    }

    /**
     * This method calculates the maximum profit that can be achieved from performing two transactions on a given stock price array.
     * It uses a dynamic programming approach with tabulation.
     *
     * @param priceArr The input array representing the stock prices.
     * @return The maximum profit that can be achieved.
     */
    public int StockBuyNSell_3_TwoTransaction_aka_maxProfit_Tabulation(int[] priceArr) {
        int maxTrans = 2;
        int[][][] memory = new int[priceArr.length + 1][2][maxTrans + 1];

        memory[priceArr.length][0][0] = 0;
        memory[priceArr.length][1][0] = 0;

        int profit = 0;

        for (int pIdx = priceArr.length - 1; pIdx >= 0; pIdx--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int trans = 1; trans <= maxTrans; trans++) {
                    if (canBuy == 0) {
                        int spent = priceArr[pIdx];
                        int bought = -spent + memory[pIdx + 1][1][trans];
                        int skip = memory[pIdx + 1][canBuy][trans];
                        profit = Math.max(bought, skip);
                    }
                    if (canBuy == 1) {
                        int earned = priceArr[pIdx];
                        int sold = earned + memory[pIdx + 1][0][trans - 1];
                        int skip = memory[pIdx + 1][canBuy][trans];
                        profit = Math.max(sold, skip);
                    }
                    memory[pIdx][canBuy][trans] = profit;
                }
            }
        }
        return memory[0][0][maxTrans];
    }

    /**
     * This method calculates the maximum profit that can be achieved from performing k transactions on a given stock price array.
     * It uses a dynamic programming approach with tabulation.
     *
     * @param priceArr The input array representing the stock prices.
     * @param k        The maximum number of transactions allowed.
     * @return The maximum profit that can be achieved.
     */
    public int StockBuyNSell_3_K_Transaction_aka_maxProfit_Tabulation(int[] priceArr, int k) {
        int[][][] memory = new int[priceArr.length + 1][2][k + 1];

        memory[priceArr.length][0][0] = 0;
        memory[priceArr.length][1][0] = 0;

        int profit = 0;

        for (int pIdx = priceArr.length - 1; pIdx >= 0; pIdx--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int trans = 1; trans <= k; trans++) {
                    if (canBuy == 0) {
                        int spent = priceArr[pIdx];
                        int bought = -spent + memory[pIdx + 1][1][trans];
                        int skip = memory[pIdx + 1][canBuy][trans];
                        profit = Math.max(bought, skip);
                    }
                    if (canBuy == 1) {
                        int earned = priceArr[pIdx];
                        int sold = earned + memory[pIdx + 1][0][trans - 1];
                        int skip = memory[pIdx + 1][canBuy][trans];
                        profit = Math.max(sold, skip);
                    }
                    memory[pIdx][canBuy][trans] = profit;
                }
            }
        }
        return memory[0][0][k];
    }


    /**
     * This method calculates the maximum profit that can be obtained from buying and selling stocks.
     * It uses a tabulation (bottom-up) approach and assumes a cooling off period after selling.
     *
     * @param priceArr The array of stock prices.
     * @return The maximum profit that can be obtained.
     * <p>
     * Example:
     * Suppose the input array is [1, 2, 3, 0, 2].
     * The maximum profit that can be obtained is 3 (buy on day 0, sell on day 2, cooldown on day 3, and buy on day 4 and sell on day 5).
     */
    public int StockBuyNSell_CoolingOff_aka_maxProfit_Tabulation(int[] priceArr) {
        int[][] memory = new int[priceArr.length + 2][2];

        memory[priceArr.length][0] = 0;
        memory[priceArr.length][1] = 0;

        int profit = 0;

        for (int pIdx = priceArr.length - 1; pIdx >= 0; pIdx--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 0) {
                    int spent = priceArr[pIdx];
                    int bought = -spent + memory[pIdx + 1][1];
                    int skip = memory[pIdx + 1][canBuy];
                    profit = Math.max(bought, skip);
                } else {
                    int earned = priceArr[pIdx];
                    int sold = earned + memory[pIdx + 2][0];
                    int skip = memory[pIdx + 1][canBuy];
                    profit = Math.max(sold, skip);
                }
                memory[pIdx][canBuy] = profit;
            }
        }
        return memory[0][0];
    }

    /**
     * This method calculates the maximum profit that can be obtained from buying and selling stocks.
     * It uses a tabulation (bottom-up) approach and assumes a transaction fee for each sell.
     *
     * @param priceArr The array of stock prices.
     * @param fees     The transaction fee.
     * @return The maximum profit that can be obtained.
     * <p>
     * Example:
     * Suppose the input array is [1, 3, 2, 8, 4, 9] and the fee is 2.
     * The maximum profit that can be obtained is 8 (buy on day 0, sell on day 3, buy on day 4 and sell on day 5).
     */
    public int StockBuyNSell_TransactionFee_aka_maxProfit_Tabulation(int[] priceArr, int fees) {
        int[][] memory = new int[priceArr.length + 1][2];

        for (int[] rows : memory) {
            Arrays.fill(rows, -1);
        }

        memory[priceArr.length][0] = 0;
        memory[priceArr.length][1] = 0;

        int profit = 0;

        for (int pIdx = priceArr.length - 1; pIdx >= 0; pIdx--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 0) {
                    int spent = priceArr[pIdx];
                    int bought = -spent + memory[pIdx + 1][1];
                    int skip = memory[pIdx + 1][canBuy];
                    profit = Math.max(bought, skip);
                } else {
                    int earned = priceArr[pIdx] - fees;
                    int sold = earned + memory[pIdx + 1][0];
                    int skip = memory[pIdx + 1][canBuy];
                    profit = Math.max(sold, skip);
                }
                memory[pIdx][canBuy] = profit;
            }
        }
        return memory[0][0];
    }

    /**
     * This method prints an array.
     *
     * @param arr The input array.
     *            <p>
     *            Example:
     *            Suppose the input array is [1, 2, 3, 4, 5].
     *            The method will print "1 2 3 4 5 ".
     */
    private void printArray(int[] arr) {
        System.out.println();
        for (var d : arr) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    /**
     * This method prints a list.
     *
     * @param list The input list.
     *             <p>
     *             Example:
     *             Suppose the input list is [1, 2, 3, 4, 5].
     *             The method will print "1 2 3 4 5 ".
     */
    private void printList(List<Integer> list) {
        System.out.println();
        for (var d : list) {
            System.out.print(d + " ");
        }
        System.out.println();
    }


}
