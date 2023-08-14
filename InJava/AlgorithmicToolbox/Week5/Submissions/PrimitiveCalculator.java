import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] minVals = new int[n+1];
        minVals[0]=0;
        if(n>0)
            minVals[1]=1;
        if(n>1)
            minVals[2]=1;
        if(n>2)
            minVals[3]=1;
        if(n>4)
            minVals[4]=2;
        if(n>5){
            for(int i=5;i<(n+1);i++){
                minVals[i]=1+minVals[i-1];
                if(i%2==0){
                    if(minVals[i]>(1+minVals[i/2]))
                        minVals[i]=1+minVals[i/2];
                }
                if(i%3==0){
                    if(minVals[i]>(1+minVals[i/3]))
                        minVals[i]=1+minVals[i/3];
                }
            }
        }
        while(n>=1){
            sequence.add(n);
            boolean m2 = (n%2==0);
            boolean m3 = (n%3 == 0);
            if(m2&&m3){
                int k1 = minVals[n-1];
                int k2 = minVals[n/2];
                int k3 = minVals[n/3];
                if(k1<k2){
                    if(k1<k3)
                        n=n-1;
                    else
                        n=n/3;
                }
                else{
                    if(k2<k3)
                        n=n/2;
                    else
                        n=n/3;
                }
            }
            else if(m2&&(!m3)){
                int k1 = minVals[n-1];
                int k2 = minVals[n/2];
                if(k1<k2)
                    n=n-1;
                else
                    n=n/2;
            }
            else if(m3&&(!m2)){
                int k1 = minVals[n-1];
                int k3 = minVals[n/3];
                if(k1<k3)
                    n=n-1;
                else
                    n=n/3;
            }
            else
                n=n-1;
            }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

