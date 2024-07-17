package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mainpackage.users.model.Seller;
import mainpackage.utils.model.Bill;

public class SellerDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USER_SQL = "INSERT INTO user (username, first_name, surname, password, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_SELLER_SQL = "INSERT INTO seller (username, company) VALUES (?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_SELLER_SQL = "SELECT company FROM seller WHERE username = ?;";

	

	public SellerDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
		System.out.println(INSERT_USER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
	            PreparedStatement sellerStatement = connection.prepareStatement(INSERT_SELLER_SQL)) {
			userStatement.setString(1, seller.getUsername());
            userStatement.setString(4, seller.getPassword());
            userStatement.setString(2, seller.getName());
            userStatement.setString(3, seller.getSurname());
            userStatement.setInt(5, seller.getRole());
            userStatement.executeUpdate();
            
            
            sellerStatement.setString(1, seller.getUsername());
            sellerStatement.setString(2, seller.getCompany());
            sellerStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Seller setSeller(String username) throws SQLException {
		try (Connection connection = getConnection();
		         PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement sellerStatement = connection.prepareStatement(LOGIN_SELLER_SQL)) {
		        
		        userStatement.setString(1, username);
		        System.out.println(userStatement);
		        
		        try (ResultSet rsu = userStatement.executeQuery()) {
		            if (rsu.next()) {
		                sellerStatement.setString(1, username);
		                System.out.println(sellerStatement);
                        String uname = rsu.getString("username");
                        String password = rsu.getString("password");
                        String name = rsu.getString("first_name");
                        String surname = rsu.getString("surname");
                        int role = rsu.getInt("role");		        
		                try (ResultSet rss = sellerStatement.executeQuery()) {
		                    if (rss.next()) {
		                        String company = rss.getString(1);   
								return new Seller(uname, name, surname, password,  role, company);
		                    } else {
		                        // Handle case where no results are found in student query
		                        System.out.println("No seller found with the provided username.");
		                        return null;
		                    }
		                }
		            } else {
		                // Handle case where no results are found in user query
		                System.out.println("No user found with the provided username.");
		                return null;
		            }
		        }
		    }
	}
	
	 public List<Bill> getCustomerBills(String username) throws SQLException {
	        List<Bill> bills = new ArrayList<>();
	        String SELECT_BILLS_SQL = "SELECT * FROM bills WHERE username = ?";
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