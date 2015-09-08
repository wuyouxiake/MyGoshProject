<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Gosh!</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Browse <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="GetAll?All=products">Products</a></li>
							<li><a href="GetAll?All=shops">Shops</a></li>
						</ul></li>
					<li><a href="Search.jsp">Search</a></li>
					<%
						if ((Boolean) session.getAttribute("loggedIn")) {
					%>
					<li><a href="GetMyCart"><span
							class="glyphicon glyphicon-shopping-cart"></span></a></li>
					<li><a href="MyShop"><span
							class="glyphicon glyphicon-home"></span></a></li>
					<li><a href="GetMyOrder"><span
							class="glyphicon glyphicon-list-alt"></span></a></li>
					<%
						}
					%>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<%
						if ((Boolean) session.getAttribute("loggedIn")) {
					%>
					<li><a href="index.jsp?logOut=true"><span
							class="glyphicon glyphicon-log-in"></span> Sign Out</a></li>
					<li><a href="EditProfile"><span
							class="glyphicon glyphicon-user"></span> Edit Profile</a></li>
					<%
						} else {
					%>
					<li><a href="SignUp"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>

					<li><a href="SignIn"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
					<%
						}
					%>

				</ul>
			</div>
		</div>
	</nav>