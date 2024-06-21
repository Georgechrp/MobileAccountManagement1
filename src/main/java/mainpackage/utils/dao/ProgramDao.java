package mainpackage.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mainpackage.utils.model.Program;


public class ProgramDao {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/mobilemanagementdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_PROGRAM_SQL = "INSERT INTO programs" 
	+ "  (program_id, program_name, base_charge, additional_charge, minutes) VALUES (?, ?, ?, ?, ?); ";
	
	private static final String GET_PROGRAMS_SQL = "SELECT * FROM programs; ";
	
	

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

	
	public void insertProgram(Program program) {
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
	
	public ArrayList<Program> getPrograms() throws SQLException {
		System.out.println(GET_PROGRAMS_SQL);
		ArrayList<Program> programs = new ArrayList<Program>();
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_PROGRAMS_SQL);
			ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int minutes = resultSet.getInt("minutes");
				Double basecharge = resultSet.getDouble("basecharge");
				Double additionalcharge = resultSet.getDouble("additionalcharge");
				Program p1 = new Program(id,name,minutes,basecharge,additionalcharge);
				programs.add(p1);
			}
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return programs;
	}

	public Program getProgramById(int programId) {
		// TODO Auto-generated method stub
		return null;
	}
}