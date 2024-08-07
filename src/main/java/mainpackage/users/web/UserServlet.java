package mainpackage.users.web;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mainpackage.users.dao.AdminDao;
import mainpackage.users.dao.ClientDao;
import mainpackage.users.dao.Ipassword;
import mainpackage.users.dao.SellerDao;
import mainpackage.users.dao.UserDao;
import mainpackage.users.model.Client;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	private ClientDao clientDao = new ClientDao();
	private AdminDao adminDao = new AdminDao();
	private SellerDao sellerDao = new SellerDao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "login":
				login(request, response);
				break;
			case "Logout":
				logout(request, response);
				break;	
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	public void login (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = Ipassword.hashPassword(password);
		System.out.println("Attempting login for user: " + username);
		int role = userDao.login(username,hashedPassword);
		if (role == 2) {
			//clientDao.setClient(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ClientMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 3) {
			sellerDao.setSeller(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("SellerMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 1) {
			adminDao.setAdmin(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == -1){
			HttpSession session = request.getSession();
			session.setAttribute("error", "Invalid username");
			response.sendRedirect("LoginPage.jsp");
		} else if (role == 0){
			HttpSession session = request.getSession();
			session.setAttribute("error", "Invalid password");
			response.sendRedirect("LoginPage.jsp");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("error", "Runtime Error: refer to console log");
			response.sendRedirect("LoginPage.jsp");
		}
	}
	 private void clearBrowserCache(HttpServletResponse response) {
	        // Προσθήκη κεφαλίδων HTTP για να διασφαλιστεί ο καθαρισμός της cache του προγράμματος περιήγησης
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	        response.setDateHeader("Expires", 0); // Proxies
	    }
	
	
	public void logout (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the session
		}
		
		clearBrowserCache(response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
}