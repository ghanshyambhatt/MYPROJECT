<html>
	<head>
		<title>DigiLocker</title>
	</head>
	<body background="square.jpg" >
	<center>
		<b>
		<%
			String str = request.getParameter("msg");
			if (str != null) {
				out.println(str);
			}else{
				out.println("Welcome to DigiLocker");
			}
		%></b>
		<br><br>
		<form>
			<a href="login.jsp"><img src="image/login.png" height="250px" /></a>
			<a href="registration.jsp"><img src="image/register.png" height="250px" /></a>
		</form>
		<%
			String err = request.getParameter("errormsg");
			if (err != null) {
				out.println(err);
			}
		%>
		</center>
	</body>
</html>

