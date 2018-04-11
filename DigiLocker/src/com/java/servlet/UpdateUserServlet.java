package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Helper.SessionAttribute;
import com.java.rest.webservice.UserService;

public class UpdateUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("name").equals("")
				|| request.getParameter("email").equals("")
				|| request.getParameter("mobile").equals("")
				|| request.getParameter("country").equals("")) {
			response
					.sendRedirect("EditProfile.jsp?msg=All fields are mandatory.");
		} else {

			SessionAttribute sess = new SessionAttribute();
			String username = sess.getUsernameFormSession(request);

			UserService service = new UserService();
			boolean result = service.updateUser(username, request
					.getParameter("name"), request.getParameter("email"),
					request.getParameter("mobile"), request
							.getParameter("country"));

			if (result) {
				response
						.sendRedirect("Message.jsp?msg=Profile has been Updated.");
			}else{
				
			}

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
