package mainpackage.users.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mainpackage.users.dao.ClientDao;
import mainpackage.users.model.Client;
import mainpackage.utils.dao.BillDao;
import mainpackage.utils.dao.CallDao;
import mainpackage.utils.dao.PhoneNumberDao;
import mainpackage.utils.dao.ProgramDao;
import mainpackage.utils.model.Bill;
import mainpackage.utils.model.PhoneNumber;

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDao clientDao;
	private BillDao billDao;
	private CallDao callDao;
	private PhoneNumberDao phoneNumberDao;
	private ProgramDao programDao;
	
	public void init() {
        clientDao = new ClientDao();
        billDao = new BillDao();
        callDao = new CallDao();
        phoneNumberDao = new PhoneNumberDao();
        programDao = new ProgramDao();
    }
    
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 doGet(request, response);
	 }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");
	        try {
	            switch (action) {
	                case "register":
	                	System.out.println("Register action called");
		                registerClient(request, response);
		                break;
	                case "My Bill":
	                    viewAccount(request, response);
	                    break;
		            case "payBill":
	                    payBill(request, response);
	                    break;
	                default:
	                    response.sendRedirect("index.jsp");
	                    break;
	            }
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        }
	    }
	
	private void registerClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		String name = request.getParameter("first_name"); // Correct parameter name
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String AFM = request.getParameter("afm");
		Double balance = 0.0; // Initialize balance to 0.0 as it's not provided in the form
		PhoneNumber phoneNumber = new PhoneNumber(request.getParameter("phone_number"), null); // Correct parameter name
		int role = 2;
		Client newClient = new Client(username, name, surname, password, role, AFM, balance, phoneNumber);
		clientDao.insertClient(newClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void viewAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		String username = (String) session.getAttribute("username");
		Bill bill = billDao.selectBillByUsername(username); // Fetch the bill based on the username
		request.setAttribute("bill", bill);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Account.jsp"); // Forward to account.jsp
		dispatcher.forward(request, response);
	}

	private void payBill(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String billId = request.getParameter("bill_id");
		billDao.payBill(billId);
		billDao.deleteBill(billId); // Remove the bill after payment
		response.sendRedirect("Account.jsp");
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
}
