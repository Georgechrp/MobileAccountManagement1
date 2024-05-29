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

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
				showNewForm(request, response);
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
				showEditForm(request, response);
				break;
			case "/register":
				register(request, response);
				break;
			case "/login":
				login(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;	
			case "/pay_bill":
				pay_bill(request, response);
				break;
			case "/display_account":
				display_account(request, response);
				break;
            case "/display_call_history":
            	display_call_history(request, response);
				break;	
            case "/display_balance":
            	display_balance(request,response);
            	break;
            case "/set_balance":
            	set_balance(request, response);
            	break;
            case "/display_programs":
            	display_programs(request, response);
            	break;
            case "/change_program":	
            	change_program(request, response);
            	break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int typeOfRegistration = Integer.parseInt(request.getParameter("userType"));
		if (typeOfRegistration == 2) {
			insertClient(request, response);
		}
		else if (typeOfRegistration == 3) {
			insertSeller(request,response);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = UserDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = UserDao.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	
	private void insertClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("name"));
		String AFM = request.getParameter("AFM");
		PhoneNumber PhoneNumber = new PhoneNumber(request.getParameter("phonenumber"), null);
		Client newClient = new Client(username, name, surname, password, role, AFM, PhoneNumber);
		clientDao.insertClient(newClient);
		response.sendRedirect("login.jsp");
	}
	
	private void insertSeller(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("name"));
		String company = request.getParameter("company");
		Seller newSeller = new Seller(username, name, surname, password, role, company);
		sellerDao.insertSeller(newSeller);
		response.sendRedirect("login.jsp");
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
