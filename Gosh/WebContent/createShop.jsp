<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>CreateShop</title>
</head>
<body>
<jsp:include page="NavBar.jsp" />
<div class="container">
		<h2>Create Shop</h2>
		<form class="form-horizontal" role="form" action="createShop"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="">Shop Name:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="shopName" name="shopName"
						placeholder="Enter Shop Name">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="">Shop Description:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="shopDesc" name="shopDesc"
						placeholder="Enter description">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="">Shop Picture:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="shopPic" name="shopPic"
						placeholder="Enter link to picture">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Create</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>