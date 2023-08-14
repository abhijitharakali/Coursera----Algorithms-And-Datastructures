import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int find_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, long maxd) {
        long[] dist = new long[adj.length];
        int[] prev = new int[adj.length];
        for (int i = 0; i < adj.length; i++){
            dist[i] = maxd;
            prev[i] = -1;
        }
        dist[0] = 0;
        for(int j = 0; j < adj.length; j++){
            for (int k  = 0; k < adj.length; k++){
                if(adj[k].isEmpty() == false){
                    for(int l : adj[k]){
                        if (dist[l] > dist[k] + find_cost(adj,cost,k,l)){
                            if (j == adj.length - 1){
                                return 1;
                            }
                            dist[l] = dist[k] + find_cost(adj,cost,k,l);
                        }
                    }
                }
            }
        }
        // write your code here
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        long maxw = 0;
        int edges = 0;
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
            edges = edges + 1;
            if (w > maxw){
                maxw = w;
            }
        }
        long maxd = maxw*m + 1;
        System.out.println(negativeCycle(adj, cost, maxd));
    }
}


