import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {

	public MyDatabase() {
		// TODO Auto-generated constructor stub
	}

	// JDK 1.7 and above
	public static Connection getJdbc() { //
		Connection conn = null;
		try {
			// Step 1: Allocate a database 'Connection' object
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lavaflo?useTimezone=true&serverTimezone=UTC",
					"root", "testpwd");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		// Step 5: Close the resources - Done automatically by try-with-resources
		return conn;
	}
	
	
}
