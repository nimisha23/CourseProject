import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to find max bw path using Kruskal algorithm using heapsort
 */
public class KruskalAlgo {

    public static final Integer NO_OF_VERTICES = 5000;
    private static int[] color;
    private static int[] dadBFS;
    private static int[] bw;
    private static int BLACK = 2;
    private static int GREY = 1;
    private static int WHITE = 0;

    public static void heapSort(Edge[] edges) {
        int n = edges.length;

        for(int i = n/2-1; i >= 0; i--) {
            heapify(edges, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Edge temp = edges[0];
            edges[0] = edges[i];
            edges[i] = temp;
            heapify(edges, i, 0);
        }
    }

    private static void heapify(Edge[] edges, int n, int i) {
        // Find largest among root, left child and right child
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && edges[l].getWeight() > edges[largest].getWeight())
            largest = l;

        if (r < n && edges[r].getWeight() > edges[largest].getWeight())
            largest = r;

        // Swap and continue heapifying if root is not largest
        if (largest != i) {
            Edge swap = edges[i];
            edges[i] = edges[largest];
            edges[largest] = swap;

            heapify(edges, n, largest);
        }
    }

    public static int maxBandwidthPath(Graph graph, int source, int destination) {
        Graph newGraph = new Graph(NO_OF_VERTICES);
        ArrayList<Edge> list = new ArrayList<>();
        for(int i = 0; i < NO_OF_VERTICES; i++) {
            for(Edge e : graph.getAdjList()[i]) {
                if(e.dest >= i) {
                    list.add(e);
                }
            }
        }
        int size = list.size();
        Edge[] edges = list.toArray(new Edge[size]);
        heapSort(edges);

        MakeUnionFind muf = new MakeUnionFind(NO_OF_VERTICES);
        for(int i = size-1; i >= 0; i--) {
            Edge e = edges[i];
            int src = muf.find(e.src);
            int dest = muf.find(e.dest);
            if (src != dest) {
                Edge edge1 = new Edge(e.src, e.dest, e.weight);
                newGraph.addEdge(e.src, e.dest, edge1);
                Edge edge2 = new Edge(e.dest, e.src, e.weight);
                newGraph.addEdge(e.dest, e.src, edge2);
                muf.union(src, dest);
            }
        }

        color = new int[NO_OF_VERTICES];
        dadBFS = new int[NO_OF_VERTICES];
        bw = new int[NO_OF_VERTICES];
        for (int v = 0; v < NO_OF_VERTICES; v++) {
            color[v] = WHITE;
            dadBFS[v] = -1;
            bw[v] = Integer.MAX_VALUE;
        }
        color[source] = GREY;
        dadBFS[source] = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (color[destination] != BLACK && !queue.isEmpty()) {
            int u = queue.poll();
            ArrayList<Edge> uEdgeList = newGraph.getAdjList()[u];
            for (Edge edge : uEdgeList) {
                //int v = edge.getOtherVertex(u);
                int v = edge.dest;
                if (color[v] == WHITE) {
                    color[v] = GREY;
                    bw[v] = Math.min(bw[u], edge.getWeight());
                    dadBFS[v] = u;
                    queue.offer(v);
                } else if (color[v] == GREY && bw[v] < Math.min(bw[u], edge.getWeight())) {
                    dadBFS[v] = u;
                    bw[v] = Math.min(bw[u], edge.getWeight());
                }
            }
            color[u] = BLACK;
        }
        return bw[destination];
    }

}
