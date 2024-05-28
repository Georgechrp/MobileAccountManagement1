package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import mainpackage.users.model.User;

public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}



	public static List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}



	public static User selectUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	public static void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}
}
