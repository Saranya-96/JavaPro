import java.util.Scanner;
public class Hollopa1 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
        System.out.println("Input: ");
        int n=s.nextInt();
        if(n%2!=0)
        {
            System.out.println("Weird");
        }
        else{
            if( n>=2 && n<=5 )
            System.out.println("Not Weird");
            else if(n>=6 && n<=20)
            System.out.println("Weird");
            else if(n>20)
            System.out.println("Not Weird");
        }
	}
}
