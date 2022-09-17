package Project1;
import java.util.*;
import java.util.regex.*;

public class ProjectMain{

	public static void main(String[] args)
	{
		// Main class
		int flag=0,choice=0,pin=0;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("\t\t\t\t **************** Welcome to Pizza Mania ****************\n");
		System.out.println("\t\t\t\t ****************  Established Since 1980  ****************\n");
		System.out.println("\t\t\t\t *********** !!FAST!!**!!Fresh!!**!!Delicious!! ***********\n");
		
		System.out.println("\n1. Make an Order\n2. Manager Login\n3. Exit");
		do			// Validation for choice
		{
			try
			{
				System.out.print("\nEnter your choice: ");
				choice=sc.nextInt();
				Pattern pat = Pattern.compile("[1,2,3]");
				Matcher mat = pat.matcher(String.valueOf(choice));
				if(mat.find())
				{
					flag=0;
				}
				else
				{
					System.out.println("\nPlease enter correct choice.");
					flag=1;
				}
			}
			catch(InputMismatchException ime)
			{
				System.out.println("\nPlease enter digits only.");
				sc.nextLine();
				flag=1;
			}
		}while(flag==1);			// loop will run until flag=1 . flag = 0 only when input is valid
		
		switch(choice)		// choice handelled through switch case
		{
		case 1:
			{
				CustomerDetails custDetails = new CustomerDetails();  // Object creation of CustomerDetails class.
				custDetails.Details();								  // Function called from CustomerDetails class.
				PizzaOrder pizzaorderprice = new PizzaOrder();		  // Object creation of PizzaOrder class.
				pizzaorderprice.orderPizza();						  // Function called from PizzaOrder class.
			}
				break;
		case 2:
			{
				do		// validation for 4 digit pin
				{
					try
					{
						System.out.print("\nEnter the 4 digit pin: ");
						pin = sc.nextInt();
					
						if(pin==1234)
						{
							flag=0;
						}
						else
						{
							System.out.println("\nPlease enter correct pin.");
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
				
				if(flag==0)
				{
					Project_Database.menudatabase();  // Calling menudatabase function of Project_Database class
					flag=1;
				}
			}
				break;
		case 3:
			{
				System.out.println("\nYou have chose to exit. Thank you for visiting.");
			}
				break;
		
		}
		
		
	}

}
