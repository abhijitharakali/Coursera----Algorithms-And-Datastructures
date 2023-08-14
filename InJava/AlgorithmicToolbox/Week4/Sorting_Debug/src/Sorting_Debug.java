import java.io.*;
import java.util.*;
// import java.util.Arrays;

public class Sorting_Debug {
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

    
    private static int[] partition33(int[] a, int l, int r) {
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
    
        private static void randomizedQuickSort1(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int m = partition2(a, l, r);
        randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);
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
        // FastScanner scanner = new FastScanner(System.in);
        // int n = scanner.nextInt();
        int n = random.nextInt(100000);
        while(true){
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(1000000000);
            b[i] = a[i];
            c[i] = a[i];
        }
        randomizedQuickSort(b, 0, n - 1);
        Arrays.sort(c);
        boolean x = true;
        for (int i = 0; i < n; i++) {
            // System.out.print(a[i] + " ");
            if(b[i]!=c[i]){
                // System.out.println(b[i]+" "+c[i]);
                System.out.println(n);
                myPrint(a);
                myPrint(b);
                myPrint(c);
                x = false;
                break;
            }
        }
        // System.out.println(" ");
        if(x == false){
            // for(int j=0;j<n;j++){
            //     System.out.print(a[j]+" ");
            // }
            break;
        }
        // System.out.println(x);
        }
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

