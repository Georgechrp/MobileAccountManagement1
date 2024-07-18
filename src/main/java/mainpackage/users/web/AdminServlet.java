package mainpackage.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.users.dao.AdminDao;
import mainpackage.users.dao.ClientDao;
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
			case "insert_admin":
				insertAdmin(request, response);
				break;
			case "insert_client":
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
			case "edit_program":
				editProgram(request,response);
				break;
			case "delete_user":
				deleteUser(request, response);
				break;
			case "Edit Programs":
				showPrograms(request, response);
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
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = 1;
		Admin newAdmin = new Admin(username, name, surname, password, role);
		adminDao.insertAdmin(newAdmin);
		response.sendRedirect("AdminPage.jsp");
	}
	
	private void insertClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = 2;
		String AFM = request.getParameter("AFM");
		Double balance = Double.parseDouble(request.getParameter("balance"));
		PhoneNumber PhoneNumber = new PhoneNumber(request.getParameter("phonenumber"), null);
		Client newClient = new Client(username, name, surname, password, role, AFM, balance, PhoneNumber);
		clientDao.insertClient(newClient);
		response.sendRedirect("AdminPage.jsp");
	}
	
	private void insertSeller(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = 3;
		String company = request.getParameter("company");
		Seller newSeller = new Seller(username, name, surname, password, role, company);
		sellerDao.insertSeller(newSeller);
		response.sendRedirect("AdminPage.jsp");
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
		response.sendRedirect("AdminPage.jsp");
	}
	
	private void deletePhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String phoneNumber = request.getParameter("phonenumber");
		phoneNumberDao.deleteNumber(phoneNumber);
		response.sendRedirect("AdminPage.jsp");
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
		response.sendRedirect("AdminPage.jsp");	
	}
	
	private void editProgram(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
	    double baseCharge = Double.parseDouble(request.getParameter("baseCharge"));
	    // Fetch the existing program
	    Program program = programDao.getProgramById(id);
	    // Update the base charge
	    program.setBaseCharge(baseCharge);
	    // Persist the updated program
	    programDao.editProgram(program);
	    response.sendRedirect("EditPrograms.jsp"); 
	}
	
	private void showPrograms(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	    ArrayList<Program> programs = programDao.getPrograms();
	    request.setAttribute("programs", programs);
	    request.getRequestDispatcher("EditPrograms.jsp").forward(request, response);
	}
}