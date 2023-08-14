import java.util.*;

public class ShortestPaths {
    
    private static int find_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    
    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] dist, int[] reachable, int[] shortest) {
        //write your code here
        Queue<Integer> q = new LinkedList<>();
        dist[s] = 0;
        reachable[s] = 1;
        ArrayList<Integer> reachable_nodes = new ArrayList<Integer>();
        
        q.add(s);
        while(q.isEmpty() == false){
            int u = q.remove();
            reachable[u] = 1;
            for (int v:adj[u]){
                if(reachable[v] == 0){
                    q.add(v);
                }
            }
        }
        for(int i = 0; i < adj.length; i++){
            if(reachable[i] == 1){
                reachable_nodes.add(i);
            }
        }
        q.clear();
        
        for(int j = 0; j < adj.length; j++){
//            for (int k = 0; k < adj.length; k++){ // This was a bug. More
//                                                  // details in the end.

            for (int k : reachable_nodes){
                if(adj[k].isEmpty() == false){
                    for(int l : adj[k]){
                        if (dist[l] > dist[k] + find_cost(adj,cost,k,l)){
                            if (j == adj.length-1){
                                q.add(l);
                            }
                            else{
                                dist[l] = dist[k] + find_cost(adj,cost,k,l);
                            }
                        }
                    }
                }
            }
        }
        while(q.isEmpty() == false){
            int u = q.remove();
            shortest[u] = 0;
            for (int v:adj[u]){
                if(shortest[v] == 1){
                    q.add(v);
                }
                shortest[v] = 0;
            }
        }
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        long maxd = maxw*m + 1;
        for (int i = 0; i < n; i++) {
            distance[i] = maxd;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

// Details about the bug mentioned prevously:

//  We need iterate over nodes that are reachable from s, and not from all
//  the nodes. To catch this bug, give a graph  input where there
//  is a cycle at a node t which is unreachable from s, but there's a path
//  from s to another node u, and a path from t to u. The output should not be
//  a '-' if there is no other cycle connecting u and also s.

//  Example:

//  6 8
//  2 3 -1
//  3 2 -1
//  1 4 1
//  1 5 1
//  1 6 1
//  2 4 1
//  2 5 1
//  2 6 1
//  1