import java.util.*;

public class MyOwnMap{
public static class Dmap {
        HashSet<Integer> keys = new HashSet<>();
        int[] indx = new int[1000000];
        ArrayList<Long> distances = new ArrayList<>();
        int k;
        long d;
        
        public Dmap(){
        }
        public boolean containsKey(int k){
            return keys.contains(k);
        }
        public void put(int k, long d){
            if(keys.contains(k)){
                int l = indx[k];
                distances.set(l, d);
            }
            else{
                int l = distances.size();
                indx[k] = l;
                distances.add(d);
                keys.add(k);
            }
        }
        public long get(int k){
            if(keys.contains(k)){
                int m = indx[k];
                return distances.get(m);
            }
            else{
                return -1;
            }
        }
    }    
    public static void main(String args[]) {
        Random r = new Random();
        Dmap distf = new Dmap();
        long[] d = new long[10];
        for(int i = 0; i<10;i++){
            long temp = r.nextLong();
            distf.put(i, temp);
            d[i] = temp;
        }
        for(int j = 0; j < 10; j++){
            System.out.println(distf.get(j)+" "+d[j]);
        }
        System.out.println(distf.get(10));
    }
}
