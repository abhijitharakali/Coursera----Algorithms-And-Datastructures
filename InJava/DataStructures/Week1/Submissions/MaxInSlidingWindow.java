import java.util.*;


public class MaxInSlidingWindow {
    
public static void printMaxInSlidingWindow(int[] inArr, int n, int k)  {
    
    Deque<Integer> slidingWindow = new LinkedList<Integer>();
    
    for (int i = 0; i < k; i++){
        while(!slidingWindow.isEmpty() && inArr[i] >= inArr[slidingWindow.peekLast()]){
            slidingWindow.removeLast();
        }
        slidingWindow.addLast(i);
    }
    
    for(int i=k; i < n; i++){
        
        System.out.print(inArr[slidingWindow.peek()] + " ");
        
        while(!slidingWindow.isEmpty() && slidingWindow.peek() <= i-k){
            slidingWindow.removeFirst();
        }
        
        while(!slidingWindow.isEmpty() && inArr[i] >= inArr[slidingWindow.peekLast()]){
            slidingWindow.removeLast();
        }
        slidingWindow.addLast(i);
    }
    System.out.print(inArr[slidingWindow.peek()]);
}  
    
public static void main(String[] args) 
    { 
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] inArr = new int[n];
        for ( int i = 0; i < n; i++ ) {
            inArr[i] = in.nextInt();
        }
        int k = in.nextInt();
        printMaxInSlidingWindow(inArr, n, k); 
    } 
    
}