package Project1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public  class Menu implements NonVegMenu{		// menu class implements the nonvegmenu interface
	static int vegpizzacount,nonvegpizzacount,vegsidescount,nonvegsidescount,beveragescount;
	
	

	@Override
	public void displayVegMenu() 
	{
		
		System.out.println("\n*** Our Veg Pizzas ***\n");
		int i=0;
		try		// connection to database established
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed in displayvegmenu.");
			}
			
			
			Statement statement = connection.createStatement();
			// ResultSet object declared to display pizzaName from database in ascending order
			ResultSet resultvegpizza = statement.executeQuery("select distinct pizzaname from vegpizzamenu order by pizzaname");  
			
			vegpizzacount=0;  // variable declared to know how many Veg pizza's are their in database 
			
			while(resultvegpizza.next()!=false)
			{
				System.out.println((i+1)+". "+resultvegpizza.getString(1));
				i++;
				vegpizzacount++;
			}
			resultvegpizza.close();
			statement.close();
			connection.close();
			
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void displayNonVegMenu() 
	{
		System.out.println("\n*** Our Non Veg Pizzas ***\n");
		int i=0;
		try		// connection to database established
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed in display nonvegmenu.");
			}
			
			
			Statement statement = connection.createStatement();
			// ResultSet object declared to display nonpizzaName from database in ascending order
			ResultSet resultnonvegpizza = statement.executeQuery("select distinct pizzaname from nonvegpizzamenu order by pizzaname"); 
			
			nonvegpizzacount=0;		// variable declared to know how many nonVeg pizza's are their in database
			
			while(resultnonvegpizza.next()!=false)
			{
				System.out.println((i+1)+". "+resultnonvegpizza.getString(1));
				i++;
				nonvegpizzacount++;
			}
			
			resultnonvegpizza.close();
			statement.close();
			connection.close();
			
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	

	
	@Override
	public void displayVegSides()
	{
		int i=0;
		try		// connection to database established
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed in display vegsidesmenu.");
			}
			
			
			Statement statement = connection.createStatement();
			// ResultSet object declared to display vegsidesName from database in ascending order
			ResultSet resultvegsides = statement.executeQuery("select distinct sidesname from vegsidesmenu order by sidesname"); 
			
			vegsidescount=0;		// variable declared to know how many VegSides's are their in database
			
			while(resultvegsides.next()!=false)
			{
				System.out.println((i+1)+". "+resultvegsides.getString(1));
				i++;
				vegsidescount++;
			}

			resultvegsides.close();
			statement.close();
			connection.close();
			
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void displayNonVegSides()
	{
		int i=0;
		try	// connection to database established
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed in display vegsidesmenu.");
			}
			
			Statement statement = connection.createStatement();
			
			// ResultSet object declared to display nonvegsidesName from database in ascending order
			ResultSet resultnonvegsides = statement.executeQuery("select distinct sidesname from nonvegsidesmenu order by sidesname"); 
			
			nonvegsidescount=0;		// variable declared to know how many nonVegSides's are their in database
			
			while(resultnonvegsides.next()!=false)
			{
				System.out.println((i+1)+". "+resultnonvegsides.getString(1));
				i++;
				nonvegsidescount++;
			}
			
			resultnonvegsides.close();
			statement.close();
			connection.close();
			
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void displayBeverages() 
	{
		System.out.println("\n*** Our Beverages ***\n");
		int i=0;
		try	// connection to database established
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed in display beverages.");
			}
			
			Statement statement = connection.createStatement();
			
			// ResultSet object declared to display BeverageName from database in ascending order
			ResultSet resultbeverages = statement.executeQuery("select distinct name from beverages order by name"); 
			
			beveragescount=0;		// variable declared to know how many nonVegSides's are their in database
			
			while(resultbeverages.next()!=false)
			{
				System.out.println((i+1)+". "+resultbeverages.getString(1));
				i++;
				beveragescount++;
			}
			
			resultbeverages.close();
			statement.close();
			connection.close();
			
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
}
