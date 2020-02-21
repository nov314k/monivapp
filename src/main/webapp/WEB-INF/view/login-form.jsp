<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
<title>monivapp</title>  
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- TODO Are the two below necessary? -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<h3>Movie Night Voting App</h3>
<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
  	<c:if test="${param.error != null}">
		<div class="alert alert-danger col-md-4">
			Invalid username and/or password
		</div>
	</c:if>
	<c:if test="${param.logout != null}">
		<div class="alert alert-success col-md-4">
			You have successfully signed out
		</div>	   					
	</c:if>
	<div style="margin-bottom: 15px">
		<input class="form-control col-md-4" type="text" name="username" placeholder="Username">
	</div>
	<div style="margin-bottom: 15px">
		<input class="form-control col-md-4" type="password" name="password" placeholder="Password">
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-success">Sign in</button>
		<a href="${pageContext.request.contextPath}/register/showRegistrationForm" class="btn btn-primary">Register</a>
		<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning">View movies</a>
	</div>
</form:form> 
</div>
</body>
</html>