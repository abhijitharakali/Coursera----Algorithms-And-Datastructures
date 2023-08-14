import java.util.*;
import java.lang.Math;

public class TempDistWithCoords{
    
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
    
    public static class Buggy {
        int n;
        int m;
        double[] df, dr;
        boolean[] reachedf,reachedr;
        ArrayList<Integer> worksetf,worksetr,wf,wr;
        PriorityQueue<Entry> qf,qr;
        ArrayList<Integer>[] adjf, adjr, costf, costr;
        int[] x,y;
        int[] prevf,prevr;
        long INFINITY = Long.MAX_VALUE / 4;



        public Buggy(ArrayList<Integer>[] adjf,ArrayList<Integer>[] adjr,ArrayList<Integer>[] costf,ArrayList<Integer>[] costr,int n,int m,int[] x,int[] y){
            this.n = n;
            this.m = m;
            this.dr = dr;
            this.df = df;
            this.reachedf = reachedf;
            this.reachedr = reachedr;
            this.worksetf = worksetf;
            this.worksetr = worksetr;
            this.wf = wf;
            this.wr = wr;
            this.qf = qf;
            this.qr = qr;
            this.adjf = adjf;
            this.adjr = adjr;
            this.costf = costf;
            this.costr = costr;
            this.x = x;
            this.y = y;
            this.INFINITY = INFINITY;
        }
        public void initVars(){
            df = new double[n];
            dr = new double[n];
            reachedf = new boolean[n];
            reachedr = new boolean[n];
            qf = new PriorityQueue<>();
            qr = new PriorityQueue<>();
            worksetf = new ArrayList<>();
            worksetr = new ArrayList<>();
            wf = new ArrayList<>();
            wr = new ArrayList<>();
            prevf = new int[n];
            prevr = new int[n];        
            Arrays.fill(df, INFINITY);
            Arrays.fill(dr, INFINITY);
            Arrays.fill(reachedf, false);
            Arrays.fill(reachedr, false);        
            Arrays.fill(prevf, -1);
            Arrays.fill(prevr, -1);        
        }

