package Project1;
import java.util.*;
import java.util.regex.*;
import java.io.IOException;

public class CustomerDetails{
	static String name;
	static String Address;
	int flag=1;
	static long length = 0,phoneNum=0;
	
	void Details() {  // Details function defined to collect details of user.
		
		Scanner sc = new Scanner(System.in);	// scanner object created for user input
		Scanner sc1 = new Scanner(System.in);

		
		
		do			// Input validation for name using Regular Expression (Regex).
		{
			try 
			{		
				Pattern pat = Pattern.compile("[0-9]");
				System.out.print("\nPlease enter your name: ");
				name = sc.nextLine();
				Matcher mat = pat.matcher(name);
				
				if(mat.find())
				{
					System.out.println("\nPlease enter a valid name (Alphabets only).\n");
					flag=1;
				}
				else if(name.length()==0||name==null)
				{
					System.out.println("\nYou have not entered anything in Name.");
					flag=1;
				}
				else
				{
					flag=0;
				}

			} 
			catch (InputMismatchException ime) 			// InputMismatchException handling
			{
				System.out.println("Please Enter a valid name");
				flag=1;
			}
		}while(flag==1);
		
		do			// Input validation for phone number.
		{
		try
		{
		while (length != 10) {
				System.out.print("\nPlease enter your contact number: ");
			
				phoneNum = sc.nextLong();
			
				length = (long) (Math.log10(phoneNum) + 1);			// Check the length of phone number and store it in variable length.

				if (length != 10) 
				{
					System.out.println("\nPlease enter a valid 10 digit mobile number");
				}
				
		}
		flag=1;
		}
		catch (InputMismatchException ie)			// InputMismatchException handling
		{
			System.out.println("\nPlease enter only digits.");
			sc.nextLine();
			flag=0;
			continue;
		}
		}while(flag==0);
		
		do
		{
			try 				// Input validation for address.
			{
				System.out.print("\nPlease enter your Address: ");
				Address = sc1.nextLine();
				
				if(Address.length()==0||Address==null)
				{
					System.out.println("\nYou have not entered anything in Address.");
					flag=1;
				}
				else
				{
					flag=0;
				}
	
			}
			catch (InputMismatchException im) 		// InputMismatchException handling
			{
				System.out.println("Please enter a valid address");
			}
		}while(flag==1);
		System.out.println("\nHello " + name + "!!!. Welcome to Pizza Station !!!\n");
		System.out.println("Get Best Pizza!! Get Best Value!!");
		

	}
	
}
