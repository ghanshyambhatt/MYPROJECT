package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.DAO.MyFileDAO;
import com.java.Helper.SessionAttribute;
import com.java.model.FileModel;

public class ViewCategory extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// getting username from session.
		SessionAttribute sess = new SessionAttribute();
		String username = sess.getUsernameFormSession(request);

		String category = request.getParameter("category");

		MyFileDAO dao = new MyFileDAO();
		List<FileModel> list;
		if (category.equals("trash")) {

			list=dao.getHiddenfiles(username);
		} else {
			
			list = dao.getFilesbyCategory(username, category);
		}

		request.setAttribute("list", list);
		RequestDispatcher rd = null;
		if (category.equals("picture")) {
			rd = request.getRequestDispatcher("picture.jsp");
		} else if (category.equals("document")) {
			rd = request.getRequestDispatcher("document.jsp");
		} else if (category.equals("other")) {
			rd = request.getRequestDispatcher("other.jsp");
		} else if (category.equals("trash")) {
			rd = request.getRequestDispatcher("trash.jsp");
		}
		rd.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
