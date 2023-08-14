import java.util.*;
import java.util.Random;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if(right>=left){
            if(right == left)
                return a[left];
            else{
                int cnt=left+(right-left)/2;
                int majl=getMajorityElement(a,left,cnt);
                int majr=getMajorityElement(a,cnt+1,right);
                if(majl==majr)
                    return majl;
                else {
                    int repr=getRepetitions(a,left,right,majr);
                    int n=right-left+1;
                    if(repr>=n/2+1)
                        return majr;
                    int repl=getRepetitions(a,left,right,majl);
                    if(repl>=n/2+1)
                        return majl;
                    return -1;
                }
            }
        }
        else
            return -1;
    }

    public static void main(String[] args) {
        Random rand = new Random(); 
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        //int n=6;
        //int lim=10;
        int[] a = new int[n];
        //a[0]=6;a[1]=6;a[2]=8;a[3]=6;a[4]=6;a[5]=3;
        //while(true){
            for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        //    a[i]=rand.nextInt(lim);
        //    //System.out.print(a[i]+" ");
            }
        //if(getMajorityElement(a, 0, (a.length-1))!=gmeNaive(a)){
        //    for(int j=0;j<n;j++){
        //        System.out.println(a[j]+" ");
        //    }
        //                    break;
        //}
        //}
        if (getMajorityElement(a, 0, (a.length-1)) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
        //System.out.println(gmeNaive(a)==getMajorityElement(a, 0, (a.length-1)));
        //System.out.println(gmeNaive(a));
        //System.out.println(getMajorityElement(a, 0, (a.length-1)));
    }

    private static int getRepetitions(int[] a, int left, int right, int key) {
        int cnt=0;
        for(int i=left;i<right+1;i++){
            if(a[i]==key)
                cnt++;
        }
        return cnt;
    }

    private static int gmeNaive(int[] a) {
        int n=a.length;
        for(int i=0;i<n;i++){
            int cnt=0;
            for (int j=i+1;j<n;j++){
            if(a[i]==a[j])
                cnt++;
        }
            if(cnt>=n/2+1)
                return a[i];
        }
        return -1;
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

