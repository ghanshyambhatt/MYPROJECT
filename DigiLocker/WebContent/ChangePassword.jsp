<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.java.model.User"%>
<html>
	<head>
	<title>Change Password</title>
		<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
		<link rel="stylesheet" href="Style/header.css" />
		<link rel="stylesheet" href="Style/upload.css" />
		<script src="javascript/dropdown.js">
</script>
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
				<a href="javascript:void(0)" class="dropbtn" onclick="myFunction()"><img
						src="menu_down_arrow.png" height="30px"> </a>
				<div class="dropdown-content" id="myDropdown">
					<a href="UserServlet?path=editprofile">Edit Profile</a>
					<a href="UserServlet?path=settings">Settings</a>
					<a href="logout">Logout</a>
				</div>
			</li>
		</ul>

		<center>
		
		<h3>Change Your Password</h3>
			<form action="UpdatePassword">
				<table>
					<tr>
						<td>
							<label>
								Current Password:
							</label>
						</td>
						<td>
							<input type="password"  name="current_password" placeholder="Enter Current Password" required/>
						</td>
					</tr>

					<tr>
						<td>
							<label>
								New Password:
							</label>
						</td>
						<td>
							<input type="password" name="new_password1" placeholder="Enter New Password" required/>
						</td>
					</tr>

					<tr>
						<td>
							<label>
								Re-Enter New Password:
							</label>
						</td>
						<td>
							<input type="password" name="new_password2" placeholder="Re-Enter New Password" required/>
						</td>
					</tr>

				</table>
				<br>
				<button type="submit">OK</button>
				<a href="UserSettings.jsp" class="cancelbtn">Cancel</a>
				<button type="reset"/>Clear</button>
				
			</form>

			<%
				String str = request.getParameter("msg");
				if (str != null) {
					out.print(str);
				}
			%>
		</center>

	</body>
</html>
