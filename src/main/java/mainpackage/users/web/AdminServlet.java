package mainpackage.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.users.dao.AdminDao;
import mainpackage.users.dao.ClientDao;
import mainpackage.users.dao.Ipassword;
import mainpackage.users.dao.SellerDao;
import mainpackage.users.dao.UserDao;
import mainpackage.users.model.Admin;
import mainpackage.users.model.Client;
import mainpackage.users.model.Seller;
import mainpackage.utils.dao.PhoneNumberDao;
import mainpackage.utils.dao.ProgramDao;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDao();
	private ClientDao clientDao = new ClientDao();
	private SellerDao sellerDao = new SellerDao();
	private PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
	private ProgramDao programDao = new ProgramDao();
	private UserDao userDao = new UserDao();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "registerAdmin":
				insertAdmin(request, response);
				break;
			case "insertAdminClient":
				insertClient(request, response);
				break;
			case "insert_seller":
				insertSeller(request, response);
				break;
			case "insert_phoneNumber":
				insertPhoneNumber(request, response);
				break;
			case "delete_phoneNumber":
				deletePhoneNumber(request, response);
				break;
			case "insert_program":
				insertProgram(request, response);	
				break;
			case "delete_user":
				deleteUser(request, response);
				break;
			case "Edit Programs":
				showPrograms(request, response);
				break;
			case "edit_programs":
				editProgram(request, response);
				break;
			default:
				//listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void insertAdmin(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("username");
		String name = request.getParameter("first_name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		int role = 1;
		String hashedPassword = Ipassword.hashPassword(password);
		Admin newAdmin = new Admin(username, name, surname, hashedPassword, role);
		adminDao.insertAdmin(newAdmin);
		response.sendRedirect("AdminMain.jsp");
	}
	
	private void insertClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		String name = request.getParameter("first_name"); // Correct parameter name
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String AFM = request.getParameter("afm");
		Double balance = 0.0; // Initialize balance to 0.0 as it's not provided in the form
		PhoneNumber phoneNumber = new PhoneNumber(request.getParameter("phone_number"), null); // Correct parameter name
		int role = 2;
		String hashedPassword = Ipassword.hashPassword(password);
		Client newClient = new Client(username, name, surname, hashedPassword, role, AFM, balance, phoneNumber);
		clientDao.insertClient(newClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void insertSeller(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = 3;
		String company = request.getParameter("company");
		String hashedPassword = Ipassword.hashPassword(password);
		Seller newSeller = new Seller(username, name, surname, hashedPassword, role, company);
		sellerDao.insertSeller(newSeller);
		response.sendRedirect("AdminMain.jsp");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("username");
		userDao.deleteUser(username);
		response.sendRedirect("ShowUsers.jsp");
	}
	
	private void insertPhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String phoneNumber = request.getParameter("phonenumber");
		int programId = Integer.parseInt(request.getParameter("programId"));
		Program program = programDao.getProgramById(programId);
		phoneNumberDao.insertNumber(phoneNumber, program);
		response.sendRedirect("AdminMain.jsp");
	}
	
	private void deletePhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String phoneNumber = request.getParameter("phonenumber");
		phoneNumberDao.deleteNumber(phoneNumber);
		response.sendRedirect("AdminMain.jsp");
	}
	
	private void insertProgram(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException  {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int minutes = Integer.parseInt(request.getParameter("minutes"));
		double baseCharge = Double.parseDouble(request.getParameter("baseCharge"));
		double additionalCharge = Double.parseDouble(request.getParameter("additionalCharge"));
		Program newProgram = new Program(id, name, minutes, baseCharge, additionalCharge);
		programDao.insertProgram(newProgram);
		response.sendRedirect("AdminMain.jsp");	
	}
	
	private void editProgram(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		 	int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        int minutes = Integer.parseInt(request.getParameter("minutes"));
	        double baseCharge = Double.parseDouble(request.getParameter("baseCharge"));
	        double additionalCharge = Double.parseDouble(request.getParameter("additionalCharge"));

	        Program program = new Program(id, name, minutes, baseCharge, additionalCharge);
	        programDao.editProgram(program);
	        showPrograms(request, response);
	     
	}
	
	private void showPrograms(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	    ArrayList<Program> programs = programDao.getPrograms();
	    request.setAttribute("programs", programs);
	    request.getRequestDispatcher("EditPrograms.jsp").forward(request, response);
	}
}