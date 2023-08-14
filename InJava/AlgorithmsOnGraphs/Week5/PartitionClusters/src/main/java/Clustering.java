import java.util.*;

public class Clustering {

    private static int find(int s, int[] parent) {
        if (s != parent[s]){
            parent[s] = find(parent[s],parent);
        }
        return parent[s];
    }

    private static void unite(int s, int t, int[] parent, int[] rank) {
        int s_id = find(s, parent);
        int t_id = find(t,parent);
        if(s_id == t_id){
            return;
        }
        if(rank[s_id] > rank[t_id]){
            parent[t_id] = s_id;
        }
        else{
            parent[s_id] = t_id;
            if (rank[s_id] == rank[t_id]){
                rank[t_id] = rank[t_id] + 1;
            }
        }
    }
    
    private static class Edge{
        int[] x, y;
        int i, j, dist;
        Edge (int[] x, int[] y, int i, int j){
            this.x = x;
            this.y = y;
            this.i = i;
            this.j = j;
            this.dist = ((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]));
        }
        public int getDist(){
            return this.dist;
        }
        public int geti(){
            return this.i;
        }
        public int getj(){
            return this.j;
        }
    }
    
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
        int n = x.length;
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
        int m = n*(n-1)/2;
        Edge[] edges = new Edge[m];
        int[] dist = new int[m];
        int cnt = 0;
        for(int j = 0; j < n-1; j++){
            for (int l = j+1; l < n; l++){
                edges[cnt] = new Edge(x, y, j, l);
                dist[cnt] = edges[cnt].getDist();
                cnt = cnt + 1;
            }
        }
        Arrays.sort(edges,Comparator.comparing(Edge::getDist));
        if (n == k){
            return Math.sqrt(edges[0].getDist());
        }
        int clusters = n;
        int ans = 0;
        System.out.println();
        for (int i = 0; i < m; i++){
            int s = edges[i].geti();
            int t = edges[i].getj();
            
            if (find(s,parent) != find(t,parent)){
                unite(s,t,parent,rank);
                clusters = clusters - 1;
//                System.out.println(s + " " + t);
//                System.out.println(clusters);
            }
            if (clusters == k-1){
                ans = edges[i].getDist();
//                System.out.println(edges[i].getDist());
                break;
            }
        }
        return Math.sqrt(ans);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}


