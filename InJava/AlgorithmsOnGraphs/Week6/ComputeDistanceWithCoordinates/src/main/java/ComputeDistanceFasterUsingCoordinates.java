import java.util.*;
import java.lang.Math;

public class ComputeDistanceFasterUsingCoordinates{
    
    static int n;
    static int m;
    static int[] x;
    static int[] y;
    static ArrayList<Integer>[] adjf;
    static ArrayList<Integer>[] costf;
    static ArrayList<Integer>[] adjr;
    static ArrayList<Integer>[] costr;
    
    private static Double query(ArrayList<Integer>[] adjf,ArrayList<Integer>[] costf,ArrayList<Integer>[] adjr,ArrayList<Integer>[] costr,int[] x,int[] y,int u,int v){
        
        
        
        return -1.0;
    }
        
    public static void main(String args[]) {
        
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        x = new int[n];
        y = new int[n];
        
        adjf = (ArrayList<Integer>[])new ArrayList[n];
        costf = (ArrayList<Integer>[])new ArrayList[n];
        
        adjr = (ArrayList<Integer>[])new ArrayList[n];
        costr = (ArrayList<Integer>[])new ArrayList[n];
        
        ArrayList<Double> final_ans = new ArrayList<Double>();
        
        for (int i = 0; i < n; i++) { 
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int s, t, c;
            s = in.nextInt();
            t = in.nextInt();
            c = in.nextInt();
            adjf[s-1].add(t-1);
            costf[s-1].add(c);
            adjr[t-1].add(s-1);
            costr[t-1].add(c);
        }

        int qr = in.nextInt();

        for (int i = 0; i < qr; i++) {
            int u, v;
            u = in.nextInt();
            v = in.nextInt();
            final_ans.add(query(adjf,costf,adjr,costr,x,y,u,v));
        }
        
        for (int i = 0; i < final_ans.size(); i++){
            System.out.println(final_ans.get(i));
        }
    }
    
}
