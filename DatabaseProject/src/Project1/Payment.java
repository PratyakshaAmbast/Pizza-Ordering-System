package Project1;
import java.util.*;
import java.util.regex.*;

public class Payment {
	
	static void makePayment() {
		int total = 0,flag=0;
		int cardChoice=0;
		
		// following 4 for loops calculate the grand total.
		if(PizzaOrder.pizzaOrderVeg.size()>0)
		{
			for (int i=0; i < PizzaOrder.totalQuantityVeg.size(); i++) 
			{
				total += PizzaOrder.vegPrices.get(i)*PizzaOrder.totalQuantityVeg.get(i);
			}
		}
		
		if(PizzaOrder.pizzaOrderNonVeg.size()>0)
		{
			for (int j = 0; j < PizzaOrder.nonVegPrices.size(); j++) 
			{
				total += PizzaOrder.nonVegPrices.get(j)*PizzaOrder.totalQuantityNonVeg.get(j);
			}
		}
		
		if(PizzaOrder.sidesOrderVeg.size()>0)
		{
			for (int j = 0; j < PizzaOrder.totalQuantityVegSides.size(); j++) 
			{
				total += PizzaOrder.sidesVegPrices.get(j)*PizzaOrder.totalQuantityVegSides.get(j);
			}
		}
		
		if(PizzaOrder.sidesOrderNonVeg.size()>0)
		{
			for (int j = 0; j < PizzaOrder.totalQuantityNonVegSides.size(); j++) 
			{
				total += PizzaOrder.sidesNonVegPrices.get(j)*PizzaOrder.totalQuantityNonVegSides.get(j);
			}
		}
		
		if(PizzaOrder.beverageorder.size()>0)
		{
			for (int j = 0; j < PizzaOrder.totalquantitybeverage.size(); j++) 
			{
				total += PizzaOrder.pricebeverage.get(j)*PizzaOrder.totalquantitybeverage.get(j);
			}
		}
		
		int userPaymentChoice = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("\nYour Total Bill is: Rs " + total+". Please refer receipt for details.");
		
			do					// userPaymentChoice validation
			{
				try
				{
				Pattern pat = Pattern.compile("[1,2,3]");
				System.out.print("\nEnter the mode of payment (1 for COD, 2 for Net Banking, 3 Wallet): ");
				userPaymentChoice = sc.nextInt();
				Matcher mat = pat.matcher(String.valueOf(userPaymentChoice));
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
			}while(flag==1);
			
			if (userPaymentChoice == 1) 
			{
				System.out.print("\nYour total Amount is " + total + ". Please keep the money ready to pay.\n" );
			}
			else if (userPaymentChoice == 2) 
			{
				do 		// cardChoice validation
				{
					try
					{
						Pattern pat1 = Pattern.compile("[1,2]");
						System.out.print("\n1. Credit card\n2. Debit card ");
						System.out.print("\n\nEnter your choice: ");
						cardChoice = sc.nextInt();
						Matcher mat = pat1.matcher(String.valueOf(cardChoice));
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
						System.out.println("\nPlease enter digits only");
						sc.nextLine();
						flag=1;
					}
				} while (flag==1);
				if (cardChoice == 1) 
				{
					int acc = 1234;
					int password = 123;
					long accNo = 0;
					int cvv = 0;
					while (accNo != acc && cvv != password) 		// accountNumber and cvv validation 
					{
						System.out.print("\nEnter last four digits your account number: ");
						accNo = sc.nextLong();
						System.out.print("\nEnter your CVV: ");
						cvv = sc.nextInt();
						if (accNo != acc || password != cvv) 
						{
							System.out.println("\nWrong Inputs. Enter Again");
							
						} 				
					}
					System.out.println("\nThank You. Your Payment has been received. Thanks For ordering.\n");
					
				}
				else if (cardChoice == 2) 
				{

					int acc = 1234;
					int password = 123;
					long accNo = 0;
					int cvv = 0;
					while (accNo != acc && cvv != password) 		// accountNumber and cvv validation 
					{
						System.out.print("\nEnter last four digit of your acc no. ");
						accNo = sc.nextLong();
						System.out.print("\nEnter your CVV: ");
						cvv = sc.nextInt();
					
						if (accNo != acc || password != cvv) 
						{
							System.out.println("\nWrong Inputs. Enter Again");
							
						} 
				
				
					} 
					System.out.println("\nThank You. Your Payment has been received. Thanks For ordering.\n");
		
				}
			}
			else if (userPaymentChoice == 3) 
			{
				int walletChoice = 0;
				
				do					// walletChoice validation
				{
					try
					{
						Pattern pat2 = Pattern.compile("[1,2,3,4]");
						System.out.println("\n1.Paytm\n2.Mobikwik\n3.FreeCharge\n4.AmazonPay");
						System.out.print("\nEnter your choice: ");
						walletChoice = sc.nextInt();
						Matcher mat = pat2.matcher(String.valueOf(walletChoice));
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
				}while(flag==1);
				
				switch (walletChoice) 
				{
				case 1:
				{
					do
					{
						System.out.print("\nEnter Paytm Password: ");			//password validation
						String paytmPass = sc.next();
						if(paytmPass.contains("1234"))
						{
							flag=0;
						}
						else
						{
							System.out.println("\nPlease enter correct password.");
							flag=1;
						}
					}while(flag==1);
					System.out.print("\nThank You. Your Payment has been received. Thanks For ordering.\n");
				}
					break;
				case 2:
		    	{
		    		do							//password validation
					{
						System.out.print("\nEnter MobiKwik Password: ");
						String mkPass = sc.next();
						if(mkPass.contains("1234"))
						{
							flag=0;
						}
						else
						{
							System.out.println("\nPlease enter correct password.");
							flag=1;
						}
					}while(flag==1);
					System.out.print("\nThank You. Your Payment has been received. Thanks For ordering.\n");
				}
		    		break;
				case 3:
		    	{
		    		do								//password validation
					{
						System.out.print("\nEnter FreeCharge Password: ");
						String fcPass = sc.next();
						if(fcPass.contains("1234"))
						{
							flag=0;
						}
						else
						{
							System.out.println("\nPlease enter correct password.");
							flag=1;
						}
					}while(flag==1);
					System.out.print("\nThank You. Your Payment has been received. Thanks For ordering.\n");
				}
		    		break;
				case 4:
		    	{
		    		do								//password validation
					{
						System.out.print("\nEnter AmazonPay Password: ");
						String amPass = sc.next();
						if(amPass.contains("1234"))
						{
							flag=0;
						}
						else
						{
							System.out.println("\nPlease enter correct password.");
							flag=1;
						}
					}while(flag==1);
					System.out.print("\nThank You. Your Payment has been received. Thanks For ordering.\n");
				}
		    		break;
				default:
		    		System.out.println("\nPlease enter correct choice.");
		    			
				}
			
			}
			else 
			{
				System.out.println("\nWrong Input Try again");
			}
			}

		
}
 
