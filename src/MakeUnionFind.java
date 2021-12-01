import java.util.Stack;

/**
 * Class to implement Union-Find data structure used in Kruskal algorithm
 */
public class MakeUnionFind {

    private int[] parent;
    private int[] rank;

    public MakeUnionFind(int v) {
        makeSet(v);
    }

    public void makeSet(int V) {
        parent = new int[V];
        rank = new int[V];
        for(int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int k) {
        int w = k;
        Stack<Integer> s = new Stack<>();
        while(parent[w] != w) {
            s.push(w);
            w = parent[w];
        }
        while(!s.isEmpty()) {
            int u = s.pop();
            parent[u] = w;
        }
        return w;
    }

    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            if (rank[px] > rank[py]) {
                parent[py] = px;
            } else if (rank[py] > rank[px]) {
                parent[px] = py;
            } else {
                parent[py] = px;
                rank[px] = rank[px] + 1;
            }
        }
    }
}
