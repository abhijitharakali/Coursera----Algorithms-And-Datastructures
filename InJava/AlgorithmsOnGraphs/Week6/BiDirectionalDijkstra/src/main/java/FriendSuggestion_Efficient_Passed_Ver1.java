import java.util.*;

/*
This code barely passes the test. I will try to see if I can make the code 
faster.
*/

public class FriendSuggestion_Efficient_Passed_Ver1{
    
    public static int n;
    public static int m;
    public static long[] df, dr;
    public static boolean[] visited;
    public static ArrayList<Integer> workset;
    public static PriorityQueue<Entry> qf,qr;
    
    public static long INFINITY = Long.MAX_VALUE / 4;
    
    public static class Entry implements Comparable<Entry>
    {
        long cost;
        int node;
          
        public Entry(int node,long cost){
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
    
    public static long shortestPath() {
        long inf = Long.MAX_VALUE;
        long dist = inf;
        if (workset.isEmpty()){
            return -1;
        }
        int ubest = workset.get(0);
        for (int u: workset){
            if(df[u] + dr[u] < dist){
                dist = df[u] + dr[u];
                ubest = u;
            }
        }
        if(dist == inf){
            return -1;
        }
//        System.out.println(ubest);
        return dist;
    }
    
    public static void visit(int node, long dist, int dir){
        if (dir == 1){
            if (df[node] > dist){
                df[node] = dist;
                qf.add(new Entry(node,dist));
                workset.add(node);
            }
        }
        else if(dir == -1){
            if (dr[node] > dist){
                dr[node] = dist;
                qr.add(new Entry(node,dist));
                workset.add(node);
            }  
        }
    }
    
    public static void clear(){

        for(int k : workset){
            df[k] = INFINITY;
            dr[k] = INFINITY;
            visited[k] = false;
        }
        workset.clear();
        qf.clear();
        qr.clear();
    }
    
    public static void Process(int dir, int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost){
        for (int i = 0; i < adj[node].size(); i++){
            int v = adj[node].get(i);
            int w = cost[node].get(i);
            if(dir == 1){
                visit(v, df[node]+w, dir);
            }
            else if(dir == -1){
                visit(v, dr[node]+w, dir);
            }
        }
    }
    
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){
            return 0;
        }
        
        clear();
        
        visit(u, 0, 1);
        visit(v, 0, -1);

        while(!qf.isEmpty() && !qr.isEmpty()){
            Entry ef = qf.poll();
            int uf = ef.node;
            if(df[uf] == INFINITY){
                return -1;
            }
            Process(1, uf, adjf, costf);
            
            if(visited[uf]){
                return shortestPath();
            }
            visited[uf] = true;
            
            Entry er = qr.poll();
            int vr = er.node;
            if(dr[vr] == INFINITY){
                return -1;
            }
            Process(-1, vr, adjr, costr);
            
            if(visited[vr]){
                return shortestPath();
            }
            visited[vr] = true;
        }
        
        return -1;
    }
        
    public static void main(String args[]) {
        
        int n;
        int m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        df = new long[n];
        dr = new long[n];
        visited = new boolean[n];
        
        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();
        workset = new ArrayList<>();
        
        Arrays.fill(df, INFINITY);
        Arrays.fill(dr, INFINITY);
        Arrays.fill(visited, false);


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
        }
    }
     
}
