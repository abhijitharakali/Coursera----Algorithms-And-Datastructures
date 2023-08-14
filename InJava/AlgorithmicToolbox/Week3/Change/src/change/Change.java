package change;

import java.util.Scanner;

public class Change {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }

    private static int getChange(int m) {
        int temp=m%10;
        int m10=(m-temp)/10;
        int m5=(temp-temp%5)/5;
        int m1=temp%5;
        return m1+m5+m10;
    }
}