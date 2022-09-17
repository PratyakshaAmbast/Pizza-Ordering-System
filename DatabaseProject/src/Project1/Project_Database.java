package Project1;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project_Database {

	public static void menudatabase() {
		
		int choice=0,flag=0;
		Scanner sc = new Scanner(System.in);
		
		Create_database cd = new Create_database(); // Create_database object cd created
		
		
		
		do			// loop will run until user does not opt to exit
		{
			System.out.println("\n1. Create a table\n2. Insert records\n3. Update records\n4. Delete records\n5. Exit");
			do
			{					// validation of choice
				try
				{
					System.out.print("\nEnter your choice: ");
					choice = sc.nextInt();
					Pattern pat = Pattern.compile("[1,2,3,4,5]");
					Matcher mat = pat.matcher(String.valueOf(choice));
					if(mat.find())
					{
						flag=0;
					}
					else
					{
						System.out.println("\nPlease enter a correct choice.");
						flag=1;
					}
				}
				catch(InputMismatchException ime) 
				{
					System.out.println("\nPlease enter a digit.");
					sc.nextLine();
					flag=1;
				}
			}while(flag==1);
			
			switch(choice)		// switch case used to handle choice
			{
			case 1:
					cd.create();
					break;
			case 2:
					cd.insert();
					break;
			case 3:
					cd.update();
					break;
			case 4:
					cd.delete();
					break;
			case 5:
				{
					System.out.println("\nYou have chose to quit. Bye !!!\n");
				}
					break;
			}
			
			
		}while(choice!=5);
		
		ProjectMain.main(null);
		
		
	}

}
