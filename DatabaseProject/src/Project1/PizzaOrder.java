package Project1;
import java.util.regex.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaOrder extends Menu
{
	public int Choice,flag=1,flag1=1,menuChoice,pizzaChoice,quantity,sideChoice,sideoption,beveragechoice;
	String sizeChoice;
	static public ArrayList<Integer> pizzaOrderVeg = new ArrayList<Integer>();
	static public ArrayList<Integer> pizzaOrderNonVeg = new ArrayList<Integer>();
	static public ArrayList<Integer> totalQuantityVeg = new ArrayList<Integer>();
	static public ArrayList<Integer> totalQuantityNonVeg = new ArrayList<Integer>();
	static public ArrayList<String> sizeNonVeg = new ArrayList<String>();
	static public ArrayList<String> sizeVeg = new ArrayList<String>();
	static public ArrayList<Integer> vegPrices = new ArrayList<Integer>();
	static public ArrayList<Integer> nonVegPrices = new ArrayList<Integer>();
	static public ArrayList<Integer> sidesOrderVeg = new ArrayList<Integer>();
	static public ArrayList<Integer> sidesOrderNonVeg = new ArrayList<Integer>();
	static public ArrayList<Integer> sidesVegPrices = new ArrayList<Integer>();
	static public ArrayList<Integer> sidesNonVegPrices = new ArrayList<Integer>();
	static public ArrayList<Integer> totalQuantityVegSides = new ArrayList<Integer>();
	static public ArrayList<Integer> totalQuantityNonVegSides = new ArrayList<Integer>();
	static public ArrayList<String> sizeNonVegsides = new ArrayList<String>();
	static public ArrayList<String> sizeVegsides = new ArrayList<String>();
	static public ArrayList<Integer> beverageorder = new ArrayList<Integer>();
	static public ArrayList<String> beveragesize = new ArrayList<String>();
	static public ArrayList<Integer> totalquantitybeverage = new ArrayList<Integer>();
	static public ArrayList<Integer> pricebeverage = new ArrayList<Integer>();
	
	// Different array lists are defined above.
	
	public void orderPizza() {		// orderPizza function defined to take order pizza.
		
		Scanner sc = new Scanner(System.in);    
		Menu menu = new Menu();					// Object declared for menu class.
		
		do											// Input validation for menuChoice
		{
			System.out.println("\n*** Menu ***");
			System.out.println("1. Display Veg Menu \n2. Display non veg menu");
			
			do
			{
				try
				{
					System.out.print("\nEnter your choice: ");
					menuChoice = sc.nextInt();
					if(menuChoice==1||menuChoice==2)
					{
						flag=0;			// If input entered is correct flag is set to 0.
					}
					else
					{
						System.out.println("\nPlease enter correct choice.");
						flag=1;			// If input entered is invalid flag is set to 1.
					}
				}
				catch(InputMismatchException ime)
				{
					System.out.println("\nPlease enter a number.");
					sc.nextLine();
					flag=1;
					continue;
				}
		
			}while(flag==1);				// Loop will continue to run until flag is 1.
			
			switch (menuChoice) 			// Switch case for menuChoice
			{
			case 1:
				{
					menu.displayVegMenu();
					
					try						// Input validation for pizzaChoice
					{
						System.out.print("\nEnter the pizza you want to order: ");
						pizzaChoice = sc.nextInt();
					}
					catch(InputMismatchException ime)
					{
						System.out.println("\nPlease enter digits only.");
						sc.nextLine();
						break;
					}
				
				
					if(pizzaChoice>=1&&pizzaChoice<=Menu.vegpizzacount)		// Checking if choice entered is valid.
						pizzaOrderVeg.add(pizzaChoice);
					else
					{
						System.out.println("\nWrong choice entered. No pizza ordered. Please enter correct choice.");
						break;
					}
					
// starts here					
					do
					{
						do
						{
							System.out.print("\nEnter the size of your pizza (S - Small / M - Medium / L - Large): ");
							sizeChoice=sc.next();
							
							Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
							Matcher match = usersize.matcher(sizeChoice);
							
							if(match.find())
							{
								flag=0;
							}
							else
							{
								System.out.println("\nPlease enter a valid size !!! ");
								flag=1;
							}
						}while(flag==1);
						
						try
						{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							
							Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
							if(connection==null)
							{
								System.out.println("Connection to oracle failed.");
							}
					
							Statement statement = connection.createStatement();
						
							ResultSet resultvegpizza = statement.executeQuery("select distinct pizzaname from vegpizzamenu order by pizzaname"); 
							
							while(resultvegpizza.next()!=false)
							{
								
								if(pizzaChoice==resultvegpizza.getRow())
								{
									ResultSet pizzasizesql = statement.executeQuery("select pizzasize from vegpizzamenu where pizzaname = '"+resultvegpizza.getString(1)+"'");
									
									while(pizzasizesql.next()!=false)
									{
										if(pizzasizesql.getString(1).contentEquals(sizeChoice.toUpperCase()))
										{
											flag1=0;
											break;
										}
										
									}
										
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different pizza size. This size is not available for this pizza in our store.");
										flag1=1;
									}
										
									pizzasizesql.close();
								}					
							}
						
						statement.close();
						resultvegpizza.close();
						
						
						
						}
						catch(SQLException|ClassNotFoundException e)
						{
							e.printStackTrace();
						}
					
					
					
					}while(flag1==1);
					
					sizeVeg.add(sizeChoice);
					flag1=1;
// ends here
					
					do					// Input validation for quantity of pizza
					{
						try
						{
							System.out.print("\nEnter the quantity of pizza: ");
							quantity = sc.nextInt();
							
							if(quantity==0)
							{
								System.out.println("\nSorry !!! You cannot order 0 quantity.");
								flag=1;
							}
							else
							{
								totalQuantityVeg.add(quantity);
								flag=0;
							}
						}
						catch(InputMismatchException ime)
						{
							System.out.println("\nPlease enter a digit.");
							sc.nextLine();
							flag=1;
							continue;
						}
						
						
					}while(flag==1);
				}
					break;
			case 2:
				{
					
					menu.displayNonVegMenu();
					try								// Input validation for pizzaChoice
					{
					System.out.print("\nEnter the pizza you want to order: ");
					pizzaChoice = sc.nextInt();
					}
					catch(InputMismatchException ime)
					{
						System.out.println("\nPlease enter digits only.");
						sc.nextLine();
						break;
					}
			
					if(pizzaChoice>=1&&pizzaChoice<=Menu.nonvegpizzacount)
						pizzaOrderNonVeg.add(pizzaChoice);
					else
					{
						System.out.println("\nWrong choice entered. No pizza ordered. Please enter correct choice.");
						break;
					}
			
// starts here
					do
					{
						do
						{
							System.out.print("\nEnter the size of your pizza (S - Small / M - Medium / L - Large): ");
							sizeChoice=sc.next();
							
							Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
							Matcher match = usersize.matcher(sizeChoice);
							
							if(match.find())
							{
								flag=0;
							}
							else
							{
								System.out.println("\nPlease enter a valid size !!! ");
								flag=1;
							}
						}while(flag==1);
						
						try
						{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							
							Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
							if(connection==null)
							{
								System.out.println("Connection to oracle failed.");
							}
					
							Statement statement = connection.createStatement();
						
							ResultSet resultnonvegpizza = statement.executeQuery("select distinct pizzaname from nonvegpizzamenu order by pizzaname"); 
							
							while(resultnonvegpizza.next()!=false)
							{
								
								if(pizzaChoice==resultnonvegpizza.getRow())
								{
									ResultSet pizzasizesql = statement.executeQuery("select pizzasize from nonvegpizzamenu where pizzaname = '"+resultnonvegpizza.getString(1)+"'");
									
									while(pizzasizesql.next()!=false)
									{
										if(pizzasizesql.getString(1).contentEquals(sizeChoice.toUpperCase()))
										{
											flag1=0;
											break;
										}
										
									}
										
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different pizza size. This size is not available for this pizza in our store.");
										flag1=1;
									}
										
									pizzasizesql.close();
								}					
							}
						
						statement.close();
						resultnonvegpizza.close();
						
						
						}
						catch(SQLException|ClassNotFoundException e)
						{
							e.printStackTrace();
						}
					
					
					
					}while(flag1==1);
					
					sizeNonVeg.add(sizeChoice);
					flag1=1;
// ends here
					
					do
					{						// Input validation for quantity of pizza.
						try
						{
							System.out.print("\nEnter the quantity of pizza: ");
							quantity = sc.nextInt();
							
							if(quantity==0)
							{
								System.out.println("\nSorry!!! You cannot order 0 quantity.");
								flag=1;
							}
							else
							{
								totalQuantityNonVeg.add(quantity);
								flag=0;
							}
						}
						catch(InputMismatchException ime)
						{
							System.out.println("\nPlease enter a digit.");
							sc.nextLine();
							flag=1;
							continue;
						}
						
						
					}while(flag==1);
				}
					break;
			}
			
			
			System.out.println("\n==================================================");
		
			do					// Validation for Choice (If user wants to order more pizza)
			{
				try
				{
					System.out.print("\nDo you want to order more pizza? (Yes-1 or No-0): ");
					Choice = sc.nextInt();
					
					if(Choice>1||Choice<0)
					{
						System.out.println("\nPlease Enter correct choice.");
						flag=1;
					}
					else
					{
						flag=0;
					}
				}
				catch(InputMismatchException ime)
				{
					System.out.println("\nPlease enter numbers only.");
					sc.nextLine();
					flag=1;
					continue;
				}
			}while(flag==1);
		}while(Choice!=0);
		
		do {
			do				// Validation for user choice for sides ordering
			{
				try
				{
					System.out.print("\nDo you want to order any sides: (1 for yes, 0 for no): ");
					Choice = sc.nextInt();
					flag=0;
				}
				catch(InputMismatchException ime)
				{
					System.out.println("\nPlease enter digits only.");
					sc.nextLine();
					flag=1;
					continue;
				}
			}while(flag==1);
			
			if (Choice != 1 && Choice != 0) {		// Checking for a valid input of choice.
				System.out.println("\nPlease enter correct choice");
			}
			else if (Choice == 1) 
			{
				System.out.print("\n1. Display Veg Sides\n2. Display Non - Veg sides  ");
				do									// Validation for sideChoice
				{
					try
					{
						System.out.print("\n\nEnter your choice: ");
						sideChoice = sc.nextInt();
						
						if(sideChoice==1||sideChoice==2)
						{
							flag=0;
						}
						else
						{
							flag=1;
						}
						
					}
					catch(InputMismatchException ime)
					{
						System.out.println("\nPlease enter digits only.");
						sc.nextLine();
						flag=1;
						continue;
					}
				}while(flag==1);
				
				switch (sideChoice) 			// Switch case for sideChoice
				{
				case 1:
					{
						
						System.out.println("\n*** Our Veg Sides ***\n");
						menu.displayVegSides();
						do										// Validation for sideOption
						{
							try
							{
								System.out.print("\nEnter the sides you want to order: ");
								sideoption = sc.nextInt();
								if(sideoption>=1&&sideoption<=Menu.vegsidescount)
								{
									sidesOrderVeg.add(sideoption);
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
								System.out.println("\nEnter digits only.");
								flag=1;
								sc.nextLine();
							}
						}while(flag==1);
//starts here
						do
						{
							do
							{
								System.out.print("\nEnter the size of your pizza (S - Small / M - Medium / L - Large): ");
								sizeChoice=sc.next();
								
								Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
								Matcher match = usersize.matcher(sizeChoice);
								
								if(match.find())
								{
									flag=0;
								}
								else
								{
									System.out.println("\nPlease enter a valid size !!! ");
									flag=1;
								}
							}while(flag==1);
							
							try
							{
								Class.forName("oracle.jdbc.driver.OracleDriver");
								
								Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
								if(connection==null)
								{
									System.out.println("Connection to oracle failed.");
								}
						
								Statement statement = connection.createStatement();
							
								ResultSet resultvegsides = statement.executeQuery("select distinct sidesname from vegsidesmenu order by sidesname"); 
								
								while(resultvegsides.next()!=false)
								{
									
									if(sideoption==resultvegsides.getRow())
									{
										ResultSet sidessizesql = statement.executeQuery("select sidessize from vegsidesmenu where sidesname = '"+resultvegsides.getString(1)+"'");
										
										while(sidessizesql.next()!=false)
										{
											if(sidessizesql.getString(1).contentEquals(sizeChoice.toUpperCase()))
											{
												flag1=0;
												break;
											}
											
										}
											
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different sides size. This size is not available for this side in our store.");
											flag1=1;
										}
											
										sidessizesql.close();
									}					
								}
							
							statement.close();
							resultvegsides.close();
							
							
							}
							catch(SQLException|ClassNotFoundException e)
							{
								e.printStackTrace();
							}
						
						
						
						}while(flag1==1);
						
						sizeVegsides.add(sizeChoice);
						flag1=1;
// ends here
						
						do			// Validation for quantity of sides
						{
							try
							{
								System.out.print("\nEnter the quantity of sides: ");
								int quantity = sc.nextInt();
								
								if(quantity==0)
								{
									System.out.println("\nSorry!!! You cannot order 0 quantity.");
									flag=1;
								}
								else
								{
									totalQuantityVegSides.add(quantity);
									flag=0;
								}
							}
							catch(InputMismatchException ime)
							{
								System.out.println("\nPlease enter digits only.");
								sc.nextLine();
								flag=1;
								continue;
							}
						}while(flag==1);
					}
						break;
				case 2: 
					{
						
						System.out.println("\n*** Our Non-Veg Sides ***\n");
						menu.displayNonVegSides();
						do
						{					// Validation for nonveg sideOption
							try
							{
								System.out.print("\nEnter the sides you want to order: ");
								sideoption = sc.nextInt();
								if(sideoption>=1&&sideoption<=Menu.nonvegsidescount)
								{
									sidesOrderNonVeg.add(sideoption);
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
								System.out.println("\nEnter digits only.");
								sc.nextLine();
								flag=1;
								
							}
						}while(flag==1);
						
// size validation starts here
						do
						{
							do
							{
								System.out.print("\nEnter the size of your pizza (S - Small / M - Medium / L - Large): ");
								sizeChoice=sc.next();
								
								Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
								Matcher match = usersize.matcher(sizeChoice);
								
								if(match.find())
								{
									flag=0;
								}
								else
								{
									System.out.println("\nPlease enter a valid size !!! ");
									flag=1;
								}
							}while(flag==1);
							
							try
							{
								Class.forName("oracle.jdbc.driver.OracleDriver");
								
								Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
								if(connection==null)
								{
									System.out.println("Connection to oracle failed.");
								}
						
								Statement statement = connection.createStatement();
							
								ResultSet resultnonvegsides = statement.executeQuery("select distinct sidesname from nonvegsidesmenu order by sidesname"); 
								
								while(resultnonvegsides.next()!=false)
								{
									
									if(sideoption==resultnonvegsides.getRow())
									{
										ResultSet sidessizesql = statement.executeQuery("select sidessize from nonvegsidesmenu where sidesname = '"+resultnonvegsides.getString(1)+"'");
										
										while(sidessizesql.next()!=false)
										{
											if(sidessizesql.getString(1).contentEquals(sizeChoice.toUpperCase()))
											{
												flag1=0;
												break;
											}
											
										}
											
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different sides size. This size is not available for this side in our store.");
											flag1=1;
										}
											
										sidessizesql.close();
									}					
								}
							
							statement.close();
							resultnonvegsides.close();
							
							
							}
							catch(SQLException|ClassNotFoundException e)
							{
								e.printStackTrace();
							}
						
						
						
						}while(flag1==1);
						
						sizeNonVegsides.add(sizeChoice);
// size validation ends here
						
						do				// Validation for quantity of nonvegsides
						{
							try
							{
								System.out.print("\nEnter the quantity of sides: ");
								int quantity = sc.nextInt();
								
								if(quantity==0)
								{
									System.out.println("\nSorry!!! You cannot order 0 quantity.");
									flag=1;
								}
								else
								{
									totalQuantityNonVegSides.add(quantity);
									flag=0;
								}
								
							}
							catch(InputMismatchException ime)
							{
								System.out.println("\nPlease enter digits only.");
								sc.nextLine();
								flag=1;
								continue;
							}
						}while(flag==1);
					}
						break;
				default:
					{
						System.out.println("\nPlease enter correct choice. No Sides ordered.");
						break;
					}
				}
			}
		} while (Choice != 0);
		
		do 
		{
			
			do
			{
				try
				{
					System.out.print("\nDo you want to order beverages (1 - Yes , 0 - No): ");
					Choice=sc.nextInt();
					if(Choice==1||Choice==0)
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
					flag=1;
					sc.nextLine();
				}
			}while(flag==1);
			
			if(Choice==1)
			{
				menu.displayBeverages();
				
				do
				{
					try
					{
						System.out.print("\nEnter the beverage choice you want to order: ");
						beveragechoice=sc.nextInt();
						
						if(beveragechoice>=1&&beveragechoice<=Menu.beveragescount)
						{
							flag=0;
							beverageorder.add(beveragechoice);
						}
						else
						{
							flag=1;
							System.out.println("\nPlease enter a valid choice.");
						}
					}
					catch(InputMismatchException ime)
					{
						System.out.println("\nPlease enter digits only");
						flag=1;
						sc.nextLine();
					}
				}while(flag==1);
				
// Validation of size starts here
				
				do
				{
					do
					{
						System.out.print("\nEnter the size of your beverage (S - Small / M - Medium / L - Large): ");
						sizeChoice=sc.next();
						
						Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
						Matcher match = usersize.matcher(sizeChoice);
						
						if(match.find())
						{
							flag=0;
						}
						else
						{
							System.out.println("\nPlease enter a valid size !!! ");
							flag=1;
						}
					}while(flag==1);
					
					try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
						if(connection==null)
						{
							System.out.println("Connection to oracle failed in beverage size choice.");
						}
				
						Statement statement = connection.createStatement();
					
						ResultSet resultbeverage = statement.executeQuery("select distinct name from beverages order by name"); 
						
						while(resultbeverage.next()!=false)
						{
							
							if(beveragechoice==resultbeverage.getRow())
							{
								ResultSet beveragesizesql = statement.executeQuery("select beveragesize from beverages where name = '"+resultbeverage.getString(1)+"'");
								
								while(beveragesizesql.next()!=false)
								{
									if(beveragesizesql.getString(1).contentEquals(sizeChoice.toUpperCase()))
									{
										flag1=0;
										break;
									}
									
								}
									
								if(flag1!=0)
								{
									System.out.println("\nPlease enter a different sides size. This size is not available for this side in our store.");
									flag1=1;
								}
									
								beveragesizesql.close();
							}					
						}
					
					statement.close();
					resultbeverage.close();
					
					
					}
					catch(SQLException|ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				
				
				
				}while(flag1==1);
				
// beverage size validation ends here	
				
				beveragesize.add(sizeChoice);
				
				do				// Validation for quantity of beverages
				{
					try
					{
						System.out.print("\nEnter the quantity of beverages: ");
						int quantity = sc.nextInt();
						
						if(quantity==0)
						{
							System.out.println("\nSorry!!! You cannot order 0 quantity.");
							flag=1;
						}
						else
						{
							totalquantitybeverage.add(quantity);
							flag=0;
						}
						
					}
					catch(InputMismatchException ime)
					{
						System.out.println("\nPlease enter digits only.");
						sc.nextLine();
						flag=1;
						continue;
					}
				}while(flag==1);
				
				
			}
			
		}while(Choice!=0);
		
		
		
		Pricing();			// Pricing function called.
		
		
	}
	
	public void Pricing()
	{
		Billing b = new Billing();					// Object created for billing class
		b.ordernames();
		int index=0;
		
		try					// connection to database established
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed.");
			}
			
		
			Statement statement = connection.createStatement();
			ResultSet getprice=null;
			if(sizeVeg.size()>0)
			{
			
				for(String userchoice:sizeVeg)
				{
					// getprice object getting the price of the vegPizza ordered.
					getprice = statement.executeQuery("select price from vegpizzamenu where pizzaname = '"+Billing.pizzanameveg.get(index)+"' and Pizzasize = '"+userchoice.toUpperCase()+"'");
					
					while(getprice.next()!=false)
					{
						vegPrices.add(getprice.getInt(1)); // price added to vegPrices ArrayList
					}
					index++;
					
					
					
				}
			}
			
			index=0;
			
			if(sizeNonVeg.size()>0)
			{
		
				for(String userchoice:sizeNonVeg)
				{
					// getprice object getting the price of the nonVegPizza ordered.
					getprice = statement.executeQuery("select price from nonvegpizzamenu where pizzaname = '"+Billing.pizzanamenonveg.get(index)+"' and Pizzasize = '"+userchoice.toUpperCase()+"'");
					while(getprice.next()!=false)
					{
						nonVegPrices.add(getprice.getInt(1));	// price added to nonVegPrices ArrayList
					}
					index++;
					
				}
			}
			
			index=0;
			
			if(sizeVegsides.size()>0)
			{
		
				for(String userchoice:sizeVegsides)
				{
					// getprice object getting the price of the vegSides ordered.
					getprice = statement.executeQuery("select price from vegsidesmenu where sidesname = '"+Billing.sidesnameveg.get(index)+"' and sidessize = '"+userchoice.toUpperCase()+"'");
					while(getprice.next()!=false)
					{
						sidesVegPrices.add(getprice.getInt(1));		// price added to sidesvegPrices ArrayList
					}
					index++;
					
				}
			}
			
			index=0;
			
			if(sizeNonVegsides.size()>0)
			{

				for(String userchoice:sizeNonVegsides)
				{
					// getprice object getting the price of the nonVegSides ordered.
					getprice = statement.executeQuery("select price from nonvegsidesmenu where sidesname = '"+Billing.sidesnamenonveg.get(index)+"' and sidessize = '"+userchoice.toUpperCase()+"'");
					while(getprice.next()!=false)
					{
						sidesNonVegPrices.add(getprice.getInt(1));	// price added to sidesNonVegPrices ArrayList
					}
					index++;
					
				}
			}
			
			index=0;
			
			if(beveragesize.size()>0)
			{

				for(String userchoice:beveragesize)
				{
					// getprice object getting the price of the nonVegSides ordered.
					getprice = statement.executeQuery("select price from beverages where name = '"+Billing.beveragename.get(index)+"' and beveragesize = '"+userchoice.toUpperCase()+"'");
					while(getprice.next()!=false)
					{
						 pricebeverage.add(getprice.getInt(1));	// price added to sidesNonVegPrices ArrayList
					}
					index++;
					
				}
			}
		
			getprice.close();
			
			statement.close();
			connection.close();
		
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		
		b.display();		// function display called and all required arraylists passed as argument.
	}
}


