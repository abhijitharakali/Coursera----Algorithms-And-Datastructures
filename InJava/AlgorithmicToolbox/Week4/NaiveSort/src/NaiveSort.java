import java.io.*;
import java.util.*;

public class NaiveSort {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
      //write your code here

        int x = a[l];
        int m1 = l;
        int m2=l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] < x) {
                m1++;
                m2++;
                int t = a[i];
                a[i] = a[m1];
                a[m1] = t;
            }
            else if(a[i]==x){
                m2++;
                int s = a[i];
                a[i] = a[m2];
                a[m2] = s;
            }
        }
        int t = a[l];
        a[l] = a[m1];
        a[m1] = t;

      int[] m = {m1, m2};
      return m;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

            private static void regularSort(int[] c){
            int n = c.length;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    if(c[i]>c[j]){
                        int t=c[i];
                        c[i]=c[j];
                        c[j]=t;
                    }
                }
            }
        }
    
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        // randomizedQuickSort(a, 0, n - 1);
        regularSort(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
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

