<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title>My Shop</title>
</head>
<body>
	<jsp:include page="NavBar.jsp" />

	<div class="container">
		<div align="center" class="jumbotron">
			<img src="${shopPic}" class="img-rounded" width="304" height="236">
			<h1>${shopName}</h1>
			<p>${shopDesc}</p>
		</div>

	</div>



	<div class="container-fluid">
		<h2>Add Product</h2>

		<form class="form-inline" role="form" method="Post" action="MyShop">
			<div class="form-group" col-sm-2>
				<div class="col-xs-2">
					<input type="text" class="form-control" id="name" name="name"
						placeholder="Enter Product Name">
				</div>
			</div>
			<div class="form-group" col-sm-2>
				<div class="col-xs-2">
					<input type="text" class="form-control" id="desc" name="desc"
						placeholder="Enter Description">
				</div>
			</div>
			<div class="form-group" col-sm-2>
				<div class="col-xs-2">
					<input type="text" class="form-control" id="photo" name="photo"
						placeholder="Enter Photo Link">
				</div>
			</div>

			<div class="form-group" col-sm-1>
				<div class="col-xs-2">
					<input type="text" class="form-control" id="price" name="price"
						min="0" placeholder="Enter Price">
				</div>
			</div>
			<div class="form-group" col-sm-1>
				<div class="col-xs-2">
					<input type="number" class="form-control" col-sm-1 id="qty"
						name="qty" min="0" placeholder="Enter Quantity">
				</div>
			</div>
			<div class="form-group" col-sm-1>
				<div class="col-xs-2">
					<input type="text" class="form-control" col-sm-1 id="ship"
						name="ship" min="0" placeholder="Enter Shipping">
				</div>
			</div>
			<div class="form-group" col-sm-1>
				<div class="col-xs-2">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form>
	</div>
	<br></br>
	${productList}
</body>
</html>