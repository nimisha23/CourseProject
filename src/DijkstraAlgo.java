import java.util.ArrayList;

/**
 * Class to find max bw path using Dijkstra algorithm without using heap
 */
public class DijkstraAlgo {

    public static int[] status;
    public static int[] dad;
    public static int[] bw;

    public static int INTREE = 2;
    public static int FRINGE = 1;
    public static int UNSEEN = 0;
    public static final Integer NO_OF_VERTICES = 5000;

    public static int maxBandwidthPath(Graph graph, int source, int destination) {
        status = new int[NO_OF_VERTICES];
        dad = new int[NO_OF_VERTICES];
        bw = new int[NO_OF_VERTICES];

        for (int i = 0; i < NO_OF_VERTICES; i++) {
            status[i] = UNSEEN;
            bw[i] = Integer.MIN_VALUE;
            dad[i] = -1;
        }
        status[source] = INTREE;

        ArrayList<Edge> neighbors = graph.getAdjList()[source];
        for (Edge edge : neighbors) {
            //int w = edge.getOtherVertex(source);
            int w = edge.dest;
            status[w] = FRINGE;
            dad[w] = source;
            bw[w] = edge.getWeight();
        }

        while (status[destination] != INTREE) {
            //pick a fringe v with max bw[v], that is maxIndex
            int maxIndex = getMaxFringe();
            status[maxIndex] = INTREE;

            ArrayList<Edge> adjEdges = graph.getAdjList()[maxIndex];

            for (Edge edge : adjEdges) {
                int w = edge.dest;
                if (status[w] == UNSEEN) {
                    dad[w] = maxIndex;
                    status[w] = FRINGE;
                    bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                } else if(status[w] == FRINGE && bw[w] < Math.min(bw[maxIndex], edge.getWeight())) {
                    dad[w] = maxIndex;
                    bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                }
            }
        }
        return bw[destination];
    }

    private static int getMaxFringe() {
        int maxBandwidth = Integer.MIN_VALUE, maxIndex = -1;
        for (int i = 0; i < NO_OF_VERTICES; i++) {
            if (status[i] == FRINGE) {
                if (bw[i] > maxBandwidth) {
                    maxBandwidth = bw[i];
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }
}
