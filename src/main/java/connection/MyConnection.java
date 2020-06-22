package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	public static Connection getMyConnection() {
		String host = "jdbc:mysql://localhost:3306/datawarehousedb";
		String username = "root";
		String password = "";

		Connection myConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			myConnection = DriverManager.getConnection(host, username, password);

			System.out.println("Success");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return myConnection;

	}
}
