import java.util.ArrayList;
import java.util.Scanner;

// Explore(node) marks all nodes going out of it, as visited, and explores all
// going out of it. However, it does not mark the input node "node" itself
// as visited. Hence, if after exploring a node, if that node ends up being
// visited, you know that there is a cycle.

// After the completion of exploring this node, move on to the next node
// and repeat the process. Note that each time, the visited array is 
// re-defined to be zero for all nodes as this process is repeated for each
// node afresh.

public class CheckForCycles {
    private static void explore(ArrayList<Integer>[] adj,int[] visited, int x){
//        visited[x] = 1;
        if (adj[x].isEmpty() == false){
            for (int i:adj[x]){
                if (visited[i] == 0){
                    visited[i] = 1;
                    explore(adj, visited, i);
                }
            }
        }
    }
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
        for (int i = 0; i < adj.length; i++){
            int[] visited = new int[adj.length];
            for (int j = 0; j < adj.length; j++){
                visited[j] = 0;
            }
            explore(adj,visited,i);
            if (visited[i] == 1){
                return 1;
            }
        }
        return 0;
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
        }
        System.out.println(acyclic(adj));
    }
}
