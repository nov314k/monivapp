<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
<title>monivapp</title>  
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- TODO Are the two below necessary? -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<h3>Movie details</h3>
<c:choose>
	<c:when test="${detail.response}">
		<table class="table table-striped">
			<tbody>
	    		<tr>
	      			<th scope="row">Title</th>
	      			<td>${detail.title}</td>
	    		</tr>
		    	<tr>
	    	  		<th scope="row">Year</th>
	      			<td>${detail.year}</td>
	    		</tr>
	    		<tr>
	      			<th scope="row">Rated</th>
	      			<td>${detail.rated}</td>
	    		</tr>
	    		<tr>
	      			<th scope="row">Released</th>
	      			<td>${detail.released}</td>
	    		</tr>
	    		<tr>
	      			<th scope="row">Runtime</th>
	      			<td>${detail.runtime}</td>
	    		</tr>
	    		<tr>
	      			<th scope="row">Genre</th>
	      			<td>${detail.genre}</td>
	    		</tr>
	    		<tr>
	      			<th scope="row">Director</th>
	      			<td>${detail.director}</td>
	    		</tr>
	    		<tr>
		      		<th scope="row">Writer</th>
	    	  		<td>${detail.writer}</td>
	    		</tr>
	    		<tr>
	      			<th scope="row">Actors</th>
	      			<td>${detail.actors}</td>
	    		</tr>
	    		<tr>
		      		<th scope="row">Plot</th>
	    	  		<td>${detail.plot}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Language</th>
		      		<td>${detail.language}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Country</th>
		      		<td>${detail.country}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Awards</th>
		      		<td>${detail.awards}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Poster</th>
		      		<td><img src=${detail.poster} /></td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Metascore</th>
		      		<td>${detail.metascore}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">imdbRating</th>
		      		<td>${detail.imdbRating}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">imdbVotes</th>
		      		<td>${detail.imdbVotes}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">imdbID</th>
		      		<td>${detail.imdbId}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Type</th>
		      		<td>${detail.type}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">DVD</th>
		      		<td>${detail.dvd}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Awards</th>
		      		<td>${detail.awards}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">BoxOffice</th>
		      		<td>${detail.boxOffice}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Production</th>
		      		<td>${detail.production}</td>
		    	</tr>
		    	<tr>
		      		<th scope="row">Website</th>
		      		<td>${detail.website}</td>
		    	</tr>
	  		</tbody>
		</table>
	</c:when>
	<c:otherwise>
   		<p>
   			Movie details cannot be retrieved via <a href="http://omdbapi.com" target="_blank">omdbapi.com</a>.
   			Please check and update the movie title.
   		</p>
   	</c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/movie/list">Back to movies list</a>
</div>
</body>
</html>
