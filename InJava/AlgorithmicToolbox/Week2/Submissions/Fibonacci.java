// package fibonacci;


import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         int n = in.nextInt();
         System.out.println(CalcFib(n));
        // TODO code application logic here

    }

    private static long CalcFib(int n) {
        if(n<2){
            return (long)n;
        }
        else{
        long[] arr = new long[n+1];
        arr[0]=0;
        arr[1]=1;
        for(int i=2; i<n+1; i++){
            arr[i]=arr[i-1]+arr[i-2];
            // System.out.println(arr[i]);
        }
        return arr[n];
        }
        }
}
