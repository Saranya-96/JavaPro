import java.util.*;
import java.io.*;

class Solution{
    public static void main(String []argh){
        Scanner s = new Scanner(System.in);
        int t=s.nextInt();
        for(int i=0;i<t;i++){
            int a = s.nextInt();
            int b = s.nextInt();
            int n = s.nextInt();
            int c = a;
            for(int j=0;j<n;j++)
            {
                c+=(Math.pow(2,j)*b);
                System.out.print(c+" ");
            }
            System.out.println();
        }
        s.close();
    }
}
