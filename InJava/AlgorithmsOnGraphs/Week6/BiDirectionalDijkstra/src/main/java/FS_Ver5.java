import java.util.*;

/*
The reason for creating this version is to take a snapshot of the progress 
made so far. I have now implemented priority queue to store the nodes that need
to be processed. However, I am failing test number 7. I think that this test
case fails because I am initializing too many values in the df and dr to 
INFINITY. Also, I plan to change the code where I process only nodes in either
proc or procr, not both.
*/

public class FS_Ver5{
    
    public static int n;
    public static int m;
    public static long[] df, dr;
    public static boolean[] procf,procr, visited;
    public static ArrayList<Integer> workset,changed;
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
        for (int u: workset){
            if(df[u] + dr[u] < dist){
                dist = df[u] + dr[u];
            }
        }
        return dist;
    }
    
    public static void visit(int node){
        if(!visited[node]){
            changed.add(node);
        }
        visited[node] = true;
    }
    
    public static void clear(){

        for(int k : changed){
            df[k] = INFINITY;
            procf[k] = false;
            dr[k] = INFINITY;
            procr[k] = false;
            visited[k] = false;
        }
        workset.clear();
        changed.clear();
        qf.clear();
        qr.clear();
    }
    
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){
            return 0;
        }
        
        clear();
        
        int nn = adjf.length;        
        df[u] = 0;
        dr[v] = 0;

        qf.add(new Entry(u,0));
        qr.add(new Entry(v,0));

        while(!qf.isEmpty() && !qr.isEmpty()){
            Entry ef = qf.poll();
            int uf = ef.node;
            if(df[uf] == INFINITY){
                return -1;
            }
            if(adjf[uf].isEmpty() == false){
                for(int vf: adjf[uf]){
                    int w = get_cost(adjf,costf,uf,vf);
                    if(df[vf] > df[uf] + w){
                        df[vf] = df[uf] + w;
                        if(!procf[vf]){
                            qf.add(new Entry(vf,df[vf]));
                        }
                    }
                    visit(vf);
                }
            }
            procf[uf] = true;
            workset.add(uf);
            visit(uf);
            if(procr[uf]){
                return shortestPath();
            }
            
            Entry er = qr.poll();
            int vr = er.node;
            if (dr[vr] == INFINITY){
                return -1;
            }
            if(adjr[vr].isEmpty() == false){
                for(int ur: adjr[vr]){
                    int w = get_cost(adjr,costr,vr,ur);
                    if(dr[ur] > dr[vr] + w){
                        dr[ur] = dr[vr] + w;
                        if(!procr[ur]){
                            qr.add(new Entry(ur,dr[ur]));
                        }
                    }
                    visit(ur);
                }
            }
            procr[vr] = true;
            workset.add(vr);
            visit(vr);
            if(procf[vr]){
                return shortestPath();
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
        procf = new boolean[n];
        procr = new boolean[n];
        visited = new boolean[n];
        
        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();
        workset = new ArrayList<>();
        changed = new ArrayList<>();
        
        Arrays.fill(df, INFINITY);
        Arrays.fill(dr, INFINITY);
        Arrays.fill(procf, false);
        Arrays.fill(procr,false);
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



