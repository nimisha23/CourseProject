import java.util.ArrayList;
import java.util.Random;

/**
 * Class to generate random sparse and dense graphs with given no of vertices and graph density
 */
public class GraphGenerator {

    private static final Integer SPARSE_AVG_VERTEX_DEGREE = 6;
    private static final Integer DENSE_AVG_VERTEX_DEGREE = 1000;

    public static Graph generateGraph(String graphType, int vertices) {
        Graph gr = new Graph(vertices);
        Random random = new Random();
        //create cycle between all the vertices of the graph
        for(int i = 0; i < vertices; i++) {
            int dest = (i+1)%5000; //here dest is not random, it is src+1 always
            int weight = random.nextInt(1000) + 1;
            Edge edge1 = new Edge(i, dest, weight);
            if (!exists(gr, i, dest)) {
                gr.addEdge(i, dest, edge1);
            }
            //undirected graph
            Edge edge2 = new Edge(dest, i, weight);
            if (!exists(gr, dest, i)) {
                gr.addEdge(dest, i, edge2);
            }
        }
        if(graphType.equalsIgnoreCase("Sparse")) {
            for(int i = 0; i < vertices; i++) {
                while(gr.getAdjList()[i].size() < SPARSE_AVG_VERTEX_DEGREE) { //vertex degree is less than 6
                    int dest = random.nextInt(vertices);
                    if(i != dest) {
                        int weight = random.nextInt(1000) + 1;
                        Edge edge1 = new Edge(i, dest, weight);
                        if (!exists(gr, i, dest)) {
                            gr.addEdge(i, dest, edge1);
                        }
                        //undirected graph
                        Edge edge2 = new Edge(dest, i, weight);
                        if (!exists(gr, dest, i)) {
                            gr.addEdge(dest, i, edge2);
                        }
                    }

                }
            }
        } else { //dense graph
            for(int i = 0; i < vertices; i++) {

                while(gr.getAdjList()[i].size() < DENSE_AVG_VERTEX_DEGREE) { //vertex degree is less than 1000
                    int dest = random.nextInt(vertices);
                    if(i != dest) {
                        int weight = random.nextInt(1000) + 1;
                        Edge edge1 = new Edge(i, dest, weight);
                        if (!exists(gr, i, dest)) {
                            gr.addEdge(i, dest, edge1);
                        }
                        //undirected graph
                        Edge edge2 = new Edge(dest, i, weight);
                        if (!exists(gr, dest, i)) {
                            gr.addEdge(dest, i, edge2);
                        }
                    }
                }
            }
        }
        return gr;
    }

    public static boolean exists(Graph gr, int x, int y) {
        ArrayList<Edge> edges = gr.getAdjList()[x];
        for(Edge e : edges) {
            if(e.src == x && e.dest == y) {
                return true;
            }
        }
        return false;
    }


}
