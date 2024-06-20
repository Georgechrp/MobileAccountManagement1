package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mainpackage.users.model.Admin;
import mainpackage.users.model.Seller;

public class AdminDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mobileaccountmanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USER_SQL = "INSERT INTO user (username, first_name, surname, password, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_ADMIN_SQL = "INSERT INTO client (username, afm, balance, phone_number) VALUES (?, ?, ?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_ADMIN_SQL = "SELECT * FROM client WHERE username = ?;";

	
	
	public AdminDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertAdmin(Admin admin) throws SQLException {
		System.out.println(INSERT_ADMIN_SQL);
		System.out.println(INSERT_USER_SQL);
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
	            PreparedStatement adminStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
			adminStatement.setString(1, admin.getUsername());
			adminStatement.setString(2, admin.getPassword());
			adminStatement.setString(3, admin.getName());
			adminStatement.setString(4, admin.getSurname());
			adminStatement.setInt(5, admin.getRole());
			adminStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Admin setAdmin (String username) {
		try (Connection connection = getConnection();
		         PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement adminStatement = connection.prepareStatement(LOGIN_ADMIN_SQL)) {
		        
		        userStatement.setString(1, username);
		        System.out.println(userStatement);
		        
		        try (ResultSet rsu = userStatement.executeQuery()) {
		            if (rsu.next()) {
		               adminStatement.setString(1, username);
		                System.out.println(adminStatement);
		                
		                try (ResultSet rss = adminStatement.executeQuery()) {
		                    if (rss.next()) {
		                        String uname = rsu.getString("username");
		                        String password = rsu.getString("password");
		                        String name = rsu.getString("first_name");
		                        String surname = rsu.getString("surname");
		                        int role = rsu.getInt("role");		           
								return new Admin(uname, name, surname, password,  role);
		                    } else {
		                        // Handle case where no results are found in student query
		                        System.out.println("No admin found with the provided username.");
		                        return null;
		                    }
		                }
		            } else {
		                // Handle case where no results are found in user query
		                System.out.println("No user found with the provided username.");
		                return null;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    }
	}
}