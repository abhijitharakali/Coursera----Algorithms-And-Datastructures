import java.util.*;
import java.io.*;

// Stolen from https://github.com/pmatushkin/dsa02_pa01/blob/master/src/tree_height/tree_height.java

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int parent[];
        // array to store the heights of subtrees
        int heights[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            heights = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {
            // Replace this code with a faster implementation
            int maxHeight = 0;

            for (int vertex = 0; vertex < n; vertex++) {
                // check if the current subtree was already measured;
                // measure if it wasn't
                if (0 == heights[vertex]) {
                    heights[vertex] = getHeight(vertex);
                }

                int height = heights[vertex];
                maxHeight = maxHeight > height ? maxHeight : height;
            }

            return maxHeight;
        }

        int getHeight(int i) {
            // if this subtree is not yet measured, calculate and remember its height
            if (0 == heights[i]) {
                int parent_i = parent[i];

                if (-1 == parent_i) {
                    heights[i] = 1;
                } else {
                    heights[i] = 1 + getHeight(parent_i);
                }
            }

            return heights[i];
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}