package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

public class DeleteServlet extends HttpServlet {

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

		String PageCategory = request.getParameter("PageCategory");

		MyFileDAO d = new MyFileDAO();

		if (PageCategory != null && PageCategory.equals("trash")) {

			d.deleteFile(f1);
			request.getRequestDispatcher("ViewCategory?category=trash")
					.forward(request, response);
		} else {

			d.hideFile(f1);

			if (PageCategory != null) {
				request.getRequestDispatcher(
						"ViewCategory?category=" + PageCategory).forward(
						request, response);

			} else {
				// calling servlet
				AllCategoryServlet ser = new AllCategoryServlet();
				ser.doGet(request, response);
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
