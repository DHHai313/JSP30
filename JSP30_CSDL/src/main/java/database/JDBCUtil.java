package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection c = null;
		try {
			// tao dang ky
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/mywebsite";
			String username = "root";
			String password = "";
			// tao ket noi
			c = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
