package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		final String URL = "jdbc:mysql://localhost/EvaluationTask1";
		final String NAME = "root";
		final String PASS = "";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(URL, NAME, PASS);
		return con;
	}

}