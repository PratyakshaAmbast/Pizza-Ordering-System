package Project1;
import java.util.*;
import java.util.regex.*;

public class Feedback {
	
	static void userfeedback() {		// function declared to take user feedback
		int flag=0,rating=0;
		Scanner sc = new Scanner(System.in);		// scanner class object declared to take user input
		do											// validation for rating choice
		{
			try
			{
				Pattern pat = Pattern.compile("[1,2,3,4,5]");
				System.out.print("\nHow much would you like to rate on the scale from 1 to 5: ");
				rating = sc.nextInt();
				Matcher mat = pat.matcher(String.valueOf(rating));
				
				if(mat.find())
				{
					flag=0;
				}
				else
				{
					System.out.println("\nPlease enter correct input.");
					flag=1;
				}
				
			}
			catch(InputMismatchException ime)
			{
				System.out.println("\nPlease enter digits only.");
				sc.nextLine();
				flag=1;
			}
		}while(flag==1);
		
		switch(rating) 			// switch case used to display appropriate message for rating chosen.
		{
			case 1:
				System.out.println("\nVery Poor User Experience! We would improve our service next time!\n");
				break;
			case 2:
				System.out.println("\nPoor User Experience! We would improve our service next time!\n");
				break;
			case 3:
				System.out.println("\nAverage User Experience! We will get better!\n");
				break;
			case 4:
				System.out.println("\nGood User Experience! Almost Perfect!\n");
				break;
			case 5:
				System.out.println("\nExcellent User Experience! Thank you! Visit Again!\n");
				break;
		}
	
	}
}
