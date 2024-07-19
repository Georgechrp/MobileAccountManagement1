package mainpackage.users.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class UserDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String JDBC_USER = "root";
	private String JDBC_PASSWORD = "root";
    
    private static final String LOGIN_VALIDATION_PASSWORD_SQL = "SELECT role FROM user WHERE username = ? AND password = ?;";
    private static final String LOGIN_VALIDATION_USERNAME_SQL = "SELECT role FROM user WHERE username = ? ;";
    private static final String ROLE_USERNAME_SQL = "SELECT role FROM user WHERE username = ?;";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM client WHERE username = ?;";
    private static final String DELETE_SELLER_SQL = "DELETE FROM seller WHERE username = ?;";
    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE username = ?;";

    protected Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class Not Found Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    

    public int login(String username, String password) throws ServletException, IOException {

    	try (Connection connection = getConnection();
             PreparedStatement passwordPreparedStatement = connection.prepareStatement(LOGIN_VALIDATION_PASSWORD_SQL);
        		PreparedStatement usernamePreparedStatement = connection.prepareStatement(LOGIN_VALIDATION_USERNAME_SQL)) {
             
        	int role = -1;
        	
        	passwordPreparedStatement.setString(1, username);
        	passwordPreparedStatement.setString(2, password);
            
        	usernamePreparedStatement.setString(1, username);
        	ResultSet resultSet2 = usernamePreparedStatement.executeQuery();
        	if (resultSet2.next()) {
                role= 0;
            }
        	
            ResultSet resultSet = passwordPreparedStatement.executeQuery();

            if (resultSet.next()) {
               role = resultSet.getInt("role");
            }
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        }
        
    }

	public void deleteUser(String username) {
		try (Connection connection = getConnection();
				PreparedStatement roleStatement = connection.prepareStatement(ROLE_USERNAME_SQL)){
			ResultSet rs = roleStatement.executeQuery();
			int role = 0;
			if(rs.next()) {
				role = rs.getInt("role");
			}
			if (role == 2) {
				PreparedStatement clientDelete = connection.prepareStatement(DELETE_CLIENT_SQL);
				clientDelete.setString(1,username);
				clientDelete.executeQuery();
			} else if (role == 3) {
				PreparedStatement sellerDelete = connection.prepareStatement(DELETE_SELLER_SQL);
				sellerDelete.setString(1,username);
				sellerDelete.executeQuery();
			}
			PreparedStatement userDelete = connection.prepareStatement(DELETE_USER_SQL);
			userDelete.setString(1,username);
			userDelete.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
