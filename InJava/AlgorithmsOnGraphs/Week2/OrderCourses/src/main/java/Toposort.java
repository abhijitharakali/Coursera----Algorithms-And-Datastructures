import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Basically dfs is a function similar to the explore function in the previous
// assignments. It starts from 0th node, keeps going till it hits a sink, which
// is assured by the input giver that there won't be cycles so a sink is bound
// to occur. Then we start to place these nodes in the order arraylist starting
// from the sink back to where we started, then move on to the next node
// repeating this process, noting that you'd never run dfs on nodes that have
// previously been explored or dfsed before.

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        int n = adj.length;
        for (int k = 0; k<n; k++){
            used[k] = 0;
        }
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
        for (int l = 0; l<n; l++){
            if (used[l] == 0){
                dfs(adj,used,order,l);
            }
        }
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
      //write your code here
      used[s] = 1;
      for (int i: adj[s]){
          if (used[i] == 0){
              dfs(adj,used,order,i);
          }
      }
      order.add(0,s);
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}


