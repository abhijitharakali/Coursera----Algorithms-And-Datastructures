package coveringsegments;

import java.util.*;

public class CoveringSegments {

//     private static int[] optimalPoints(Segment[] segments) {
    private static void optimalPoints(int[] a,int[] b, int[] sidx) {
        int n=a.length;
        boolean skip=(0==1);
        
        if(n==1){
            int temp=findPoint(a[0],b[0]);
            System.out.println(1);
            System.out.println(temp);
            return;
        }
        else{
            int nsols=0;
            int[] points = new int[n];
            for(int h=0;h<n;h++){
                points[h]=0;
            }
            
            int cnt=0;
            while(cnt<n){

                if(cnt==n)
                    break;
                int min=a[sidx[cnt]];
                int max=b[sidx[cnt]];
                while(min<max){
                    cnt++;
                    if((cnt)==n){
                        break;
                    }
                    if(a[sidx[(cnt)]]>max){
                        break;
                    }
                    else{
                        min=a[sidx[(cnt)]];
                    }
                    if(b[sidx[(cnt)]]<max){
                        max=b[sidx[(cnt)]];
                    }
                }

                if(skip)
                    skip=skip;
                else{
                    points[nsols]=findPoint(min,max);
                    nsols++;
                }
                
                
                skip=(min==max);
            }
            System.out.println(nsols);
            for(int k=0;k<nsols;k++)
                System.out.print(points[k]+" ");
        }
    }
    
     private static List<Member> units = new ArrayList<>();
     
     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            units.add(new Member(i, a[i]));
        }
        
        units.sort(new NumberComparator());
        
        int[] sidx = new int[n];
        int j=0;
        for (Member element : units) {
            sidx[j] = element.index;
            j++;
        }
        
        optimalPoints(a,b,sidx);
    }

    private static int findPoint(int i, int i0) {
        if(i==i0)
            return i;
        else if((i0-i)==1)
            return i0;
        else{
            int j=i+1;
            return j;
        }
    }
} 
class Member{
    public int index;
    private int value;

    public Member(int index, int value){
        this.index = index;
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

}

class NumberComparator implements Comparator<Member>{
    @Override
    public int compare(Member a, Member b) {
        return a.getValue() - b.getValue();
    }
     
}
 
