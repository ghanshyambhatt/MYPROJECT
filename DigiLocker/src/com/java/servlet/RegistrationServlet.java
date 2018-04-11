package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.rest.webservice.UserService;

public class RegistrationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();

		if (request.getParameter("username").equals("")
				|| request.getParameter("password").equals("")
				|| request.getParameter("name").equals("")
				|| request.getParameter("email").equals("")
				|| request.getParameter("mobile").equals("")) {
			response.sendRedirect("registration.jsp?msg=All fields are mandatory.");
		} else {
			UserService service = new UserService();
			String result = service.register(request.getParameter("username"),
					request.getParameter("password"), request
							.getParameter("name"), request
							.getParameter("email"), request
							.getParameter("mobile"), request
							.getParameter("country"));

			if (result.equals("success")) {
				response
						.sendRedirect("home.jsp?msg=Registration Successful. Click login to start using user personal DigiLocker.");
			} else if (result.equals("internal_error")) {
				response
						.sendRedirect("registration.jsp?msg=Registration Failed due to internal error. Please try again later.");
			} else if (result.equals("email_error")) {
				response
						.sendRedirect("registration.jsp?msg=Email ID is already registered with an account. Please enter a valid email ID.");
			} else if (result.equals("username_error")) {
				response
						.sendRedirect("registration.jsp?msg=Username has already been taken. Please try another username.");
			}

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
