package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainpackage.utils.model.Bill;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;

public class BillDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_BILL_SQL = "INSERT INTO bills" 
	+ "  (bill_id, username, number_of_calls, billing_month) VALUES (?, ?, ?, ?); ";
	
	

	public BillDao() {
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
	
	public void insertBill(Bill bill) throws SQLException {
		System.out.println(INSERT_BILL_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BILL_SQL)) {
			preparedStatement.setString(1, bill.getBill_id());
			preparedStatement.setString(2, bill.getUsernameOfClient());
			preparedStatement.setInt(3, bill.getNumberOfCalls());
			preparedStatement.setInt(4, bill.getBillingMonth());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
}

