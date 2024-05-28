package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mainpackage.users.model.Seller;

public class SellerDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_SELLER_SQL = "INSERT INTO users" 
	+ "  (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?); " + "INSERT INTO sellers" 
	+ "  (company, username) VALUES (?, ?); ";
	
	

	public SellerDao() {
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

	public void insertSeller(Seller seller) throws SQLException {
		System.out.println(INSERT_SELLER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SELLER_SQL)) {
			preparedStatement.setString(1, seller.getUsername());
			preparedStatement.setString(2, seller.getPassword());
			preparedStatement.setString(3, seller.getName());
			preparedStatement.setString(4, seller.getSurname());
			preparedStatement.setString(5, seller.getRole());
			preparedStatement.setString(6, seller.getCompany());
			preparedStatement.setString(7, seller.getUsername());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
