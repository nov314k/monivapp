<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">

<head>
<title>monivapp</title>  
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<!-- TODO Remove
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
-->
</head>

<body>

<div class="container">
<h3>Update a suggested movie</h3>

<form:form action="update" modelAttribute="movie" method="POST">

	<form:hidden path="id" />

	<div style="margin-bottom: 15px">
		<form:input path="title" placeholder="Movie title" class="form-control" />
	</div>

	<!-- TODO Change so that only an ADMIN can change the number of votes -->
	<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
		<div style="margin-bottom: 15px">
			<form:input path="votes" class="form-control" />
		</div>
	</security:authorize>

	<button type="submit" class="btn btn-success">Save</button>

	<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning">Cancel</a>
</form:form>

</div>
</body>
</html>