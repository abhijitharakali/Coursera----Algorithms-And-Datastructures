import static java.lang.System.nanoTime;
import java.util.*;

public class Investigate_Initialization_Time{
        public static void main(String args[]) {
            int n = 10000;
            
            long t0 = System.nanoTime();
            
            long c1 = Long.MAX_VALUE / 4;
            long[] d1 = new long[n];
            Arrays.fill(d1, c1);
            
            long t1 = System.nanoTime();
            
            long c2 = Long.MAX_VALUE / 4;
            long[] d2 = new long[n];
            for(int i = 0; i < n; i++){
                d2[i] = c2;
            }
            
            long t2 = System.nanoTime();
            
            boolean[] proc = new boolean[n];
            Arrays.fill(proc, false);
            
            long t3 = System.nanoTime();
            
            Long c3 = Long.MAX_VALUE / 4;
            Long[][] distance = new Long[][] {new Long[n], new Long[n]};
            for (int i = 0; i < n; ++i) {
                distance[0][i] = distance[1][i] = c3;
            }            
            
            long t4 = System.nanoTime();
            
            ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n];
            ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n];
            ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
            ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n];
        
//            ArrayList<Long> final_ans = new ArrayList<>();
        
        
            for (int i = 0; i < n; i++) {
                adjf[i] = new ArrayList<>();
                costf[i] = new ArrayList<>();
                adjr[i] = new ArrayList<>();
                costr[i] = new ArrayList<>();
            }
            
            long t5 = System.nanoTime();
            
            ArrayList<Integer>[][] adj;
            ArrayList<Integer>[][] cost;
            adj = (ArrayList<Integer>[][])new ArrayList[2][];
            cost = (ArrayList<Integer>[][])new ArrayList[2][];
            for (int side = 0; side < 2; ++side) {
                adj[side] = (ArrayList<Integer>[])new ArrayList[n];
                cost[side] = (ArrayList<Integer>[])new ArrayList[n];
                for (int i = 0; i < n; i++) {
                    adj[side][i] = new ArrayList<Integer>();
                    cost[side][i] = new ArrayList<Integer>();
                }
            }
            
            long t6 = System.nanoTime();
            
            long c4 = -1;
            long[] d3 = new long[n];
            Arrays.fill(d3, c4);            
            
            long t7 = System.nanoTime();
            
            ArrayList<Long>[] minmatrix = (ArrayList<Long>[])new ArrayList[n];
            
            for(int i = 0; i < n; i++){
                minmatrix[i] = new ArrayList<>();
                for(int j = 0; j < n; j++){
                    minmatrix[i].add(-1L);
                }
                assert minmatrix.length == n;
            }
            
            long c5 = Long.MAX_VALUE / 4;
            
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    minmatrix[i].set(j, -1L);
                }
            }
            long t8 = System.nanoTime();
            
            System.out.println(t1-t0);
            System.out.println(t2-t1);
            System.out.println(t3-t2);
            System.out.println(t4-t3);
            System.out.println(t5-t4);
            System.out.println(t6-t5);
            System.out.println(t7-t6);
            System.out.println((t8-t7)/1000000000);
        }
        
        
}
