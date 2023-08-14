package partitionofsorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartitionOfSorting {

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
    
       private static int[] partition3(int[] a, int l, int r) {
        int x = a[l];
        int m1 = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                m1++;
                int t = a[i];
                a[i] = a[m1];
                a[m1] = t;
            }
        }
        int t = a[l];
        a[l] = a[m1];
        a[m1] = t;
        int m2 = m1;
        for(int k = 0; k < m2; k++){
            //myPrint(a);System.out.println(m1);

            if(a[k]==x){
                while((a[m1]==x)&&(m1>0))
                    m1--;
                if(k>=m1){
                    m1++;
                    break;
                }
                int s = a[k];
                a[k] = a[m1];
                a[m1] = s;
                //myPrint(a);
                //System.out.println("Maybe a mistake");
            }
        }
        int[] m = {m1, m2};
        return m;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] m = partition3(a, 0, n-1);

        System.out.println(m[0]+" "+m[1]);
        myPrint(a);
    }

    private static void myPrint(int[] a) {
    for(int i=0;i<a.length;i++){
        System.out.print(a[i]+" ");
    }
    System.out.println();
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