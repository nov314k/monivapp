<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">

<head>
<title>monivapp</title>  
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>

<body>

<div class="container">
<h3>Search Open Movies Database <a href="http://omdbapi.com" target="_blank">omdbapi.com</a></h3>

<form:form action="search" modelAttribute="searchResult" method="POST">

	<div style="margin-bottom: 15px">
		<form:input path="title" placeholder="Movie title search term" class="form-control" />
	</div>

	<button type="submit" class="btn btn-success btn-sm">Search</button>

	<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning btn-sm">Cancel</a>

</form:form>
</div>
</body>
</html>