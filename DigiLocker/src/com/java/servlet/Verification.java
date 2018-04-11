package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.rest.webservice.UserService;

public class Verification extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		UserService service = new UserService();
		boolean result = service.verify(request.getParameter("id"));

		if (result) {
			response
					.sendRedirect("home.jsp?msg=Congratulations! You have successfully verified your email id. Login to start using your DigiLocker.");
		} else {
			if (result) {
				response
						.sendRedirect("home.jsp?msg=Invalid Request. Link has been expired.");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
