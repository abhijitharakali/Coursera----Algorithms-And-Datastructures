import java.util.*;

public class TreeHeightUsingNodes_BreadthFirst {
    
    private static class Node  
{ 
    private int data; 
    private ArrayList<Node> children;
    private Node parent;
   
    public Node(int item)  
    { 
        data = item; 
        children = new ArrayList<>();
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
            
            int root_index = 0;
            
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
                if (parent[i] == -1){
                    root_index = i;
                }
                else
                {
                    nodes[i].setParent(nodes[parent[i]]);
                    nodes[parent[i]].addChild(nodes[i]);
                }
                
            }            
            
            int maxHeight = 1; // We'll always have the root node so minimum depth is 1
            
            ArrayList<Node> currentParentNodes = new ArrayList<>();
            ArrayList<Node> currentChildNodes = new ArrayList<>();
            
            currentParentNodes.add(nodes[root_index]);
            currentChildNodes.addAll(nodes[root_index].children);
            
            while(!currentChildNodes.isEmpty()){
                
                maxHeight = maxHeight + 1;
                
                currentParentNodes.clear();
                currentParentNodes.addAll(currentChildNodes);
                currentChildNodes.clear();
                
                for (int j =0; j < currentParentNodes.size(); j++){
                    Node tempNode = currentParentNodes.get(j);
                    
                    if(!tempNode.children.isEmpty()){
                        currentChildNodes.addAll(tempNode.children);
                    }
                    
                }
                
            }
            System.out.println(maxHeight);
//         // Code below was used with the leaf indices approach

//            int maxHeight = 0;
//            ArrayList<Integer> leaf_indices = new ArrayList<>();
//            for (int j = 0; j<n; j++){
//                if(nodes[j].children.isEmpty()){
//                    leaf_indices.add(j);
//                }
//            }
//            
//            int m = leaf_indices.size();
                        
//            for(int k = 0; k < m; k++){
//                int temp = 1;
//                int leaf_index = leaf_indices.get(k);
//                Node curNode = nodes[leaf_index];
//                
//                while(curNode.parent != null){
//                    temp = temp + 1;
//                    curNode = curNode.parent;
//                }
//                maxHeight = maxHeight > temp ? maxHeight : temp;
//            }
//            System.out.println(maxHeight);
    }
    
}
        

