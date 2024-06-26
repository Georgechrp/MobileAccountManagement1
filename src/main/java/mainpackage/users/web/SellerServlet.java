package mainpackage.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.users.dao.ClientDao;
import mainpackage.users.dao.SellerDao;
import mainpackage.users.model.Client;
import mainpackage.users.model.Seller;
import mainpackage.utils.dao.PhoneNumberDao;
import mainpackage.utils.dao.ProgramDao;
import mainpackage.utils.model.PhoneNumber;
import mainpackage.utils.model.Program;

public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerDao sellerDao = new SellerDao();
	private ClientDao clientDao = new ClientDao();
	private PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
	private ProgramDao programDao = new ProgramDao();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "register":
				register(request, response);
				break;
			case "login":
				UserServlet userServlet = (UserServlet) getServletContext().getAttribute("userServlet");
		        userServlet.login(request, response);
		        break;
			case "logout":
				UserServlet userServlet1 = (UserServlet) getServletContext().getAttribute("userServlet");
		        userServlet1.logout(request, response);
				break;
			case "insertNewClient":
				insertNewClient(request, response);
				break;
			case "Show Programs":
				display_programs(request, response);
				break;
			case "Match Client":
				matchClient(request, response);
				break;
			case "Match":
				changeProgram(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void changeProgram(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clientDao.changeProgram(request.getParameter("number"),Integer.parseInt(request.getParameter("program")));
		RequestDispatcher dispatcher = request.getRequestDispatcher("MatchClient.jsp");
		dispatcher.forward(request, response);
	}


	private void insertNewClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("SellerMain.jsp");
	    dispatcher.forward(request, response);
	}

	private void matchClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		ArrayList <Client> clients = clientDao.getClients();
		ArrayList <Program> programs = programDao.getPrograms();
		request.setAttribute("clients", clients);
        request.setAttribute("programs", programs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("MatchClient.jsp");
		dispatcher.forward(request, response);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String company = request.getParameter("company");
		int role = 3;
		Seller newSeller = new Seller(username, name, surname, password, role, company);
		sellerDao.insertSeller(newSeller);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void display_programs(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	    ArrayList<Program> programs = programDao.getPrograms();
	    request.setAttribute("programs", programs);
	    request.getRequestDispatcher("/ShowPrograms.jsp").forward(request, response);
	}

	
    public SellerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
}
