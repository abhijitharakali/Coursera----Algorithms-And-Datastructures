import java.util.*;

public class testTreeSet{
    
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
        
        TreeSet<Entry> queue = new TreeSet<>();
        queue.add(new Entry(1,10));
        queue.add(new Entry(2,20));
        queue.add(new Entry(1,5));
        
        Iterator itr = queue.iterator();
        while(itr.hasNext()){
            Entry temp = (Entry)itr.next();
            System.out.println(temp.cost);
        }
        
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

