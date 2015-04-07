package core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection() {
		String address = "jdbc:mysql://ipAdress/Database";
		String id = "your_id";
		String pw = "your_pw";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(address, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}