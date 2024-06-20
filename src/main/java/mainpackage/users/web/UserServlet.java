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
import javax.servlet.http.HttpSession;

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



@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	ClientDao clientDao = new ClientDao();
	AdminDao adminDao = new AdminDao();
	SellerDao sellerDao = new SellerDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/login":
				login(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;	
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	private void login (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
        System.out.println("Attempting login for user: " + username);
    	int role = userDao.Login(username,password);
    	if (role == 2) {
    		Client currentClient = clientDao.setClient(username);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("Client Main.jsp");
		    dispatcher.forward(request, response);
		} else if (role == 3) {
			Seller currentSeller = sellerDao.setSeller(username);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("Seller Main.jsp");
		    dispatcher.forward(request, response);
		} else if (role == 1) {
			Admin currentAdmin = adminDao.setAdmin(username);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
		    dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
            session.setAttribute("error", "Invalid username or password");
            response.sendRedirect("LoginPage.jsp");
		}
	}
	
	private void logout (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("LogoutPage.jsp");
		dispatcher.forward(request, response);
	}
}
