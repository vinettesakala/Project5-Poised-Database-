// Importing necessary classes for use in the main program.import java.io.BufferedReader;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * The Poised Project class runs the main method for the Poised Program to execute. 
 * <p>
 * This program was designed for a small engineering company called 'Poised, to act as a project management system.
 * It uses a JDBC driver to access external information from the database 'PoisePMS' for use within the program. 
 * A menu with options is displayed to the user, which enables them to add new projects, edit existing projects etc., 
 * the results of which are then displayed to the user, and updated in the external database. The 'PoisePMS' database 
 * contains two tables of information namely, 'projects' and 'person'. The 'projects' 
 * table of information is accessed, edited and utilized extensively in this program. 
 * 
 * @author Vinette Sakala
 */
public class programDatabase {
	/**
	 *This is a  constructor
	 */
	public programDatabase() {}
	
	/**
	 * This is the main method which runs the Poised Management System Program.
	 * <p>
	 * @param args java command line arguments 
	 * @throws ParseException occurs if a date string is in the wrong format to be parsed 
	 */
	 public static void main(String[] args) throws ParseException, ClassNotFoundException {
		 
		 // Initializing a 'projectsDatabase' object to call methods from the projectsDatabase class.
		 projectsDatabase projObj = new projectsDatabase();
			
	     //Printing out message to User
		 System.out.println("Welcome to poised your Project Management Software");
		 
		 //Declaring variables
		 int option;
		 // A while loop is used to repeatedly return the user to the main menu after each choice made,
		 // until they select number 8, to exit the loop and log out of the program.
		 while(true) {
			 //Initiate scanner object
		     @SuppressWarnings("resource")
		     Scanner input = new Scanner(System.in);
			 //Displaying menu to user	
		     System.out.println("\nSelect one of the options below:"
		    		        + "\n1. View Existing Projects"
		                    + "\n2. Create a new project:"
		                    + "\n3. Update Existing Project Info:" 
		                    + "\n4. Finalise Project"
		                    + "\n5. View uncompleted projects" 
		                    + "\n6. View overdue projects"
		                    + "\n7. Find project"
		                    + "\n8. Exit Program");     
		    	 String userInput =input.nextLine();
	             option = Integer.parseInt(userInput);
	            
	             // A try-catch block is used to connect to the MySQL server and access the PoisePMS database.
	             try {
	            	 Connection connection = DriverManager.getConnection(
	 					 "jdbc:mysql://localhost:3306/PoisePMS?allowPublicKeyRetrieval=true&useSSL=false",
	                     "otheruser",
	                     "swordfish"
	                     );
	            	 
	            	 // Statement object created.
	            	 Statement statement = connection.createStatement();
	            
	            	 /* The actions related to user choice are now acted on. 
	     			 * If the user selects option '1', they are able to view all the projects listed in the database.
	     			 * The printAllFromTable() method is defined and explained below the main program method.
	     			 */
	            	 if (option == 1) {
	            		 projObj.printAllFromTable(statement);
	            	 }
	            	 /* If the user selects option '2', they are prompted for new project information.
				     * Their inputs are  stored in associated variables for use.
				     */
	            	 else if (option  == 2) {
	            		 projObj.addProject(statement);
	            	 }
	            	 /* If the user selects option '3' then they are allowed to edit project details on a chosen project. 
	 			     * They are prompted to enter a project number and then shown a short sub-menu with two edit choices.
	 			     * They then select whether they would like to edit the due date or amount paid on the project.
	 			     */
	 			     else if (option == 3) {
	 			    	 projObj.editProject(statement);
				     }
	            	 /* If the user selects option' 4' from the main menu, they are prompted for a project number to finalise it.
	     			 * 	Using the project number, the program then locates the total fee and amount paid for that particular project.
	     			 * These amounts are stored in the 'total_Fee' and 'amount_paid' variables.
	     			 */
	 			     else if (option == 4) {
	 					
	 					projObj.finaliseProject(statement);
	 					
	 				}
	            	 /* If the user selects option '5', they are able to view all incomplete projects in the database.
	     			 * An incomplete project is not finalised and has no completion date added, therefore the program locates incomplete project info 
	     			 * by checking those two columns in the projects table of the PoisePMS database.
	     			 */
	 			    else if (option == 5) {
	 					
	 					projObj.viewIncomplete(statement);
	 					
	 				 }
	            	 /* If the user selects option '6' from the main menu they are able to view all overdue projects, if any are listed. 
	     			 * A boolean proj_Check variable is set to determine whether overdue projects are present to be displayed.
	     			 * A string array 'info' is declared to store date information once it has been located and split from the table.
	     			 * Two more arrays, for months in integers and months in words are also set to get the month value stored in the table.
	     			 */
	 			     else if (option == 6) {
	 					
	 					projObj.viewOverdue(statement);
	 		
	 			     }
	            	 // If the user selects option '7' from the main menu, they are given the option to locate a project by number or name.	
	 			     else if (option == 7) {
	 					
	 					projObj.findProject(statement);
	 			     }
	            	 // The last option in the main menu, number '8', allows the user to log out of the system.
	     		     else if (option == 8) {
	     		    	 // If chosen, a successful log out message is displayed and the main while loop menu is exited.
	     			     System.out.println("You are successfully logged out.");
	     			     break;	
	     		     }		
	            	 // Catch created for SQL exception.	
	             }catch (SQLException e) {  
	            	e.printStackTrace();   
	             }
	     }	 
	 } 	
}






