import java.util.*;
/*
This code passed, at least once, with execution time of 106s.
*/
public class FriendSuggestion_Efficient_Passed_Ver3{
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
    
   
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        if(v == u){
            return 0;
        }
        
        long dist = inf;
        
        HashMap<Integer,Long> distf,distr;
        HashSet<Integer> wf,wr;
        PriorityQueue<Entry> qf,qr; 

        qf = new PriorityQueue<>();
        qr = new PriorityQueue<>();
        wf = new HashSet<>();
        wr = new HashSet<>();
        distf = new HashMap<>();
        distr = new HashMap<>();     
        
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


