<html>
	<head>
		<title>Login</title>
		<style>
</style>
<link rel="stylesheet" href="Style/login.css"></link>
	</head>
	<body>
		<center>
			<form action="login" method="post">
				<div class="container">
				<h2 align="center">Login</h2>
					<label>
						<b>Username</b>
					</label>
					<input type="text" name="username" id="username" size="45"
						placeholder="Enter Username" required/>
					<br>

					<label>
						<b>Password</b>
					</label>
				
					<input type="password" name="password" id="password"
						placeholder="Enter Password" required/>



					<br>
					<button type="submit" value="Login">Login</button><br>
					<button type="reset" value="Clear" class="clearbtn">Clear</button>
					<br>
				</div>
				<span class="psw"><a href="ForgotPassword.jsp">Forgot Password</a></span>
				<br>
				<br>
				<div>
					Don't have an account?
				</div>
				<a href="registration.jsp">Click here to Register</a>

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

