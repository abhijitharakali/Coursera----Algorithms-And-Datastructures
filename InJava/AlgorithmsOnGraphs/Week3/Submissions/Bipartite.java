/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhijitharakali
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        int[] color = new int[adj.length];
        //write your code here
        for (int i = 0; i<adj.length; i++){
            color[i] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int w = 0; w < adj.length; w++){
            q.clear();
            q.add(w);
            while(q.isEmpty() == false){
                int u = q.remove();
                for (int v:adj[u]){
                    if (color[v] == -1){
                        q.add(v);
                        color[v] = ((color[u] + 1) & 1);
                    }
                }
            }
        }
        for (int j = 0; j < adj.length; j++){
//            if (color[j] == -1){
//                continue;
//            }
            for (int k: adj[j]){
                if ( color[j] == color[k]){
                        return 0;
                    }
            }
        }
        return 1;
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
        System.out.println(bipartite(adj));
    }
}
