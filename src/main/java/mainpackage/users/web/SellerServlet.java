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


@WebServlet("/SellerServlet")
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
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/register":
				register(request, response);
				break;
			case "/login":
				UserServlet userServlet = (UserServlet) getServletContext().getAttribute("userServlet");
		        userServlet.login(request, response);
		        break;
			case "/logout":
				UserServlet userServlet = (UserServlet) getServletContext().getAttribute("userServlet");
		        userServlet.logout(request, response);
				break;		
			case "/display_programs":
				display_programs(request, response);
				break;	
			case "/insertClient":
				AdminServlet adminServlet = (AdminServlet) getServletContext().getAttribute("adminServlet");
		        adminServlet.insertClient(request, response);
				break;	
			case "/matchClient":
				matchClient(request, response);
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
		String company = request.getParameter("company");
		int role = 3;
		Seller newSeller = new Seller(username, name, surname, password, company, role);
		sellerDao.insertSeller(newSeller);
		response.sendRedirect("list");
	}
	
	
	private void display_programs(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
		dispatcher.forward(request, response);
	}
	
	private void matchClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

	}

	
    public SellerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
}
