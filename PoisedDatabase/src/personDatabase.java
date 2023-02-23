import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Persons class contains a method to view a customer's details relating to a specific project in an easy-to-read format.
 * <p>
 * The method in this class is used in the Projects class to create a customer object and display their details to generate an invoice.
 * @author Vinette Sakala
 */
public class personDatabase {

	/**
	 * This method enables a customer object to be displayed in an easy-to-read format.
	 * <p>
	 * It selects a customer's details from the 'projects' table in the external 'PoisePMS' database by 
	 * locating a specific project number, and then displays the necessary information in a clear format to the user.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @param projectNumber projectNumber an integer entered by the user to locate a specific project object
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public void displayCustomer(Statement statement, int projectNumber) throws SQLException {
		
		ResultSet results1 = statement.executeQuery("SELECT name, surname,telephoneNumber, emailAddress, physicalAddress FROM person WHERE projectNumber = " + projectNumber
				+ " AND personType = 'Customer'");
		
		// Customer details displayed using iterator in table.
		while (results1.next()) {  
			System.out.println( 
					"\nCustomer Name: " + results1.getString("name")
					+"\nCustomer Surname" + results1.getString("surname")
					+ "\nContact Number: " + results1.getInt("telephoneNumber")
					+ "\nEmail Address: " + results1.getString("emailAddress")
					+ "\nPhysical Address: " + results1.getString("physicalAddress")
					);	
		}
	}
}

