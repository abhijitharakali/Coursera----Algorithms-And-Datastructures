// This program uses Prim's Algorithm'

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.0;
        //write your code here
        
        int n = x.length;
        if (n == 1){
            return result;
        }
        int[] cost = new int[n];
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        Random ran = new Random();
        int u0 = ran.nextInt(n);
        
        for (int i = 0; i < n; i++){
            if (i == u0){
                cost[i] = 0;
            }
            else{
                cost[i] = (x[i]-x[u0])*(x[i]-x[u0])+(y[i]-y[u0])*(y[i]-y[u0]);
                nodes.add(i);
            }
        }
        
        

        while (nodes.isEmpty() == false){
            int temp = Integer.MAX_VALUE;
            int prev = u0;
            
            for (int u:nodes){
                if(cost[u]<temp){
                    temp = cost[u];
                    u0 = u;
                }
            }

            for(int v: nodes){
                if (v != u0){
                    if (cost[v] > (x[v]-x[u0])*(x[v]-x[u0])+(y[v]-y[u0])*(y[v]-y[u0])){
                        cost[v] = (x[v]-x[u0])*(x[v]-x[u0])+(y[v]-y[u0])*(y[v]-y[u0]);
                    }
                }
            }
                        
            for (int v = 0; v < nodes.size(); v++){
            
                if (nodes.get(v) == u0){
                    nodes.remove(v);
                }
            
            }
        }
        for (int j = 0; j < n; j++){
            result = result + Math.sqrt(cost[j]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}


