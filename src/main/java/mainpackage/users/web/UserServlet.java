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
import mainpackage.users.dao.SellerDao;
import mainpackage.users.dao.UserDao;
import mainpackage.users.model.Admin;
import mainpackage.users.model.Client;
import mainpackage.users.model.Seller;
import mainpackage.users.model.User;

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
		System.out.println("Attempting login for user: " + username);
		int role = userDao.login(username,password);
		if (role == 2) {
			clientDao.setClient(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ClientMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 3) {
			sellerDao.setSeller(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("SellerMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 1) {
			adminDao.setAdmin(username);
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
	
	
	public void logout (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("LogoutPage.jsp");
		dispatcher.forward(request, response);
	}
}
