import java.util.*;

/*
So brutal! This code took 126s, while it takes 125s to pass! 
*/

public class FriendSuggestion_Efficient_Ver4{
    public static long inf = Long.MAX_VALUE / 4;
        
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
    
    public static long shortestPath(HashSet<Integer> wr, HashSet<Integer> wf, HashMap<Integer,Long> distr, HashMap<Integer,Long> distf) {

        long dist = inf;
        
        if (wf.isEmpty() || wr.isEmpty()){
            return -1;
        }
        if(wf.size() < wr.size()){
            Iterator itr = wf.iterator();
            while(itr.hasNext()){
                int uu = (int)itr.next();
                
                if(distr.containsKey(uu)){
                    if(distf.get(uu)+distr.get(uu) < dist){
                        dist = distf.get(uu)+distr.get(uu);
                    }
                }
            }
        }
        else{
            Iterator itr = wr.iterator();
            while(itr.hasNext()){
                int uu = (int)itr.next();
               
                if(distf.containsKey(uu)){
                    if(distf.get(uu)+distr.get(uu) < dist){
                        dist = distf.get(uu)+distr.get(uu);
                    }
                }
            }            
        }
            
        if(dist == inf){
            return -1;
        }
        return dist;
    } 
    
    public static void visit(int node, long dist, PriorityQueue<Entry> q, HashMap<Integer,Long> distq){

            if(distq.containsKey(node)){                
                if (distq.get(node) > dist){
                    distq.replace(node, dist);
                    q.add(new Entry(node,dist));
                }
            }
            else{
                distq.put(node, dist);
                q.add(new Entry(node,dist));
            }

    }
    
    
    public static void Process(int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,PriorityQueue<Entry> q, HashMap<Integer,Long> distq){
        for (int i = 0; i < adj[node].size(); i++){
            int v = adj[node].get(i);
            int w = cost[node].get(i);
            visit(v, distq.get(node) + w, q, distq);
        }
    }
    
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        if(v == u){
            return 0;
        }
        HashMap<Integer,Long> distf,distr;
        HashSet<Integer> wf,wr;
        PriorityQueue<Entry> qf,qr;        
        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();
        wf = new HashSet<>();
        wr = new HashSet<>();
        distf = new HashMap<Integer,Long>();
        distr = new HashMap<Integer,Long>();
        
        visit(u, 0, qf, distf);
        visit(v, 0, qr, distr);

        while(!qf.isEmpty() && !qr.isEmpty()){
            Entry ef = qf.poll();
            int uf = ef.node;
//            if(distf.containsKey(uf) == false){
//                return -1;
//            }           
            Process(uf, adjf, costf, qf, distf);
            
            if(wr.contains(uf)){
                wf.add(uf);
                return shortestPath(wr,wf,distr,distf);
            }
            wf.add(uf);
            
            Entry er = qr.poll();
            int vr = er.node;
//            if(distr.containsKey(vr) == false){
//                return -1;
//            }
            Process(vr, adjr, costr, qr, distr);
            
            if(wf.contains(vr)){
                wr.add(vr);
                return shortestPath(wr, wf, distr, distf);
            }
            wr.add(vr);
        }
        
        return -1;
    }
        
    public static void main(String args[]) {
        
        int n;
        int m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n];

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
        long[] final_ans = new long[qrr];

        for (int i = 0; i < qrr; i++) {
            int u, v;
            u = in.nextInt()-1;
            v = in.nextInt()-1;
            final_ans[i] = (query(adjf,adjr,costf,costr,u,v));
        }
        
        for (int i = 0; i < final_ans.length; i++){
            
            System.out.println(final_ans[i]);
        }
    }
     
}
