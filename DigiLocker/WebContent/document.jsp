<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.java.model.User"%>
<html>
	<head>
		<title>Document</title>
		<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
		<link rel="stylesheet" href="Style/table_view.css"></link>
		<link rel="stylesheet" href="Style/header.css" />
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
						src="menu_down_arrow.png" height="30px" on>
				</a>
				<div class="dropdown-content" id="myDropdown">
					<a href="UserServlet?path=editprofile">Edit Profile</a>
					<a href="UserServlet?path=settings">Settings</a>
					<a href="logout">Logout</a>
				</div>
			</li>
		</ul>

		<!-- Sidenav -->
		<nav class="w3-sidenav w3-collapse w3-theme-l5 w3-animate-left"
			style="z-index:3;width:250px;margin-top:51px;" id="mySidenav">
		<a href="javascript:void(0)" onclick="w3_close()"
			class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large"
			title="close menu"> <i class="fa fa-remove"></i> </a>
		<h4>
			<b>Category</b>
		</h4>
		<div class="form-actions">
			<a href="AllCategoryServlet" class="w3-hover-grey">All Files</a>
			<a href="ViewCategory?category=picture" id="101"
				class="w3-hover-grey">Picture</a>
			<a href="ViewCategory?category=document" id="102"
				class="w3-hover-grey">Document</a>
			<a href="ViewCategory?category=other" id="103" class="w3-hover-grey">Other</a>
			<a href="ViewCategory?category=trash" id="103" class="w3-hover-grey">Trash</a>
		</div>
		</nav>

		<h3 align="center">
			Document
		</h3>
		<c:if test="${list!=null }">
			<table id="file_table" style="margin-left: 250px">
				<tr>
					<th>
						File Name
					</th>
					<th>
						Description
					</th>
					<th>
						Category
					</th>
					<th>
					</th>
					<th>
					</th>
					<c:forEach items="${list}" var="record">
						<tr>
							<td>
								${record.fileName}
							</td>
							<td>
								${record.description}
							</td>
							<td>
								<c:choose>
									<c:when test="${record.category_ID==101}">Picture</c:when>
									<c:when test="${record.category_ID==102}">Document</c:when>
									<c:otherwise>Other</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a
									href="http://pnraashishprasad.virtusa.com:8080/DigLocker/download?FileName=${record.fileName}&CategoryID=${record.category_ID}">
									<img src="download.png" height="35px" />
								</a>
							</td>
							<td>
								<a
									href="http://pnraashishprasad.virtusa.com:8080/DigLocker/delete?FileName=${record.fileName}&CategoryID=${record.category_ID}&PageCategory=document">
									<img src="delete.png" height="35px" />
								</a>
							</td>
						</tr>
					</c:forEach>
			</table>
		</c:if>
	</body>
</html>