        public int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
            int indx = adj[u].indexOf(v);
            return cost[u].get(indx);
        }
        public int findWorksetMin(int dir){
            double dist = Long.MAX_VALUE / 4;
            int ubest;
            if(dir == 1){
                ubest = wf.get(0);
            }
            else {
                ubest = wr.get(0);
            }
            if(dir == 1){
                for (int u: wf){
                    if(df[u] + dr[u] < dist){
                        dist = df[u] + dr[u];
                        ubest = u;
                    }
                }            
            }
            else if(dir == -1){
                for (int u: wr){
                    if(df[u] + dr[u] < dist){
                        dist = df[u] + dr[u];
                        ubest = u;
                    }
                }             
            }
            return ubest;
        }
        public long shortestPath() {
            long inf = Long.MAX_VALUE;
            double dist = inf;
            if (worksetf.isEmpty() && worksetr.isEmpty()){
                return -1;
            }

            int ubest;
            if(wf.size() < wr.size()){
                ubest = findWorksetMin(1);
            }
            else{
                ubest = findWorksetMin(-1);
            }
            dist = df[ubest]+dr[ubest];
            if(dist >= inf){
                return -1;
            }
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
        public void replace_qf(int node,double dist){
            PriorityQueue<Entry> q = new PriorityQueue<>();
            double min_dist = dist;
            while(qf.isEmpty() == false){
                Entry ef = qf.poll();
                if(ef.node != node){
                    q.add(ef);
                }
                else{
                    if(min_dist > ef.cost){
                        min_dist = ef.cost;
                    }
                }
            }
            q.add(new Entry(node,min_dist));
            qf.addAll(q);
        }

        public void replace_qr(int node,double dist){
            PriorityQueue<Entry> q = new PriorityQueue<>();
            double min_dist = dist;
            while(qr.isEmpty() == false){
                Entry er = qr.poll();
                if(er.node != node){
                    q.add(er);
                }
                else{
                    if(min_dist > er.cost){
                        min_dist = er.cost;
                    }
                }
            }
            q.add(new Entry(node,min_dist));
            qr.addAll(q);
        }    

        public void visit(int node_src, int node_dest, double dist, int dir){
            if (dir == 1){
                if (df[node_dest] > dist){

                    if(!worksetf.contains(node_dest)){

                        df[node_dest] = dist;
                        qf.add(new Entry(node_dest,dist));
                        worksetf.add(node_dest);
                        prevf[node_dest] = node_src;

                    }

                    else {
                        df[node_dest] = dist;
                        replace_qf(node_dest,dist);
                        prevf[node_dest] = node_src;
                    }
                }
            }

            else if (dir == -1){
                if (dr[node_dest] > dist){

                    if(!worksetr.contains(node_dest)){

                        dr[node_dest] = dist;
                        qr.add(new Entry(node_dest,dist));
                        worksetr.add(node_dest);
                        prevr[node_dest] = node_src;

                    }

                    else {
                        dr[node_dest] = dist;
                        replace_qr(node_dest,dist);
                        prevr[node_dest] = node_src;
                    }
                }
            }
        }

        public void clear(){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.addAll(worksetf);
            temp.addAll(worksetr);
            for(int k : temp){
                df[k] = INFINITY;
                dr[k] = INFINITY;
                reachedf[k] = false;
                reachedr[k] = false;
                prevf[k] = -1;
                prevr[k] = -1;
            }
            worksetf.clear();
            worksetr.clear();
            wf.clear();
            wr.clear();
            qf.clear();
            qr.clear();
        }

        public void Process(int dir, int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,int s,int t){
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

        public double pf(int u, int v, int node){
            double dr = Math.sqrt( ( x[u]-x[node] ) * ( x[u]-x[node] ) + ( y[u]-y[node] ) * ( y[u]-y[node] ) );
            double df = Math.sqrt( ( x[v]-x[node] ) * ( x[v]-x[node] ) + ( y[v]-y[node] ) * ( y[v]-y[node] ) );
            return (df-dr)/2;
        }

        public double pr(int u, int v, int node){
            double dr = Math.sqrt( ( x[u]-x[node] ) * ( x[u]-x[node] ) + ( y[u]-y[node] ) * ( y[u]-y[node] ) );
            double df = Math.sqrt( ( x[v]-x[node] ) * ( x[v]-x[node] ) + ( y[v]-y[node] ) * ( y[v]-y[node] ) );
            return (dr-df)/2;
        }    

        public long query(int u,int v){

            if(v == u){
                return 0;
            }

            clear();

            visit(-1,u, 0, 1);
            wf.add(u);

            visit(-1,v, 0, -1);
            wr.add(v);

            while(!qf.isEmpty() && !qr.isEmpty()){
                Entry ef = qf.poll();
                int uf = ef.node;
                if(df[uf] == INFINITY){
                    return -1;
                }
                Process(1, uf, adjf, costf, u, v);

                if(reachedr[uf]){
                    wf.add(uf);
                    return shortestPath();
                }
                wf.add(uf);
                reachedf[uf] = true;

                Entry er = qr.poll();
                int vr = er.node;
                if(dr[vr] == INFINITY){
                    return -1;
                }
                Process(-1, vr, adjr, costr, u, v);

                if(reachedf[vr]){
                    wr.add(vr);
                    return shortestPath();
                }
                wr.add(vr);
                reachedr[vr] = true;
            }

            return -1;
        }
    }        

    public static class Reference{

    int n;
    int m;
    int cntf,cntr;
    long[] df, dr;
    ArrayList<Integer> workset,path;
    boolean[] wf,wr,visf,visr;
    PriorityQueue<Entry> qf,qr;
    int[] prevf,prevr;
    ArrayList<Integer>[] adjCache;
    ArrayList<Long>[] costCache;
    ArrayList<Integer>[] adjq;
    long INFINITY = Long.MAX_VALUE / 4;
    ArrayList<Integer>[] adjf, adjr, costf, costr;
    
    public Reference(ArrayList<Integer>[] adjf,ArrayList<Integer>[] adjr,ArrayList<Integer>[] costf,ArrayList<Integer>[] costr,int n,int m){
            this.n = n;
            this.m = m;
            this.cntf = cntf;
            this.cntr = cntr;
            this.dr = dr;
            this.df = df;
            this.workset = workset;
            this.path = path;
            this.wf = wf;
            this.wr = wr;
            this.visf = visf;
            this.visr = visr;
            this.qf = qf;
            this.qr = qr;
            this.prevf = prevf;
            this.prevr = prevr;
            this.adjCache = adjCache;
            this.costCache = costCache;
            this.adjq = adjq;
            this.INFINITY = INFINITY;
            this.adjf = adjf;
            this.adjr = adjr;
            this.costf = costf;
            this.costr = costr;      
    }
    
    public void initVars(){
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
        adjCache = (ArrayList<Integer>[])new ArrayList[n];
        costCache = (ArrayList<Long>[])new ArrayList[n];
        adjq = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjCache[i] = new ArrayList<>();
            costCache[i] = new ArrayList<>();
            adjq[i] = new ArrayList<>();
        }        
    }

    public int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    public void updateCache(int ubest,long distuv){
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
    
    public long shortestPath() {
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
        updateCache(ubest,dist);
        
        return dist;
    }
    
    public void visit(int node, long dist, int dir,int prev_node){
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
    
    public void clear(){

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
    
    public void Process(int dir, int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost){
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
    public void updateCachef(int u,int uf){
        if(!adjCache[u].contains(uf)){
            adjCache[u].add(uf);
            costCache[u].add(df[uf]);
        }
    }
    public void updateCacher(int vr,int v){
        if(!adjCache[vr].contains(v)){
            adjCache[vr].add(v);
            costCache[vr].add(dr[vr]);
        }
    }    
    public long query(int u,int v){
        
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
                    return shortestPath();
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
                    return shortestPath();
                }
            }
        }
        
        return -1;
    }
    
    
    
    
}
    
    public static void printInput(ArrayList<Integer>[] adjf,ArrayList<Integer>[] costf,int n,int m,int[] x,int[] y,int u, int v){
        System.out.println();
        System.out.println(n+" "+m);
        for(int i = 0;i < n; i++){
            System.out.println(x[i]+" "+y[i]);
        }
        for(int j = 0; j < n; j++){
            if(!adjf[j].isEmpty()){
                for(int k : adjf[j]){
                    System.out.println(j+" "+k+" "+(costf[j].get(adjf[j].indexOf(k))));
                }
            }
        }
        System.out.println("1");
        System.out.println(u+" "+v);
        System.out.println();
    }

    public static void main(String args[]) {
        int n = 20;
        int m_max = n*(n-1)/2;
        Random rand = new Random();
        boolean eflag = true;
        
        while(eflag){
            int m = rand.nextInt(m_max);

            ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n];
            ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n];
            ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
            ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n];        
            int[] x = new int[n];
            int[] y = new int[n];
            for (int i = 0; i < n; i++) { 
                int xi, yi, radius;
                radius = (int)Math.sqrt(rand.nextInt(150*150));
                if(radius == 0){
                    radius = 1;
                }
                xi = rand.nextInt(2*radius)-radius;
                yi = rand.nextInt(2*radius)-radius;
                x[i] = xi;
                y[i] = yi;
            }         
            for (int i = 0; i < n; i++) {
                adjf[i] = new ArrayList<>();
                costf[i] = new ArrayList<>();
                adjr[i] = new ArrayList<>();
                costr[i] = new ArrayList<>();
            }

