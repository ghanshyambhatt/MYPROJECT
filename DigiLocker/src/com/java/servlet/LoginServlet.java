package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.DAO.MySessionDAO;
import com.java.DAO.MyUserDAO;
import com.java.model.User;
import com.java.rest.webservice.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();

		if (request.getParameter("username").equals("")
				|| request.getParameter("password").equals("")) {
			response
					.sendRedirect("login.jsp?msg=Username or Password cannot be empty.");
		} else {
			UserService service = new UserService();
			boolean result = service.login(request.getParameter("username"),
					request.getParameter("password"));
			if (result) {
				// creating new session.
				HttpSession session = request.getSession();
				String session_id = session.getId();

				MySessionDAO sessdao = new MySessionDAO();
				if (sessdao.sessionExists(session_id)) {
					response
							.sendRedirect("home.jsp?errormsg=Invalid Session. A user is already logged in on this browser.");
				} else {

					// storing username in session.
					session.setAttribute("username", request
							.getParameter("username"));

					//getting user details from database.
					MyUserDAO dao = new MyUserDAO();
					User user = dao.getUserbyUsername(request
							.getParameter("username"));
					
					//setting session attribute.
					session.setAttribute("name", user.getName());
				
					// saving in database.
					MySessionDAO sess = new MySessionDAO();
					sess.createActiveSession(session_id, request
							.getParameter("username"));

					response.sendRedirect("welcome.jsp?msg=Weclome to DigiLocker "+user.getName());
				}
			} else {
				response
						.sendRedirect("login.jsp?msg=Username or Password does not exists.");
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
