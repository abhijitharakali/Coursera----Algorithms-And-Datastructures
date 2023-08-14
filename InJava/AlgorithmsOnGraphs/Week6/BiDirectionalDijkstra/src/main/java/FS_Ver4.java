import java.util.*;

/**
 * Here, I initialized almost every variable as being global.
 * However, it took 250s to complete test #5. Looks like a priority queue
 * needs to be implemented for the distance, instead of using extractmin 
 * routine. I will try to attempt that in the next version.
 * @author abhijitharakali
 */

public class FS_Ver4{
    
    public static int n;
    public static int m;
    public static long[] df, dr;
    public static boolean[] procf,procr,reachedf,reachedr;
    public static ArrayList<Integer> qf, qr, workset, changed;
    
    public static Long INFINITY = Long.MAX_VALUE / 4;
    
    public static int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    
    public static Long shortestPath() {
        Long inf = Long.MAX_VALUE;
        Long dist = inf;
        if (workset.isEmpty()){
            return -1L;
        }
        for (int u: workset){
            if(df[u] + dr[u] < dist){
                dist = df[u] + dr[u];
            }
        }
        return dist;
    }
    
    public static int extractMinf(){
        if(qf.isEmpty()){
            return -1;
        }
        int ans = qf.get(0);
        int indx = 0;
        for(int i = 0; i < qf.size(); i++){
            int j = qf.get(i);
            if (df[ans] > df[j]){
                ans = j;
                indx = i;
            }
        }
        qf.remove(indx);
        return ans;
    }

    public static int extractMinr(){
        if(qr.isEmpty()){
            return -1;
        }
        int ans = qr.get(0);
        int indx = 0;
        for(int i = 0; i < qr.size(); i++){
            int j = qr.get(i);
            if (dr[ans] > dr[j]){
                ans = j;
                indx = i;
            }
        }
        qr.remove(indx);
        return ans;
    }
    
    public static void addf(int u){
        if(!reachedf[u]){
            reachedf[u] = true;
            changed.add(u);
        }
    }
    
    public static void addr(int v){
        if(!reachedr[v]){
            reachedr[v] = true;
            changed.add(v);
        }
    }
    
    public static void clear(){
        
        for(int k: changed){
            df[k] = INFINITY;
            procf[k] = false;
            reachedf[k] = false;
            dr[k] = INFINITY;
            procr[k] = false;
            reachedr[k] = false;
        }
        changed.clear();
        workset.clear();
        qf.clear();
        qr.clear();
    }
    
    public static Long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){
            return 0L;
        }
        
        clear();
        
        int nn = adjf.length;        
        df[u] = 0L;
        dr[v] = 0L;
        procf[u] = true;
        procr[v] = true;

        
        for(int i = 0; i < nn; i++){
            qf.add(i);
            qr.add(i);
        }

        while(!qf.isEmpty() && !qr.isEmpty()){
            int uf = extractMinf();
            if(df[uf] == INFINITY){
                return -1L;
            }
            if(adjf[uf].isEmpty() == false){
                for(int vf: adjf[uf]){
                    int w = get_cost(adjf,costf,uf,vf);
                    if(df[vf] > df[uf] + w){
                        df[vf] = df[uf] + w;
                    }
                    addf(vf);
                }
            }
            procf[uf] = true;
            workset.add(uf);
            addf(uf);
            if(procr[uf]){
                return shortestPath();
            }
            
            int vr = extractMinr();
            if (dr[vr] == INFINITY){
                return -1L;
            }
            if(adjr[vr].isEmpty() == false){
                for(int ur: adjr[vr]){
                    int w = get_cost(adjr,costr,vr,ur);
                    if(dr[ur] > dr[vr] + w){
                        dr[ur] = dr[vr] + w;
                    }
                    addr(ur);
                }
            }
            procr[vr] = true;
            workset.add(vr);
            addr(vr);
            if(procf[vr]){
                return shortestPath();
            }
        }
        
        return -1L;
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
        reachedf = new boolean[n];
        reachedr = new boolean[n];
        
        qf = new ArrayList<>();
        qr = new ArrayList<>();
        workset = new ArrayList<>();
        changed = new ArrayList<>();
        
        Arrays.fill(df, INFINITY);
        Arrays.fill(dr, INFINITY);
        Arrays.fill(procf, false);
        Arrays.fill(procr,false);
        Arrays.fill(reachedf, false);
        Arrays.fill(reachedr, false);


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



