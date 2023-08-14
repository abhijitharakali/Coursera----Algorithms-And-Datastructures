import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int n = w.length+1;
        int[][] m = new int[W+1][n];
        for(int i=0;i<(W+1);i++){
            m[i][0]=0;
        }
        for(int j=1;j<n;j++){
            m[0][j]=0;
        }
        for(int j=1;j<n;j++){
            for(int ws=1;ws<(W+1);ws++){
                m[ws][j]=m[ws][j-1];
                if(w[j-1]<=ws){
                    int val=m[ws-w[j-1]][j-1]+w[j-1];
                    // System.out.print(val+" ");
                    if(m[ws][j]<val)
                        m[ws][j]=val;
                }
                // System.out.println(ws+" "+j+" "+m[ws][j]);
            }
        }
        return m[W][n-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

