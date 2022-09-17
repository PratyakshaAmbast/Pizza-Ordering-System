package Project1;

import java.sql.*;
import java.util.*;
import java.util.regex.*;

public class Create_database {
	
	
	
void create()
{
	ArrayList<String> columnname = new ArrayList<String>();
	ArrayList<String> datatype = new ArrayList<String>();
	String tablename,cn,dt,sqlquery,query,buffer="",buffer1= "";
	int choice=0,flag=1;
	
	Scanner scanner = new Scanner(System.in); // scanner class object created
	Scanner scanner1 = new Scanner(System.in);
	
	try			// connection to database established
	{		
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
			if(connection==null)
			{
				System.out.println("Connection to oracle failed.");
			}
			
				
				Statement state = connection.createStatement();
				
				do		// validation for tablename done
				{
					System.out.print("\nEnter the table name to create: ");
					tablename=scanner.nextLine();
					
					
					ResultSet tablecheck = state.executeQuery("select tname from tab");
					
					while(tablecheck.next()!=false)
					{
								if(tablecheck.getString(1).contentEquals(tablename.toUpperCase()))
						{
							flag=1;
							break;
						}
						else
						{
							flag=0;
						}
					}
					
					if(flag==1)
					{
						System.out.println("\nPlease enter a different table name. This table already exists.");
					}
					
					tablecheck.close();
				}while(flag==1);
				
				state.close();
				
				do		// inputs for columnname and datatype taken
				{
					System.out.print("\nEnter the column name: ");
					cn = scanner.nextLine();
					columnname.add(cn);
					System.out.print("\nEnter the datatype: ");
					dt = scanner.nextLine();
					datatype.add(dt);
					
					
					do		// validation for choice 
					{
						try
						{
							System.out.print("\nDo you want to enter more fields (1- Yes, 0- No): ");
							choice=scanner.nextInt();
							if(choice==1||choice==0)
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
							scanner.nextLine();
						}
						
						}while(flag==1);
					}while(choice==1);
					
					int i=columnname.size();
					
					query = "create table "+tablename+"(";  // initial query built 
					
					for(int j=0;j<i;j++)		// for loop to append the column name and data types to query
					{
						buffer = buffer1.concat(columnname.get(j)+" "+datatype.get(j)+",");
						buffer1=buffer;	
					}
					
					buffer1=buffer1.substring(0, (buffer1.length()-1)); // to remove the last ',' from query built in for loop
			
					sqlquery = query.concat(buffer1).concat(")");  // full sql query built
					System.out.println(sqlquery);
					
					Statement statement = connection.createStatement();
					statement.executeUpdate(sqlquery);
					statement.close();
					System.out.println("Table created succesfully !!!");
					
					
		
					connection.close();
					
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
	}
	
	void insert()
	{
		String tablename,pizzaname,size;
		int sno=0,price=0,flag=1,choice=0;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner1 = new Scanner(System.in);
		try		// connection established to database
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try
			{
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
				if(connection==null)
				{
					System.out.println("Connection to oracle failed.");
				}
				
				do
				{
					Statement state = connection.createStatement();
					
					do		// records to be inserted asked from user
					{
						System.out.print("\nEnter the table name to insert records in: ");
						tablename=scanner1.nextLine();
						
						// tablename validation done 
						ResultSet tablecheck = state.executeQuery("select tname from tab");
						
						while(tablecheck.next()!=false&&flag==1)
						{
							if(tablecheck.getString(1).contentEquals(tablename.toUpperCase()))
							{
								flag=0;
								break;
							}
							else
							{
								flag=1;
							}
						}
						
						if(flag==1)
						{
							System.out.println("\nPlease enter a different table name. This table does not exists.");
						}
						
						tablecheck.close();
					}while(flag==1);
					
					state.close();
					
					
					do 		// serial number exception handling
					{
						try
						{
							System.out.print("\nEnter Serial number (Sno): ");
							sno = scanner.nextInt();
							scanner.nextLine();
							flag=0;
						}
						catch(InputMismatchException ime)
						{
							System.out.println("\nPlease enter digits only");
							scanner.nextLine();
							flag=1;
						}
					}while(flag==1);
					
					do		// pizza name validation done
					{
						Pattern pat = Pattern.compile("[0-9]");
						System.out.print("\nEnter Pizza Name / Sides Name / Beverage Name: ");
						pizzaname = scanner1.nextLine();
						Matcher mat = pat.matcher(pizzaname);
						
						if(mat.find())
						{
							flag=1;
							System.out.println("\nPlease enter a valid name.");
						}
						else
						{
							flag=0;
						}
					}while(flag==1);
					
					do		// size validation done
					{
						Pattern pat = Pattern.compile("[s,S,m,M,l,L]");
						System.out.print("\nEnter size (S-Small,M-Medium,L-Large): ");
						size = scanner1.nextLine();
						Matcher mat = pat.matcher(size);
						
						if(mat.find())
						{
							flag=0;
							
						}
						else
						{
							System.out.println("\nPlease enter a valid size.");
							flag=1;
						}
					}while(flag==1);
					
					do 		// price exception handling
					{
						try
						{
							System.out.print("\nEnter Price: ");
							price = scanner.nextInt();
							scanner.nextLine();
							flag=0;
						}
						catch(InputMismatchException ime)
						{
							System.out.println("\nPlease enter digits only");
							scanner.nextInt();
							flag=1;
						}
					}while(flag==1);
					
					
					
					String sqlquery = "insert into "+tablename+" values("+sno+",'"+pizzaname+"','"+size.toUpperCase()+"',"+price+")";  // query built
					Statement statement = connection.createStatement();
					statement.executeUpdate(sqlquery);		// query executed
					statement.close();
					System.out.println("\nRecord inserted succesfully !!!");
					
					do
					{
						try
						{
							System.out.print("\nDo you want to insert more records ( 1 - Yes , 0 - No ): ");
							choice=scanner1.nextInt();
							
							if(choice==1||choice==0)
							{
								flag=0;
							}
							else
							{
								flag=1;
								System.out.println("\nPlease enter a valid choice.");
							}
						}
						catch(InputMismatchException ime)
						{
							System.out.println("\nPlease enter digits only.");
							scanner1.nextLine();
							flag=1;
						}
						
						
						
					}while(flag==1);
					
					
				
				
			}while(choice==1);
			
			connection.close();
			
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
	}
	
	void update()
	{
		String tablename,pizzaname,newpizzaname,size,newsize;
		int flag=0,flag1=1,choice=0,newprice=0;
		String sqlquery=null;
		
		
		Scanner scanner1 = new Scanner(System.in);
		try		// connection established to database
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try
			{
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
				if(connection==null)
				{
					System.out.println("Connection to oracle failed.");
				}
				
				
				
				do
				{
					Statement state = connection.createStatement();
					do		// records to be inserted asked from user
					{
						System.out.print("\nEnter the table name to update records in: ");
						tablename=scanner1.nextLine();
						
						// tablename validation done 
						ResultSet tablecheck = state.executeQuery("select tname from tab");
						
						while(tablecheck.next()!=false)
						{
							if(tablecheck.getString(1).contentEquals(tablename.toUpperCase()))
							{
								flag=0;
								break;
							}
							else
							{
								flag=1;
							}
						}
						
						if(flag==1)
						{
							System.out.println("\nPlease enter a different table name. This table does not exists.");
						}
						
						tablecheck.close();
					}while(flag==1);
					
					state.close();
					
					Statement statement = connection.createStatement();
					
					System.out.println("\n1. Update Pizza Name / Sides Name / Beverage Name\n2. Update Price\n3. Update Size\n4. Exit");
					
					do
					{
						try
						{
							Pattern userchoice = Pattern.compile("[1,2,3,4]");
							System.out.print("\nEnter your choice: ");
							choice=scanner1.nextInt();
							scanner1.nextLine();
							Matcher match = userchoice.matcher(String.valueOf(choice));
							
							if(match.find())
							{
								flag=0;
							}
							else
							{
								System.out.println("\nPlease enter a valid choice !!!");
								flag=1;
							}
						}
						catch(InputMismatchException ime)
						{
							System.out.println("\nPlease enter a digit !!!");
							scanner1.nextLine();
							flag=1;
						}
					}while(flag==1);
					
					switch(choice)
					{
						case 1:
						{
							do
							{
								do
								{
									System.out.print("\nEnter the Pizza Name / Sides Name / Beverage Name to change: ");
									pizzaname=scanner1.nextLine();
									
									Pattern foodname = Pattern.compile("[a-z,A-Z]");
									Matcher match = foodname.matcher(pizzaname);
									
									if(match.find())
									{
										flag=0;
									}
									else if(pizzaname.length()==0 || pizzaname==null)
									{
										System.out.println("\nField cannot be empty!!!");
										flag=1;
									}
									else
									{
										System.out.println("\nPlease enter a valid Pizza/Sides/Beverage Name !!!");
										flag=1;
									}
								}while(flag==1);
									
									if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
									{
										ResultSet pizzanamesql = statement.executeQuery("select distinct pizzaname from "+tablename+" order by pizzaname");
										
										while(pizzanamesql.next()!=false)
										{
											if(pizzanamesql.getString(1).contentEquals(pizzaname))
											{
												flag1=0;
												break;
											}
											
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different pizzaname. This does not exists in database.");
											flag1=1;
										}
										
										pizzanamesql.close();
									}
									else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
									{
										ResultSet sidesnamesql = statement.executeQuery("select distinct sidesname from "+tablename+" order by sidesname");
										
										while(sidesnamesql.next()!=false)
										{
											if(sidesnamesql.getString(1).contentEquals(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different sidesname. This does not exists in database.");
											flag1=1;
										}
										
										sidesnamesql.close();
									}
									else if(tablename.toUpperCase().contains("BEVERAGES"))
									{
										ResultSet beveragenamesql = statement.executeQuery("select distinct name from "+tablename+" order by name");
										
										while(beveragenamesql.next()!=false)
										{
											if(beveragenamesql.getString(1).contentEquals(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different beverage name. This does not exists in database.");
											flag1=1;
										}
										
										beveragenamesql.close();
									}
							}while(flag1==1);
							
							flag1=1;
							
							do
							{
								do
								{
									System.out.print("\nEnter the size of the pizza/sides/beverage you want to change name of (S - Small , M - Medium , L - Large): ");
									size=scanner1.nextLine();
									
									Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
									Matcher match = usersize.matcher(size);
									
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
								
								if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
								{
									ResultSet pizzasizesql = statement.executeQuery("select pizzasize from "+tablename+" where pizzaname = '"+pizzaname+"'");
									
									while(pizzasizesql.next()!=false)
									{
										if(pizzasizesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
										
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different pizza size. This size does not exists for this pizza in database.");
										flag1=1;
									}
									
									pizzasizesql.close();
								}
								else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
								{
									ResultSet sidessizesql = statement.executeQuery("select sidessize from "+tablename+" where sidesname = '"+pizzaname+"'");
									
									while(sidessizesql.next()!=false)
									{
										if(sidessizesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different sides size. This size does not exists for this side in database.");
										flag1=1;
									}
									
									sidessizesql.close();
								}
								else if(tablename.toUpperCase().contains("BEVERAGES"))
								{
									ResultSet beveragenamesql = statement.executeQuery("select beveragesize from "+tablename+" where name = '"+pizzaname+"'");
									
									while(beveragenamesql.next()!=false)
									{
										if(beveragenamesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different beverage size. This size does not exists for this beverage in database.");
										flag1=1;
									}
									
									beveragenamesql.close();
								}
							}while(flag1==1);
									
									
								do
								{
									System.out.print("\nEnter the new Pizza Name / Sides Name / Beverage Name to update: ");
									newpizzaname=scanner1.nextLine();
									
									Pattern foodname = Pattern.compile("[a-z,A-Z]");
									Matcher match = foodname.matcher(newpizzaname);
									
									if(match.find())
									{
										flag=0;
									}
									else if(newpizzaname.length()==0 || newpizzaname==null)
									{
										System.out.println("\nField cannot be empty!!!");
										flag=1;
									}
									else
									{
										System.out.println("\nPlease enter a valid Pizza/Sides/Beverage Name !!!");
										flag=1;
									}
									
									
								}while(flag==1);
								
								if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
								{
									sqlquery = "update "+tablename+" set pizzaname = '"+newpizzaname+"' where pizzaname = '"+pizzaname+"' and pizzasize = '"+size.toUpperCase()+"'";  // query built
									statement.executeUpdate(sqlquery);		// query executed
									statement.close();
									System.out.println("\nRecord updated succesfully !!!");
								}
								else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
								{
									sqlquery = "update "+tablename+" set sidesname = '"+newpizzaname+"' where sidesname = '"+pizzaname+"' and sidessize = '"+size.toUpperCase()+"'";  // query built
									statement.executeUpdate(sqlquery);		// query executed
									statement.close();
									System.out.println("\nRecord updated succesfully !!!");
								}
								else if(tablename.toUpperCase().contains("BEVERAGES"))
								{
									sqlquery = "update "+tablename+" set name = '"+newpizzaname+"' where name = '"+pizzaname+"' and beveragesize = '"+size.toUpperCase()+"'";
									statement.executeUpdate(sqlquery); // query executed
									statement.close();
									System.out.println("\nRecord updated succesfully !!!");
								}
							
							
							
							
							
						}
							break;
						
						case 2:
						{
							do
							{
								do
								{
									System.out.print("\nEnter the Pizza Name / Sides Name / Beverage Name to change price of: ");
									pizzaname=scanner1.nextLine();
									
									Pattern foodname = Pattern.compile("[a-z,A-Z]");
									Matcher match = foodname.matcher(pizzaname);
									
									if(match.find())
									{
										flag=0;
									}
									else if(pizzaname.length()==0 || pizzaname==null)
									{
										System.out.println("\nField cannot be empty!!!");
										flag=1;
									}
									else
									{
										System.out.println("\nPlease enter a valid Pizza/Sides/Beverage Name !!!");
										flag=1;
									}
								}while(flag==1);
									
									if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
									{
										ResultSet pizzanamesql = statement.executeQuery("select distinct pizzaname from "+tablename+" order by pizzaname");
										
										while(pizzanamesql.next()!=false)
										{
											if(pizzanamesql.getString(1).contentEquals(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different pizzaname. This does not exists in database.");
											flag1=1;
										}
										
										pizzanamesql.close();
									}
									else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
									{
										ResultSet sidesnamesql = statement.executeQuery("select distinct sidesname from "+tablename+" order by sidesname");
										
										while(sidesnamesql.next()!=false)
										{
											if(sidesnamesql.getString(1).contentEquals(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different sidesname. This does not exists in database.");
											flag1=1;
										}
										
										sidesnamesql.close();
									}
									else if(tablename.toUpperCase().contains("BEVERAGES"))
									{
										ResultSet beveragenamesql = statement.executeQuery("select distinct name from "+tablename+" order by name");
										
										while(beveragenamesql.next()!=false)
										{
											if(beveragenamesql.getString(1).contentEquals(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different beverage name. This does not exists in database.");
											flag1=1;
										}
										
										beveragenamesql.close();
									}
							}while(flag1==1);
							
							flag1=1;
							
							do
							{
								do
								{
									System.out.print("\nEnter the size of the pizza/sides/beverage you want to change price of (S - Small , M - Medium , L - Large): ");
									size=scanner1.nextLine();
									
									Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
									Matcher match = usersize.matcher(size);
									
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
								
								if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
								{
									ResultSet pizzasizesql = statement.executeQuery("select pizzasize from "+tablename+" where pizzaname = '"+pizzaname+"'");
									
									while(pizzasizesql.next()!=false)
									{
										if(pizzasizesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
										
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different pizza size. This size does not exists for this pizza in database.");
										flag1=1;
									}
									
									pizzasizesql.close();
								}
								else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
								{
									ResultSet sidessizesql = statement.executeQuery("select sidessize from "+tablename+" where sidesname = '"+pizzaname+"'");
									
									while(sidessizesql.next()!=false)
									{
										if(sidessizesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different sides size. This size does not exists for this side in database.");
										flag1=1;
									}
									
									sidessizesql.close();
								}
								else if(tablename.toUpperCase().contains("BEVERAGES"))
								{
									ResultSet beveragenamesql = statement.executeQuery("select beveragesize from "+tablename+" where name = '"+pizzaname+"'");
									
									while(beveragenamesql.next()!=false)
									{
										if(beveragenamesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different beverage size. This size does not exists for this beverage in database.");
										flag1=1;
									}
									
									beveragenamesql.close();
								}
							}while(flag1==1);
							
							flag1=1;
							
							do
							{
								try
								{
									System.out.print("\nEnter the price to update: ");
									newprice = scanner1.nextInt();
									scanner1.nextLine();
									
									Pattern userprice = Pattern.compile("[0-9]");
									Matcher match = userprice.matcher(String.valueOf(newprice));
									
									if(match.find())
									{
										flag=0;
									}
									else
									{
										flag=1;
										System.out.println("\nEnter a valid price.");
									}
								}
								catch(InputMismatchException ime)
								{
									System.out.println("\nPlease enter digits only.");
									flag=1;
									scanner1.nextLine();
								}
								
							}while(flag==1);
							
							if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
							{
								sqlquery = "update "+tablename+" set price = "+newprice+" where pizzaname = '"+pizzaname+"' and pizzasize = '"+size.toUpperCase()+"'";  // query built
								statement.executeUpdate(sqlquery);		// query executed
								statement.close();
								System.out.println("\nRecord updated succesfully !!!");
							}
							else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
							{
								sqlquery = "update "+tablename+" set price = "+newprice+" where sidesname = '"+pizzaname+"' and sidessize = '"+size.toUpperCase()+"'";  // query built
								statement.executeUpdate(sqlquery);		// query executed
								statement.close();
								System.out.println("\nRecord updated succesfully !!!");
							}
							else if(tablename.toUpperCase().contains("BEVERAGES"))
							{
								sqlquery = "update "+tablename+" set price = "+newprice+" where name = '"+pizzaname+"' and beveragesize = '"+size.toUpperCase()+"'";
								statement.executeUpdate(sqlquery);
								statement.close();
								System.out.println("\nRecord updated succesfully !!!");
							}
						}
							break;
						
						case 3: 
						{
							flag1=1;
							do
							{
								do
								{
									System.out.print("\nEnter the Pizza Name / Sides Name / Beverage Name to change size of: ");
									pizzaname=scanner1.nextLine();
									
									Pattern foodname = Pattern.compile("[a-z,A-Z]");
									Matcher match = foodname.matcher(pizzaname);
									
									if(match.find())
									{
										flag=0;
									}
									else if(pizzaname.length()==0 || pizzaname==null)
									{
										System.out.println("\nField cannot be empty!!!");
										flag=1;
									}
									else
									{
										System.out.println("\nPlease enter a valid Pizza/Sides/Beverage Name !!!");
										flag=1;
									}
								}while(flag==1);
									
									if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
									{
										ResultSet pizzanamesql = statement.executeQuery("select distinct pizzaname from "+tablename+" order by pizzaname");
										
										while(pizzanamesql.next()!=false)
										{
											if(pizzanamesql.getString(1).contains(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different pizzaname. This does not exists in database.");
											flag1=1;
										}
										
										pizzanamesql.close();
									}
									else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
									{
										ResultSet sidesnamesql = statement.executeQuery("select distinct sidesname from "+tablename+" order by sidesname");
										
										while(sidesnamesql.next()!=false)
										{
											if(sidesnamesql.getString(1).contains(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different sidesname. This does not exists in database.");
											flag1=1;
										}
										
										sidesnamesql.close();
									}
									else if(tablename.toUpperCase().contains("BEVERAGES"))
									{
										ResultSet beveragenamesql = statement.executeQuery("select distinct name from "+tablename+" order by name");
										
										while(beveragenamesql.next()!=false)
										{
											if(beveragenamesql.getString(1).contentEquals(pizzaname))
											{
												flag1=0;
												break;
											}
										}
										
										if(flag1!=0)
										{
											System.out.println("\nPlease enter a different beverage name. This does not exists in database.");
											flag1=1;
										}
										
										beveragenamesql.close();
									}
							}while(flag1==1);
							
							flag1=1;
							
							do
							{
								do
								{
									System.out.print("\nEnter the current size of the pizza/sides/beverage: ");
									size=scanner1.nextLine();
									
									Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
									Matcher match = usersize.matcher(size);
									
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
								
								if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
								{
									ResultSet pizzasizesql = statement.executeQuery("select pizzasize from "+tablename+" where pizzaname = '"+pizzaname+"'");
									
									while(pizzasizesql.next()!=false)
									{
										if(pizzasizesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
										
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different pizza size. This size does not exists for this pizza in database.");
										flag1=1;
									}
									
									pizzasizesql.close();
								}
								else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
								{
									ResultSet sidessizesql = statement.executeQuery("select sidessize from "+tablename+" where sidesname = '"+pizzaname+"'");
									
									while(sidessizesql.next()!=false)
									{
										if(sidessizesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different sides size. This size does not exists for this side in database.");
										flag1=1;
									}
									
									sidessizesql.close();
								}
								else if(tablename.toUpperCase().contains("BEVERAGES"))
								{
									ResultSet beveragenamesql = statement.executeQuery("select beveragesize from "+tablename+" where name = '"+pizzaname+"'");
									
									while(beveragenamesql.next()!=false)
									{
										if(beveragenamesql.getString(1).contentEquals(size.toUpperCase()))
										{
											flag1=0;
											break;
										}
									}
									
									if(flag1!=0)
									{
										System.out.println("\nPlease enter a different beverage size. This size does not exists for this beverage in database.");
										flag1=1;
									}
									
									beveragenamesql.close();
								}
							}while(flag1==1);
							
							do
							{
								System.out.print("\nEnter the new size of the pizza/sides/beverage: ");
								newsize=scanner1.nextLine();
								
								Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
								Matcher match = usersize.matcher(newsize);
								
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
							
							if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
							{
								sqlquery = "update "+tablename+" set pizzasize = '"+newsize.toUpperCase()+"' where pizzaname = '"+pizzaname+"' and pizzasize = '"+size.toUpperCase()+"'";  // query built
								statement.executeUpdate(sqlquery);		// query executed
								statement.close();
								System.out.println("\nRecord updated succesfully !!!");
							}
							else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
							{
								sqlquery = "update "+tablename+" set sidessize = '"+newsize.toUpperCase()+"' where sidesname = '"+pizzaname+"' and sidessize = '"+size.toUpperCase()+"'";  // query built
								statement.executeUpdate(sqlquery);		// query executed
								statement.close();
								System.out.println("\nRecord updated succesfully !!!");
							}
							else if(tablename.toUpperCase().contains("BEVERAGES"))
							{
								sqlquery="update "+tablename+" set beveragesize = '"+newsize.toUpperCase()+"' where name = '"+pizzaname+"' and beveragesize = '"+size.toUpperCase()+"'";
								statement.executeUpdate(sqlquery);
								statement.close();
								System.out.println("\nRecords updated succesfully !!!");
							}
						}
							break;
					case 4:
						{
							System.out.println("\nYou have chose to exit ! Bye!!!");
							
						}
						break;
					}
						do
						{
							try
							{
								System.out.print("\nDo you want to update more records ( 1 - Yes , 0 - No ): ");
								choice=scanner1.nextInt();
								scanner1.nextLine();
								
								if(choice==1||choice==0)
								{
									flag=0;
								}
								else
								{
									flag=1;
									System.out.println("\nPlease enter a valid choice.");
								}
							}
							catch(InputMismatchException ime)
							{
								System.out.println("\nPlease enter digits only.");
								scanner1.nextLine();
								flag=1;
							}
							
							
							
						}while(flag==1);
						
						
					
					
				}while(choice==1);
				
				connection.close();
				
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
	}
	
	void delete()
	{
		int choice=0,flag=0,flag1=1;
		String pizzaname,tablename,size;
		Scanner scanner = new Scanner(System.in);
		
		try		// connection established to database
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			try
			{
				Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "system");
				if(connection==null)
				{
					System.out.println("Connection to oracle failed.");
				}
				
				do
				{
				Statement state = connection.createStatement();
				
				do		// records to be inserted asked from user
				{
					System.out.print("\nEnter the table name to delete records in: ");
					tablename=scanner.nextLine();
					
					// tablename validation done 
					ResultSet tablecheck = state.executeQuery("select tname from tab");
					
					while(tablecheck.next()!=false)
					{
						if(tablecheck.getString(1).contentEquals(tablename.toUpperCase()))
						{
							flag=0;
							break;
						}
						else
						{
							flag=1;
						}
					}
						
					if(flag==1)
					{
						System.out.println("\nPlease enter a different table name. This table does not exists.");
					}
					
					tablecheck.close();
				}while(flag==1);
					
				state.close();
					
				Statement statement = connection.createStatement();
				do
				{
					do
					{
						System.out.print("\nEnter the name of the pizza/sides/beverage: ");
						pizzaname = scanner.nextLine();
						
						Pattern pat = Pattern.compile("[A-Z,a-z]");
						Matcher mat = pat.matcher(pizzaname);
						
						if(mat.find())
						{
							flag=0;
						}
						else
						{
							System.out.println("\nPlease enter a valid name.");
							flag=1;
						}
						
					}while(flag==1);
					
					if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
					{
						ResultSet pizzanamesql = statement.executeQuery("select distinct pizzaname from "+tablename+" order by pizzaname");
						
						while(pizzanamesql.next()!=false)
						{
							if(pizzanamesql.getString(1).contentEquals(pizzaname))
							{
								flag1=0;
								break;
							}
							
						}
						
						if(flag1!=0)
						{
							System.out.println("\nPlease enter a different pizzaname. This does not exists in database.");
							flag1=1;
						}
						
						pizzanamesql.close();
					}
					else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
					{
						ResultSet sidesnamesql = statement.executeQuery("select distinct sidesname from "+tablename+" order by sidesname");
						
						while(sidesnamesql.next()!=false)
						{
							if(sidesnamesql.getString(1).contentEquals(pizzaname))
							{
								flag1=0;
								break;
							}
						}
						
						if(flag1!=0)
						{
							System.out.println("\nPlease enter a different sidesname. This does not exists in database.");
							flag1=1;
						}
						
						sidesnamesql.close();
					}
					else if(tablename.toUpperCase().contains("BEVERAGES"))
					{
						ResultSet beveragenamesql = statement.executeQuery("select distinct name from "+tablename+" order by name");
						
						while(beveragenamesql.next()!=false)
						{
							if(beveragenamesql.getString(1).contentEquals(pizzaname))
							{
								flag1=0;
								break;
							}
						}
						
						if(flag1!=0)
						{
							System.out.println("\nPlease enter a different beverage name. This does not exists in database.");
							flag1=1;
						}
						
						beveragenamesql.close();
					}
				}while(flag1==1);
				
				flag1=1;
				
				do
				{
					do
					{
						System.out.print("\nEnter the size of the pizza/sides/beverage to delete: ");
						size=scanner.nextLine();
						
						Pattern usersize = Pattern.compile("[s,m,l,S,M,L]");
						Matcher match = usersize.matcher(size);
						
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
					
					if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
					{
						ResultSet pizzasizesql = statement.executeQuery("select pizzasize from "+tablename+" where pizzaname = '"+pizzaname+"'");
						
						while(pizzasizesql.next()!=false)
						{
							if(pizzasizesql.getString(1).contentEquals(size.toUpperCase()))
							{
								flag1=0;
								break;
							}
							
						}
						
						if(flag1!=0)
						{
							System.out.println("\nPlease enter a different pizza size. This size does not exists for this pizza in database.");
							flag1=1;
						}
						
						pizzasizesql.close();
					}
					else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
					{
						ResultSet sidessizesql = statement.executeQuery("select sidessize from "+tablename+" where sidesname = '"+pizzaname+"'");
						
						while(sidessizesql.next()!=false)
						{
							if(sidessizesql.getString(1).contentEquals(size.toUpperCase()))
							{
								flag1=0;
								break;
							}
						}
						
						if(flag1!=0)
						{
							System.out.println("\nPlease enter a different sides size. This size does not exists for this side in database.");
							flag1=1;
						}
						
						sidessizesql.close();
					}
					else if(tablename.toUpperCase().contains("BEVERAGES"))
					{
						ResultSet beveragenamesql = statement.executeQuery("select beveragesize from "+tablename+" where name = '"+pizzaname+"'");
						
						while(beveragenamesql.next()!=false)
						{
							if(beveragenamesql.getString(1).contentEquals(size.toUpperCase()))
							{
								flag1=0;
								break;
							}
						}
						
						if(flag1!=0)
						{
							System.out.println("\nPlease enter a different beverage size. This size does not exists for this beverage in database.");
							flag1=1;
						}
						
						beveragenamesql.close();
					}
				}while(flag1==1);
				
				String sqlquery;
				
				if(tablename.toUpperCase().contains("VEGPIZZAMENU")||tablename.toUpperCase().contains("NONVEGPIZZAMENU"))
				{
					sqlquery = "delete from "+tablename+" where pizzaname = '"+pizzaname+"' and pizzasize = '"+size.toUpperCase()+"'";  // query built
					statement.executeUpdate(sqlquery);		// query executed
					statement.close();
					System.out.println("\nRecord deleted succesfully !!!");
				}
				else if(tablename.toUpperCase().contains("VEGSIDESMENU")||tablename.toUpperCase().contains("NONVEGSIDESMENU"))
				{
					sqlquery = "delete from "+tablename+" where sidesname = '"+pizzaname+"' and sidessize = '"+size.toUpperCase()+"'";  // query built
					statement.executeUpdate(sqlquery);		// query executed
					statement.close();
					System.out.println("\nRecord deleted succesfully !!!");
				}
				else if(tablename.toUpperCase().contains("BEVERAGES"))
				{
					sqlquery = "delete from "+tablename+" where name = '"+pizzaname+"' and beveragesize = '"+size.toUpperCase()+"'";
					statement.executeUpdate(sqlquery);		// query executed
					statement.close();
					System.out.println("\nRecord deleted succesfully !!!");
				}
				
				
				do
				{
					try
					{
						System.out.print("\nDo you want to delete more records ( 1 - Yes , 0 - No ): ");
						choice=scanner.nextInt();
						
						if(choice==1||choice==0)
						{
							flag=0;
						}
						else
						{
							flag=1;
							System.out.println("\nPlease enter a valid choice.");
						}
					}
					catch(InputMismatchException ime)
					{
						System.out.println("\nPlease enter digits only.");
						scanner.nextLine();
						flag=1;
					}
					
					
					
				}while(flag==1);
				
				}while(choice==1);
				
				connection.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
	}
}
