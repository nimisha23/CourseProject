public class Edge {

    /**
     * Class to represent weighted edges of a graph.
     */
    int src;
    int dest;
    int weight;

    public int getWeight() {
        return weight;
    }

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int getOtherVertex(int x) {
        if(x == src) {
            return dest;
        } else if(x == dest) {
            return src;
        } else {
            throw new IllegalArgumentException("Not a valid edge vertex");
        }
    }
}
