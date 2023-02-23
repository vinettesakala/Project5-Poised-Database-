import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException; 

/**
 * The Projects class contains all the methods used in the 'programDatabase' which pertain to adding, editing, finalising, 
 * or viewing various projects for the Poised Management System.
 * <p>
 * @author Vinette Sakala
 */
public class projectsDatabase  {
	 

	 Scanner input = new Scanner(System.in);
	/**
	 * This method allows user to create a new project object, which is added to the 'projects' table in the 
	 * PoisePMS database. 
	 * <p>
	 * It prompts users to enter various information related to a new project object, and then connects to the external
	 * database to update project information. The 'programDatabase' class calls on this method when the user chooses to add 
	 * a new project to the system.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @return 
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public  void addProject(Statement statement) throws SQLException {
		int projectNumber = 0;
		// Project input
        boolean flag1 = false;
	     while(!flag1) {
	    	 //make the loop break, if no exception caught
	         flag1 = true;
		     try {
		    	 System.out.println("Enter the project number: ");
		         String userInput =input.nextLine();
                projectNumber = Integer.parseInt(userInput);
            }catch(NumberFormatException e) {
           	 System.out.println("Please enter a number!");
                //make the loop repeat
                flag1 = false;
	         }
	     }
	     System.out.println("Enter the project name: ");
	     String projectName = input.nextLine();
	     System.out.println("Enter the type of building eg House,Apartment block,Store etc");
	     String buildingType = input.nextLine();
	     System.out.println("Enter the physical address for the project: ");
	     String projectAddress = input.nextLine();
	     int numberERF = 0;
	     boolean flag2 = false;
	     while(!flag2) {
	    	 //make the loop break, if no exception caught
	         flag2 = true;
	         try {
	        	 System.out.println("Enter the ERF number: ");
	             String userInput =input.nextLine();
                 numberERF= Integer.parseInt(userInput);
            }catch(NumberFormatException e) {
           	 System.out.println("Please enter a number!");
                //make the loop repeat
                flag2 = false;
            }
        }
	     double projectFee = 0;
	     boolean flag3 = false;
	     while(!flag3) {
	    	 //make the loop break, if no exception caught
	         flag3 = true;
	         try {
	        	 System.out.println("Enter the total fee being charged for the project: ");
	             String userInput =input.nextLine();
                 projectFee= Double.parseDouble(userInput);
            }catch(NumberFormatException e) {
           	 System.out.println("Please enter amount!");
                //make the loop repeat
                flag3 = false;
            }
        }
	     double amountPaid = 0;
	     boolean flag4 = false;
	     while(!flag4) {
	    	 flag4 =true;
	         try {
	        	 System.out.println("Enter the total amount paid to date: ");
	             String inputUser =input.nextLine();
                 amountPaid= Double.parseDouble(inputUser);
            }catch(NumberFormatException e) {
           	 System.out.println("Please enter amount!");
                //make the loop repeat
                flag4 = false;
            }
        }
	     //Enter project deadline
	     System.out.println("Enter due date for example  3-Dec-2023 (day/month/year).Please use exact date format. ");
	     String deadline = input.nextLine();
	     //Set project completed to no
	     String projectCompleted = "No";
	     String dateCompleted = "Null";
	     
	     /* The projects table in the 'PoisePMS' database is then updated.
		 * The information inputed by the user is inserted into the appropriate columns, 
		 * thus creating and storing a new project object.
		 */
		 statement.executeUpdate(
	                "INSERT INTO projects VALUES (" + projectNumber + ", " + "'" + projectName + "'" + ", " + "'" 
	        + buildingType + "'" + ", " + "'" + projectAddress + "'" + ", " + "'" + numberERF + "'" + ", " + projectFee + ", " + amountPaid + ", " + 
	        "'" + deadline + "'" + ", " + "'" + projectCompleted + "'" + ", " + "'" + dateCompleted + "'" + ");"
	            );
			
