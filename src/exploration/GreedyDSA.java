package exploration;


import exploration.helpers.greedy.Job;
import exploration.helpers.greedy.helpers.Meeting;
import exploration.helpers.greedy.helpers.MeetingComparator;

import java.util.*;
import java.util.stream.Collectors;

public class GreedyDSA {

    /* Note


# Optimization Problems

Optimization problems are those that require either a minimum or maximum result. For example, consider a scenario where you need to travel from point A to point B. There are many modes of transportation available such as walking, bus, car, train, plane, etc. However, if there is a constraint that the travel must be completed within 12 hours, then only a few options like train or plane become feasible solutions. If there is an additional requirement to minimize cost, then the problem needs to be optimized to achieve the minimum result. This is called the optimal solution and there is usually only one such solution.

There are three strategies for solving this kind of problem:
1. Greedy Method
2. Dynamic Programming (DP)
3. Branch and Bound

## Greedy Method

The greedy method involves solving the problem in stages. Based on the given input, if that input is feasible, then it is included in the solution. At the end, we get the optimal solution.

For instance, consider the process of buying a car. One way is to evaluate every car available and then choose the best one. This is a lengthy process. Instead, we can:
1. Filter out the brands we do not like.
2. Pick 2 brands and then filter out models.
3. From the remaining models, choose the best car.

This process may not necessarily yield the best car overall, but it does yield the best car for the individual making the choice.

## Coding Problem: The Knapsack Problem

The knapsack problem is a common coding problem that involves fitting items into a container based on certain conditions and requirements for maximum or minimum optimization.

Consider the following scenario:

Objects[]: 1, 2, 3, 4, 5, 6, 7 (Divisible objects like cake, ice cream etc. i.e., can take fraction and non-divisible i.e., house, TV etc.)
Profits[]: 10, 5, 15, 7, 6, 18, 3
Weights[]: 2, 3, 5, 7, 1, 4, 1
Profit per Weight[]: 5, 1.6, 3, 1, 6, 4.5, 3 (p/w)

The problem here is to fill a bag (or knapsack) which has a capacity of 15 lb such that we get maximum profit. The condition is that the maximum weight is 15 lb and the requirement is to maximize profit.

Let's try the greedy method. First, we find the profit per weight as shown above. Then, we fill the bag such that we get maximum profit. We start with the maximum profit per weight and add to the bag, then move to lesser and lesser profit per weight, keeping track of the weight as we go:

| $ | Lb |
|---|----|
| 6 | 1 lb (14 lb left) |
| 5 | 2 lb (12 lb left) |
| 4.5 | 4 lb (8 lb left) |
| 3 | 5 lb (3 lb left) |
| 3 | 1 lb (2 lb left) |
| 1.6 | 2/3 lb (0 lb left) |
```


    * */


    /**
     * This method is used to find the maximum number of children that can be contented given a certain amount of cookies.
     * The method uses a greedy approach to solve the problem.
     * <p>
     * The greedy method is a problem-solving strategy that is used in optimization problems where the goal is to find an optimal solution.
     * The idea is to make the locally optimal choice at each stage with the hope that these local solutions will lead to a global optimum.
     * <p>
     * For example, consider the problem of buying the best car. One way is to go over each car available and then choose the best.
     * This is a very lengthy process. Instead, we can use a greedy approach:
     * 1. Filter out brands we do not like.
     * 2. Pick 2 brands and then again filter out models.
     * 3. Then from the remaining models, we choose the best car.
     * <p>
     * This method is similar to the Knapsack problem, which is basically any container problem where things need to fit based on a condition and requirement for max/min.
     *
     * @param  greedForCookie An array representing the greed level of each child for cookies.
     * @param  sizeOfCookie   An array representing the size of each cookie.
     * @return The maximum number of children that can be contented.
     */
    public int findContentChildren_aka_AssignCookies(int[] greedForCookie, int[] sizeOfCookie) {
        // Sort the arrays
        Arrays.sort(greedForCookie); // Like profit/w that needs to fit or satisfy weight capacity.
        Arrays.sort(sizeOfCookie); // Like weight capacity but not fixed rather declared in array

        int cookieSzIdx = 0;
        int greedyIdx = 0;

        // Iterate over both arrays
        while (cookieSzIdx < sizeOfCookie.length && greedyIdx < greedForCookie.length) {
            // If the size of the cookie satisfies the child's greed, move to the next child
            if (sizeOfCookie[cookieSzIdx] >= greedForCookie[greedyIdx]) {
                greedyIdx++;
            }
            // Always go for the next size
            cookieSzIdx++;
        }
        // Return the number of contented children
        return greedyIdx;
    }

