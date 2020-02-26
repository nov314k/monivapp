<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h3>Suggest a movie to watch</h3>

<p>
Movies not found in <a href="http://omdbapi.com" target="_blank">omdbapi.com</a>
can also be suggested.
</p>

<form:form action="add" modelAttribute="movie" method="POST">

	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger">
			${errorMessage}
		</div>
	</c:if>

	<form:hidden path="id" />

	<div style="margin-bottom: 15px">
		<form:input path="title" placeholder="Movie title" class="form-control" />
	</div>

	<button type="submit" class="btn btn-success btn-sm">Save</button>

	<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning btn-sm">Cancel</a>
	
	<!-- <a href="${pageContext.request.contextPath}/search/showForm" class="btn btn-primary btn-sm">Search OMDB</a> -->

</form:form>
</div>
</body>
</html>