			// A successful message is displayed and the user can then view the updated project list.
			System.out.println("\nYour new project was successfully added. View updated projects below: \n"); 
			printAllFromTable(statement); 	
	}
	
	String userInput = "";
	int choice = 0;
	/**
	 * This method allows users to edit project information, relating to the project due date and total amount paid.
	 * <p>
	 * It displays a sub-menu to the user with two edit choices and executes the action depending on their choice. 
	 * The edited information is then written under the corresponding column of the 'projects' table in the 
	 * external PoisePMS database.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public void editProject(Statement statement) throws SQLException {
	
	    Scanner in = new Scanner(System.in);
		// The user is prompted to enter a project number to edit.
		System.out.println("Please enter the number of the project you wish to update: \n");
		int projectNumber = in.nextInt();
        in.nextLine();
		System.out.println("Would you like to:" + 
				"\n1. Edit the project due date or" +
				"\n2. Edit the total amount paid of the fee to date?"+
				"\nChoose either 1 or 2"); // Edit options displayed.
				userInput =in.nextLine();
                choice = Integer.parseInt(userInput);
		
		/* If the user selects option 1, they are prompted to enter a new deadline.
		 * The new value is then written to the projects table with the executeUpdate() SQL statement.
		 */
		if (choice == 1) {
			System.out.println("Please enter a new project deadline: ");
			 String updateDeadline = input.nextLine();
			 
			statement.executeUpdate(
                    "UPDATE projects SET deadline = '" + updateDeadline + "'" + " WHERE projectNumber = " + projectNumber
                );
			
			// A successful message is displayed to the user and then they are able to view the list of updated projects.
			System.out.println("Your project info has been successfully updated. View projects below: ");
			printAllFromTable(statement);
		
		/* If the user selects option 2, they are prompted to enter a new amount paid.
		 * The new value is then written to the projects table with the executeUpdate() SQL statement.
		 */
		} else if (choice == 2) {
			Scanner input1 = new Scanner(System.in);
			
			System.out.println("Please enter a new total amount paid: ");
			int updateAmount = input1.nextInt();
	         input1.nextLine();
			
			statement.executeUpdate(
                    "UPDATE projects SET amountPaid = " + updateAmount + " WHERE ProjectNumber = " + projectNumber
                );
			
			// A successful message is displayed to the user and then they are able to view the list of updated projects.
			System.out.println("Your project info has been successfully updated. View projects below: ");
			printAllFromTable(statement);
			
		}
	}
	/**
    * This method allows users to finalise a project located in the projects table in the external 'PoisePMS' database.
    * <p>
    * The user is prompted to enter a project number to locate the project. If there is an outstanding amount on the project,
    * an invoice is generated and displayed with customer details. Otherwise, the project is just marked as finalised and a
    * completion date is added.
    * <p>
    * @param statement statement object linked to connection to perform SQL commands
    * @throws SQLException occurs if there is an error accessing the database information
    */
	public void finaliseProject(Statement statement) throws SQLException {
		Scanner in = new Scanner(System.in);
	    // The user is prompted to enter a project number to finalise.
	    System.out.println("Please enter the  project number that you wish to finalise: ");
	    int projectNumber = in.nextInt();
        in.nextLine();
	    // Selecting the Total_Fee and Amount_Paid columns from the table.
	    ResultSet results2 = statement.executeQuery("SELECT totalFee, amountPaid FROM projects WHERE projectNumber = " + projectNumber);
	    float total_Fee = 0;
	    float amount_paid = 0;
	
	    // Iterating through the columns and storing the two float numbers into corresponding variables.
	    while (results2.next()) {
	    	total_Fee = results2.getFloat("totalFee"); 
		    amount_paid = results2.getFloat("amountPaid");
		
	    }
	    // If the project has been paid in full, the amount paid will equal the total fee for the project.
	    // This means no invoice needs to be generated.
	    if (total_Fee == amount_paid) {
	    	System.out.println("This project has already been paid in full. No invoice to be generated.");
		
		    // The user is then prompted to enter a completion date, which is written into the 'dateCompleted' column 
		    // in the projects table with the executeUpdate() statement.
		    System.out.println("Please add a completion date for the project: ");
		    String completionDate = in.nextLine();
		    // Completion date added to user's chosen project by project number.
		    statement.executeUpdate(
		    		"UPDATE projects SET dateCompleted = " + "'" + completionDate + "'" + " WHERE projectNumber = " + projectNumber
				);
		
		    // The project is then marked as finalised by writing 'Yes' to the projectCompleted column in the table.
		    statement.executeUpdate(
        		 "UPDATE projects SET projectCompleted = 'Yes' WHERE projectNumber = " + projectNumber
                );
		
		    // A successful message is displayed and the user is able to view the updated project list.
		    System.out.println("Your project has been successfully finalised. View projects below: ");
		    printAllFromTable(statement);
	
	       /* If there is still an amount outstanding on the project, an invoice will be generated.
	       * A 'personDatabase' object is then created to access the 'displayPerson() method from the personDatabase class.
	       * The customer details for the selected project are then displayed to the user for the invoice. 
	       */
	    } else if (total_Fee != amount_paid) {
	    	System.out.println("There is still an outstanding amount to be paid for this project. View your invoice below: \n");
		
		    personDatabase customer = new personDatabase();
		    customer.displayCustomer(statement, projectNumber);
		
		    // Added to the customer info, is the amount owing on the project.
		    System.out.println("\nAmount Outstanding: R" + (total_Fee - amount_paid));
		
		   // The user is then prompted to enter a completion dated for the project.
		   System.out.println("\nPlease add a completion date for the project: ");
		   String completionDate = in.nextLine();
		
		   // The date entered by the user is written to the projects table under the 'dateCompleted' column.
		   statement.executeUpdate(
				"UPDATE projects SET dateCompleted = " + "'" + completionDate + "'" + " WHERE projectNumber= " + projectNumber
				);
		
		   // The project is then marked as finalised by writing 'Yes' to the projectCompleted column in the table.
		   statement.executeUpdate(
        		 "UPDATE projects SET projectCompleted = 'Yes' WHERE projectNumber = " + projectNumber
                );
		
		  // A successful message is displayed and the user is able to view the updated project list.
		  System.out.println("Your project has been successfully finalised. View projects below: ");
		  printAllFromTable(statement);	
	}
}
	/**
    * This method allows users to view all project objects that are incomplete (i.e. not finalised and no completion 
    * date added) in the projects table in the external 'PoisePMS' database.
    * <p>
    * @param statement statement object linked to connection to perform SQL commands  
    * @throws SQLException occurs if there is an error accessing the database information
    */
    public void viewIncomplete(Statement statement) throws SQLException {
	
    	System.out.println("\nPlease view all incomplete projects below: \n");
	
	    ResultSet results3 = statement.executeQuery("SELECT * FROM projects WHERE projectCompleted = 'No' AND dateCompleted = 'Null'");
	
	    // All incomplete projects are displayed using a table iterator.
	    while (results3.next()) {
	    	System.out.println( 
				"Project Number: \t" + results3.getInt("projectNumber")
                + "\nProject Name: \t" + results3.getString("projectName") 
                + "\nBuilding Type: \t" + results3.getString("buildingType")        
                + "\nPhysical Address: " + results3.getString("physicalAddress") 
                + "\nERF Number: \t" + results3.getString("erfNumber") 
                + "\nTotal Fee: \tR" + results3.getFloat("totalFee") 
                + "\nAmount Paid: \tR" + results3.getFloat("amountPaid")  
                + "\nDeadline: \t" + results3.getString("deadline") 
                + "\nProject Completed: \t" + results3.getString("projectCompleted") 
                + "\nCompletion Date: " + results3.getString("dateCompleted") 
                + "\n"
				);
	}
}

