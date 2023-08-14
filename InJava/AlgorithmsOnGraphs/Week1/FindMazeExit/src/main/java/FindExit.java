import java.util.ArrayList;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhijitharakali
 */
public class FindExit {
    private static int reach(ArrayList<Integer>[] adj,int[] visited, int x, int y) {
        if (visited[x-1] == 0){
            visited[x-1] = 1;
            if (x == y){
                return 1;
            }
            if(adj[x-1].isEmpty() == false){
                for (int i: adj[x-1]){
                    if (i == (y-1)) return 1;
                    else if (visited[i] == 0){
                        if (reach(adj, visited, (i+1),y) == 1){
                            return 1;
                        }
                    }
                }
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[] visited = new int[adj.length];
        for (int j = 0; j < adj.length; j++){
            visited[j] = 0;
        }
        System.out.println(reach(adj,visited, x, y));
    }

}