    /**
     * This method calculates the maximum total number of units that can be put on the truck.
     * It uses a greedy approach to pick the box that has the maximum number of items.
     *
     * @param  boxTypes  2D array where each element is an array of two integers:
     *                  numberOfBoxes (the number of boxes of type i) and
     *                  numberOfUnitsPerBox (number of units in each box of the type i)
     * @param  truckSize The capacity of the truck
     * @return The maximum total number of units that can be put on the truck
     */
    public int Fractional_Knapsack_Better(int[][] boxTypes, int truckSize) {
        // Sort by second element of array in descending order
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int unitCount = 0;

        for (int[] boxtype : boxTypes) {
            // Get only the number of boxes that can fit in the truck
            int boxCount = Math.min(truckSize, boxtype[0]);
            // Calculate the total number of units
            unitCount += boxCount * boxtype[1];
            // Reduce the truck's capacity
            truckSize -= boxCount;
            // If the truck is already loaded, break the loop
            if (truckSize == 0) break;
        }

        return unitCount;
    }

    /**
     * This method calculates the minimum number of coins needed to reach a target value.
     * It uses a greedy approach to pick the largest coin that can divide the given value.
     *
     * @param  coins       Array of available coins
     * @param  targetValue The target value
     * @return List of coins used to reach the target value
     */
    public List<Integer> minCoins(int[] coins, int targetValue) {
        // Sort coins in ascending order
        Arrays.sort(coins);
        var resList = new ArrayList<Integer>();

        for (int c = coins.length - 1; c >= 0; c--) {
            int coinValue = coins[c];
            // Use the same coin if possible
            while (targetValue >= coinValue) {
                targetValue -= coinValue;
                // Add the coin to the result each time it is used
                resList.add(coinValue);
            }
        }
        return resList;
    }

