package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import mainpackage.utils.model.Call;
import mainpackage.utils.model.Program;


public class CallDao {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_CALL_SQL = "INSERT INTO call" 
	+ "  (call_id, startTime, endTime, caller_phone_number, receiver_phone_number) VALUES (?, ?, ?, ?, ?); ";
	
	private static final String GET_CALLS_SQL = "SELECT * FROM call; ";
	
	

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

	public void insertCall(Call call) throws SQLException {
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

	
	public ArrayList <Call> getCalls() throws SQLException {
		System.out.println(GET_CALLS_SQL);
		ArrayList<Call> calls = new ArrayList<Call>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_CALLS_SQL);
				ResultSet resultSet = preparedStatement.executeQuery()) {
				while(resultSet.next()) {
					String callerPhoneNumber = resultSet.getString("callerPhoneNumber");
					String receiverPhoneNumber = resultSet.getString("receiverPhoneNumber");
					LocalDateTime startTime = LocalDateTime.now();
					LocalDateTime endTime = LocalDateTime.now();
					String callId = resultSet.getString("callId");
					Call cl1 = new Call(callerPhoneNumber, receiverPhoneNumber, startTime, endTime, callId);
					calls.add(cl1);
				}
				System.out.println(preparedStatement);
			} catch (SQLException e) {
				System.out.println(e.getStackTrace());
			}
		
		
		return calls;
	}
}