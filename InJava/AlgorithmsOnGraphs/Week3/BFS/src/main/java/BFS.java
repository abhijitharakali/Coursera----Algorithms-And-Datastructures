import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int[] dist = new int[adj.length];
        //write your code here
        for (int i = 0; i<adj.length; i++){
            if (i == s){
                dist[i] = 0;
            }
            else{
                dist[i] = -1;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while(q.isEmpty() == false){
            int u = q.remove();
            for (int v:adj[u]){
                if (dist[v] == -1){
                    q.add(v);
                    dist[v] = dist[u] + 1;
                    if (v == t){
                        return dist[v];
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}


