import java.util.*;
/*
Got rid of priority queue from Passed_Ver4, and used distf and distr
to find uf and vr in query function. Time blew up to 250s!
*/
public class FriendSuggestion_Efficient_Ver11{
    public static long inf = Long.MAX_VALUE / 4;
    /*
    Function to get the cost between two nodes.
    */
    
    public static int get_cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v){
        int indx = adj[u].indexOf(v);
        return cost[u].get(indx);
    }
    
    /*
    Once the node common to forward and backward search is found, the function
    below iterates over elements of either forward, or backward search,
    whichever set is smaller. No need to search them both.
    */
    
    public static long shortestPath(HashSet<Integer> wr, HashSet<Integer> wf, HashMap<Integer,Long> distr, HashMap<Integer,Long> distf, long dstart) {

        long dist = dstart; /*
        Store a large value in a temporary variable dist, to be reduced if we
        find a node having smaller distance value. Perhaps redundant because
        we know that wr and wf intersect in atleast one node if this function was 
        called.
        */
        
        if (wf.isEmpty() || wr.isEmpty()){ /*
            Return -1 if one of wr or wf is empty. This perhaps never happens so 
            it might as well be removed.
            */
            return -1;
        }
        int ubest = -1;
        
        if(wf.size() < wr.size()){ /*
            Find shortest path using nodes of wr OR wf, not both. Algorithm
            guarentees that shortest path will have one node in wr, and one in
            wf. So searching the smaller set will reduce time.
            */
            Iterator itr = wf.iterator();
            while(itr.hasNext()){
                int uu = (int)itr.next();
                
                if(distr.containsKey(uu)){ /*
                    If there is no entry in reverse graph for node uu,
                    it means that this was never relaxed, and hence not reached,
                    so we might as well ignore it.
                    
                    Note that this condition might result in the value of
                    dist to remain at inf if the two nodes are not connected.
                    */
                    if(distf.get(uu)+distr.get(uu) < dist){
//                        System.out.println("Forward "+uu);
                        dist = distf.get(uu)+distr.get(uu);
                        ubest = uu;
                    }
                }
            }
        }
        /*
        Repeat symmetrically with wr if wr is smaller than or equal in size to 
        wf.
        */
        else{
            Iterator itr = wr.iterator();
            while(itr.hasNext()){
                int uu = (int)itr.next();
//                System.out.println(uu);
                if(distf.containsKey(uu)){
                    if(distf.get(uu)+distr.get(uu) < dist){
//                        System.out.println("Reverse "+uu);
                        dist = distf.get(uu)+distr.get(uu);
                        ubest = uu;
                    }
                }
            }            
        }
//        System.out.println();
//        System.out.println(ubest);
        if(dist == inf){ /*
            If none of the distances are less than infinity, it means
            that no shortest path exists. Return -1. Perhaps redundant because
            we know that wr and wf intersect in atleast one node if this 
            function was called so this condition is never satisfied.
            */
            return -1;
        }
        return dist;
    } 
    
    /*
    The function below is named visit but is basically same as the function
    'relax' used in literature.
    */
    
    public static void visit(int node, long dist, HashMap<Integer,Long> distq){
            if(distq.containsKey(node)){                
                if (distq.get(node) > dist){
                    distq.replace(node, dist);
                }
            }
            else{
                distq.put(node, dist);
            }
    }
    
    /*
    Function below takes the node of minimum distance from the priority queue,
    then relaxes all of its adjacent nodes.
    */
    
    public static void Process(int node, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, HashMap<Integer,Long> distq, HashSet<Integer> workset){
        for (int i = 0; i < adj[node].size(); i++){ /*
            For all nodes adjacent to node 'node', relax them.
            */
            int v = adj[node].get(i);
            if(workset.contains(v) == false){ /*
                Do not relax nodes if they are in wf or wr as the case maybe.
                This is because the final distance of these nodes are
                already known, and there is no point in relaxing them further.
                */
                int w = cost[node].get(i);
                visit(v, distq.get(node) + w, distq);
            }
        }
    }
    /*
    Iterate over those entries in distf/distr that are not in wf/wr respectively.
    Find the index with least distance. Return -1 if not present.
    */
    public static int findMinNode(HashSet<Integer> proc, HashMap<Integer,Long> distq){
        int minindx = -1;
        long mindist = inf;
        for(int i: distq.keySet()){
            long temp = distq.get(i);
            if((!proc.contains(i)) && (mindist > temp)){
                minindx = i;
                mindist = temp;
            }
        }
        return minindx;
    }
    
    /*
    Query is the funcion that takes the start and end nodes, and returns the
    shortest distance. There could be as many as 1000 queries, each query
    will be most likely be on different pair of nodes.
    */
    
    public static long query(ArrayList<Integer>[] adjf, ArrayList<Integer>[] adjr, ArrayList<Integer>[] costf, ArrayList<Integer>[] costr,int u,int v){
        
        if(v == u){             // Output 0 if nodes are the same.
            return 0;
        }
        long dstart = inf;
        if(adjf[u].contains(v)){
            dstart = get_cost(adjf,costf,u,v);
        }        
        HashMap<Integer,Long> distf,distr;  /*
        Distances are stored in hashmaps. If the hashmap does not contain a 
        certain key, we know that that node has not yet been relaxed/visited.
        */
        HashSet<Integer> wf,wr; /*
        The nodes with minimum distance, that get popped/polled out of the
        priority queues, are stored in these hashsets. Once there is an 
        intersection, we iterate over the elements of the smaller of wr or wf,
        to find the minimum of the sum of distances of distf and distr for each 
        node in that set.
        */
        wf = new HashSet<>();
        wr = new HashSet<>();
        distf = new HashMap<Integer,Long>();
        distr = new HashMap<Integer,Long>();
        
        visit(u, 0, distf); // Visit source and destination nodes initially.
        visit(v, 0, distr);

        while(true){ // Infinite while loop
            int uf = findMinNode(wf,distf);       // Get node that has minimum distance
            if (uf == -1){
                return -1;
            }
            if(wf.contains(uf) == false){ /* If this node was already polled out
                before, then no need to relax nodes adjacent to uf.
                */
                wf.add(uf); // Add this to the set of nodes whose final distance 
                            // is known
                
                Process(uf, adjf, costf, distf, wf); /* Relax nodes
                adjacent to uf.
                */  
                if(wr.contains(uf)){ /* If uf is in the set of nodes whose final
                    distance has been found in the reverse graph, then jump
                    to shortest path function to find the shortest distance.
                    */
                    return shortestPath(wr,wf,distr,distf, dstart);
                }
              
            }
            /*
            Repeat symmetrically with the reverse graph.
            */

            int vr = findMinNode(wr,distr);
            if (vr == -1){
                return -1;
            }            
            if(wr.contains(vr) == false){
                wr.add(vr);                
                Process(vr, adjr, costr, distr, wr);
                if(wf.contains(vr)){
                    return shortestPath(wr, wf, distr, distf, dstart);
                }
                
            }
        }

    }
    
    /*
    Main function.
    */
    
    public static void main(String args[]) {
        
        int n; // Number of nodes
        int m; // Number of edges
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        ArrayList<Integer>[] adjf = (ArrayList<Integer>[])new ArrayList[n]; // Forward adjacency arraylist
        ArrayList<Integer>[] costf = (ArrayList<Integer>[])new ArrayList[n]; // Cost arraylist for forward graph
        ArrayList<Integer>[] adjr = (ArrayList<Integer>[])new ArrayList[n]; // Reverse adjacency arraylist
        ArrayList<Integer>[] costr = (ArrayList<Integer>[])new ArrayList[n]; // Cost arraylist for reverse graph

        for (int i = 0; i < n; i++) {       // Initialize the arraylists
            adjf[i] = new ArrayList<>();
            costf[i] = new ArrayList<>();
            adjr[i] = new ArrayList<>();
            costr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) { // Construct the graph by filling the 
            int s, t, c;                // adjacency and cost arraylists.
            s = in.nextInt();
            t = in.nextInt();
            c = in.nextInt();
            adjf[s - 1].add(t - 1);
            costf[s - 1].add(c);
            adjr[t - 1].add(s - 1);
            costr[t - 1].add(c);
        }
        

        int qrr = in.nextInt();             // Input number of queries.
        long[] final_ans = new long[qrr];   // Arraylist to spit out the final answers

        for (int i = 0; i < qrr; i++) {     // Find the shortest distance for each query.
            int u, v;
            u = in.nextInt()-1;
            v = in.nextInt()-1;
            final_ans[i] = (query(adjf,adjr,costf,costr,u,v));
        }
        
        for (int i = 0; i < final_ans.length; i++){ // Output the answers.
            
            System.out.println(final_ans[i]);
        }
    }
     
}


