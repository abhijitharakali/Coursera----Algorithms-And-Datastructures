import java.util.*;

//Main lesson learnt: Queue in all the nodes. See below.

public class Dijkstra {
    private static int find_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    private static int min_indx(long[] dist, Queue<Integer> q) {
        int ans = q.peek();
        for (int i: q){
            if (dist[ans] > dist[i]){
                ans = i;
            }
        }
        return ans;
    }
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t, long maxd) {
        int[] found = new int[adj.length];
        long[] dist = new long[adj.length];
        int[] reached = new int[adj.length];
        Queue<Integer> q = new LinkedList<>();
        for (int j = 0; j < adj.length; j++){
            found[j] = 0;
            dist[j] = maxd;
            reached[j] = 0;
            if (j != s){
                q.add(j);
            }
        }
        dist[s] = 0;
        found[s] = 1;
        reached[s] = 1;
        
        int current = s;
        
        while (adj[current].isEmpty() == false) {
            for (int v:adj[current]){
                if (found[v] == 0){
                    long temp = dist[current] + find_cost(adj,cost,current,v);
                    if (dist[v] > temp){
                        dist[v] = temp;
                    }
//                    q.add(v); // This was the bug I struggled with

// You need to initialy enqueue all the nodes except the starting node.
// Then you need to remove the ones whose minimum distance is found.
                }
                reached[v] = 1;
            }
            current = min_indx(dist,q);
            if (current == t){
                return dist[t];
            }
            found[current] = 1;
            Iterator<Integer> itr = q.iterator();
            while(itr.hasNext()){
                int temp = itr.next();
                if (temp == current){
                    itr.remove();
                    break;
                }
            }
//            q.clear(); // This was the bug I struggled with
        }
        if (reached[t] == 0){
            return -1;
        }
        else{
            return dist[t];
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
            edges = edges + 1;
            cost[x - 1].add(w);
            if (w > maxw){
                maxw = w;
            }
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        long maxd = maxw*m + 1;
        System.out.println(distance(adj, cost, x, y, maxd));
    }
}


