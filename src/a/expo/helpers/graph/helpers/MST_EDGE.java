package a.expo.helpers.graph.helpers;

public class MST_EDGE implements Comparable<MST_EDGE> {
  public   int source;
    public int destination;
    public int weight;

    public MST_EDGE(int source, int destination, int weight) {
        this.weight = weight;
        this.destination = destination;
        this.source = source;
    }

    @Override
    public int compareTo(MST_EDGE obj) {
        return this.weight - obj.weight;
    }

}
