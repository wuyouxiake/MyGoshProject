<!DOCTYPE html>
<html lang="en">
<head>
<title>Gosh!</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		if (session.getAttribute("loggedIn") == null) {
			session.setAttribute("loggedIn", false);
			//session.setAttribute("admin", false);
		}
		if (request.getParameter("logOut") != null) {
			if (request.getParameter("logOut").equals("true")) {
		session.setAttribute("loggedIn", false);
			}
		}
	%>

<jsp:include page="NavBar.jsp" />
	<div class="container">
		<div class="jumbotron">
			<h1>Welcome to Gosh!</h1>
		</div>
	</div>
	<%if (request.getParameter("logOut") != null) {
			if (request.getParameter("logOut").equals("true")) {
				%>
	<div class"container">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Success!</strong> You have logged out.
		</div>
	</div>
	<%}}%>
	${alertMessage} ${logIn}
</body>
</html>
