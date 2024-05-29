package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import mainpackage.utils.model.Call;

public class CallDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_CALL_SQL = "INSERT INTO calls" 
	+ "  (call_id, startTime, endTime, caller_phone_number, receiver_phone_number) VALUES (?, ?, ?, ?, ?); ";
	
	

	public CallDao() {
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

	public void insertUser(Call call) throws SQLException {
		System.out.println(INSERT_CALL_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CALL_SQL)) {
			Timestamp timestamp = Timestamp.valueOf(call.getStartTime());
			preparedStatement.setString(1, call.getCallId());
			preparedStatement.setTimestamp(2, timestamp);
			timestamp = Timestamp.valueOf(call.getEndTime());
			preparedStatement.setTimestamp(3, timestamp);
			preparedStatement.setString(4, call.getCallerPhoneNumber());
			preparedStatement.setString(5, call.getReceiverPhoneNumber());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}