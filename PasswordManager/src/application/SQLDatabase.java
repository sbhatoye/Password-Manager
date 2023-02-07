package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDatabase {

	Connection connect = null;
	java.sql.PreparedStatement pst;

	public static Connection dataBConnect() {

		try {
			// Link for JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			/**
			 * 
			 * The method DriverManager.getConnection() allows for a connection to be
			 * established with the database
			 * 
			 * Parameters:
			 * 
			 * URL - form is jdbc:subprotocol:subname 
			 * user - username of database 
			 * password - user's password
			 * 
			 * If connection is successful, we can create a PreparedStatement object from this
			 * connection to query the database
			 * 
			 **/
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_manager", "root", "");
			return c;

		} catch (Exception e2) {

			System.out.println(e2);
			return null;
		}

	}

}