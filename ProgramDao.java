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
	private String jdbcPassword = "gr3ty";

	private static final String INSERT_PROGRAM_SQL = "INSERT INTO program" 
	+ "  (program_id, program_name, base_charge, additional_charge, minutes) VALUES (?, ?, ?, ?, ?); ";
	
	private static final String SELECT_PROGRAM_BY_ID = "SELECT * FROM program WHERE program_id = ?;";
	
	private static final String UPDATE_PROGRAM_SQL = "UPDATE program SET base_charge = ? WHERE program_id = ?; ";			
	
	private static final String GET_PROGRAMS_SQL = "SELECT * FROM program; ";
	
	

	public ProgramDao() {
	}

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

	
	public void insertProgram(Program program) {
		System.out.println(INSERT_PROGRAM_SQL);
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
	
	
	public Program getProgramById(int id) {
		Program program = null;
	    try (Connection connection = getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROGRAM_BY_ID)) {
	        preparedStatement.setInt(1, id);
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        if (rs.next()) {
	            String name = rs.getString("program_name");
	            int minutes = rs.getInt("minutes");
	            double baseCharge = rs.getDouble("base_charge");
	            double additionalCharge = rs.getDouble("additional_charge");
	            program = new Program(id, name, minutes, baseCharge, additionalCharge);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return program;
	}
	
	public void editProgram(Program program) {
		System.out.println(UPDATE_PROGRAM_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROGRAM_SQL)) {
			preparedStatement.setDouble(1, program.getBaseCharge());
	        preparedStatement.setInt(2, program.getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	
	public ArrayList<Program> getPrograms() throws SQLException {
		System.out.println(GET_PROGRAMS_SQL);
		ArrayList<Program> programs = new ArrayList<Program>();
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


}
