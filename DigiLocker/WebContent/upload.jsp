<%@page import="com.java.model.User"%>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
		<link rel="stylesheet" href="Style/button_style.css">
		<link rel="stylesheet" href="Style/header.css" />
		<link rel="stylesheet" href="Style/upload.css" />
		<script src="javascript/dropdown.js">
</script>
	</head>
	<body background="square.jpg">
		<center>
			<ul>
				<li>
					<a href="welcome.jsp">Home</a>
				</li>

				<li>
					<a href="UserServlet?path=user" style="margin-left: 850px"> <%
 	out.print(request.getSession().getAttribute("name"));
 %> </a>
				</li>

				<li class="dropdown">
					<a href="javascript:void(0)" class="dropbtn" onclick="myFunction()"><img
							src="menu_down_arrow.png" height="30px" on> </a>
					<div class="dropdown-content" id="myDropdown">
						<a href="UserServlet?path=editprofile">Edit Profile</a>
						<a href="UserServlet?path=settings">Settings</a>
						<a href="logout">Logout</a>
					</div>
				</li>
			</ul>

			<form action="upload" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							Select a file :
						</td>
						<td>
							<input type="file" name="file" size="45" />
						</td>
					</tr>
					<tr>
						<td>
							File Name:
						</td>
						<td>
							<input type="text" name="filename" id="filename"
								placeholder="Enter Filename" required />
						</td>
					</tr>
					<tr>
						<td>
							Category:
						</td>
						<td>
							<select name="category" id="category">
								<option value="picture">
									Picture
								</option>
								<option value="document">
									Document
								</option>
								<option value="other">
									Other
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							Description:
						</td>
						<td>
							<input type="text" name="file_description" id="file_description"
								placeholder="Enter Description" />
						</td>
					</tr>
					<tr>
						<td>
							Secure:
						</td>
						<td>
							<label class="switch">
								<input type="checkbox" name="secure" id="secure" />
								<div class="slider round"></div>
							</label>
						</td>
					</tr>
				</table>
				<br>
				<button type="submit" value="Upload File">
					Upload
				</button>
				<a href="welcome.jsp" class="cancelbtn">Cancel</a>
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
