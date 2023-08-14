import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhijitharakali
 */

public class TreeHeight_Main {
    
public static long[] heights;
public static long[] parent;
    
	static public void main(String[] args) {
            int n;
            Scanner in = new Scanner(System.in);
            n = in.nextInt();
            parent = new long[n];
            heights = new long[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextLong();
                if (parent[i] == -1) {
                    heights[i] = 1;
                }
                else {
                    heights[i] = 0;
                }
            }
            
            long maxHeight = 0;
            
            for (int j = 0; j < n; j++) {
                
                if (heights[j] == 0) {
                    heights[j] = calc_height(j);
                }
                
                if (maxHeight < heights[j]) {
                    maxHeight  = heights[j];
                }
                
            }
            System.out.println(maxHeight);
            
	}

    private static long calc_height(int j) {
        if (parent[j] == -1) {
            heights[j] = 1;
        }
        else {
            heights[j] = 1 + calc_height( (int) parent[j]);
        }
        return heights[j];
    }

    
}
