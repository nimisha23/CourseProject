import java.util.Random;

/**
 * Test Class that generates Sparse and Dense graphs and run the three algorithms on 5 vertex pairs of each graph
 * Prints the sum and average of the running time of the three algorithms
 */
public class Test {

        private static final int NO_OF_VERTICES = 5000;
        private static final int NO_OF_GRAPHS = 5;
        private static final int NO_OF_VERTEX_PAIR = 5;

        public static void main(String[] args) {
            // five pairs of graphs
            long sum1 = 0;
            long sum2 = 0;
            long sum3 = 0;
            long sum4 = 0;
            long sum5 = 0;
            long sum6 = 0;

            for (int i = 0; i < NO_OF_GRAPHS; i++) {

                System.out.println("GRAPH GENERATION INSTANCE :: " + (i + 1));
                long startTime1 = System.currentTimeMillis();
                Graph sparse = GraphGenerator.generateGraph("Sparse", NO_OF_VERTICES);
                long endTime1 = System.currentTimeMillis();
                System.out.println("Sparse graph generation time: " + (endTime1 - startTime1));
                System.out.println(" ");

                for (int j = 0; j < NO_OF_VERTEX_PAIR; j++) {
                    // five pairs of sources and destinations
                    System.out.println("VERTICES PAIR :: " + (j + 1));

                    long maxBandwidthTime0 = System.currentTimeMillis();
                    Random randomGenerator = new Random();
                    int source = randomGenerator.nextInt(NO_OF_VERTICES);
                    int destination = -1;
                    while (true) {
                        destination = randomGenerator.nextInt(NO_OF_VERTICES);
                        if (source != destination) break;
                    }
                    System.out.println("Source: " + source + " Destination: " + destination);
                    int bw1 = DijkstraAlgo.maxBandwidthPath(sparse, source, destination);
                    System.out.println("Dijkstra Algorithm Max Bandwidth: " + bw1);
                    long maxBandwidthTime1 = System.currentTimeMillis();
                    System.out.println("Dijkstra Algorithm execution time: " + (maxBandwidthTime1 - maxBandwidthTime0));
                    sum1 += (maxBandwidthTime1 - maxBandwidthTime0);
                    int bw2 = DijkstraAlgoWithHeap.maxBandwidthPath(sparse, source, destination);
                    System.out.println("Dijkstra Algorithm With Heap Max Bandwidth: " + bw2);
                    long maxBandwidthTime2 = System.currentTimeMillis();
                    System.out.println("Dijkstra Algorithm With Heap execution time: " + (maxBandwidthTime2 - maxBandwidthTime1));
                    sum2 += (maxBandwidthTime2 - maxBandwidthTime1);
                    int bw3 = KruskalAlgo.maxBandwidthPath(sparse, source, destination);
                    System.out.println("Kruskal Algorithm Max Bandwidth: " + bw3);
                    long maxBandwidthTime3 = System.currentTimeMillis();
                    System.out.println("Kruskal Algorithm execution time: " + (maxBandwidthTime3 - maxBandwidthTime2));
                    sum3 += (maxBandwidthTime3 - maxBandwidthTime2);
                    System.out.println(" ");
                }



                long startTime2 = System.currentTimeMillis();
                Graph dense = GraphGenerator.generateGraph("Dense", NO_OF_VERTICES);
                long endTime2 = System.currentTimeMillis();
                System.out.println("Dense graph generation time: " + (endTime2 - startTime2));
                System.out.println(" ");
                for (int j = 0; j < NO_OF_VERTEX_PAIR; j++) {
                    System.out.println("VERTICES PAIR :: " + (j + 1));
                    Random randomGenerator = new Random();
                    int source = randomGenerator.nextInt(NO_OF_VERTICES);
                    int destination = -1;
                    while (true) {
                        destination = randomGenerator.nextInt(NO_OF_VERTICES);
                        if (source != destination) break;
                    }
                    System.out.println("Source: " + source + " Destination: " + destination);
                    long maxBandwidthTime0 = System.currentTimeMillis();
                    int bw1 = DijkstraAlgo.maxBandwidthPath(dense, source, destination);
                    System.out.println("Dijkstra Algorithm Max Bandwidth: " + bw1);
                    long maxBandwidthTime1 = System.currentTimeMillis();
                    System.out.println("Dijkstra Algorithm execution time: " + (maxBandwidthTime1 - maxBandwidthTime0));
                    sum4 += (maxBandwidthTime1 - maxBandwidthTime0);
                    int bw2 = DijkstraAlgoWithHeap.maxBandwidthPath(dense, source, destination);
                    System.out.println("Dijkstra Algorithm With Heap Max Bandwidth: " + bw2);
                    long maxBandwidthTime2 = System.currentTimeMillis();
                    System.out.println("Dijkstra Algorithm With Heap execution time: " + (maxBandwidthTime2 - maxBandwidthTime1));
                    sum5 += (maxBandwidthTime2 - maxBandwidthTime1);
                    int bw3 = KruskalAlgo.maxBandwidthPath(dense, source, destination);
                    System.out.println("Kruskal Algorithm Max Bandwidth: " + bw3);
                    long maxBandwidthTime3 = System.currentTimeMillis();
                    System.out.println("Kruskal Algorithm execution time: " + (maxBandwidthTime3 - maxBandwidthTime2));
                    sum6 += (maxBandwidthTime3 - maxBandwidthTime2);

                    System.out.println(" ");
                }

            }

            System.out.println("Sum of running time for Dijkstra algorithm for Sparse graph in sec :: " + (double)sum1/1000);
            System.out.println("Sum of running time for Dijkstra algorithm with heap for Sparse graph in sec :: " + (double)sum2/1000);
            System.out.println("Sum of running time for Kruskal algorithm for Sparse graph in sec :: " + (double)sum3/1000);
            System.out.println("Average running time of Dijkstra algorithm for Sparse graph in sec :: " + (double)sum1/25000);
            System.out.println("Average running time of Dijkstra algorithm with heap for Sparse graph in sec :: " + (double)sum2/25000);
            System.out.println("Average running time of Kruskal algorithm for Sparse graph in sec :: " + (double)sum3/25000);

            System.out.println("Sum of running time for Dijkstra algorithm for Dense graph in sec :: " + (double)sum4/1000);
            System.out.println("Sum of running time for Dijkstra algorithm with heap for Dense graph in sec :: " + (double)sum5/1000);
            System.out.println("Sum of running time for Kruskal algorithm for Dense graph in sec :: " + (double)sum6/1000);
            System.out.println("Average running time of Dijkstra algorithm for Dense graph in sec :: " + (double)sum4/25000);
            System.out.println("Average running time of Dijkstra algorithm with heap for Dense graph in sec :: " + (double)sum5/25000);
            System.out.println("Average running time of Kruskal algorithm for Dense graph in sec :: " + (double)sum6/25000);

        }
}
