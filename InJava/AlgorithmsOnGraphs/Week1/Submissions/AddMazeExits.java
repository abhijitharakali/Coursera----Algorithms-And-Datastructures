import java.util.ArrayList;
import java.util.Scanner;

public class AddMazeExits {
    
    private static int number_of_trees(ArrayList<Integer>[] adj){
        int ntrees = 0;
        int[] visited = new int[adj.length];
        for (int j = 0; j < adj.length; j++){
            visited[j] = 0;
        }
        for (int k = 0; k < adj.length; k++){
            if (visited[k] == 0){
                explore(adj, visited, k);
                ntrees = ntrees + 1;
            }
        }
        return ntrees;
    }
    
    private static void explore(ArrayList<Integer>[] adj,int[] visited, int x){
        visited[x] = 1;
        if (adj[x].isEmpty() == false){
            for (int i:adj[x]){
                if (visited[i] == 0){
                    explore(adj, visited, i);
                }
            }
        }
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
        System.out.println(number_of_trees(adj));
//        System.out.println(adj[3].isEmpty());
    }

}