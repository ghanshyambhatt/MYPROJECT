
<%@page import="com.java.model.User"%><!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
		<link rel="stylesheet"
			href="http://www.w3schools.com/lib/w3-theme-black.css">
		<link rel="stylesheet" href="Style/header.css" />
		<link rel="stylesheet" href="Style/welcome.css" />
		<script src="javascript/dropdown.js">
</script>
		<script type="text/javascript">

function showfunction() {
	var div = document.getElementById('categoryoptions');
	
		div.style.display = 'block';
	
};
function hidefunction() {
	var div = document.getElementById('categoryoptions');

		div.style.display = 'none';

};
</script>
	</head>
	<body background="square.jpg">

		<ul>
			<li>
				<a href="welcome.jsp">Home</a>
			</li>

			<li>
				<a href="UserServlet?path=user" style="margin-left: 850px"><% out.print(request.getSession().getAttribute("name"));%></a>
			</li>

			<li class="dropdown">
				<a href="javascript:void(0)" class="dropbtn" onclick="myFunction()"><img
						src="menu_down_arrow.png" height="30px" > </a>
				<div class="dropdown-content" id="myDropdown">
					<a href="UserServlet?path=editprofile">Edit Profile</a>
					<a href="UserServlet?path=settings">Settings</a>
					<a href="logout">Logout</a>
				</div>
			</li>
		</ul>

		<center>
		<dir><b><%
				String str = request.getParameter("msg");
				if (str != null) {
					out.print(str);
				}
			%></b></dir>
			<div>
				<a href="upload.jsp" class="w3-hover-green" ><img
						src="image/upload.png" height="200px"/></a>

				<a onmouseover="showfunction()" onmouseout="hidefunction()"><img src="image/view.png" height="200px" /></a>
				
				<a href="AllCategoryServlet" class="w3-hover-green"><img
						src="image/all.png" height="200px"/></a>

				

				<div id="categoryoptions" class="categorylist" onmouseover="showfunction()" onmouseout="hidefunction()">
					<a href="ViewCategory?category=picture"><img src="image/picture.png" height="150px"/></a>
					<a href="ViewCategory?category=document"><img src="image/document.png" height="150px"/></a>
					<a href="ViewCategory?category=other"><img src="image/other.png" height="150px"/></a>
				</div>



			</div>
		</center>

	</body>
</html>
