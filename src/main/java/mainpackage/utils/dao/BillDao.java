package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mainpackage.utils.model.Bill;

public class BillDao {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_BILL_SQL = "INSERT INTO bill" 
	+ "  (bill_id, username, number_of_calls, billing_month) VALUES (?, ?, ?, ?); ";
	
	private static final String SELECT_BILL_BY_USERNAME = "SELECT * FROM bill WHERE username = ?";
	private static final String UPDATE_BILL_PAID_SQL = "UPDATE bill SET paid = 1 WHERE bill_id = ?;";
	private static final String DELETE_BILL_SQL = "DELETE FROM bill WHERE bill_id = ?";
	private static final String SELECT_BILLS_SQL = "SELECT * FROM bill WHERE username = ?";

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
	
	public Bill selectBillByUsername(String username) {
		Bill bill = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILL_BY_USERNAME)) {
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String bill_id = rs.getString("bill_id");
				int billingMonth = rs.getInt("billing_month");
				int numberOfCalls = rs.getInt("number_of_calls");
				bill = new Bill(bill_id, username, billingMonth, numberOfCalls);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bill;
	}

	public void payBill(String billId) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BILL_PAID_SQL)) {
			preparedStatement.setString(1, billId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	public void deleteBill(String billId) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BILL_SQL)) {
			preparedStatement.setString(1, billId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Bill> getCustomerBills(String username) throws SQLException {
		ArrayList<Bill> bills = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILLS_SQL)) {
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Bill bill = new Bill(
						resultSet.getString("bill_id"),
						resultSet.getString("username"),
						resultSet.getInt("billing_month"),
						resultSet.getInt("number_of_calls")
						);
				bills.add(bill);
			}
		}
		return bills;
	}
}
