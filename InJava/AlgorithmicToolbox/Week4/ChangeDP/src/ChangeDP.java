import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        int[] change = new int[m+1];
        change[0]=0;
        if(m>0)
            change[1]=1;
        if(m>1)
            change[2]=2;
        if(m>2)
            change[3]=1;
        if(m>3)
            change[4]=1;
        if(m>4){
            for(int i=5;i<(m+1);i++){
                change[i]=1+min(change[i-1],change[i-3],change[i-4]);
            }
        }
        return change[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }

    private static int min(int i, int i0, int i1) {
    if(i<i0){
        if(i<i1)
            return i;
        else
            return i1;
    }
    else{
        if(i0<i1)
            return i0;
        else
            return i1;
    }
    }
}

