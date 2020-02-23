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
<h3>Movie Night Voting App</h3>

<table class="table table-bordered table-striped">

 	<thead class="thead-dark">
		<tr>
			<th>username</th>
			<th>action</th>
			<th>movie_id</th>
			<th>date</th>
			<th>ADMINISTRATION</th>
		</tr>
	</thead>
	
	<c:forEach var="tempAction" items="${actions}">
	
		<c:url var="deleteLink" value="/action/delete">
			<c:param name="actionId" value="${tempAction.id}" />
		</c:url>
	
		<c:url var="updateLink" value="$/action/updateForm">
			<c:param name="actionId" value="${tempAction.id}" />
		</c:url>
	
		<tr>
			<td>${tempAction.username}</td>
			<td>${tempAction.action}</td>
			<td>${tempAction.movie_id}</td>
			<td>${tempAction.date}</td>
			<td>
				<a href="${updateLink}" class="btn btn-warning btn-sm">Update</a>
				<a href="${deleteLink}" class="btn btn-danger btn-sm">Delete</a>
			</td>
		</tr>
	</c:forEach>					
</table>

<a href="${pageContext.request.contextPath}/">Back to movies list</a>

</div>
</security:authorize>
</body>
</html>