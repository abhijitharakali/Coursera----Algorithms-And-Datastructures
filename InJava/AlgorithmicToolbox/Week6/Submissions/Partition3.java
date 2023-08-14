import java.util.*;

public class Partition3 {
    private static int partition3(int[] A) {
        int n=A.length;
        int sum=0;
        for(int i=0;i<n;i++)
            sum=sum+A[i];
        if((sum%3)!=0){
            // System.out.println("a");
            return 0;
        }
        sum=sum/3;

        return partition(A,(n-1),sum,sum,sum);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }

    private static int partition(int[] A, int n, int p0, int p1, int p2) {
    
    if((p0==0)&&(p1==0)&&(p2==0))
        return 1;    
        
    if(n<0){
        // System.out.println("b");
        return 0;
    }

    int a=0;
    int b=0;
    int c=0;
    if(p0>=A[n])
        a=partition(A,n-1,p0-A[n],p1,p2);
    if((a==0)&&(p1>=A[n])){
        b=partition(A,n-1,p0,p1-A[n],p2);
    }
    if((a==0)&&(b==0)&&(p2>=A[n])){
        c=partition(A,n-1,p0,p1,p2-A[n]);
    }
    if((a==1)||(b==1)||(c==1))
        return 1;
    else{
        // System.out.println("c");
        return 0;
    }
    }
}

