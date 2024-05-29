package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainpackage.users.model.Client;
import mainpackage.users.model.User;

public class ClientDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_CLIENT_SQL = "INSERT INTO users" 
	+ "  (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?); " + "INSERT INTO clients" 
	+ "  (username, balance, phone_number, AFM) VALUES (?, ?, ?, ?); ";
	
	

	public ClientDao() {
	}

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

	public void insertClient(Client client) throws SQLException {
		System.out.println(INSERT_CLIENT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
			preparedStatement.setString(1, client.getUsername());
			preparedStatement.setString(2, client.getPassword());
			preparedStatement.setString(3, client.getName());
			preparedStatement.setString(4, client.getSurname());
			preparedStatement.setInt(5, client.getRole());
			preparedStatement.setString(6, client.getUsername());
			preparedStatement.setDouble(7, client.getBalance());
			preparedStatement.setString(8, client.getPhoneNumber().getNumber());
			preparedStatement.setString(9, client.getAFM());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
