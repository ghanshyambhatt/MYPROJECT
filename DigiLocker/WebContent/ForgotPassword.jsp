<html>
	<head>
		<title>Reset Password</title>
		<link rel="stylesheet" href="Style/forgetpass.css">
	</head>
	<body>
		<center>
			<form action="reset" method="post">
				<div class="container">
					Please enter your registered username.
					<br>
					We will send your new DigiLocker password to your registered email
					ID.
					<p>
						<b>Username:</b>
						<input type="text" name="username" size="45" placeholder="Enter Your Username" required/>
					</p>

					<br>
					<button type="submit" value="OK">
						OK
					</button>
					<br>
					<br>
					<a href="login.jsp" class="cancelbtn">Cancel</a>
					<br><br>

					<%
						String str = request.getParameter("msg");
						if (str != null) {
							out.print(str);
						}
					%>

				</div>
			</form>
		</center>
	</body>
</html>

