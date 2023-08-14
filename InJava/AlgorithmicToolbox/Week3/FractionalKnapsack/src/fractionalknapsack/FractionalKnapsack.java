package fractionalknapsack;

import java.text.DecimalFormat;

// import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        int n=values.length;
        double[] nval = new double[n];
        
        for(int i=0;i<n;i++){
            nval[i]=(double)values[i]/(double)weights[i];
        }
        double space = (double)capacity;
        
        while(space>0){
            int maxi=get_maxi(nval);
            // System.out.println(java.util.Arrays.toString(nval));
            // System.out.println(value);
            if(nval[maxi]==0)
                return value;
            else if(weights[maxi]>space)
            {
                value=value+space*nval[maxi];
                return value;
            }
            else {
                value=value+values[maxi];
                space=space-weights[maxi];
            }
            nval[maxi]=0;
        }
        // Arrays.sort(nval, 0, n-1);
        
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        //DecimalFormat df = new DecimalFormat();
        //df.setMaximumFractionDigits(4);
        System.out.printf("%.4f",getOptimalValue(capacity, values, weights));
    }

    private static int get_maxi(double[] nval) {
        int m=nval.length;
        int ind=0;
        for(int i=0;i<m;i++){
            if(nval[i]>nval[ind])
                ind=i;
        }
        return ind;
    }

//    private static double[] delete(double[] nval, int maxi) {
//        int n=nval.length;
//        int j=0;
//        double[] temp = new double[n-1];
//        for(int i=0;i<n;i++){
//            if(i!=maxi)
//            {
//                temp[j]=nval[i];
//                j++;
//            }
//        }
//        return temp;
//    }
} 
