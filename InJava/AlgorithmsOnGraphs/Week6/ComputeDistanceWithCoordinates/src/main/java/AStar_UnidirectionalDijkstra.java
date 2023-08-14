import java.util.*;

//Main lesson learnt: Queue in all the nodes. See below.

public class AStar_UnidirectionalDijkstra {
    public static int[] x;
    public static int[] y;    
    
    public static int n;
    public static int m;
    public static double[] distance;
    public static ArrayList<Integer> workset,path;
    public static boolean[] wf;
    public static PriorityQueue<Entry> queue;
    public static int[] prev;
    public static ArrayList<Integer>[] adjCache;
    public static ArrayList<Long>[] costCache;
    public static ArrayList<Integer>[] adjq;
    public static ArrayList<Integer>[] adj;
    public static ArrayList<Integer>[] cost;
    
    public static long INFINITY = Long.MAX_VALUE / 4;
    public static void clear(){

        for(int k : workset){
            distance[k] = INFINITY;
            wf[k] = false;prev[k] = -1;
        }
        workset.clear();
        queue.clear();
    }    
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
    
    public static int get_cost(int u, int v){
        if(u == v){
            return 0;
        }
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    public static void updateCache(int v){
        path.clear();
        path.add(v);
        int ur = prev[v];
        int vr = v;
        
        while(ur!=-1){
            path.add(ur);
            vr = ur;
            ur = prev[vr];
        }
        Collections.reverse(path);
        int np = path.size();
        long cf = 0;
        
        for(int i = 0; i < np; i++){
            
            int ui = path.get(i);
            
            if(!adjq[ui].isEmpty()){
                
                for(int j = i; j < np; j++ ){                    
                    int vj = path.get(j);
                    if(ui == vj){
                        cf = 0;
                    }
                    else {
                        int prevu = prev[vj];
                        cf = cf + get_cost(prevu,vj);
                    }
                    if(adjq[ui].contains(vj)){
                        if(!adjCache[ui].contains(vj)){
                            adjCache[ui].add(vj);
                            costCache[ui].add(cf);
                        }
                    }
                }
                
            }
        }
    }

    
    public static void visit(int node, double dist,int prev_node){

            if (distance[node] > dist){
                distance[node] = dist;
                if(workset.contains(node)){
                    queue.remove(new Entry(node,distance[node]));
                }
                else{
                    workset.add(node);
                }
                queue.add(new Entry(node,dist));                
                prev[node] = prev_node;
            }
        

    }
    
    
    public static void Process(int node, int t){
        for (int i = 0; i < adj[node].size(); i++){
            int v = adj[node].get(i);
            if((wf[v] == false)){
                int w = cost[node].get(i);
                visit(v, distance[node]+w+pot(node,v,t), node);
            }
        }
    }

    public static long query(int u,int v){
        
        if(v == u){
            return 0;
        }
        if(adjCache[u].contains(v)){
            int indx = adjCache[u].indexOf(v);
            return costCache[u].get(indx);
        }
        clear();
        
        visit(u, 0, -1);
        
        
        while(!queue.isEmpty()){
            Entry ef = queue.poll();
            int uf = ef.node;
            if(distance[uf] == INFINITY){
                return -1;
            }         
            if(wf[uf] == false){
                wf[uf] = true;
                if(adjq[u].contains(uf)){
                    updateCache(uf);
                }                               
                if(uf == v){
                    return costCache[u].get(adjCache[u].indexOf(v));
                }
                Process(uf,v);
            }
        }
        
        return -1;
    }
    public static double pot(int u, int v, int t){
       return dE(v,t)-dE(u,t);
    } 
    public static double dE(int u, int v){
        return Math.sqrt( ( x[u]-x[v] ) * ( x[u]-x[v] ) + ( y[u]-y[v] ) * ( y[u]-y[v] ) );
    }    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        x = new int[n];
        y = new int[n];        
        
        long maxw = 0;
        int edges = 0;
        adj = (ArrayList<Integer>[])new ArrayList[n];
        cost = (ArrayList<Integer>[])new ArrayList[n];
        adjCache = (ArrayList<Integer>[])new ArrayList[n];
        costCache = (ArrayList<Long>[])new ArrayList[n];
        adjq = (ArrayList<Integer>[])new ArrayList[n];

        distance = new double[n];
        Arrays.fill(distance, INFINITY);
        
        workset = new ArrayList<Integer>();
        path = new ArrayList<Integer>();
        wf = new boolean[n];
        Arrays.fill(wf, false);
        queue = new PriorityQueue<>();
        
        prev = new int[n];        
        Arrays.fill(prev, -1);
        
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();

            adjCache[i] = new ArrayList<>();
            costCache[i] = new ArrayList<>();
            adjq[i] = new ArrayList<>();            
        }
        for (int i = 0; i < n; i++) { 
            int xi, yi;
            xi = scanner.nextInt();
            yi = scanner.nextInt();
            x[i] = xi;
            y[i] = yi;
        }         
        for (int i = 0; i < m; i++) {
            int s, t, c;
            s = scanner.nextInt();
            t = scanner.nextInt();
            c = scanner.nextInt();
            edges = edges + 1;
            
            if(adj[s-1].contains(t-1)){
                int fdx = adj[s-1].indexOf(t-1);
                if(fdx != 1 ){
                    int cp =cost[s-1].get(fdx);
                    if (cp > c){
                        cost[s-1].set(fdx, c);
                    }
                }
            }
            else {
                adj[s - 1].add(t - 1);
                cost[s - 1].add(c);
            }            
            if (c > maxw){
                maxw = c;
            }
        }
        
        ArrayList<Long> final_ans = new ArrayList<>();
        int qrr = scanner.nextInt();

        for (int i = 0; i < qrr; i++) {
            int u, v;
            u = scanner.nextInt()-1;
            v = scanner.nextInt()-1;
            adjq[u].add(v);
            final_ans.add(query(u,v));
        }

        for (int i = 0; i < final_ans.size(); i++){
            System.out.println(final_ans.get(i));
        }        
    }
}



