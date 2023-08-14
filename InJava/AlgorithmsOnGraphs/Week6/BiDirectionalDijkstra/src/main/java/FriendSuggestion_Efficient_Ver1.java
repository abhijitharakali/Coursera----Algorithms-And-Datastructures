import java.util.*;
//import java.lang.Math;
/**
 * This code passes sanity tests but fails to pass the time limit.
 * @author abhijitharakali
 */
public class FriendSuggestion_Efficient_Ver1{
    
//    public static int n;
//    public static int m;
//    public static Long[] df, dr;
//    public static boolean[] procf;
//    public static boolean[] procr;
    
//    static int[] x;
//    static int[] y;
//    static ArrayList<Integer>[] adjf;
//    static ArrayList<Integer>[] costf;
//    static ArrayList<Integer>[] adjr;
//    static ArrayList<Integer>[] costr;
    
    public static int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    
    public static Long shortestPath(ArrayList<Integer> workset, ArrayList<Integer> qf, ArrayList<Integer> qr,long[] df,long[] dr) {
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
//        clear(workset, qf, qr);
        return dist;
    }
    
    public static int extractMinf(ArrayList<Integer> qf,long[] df){
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

    public static int extractMinr(ArrayList<Integer> qr,long[] dr){
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
    
//    public static void clear(ArrayList<Integer> workset, ArrayList<Integer> qf, ArrayList<Integer> qr){
//        Long INFINITY = Long.MAX_VALUE / 4;
//        for(int k: workset){
//            df[k] = INFINITY;
//            procf[k] = false;
//            dr[k] = INFINITY;
//            procr[k] = false;
//        }
//        workset.clear();
//        qf.clear();
//        qr.clear();
//    }
    
    public static Long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){
            return 0L;
        }
        int nn = adjf.length;
//        Long[] df, dr;
        boolean[] procf;
        boolean[] procr;
        
        Long INFINITY = Long.MAX_VALUE / 4;
        long[] df = new long[nn];
        long[] dr = new long[nn];
        procf = new boolean[nn];
        procr = new boolean[nn];
        
        Arrays.fill(df, INFINITY);
        Arrays.fill(dr, INFINITY);
        Arrays.fill(procf, false);
        Arrays.fill(procr,false);
        
        
        ArrayList<Integer> qf = new ArrayList<>();
        ArrayList<Integer> qr = new ArrayList<>();
        ArrayList<Integer> workset = new ArrayList<>();
        for(int i = 0; i < nn; i++){
            qf.add(i);
            qr.add(i);
//            if (i != u){
//                qf.add(i);
//            }
//            if(i != v){
//                qr.add(i);
//            }
        }
        
        df[u] = 0L;
        dr[v] = 0L;
        procf[u] = true;
        procr[v] = true;
        
//        workset.add(u);
//        workset.add(v);
        
        while(!qf.isEmpty() && !qr.isEmpty()){
            int uf = extractMinf(qf,df);
            if(df[uf] == INFINITY){
//                clear(workset, qf, qr);
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
                return shortestPath(workset, qf, qr,df,dr);
            }
            
            int vr = extractMinr(qr,dr);
            if (dr[vr] == INFINITY){
//                clear(workset, qf, qr);
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
                return shortestPath(workset, qf, qr,df,dr);
            }
        }
        
//        clear(workset, qf, qr);
        return -1L;
    }
        
    public static void main(String args[]) {
        
        int n;
        int m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

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