    /**
     * This method checks if it is possible to give change for each transaction.
     * It uses a greedy approach to give change for 10 and 20 dollar bills.
     *
     * @param  bills Array of bills
     * @return true if it is possible to give change for each transaction, false otherwise
     */
    public boolean lemonadeChange(int[] bills) {
        int fivesInRegister = 0;
        int tensInRegister = 0;

        for (int bl = 0; bl < bills.length; bl++) {
            if (bills[bl] == 5) {
                fivesInRegister++;
            } else if (bills[bl] == 10) {
                if (fivesInRegister <= 0) {
                    return false;
                }
                tensInRegister++;
                fivesInRegister--; // Gave back as change for 10$
            } else { // Came 20$ bill
                // If have 10 and 5 bills
                if (tensInRegister > 0 && fivesInRegister > 0) {
                    tensInRegister--;
                    fivesInRegister--;
                }
                // Only fives left then give
                else if (fivesInRegister >= 3) {
                    fivesInRegister -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This method checks if a string is valid.
     * It uses a greedy approach to balance out the left and star characters.
     *
     * @param  inpStr The input string
     * @return true if the string is valid, false otherwise
     */
    public boolean checkValidString(String inpStr) {
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        for (int i = 0; i < inpStr.length(); i++) {
            char ch = inpStr.charAt(i);

            if (ch == '(') {
                leftStack.push(i); // Index is important to see who came first
            } else if (ch == '*') {
                starStack.push(i); // Index is important to see who came first
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!starStack.empty()) {
                    starStack.pop();
                } else {
                    return false; // Empty i.e. not balanced anymore
                }
            }
        }

        // When string is over but left and star are still remaining we just need to make sure
        // both balanced out each other and no left should remain
        while (!starStack.isEmpty() && !leftStack.isEmpty() && leftStack.peek() < starStack.peek()) {
            starStack.pop();
            leftStack.pop();
        }
        // Still ( present i.e. not balanced even star could not compensate
        if (!leftStack.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This method calculates the maximum number of meetings that can be attended.
     *
     * @param  start  Array of start times
     * @param  finish Array of finish times
     * @return List of meetings that can be attended
     */
    public List<Integer> maxMeeting(int[] start, int[] finish) {
        if (start.length != finish.length) return null;

        var meetings = new ArrayList<Meeting>();

        for (int i = 0; i < start.length; i++) {
            meetings.add(new Meeting(start[i], finish[i], i));
        }
        return maxMeeting(meetings);
    }


    /**
     * This method finds the maximum number of meetings that can be held in one room.
     * It sorts the meetings by their end time and then iteratively checks if the start time of the current meeting is later than the end time of the last meeting.
     * If it is, the current meeting can be held after the last meeting, so it is added to the result list.
     * The position of the meeting is also adjusted by adding 1 because the original position was 0-indexed.
     *
     * @param  meetings A list of Meeting objects representing the meetings to be scheduled.
     * @return A list of integers representing the 1-indexed positions of the meetings that can be held.
     */
    private List<Integer> maxMeeting(List<Meeting> meetings) {
        var res = new ArrayList<Integer>();

        MeetingComparator meetingComparator = new MeetingComparator();
        Collections.sort(meetings, meetingComparator);

        res.add(meetings.get(0).position);
        int lastMtEndTime = meetings.get(0).end;

        for (int m = 1; m < meetings.size(); m++) {
            var currMt = meetings.get(m);

            if (currMt.start > lastMtEndTime) {
                res.add(currMt.position);
                lastMtEndTime = currMt.end;
            }
        }

        return res.stream().map(d -> d + 1).collect(Collectors.toList());
    }

    /**
     * This method checks if a person can attend all the given meetings.
     * It sorts the meetings by their end time and then iteratively checks if the start time of the current meeting is earlier than the end time of the last meeting.
     * If it is, the person cannot attend all the meetings, so it returns false.
     * If the person can attend all the meetings, it returns true.
     *
     * @param  intervals A 2D array where each row represents a meeting, the first element of the row is the start time of the meeting, and the second element is the end time.
     * @return A boolean value indicating whether the person can attend all the meetings.
     */
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length <= 0) return true;

        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);

        int prevMeetingEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            var curMeetingStart = intervals[i][0];

            if (curMeetingStart < prevMeetingEnd) {
                return false;
            }
            prevMeetingEnd = intervals[i][1];
        }

        return true;
    }

    /**
     * This method checks if it is possible to reach the last index of the array by jumping.
     * The maximum jump length at each index is determined by the value at that index.
     * It iteratively checks if the current index is reachable from the previous indices.
     * If it is not, it returns false.
     * If the last index is reachable, it returns true.
     *
     * @param  nums An array of non-negative integers representing the maximum jump length at each index.
     * @return A boolean value indicating whether the last index is reachable.
     */
    public boolean canJump(int[] nums) {
        int reachable = 0;

        for (int pos = 0; pos < nums.length; pos++) {
            if (pos > reachable) {
                return false;
            }
            int jumLen = pos + nums[pos];
            reachable = Math.max(reachable, jumLen);
        }
        return true;
    }

    /**
     * This method calculates the minimum number of jumps required to reach the last index of the array.
     * The maximum jump length at each index is determined by the value at that index.
     * It iteratively checks if the current index is reachable from the previous indices and updates the maximum reachable index.
     * It also counts the number of jumps made.
     * If the current index is the end of the previous jump, it increments the jump count and updates the end of the current jump.
     *
     * @param  nums An array of non-negative integers representing the maximum jump length at each index.
     * @return An integer representing the minimum number of jumps required to reach the last index.
     */
    public int minJump_UnderStable(int[] nums) {
        int soFarMaxJump = 0;
        int prevJumEnded = 0;
        int jumpcount = 0;

        for (int pos = 0; pos < nums.length - 1; pos++) {
            soFarMaxJump = Math.max(soFarMaxJump, pos + nums[pos]);

            if (pos == prevJumEnded) {
                jumpcount++;
                prevJumEnded = soFarMaxJump;
            }
        }
        return jumpcount;
    }


    /**
     * This method calculates the minimum number of platforms required for all trains to arrive and depart without waiting.
     * It sorts the arrival and departure times independently because we don't care which train comes and goes,
     * instead we care about what is taking place between two time starts/ends or in this case platform requirements for trains coming and going.
     *
     * @param  arr Array of train arrival times
     * @param  dep Array of train departure times
     * @return The minimum number of platforms required
     */
    public int min_platform_required(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int ct = 1;
        int startTimeIdx = 1;
        int endTimeIdx = 0;

        // At least 1 platform is always needed.
        int totalPlatForms = 1;

        while (startTimeIdx < arr.length && endTimeIdx < arr.length) {
            // If a train departs later than the next arrival, then a new platform is needed
            if (dep[endTimeIdx] >= arr[startTimeIdx]) {
                ct++;
                startTimeIdx++;
            }
            // If a train arrives later than the previous departure, then use the same platform
            else if (arr[startTimeIdx] > dep[endTimeIdx]) {
                ct--;
                endTimeIdx++;
            }

            // To make sure at least 1 platform is required if ct comes down to 0.
            if (ct > totalPlatForms) {
                totalPlatForms = ct;
            }
        }

        return totalPlatForms;
    }

    /**
     * This method schedules jobs to maximize profit. It uses a greedy approach: pick max profit jobs first and mark job can be done on its deadline
     * and then pick second max profit keep marking job and once all fit done.
     *
     * @param  jobArr Array of jobs with their deadlines and profits
     * @return An array where the first element is the total number of jobs done and the second element is the total profit
     */
    public int[] JobScheduling(Job[] jobArr) {
        // Sort by profit descending
        Arrays.sort(jobArr, (a, b) -> b.profit - a.profit);

        // Find max deadline
        int maxLimit = -1;
        for (int i = 0; i < jobArr.length; i++) {
            maxLimit = Math.max(maxLimit, jobArr[i].deadline);
        }

        // Prepare job marking table
        int[] doneJobTable = new int[maxLimit + 1];
        for (int i = 1; i <= maxLimit; i++) {
            doneJobTable[i] = -1;
        }

        // Go over each job and prepare profit and # of jobs
        int totalJobs = 0;
        int totalProfit = 0;

        for (int jb = 0; jb < jobArr.length; jb++) {
            int jobLimit = jobArr[jb].deadline;

            for (int l = jobLimit; l >= 0; l--) {
                // If slot is available then do job else keep looking
                if (doneJobTable[l] == -1) {
                    doneJobTable[l] = jb; // Marked
                    totalJobs++;
                    totalProfit += jobArr[jb].profit;
                    break;
                }
            }
        }

        int ans[] = new int[2];
        ans[0] = totalJobs;
        ans[1] = totalProfit;
        return ans;
    }


    /**
     * This method is used to insert a new interval into an existing list of intervals and then merge all overlapping intervals.
     * The method uses a greedy approach to solve the problem.
     *
     * @param  intervals   An array of intervals.
     * @param  newInterval The new interval to be inserted.
     * @return The merged intervals after the insertion of the new interval.
     */
    public int[][] insert_interval(int[][] intervals, int[] newInterval) {
        // Add the new interval to the existing list of intervals
        int[][] allIntervals = addToExistingIntervalList(intervals, newInterval);

        // Prepare the result list
        List<int[]> res = new ArrayList<>();

        for (int p = 0; p < allIntervals.length; p++) {
            // Initialize the current interval
            int[] currInterval = {allIntervals[p][0], allIntervals[p][1]};

            // Merge until the list gets exhausted or no overlap is found
            while (p < allIntervals.length && IsIntervalOverLap(currInterval, allIntervals[p])) {
                // Update the current interval as it got merged now and time to see if the next one gets merged or not
                currInterval = mergeTwoIntervals(currInterval, allIntervals[p]);
                p++;
            }
            // Once the current interval goes all the way down and merged, then only add to the result
            res.add(currInterval);
            p--; // Restart from the previous index in the for loop as when p++ got increased and not matched and came out of the while loop
        }

        // Return the merged intervals
        return res.toArray(new int[res.size()][2]);
    }

    /**
     * This method is used to merge two intervals.
     *
     * @param  a The first interval.
     * @param  b The second interval.
     * @return The merged interval.
     */
    private int[] mergeTwoIntervals(int[] a, int[] b) {
        // Pick the smaller start and the larger end and merge
        int[] newInterval = {Math.min(a[0], b[0]), Math.max(a[1], b[1])};
        return newInterval;
    }

    /**
     * This method is used to check if two intervals overlap.
     *
     * @param  a The first interval.
     * @param  b The second interval.
     * @return True if the intervals overlap, false otherwise.
     */
    private boolean IsIntervalOverLap(int[] a, int[] b) {
        return a[1] >= b[0];
    }

    /**
     * This method is used to add a new interval to an existing list of intervals.
     *
     * @param  intervals   The existing list of intervals.
     * @param  newInterval The new interval to be added.
     * @return The updated list of intervals.
     */
    private int[][] addToExistingIntervalList(int[][] intervals, int[] newInterval) {
        // Convert the array of intervals to a list for easy addition
        List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        boolean inserted = false;

        for (int i = 0; i < intervals.length; i++) {
            // When the start of the new interval is earlier than the start of the next interval
            if (newInterval[0] < intervals[i][0]) {
                // Add the new interval at the particular position
                intervalList.add(i, newInterval);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            // Add the new interval at the end
            intervalList.add(newInterval);
        }

        // Convert the list back to an array and return
        return intervalList.toArray(new int[intervalList.size()][2]);
    }


    /**
     * This method merges all overlapping intervals.
     * It uses a greedy approach to merge the intervals that are already inserted.
     *
     * @param  intervals 2D array of intervals
     * @return 2D array of merged intervals
     */
    public int[][] merge_intervals(int[][] intervals) {
        // Sort by first element of array in ascending order
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> res = new ArrayList<>();

        for (int p = 0; p < intervals.length; p++) {
            // Current interval
            int[] currInterval = {intervals[p][0], intervals[p][1]};

            // Merge until the list gets exhausted or no overlap is found
            while (p < intervals.length && IsIntervalOverLap(currInterval, intervals[p])) {
                // Update current interval as it got merged now
                currInterval = mergeTwoIntervals(currInterval, intervals[p]);
                p++;
            }
            // Once current interval goes all the way down and merged then only add to result
            res.add(currInterval);
            p--;
        }

        return res.toArray(new int[res.size()][2]);
    }

    /**
     * This method calculates the minimum number of non-overlapping intervals.
     * It uses a greedy approach to remove the minimum number of intervals so that the rest of the intervals are non-overlapping.
     *
     * @param  intervals 2D array of intervals
     * @return The minimum number of intervals to remove to make the rest of the intervals non-overlapping
     */
    public int non_overlapping_interval(int[][] intervals) {
        // Sort by first element of array in ascending order
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        int prev = 0;
        int counter = 0;

        for (int cur = 1; cur < intervals.length; cur++) {
            // If previous end > current start
            if (intervals[prev][1] > intervals[cur][0]) {
                counter++; // And move on

                // But when previous end larger than current end then ignore previous and make current next previous
                if (intervals[prev][1] >= intervals[cur][1]) {
                    prev = cur;
                }
            } else {
                // Move on all good
                prev = cur;
            }
        }

        return counter;
    }

    /**
     * This method calculates the minimum number of candies to distribute to children.
     * It uses a greedy approach to give each child a number of candies based on their rating.
     *
     * @param  ratings Array of children's ratings
     * @return The minimum number of candies required
     */
    public int candy_distribution(int[] ratings) {
        int[] candies = new int[ratings.length];

        // Give 1 to each first
        Arrays.fill(candies, 1);

        // Look left 1
        for (int p = 1; p < ratings.length; p++) {
            if (ratings[p] > ratings[p - 1]) {
                candies[p] = candies[p - 1] + 1;
            }
        }

        // Look right 1
        for (int p = ratings.length - 2; p >= 0; p--) {
            if (ratings[p] > ratings[p + 1]) {
                int increasedCand = candies[p + 1] + 1;
                int mycandy = candies[p];
                candies[p] = Math.max(mycandy, increasedCand);
            }
        }
        int totalMinCandy = 0;
        for (int p = 0; p < candies.length; p++) {
            totalMinCandy += candies[p];
        }
        return totalMinCandy;
    }


    /**
     * This method calculates the maximum profit that can be obtained by scheduling jobs.
     * It uses a priority queue to store the end time and profit of each job, and a greedy algorithm to choose the jobs.
     * The jobs are sorted by their start time, and for each job, it checks if its start time is later than the end time of the previous job.
     * If it is, it adds the profit of the current job to the maximum profit so far and updates the end time.
     * Finally, it returns the maximum profit.
     *
     * @param  startTime An array of integers representing the start time of each job.
     * @param  endTime   An array of integers representing the end time of each job.
     * @param  profit    An array of integers representing the profit of each job.
     * @return An integer representing the maximum profit.
     */
    public int jobScheduling_maxProfit_PQ(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> allJobs = new ArrayList<>();
        for (int i = 0; i < profit.length; i++) {
            var eachJb = new ArrayList<Integer>();
            eachJb.add(startTime[i]);
            eachJb.add(endTime[i]);
            eachJb.add(profit[i]);
            allJobs.add(eachJb);
        }

        var sortedAllJobs = allJobs.stream().sorted((a, b) -> a.get(0) - b.get(0)).collect(Collectors.toList());

        return jobScheduling_maxProfit_PQ(sortedAllJobs);
    }

    /**
     * This helper method calculates the maximum profit that can be obtained by scheduling jobs.
     * It uses a priority queue to store the end time and profit of each job, and a greedy algorithm to choose the jobs.
     * The jobs are sorted by their start time, and for each job, it checks if its start time is later than the end time of the previous job.
     * If it is, it adds the profit of the current job to the maximum profit so far and updates the end time.
     * Finally, it returns the maximum profit.
     *
     * @param  allJobs A list of lists, where each list contains the start time, end time, and profit of a job.
     * @return An integer representing the maximum profit.
     */
    int jobScheduling_maxProfit_PQ(List<List<Integer>> allJobs) {
        PriorityQueue<List<Integer>> minPQ_EndTime = new PriorityQueue<>((l1, l2) -> l1.get(0) - l2.get(0));
        int totalJobs = allJobs.size();
        int maxProfit = 0;

        for (int curJob = 0; curJob < totalJobs; curJob++) {
            int start = allJobs.get(curJob).get(0);
            int end = allJobs.get(curJob).get(1);
            int profit = allJobs.get(curJob).get(2);

            while (!minPQ_EndTime.isEmpty() && start >= minPQ_EndTime.peek().get(0)) {
                int prevProfit = minPQ_EndTime.peek().get(1);
                maxProfit = Math.max(prevProfit, maxProfit);
                minPQ_EndTime.remove();
            }

            var pqList = new ArrayList<Integer>();
            pqList.add(end);
            pqList.add(profit + maxProfit);
            minPQ_EndTime.add(pqList);
        }

        while (!minPQ_EndTime.isEmpty()) {
            int prevProfit = minPQ_EndTime.peek().get(1);
            maxProfit = Math.max(prevProfit, maxProfit);
            minPQ_EndTime.remove();
        }
        return maxProfit;
    }

    /**
     * This method removes k digits from the input number to make the remaining number as small as possible.
     * It iteratively checks each digit and if the current digit is smaller than the previous digit, it removes the previous digit.
     * After all comparisons, if there are still digits left to remove, it removes them from the end.
     * It also trims leading zeros from the result.
     * If the result is empty, it returns "0".
     *
     * @param  nums A string representing the input number.
     * @param  k    An integer representing the number of digits to remove.
     * @return A string representing the smallest possible number after removing k digits.
     */
    public String remove_K_digits(String nums, int k) {
        StringBuilder res = new StringBuilder();
        res.append(nums.charAt(0));

        for (int digit = 1; digit < nums.length(); digit++) {
            if (k > 0 && res.length() > 0 && nums.charAt(digit) < res.charAt(res.length() - 1)) {
                res.deleteCharAt(res.length() - 1);
                k--;
                digit--;
                continue;
            }
            res.append(nums.charAt(digit));
        }

        while (k > 0 && res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
            k--;
        }

        while (res.length() > 0 && res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }

        if (res.length() == 0) {
            res.append('0');
        }
        return res.toString();
    }


    /**
     * This method checks if a string is valid based on the constraints of parentheses and stars.
     * It works for the majority of use cases but not all, as the constraint is that open parentheses come first then close.
     *
     * @param  inpStr The input string to check
     * @return True if the string is valid, false otherwise
     */
    public boolean checkValidString_WorksForMajorityUseCases(String inpStr) {
        int parenthesis = 0;
        int star = 0;

        for (int i = 0; i < inpStr.length(); i++) {
            char ch = inpStr.charAt(i);

            if (ch == '(') {
                parenthesis++;
            } else if (ch == ')') {
                parenthesis--;
            } else {
                star++;
            }
        }

        if (parenthesis == 0) {
            return true;
        } else {
            int left = Math.abs(parenthesis) - star;
            return left <= 0;
        }
    }

    /**
     * This method calculates the maximum total number of units that can be put on the truck using a greedy approach.
     * It picks the box(s) that has the max items and starts from there.
     *
     * @param  boxTypes  2D array where each element is an array of two integers: [numberOfBoxes, numberOfUnitsPerBox]
     * @param  truckSize The size of the truck
     * @return The maximum total number of units that can be put on the truck
     */
    public int Fractional_Knapsack_ON2(int[][] boxTypes, int truckSize) {
        int unitCount = 0;
        int remainTruckSize = truckSize;

        while (remainTruckSize > 0) {
            // Get the box that has the max unit
            int maxUnitBoxIdx = findBoxTypeHasMaxUnit(boxTypes);

            if (maxUnitBoxIdx == -1) break; // All boxes loaded

            // Now find how many boxes are there for that max unit
            int boxCountForMaxUnitBoxId = boxTypes[maxUnitBoxIdx][0];

            // Find out how many can still fit
            int boxCount = Math.min(remainTruckSize, boxCountForMaxUnitBoxId);

            // Now calculate how many units those boxes can make for max unit box index
            unitCount += boxCount * boxTypes[maxUnitBoxIdx][1];

            // Deduct from remaining
            remainTruckSize -= boxCount;

            // Mark particular box loaded
            boxTypes[maxUnitBoxIdx][1] = -1;
        }

        return unitCount;
    }

    /**
     * This method finds the box type that has the maximum unit.
     *
     * @param  boxTypes 2D array where each element is an array of two integers: [numberOfBoxes, numberOfUnitsPerBox]
     * @return The index of the box type that has the maximum unit
     */
    private int findBoxTypeHasMaxUnit(int[][] boxTypes) {
        int maxUnitBoxIdx = -1;
        int maxUnits = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            if (boxTypes[i][1] > maxUnits) {
                maxUnits = boxTypes[i][1];
                maxUnitBoxIdx = i;
            }
        }
        return maxUnitBoxIdx;
    }

    /**
     * This method calculates the minimum number of jumps to reach the end of the array.
     * It uses a greedy approach to jump to the farthest reachable index.
     *
     * @param  nums Array of maximum jump lengths at each index
     * @return The minimum number of jumps to reach the end of the array
     */
    public int minJump(int[] nums) {
        int farestItCanJump = 0;
        int count = 0;
        int end = 0;
        int targetIdx = nums.length - 1;

        if (targetIdx == 0 && nums[0] == 0) return 0;

        for (int s = 0; s < nums.length; s++) {
            // Get jump length and record max jump so far
            int jumLen = s + nums[s];
            farestItCanJump = Math.max(jumLen, farestItCanJump);

            // Reached and also worked for 1 element in array only use case
            if (farestItCanJump >= targetIdx) {
                count = count + 1;
                break;
            }

            // Covered all items in between s -> end
            if (s == end) {
                count = count + 1; // Attempt to jump and not yet reached to last index so counter ++
                end = farestItCanJump; // New max jump to add as end to try out in-between jumps to see if it reaches to last index
            }
        }
        return count;
    }

    /**
     * This method calculates the maximum profit from scheduling jobs.
     * It uses a greedy approach to schedule the jobs with non-overlapping intervals.
     *
     * @param  startTime Array of start times
     * @param  endTime   Array of end times
     * @param  profit    Array of profits
     * @return The maximum profit from scheduling jobs
     */
    public int jobScheduling_maxProfit(int[] startTime, int[] endTime, int[] profit) {
        // Just prep new Array to store all at same place
        int[][] jobs = new int[profit.length][3]; // start, end & profit
        for (int a = 0; a < startTime.length; a++) {
            jobs[a][0] = startTime[a];
            jobs[a][1] = endTime[a];
            jobs[a][2] = profit[a];
        }

        // Now sort by ending time
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        TreeMap<Integer, Integer> endTimeNmxProfit = new TreeMap<>();
        endTimeNmxProfit.put(0, 0); // Just start with nothing

        // Go over each job and store end time with max profits
        for (int[] curJob : jobs) {
            // Previously stored Max profit for end time <= current end time
            int prevMaXForSmallerorEqualEndTime = endTimeNmxProfit.floorEntry(curJob[0]).getValue();
            int curProft = curJob[2];

            int newMaxProfit = prevMaXForSmallerorEqualEndTime + curProft;

            // Say it had so far $50 max profit
            int maxProfitSoFar = endTimeNmxProfit.lastEntry().getValue();

            // When new profit is greater than so far achieved and stored profit then
            if (newMaxProfit > maxProfitSoFar) {
                // Add this end time and its new max profit
                endTimeNmxProfit.put(curJob[1], newMaxProfit);
            }
        }

        // Always last entry has max profit
        return endTimeNmxProfit.lastEntry().getValue();
    }

}

