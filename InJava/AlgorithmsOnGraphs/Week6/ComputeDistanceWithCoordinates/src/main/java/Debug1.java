import java.util.*;

/*
In this code, I figure out the shortest path from node 66 to node 77 which is
where the code is failing. I do that by using bidirectional Dijkstra. Next,
I will figure out the path that buggy A* code finds. Hopefully, I will know
where the bug exists.
*/

public class Debug1{
    
    public static int n;
    public static int m;
    public static long[] df, dr;
    public static boolean[] visited;
    public static ArrayList<Integer> workset,path;
    public static PriorityQueue<Entry> qf,qr;
    public static int[] prevf,prevr;
    
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

    public static void printShortestPath(int ubest,ArrayList<Integer>[] adjf, ArrayList<Integer>[] costf){
        path.clear();
        int ur = prevr[ubest];
        int vr = ubest;
        
        while(ur!=-1){
            path.add(ur);
            vr = ur;
            ur = prevr[vr];
        }    
        Collections.reverse(path);
        
        path.add(ubest);
        
        int uf = prevf[ubest];
        int vf = ubest;
        
        while(uf!=-1){
            path.add(uf);
            vf = uf;
            uf = prevf[vf];
        }
    
        Collections.reverse(path);
        
        System.out.println();
        int nn = path.size();
        for(int i = 0;i < nn-1;i++){
            int a = path.get(i);
            int b = path.get(i+1);
            int c = get_cost(adjf,costf,a,b);
            System.out.println(a + " " + b + " " + c);
        }
        System.out.println();
    }

    
    public static long shortestPath(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr) {
        long inf = Long.MAX_VALUE;
        long dist = inf;
        if (workset.isEmpty()){
            return -1;
        }
        int ubest = workset.get(0);
        for (int u: workset){
//            System.out.println(u);
            if(df[u] + dr[u] < dist){
                dist = df[u] + dr[u];
                ubest = u;
            }
            if(u == 8){
//                System.out.println("9 dist: " + (df[u]+dr[u])+" "+dist);
//                printShortestPath(u,adjf,costf);
            }
            if(u == 2){
//                System.out.println("3 dist: " + (df[u]+dr[u])+" "+dist);
//                printShortestPath(u,adjf,costf);
            }
        }
        if(dist == inf){
            return -1;
        }
        
        int ur = prevr[ubest];
        int vr = ubest;
        
        while(ur!=-1){
            vr = ur;
            ur = prevr[vr];
        }             
        

        int uf = prevf[ubest];
        int vf = ubest;
        while(uf!=-1){
            vf = uf;
            uf = prevf[vf];            
        }
        printShortestPath(ubest,adjf,costf);
        System.out.println(ubest);
        return dist;
    }
    
    public static void visit(int node_src, int node_dest, long dist, int dir){
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
                visit(node, v, df[node]+w, dir);
            }
            else if(dir == -1){
                visit(node, v, dr[node]+w, dir);
            }
        }
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
            Process(1, uf, adjf, costf);
            
            if(visited[uf]){
                return shortestPath(adjf,adjr,costf,costr);
            }
            visited[uf] = true;
            
            Entry er = qr.poll();
            int vr = er.node;
            if(dr[vr] == INFINITY){
                return -1;
            }
            Process(-1, vr, adjr, costr);
            
            if(visited[vr]){
                return shortestPath(adjf,adjr,costf,costr);
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
        path = new ArrayList<>();
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
        int[] x = new int[n];
        int[] y = new int[n];
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
        }
    }
     
}
