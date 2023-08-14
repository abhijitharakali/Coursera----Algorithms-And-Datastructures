import java.util.*;

class StackWithMaxFunction {
    
    public static class stackMax {
        static Stack<Integer> data = new Stack<Integer>();
        static Stack<Integer> currentMax = new Stack<Integer>();
        
        public static void push(int d) {
            data.push(d);
            if(currentMax.isEmpty()){
                currentMax.push(d);
                return;
            }
            
            if(d >= currentMax.peek()) {
                currentMax.push(d);
            }
            return;
        }
        
        public static int getMax() {
            return currentMax.peek();
        }
        
        public static void pop() {
            int temp = data.peek();
            data.pop();
            if(temp == currentMax.peek()){
                currentMax.pop();
            }
        }
    }
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        stackMax s = new stackMax();
        Queue<Integer> maxOut = new LinkedList<>(); 
        
        for(int j = 0; j < n+1; ++j){
            
            String[] inp = in.nextLine().split(" ");
            String op = inp[0];
            
            if("push".equals(op)) {
                int pushVal = Integer.parseInt(inp[1]);
                s.push(pushVal);
            }
            
            else if("pop".equals(op)) {
                s.pop();
            }
            
            else if("max".equals(op)) {
                maxOut.add(s.getMax());
                // System.out.println(s.getMax());
            }
            
        }
        
        if(maxOut.size() > 0){
            int m = maxOut.size();
            for(int k=0; k < m; k++){
                System.out.println(maxOut.remove());
            }
        }
    }
    
}

