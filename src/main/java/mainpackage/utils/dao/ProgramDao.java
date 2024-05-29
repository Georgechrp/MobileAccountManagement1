package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainpackage.utils.model.Program;


public class ProgramDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_PROGRAM_SQL = "INSERT INTO programs" 
	+ "  (program_id, program_name, base_charge, additional_charge, minutes) VALUES (?, ?, ?, ?, ?); ";
	
	

	public ProgramDao() {
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

	public void insertUser(Program program) throws SQLException {
		System.out.println(INSERT_PROGRAM_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROGRAM_SQL)) {
			preparedStatement.setInt(1, program.getId());
			preparedStatement.setString(2, program.getName());
			preparedStatement.setDouble(3, program.getBaseCharge());
			preparedStatement.setDouble(4, program.getAdditionalCharge());
			preparedStatement.setInt(5, program.getMinutes());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public Program getProgramById(int programId) {
		// TODO Auto-generated method stub
		return null;
	}
}