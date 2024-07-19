package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;

public class PhoneNumberDao {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private static final String INSERT_PHONENUMBER_SQL = "INSERT INTO phone_number" 
	+ "  (program_id,  phone_number) VALUES ( ?, ?); ";
	

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

	public void insertNumber(String phoneNumber, Program program) throws SQLException {
		System.out.println(INSERT_PHONENUMBER_SQL);
		// try-with-resource statement will auto close the connection.
		PhoneNumber phoNum = new PhoneNumber(phoneNumber, program);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PHONENUMBER_SQL)) {
			preparedStatement.setInt(1, phoNum.getProgram().getId());
			preparedStatement.setString(2, phoNum.getNumber());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public  void deleteNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		
	}
}