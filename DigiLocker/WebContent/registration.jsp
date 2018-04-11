<html>
	<head>
		<title>Register</title>
		<link rel="stylesheet" href="Style/registration.css">
		<script type="text/javascript" src="javascript/countries.js">
</script>
	</head>
	<body>
		<center>
			<div class="container">
			<h2>Register</h2>
				<form action="register" method="post">
					<table>
					<col width="100">
						<tr>
							<td>
								<b>Name:</b>
							</td>
							<td>
								<input type="text" name="name" id="name" size="45"
									placeholder="Enter Your Name" required />
							</td>
						</tr>
						<tr>
							<td>
								<b>Username:</b>
							</td>
							<td>
								<input type="text" name="username" size="45"
									placeholder="Enter Username" required />
							</td>
						</tr>

						<tr>
							<td>
								<b>Password:</b>
							</td>
							<td>
								<input type="password" name="password" id="password"
									placeholder="Enter Password" required />
							</td>
						</tr>
						<tr>
							<td>
								<b>Country:</b>
							</td>
							<td>
								<select onchange="print_state('state',this.selectedIndex);"
									id="country" name="country"></select>
							</td>
						</tr>

						<script language="javascript">
print_country("country");</script>
						<tr>
							<td>
								<b>Email:</b>
							</td>
							<td>
								<input type="text" name="email" size="45"
									placeholder="Enter Your Email" required />
							</td>
						</tr>
						<tr>
							<td>
								<b>Mobile:</b>
							</td>
							<td>
								<input type="text" name="mobile" id="mobile"
									placeholder="Enter Your Mobile Number" required />
							</td>
						</tr>
					</table>
					<button type="submit" value="Register">Register</button>

					<button type="reset" value="Clear" class="clearbtn">Clear</button>

					<a href="login.jsp" class="cancelbtn">Cancel</a>
				</form>
			</div>
			<%
				String str = request.getParameter("msg");
				if (str != null) {
					out.print(str);
				}
			%>
		</center>
	</body>
</html>

