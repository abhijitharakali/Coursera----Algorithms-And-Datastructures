import java.util.*;
import static java.lang.System.nanoTime;

public class testPQueue{
    
    public static class Entry implements Comparable<Entry>
        {
            long cost;
            int node;
          
            public Entry(int node,long cost)
            {
                this.cost = cost;
                this.node = node;
            }
         
            public int compareTo(Entry other)
            {
                return cost < other.cost ? -1 : cost > other.cost ? 1 : 0;
            }
        }
    
    public static void main(String args[]){
        
//        Random r = new Random();
//        
//        long t0 = System.nanoTime();
//        
//        PriorityQueue<Entry> q = new PriorityQueue<>();
//        for(int j = 0; j < 1000; j++){
//            for(int i = 0;i < 1000000;i++){
//                q.add(new Entry(r.nextInt(),r.nextLong()));
//            }
//            q.clear();
//        }
//        
        long t1 = System.nanoTime();
//        System.out.println((t1-t0)/1000000000);
        
        PriorityQueue<Entry> queue;
        queue = new PriorityQueue<>();
        Random r = new Random();
        for(int i = 0; i < 100000000; i++){
            queue.add(new Entry(i,r.nextLong()));
        }
        while(queue.isEmpty() == false){
            Entry etemp = queue.poll();
        }
        long t2 = System.nanoTime();
        System.out.println((t2-t1)/1000000000);
//        PriorityQueue<Entry> queue;
//        queue = new PriorityQueue<>();
//        Random r = new Random();
//        queue.add(new Entry(0,r.nextLong()));
//        queue.add(new Entry(1,r.nextLong()));
//        queue.add(new Entry(2,r.nextLong()));
//        queue.add(new Entry(3,r.nextLong()));
//        queue.add(new Entry(4,r.nextLong()));
//        queue.add(new Entry(5,r.nextLong()));
//        queue.add(new Entry(6,r.nextLong()));
//        queue.add(new Entry(7,r.nextLong()));
//        while(queue.isEmpty() == false){
//            System.out.println(queue.poll().cost);
//        }
    }
}
