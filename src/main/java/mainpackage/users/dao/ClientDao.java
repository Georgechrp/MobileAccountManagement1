package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mainpackage.users.model.Client;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;

public class ClientDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_USER_SQL = "INSERT INTO user (username, first_name, surname, password, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_PHONENUMBER_SQL = "INSERT INTO phone_number (programid, number) VALUES (?, ?)";
	private static final String INSERT_CLIENT_SQL = "INSERT INTO client (username, afm, balance, phone_number) VALUES (?, ?, ?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_CLIENT_SQL = "SELECT * FROM client WHERE username = ?;";
	private static final String PROGRAM_SQL = "SELECT id, name, minutes, baseCharge, additionalCharge FROM programs WHERE id = ?";
	private static final String GET_CLIENTS_SQL = "SELECT user.username, user.password, user.first_name, user.surname, user.role, client.afm,\r\n"
			+ "client.balance, client.phone_number, phone_number.programid, program.name, program.minutes,\r\n"
			+ "program.basecharge, program.additionalcharge\r\n"
			+ "FROM user\r\n"
			+ "INNER JOIN client ON user.username=client.username\r\n"
			+ "INNER JOIN phone_number ON client.phone_number=phone_number.number\r\n"
			+ "INNER JOIN program ON phone_number.programid=program.id;";
	

	public ClientDao() {
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

	public void insertClient(Client client) throws SQLException {
		System.out.println(INSERT_CLIENT_SQL);
		System.out.println(INSERT_USER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
				PreparedStatement numberStatement = connection.prepareStatement(INSERT_PHONENUMBER_SQL);
	            PreparedStatement clientStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
			userStatement.setString(1, client.getUsername());
            userStatement.setString(4, client.getPassword());
            userStatement.setString(2, client.getName());
            userStatement.setString(3, client.getSurname());
            userStatement.setInt(5,client.getRole());
            userStatement.executeUpdate();
            
            numberStatement.setString(2, client.getPhoneNumber().getNumber());
            numberStatement.setInt(1, 0);
            numberStatement.executeUpdate();
            
            // Set parameters for student table
            clientStatement.setString(1, client.getUsername());
            clientStatement.setString(2, client.getAFM());
            clientStatement.setString(4, client.getPhoneNumber().getNumber());
            clientStatement.setDouble(3, 0.0);
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
		                        String afm = rsu.getString("afm");
		                        int role = rsu.getInt("role");
		                        String phone_number = rss.getString("phone_number");
		                        Double balance = rss.getDouble("balance");
		                
		                        int programId = rss.getInt("programid");
		                        Program program = null;
		                        try (PreparedStatement programStatement = connection.prepareStatement(PROGRAM_SQL)) {
		                            programStatement.setInt(1, programId);
		                            System.out.println(programStatement);

		                            try (ResultSet rsp = programStatement.executeQuery()) {
		                                if (rsp.next()) {
		                                    int id = rsp.getInt("id");
		                                    String name1 = rsp.getString("name");
		                                    int minutes = rsp.getInt("minutes");
		                                    double baseCharge = rsp.getDouble("basecharge");
		                                    double additionalCharge = rsp.getDouble("additionalcharge");

		                                    program = new Program(id, name1, minutes, baseCharge, additionalCharge);
		                                } else {
		                                    System.out.println("No program found with the provided ID.");
		                                    program = null;
		                                }
		                            }
		                        }
		                        
		                        PhoneNumber phoneNumber = new PhoneNumber(phone_number, program);
		                        
								return new Client(uname, name, surname, password, role, afm, balance, phoneNumber);
		                    } else {
		                        // Handle case where no results are found in student query
		                        System.out.println("No client found with the provided username.");
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
	
	public ArrayList <Client> getClients() throws SQLException {
		System.out.println(GET_CLIENTS_SQL);
		ArrayList<Client> clients = new ArrayList<Client>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENTS_SQL)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				//CLIENT
				String username = resultSet.getString("user.username");
				String name = resultSet.getString("user.first_name");
				String surname = resultSet.getString("user.surname");
				String password = resultSet.getString("user.password");
				int role = 2;
				String AFM = resultSet.getString("client.AFM");
				Double balance = resultSet.getDouble("client.balance");
				String phone_number = resultSet.getString("client.phone_number");

				//PROGRAM
				int id = resultSet.getInt("phone_number.programid");
				String program_name = resultSet.getString("program.name");;
				int minutes = resultSet.getInt("program.minutes");
				Double baseCharge = resultSet.getDouble("program.basecharge");
				Double additionalCharge = resultSet.getDouble("program.additionalcharge");
				Program program = new Program (id, program_name,  minutes, baseCharge, additionalCharge);
				PhoneNumber phoneNumber = new PhoneNumber (phone_number, program);

				Client c1 = new Client(username, name, surname, password, role, AFM,  balance, phoneNumber);
				clients.add(c1);
			}
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return clients;
	}
}