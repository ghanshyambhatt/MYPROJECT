package com.java.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.DAO.MySessionDAO;
import com.java.exception.MyExceptionHandler;

public class AuthenticationFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		boolean isValid = isValidRequest(req);
		if (isValid) {
			chain.doFilter(req, res);
		} else {
			HttpServletResponse response = (HttpServletResponse) res;

			response
					.sendRedirect("home.jsp?msg=Invalid Request. You are currently not logged in!");
			// new MyExceptionHandler(null, "request validation failed");
		}
	}

	private boolean isValidRequest(ServletRequest request) {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		MySessionDAO sessdao = new MySessionDAO();
		if (sessdao.sessionExists(session.getId())) {
			return true;
		} else {
			return false;

		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
