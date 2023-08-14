import java.util.Scanner;

public class FibonacciLastDigit {

    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         long n = in.nextInt();
         System.out.println(CalcFiblast(n));
        // TODO code application logic here

    }
    
        private static int CalcFiblast(long n) {
        if((n%60)<2){
            return (int)(n%60);
        }
        else{
        int m = (int)(n%60);
        long[] arr = new long[m+1];
        arr[0]=0;
        arr[1]=1;
        for(int i=2; i<m+1; i++){
            arr[i]=arr[i-1]+arr[i-2];
            // System.out.println(arr[i]);
        }
        return (int)(arr[m]%10);
        }
        }
}