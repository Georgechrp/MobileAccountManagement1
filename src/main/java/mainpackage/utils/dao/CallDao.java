package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import mainpackage.utils.model.Call;

public class CallDao {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_CALL_SQL = "INSERT INTO calls" 
	+ "  (call_id, startTime, endTime, caller_phone_number, receiver_phone_number) VALUES (?, ?, ?, ?, ?); ";
	
	private static final String SELECT_CALLS_BY_PHONE_NUMBER = "SELECT id, caller, receiver, start_time, end_time FROM `call` WHERE caller = ? OR receiver = ?";
	
	

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
	
	 public List<Call> getCallHistory(String phoneNumber) {
	        List<Call> calls = new ArrayList<>();
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CALLS_BY_PHONE_NUMBER)) {
	            preparedStatement.setString(1, phoneNumber);
	            preparedStatement.setString(2, phoneNumber);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String callId = rs.getString("id");
	                String callerPhoneNumber = rs.getString("caller");
	                String receiverPhoneNumber = rs.getString("receiver");
	                LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
	                LocalDateTime endTime = rs.getTimestamp("end_time").toLocalDateTime();

	                Call call = new Call(callerPhoneNumber, receiverPhoneNumber, startTime, endTime, callId);
	                calls.add(call);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return calls;
	    }
}