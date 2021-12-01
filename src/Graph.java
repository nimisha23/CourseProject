import java.util.ArrayList;
import java.util.List;

public class Graph {

    /**
     * Class to store the generated graphs.
     */

    private int vertices;
    private ArrayList<Edge>[] adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices ; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int dest, Edge e) {
        adjList[src].add(e);
    }

    public int getVertices() {
        return vertices;
    }

    public ArrayList<Edge>[] getAdjList() {
        return adjList;
    }
}
