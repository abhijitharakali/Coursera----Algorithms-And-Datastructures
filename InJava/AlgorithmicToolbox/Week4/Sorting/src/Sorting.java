import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

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
        //System.out.println("Enter the recursion");
        //myPrint(a);
        
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        
        //myPrint(a);
        //System.out.println(k);
        //System.out.println(l+" "+r);
        int[] m = partition3(a, l, r);
        //myPrint(a);   
        

        //myPrint(m);
        //System.out.print(l+" "+(m[0]-1)+" ");
        //System.out.println((m[1]+1)+" "+r);

        
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        //System.out.println("Final Answer");
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

