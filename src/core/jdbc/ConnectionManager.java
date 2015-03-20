package core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection() {
		String address = "jdbc:mysql://127.0.0.1/jado_dev";
		String id = "root";
		String pw = "gpdnjs26";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(address, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}