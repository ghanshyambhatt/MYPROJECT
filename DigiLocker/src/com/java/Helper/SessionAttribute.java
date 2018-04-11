package com.java.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionAttribute {

	public String getUsernameFormSession(HttpServletRequest request) {
		
		// getting username from session.
		HttpSession session = request.getSession();
		return session.getAttribute("username").toString();
	}
}
