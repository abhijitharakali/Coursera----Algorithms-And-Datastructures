import java.util.*;
import java.lang.Math;
//import static java.lang.System.nanoTime;

/*
This code failed test case #3. The bug was hard to find but I nevertheless
found it. Basically, the array visited[] should be split between forward and
reverse directions. I should be checking something like 

if(visited_reverse[uf])

instead of 

if(visited[uf]).

What if I had visited uf in the forward direction before? This should not trigger
a return call to the shortestDistance routine. The fix has worked for this 
particular test case. I need to see if the time contraints are satisfied, and
if there are no more bugs. Up up and awaaaaaaay!
*/

public class DistWithCoords_Ver1{
    
    public static int n;
    public static int m;
    public static int[] x;
    public static int[] y;
    public static double[] df, dr;
    public static boolean[] visited;
    public static ArrayList<Integer> workset;
    public static PriorityQueue<Entry> qf,qr;
    
    public static int[] prevf,prevr;
    
    public static long INFINITY = Long.MAX_VALUE / 4;
    
    public static class Entry implements Comparable<Entry>
    {
        double cost;
        int node;
          
        public Entry(int node,double cost){
            this.cost = cost;
            this.node = node;
        }
         
        public int compareTo(Entry other){
            return cost < other.cost ? -1 : cost > other.cost ? 1 : 0;
        }
    }
    
    public static int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    
    public static long shortestPath(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr) {
        long inf = Long.MAX_VALUE;
        double dist = inf;
        if (workset.isEmpty()){
            return -1;
        }
        int ubest = workset.get(0);
//        double d97 = 0;
//        double d49 = 0;
        for (int u: workset){
//            if(u == 97){
//                System.out.println(97);
//            }
            if(df[u] + dr[u] < dist){
                dist = df[u] + dr[u];
//                if(u == 97){
//                    d97 = dist;
//                }
//                if(u == 49){
//                    d49 = dist;
//                }
                ubest = u;
            }
        }
//        System.out.println(ubest);
//        System.out.println(d97+" "+d49);
        if(dist == inf){
            return -1;
        }
//        ubest = 97;
        long cf = 0;
        long cr = 0;
        
        int uf = prevf[ubest];
        int vf = ubest;
        
        while(uf!=-1){
            cf = cf + get_cost(adjf,costf,uf,vf);
            vf = uf;
            uf = prevf[vf];
        }

        int ur = prevr[ubest];
        int vr = ubest;
        
        while(ur!=-1){
            cr = cr + get_cost(adjr,costr,ur,vr);
            vr = ur;
            ur = prevr[vr];
        }        
        
        return (cf+cr);
    }
    
    public static void visit(int node_src, int node_dest, double dist, int dir){
        if (dir == 1){
            if (df[node_dest] > dist){
                df[node_dest] = dist;
                qf.add(new Entry(node_dest,dist));
                workset.add(node_dest);
                prevf[node_dest] = node_src;
            }
        }
        else if(dir == -1){
            if (dr[node_dest] > dist){
                dr[node_dest] = dist;
                qr.add(new Entry(node_dest,dist));
                workset.add(node_dest);
                prevr[node_dest] = node_src;
            }  
        }
    }
    
    public static void clear(){

        for(int k : workset){
            df[k] = INFINITY;
            dr[k] = INFINITY;
            visited[k] = false;
            prevf[k] = -1;
            prevr[k] = -1;
        }
        workset.clear();
        qf.clear();
        qr.clear();
    }
    
    public static void Process(int dir, int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,int s,int t){
        for (int i = 0; i < adj[node].size(); i++){
            int v = adj[node].get(i);
            int w = cost[node].get(i);
            if(dir == 1){
                visit(node, v, df[node]+w-pf(s,t,node)+pf(s,t,v), dir);
            }
            else if(dir == -1){
                visit(node, v, dr[node]+w-pr(s,t,node)+pr(s,t,v), dir);
            }
        }
    }
    
    public static double pf(int u, int v, int node){
        double dr = Math.sqrt( ( x[u]-x[node] ) * ( x[u]-x[node] ) + ( y[u]-y[node] ) * ( y[u]-y[node] ) );
        double df = Math.sqrt( ( x[v]-x[node] ) * ( x[v]-x[node] ) + ( y[v]-y[node] ) * ( y[v]-y[node] ) );
        return (df-dr)/2;
    }
    
    public static double pr(int u, int v, int node){
        double dr = Math.sqrt( ( x[u]-x[node] ) * ( x[u]-x[node] ) + ( y[u]-y[node] ) * ( y[u]-y[node] ) );
        double df = Math.sqrt( ( x[v]-x[node] ) * ( x[v]-x[node] ) + ( y[v]-y[node] ) * ( y[v]-y[node] ) );
        return (dr-df)/2;
    }    
    
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){
            return 0;
        }
        
        clear();
        
        visit(-1,u, 0, 1);
        visit(-1,v, 0, -1);

        while(!qf.isEmpty() && !qr.isEmpty()){
            Entry ef = qf.poll();
            int uf = ef.node;
            if(df[uf] == INFINITY){
                return -1;
            }
            Process(1, uf, adjf, costf, u, v);
            
            if(visited[uf]){
                return shortestPath(adjf,adjr,costf,costr);
//                return Math.round(shortestPath()+pf(u,v,u)-pf(u,v,v));
            }
            visited[uf] = true;
            
            Entry er = qr.poll();
            int vr = er.node;
            if(dr[vr] == INFINITY){
                return -1;
            }
            Process(-1, vr, adjr, costr, u, v);
            
            if(visited[vr]){
                return shortestPath(adjf,adjr,costf,costr);
//                return Math.round(shortestPath()+pf(u,v,u)-pf(u,v,v));
            }
            visited[vr] = true;
        }
        
        return -1;
    }
        
    public static void main(String args[]) {
        
//        long t0 = System.nanoTime();
        
        int n;
        int m;

        
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        df = new double[n];
        dr = new double[n];
        visited = new boolean[n];
        x = new int[n];
        y = new int[n];
        
        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();
        workset = new ArrayList<>();
        prevf = new int[n];
        prevr = new int[n];
        
        Arrays.fill(df, INFINITY);
        Arrays.fill(dr, INFINITY);
        Arrays.fill(visited, false);
        Arrays.fill(prevf, -1);
        Arrays.fill(prevr, -1);


        ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n];
        
        ArrayList<Long> final_ans = new ArrayList<>();
        
        
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
            adjf[s - 1].add(t - 1);
            costf[s - 1].add(c);
            adjr[t - 1].add(s - 1);
            costr[t - 1].add(c);
        }
        
        
        int qrr = in.nextInt();

        for (int i = 0; i < qrr; i++) {
            int u, v;
            u = in.nextInt()-1;
            v = in.nextInt()-1;
            final_ans.add(query(adjf,adjr,costf,costr,u,v));
        }

        for (int i = 0; i < final_ans.size(); i++){
            System.out.println(final_ans.get(i));
//            long temp = Math.round(final_ans.get(i));
//            System.out.println(temp);
        }
        
//        long t1 = System.nanoTime();
//        System.out.println((t1-t0));
    }
     
}
