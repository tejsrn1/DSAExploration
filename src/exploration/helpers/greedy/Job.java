package exploration.helpers.greedy;

public class Job {
    public int id;
    public int profit;
    public int deadline;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.profit = profit;
        this.deadline = deadline;
    }
}
