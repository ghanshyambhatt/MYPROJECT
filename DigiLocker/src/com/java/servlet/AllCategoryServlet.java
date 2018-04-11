package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.DAO.MyFileDAO;
import com.java.Helper.SessionAttribute;
import com.java.model.FileModel;

public class AllCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//getting username from session.
		SessionAttribute sess=new SessionAttribute();
		String username=sess.getUsernameFormSession(request);
		
		MyFileDAO dao = new MyFileDAO();
		List<FileModel> list = dao.getfiledetails(username);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("AllFiles.jsp");
		rd.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
