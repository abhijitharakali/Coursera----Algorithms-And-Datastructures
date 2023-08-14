package lcm;

import java.util.Scanner;

public class LCM {

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long a = scanner.nextLong();
    long b = scanner.nextLong();

    System.out.println(find_lcm(a, b));
  
    }

    private static long find_lcm(long a, long b) {
        long d = find_gcd(a,b);
        if(a==0)
            return 0;
        else if(b==0)
            return 0;
        else{
            long c=a/d;
            long f=b/d;
            return c*d*f;
        }
    }
        private static long find_gcd(long a, long b) {
        if(a==0)
            return b;
        else if(b==0)
            return a;
        else if(a==b)
            return a;
        else if(a>b)
            return find_gcd(a%b,b);
        else
            return find_gcd(a,b%a);        
    }
}