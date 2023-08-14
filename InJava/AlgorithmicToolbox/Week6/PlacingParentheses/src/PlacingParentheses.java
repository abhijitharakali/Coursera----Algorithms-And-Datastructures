import java.util.Scanner;

public class PlacingParentheses {
    public static long getMaximValue(String exp) {
        if(exp.length()==1){
            long temp = exp.charAt(0)-'0';
            return temp;
        }
        int nexp = exp.length();
        int n = (nexp+1)/2;
        
        String op = "";
        for(int i=1;i<n;i++){
            char c = exp.charAt(2*i-1);
            op = op + String.valueOf(c);
        }
        
        long[] d = new long[n];
        long[][] min = new long[n][n];
        long[][] max = new long[n][n];
        for(int i=0;i<n;i++){
            min[i][i]=d[i];
            max[i][i]=d[i];
        }
        for(int s=0;s<n-1;s++){
            for(int i=0;i<n-s;i++){
                int j=i+s;
                long[] mm = new long[2];
                mm = MinAndMax(i+1,j+1);
                min[i][j]=mm[0];
                max[i][j]=mm[1];
            }
        }
        return max[0][n-1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }

    private static long[] MinAndMax(int i, int j) {
        long mn= Long.MIN_VALUE;
        long mx= Long.MAX_VALUE;
        for(k=i-1;k<j-1;k++){
            
        }
    }
}

