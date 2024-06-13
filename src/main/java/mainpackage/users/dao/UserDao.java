package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mainpackage.users.model.User;

public class UserDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";
    
    private static final String LOGIN_VALIDATION_PASSWORD_SQL = "SELECT role FROM user WHERE username = ? AND password = ?;";
    
    private static final String LOGIN_VALIDATION_USERNAME_SQL = "SELECT role FROM user WHERE username = ? ;";

    protected Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class Not Found Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    

    public int Login(String username, String password) throws ServletException, IOException {

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
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
               role = resultSet.getInt(role);
               
            }
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        }
        
    }
    
}

