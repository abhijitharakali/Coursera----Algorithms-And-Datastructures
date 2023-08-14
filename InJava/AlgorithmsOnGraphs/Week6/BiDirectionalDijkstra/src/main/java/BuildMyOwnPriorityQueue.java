import java.util.*;
import static java.lang.System.nanoTime;

public class BuildMyOwnPriorityQueue{
    
    public static class Entry implements Comparable<Entry>
    {
        long cost;
        int node;
          
        public Entry(int node,long cost){
            this.cost = cost;
            this.node = node;
        }
         
        public int compareTo(Entry other){
            return cost < other.cost ? -1 : cost > other.cost ? 1 : 0;
        }
    }    
    
    public static class myQueue {
        int size;
        ArrayList<Entry> h = new ArrayList<>();
        
        public myQueue(){
            this.h = h;
            this.size = this.size;
        }
        
        private int parent(int i){
            if(i > 0){
                return (i-1)/2;
            }
            else{
                return 0;
            }
        }
        private int left(int i){
            return (2*i+1);
        }
        private int right(int i){
            return (2*i+2);
        }
        private void swap(int i, int j){
            Entry etemp = h.get(i);
            h.set(i, h.get(j));
            h.set(j, etemp);
        }
        private void siftup(int i){
            if(i < size){
                while((i > 0) && (h.get(parent(i)).cost > h.get(i).cost )){
                    swap(i,parent(i));
                    i = parent(i);
                }
            }
        }
        private void siftdown(int i){
            int maxindex = i;
            int l = left(i);
            int r = right(i);
            if((l < size)){
                if((h.get(l).cost < h.get(maxindex).cost)){
                    maxindex = l;
                }
            }
            if((r < size)){
                if((h.get(r).cost < h.get(maxindex).cost)){
                    maxindex = r;
                }
            }
            if(i != maxindex){
                swap(i,maxindex);
                siftdown(maxindex);
            }
        }
        public void add(Entry p){
            h.add(p);
            size = size+1;
            siftup(size-1);
        }
        public Entry poll(){
            Entry result = h.get(0);
            h.set(0, h.get(size-1));
            h.remove(size-1);
            size = size - 1;
            siftdown(0);
            return result;
        }
        public boolean isEmpty(){
            return h.isEmpty();
        }
        public void clear(){
            size = 0;
            h.clear();
        }
    }
    
    
    public static void main(String args[]){
        
        long t1 = System.nanoTime();
        myQueue queue = new myQueue();
        Random r = new Random();
        for(int i = 0; i < 100000000; i++){
            queue.add(new Entry(i,r.nextLong()));
        }
        while(queue.isEmpty() == false){
            Entry etemp = queue.poll();
        }
        long t2 = System.nanoTime();
        System.out.println((t2-t1)/1000000000);
    }
    
}

