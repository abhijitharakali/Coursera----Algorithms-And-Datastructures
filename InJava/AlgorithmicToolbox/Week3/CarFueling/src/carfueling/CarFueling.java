package carfueling;

import java.util.*;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        int n=stops.length;
        if(stops[0]>tank)
            return -1;
        else if(dist/tank>(n+1))
            return -1;
        else if(dist>stops[n-1]+tank)
            return -1;
        else if(tank>=dist)
            return 0;
        
        int cnt=0;
        int lf=0;
        
        for(int i=0;i<n;i++){
            if((i>0)&&(stops[i]>stops[i-1]+tank))
                return -1;
            if((i>0)&&(stops[i]>lf+tank)){
                lf=stops[i-1];
                cnt++;
            }
            }
        if(dist>lf+tank){
            // lf=stops[n-1];
            cnt++;
        }
        
        return cnt;
    }
//    private static int findUniqueElements(int[] arr){
//        int unq;
//        HashMap<Integer,Integer> hm = new HashMap<>(); 
//        for (int i = 0; i < arr.length; i++) { //
//            hm.put(arr[i], i); 
//        } 
//        unq=hm.keySet().size();
//        return unq;
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
