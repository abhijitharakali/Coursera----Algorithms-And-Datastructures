import java.util.Scanner;

public class FibonacciSumLastDigit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s%10);
    }

    private static long getFibonacciSum(long n) {
        long l = pisanoPeriod(10);
        n=(n+2)%l;
        return(getFibonacciHuge(n)-1);
    }
    
        private static long getFibonacciHuge(long n) {
                if(n<2){
            return (int)(n);
        }
        else{
        long[] arr = new long[(int)n+1];
        arr[0]=0;
        arr[1]=1;
        for(int i=2; i<(int)n+1; i++){
            arr[i]=(arr[i-1]+arr[i-2]);
            // System.out.println(arr[i]);
        }
        return (arr[(int)n]);
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