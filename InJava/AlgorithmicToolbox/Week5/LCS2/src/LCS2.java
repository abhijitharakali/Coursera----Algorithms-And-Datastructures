import java.util.*;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        int[][] m = new int[a.length+1][b.length+1];
        m[0][0]=0;
        for(int i=1;i<(a.length+1);i++){
            m[i][0]=0;
        }
        for(int j=1;j<(b.length+1);j++){
            m[0][j]=0;
        }
        for(int j=1;j<(b.length+1);j++)
            for(int i=1;i<(a.length+1);i++){
                int ins = m[i][j-1];
                int del = m[i-1][j];
                int mtc = 1+m[i-1][j-1];
                int mm = m[i-1][j-1];
                int ci = a[i-1];
                int cj = b[j-1];
                if(ci==cj){
                    m[i][j]=max(ins,del,mtc);
                    // System.out.println(i+" "+j+" "+ci+" "+cj);
                }
                else{
                    m[i][j]=max(ins,del,mm);
                }
            }
        return m[a.length][b.length];
    }

        private static int max(int ins, int del, int mtc) {
        int largest = ins;
        if (largest < del) largest = del;
        if (largest < mtc) largest = mtc;
        return largest;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}

