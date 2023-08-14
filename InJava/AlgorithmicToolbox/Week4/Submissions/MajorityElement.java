import java.util.*;
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
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, (a.length-1)) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int getRepetitions(int[] a, int left, int right, int key) {
        int cnt=0;
        for(int i=left;i<right+1;i++){
            if(a[i]==key)
                cnt++;
        }
        return cnt;
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

