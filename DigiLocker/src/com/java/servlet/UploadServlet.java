package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.exception.MyExceptionHandler;
import com.java.rest.webservice.FileService;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// saving and storing file in database.
		FileService service = new FileService();
		try {
			service.uploadFile(request);
		} catch (MyExceptionHandler e) {
			e.printStackTrace();
		}
		response.sendRedirect("upload.jsp?msg=File Uploaded Successfully!");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}