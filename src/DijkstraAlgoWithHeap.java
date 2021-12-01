import java.util.ArrayList;

/**
 * Class to find max bw path using Dijkstra algorithm using heap data structure
 */
public class DijkstraAlgoWithHeap {

    public static int[] status;
    public static int[] dad;
    public static int[] bw;
    public static MaxHeap heap;

    public static int INTREE = 2;
    public static int FRINGE = 1;
    public static int UNSEEN = 0;
    public static final Integer NO_OF_VERTICES = 5000;

    public static int maxBandwidthPath(Graph graph, int source, int destination) {
        status = new int[NO_OF_VERTICES];
        dad = new int[NO_OF_VERTICES];
        bw = new int[NO_OF_VERTICES];
        heap = new MaxHeap(NO_OF_VERTICES);

        for (int i = 0; i < NO_OF_VERTICES; i++) {
            status[i] = UNSEEN;
            bw[i] = Integer.MAX_VALUE;
            dad[i] = -1;
        }
        status[source] = INTREE;

        ArrayList<Edge> neighbors = graph.getAdjList()[source];
        for (Edge edge : neighbors) {
            int w = edge.dest;
            status[w] = FRINGE;
            dad[w] = source;
            bw[w] = edge.getWeight();
            heap.insert(bw[w], w);
        }

        int maxIndex = 0;
        while (status[destination] != INTREE) {
            //pick a fringe from the max-heap with max bw
            maxIndex = heap.extractMaxIndex();
            if (maxIndex != -1) {
                status[maxIndex] = INTREE;
                heap.delete(heap.getPosByHeapNodeName(maxIndex));
                ArrayList<Edge> adjEdges = graph.getAdjList()[maxIndex];
                for (Edge edge : adjEdges) {
                    int w = edge.dest;
                    if (status[w] == UNSEEN) {
                        dad[w] = maxIndex;
                        status[w] = FRINGE;
                        bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                        heap.insert(bw[w], w);
                    } else if(status[w] == FRINGE && bw[w] < Math.min(bw[maxIndex], edge.getWeight())) {
                        dad[w] = maxIndex;
                        heap.delete(heap.getPosByHeapNodeName(w));
                        bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                        heap.insert(bw[w], w);
                    }
                }
            }
        }
        return bw[destination];
    }


}
