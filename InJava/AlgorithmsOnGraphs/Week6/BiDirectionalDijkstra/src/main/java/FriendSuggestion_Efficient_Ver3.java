import java.util.*;

/*
I got rid of visited, df, and dr arrays. However, I'm still failing the test
case #7. I will next try to initalize hashmap and the arraylists inside the 
query. This will save the time of clearing those variables.
*/

public class FriendSuggestion_Efficient_Ver3{
    
    public static int n;
    public static int m;
    public static HashMap<Integer,Long> distf,distr;
    public static HashSet<Integer> wf,wr;
    public static PriorityQueue<Entry> qf,qr;
        
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
        long inf = Long.MAX_VALUE / 4;
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
    
    public static void visit(int node, long dist, int dir){
        if (dir == 1){
            if(distf.containsKey(node)){                
                if (distf.get(node) > dist){
                    distf.replace(node, dist);
                    qf.add(new Entry(node,dist));
                }
            }
            else{
                distf.put(node, dist);
                qf.add(new Entry(node,dist));
            }
        }
        else if(dir == -1){
            if(distr.containsKey(node)){                
                if (distr.get(node) > dist){
                    distr.replace(node, dist);
                    qr.add(new Entry(node,dist));
                    
                }
            }
            else{
                distr.put(node, dist);
                qr.add(new Entry(node,dist));
            }
        }
    }
    
    public static void clear(){
        
        distf.clear();
        distr.clear();
        wf.clear();
        wr.clear();
        qf.clear();
        qr.clear();
    }
    
    public static void Process(int dir, int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost){
        for (int i = 0; i < adj[node].size(); i++){
            int v = adj[node].get(i);
            int w = cost[node].get(i);
            if(dir == 1){
                visit(v, distf.get(node) + w, dir);
            }
            else if(dir == -1){
                visit(v, distr.get(node) + w, dir);
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
            if(distf.containsKey(uf) == false){
                return -1;
            }
            Process(1, uf, adjf, costf);
            
            if(wr.contains(uf)){
                wf.add(uf);
                return shortestPath();
            }
            wf.add(uf);
            
            Entry er = qr.poll();
            int vr = er.node;
            if(distr.containsKey(vr) == false){
                return -1;
            }
            Process(-1, vr, adjr, costr);
            
            if(wf.contains(vr)){
                wr.add(vr);
                return shortestPath();
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
        
        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();
        wf = new HashSet<>();
        wr = new HashSet<>();
        distf = new HashMap<Integer,Long>();
        distr = new HashMap<Integer,Long>();
        



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
