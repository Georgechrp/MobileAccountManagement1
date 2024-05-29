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
 * Servlet implementation class ClientServlet
 */
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDao();
	private ClientDao clientDao = new ClientDao();
	private SellerDao sellerDao = new SellerDao();
	private PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
	private ProgramDao programDao = new ProgramDao();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		
		try {
			switch (action) {
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
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
			
	}
	
	
	private void display_programs(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void change_program(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void display_balance(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void set_balance(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void display_call_history(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void display_account(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void register (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			String username = request.getParameter("name");
			String name = request.getParameter("name");
			String surname = request.getParameter("name");
			String password = request.getParameter("name");
			int role = Integer.parseInt(request.getParameter("name"));
			String AFM = request.getParameter("AFM");
			PhoneNumber PhoneNumber = new PhoneNumber(request.getParameter("phonenumber"), null);
			Client newClient = new Client(username, name, surname, password, role, AFM, PhoneNumber);
			clientDao.insertClient(newClient);
			response.sendRedirect("list");
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String username = request.getParameter("name");
		String password = request.getParameter("name");
		clientDao.equals(password);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	
    private void pay_bill(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	/**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
}
