package mainpackage.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mainpackage.users.model.Client;
import mainpackage.users.model.Seller;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;

public class SellerDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mobileaccountmanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USER_SQL = "INSERT INTO user (username, first_name, surname, password, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_SELLER_SQL = "INSERT INTO seller (username, company) VALUES (?, ?, ?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_SELLER_SQL = "SELECT * FROM seller WHERE username = ?;";
	private static final String GET_CLIENTS_SQL = "SELECT user.username, user.password, user.first_name, user.surname, user.role, client.afm,\r\n"
			+ "client.balance, client.phone_number, phone_number.programid, program.name, program.minutes,\r\n"
			+ "program.basecharge, program.additionalcharge\r\n"
			+ "FROM user\r\n"
			+ "INNER JOIN client ON user.username=client.username\r\n"
			+ "INNER JOIN phone_number ON client.phone_number=phone_number.number\r\n"
			+ "INNER JOIN program ON phone_number.programid=program.id;";

	

	public SellerDao() {
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

	public void insertSeller(Seller seller) throws SQLException {
		System.out.println(INSERT_SELLER_SQL);
		System.out.println(INSERT_USER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
	            PreparedStatement sellerStatement = connection.prepareStatement(INSERT_SELLER_SQL)) {
			userStatement.setString(1, seller.getUsername());
            userStatement.setString(2, seller.getPassword());
            userStatement.setString(3, seller.getName());
            userStatement.setString(4, seller.getSurname());
            userStatement.setInt(5, seller.getRole());
            userStatement.executeUpdate();
            
            
            sellerStatement.setString(1, seller.getUsername());
            sellerStatement.setString(2, seller.getCompany());
            sellerStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Seller setSeller(String username) {
		try (Connection connection = getConnection();
		         PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement sellerStatement = connection.prepareStatement(LOGIN_SELLER_SQL)) {
		        
		        userStatement.setString(1, username);
		        System.out.println(userStatement);
		        
		        try (ResultSet rsu = userStatement.executeQuery()) {
		            if (rsu.next()) {
		                sellerStatement.setString(1, username);
		                System.out.println(sellerStatement);
		                
		                try (ResultSet rss = sellerStatement.executeQuery()) {
		                    if (rss.next()) {
		                        String uname = rsu.getString("username");
		                        String password = rsu.getString("password");
		                        String name = rsu.getString("first_name");
		                        String surname = rsu.getString("surname");
		                        String company = rsu.getString("company");
		                        int role = rsu.getInt("role");		           
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
