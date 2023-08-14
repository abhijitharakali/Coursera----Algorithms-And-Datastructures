import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
        int[][][] m = new int[a.length+1][b.length+1][c.length+1];
        m[0][0][0]=0;
        for(int i=1;i<(a.length+1);i++){
            m[i][0][0]=0;
        }
        for(int j=1;j<(b.length+1);j++){
            m[0][j][0]=0;
        }
        for(int k=1;k<(c.length+1);k++){
            m[0][0][k]=0;
        }
        for(int k=1;k<(c.length+1);k++)
            for(int j=1;j<(b.length+1);j++)
                for(int i=1;i<(a.length+1);i++){
                int ins = m[i][j-1][k];
                int del = m[i-1][j][k];
                int indel=m[i][j][k-1];
                int mtc = 1+m[i-1][j-1][k-1];
                int mm = m[i-1][j-1][k-1];
                int ci = a[i-1];
                int cj = b[j-1];
                int ck = c[k-1];
                if((ci==cj)&&(ci==ck)){
                    m[i][j][k]=max(ins,del,indel,mtc);
                    // System.out.println(i+" "+j+" "+ci+" "+cj);
                }
                else{
                    m[i][j][k]=max(ins,del,indel,mm);
                }
            }
        return m[a.length][b.length][c.length];
    }
        private static int max(int ins, int del, int indel, int mtc) {
        int largest = ins;
        if (largest < del) largest = del;
        if (largest < mtc) largest = mtc;
        if(largest < indel) largest = indel;
        return largest;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

