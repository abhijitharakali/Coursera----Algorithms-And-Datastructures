import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class SCC_Debug {
    public static int clock = 0;
    public static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj,ArrayList<Integer>[] adjr) {
        //write your code here
        int scc = 0;
        int[] visited = new int[adjr.length];
        int[] post = new int[adjr.length];
        ArrayList<Integer> unvisited = new ArrayList<Integer>();
        for (int i = 0; i < adjr.length; i++){
            visited[i] = 0;
            post[i] = -1;
            unvisited.add(i);
        }
//        for (int v = 0; v < adjr.length; v++){
//            System.out.println(adjr[v]);
//        }
        dfs(adjr,visited,post);
//        for (int v = 0; v < post.length; v++){
//            System.out.println(post[v]);
//        }
        for (int k = 0; k < adj.length; k++){
            visited[k] = 0;
        }
        
        while (unvisited.isEmpty() == false){
//            System.out.println(unvisited);
            int x = unvisited.get(0);
            for (int j:unvisited){
                if (post[j] > post[x]) {
                    x = j;
                }
            }
            if (visited[x] == 0){
//                System.out.println(x);
//                System.out.println(unvisited.size());
                explore_scc(adj, visited, unvisited, x);
                scc = scc + 1;
            }
        }
        return scc;
    }
    
    public static void dfs(ArrayList<Integer>[] adjr,int[] visited, int[] post){
        for (int i = 0; i < adjr.length; i++){
            if (visited[i] == 0){
                explore(adjr,visited,post,i);
            }
        }
        
    }
    
    public static void explore(ArrayList<Integer>[] adjr,int[] visited, int[] post, int x){
        visited[x] = 1;
        clock = clock + 1;
        if (adjr[x].isEmpty() == false){
            for (int i:adjr[x]){
                if (visited[i] == 0){
                    explore(adjr, visited, post, i);
                }
            }
        }
        post[x] = clock;
//        System.out.println(x);
//        System.out.println(post[x]);
        clock = clock + 1;
    }
    
    public static void explore_scc(ArrayList<Integer>[] adj,int[] visited, ArrayList<Integer> unvisited, int x){
        visited[x] = 1;
//        System.out.println(x);
//        System.out.println(unvisited);
        unvisited.removeAll(Arrays.asList(x));
//        System.out.println(unvisited);

        if (adj[x].isEmpty() == false){
            for (int i:adj[x]){
                if (visited[i] == 0){
//                    System.out.print(x);
//                    System.out.println(i);
                    explore_scc(adj, visited, unvisited, i);
                }
            }
        }
//        System.out.println();
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



