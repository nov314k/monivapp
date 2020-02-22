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
To be able to view movie details via <a href="http://omdbapi.com" target="_blank">omdbapi.com</a>,
movie titles have to be entered as they are cataloged in their database.
If details are not required, any movie title can be entered. Search-by-title feature is under development!
</p>
<p>
Some titles that have been tested are: Shawshank Redemption, The Godfather, The Dark Knight,
12 Angry Men, Pulp Fiction, Fight Club, Forrest Gump, Inception, Goodfellas, Seven Samurai, Se7en, Parasite,
City of God, Life is Beautiful, Underground.
</p>
<p>
We are not checking for duplicate movie titles at the moment ;-)
</p>
<form:form action="add" modelAttribute="movie" method="POST">
	<form:hidden path="id" />
	<div style="margin-bottom: 15px">
		<form:input path="title" placeholder="Movie title" class="form-control" />
	</div>
	<button type="submit" class="btn btn-success">Save</button>
	<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning">Cancel</a>
</form:form>
</div>
</body>
</html>