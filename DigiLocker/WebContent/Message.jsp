<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.java.model.User"%>
<html>
	<head>
		<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
		<link rel="stylesheet" href="Style/header.css" />
		<script src="javascript/dropdown.js" ></script>
	</head>
	<body background="square.jpg" >

		<ul>
			<li>
				<a href="welcome.jsp">Home</a>
			</li>

			<li>
				<a href="UserServlet?path=user" style="margin-left: 850px"><% out.print(request.getSession().getAttribute("name"));%></a>
			</li>

			<li class="dropdown">
				<a href="javascript:void(0)" class="dropbtn" onclick="myFunction()"><img src="menu_down_arrow.png" height="30px" on></a>
				<div class="dropdown-content" id="myDropdown">
					<a href="UserServlet?path=editprofile">Edit Profile</a>
					<a href="UserServlet?path=settings">Settings</a>
					<a href="logout">Logout</a>
				</div>
			</li>
		</ul>

		<center>
			
			<%
				String str = request.getParameter("msg");
				if (str != null) {
					out.print(str);
				}
			%>
		</center>

	</body>
</html>
