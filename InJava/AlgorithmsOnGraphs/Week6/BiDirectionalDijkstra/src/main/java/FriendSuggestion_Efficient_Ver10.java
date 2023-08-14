import java.util.*;

/*
Same as FriendSuggestion_Efficient_Passed_Ver3, which is flattened code. The
only difference is I added my own priority queue based on arraylist 
implementation of the heap. Did not help much.
*/

public class FriendSuggestion_Efficient_Ver10{
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

    public static class myQueue {
        int size;
        ArrayList<Entry> h = new ArrayList<>();
        
        public myQueue(){
            this.h = h;
            this.size = this.size;
        }
        
        private int parent(int i){
            if(i > 0){
                return (i-1)/2;
            }
            else{
                return 0;
            }
        }
        private int left(int i){
            return (2*i+1);
        }
        private int right(int i){
            return (2*i+2);
        }
        private void swap(int i, int j){
            Entry etemp = h.get(i);
            h.set(i, h.get(j));
            h.set(j, etemp);
        }
        private void siftup(int i){
            if(i < size){
                while((i > 0) && (h.get(parent(i)).cost > h.get(i).cost )){
                    swap(i,parent(i));
                    i = parent(i);
                }
            }
        }
        private void siftdown(int i){
            int maxindex = i;
            int l = left(i);
            int r = right(i);
            if((l < size)){
                if((h.get(l).cost < h.get(maxindex).cost)){
                    maxindex = l;
                }
            }
            if((r < size)){
                if((h.get(r).cost < h.get(maxindex).cost)){
                    maxindex = r;
                }
            }
            if(i != maxindex){
                swap(i,maxindex);
                siftdown(maxindex);
            }
        }
        public void add(Entry p){
            h.add(p);
            size = size+1;
            siftup(size-1);
        }
        public Entry poll(){
            Entry result = h.get(0);
            h.set(0, h.get(size-1));
            h.remove(size-1);
            size = size - 1;
            siftdown(0);
            return result;
        }
        public boolean isEmpty(){
            return h.isEmpty();
        }
        public void clear(){
            size = 0;
            h.clear();
        }
    }    
   
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        if(v == u){
            return 0;
        }
        
        long dist = inf;
        
        myQueue qf = new myQueue();
        myQueue qr = new myQueue();
        HashSet<Integer> wf = new HashSet<>();
        HashSet<Integer> wr = new HashSet<>();
        HashMap<Integer,Long> distf = new HashMap<>();
        HashMap<Integer,Long> distr = new HashMap<>(); 
        
        distf.put(u, 0L);
        qf.add(new Entry(u,0L));
        
        distr.put(v, 0L);
        qr.add(new Entry(v,0L));
        
        while(!qf.isEmpty() && !qr.isEmpty()){
            
            Entry ef = qf.poll();
            int uf = ef.node;
            if(wf.contains(uf) == false){ /* Added to make sure that  
                nodes, adjacent to nodes of wf, are not relaxed. 
                This must save execution time.
                    */                                  
                wf.add(uf);

                for (int i = 0; i < adjf[uf].size(); i++){
                    int vv = adjf[uf].get(i);
                    if(wf.contains(vv) == false){   /* Added to make sure that  
                    nodes in wf are not relaxed. This must save execution time.
                    */                                  
                        int w = costf[uf].get(i);
                        long dt = distf.get(uf) + w;
                        if(distf.containsKey(vv)){
                            long tf = distf.get(vv);
                            if (tf > dt){                            
                                distf.replace(vv, dt);
                                qf.add(new Entry(vv,dt));
                            }
                        }
                        else{
                            distf.put(vv, dt);
                            qf.add(new Entry(vv,dt));
                        }                
                    }            
                }

                if(wr.contains(uf)){
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
            
            }
            
            Entry er = qr.poll();
            int vr = er.node;
                        
            if(wr.contains(vr) == false){/* Added to make sure that  
                nodes, adjacent to nodes of wr, are not relaxed. 
                This must save execution time.
                    */                        
                wr.add(vr);

                for (int i = 0; i < adjr[vr].size(); i++){
                    int uu = adjr[vr].get(i);
                    if(wr.contains(uu) == false){    /* Added to make sure that  
                    nodes in wr are not relaxed. This must save execution time.
                    */                                  
                        int w = costr[vr].get(i);
                        long dt = distr.get(vr) + w;

                        if(distr.containsKey(uu)){
                            long tf = distr.get(uu);
                            if (tf > dt){
                                distr.replace(uu, dt);
                                qr.add(new Entry(uu,dt));
                            }
                        }
                        else{
                            distr.put(uu, dt);
                            qr.add(new Entry(uu,dt));
                        }                
                    }             
                }            
                if(wf.contains(vr)){


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



