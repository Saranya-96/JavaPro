Call Taxi Booking Application program - zoho interview question
 Design a Call taxi booking application
-There are n number of taxi’s. For simplicity, assume 4. But it should work for any number of taxi’s.
-The are 6 points(A,B,C,D,E,F)
-All the points are in a straight line, and each point is 15kms away from the adjacent points.
-It takes 60 mins to travel from one point to another
-Each taxi charges Rs.100 minimum for the first 5 kilometers and Rs.10 for the subsequent kilometers.
-For simplicity, time can be entered as absolute time. Eg: 9hrs, 15hrs etc.
-All taxi’s are initially stationed at A.
-When a customer books a Taxi, a free taxi at that point is allocated
-If no free taxi is available at that point, a free taxi at the nearest point is allocated.
-If two taxi’s are free at the same point, one with lower earning is allocated
-Note that the taxi only charges the customer from the pickup point to the drop point. Not the distance it travels from an adjacent point to pickup the customer.
-If no taxi is free at that time, booking is rejected
Design modules for
1)    Call taxi booking 
Input 1:
Customer ID: 1
Pickup Point: A
Drop Point: B
Pickup Time: 9

Output 1:
Taxi can be allotted.
Taxi-1 is allotted

Input 2:
Customer ID: 2
Pickup Point: B
Drop Point: D
Pickup Time: 9

Output 1:
Taxi can be allotted.
Taxi-2 is allotted 
(Note: Since Taxi-1 would have completed its journey when second booking is done, so Taxi-2 from nearest point A which is free is allocated)
Input 3:
Customer ID: 3
Pickup Point: B
Drop Point: C
Pickup Time: 12

Output 1:
Taxi can be allotted.
Taxi-1 is allotted 
2) Display the Taxi details
Taxi No:    Total Earnings:
BookingID    CustomerID    From    To    PickupTime    DropTime    Amount
   
Output:
Taxi-1    Total Earnings: Rs. 400

1     1     A    B    9    10    200
3    3    B    C    12    13    200

Taxi-2 Total Earnings: Rs. 350
2    2    B    D    9    11    350 

