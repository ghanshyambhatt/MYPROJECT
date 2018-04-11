package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.DAO.MyFileDAO;
import com.java.Helper.SessionAttribute;
import com.java.model.FileModel;

public class ShowFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// getting username from session.
		SessionAttribute sess = new SessionAttribute();
		String username = sess.getUsernameFormSession(request);

		FileModel f1 = new FileModel();
		f1.setFileName(request.getParameter("FileName"));
		f1
				.setCategory_ID((Integer.parseInt(request
						.getParameter("CategoryID"))));
		f1.setUserID(username);

		MyFileDAO d = new MyFileDAO();
		d.showFile(f1);

		request.getRequestDispatcher("ViewCategory?category=trash").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
