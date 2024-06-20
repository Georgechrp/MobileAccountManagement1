package mainpackage.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import mainpackage.users.model.User;
import mainpackage.utils.dao.PhoneNumberDao;
import mainpackage.utils.dao.ProgramDao;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDao();
	private ClientDao clientDao = new ClientDao();
	private SellerDao sellerDao = new SellerDao();
	private PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
	private ProgramDao programDao = new ProgramDao();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				//showNewForm(request, response);
				break;
			case "/insert_client":
				insertClient(request, response);
				break;
			case "/insert_admin":
				insertAdmin(request, response);
				break;
			case "/insert_seller":
				insertSeller(request, response);
				break;
			case "/insert_phoneNumber":
				insertPhoneNumber(request, response);
				break;
			case "/delete_user":
				deleteUser(request, response);
				break;
			case "/delete_phoneNumber":
				deletePhoneNumber(request, response);
				break;
			case "/edit":
				//showEditForm(request, response);
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
		int role = Integer.parseInt(request.getParameter("name"));
		Admin newAdmin = new Admin(username, name, surname, password, role);
		adminDao.insertAdmin(newAdmin);
		response.sendRedirect("list");
	}
	
	private void insertClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("role"));
		String AFM = request.getParameter("AFM");
		Double balance = Double.parseDouble(request.getParameter("balance"));
		PhoneNumber PhoneNumber = new PhoneNumber(request.getParameter("phonenumber"), null);
		Client newClient = new Client(username, name, surname, password, role, AFM, balance, PhoneNumber);
		clientDao.insertClient(newClient);
		response.sendRedirect("list");
	}
	
	private void insertSeller(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("role"));
		String company = request.getParameter("company");
		Seller newSeller = new Seller(username, name, surname, password, role, company);
		sellerDao.insertSeller(newSeller);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("username");
		UserDao.deleteUser(username);
		response.sendRedirect("list");
	}
	
	private void insertPhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String phoneNumber = request.getParameter("phonenumber");
		int programId = Integer.parseInt(request.getParameter("programId"));
		Program program = programDao.getProgramById(programId);
		phoneNumberDao.insertNumber(phoneNumber, program);
		response.sendRedirect("list");
	}
	
	private void deletePhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String phoneNumber = request.getParameter("phonenumber");
		phoneNumberDao.deleteNumber(phoneNumber);
		response.sendRedirect("list");
	}
	
	

}
