package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mainpackage.users.model.Client;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;

public class ClientDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mobileaccountmanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USER_SQL = "INSERT INTO user (username, first_name, surname, password, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_CLIENT_SQL = "INSERT INTO client (username, afm, balance, phone_number) VALUES (?, ?, ?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_CLIENT_SQL = "SELECT * FROM client WHERE username = ?;";
	private static final String PROGRAM_SQL = "SELECT id, name, minutes, baseCharge, additionalCharge FROM programs WHERE id = ?";

	

	public ClientDao() {
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

	public void insertClient(Client client) throws SQLException {
		System.out.println(INSERT_CLIENT_SQL);
		System.out.println(INSERT_USER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
	            PreparedStatement clientStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
			userStatement.setString(1, client.getUsername());
            userStatement.setString(2, client.getPassword());
            userStatement.setString(3, client.getName());
            userStatement.setString(4, client.getSurname());
            userStatement.setInt(5,client.getRole());
            userStatement.executeUpdate();
            
            // Set parameters for student table
            clientStatement.setString(1, client.getUsername());
            clientStatement.setString(2, client.getAFM());
            clientStatement.setString(3, client.getPhoneNumber().getNumber());
            clientStatement.setDouble(2, client.getBalance());
            clientStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Client setClient(String username) {
		try (Connection connection = getConnection();
		         PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement clientStatement = connection.prepareStatement(LOGIN_CLIENT_SQL)) {
		        
		        userStatement.setString(1, username);
		        System.out.println(userStatement);
		        
		        try (ResultSet rsu = userStatement.executeQuery()) {
		            if (rsu.next()) {
		                clientStatement.setString(1, username);
		                System.out.println(clientStatement);
		                
		                try (ResultSet rss = clientStatement.executeQuery()) {
		                    if (rss.next()) {
		                        String uname = rsu.getString("username");
		                        String password = rsu.getString("password");
		                        String name = rsu.getString("first_name");
		                        String surname = rsu.getString("surname");
		                        String afm = rsu.getString("AFM");
		                        int role = rsu.getInt("role");
		                        String phone_number = rss.getString("phone_number");
		                        Double balance = rss.getDouble("balance");
		                
		                        int programId = rss.getInt("program_id");
		                        Program program = null;
		                        try (PreparedStatement programStatement = connection.prepareStatement(PROGRAM_SQL)) {
		                            programStatement.setInt(1, programId);
		                            System.out.println(programStatement);

		                            try (ResultSet rsp = programStatement.executeQuery()) {
		                                if (rsp.next()) {
		                                    int id = rsp.getInt("id");
		                                    String name1 = rsp.getString("name");
		                                    int minutes = rsp.getInt("minutes");
		                                    double baseCharge = rsp.getDouble("baseCharge");
		                                    double additionalCharge = rsp.getDouble("additionalCharge");

		                                    program = new Program(id, name1, minutes, baseCharge, additionalCharge);
		                                } else {
		                                    System.out.println("No program found with the provided ID.");
		                                    return null;
		                                }
		                            }
		                        }
		                        
		                        PhoneNumber phoneNumber = new PhoneNumber(phone_number, program);
		                        
								return new Client(uname, name, surname, password, role, afm, balance, phoneNumber);
		                    } else {
		                        // Handle case where no results are found in student query
		                        System.out.println("No student found with the provided username.");
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
