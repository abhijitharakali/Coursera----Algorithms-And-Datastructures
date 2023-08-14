import java.util.*;
/*
In this code, I have tried to impliment the code using priority queues for the
distance variable. However, I realized a mistake I made in Ver2, jumped to Ver4, 
then saw that Ver4 fails the time limit criterion anyway. So I am now going to
try and impliment the priority queue version of this code next.
*/
public class FS_Ver3{
    
    public static int n;
    public static int m;
    public static long[] df, dr;
    public static boolean[] procf;
    public static boolean[] procr;
    
//    static int[] x;
//    static int[] y;
//    static ArrayList<Integer>[] adjf;
//    static ArrayList<Integer>[] costf;
//    static ArrayList<Integer>[] adjr;
//    static ArrayList<Integer>[] costr;
    public static class Entry implements Comparable<Entry>
    {
            long cost;
            int node;
          
            public Entry(int node,long cost)
            {
                this.cost = cost;
                this.node = node;
            }
         
            public int compareTo(Entry other)
            {
                return cost < other.cost ? -1 : cost > other.cost ? 1 : 0;
            }
    }
    
    public static int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    
    public static Long shortestPath(ArrayList<Integer> workset, PriorityQueue<Entry> qf, PriorityQueue<Entry> qr) {
        Long inf = Long.MAX_VALUE;
        Long dist = inf;
        if (workset.isEmpty()){
            return -1L;
        }
//        int ubest = workset.get(0);
        for (int u: workset){
            if(df[u] + dr[u] < dist){
//                ubest = u;
                dist = df[u] + dr[u];
            }
        }
        clear(workset, qf, qr);
        return dist;
    }
    
    
    public static void clear(ArrayList<Integer> workset, PriorityQueue<Entry> qf, PriorityQueue<Entry> qr){
        Long INFINITY = Long.MAX_VALUE / 4;
        for(int k: workset){
            df[k] = INFINITY;
            procf[k] = false;
            dr[k] = INFINITY;
            procr[k] = false;
        }
        workset.clear();
        qf.clear();
        qr.clear();
    }
    
    public static Long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){
            return 0L;
        }
        int nn = adjf.length;
        
        
        Long INFINITY = Long.MAX_VALUE / 4;

        df[u] = 0L;
        dr[v] = 0L;
        procf[u] = true;
        procr[v] = true;
        
        PriorityQueue<Entry> qf,qr;
        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();

        
        ArrayList<Integer> workset = new ArrayList<>();
        for(int i = 0; i < nn; i++){
            qf.add(new Entry(i,df[i]));
            qr.add(new Entry(i,dr[i]));
        }
        
        
        while(!qf.isEmpty() && !qr.isEmpty()){
            Entry ef = qf.poll();
            int uf = ef.node;
            long dff = ef.cost;
            if(dff == INFINITY){
                clear(workset,qf,qr);
                return -1L;
            }
            if(adjf[uf].isEmpty() == false){
                for(int vf: adjf[uf]){
                    int w = get_cost(adjf,costf,uf,vf);
                    if(df[vf] > df[uf] + w){
                        df[vf] = df[uf] + w;
                    }
                }
            }
            procf[uf] = true;
            workset.add(uf);
            if(procr[uf]){
                return shortestPath(workset, qf, qr);
            }
            
            Entry er = qr.poll();
            int vr = er.node;
            long drr = er.cost;
            if (drr == INFINITY){
                clear(workset, qf, qr);
                return -1L;
            }
            if(adjr[vr].isEmpty() == false){
                for(int ur: adjr[vr]){
                    int w = get_cost(adjr,costr,vr,ur);
                    if(dr[ur] > dr[vr] + w){
                        dr[ur] = dr[vr] + w;
                    }
                }
            }
            procr[vr] = true;
            workset.add(vr);
            if(procf[vr]){
                return shortestPath(workset, qf, qr);
            }
        }
        
        clear(workset, qf, qr);
        return -1L;
    }
        
    public static void main(String args[]) {
        
        Long INFINITY = Long.MAX_VALUE / 4;
        int n;
        int m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        df = new long[n];
        dr = new long[n];
        procf = new boolean[n];
        procr = new boolean[n];
        
        Arrays.fill(df, INFINITY);
        Arrays.fill(dr, INFINITY);
        Arrays.fill(procf, false);
        Arrays.fill(procr,false);
        
//        x = new int[n];
//        y = new int[n];

        ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n];
        
        ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n];
        
        ArrayList<Long> final_ans = new ArrayList<>();
        
//        for (int i = 0; i < n; i++) { 
//            x[i] = in.nextInt();
//            y[i] = in.nextInt();
//        }
        
        for (int i = 0; i < n; i++) {
            adjf[i] = new ArrayList<>();
            costf[i] = new ArrayList<>();
            adjr[i] = new ArrayList<>();
            costr[i] = new ArrayList<>();
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

        int qr = in.nextInt();

        for (int i = 0; i < qr; i++) {
            int u, v;
            u = in.nextInt()-1;
            v = in.nextInt()-1;
            final_ans.add(query(adjf,adjr,costf,costr,u,v));
        }
        
        for (int i = 0; i < final_ans.size(); i++){
            System.out.println(final_ans.get(i));
        }
    }
    
}


