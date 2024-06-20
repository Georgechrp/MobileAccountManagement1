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

import mainpackage.users.dao.ClientDao;
import mainpackage.users.model.Client;
import mainpackage.utils.dao.BillDao;
import mainpackage.utils.dao.CallDao;
import mainpackage.utils.dao.PhoneNumberDao;
import mainpackage.utils.dao.ProgramDao;
import mainpackage.utils.model.PhoneNumber;


@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDao clientDao = new ClientDao();
	private BillDao billDao = new BillDao();
	private CallDao callDao = new CallDao();
	private PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
	private ProgramDao programDao = new ProgramDao();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/register":
				register(request, response);
				break;
			case "/login":
				//UserServlet userServlet = (UserServlet) getServletContext().getAttribute("userServlet");
		        //userServlet.login();
		        break;
			case "/logout":
				//UserServlet userServlet = (UserServlet) getServletContext().getAttribute("userServlet");
		        //userServlet.logout();
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
	
	
	private void register (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String AFM = request.getParameter("AFM");
		Double balance = Double.parseDouble(request.getParameter("balance"));
		PhoneNumber PhoneNumber = new PhoneNumber(request.getParameter("phonenumber"), null);
		int role = 2;
		Client newClient = new Client(username, name, surname, password, role,  AFM, balance, PhoneNumber);
		clientDao.insertClient(newClient);
		response.sendRedirect("list");
	}
	
	
	private void display_programs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("programs.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void change_program(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("programs.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void display_balance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("balance.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void set_balance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("balance.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void display_call_history(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("call_history.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void display_account(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
		dispatcher.forward(request, response);
	}
	
	
    private void pay_bill(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
	}
    
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
}
