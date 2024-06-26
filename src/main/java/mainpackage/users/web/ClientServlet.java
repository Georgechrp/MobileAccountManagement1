package mainpackage.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.users.dao.ClientDao;
import mainpackage.users.model.Client;
import mainpackage.utils.dao.BillDao;
import mainpackage.utils.dao.CallDao;
import mainpackage.utils.dao.PhoneNumberDao;
import mainpackage.utils.dao.ProgramDao;
import mainpackage.utils.model.Call;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;


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
		            case "Show Account":
		            	System.out.println("Show Account action called");
		                display_account(request, response);
		                break;
		            case "Show Call History":    
		            	System.out.println("Show Call History action called");
		                display_call_history(request, response);
		                break;
		            case "Pay Bill":    
		            	System.out.println("Pay Bill action called");
		                pay_bill(request, response);
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
		String name = request.getParameter("first_name"); 
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String AFM = request.getParameter("afm");
		Double balance = 0.0;
		PhoneNumber phoneNumber = new PhoneNumber(request.getParameter("phone_number"), null); 
		int role = 2;
		Client newClient = new Client(username, name, surname, password, role, AFM, balance, phoneNumber);
		clientDao.insertClient(newClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	

	private void display_account(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void display_call_history(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		ArrayList <Call> calls = callDao.getCalls();
		request.setAttribute("calls", calls);
		request.getRequestDispatcher("ShowCalls.jsp").forward(request, response);		
	}
	
	
	
	
    private void pay_bill(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException,  ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("pay_bill.jsp");
		dispatcher.forward(request, response);
	}
    
    
    
    private void listUser(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
	    dispatcher.forward(request, response);
	}
    
    
    public ClientServlet() {
        super();
      
    }
}