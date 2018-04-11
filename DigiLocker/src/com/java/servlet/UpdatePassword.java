package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserAuthentication;

import com.java.DAO.MyUserDAO;
import com.java.Helper.SessionAttribute;
import com.java.model.User;

public class UpdatePassword extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String current_password = request.getParameter("current_password");
		String new_password1 = request.getParameter("new_password1");
		String new_password2 = request.getParameter("new_password2");

		if (current_password.equals("") || new_password1.equals("")
				|| new_password2.equals("")) {
			response
					.sendRedirect("ChangePassword.jsp?msg=All fields are mandatory.");
		} else if (new_password1.equals(new_password2)) {

			SessionAttribute sess = new SessionAttribute();
			String username = sess.getUsernameFormSession(request);

			User user = new User();
			user.setUsername(username);
			user.setPassword(current_password);
			UserAuthentication auth = new UserAuthentication(user);
			boolean result = auth.UserValidate();

			if (result) {

				MyUserDAO dao = new MyUserDAO();
				boolean changed = dao.changePassword(username, new_password1);
				if (changed) {
					response
							.sendRedirect("ChangePassword.jsp?msg=Password Changed successfully.");
				}

			} else {
				response
						.sendRedirect("ChangePassword.jsp?msg=Incorrect Password. Please check and try again..");
			}

		} else {
			response
					.sendRedirect("ChangePassword.jsp?msg=New Password did not match. Please check and retry.");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
