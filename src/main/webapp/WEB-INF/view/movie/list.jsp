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
<h3>Movie Night Voting App</h3>

<security:authorize access="hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
	
	<p>
		Hello <security:authentication property="principal.username" />!
		You have ${numofRemainingVotes} votes remaining,
		and you can add ${numofRemainingAdditions} movies.<br />
	</p>

	<!-- "Suggest a movie to watch" button, and "Sign out" button -->	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST" class="form-horizontal">
		
		<!-- 
		<a href="${pageContext.request.contextPath}/search/searchForm" class="btn btn-primary btn-sm">
				Search OMDB (experimental)</a>
		-->
		
		<!-- TODO Do all the logic in the controller -->
		<c:choose>
			<c:when test="${numofRemainingAdditions > 0}">
				<!-- TODO Remove
		   		<input type="button" value="Suggest a movie to watch"
					onclick="window.location.href='addForm'; return false;"
					class="btn btn-primary btn-sm mb-3" />
				-->
				<a href="${pageContext.request.contextPath}/movie/addForm" class="btn btn-primary btn-sm">
					Suggest a movie to watch</a>
		   	</c:when>
		   	<c:otherwise>
		   		&nbsp;
		   	</c:otherwise>
		</c:choose>
		
		<input type="submit" value="Sign out" class="btn btn-primary btn-sm" />
	
	</form:form>
	
</security:authorize>

<!-- "Sign in" button -->
<security:authorize access="!hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
	<a href="${pageContext.request.contextPath}/loginForm" class="btn btn-primary btn-sm">
	Sign in to vote and suggest movies to watch</a>
	<!-- TODO Remove
	<input type="button" value="Sign in to vote and suggest movies to watch"
		onclick="window.location.href='loginForm'; return false;"
		class="btn btn-primary btn-sm mb-3" />
	-->
</security:authorize>

<p>
	Movie details (via links below) are fetched fresh from
	<a href="http://omdbapi.com" target="_blank">omdbapi.com</a>. 
</p>

<table class="table table-bordered table-striped">

 	<thead class="thead-dark">
		<tr>
			<th>Movie Title</th>
			<th>Votes</th>
			<security:authorize access="hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
				<th>Available Actions</th>
			</security:authorize>
		</tr>
	</thead>
	
	<c:forEach var="tempMovie" items="${movies}">

		<c:url var="updateLink" value="/movie/updateForm">
			<c:param name="movieId" value="${tempMovie.id}" />
		</c:url>					

		<c:url var="deleteLink" value="/movie/delete">
			<c:param name="movieId" value="${tempMovie.id}" />
		</c:url>					

		<c:url var="voteLink" value="/movie/vote">
			<c:param name="movieId" value="${tempMovie.id}" />
		</c:url>

		<c:url var="detailLink" value="/movie/detail">
			<c:param name="movieId" value="${tempMovie.id}" />
		</c:url>

		<tr>
			<td>
				<a href="${detailLink}">${tempMovie.title}</a>
			</td>
			
			<td>${tempMovie.votes}</td>
			
			<!-- Available actions -->
			<security:authorize access="hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
				<td>

					<security:authorize access="hasAnyRole('ADMIN')">
						<a href="${deleteLink}" class="btn btn-danger btn-sm"
					   		onclick="if (!(confirm('Are you sure you want to delete this movie?'))) return false">Delete</a>
					</security:authorize>
			
					<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
						<a href="${updateLink}" class="btn btn-warning btn-sm">Update</a>
					</security:authorize>
			
					<!-- TODO Do all the logic in the controller -->
					<c:choose>	
						<c:when test="${numofRemainingVotes > 0}">
		    				<a href="${voteLink}" class="btn btn-success btn-sm">Vote</a>
		    			</c:when>
		    			<c:otherwise>
		    				&nbsp;
		    			</c:otherwise>
					</c:choose>
					
				</td>
			</security:authorize>
		</tr>
	</c:forEach>					
</table>

<security:authorize access="hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
	<p>
		In the period of one calendar month from today's date,
		you can vote 3 times, and you can suggest 3 new movies to watch.
		You can vote for the same movie multiple times.

		<security:authentication property="principal.authorities" />
	</p>
</security:authorize>

<security:authorize access="hasAnyRole('ADMIN')">
	<a href="${pageContext.request.contextPath}/action/list" class="btn btn-danger btn-sm">
	Delete actions (for testing only)</a>
</security:authorize>

</div>
</body>
</html>