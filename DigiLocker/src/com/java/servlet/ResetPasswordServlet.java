package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Helper.EmailUtility;
import com.java.rest.webservice.UserService;

public class ResetPasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("username").equals("")) {
			response.sendRedirect("ForgotPassword.jsp?msg=Please enter your registered Username.");
		} else {
			String username = request.getParameter("username");
			UserService service = new UserService();
			String result = service.resetPassword(username);

			response.sendRedirect("ForgotPassword.jsp?msg=" + result);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