/**
 * This method allows users to view all project objects that are overdue in the projects table in the 
 * external 'PoisePMS' database.
 * <p>
 * When called on, the method runs through all deadlines of incomplete projects and compares the deadline date with the
 * current date. If overdue, the project is displayed in an easy-to-read format. If no overdue projects are present, an
 * appropriate error message is displayed to the user.
 * <p>
 * @param statement statement object linked to connection to perform SQL commands
 * @throws SQLException occurs if there is an error accessing the database information
 * @throws ParseException occurs if a date string is in the wrong format to be parsed 
 */

public void viewOverdue(Statement statement) throws SQLException, ParseException {
	
	ResultSet results;
	boolean proj_Check = false;
	String[] info;
	int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	String[] monthsofYear = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	int monthNum = 0;
	
	// Overdue projects will be incomplete, therefore only the deadline date info from columns of incomplete projects are located.
	results = statement.executeQuery("SELECT deadline FROM projects WHERE projectCompleted = 'No' AND dateCompleted = 'Null'");
		
	
	
	// Iterating through the deadline dates in the incomplete projects to check if they are overdue.
	while (results.next()) {
		
		// The deadline date in the project is stored in the string variable 'date_info'.
		// This variable is then split into an array called 'info' by removing the dash character '-' from the date (e.g. 15-Dec-2022)
		// The first indexed value of 'info' is then parsed and stored into an integer variable called 'day'.
		String date_info = results.getString("deadline");
		info = date_info.split("-");
		int day = Integer.parseInt(info[0]);
		
		/* The second indexed value from the info array is stored in a variable called 'monthInfo'.
		 * monthInfo is then split further to store only three letters of the month name into string variable 'month' (e.g. 'Dec').
		 * A year variable is also created and assigned the parsed value from the third index in 'info' array.
		 */
		String monthInfo = info[1];
		String month = (monthInfo.substring(0,2));
		int year = Integer.parseInt(info[2]);
		
		/* A for loop is then used to compare the substring 'month' with the monthsofYear string array.
		 * Once matched with an abbreviated month of the year, the corresponding number from the integer array 'months'
		 * is stored in the 'monthNum' variable, for use as date info.
		 */
		for (int index = 0; index < monthsofYear.length ; index++) {	
			if (month.equalsIgnoreCase(monthsofYear[index])) {
				monthNum = months[index];
				
			}
		}
		// Getting the current date and storing it as a string.
		String current = "" + java.time.LocalDate.now();  
		
		// Creating a new simple date format object.
		SimpleDateFormat dateObj = new SimpleDateFormat("d-MM-yyyy");
		
		// Dates d1 and d2 are then created by parsing string info from 'current' date 
		// and date info gathered from the file above.
		Date d1 = dateObj.parse(current);
		
		Date d2 = dateObj.parse(day + "-" + monthNum + "-" + year);
		 
		// If the current date has passed the deadline for the project, it is overdue.
		// The proj_Check is set to 'true' and all of the columns for that project are selected and displayed.
		if (d1.compareTo(d2) < 0) {
			proj_Check = true;
			
			System.out.println("\nPlease view all overdue projects below: \n");
		
		
			results = statement.executeQuery("SELECT * from projects WHERE deadline = '" + date_info + "'");
			
			// Iterating and displaying all info related to the overdue project.
			while (results.next()) {
	    		System.out.println(
	    				 "Project Number: \t" + results.getInt("projectNumber")
	    	                + "\nProject Name: \t" + results.getString("projectName") 
	    	                + "\nBuilding Type: \t" + results.getString("buildingType")        
	    	                + "\nPhysical Address: " + results.getString("physicalAddress") 
	    	                + "\nERF Number: \t" + results.getString("erfNumber") 
	    	                + "\nTotal Fee: \tR" + results.getFloat("totalFee") 
	    	                + "\nAmount Paid: \tR" + results.getFloat("amountPaid")  
	    	                + "\nDeadline: \t" + results.getString("deadline") 
	    	                + "\nProject Completed: \t" + results.getString("projectCompleted") 
	    	                + "\nCompletion Date: " + results.getString("dateCompleted") 
	    	                + "\n"
	    				 
	            );
			}	
		// However, if there are no overdue projects, proj_Check is set to 'false'.
		} else {
			proj_Check = false;
		}
// If proj_Check is set at false after the projects are all checked, an appropriate message is displayed to the user.		
} if (proj_Check == false) {
	System.out.println("There are no overdue projects listed on the system.");
}
}

