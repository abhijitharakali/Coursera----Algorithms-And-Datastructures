import java.util.*;

public class TreeHeight_UsingNodes {
    
    private static class Node  
{ 
    private int data; 
    private ArrayList<Node> children;
    private Node parent;
   
    public Node(int item)  
    { 
        data = item; 
        children = new ArrayList<Node>();
        parent = null;
    }
    
    public int getData(){
        return data;
    }
    
    public void setData(int item){
        data = item;
    }
    
    public Node[] getChildren(){
        return children.toArray(new Node[0]);
    }
    
    public Node getParent(){
        return parent;
    }
    
    public void setChildren(Node[] inData){
        for (int i=0; i<inData.length; i++){
            children.add(inData[i]);
        }
    }
    
    public void addChild(Node child){
        children.add(child);
    }
    
    public void setParent(Node pp){
        parent = pp;
    }
    
}
    
    
    
    static public void main(String[] args) {
            int n;
            Scanner in = new Scanner(System.in);
            n = in.nextInt();
            Node nodes[] = new Node[n];
            
            int[] parent = new int[n];
            
            for (int l=0; l < n; l++){
                nodes[l] = new Node(l);
            }
            
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
                if (parent[i] != -1)
                {
                    nodes[i].setParent(nodes[parent[i]]);
                    nodes[parent[i]].addChild(nodes[i]);
                }
                
            }
            
            
            ArrayList<Integer> leaf_indices = new ArrayList<Integer>();

//            ArrayList<Integer> leaf_indices = new ArrayList<>();
//            System.out.println(n);
            for (int j = 0; j<n; j++){
                if(nodes[j].children.isEmpty()){
                    leaf_indices.add(j);
                }
            }
            
            int m = leaf_indices.size();
            
            int maxHeight = 0;
            
            for(int k = 0; k < m; k++){
                int temp = 1;
                int leaf_index = leaf_indices.get(k);
                Node curNode = nodes[leaf_index];
                
                while(curNode.parent != null){
                    temp = temp + 1;
                    curNode = curNode.parent;
                }
                maxHeight = maxHeight > temp ? maxHeight : temp;
            }
            System.out.println(maxHeight);
    }
    
}
        
