import java.util.*;
import java.lang.Math;

public class checkInputs{
    public static int[] x,y;
    
        public static double dE(int u, int v){
            return Math.sqrt( ( x[u]-x[v] ) * ( x[u]-x[v] ) + ( y[u]-y[v] ) * ( y[u]-y[v] ) );
        }
    public static int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }        
        public static void main(String args[]) {
        
        
        int n;
        int m;

        
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        x = new int[n];
        y = new int[n];
        


        ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n];
        
        
        
        for (int i = 0; i < n; i++) {
            adjf[i] = new ArrayList<>();
            costf[i] = new ArrayList<>();
            adjr[i] = new ArrayList<>();
            costr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) { 
            int xi, yi;
            xi = in.nextInt();
            yi = in.nextInt();
            x[i] = xi;
            y[i] = yi;
        }        
        
        for (int i = 0; i < m; i++) {
            int s, t, c;
            s = in.nextInt();
            t = in.nextInt();
            c = in.nextInt();
            if(adjf[s-1].contains(t-1)){
                int fdx = adjf[s-1].indexOf(t-1);
                if(fdx != 1 ){
                    int cp =costf[s-1].get(fdx);
                    if (cp > c){
                        costf[s-1].set(fdx, c);
                        int sdx = adjr[t-1].indexOf(s-1);
                        assert sdx != -1: "Error";
                        costr[t-1].set(sdx, c);
                    }
                }
            }
            else {
                adjf[s - 1].add(t - 1);
                costf[s - 1].add(c);
                adjr[t - 1].add(s - 1);
                costr[t - 1].add(c);
            }
            
        }
        
        
        int qrr = in.nextInt();

        for (int i = 0; i < qrr; i++) {
            int u, v;
            u = in.nextInt()-1;
            v = in.nextInt()-1;
        }
        for(int i = 0; i < n; i++){
            if(!adjf[i].isEmpty()){
               for(int j:adjf[i]){
                   int w = get_cost(adjf,costf,i,j);
                   System.out.println(w+" "+dE(i,j));
               } 
            }
        }
    }
}
