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
</head>

<body>

<!-- TODO Remove additional security checks as redundant -->
<security:authorize access="hasAnyRole('ADMIN')">

<div class="container">
<h3>Update an action</h3>

<form:form action="update" modelAttribute="action" method="POST">

	<form:hidden path="id" />

	<div style="margin-bottom: 15px">
		<form:input path="username" class="form-control" />
	</div>

	<div style="margin-bottom: 15px">
		<form:input path="action" class="form-control" />
	</div>

	<div style="margin-bottom: 15px">
		<form:input path="movie_id" class="form-control" />
	</div>

	<div style="margin-bottom: 15px">
		<form:input path="date" class="form-control" />
	</div>

	<button type="submit" class="btn btn-success btn-sm">Save</button>
	
	<a href="${pageContext.request.contextPath}/action/list" class="btn btn-warning btn-sm">Cancel</a>

</form:form>
</div>
</security:authorize>
</body>
</html>