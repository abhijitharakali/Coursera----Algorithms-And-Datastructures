import java.util.*;

public class CheckBullshit{
    
    public static int n;
    public static int m;
    public static int cntf,cntr;
    public static long[] df, dr;
    public static ArrayList<Integer> workset,path;
    public static boolean[] wf,wr,visf,visr;
    public static PriorityQueue<Entry> qf,qr;
    public static int[] prevf,prevr;
    public static ArrayList<Integer>[] adjCache;
    public static ArrayList<Long>[] costCache;
    public static ArrayList<Integer>[] adjq;
    
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
    public static void updateCache(int ubest,ArrayList<Integer>[] adjf, ArrayList<Integer>[] costf,long distuv){
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
        
        int np = path.size();
        long di,dj;
        for(int i = 0; i < np; i++){
            int ui = path.get(i);
            
            if(!adjq[ui].isEmpty()){
                
                if(wf[ui]){
                    di = df[ui];
                }
                else{
                    di = distuv - dr[ui];
                }
                for(int j = i; j < np; j++ ){                    
                    int vj = path.get(j);
                    
                    if(adjq[ui].contains(vj)){
                        
                        if(wf[vj]){
                            dj = df[vj];
                        }
                        else{
                            dj = distuv - dr[vj];
                        }
                        if(!adjCache[ui].contains(vj)){
                            adjCache[ui].add(vj);
                            costCache[ui].add(dj-di);
                        }
                    }
                }
            }
        }
    }
    
    public static long shortestPath(ArrayList<Integer>[] adjf,ArrayList<Integer>[] costf) {
        long inf = Long.MAX_VALUE / 4;
        long dist = inf;
        if (workset.isEmpty()){
            return -1;
        }
        int ubest = workset.get(0);
        if(cntf > cntr){
            for (int u: workset){
                if((df[u] + dr[u] < dist) && (wr[u] )){
                    dist = df[u] + dr[u];
                    ubest = u;
                }
            }
        }
            
        else {
            for (int u: workset){
                if((df[u] + dr[u] < dist) && (wf[u])){
                dist = df[u] + dr[u];
                ubest = u;
                }
            }
        }
        
        
        if(dist == inf){
            return -1;
        }
        updateCache(ubest,adjf,costf,dist);
        
        return dist;
    }
    
    public static void visit(int node, long dist, int dir,int prev_node){
        if (dir == 1){
            if (df[node] > dist){
                df[node] = dist;
                if(visf[node]){
                    qf.remove(new Entry(node,df[node]));
                }
                qf.add(new Entry(node,dist));
                workset.add(node);
                visf[node] = true;
                prevf[node] = prev_node;
            }
        }
        else if(dir == -1){
            if (dr[node] > dist){
                dr[node] = dist;
                if(visr[node]){
                    qr.remove(new Entry(node,dr[node]));
                }
                qr.add(new Entry(node,dist));
                workset.add(node);
                visr[node] = true;
                prevr[node] = prev_node;
            }  
        }
    }
    
    public static void clear(){

        for(int k : workset){
            df[k] = dr[k] = INFINITY;
            wf[k] = wr[k] = visf[k] = visr[k] = false;
        }
        workset.clear();
        cntf = 0;
        cntr = 0;
        qf.clear();
        qr.clear();
    }
    
    public static void Process(int dir, int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost){
        for (int i = 0; i < adj[node].size(); i++){
            int v = adj[node].get(i);
            if((dir == 1) && (wf[v] == false)){
                int w = cost[node].get(i);
                visit(v, df[node]+w, dir,node);
            }
            else if((dir == -1) && wr[v] == false){
                int w = cost[node].get(i);
                visit(v, dr[node]+w, dir, node);
            }
        }
    }
    public static void updateCachef(int u,int uf){
        if(!adjCache[u].contains(uf)){
            adjCache[u].add(uf);
            costCache[u].add(df[uf]);
        }
    }
    public static void updateCacher(int vr,int v){
        if(!adjCache[vr].contains(v)){
            adjCache[vr].add(v);
            costCache[vr].add(dr[vr]);
        }
    }    
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){
            return 0;
        }
        if(adjCache[u].contains(v)){
            int indx = adjCache[u].indexOf(v);
            return costCache[u].get(indx);
        }
        clear();
        
        visit(u, 0, 1, -1);
        visit(v, 0, -1, -1);
        
        
        while(!qf.isEmpty() && !qr.isEmpty()){
            Entry ef = qf.poll();
            int uf = ef.node;
            if(df[uf] == INFINITY){
                return -1;
            }         
            if(wf[uf] == false){
                wf[uf] = true;
                if(adjq[u].contains(uf)){
                    updateCachef(u,uf);
                }               
                cntf = cntf + 1;
                Process(1, uf, adjf, costf);

                if(wr[uf]){
                    return shortestPath(adjf,costf);
                }
            }
            Entry er = qr.poll();
            int vr = er.node;
            if(dr[vr] == INFINITY){
                return -1;
            }           
            if(wr[vr] == false){
                wr[vr] = true;
                if(adjq[vr].contains(v)){
                    updateCacher(vr,v);
                }
                cntr = cntr + 1;
                Process(-1, vr, adjr, costr);

                if(wf[vr]){
                    return shortestPath(adjf,costf);
                }
            }
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
        
        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();
        workset = new ArrayList<>();
        path = new ArrayList<>();
        wf = new boolean[n];
        wr = new boolean[n];
        visf = new boolean[n];
        visr = new boolean[n];
        prevf = new int[n];
        prevr = new int[n];
        
        Arrays.fill(df, INFINITY);
        Arrays.fill(dr, INFINITY);
        Arrays.fill(wf, false);
        Arrays.fill(wr, false);
        Arrays.fill(visf, false);
        Arrays.fill(visr, false);
        Arrays.fill(prevf, -1);
        Arrays.fill(prevr, -1);
        
        ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n];
        
        adjCache = (ArrayList<Integer>[])new ArrayList[n];
        costCache = (ArrayList<Long>[])new ArrayList[n];
        adjq = (ArrayList<Integer>[])new ArrayList[n];
        
        ArrayList<Long> final_ans = new ArrayList<>();
                
        for (int i = 0; i < n; i++) {
            adjf[i] = new ArrayList<>();
            costf[i] = new ArrayList<>();
            adjr[i] = new ArrayList<>();
            costr[i] = new ArrayList<>();
            
            adjCache[i] = new ArrayList<>();
            costCache[i] = new ArrayList<>();
            adjq[i] = new ArrayList<>();
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
            adjf[s -1 ].add(t - 1);
            costf[s - 1].add(c);
            adjr[t - 1].add(s - 1);
            costr[t - 1].add(c);
        }

        int qrr = in.nextInt();

        for (int i = 0; i < qrr; i++) {
            int u, v;
            u = in.nextInt()-1;
            v = in.nextInt()-1;
            adjq[u].add(v);
            final_ans.add(query(adjf,adjr,costf,costr,u,v));
        }
        
        for (int i = 0; i < final_ans.size(); i++){
            System.out.println(final_ans.get(i));
        }
    }
    
    
}
