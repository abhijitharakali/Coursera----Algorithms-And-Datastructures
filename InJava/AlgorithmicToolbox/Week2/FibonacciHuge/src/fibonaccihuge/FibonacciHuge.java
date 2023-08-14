package fibonaccihuge;

import java.util.Scanner;

public class FibonacciHuge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        long l = pisanoPeriod(m);
        n=n%l;
        System.out.println(getFibonacciHuge(n, m));
        // System.out.println(l);
    
    }

    private static long getFibonacciHuge(long n, long m) {
                if(n<2){
            return (int)(n);
        }
        else{
        long[] arr = new long[(int)n+1];
        arr[0]=0;
        arr[1]=1;
        for(int i=2; i<(int)n+1; i++){
            arr[i]=(arr[i-1]+arr[i-2])%m;
            // System.out.println(arr[i]);
        }
        return arr[(int)n];
        }
    }

    private static long pisanoPeriod(long m) {
        long p = m*m;
        long a=0; long b= 1;long temp = a;
        for(long i = 0; i<p;i++){
            temp=a%m;
            a=b%m;
            b=(b+temp)%m;
            if((a==0)&&(b==1))
                return i+1;
        }
        return p;
    }
}