import java.util.*;

class EditDistance {
    public static int D(String s, String t){
        int a = s.length();
        int b = t.length();
        int[][] m = new int[a+1][b+1];
        m[0][0]=0;
        for(int i=1;i<(a+1);i++){
            m[i][0]=i;
        }
        for(int j=1;j<(b+1);j++){
            m[0][j]=j;
        }
        for(int j=1;j<(b+1);j++)
            for(int i=1;i<(a+1);i++){
                int ins = 1+m[i][j-1];
                int del = 1+m[i-1][j];
                int mtc = m[i-1][j-1];
                int mm = 1+m[i-1][j-1];
                char ci = s.charAt(i-1);
                char cj = t.charAt(j-1);
                if(ci==cj){
                    m[i][j]=min(ins,del,mtc);
                }
                else{
                    m[i][j]=min(ins,del,mm);
                }
            }
        return m[a][b];
    }
  public static int EditDistance(String s, String t) {
        int a = s.length();
        int b = t.length();
        int[][] m = new int[a+1][b+1];
        m[0][0]=0;
        for(int i=1;i<(a+1);i++){
            m[i][0]=i;
        }
        for(int j=1;j<(b+1);j++){
            m[0][j]=j;
        }
        for(int j=1;j<(b+1);j++)
            for(int i=1;i<(a+1);i++){
                int ins = 1+m[i][j-1];
                int del = 1+m[i-1][j];
                int mtc = m[i-1][j-1];
                int mm = 1+m[i-1][j-1];
                char ci = s.charAt(i-1);
                char cj = t.charAt(j-1);
                if(ci==cj){
                    m[i][j]=min(ins,del,mtc);
                }
                else{
                    m[i][j]=min(ins,del,mm);
                }
            }
        return m[a][b];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

    private static int min(int ins, int del, int mtc) {
        int smallest = ins;
        if (smallest > del) smallest = del;
        if (smallest > mtc) smallest = mtc;
        return smallest;
    }

}
