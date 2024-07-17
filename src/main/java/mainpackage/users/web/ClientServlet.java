package mainpackage.users.web;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

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
import mainpackage.utils.model.Call;
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
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
	        
		 	String action = request.getParameter("action");
		    System.out.println("Action parameter: " + action);
		    try {
		        switch (action) {
		            case "register":
		                System.out.println("Register action called");
		                registerClient(request, response);
		                break;
		            case "Call history":
		                System.out.println("Call history action called");
		                displayCallHistory(request, response);
		                break;
		            // other cases...
		            case "My Bill":
	                    viewAccount(request, response);
	                    break;
		            case "payBill":
	                    payBill(request, response);
	                    break;
		            
		            default:
		                response.getWriter().println("Unknown action: " + action);
		                break;
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace(response.getWriter());
		        throw new ServletException(ex);
		    }
		}
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");
	        try {
	            switch (action) {
	                case "listUser":
	                    listUser(request, response);
	                    break;
	                case "viewAccount":
	                    viewAccount(request, response);
	                    break;
	                // Add more cases as needed
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
		String phone_number = request.getParameter("phone_number");
		
		int role = 2;
		
		Client newClient = new Client(username, name, surname, password, role, AFM, balance, phone_number);
		clientDao.insertClient(newClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
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
	
	
	private void displayCallHistory(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
		 HttpSession session = request.getSession(false);
		    if (session == null || session.getAttribute("username") == null) {
		        response.sendRedirect("login.jsp");
		        return;
		    }
	    String phoneNumber = request.getParameter("phone_number");
	    List<Call> callHistory = callDao.getCallHistory(phoneNumber);
	    request.setAttribute("callHistory", callHistory); // Ensure this line is correct
	    RequestDispatcher dispatcher = request.getRequestDispatcher("CallHistory.jsp");
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
    
    private void listUser(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
	    dispatcher.forward(request, response);
	}
    

}