/**
 * This method allows users to find project objects from the projects table in the external 'PoisePMS' 
 * database by either entering the project name or number.
 * <p>
 * Using either name or number, the project is then located from the external database and displayed in an easy-to-read-format.
 * <p>
 * @param statement statement object linked to connection to perform SQL commands
 * @throws SQLException occurs if there is an error accessing the database information
 */
int searchChoice = 0;
public void findProject(Statement statement) throws SQLException {
	Scanner in = new Scanner(System.in);
	System.out.println("Would you like to search for your project by \n1.) project number or \n2.) project name? \nPlease select either 1 or 2.");
	userInput =in.nextLine();
    searchChoice = Integer.parseInt(userInput);

	/* If they choose option 1, they are prompted to enter the project number.
	 * Once the number has been entered, the program selects all info related to that project to display to the user.
	 */
	if (searchChoice == 1) {
		System.out.println("\nPlease enter the project number you wish to locate: ");
		int projectNumber = in.nextInt();
        in.nextLine();
		
		System.out.println("\nPlease view your project details below: \n");
		
		ResultSet results6 = statement.executeQuery("SELECT * from projects WHERE projectNumber = " + projectNumber);
		
		// Iterating through project info by column of the project selected by the user.
		while (results6.next()) {
    		System.out.println(
    				"Project Number: \t" + results6.getInt("projectNumber")
                    + "\nProject Name: \t" + results6.getString("projectName") 
                    + "\nBuilding Type: \t" + results6.getString("buildingType")        
                    + "\nPhysical Address: " + results6.getString("physicalAddress") 
                    + "\nERF Number: \t" + results6.getString("erfNumber") 
                    + "\nTotal Fee: \tR" + results6.getFloat("totalFee") 
                    + "\nAmount Paid: \tR" + results6.getFloat("amountPaid")  
                    + "\nDeadline: \t" + results6.getString("deadline") 
                    + "\nProject Completed: \t" + results6.getString("projectCompleted") 
                    + "\nCompletion Date: " + results6.getString("dateCompleted") 
                    + "\n"
    				
            );
		}
	/* If the user selects option 2, they are prompted to enter the project name. 
	 * Once entered, the program selecst all info related to that project to display to the user.
	 */
	} else if (searchChoice == 2) {
		System.out.println("\nPlease enter the name of the project you wish to locate: ");
		String projectName = in.nextLine();
	
		System.out.println("\nPlease view your project details below: \n");
		
		ResultSet results7 = statement.executeQuery("SELECT * from projects WHERE projectName = '" + projectName + "'");
		
		// Iterating through project info by column of the project selected by the user.
		while (results7.next()) {
    		System.out.println(
    				"Project Number: \t" + results7.getInt("projectNumber")
                    + "\nProject Name: \t" + results7.getString("projectName") 
                    + "\nBuilding Type: \t" + results7.getString("buildingType")        
                    + "\nPhysical Address: " + results7.getString("physicalAddress") 
                    + "\nERF Number: \t" + results7.getString("erfNumber") 
                    + "\nTotal Fee: \tR" + results7.getFloat("totalFee") 
                    + "\nAmount Paid: \tR" + results7.getFloat("amountPaid")  
                    + "\nDeadline: \t" + results7.getString("deadline") 
                    + "\nProject Completed: \t" + results7.getString("projectCompleted") 
                    + "\nCompletion Date: " + results7.getString("dateCompleted") 
                    + "\n"
            );
		}
	}
}
/**
 * This method displays all information from the projects table in the PoisePMS database when called on.
 * <p>
 * @param statement statement object linked to connection to perform SQL commands
 * @throws SQLException occurs if there is an error accessing the database information
 */
public void printAllFromTable(Statement statement) throws SQLException{
        
		// Selecting all information (i.e. all rows) from the main_project_info table.
    	ResultSet results = statement.executeQuery("SELECT * FROM projects");
    	
    	// Iterating through info in each column to display to the user.
    	while (results.next()) {
    		System.out.println(
                "Project Number: \t" + results.getInt("projectNumber")
                + "\nProject Name: \t" + results.getString("projectName") 
                + "\nBuilding Type: \t" + results.getString("buildingType")        
                + "\nPhysical Address: " + results.getString("physicalAddress") 
                + "\nERF Number: \t" + results.getString("erfNumber") 
                + "\nTotal Fee: \tR" + results.getFloat("totalFee") 
                + "\nAmount Paid: \tR" + results.getFloat("amountPaid")  
                + "\nDeadline: \t" + results.getString("deadline") 
                + "\nProject Completed: \t" + results.getString("projectCompleted") 
                + "\nCompletion Date: " + results.getString("dateCompleted") 
                + "\n"
            );
        }
 }

}

