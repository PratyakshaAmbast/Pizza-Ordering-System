package Project1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Billing extends PizzaOrder{
	
	static int total=0;
	static public ArrayList<String> pizzanameveg = new ArrayList<String>();
	static public ArrayList<String> pizzanamenonveg = new ArrayList<String>();
	static public ArrayList<String> sidesnameveg = new ArrayList<String>();
	static public ArrayList<String> sidesnamenonveg = new ArrayList<String>();
	static public ArrayList<String> beveragename = new ArrayList<String>();
	
	void ordernames()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed.");
			}
	
			Statement statement = connection.createStatement();
			
			for(Integer o:PizzaOrder.pizzaOrderVeg)
			{
				ResultSet resultvegpizza = statement.executeQuery("select distinct pizzaname from vegpizzamenu order by pizzaname"); 
				
				while(resultvegpizza.next()!=false)
				{
					
						if(o==resultvegpizza.getRow())
						{
							pizzanameveg.add(resultvegpizza.getString(1));
						}					
				}
				
				resultvegpizza.close();
			}
			
			for(Integer o:PizzaOrder.pizzaOrderNonVeg)
			{
				ResultSet resultnonvegpizza = statement.executeQuery("select distinct pizzaname from nonvegpizzamenu order by pizzaname"); 
				
				while(resultnonvegpizza.next()!=false)
				{
					
						if(o==resultnonvegpizza.getRow())
						{
							pizzanamenonveg.add(resultnonvegpizza.getString(1));
						}					
				}
				
				resultnonvegpizza.close();
			}
			
			for(Integer o:PizzaOrder.sidesOrderVeg)
			{
				ResultSet resultvegsides = statement.executeQuery("select distinct sidesname from vegsidesmenu order by sidesname"); 
				
				while(resultvegsides.next()!=false)
				{
					
						if(o==resultvegsides.getRow())
						{
							sidesnameveg.add(resultvegsides.getString(1));
						}					
				}
				
				resultvegsides.close();
			}
			
			for(Integer o:PizzaOrder.sidesOrderNonVeg)
			{
				ResultSet resultnonvegsides = statement.executeQuery("select distinct sidesname from nonvegsidesmenu order by sidesname"); 
				
				while(resultnonvegsides.next()!=false)
				{
					
						if(o==resultnonvegsides.getRow())
						{
							sidesnamenonveg.add(resultnonvegsides.getString(1));
						}					
				}
				
				resultnonvegsides.close();
			}
			
			for(Integer o:PizzaOrder.beverageorder)
			{
				ResultSet resultbeverage = statement.executeQuery("select distinct name from beverages order by name"); 
				
				while(resultbeverage.next()!=false)
				{
					
						if(o==resultbeverage.getRow())
						{
							beveragename.add(resultbeverage.getString(1));
						}					
				}
				
				resultbeverage.close();
			}

			
			
			statement.close();
			
			
			
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	
	void display()
	{
		
		
		System.out.println("\nThank you, "+CustomerDetails.name+" your Bill Receipt has been generated !!!");
		Scanner sc = new Scanner(System.in);					// scanner class object created for userInput
		Payment.makePayment();									// static function makePayment called
		do														// validation for feedback choice
		{
			try
			{
				System.out.print("\nDo you want to enter a feedback? (1-Yes|0-No): ");
				int feedback = sc.nextInt();
				if(feedback==1) 
				{
					Feedback.userfeedback();		// static function userfeedback called
					flag=0;
				}
				else if(feedback==0)
				{
					System.out.println("\nThank you! Visit Again!");
					flag=0;
				}
				else
				{
					System.out.println("\nPlease enter correct choice.");
					flag=1;
					continue;
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
	
		
		 File file = new File("Bill.txt");			// file object created 
	     
	     PrintStream stream;						// printstream object created
	     
		try {
			stream = new PrintStream(file);			// file object passed to printstream
			System.setOut(stream);					// writing console output to file started
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
     
	     
	     
		System.out.printf("%60s"," ********** Final Bill **********\n\n");
		System.out.println("=======================================================================================");
		System.out.printf("%-30s %5s %15s %15s %17s\n","Pizza Name","Size","Quantity","Unit Price","Total Amount");
		System.out.println("=======================================================================================");
		
		if(PizzaOrder.pizzaOrderVeg.size()>0)			// printing veg pizza order
		{
			for(int i=0;i<pizzaOrderVeg.size();i++)
			{
				System.out.printf("%-30s %3s %13d %15d %14d\n\n",pizzanameveg.get(i),PizzaOrder.sizeVeg.get(i).toUpperCase(),PizzaOrder.totalQuantityVeg.get(i),PizzaOrder.vegPrices.get(i),(PizzaOrder.vegPrices.get(i)*PizzaOrder.totalQuantityVeg.get(i)));
				total+=PizzaOrder.vegPrices.get(i)*PizzaOrder.totalQuantityVeg.get(i);
				
				
			}
			
		
			
	
		}
		if(PizzaOrder.pizzaOrderNonVeg.size()>0)		// printing nonveg pizza order
		{
			
			for(int k=0;k<pizzaOrderNonVeg.size();k++)
			{
				System.out.printf("%-30s %3s %13d %15d %14d\n\n",pizzanamenonveg.get(k),PizzaOrder.sizeNonVeg.get(k).toUpperCase(),PizzaOrder.totalQuantityNonVeg.get(k),PizzaOrder.nonVegPrices.get(k),(PizzaOrder.nonVegPrices.get(k)*PizzaOrder.totalQuantityNonVeg.get(k)));
				total+=PizzaOrder.nonVegPrices.get(k)*PizzaOrder.totalQuantityNonVeg.get(k);
			}
		}
		
		if(PizzaOrder.sidesOrderVeg.size()>0)		// printing veg sides order
		{
			
			for(int k=0;k<PizzaOrder.sidesOrderVeg.size();k++)
			{
				System.out.printf("%-30s %3s %13d %15d %14d\n\n",sidesnameveg.get(k),PizzaOrder.sizeVegsides.get(k).toUpperCase(),PizzaOrder.totalQuantityVegSides.get(k),PizzaOrder.sidesVegPrices.get(k),(PizzaOrder.sidesVegPrices.get(k)*PizzaOrder.totalQuantityVegSides.get(k)));
				total+=PizzaOrder.sidesVegPrices.get(k)*PizzaOrder.totalQuantityVegSides.get(k);
			}
		}
		
		if(PizzaOrder.sidesOrderNonVeg.size()>0)		// printing nonveg sides order
		{
			
			for(int k=0;k<PizzaOrder.sidesOrderNonVeg.size();k++)
			{
				System.out.printf("%-30s %3s %13d %15d %14d\n\n",sidesnamenonveg.get(k),PizzaOrder.sizeNonVegsides.get(k).toUpperCase(),PizzaOrder.totalQuantityNonVegSides.get(k),PizzaOrder.sidesNonVegPrices.get(k),(PizzaOrder.sidesNonVegPrices.get(k)*PizzaOrder.totalQuantityNonVegSides.get(k)));
				total+=PizzaOrder.sidesNonVegPrices.get(k)*PizzaOrder.totalQuantityNonVegSides.get(k);
			}
		}
		
		if(PizzaOrder.beverageorder.size()>0)		// printing beverage order
		{
			
			for(int k=0;k<PizzaOrder.beverageorder.size();k++)
			{
				System.out.printf("%-30s %3s %13d %15d %14d\n\n",beveragename.get(k),PizzaOrder.beveragesize.get(k).toUpperCase(),PizzaOrder.totalquantitybeverage.get(k),PizzaOrder.pricebeverage.get(k),(PizzaOrder.pricebeverage.get(k)*PizzaOrder.totalquantitybeverage.get(k)));
				total+=PizzaOrder.pricebeverage.get(k)*PizzaOrder.totalquantitybeverage.get(k);
			}
		}
		
			System.out.println("=======================================================================================");
			System.out.printf("Grand Total %67d\n" , total);					// printing total
			System.out.println("=======================================================================================");
			
			System.out.println("\n\t\t\t"+CustomerDetails.name + " Thanks for Ordering! "+"\n\n\tYour order will be delivered at this address:  " + CustomerDetails.Address);
			System.out.println("\n\t You will be contacted on this mobile number: "+CustomerDetails.phoneNum);
		
		
	}

}