import java.util.*;
public class TaxiSam {
	public static void main(String[] args) {
		 Scanner s=new Scanner(System.in);
	        Taxi[] taxi=new Taxi[10];
	        int taxiId=4,bookingId=0;
	        Booking[] booking=new Booking[20];
	        taxi[0]=new Taxi();
	        taxi[1]=new Taxi();
	        taxi[2]=new Taxi();
	        taxi[3]=new Taxi();
	        int choice,availability;
	        while(true)
	        {
	            System.out.println("__________Call_Taxi_Booking___________");
	            System.out.println("1.Booking");
	            System.out.println("2.Booking Details");
	            System.out.println("3.Taxi Details");
	            System.out.println("4.Exit");
	            System.out.println("Enter ur choice:");
	            choice=s.nextInt();
	            switch(choice)
	            {
	                case 1:
	                    availability=addBooking(booking,bookingId,taxi,taxiId);
	                    if(availability!=-1)
	                    {
	                        System.out.println("Your booking is successfull with taxi no:"+(availability));
	                        booking[bookingId].dropTime();
	                        calcEarnings(booking,bookingId,taxi,availability,booking[bookingId].pickUpPoint,booking[bookingId].dropPoint);
	                        bookingId++;
	                    }
	                    else
	                    {
	                        System.out.println("No taxi is free for your pickUpTime!!");
	                        System.out.println("You may chance your pickup time and try your booking!!");
	                    }
	                    break;
	                case 2:
	                    System.out.println("BookingId    CustomerId    TaxiNo    PickupPoint    DropPoint   PickupTime    DropTime   Earnings");
	                    for(int bookingid=0;bookingid<bookingId;bookingid++)
	                    {
	                        System.out.format("%-13d%-15d%-13d%-13s%-13s%-13d%-13d%-13d",bookingid,booking[bookingid].customer_id,booking[bookingid].taxiNo,booking[bookingid].pickUpPoint,booking[bookingid].dropPoint,booking[bookingid].pickUpTime,booking[bookingid].dropTime,booking[bookingid].earning);
	                        System.out.println("");
	                    }
	                    break;
	                case 3:
	                    for(int taxiid=0;taxiid<taxiId;taxiid++)
	                    {
	                        int temp=0;
	                        System.out.println("-----------------------------------");
	                        System.out.format("%-10s%-10d%-10s%-10s","TaxiNo:",taxiid,"CurrentPosition: ",taxi[taxiid].currentPosition);
	                        System.out.println("");
	                        System.out.println("___________________________________");
	                        System.out.println("-----------------------------------");
	                        for(int bookingid=0;bookingid<bookingId;bookingid++)
	                        {
	                            if(booking[bookingid].taxiNo==taxiid)
	                            {
	                                if(temp==0)
	                                    System.out.println("BookingId    CustomerId    TaxiNo    PickupPoint    DropPoint   PickupTime    DropTime   Earnings");
	                                System.out.format("%-13d%-15d%-13d%-13s%-13s%-13d%-13d%-13d",bookingid,booking[bookingid].customer_id,booking[bookingid].taxiNo,booking[bookingid].pickUpPoint,booking[bookingid].dropPoint,booking[bookingid].pickUpTime,booking[bookingid].dropTime,booking[bookingid].earning);
	                                System.out.println("");
	                                temp=1;
	                            }
	                        }
	                        System.out.format("%-10s%-10d","TotalEarnings:",taxi[taxiid].earning);

	                        System.out.println("");
	                    }
	                    break;
	                case 4:
	                    return;
	                default:
	                    System.out.println("Please enter valid option!!!!");
	            }
	        }
	    }
	    public static int addBooking(Booking[] booking,int bookingId,Taxi[] taxi,int taxiId)
	    {
	        int availability,customer_id,pickUpTime;
	        char pickUpPoint,dropPoint;
	        Scanner s=new Scanner(System.in);
	        System.out.println("Enter Customer id:");
	        customer_id=s.nextInt();
	        System.out.println("Enter Pickup point(a-f):");
	        s.nextLine();
	        pickUpPoint=s.nextLine().charAt(0);
	        System.out.println("Enter Drop point(a-f):");
	        dropPoint=s.nextLine().charAt(0);
	        System.out.println("Enter pickup Time:");
	        pickUpTime=s.nextInt();
	        booking[bookingId]=new Booking(customer_id,pickUpPoint,dropPoint,pickUpTime);
	        availability = booking[bookingId].checkAvailability(taxi,taxiId);
	        return availability;
	    }
	    public static void calcEarnings(Booking[] booking,int bookingId,Taxi[] taxi,int taxiId,int pickUpPoint,int dropPoint)
	    {
	        int earning;
	        earning=(((Math.abs(pickUpPoint-dropPoint)*15)-5)*10)+100;
	        booking[bookingId].earning=earning;
	        taxi[taxiId].earning+=earning;
	    }
	}
	class Taxi
	{
	    char currentPosition;
	    int earning,departureTime;
	    public Taxi()
	    {
	        currentPosition='a';
	    }
	    public boolean isTaxiFree(int pickupTime)
	    {
	        if(departureTime<pickupTime)
	            return false;
	        return true;
	    }
	    public void departureTime(char pickUpPoint,char dropPoint,int pickUpTime)
	    {
	        departureTime=pickUpTime+Math.abs(pickUpPoint-dropPoint);
	    }
	} 
	class Booking
	{
	    int customer_id,pickUpTime,dropTime,taxiNo,earning;
	    char pickUpPoint,dropPoint;
	    public Booking(int cust_id,char pickupPoint,char droppoint,int pickupTime)
	    {
	        customer_id=cust_id;
	        pickUpPoint=pickupPoint;
	        dropPoint=droppoint;
	        pickUpTime=pickupTime;
	    }
	    public void dropTime()
	    {
	        this.dropTime=pickUpTime+Math.abs(pickUpPoint-dropPoint);
	    }
	    public int checkAvailability(Taxi[] taxi,int taxiCount)
	    {
	        int taxiId,taxiid=0,shortestDistance=6;
	        for(taxiId=0;taxiId<taxiCount;taxiId++)
	        {
	            if(taxi[taxiId].isTaxiFree(pickUpTime))
	            {
	                if(Math.abs(taxi[taxiId].currentPosition-pickUpPoint)<shortestDistance)
	                {
	                    taxiid=taxiId;
	                }
	                if(Math.abs(taxi[taxiId].currentPosition-pickUpPoint)==shortestDistance)
	                {
	                    if(taxi[taxiId].earning<taxi[taxiid].earning)
	                        taxiid=taxiId;
	                }
	            }
	            shortestDistance=Math.abs(taxi[taxiid].currentPosition-pickUpPoint);
	        }
	        if(shortestDistance!=6)
	        {
	            taxi[taxiid].departureTime(pickUpPoint,dropPoint,pickUpTime);
	            taxi[taxiid].currentPosition=dropPoint;
	            taxiNo=taxiid;
	            return taxiNo;
	        }
	        return -1;

	}

}


Output:


__________Call_Taxi_Booking___________
1.Booking
2.Booking Details
3.Taxi Details
4.Exit
Enter ur choice:
1
Enter Customer id:
1
Enter Pickup point(a-f):
a
Enter Drop point(a-f):
d
Enter pickup Time:
9
Your booking is successfull with taxi no:0
__________Call_Taxi_Booking___________
1.Booking
2.Booking Details
3.Taxi Details
4.Exit
Enter ur choice:
2
BookingId    CustomerId    TaxiNo    PickupPoint    DropPoint   PickupTime    DropTime   Earnings
0            1              0            a            d            9            12           500          
__________Call_Taxi_Booking___________
1.Booking
2.Booking Details
3.Taxi Details
4.Exit
Enter ur choice:
1
Enter Customer id:
2
Enter Pickup point(a-f):
c
Enter Drop point(a-f):
f
Enter pickup Time:
10
Your booking is successfull with taxi no:0
__________Call_Taxi_Booking___________
1.Booking
2.Booking Details
3.Taxi Details
4.Exit
Enter ur choice:
2
BookingId    CustomerId    TaxiNo    PickupPoint    DropPoint   PickupTime    DropTime   Earnings
0            1              0            a            d            9            12           500          
1            2              0            c            f            10           13           500          
__________Call_Taxi_Booking___________
1.Booking
2.Booking Details
3.Taxi Details
4.Exit
Enter ur choice:
4
