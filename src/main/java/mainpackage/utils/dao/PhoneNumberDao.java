package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainpackage.utils.model.PhoneNumber;

public class PhoneNumberDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_PHONENUMBER_SQL = "INSERT INTO phone_numbers" 
	+ "  (phone_number, program_id, minutes, program_name) VALUES (?, ?, ?, ?); ";
	

	public PhoneNumberDao() {
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

	public void insertUser(PhoneNumber phoNum) throws SQLException {
		System.out.println(INSERT_PHONENUMBER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PHONENUMBER_SQL)) {
			preparedStatement.setString(1, phoNum.getNumber());
			preparedStatement.setInt(2, phoNum.getProgram().getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}