            for(int i = 0; i < m; i++){
                int s, t, c;
                s = rand.nextInt(n);
                t = rand.nextInt(n);
                while(adjf[s].contains(t)){
                    s = rand.nextInt(n);
                    t = rand.nextInt(n);
                }
                int dtemp = (int)Math.sqrt((x[s] - x[t])*(x[s] - x[t]) + (y[s] - y[t])*(y[s] - y[t]));
                if((int)(0.3*dtemp) > 0){
                    c = rand.nextInt((int)(0.3*dtemp)) + dtemp;
                }
                else {
                    c = (int) (1.3*dtemp);
                }
                adjf[s].add(t);
                costf[s].add(c);
                adjr[t].add(s);
                costr[t].add(c);            
            }
            Buggy bug = new Buggy(adjf,adjr,costf,costr,n,m,x,y);
            bug.initVars();
            Reference ref = new Reference(adjf,adjr,costf,costr,n,m);
            ref.initVars();

            boolean fbreak = false;

            for(int u = 0; u < n; u++){
                for(int v = 0; v < n; v++){
                    if((u!=v)){
                            if((bug.query(u, v) != ref.query(u, v))){
                            eflag = false;
                            printInput(adjf,costf,n,m,x,y,u,v);
                            fbreak = true;
                            break;
                        }
                    }
                }
                if(fbreak){
                    break;
                }
            }
        }

    }
    
}
