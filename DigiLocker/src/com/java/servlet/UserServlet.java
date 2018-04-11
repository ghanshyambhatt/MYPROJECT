package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.DAO.MyUserDAO;
import com.java.Helper.SessionAttribute;
import com.java.model.User;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getParameter("path");
		if (path.equals("user")) {

			SessionAttribute sess = new SessionAttribute();
			String username = sess.getUsernameFormSession(request);

			MyUserDAO dao = new MyUserDAO();
			User user = dao.getUserbyUsername(username);
			
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			RequestDispatcher rd = request
					.getRequestDispatcher("UserDetails.jsp");
			rd.forward(request, response);

		} else if (path.equals("editprofile")) {
			
			SessionAttribute sess = new SessionAttribute();
			String username = sess.getUsernameFormSession(request);

			MyUserDAO dao = new MyUserDAO();
			User user = dao.getUserbyUsername(username);
			
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			RequestDispatcher rd = request
					.getRequestDispatcher("EditProfile.jsp");
			rd.forward(request, response);

		}else if (path.equals("settings")) {
			response.sendRedirect("UserSettings.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
