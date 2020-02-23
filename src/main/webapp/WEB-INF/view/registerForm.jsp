<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">

<head>
<title>monivapp</title>  
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<!-- TODO Remove
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
-->
<style>.error {color:red}</style>
</head>

<body>

<div class="container">

<h3>Movie Night Voting App</h3>
<h6>All fields are required to register a new user</h6>

<form:form action="${pageContext.request.contextPath}/register/register" modelAttribute="crmUser">

	<c:if test="${registrationError != null}">
		<div class="alert alert-danger col-md-4">
			${registrationError}
		</div>
	</c:if>

	<div style="margin-bottom: 15px">
		<form:errors path="userName" cssClass="error" />
		<form:input path="userName" placeholder="Username" class="form-control col-md-4" />
	</div>

	<div style="margin-bottom: 15px">
		<form:errors path="password" cssClass="error" />
		<form:password path="password" placeholder="Password" class="form-control col-md-4" />
	</div>

	<div style="margin-bottom: 15px">
		<form:errors path="matchingPassword" cssClass="error" />
		<form:password path="matchingPassword" placeholder="Confirm password" class="form-control col-md-4" />
	</div>

	<div style="margin-bottom: 15px">
		<form:errors path="firstName" cssClass="error" />
		<form:input path="firstName" placeholder="First name" class="form-control col-md-4" />
	</div>

	<div style="margin-bottom: 15px">
		<form:errors path="lastName" cssClass="error" />
		<form:input path="lastName" placeholder="Last name" class="form-control col-md-4" />
	</div>

	<div style="margin-bottom: 15px">
		<form:errors path="email" cssClass="error" />
		<form:input path="email" placeholder="Email" class="form-control col-md-4" />
	</div>

	<button type="submit" class="btn btn-success btn-sm">Register</button>

	<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning btn-sm">View movies</a>

</form:form>
</div>
</body>
</html>