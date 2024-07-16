package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainpackage.users.model.Admin;

public class AdminDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "gr3ty";

	private static final String INSERT_ADMIN_SQL = "INSERT INTO users" 
	+ "  (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?); ";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertAdmin(Admin admin) throws SQLException {
		System.out.println(INSERT_ADMIN_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement.setString(2, admin.getPassword());
			preparedStatement.setString(3, admin.getName());
			preparedStatement.setString(4, admin.getSurname());
			preparedStatement.setInt(5, admin.getRole());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void setAdmin(String username) {
		// TODO Auto-generated method stub
		
	}
}