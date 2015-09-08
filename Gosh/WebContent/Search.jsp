<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="NavBar.jsp" />
<div align="left">
<form class="form-horizontal" role="form" method="get" action="Search">
    
<input type="text" name="target" required>
<input type="submit" name="submit" Value="Seacrh">
<br><br>

<input type="radio" name="choice" value="shop" checked>Shop

<input type="radio" name="choice" value="product">Product
<br><br>



</form>

</div>


</body>
</html>