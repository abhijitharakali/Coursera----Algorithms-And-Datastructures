import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj,ArrayList<Integer>[] adjr) {
        //write your code here
        int scc = 0;
        int[] visited = new int[adjr.length];
        for (int j = 0; j < adjr.length; j++){
            visited[j] = 0;
        }
        ArrayList<Integer> order = new ArrayList<Integer>();
        for (int l = 0; l < adjr.length; l++){
            if (visited[l] == 0){
                explore(adjr,visited,order,l);
            }
        }
        Collections.reverse(order);
        for (int j = 0; j < adjr.length; j++){
            visited[j] = 0;
        }
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int v: order){
            if (visited[v] == 0){
                explore(adj,visited,temp,v);
                scc = scc + 1;
            }
        }
        return scc;
    }
    
    
    private static void explore(ArrayList<Integer>[] adj,int[] visited, ArrayList<Integer> order, int x){
        visited[x] = 1;
        if (adj[x].isEmpty() == false){
            for (int i:adj[x]){
                if (visited[i] == 0){
                    explore(adj, visited, order, i);
                }
            }
        }
        order.add(x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            adjr[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adjr[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj,adjr));
    }
}


