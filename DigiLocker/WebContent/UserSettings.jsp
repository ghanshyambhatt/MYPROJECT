<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
		<link rel="stylesheet" href="Style/header.css" />
		<link rel="stylesheet" href="Style/settings.css" />
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

		<h3>Settings</h3><br><br>
		<a href="ChangePassword.jsp" class="actionbtn">Change Password</a><br><br><br>
		<a href="#" class="actionbtn">Deactivate My Account</a>
			
	</body>
</html>
