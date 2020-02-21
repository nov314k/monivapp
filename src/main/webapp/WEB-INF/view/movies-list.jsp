<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>monivapp</title>  
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- TODO Are the two below necessary? -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<h3>Movie Night Voting App</h3>
<div id="content">
<security:authorize access="hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
	<p>
	Hello <security:authentication property="principal.username" />!
	You have ${numofRemainingVotes} votes remaining, and you can add ${numofRemainingAdditions} movies.<br />
	</p>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST" class="form-horizontal">
		<c:choose>
			<c:when test="${numofRemainingAdditions > 0}">
		   		<input type="button" value="Suggest a movie to watch"
					onclick="window.location.href='showFormForAdd'; return false;"
					class="btn btn-primary btn-sm mb-3" />
		   	</c:when>
		   	<c:otherwise>
		   		<div class="font-italic">Suggesting a movie is not available!</div>
		   	</c:otherwise>
		</c:choose>
		<input type="submit" value="Sign out" class="btn btn-primary btn-sm mb-3" />
	</form:form>
</security:authorize>
<table class="table table-bordered table-striped">
 	<thead class="thead-dark">
	<tr>
		<th>Title</th>
		<th>Votes</th>
		<th>Action</th>
	</tr>
	</thead>
	<c:forEach var="tempMovie" items="${movies}">
		<c:url var="updateLink" value="/movie/showFormForUpdate">
			<c:param name="movieId" value="${tempMovie.id}" />
		</c:url>					
		<c:url var="deleteLink" value="/movie/delete">
			<c:param name="movieId" value="${tempMovie.id}" />
		</c:url>					
		<c:url var="voteLink" value="/movie/vote">
			<c:param name="movieId" value="${tempMovie.id}" />
		</c:url>
		<tr>
			<td>${tempMovie.title}</td>
			<td>${tempMovie.votes}</td>
			<td>
				<security:authorize access="hasAnyRole('ADMIN')">
					<a href="${deleteLink}" class="btn btn-danger btn-sm"
				   		onclick="if (!(confirm('Are you sure you want to delete this movie?'))) return false">Delete</a>
				</security:authorize>
				<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
					<a href="${updateLink}" class="btn btn-warning btn-sm">Update</a>
				</security:authorize>
				<security:authorize access="hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
					<c:choose>
						<c:when test="${numofRemainingVotes > 0}">
	    					<a href="${voteLink}" class="btn btn-success btn-sm">Vote</a>
	    				</c:when>
	    				<c:otherwise>
	    					<div class="font-italic">You have used up your votes!</div>
	    				</c:otherwise>
					</c:choose>
				</security:authorize>
			</td>
		</tr>
	</c:forEach>					
</table>
<p>
In the period of one calendar month from today's date,
you can vote 3 times, and you can suggest 3 new movies to watch.
You can vote for the same movie multiple times.
<security:authentication property="principal.authorities" />
</p>
</div>
</div>
</body>
</html>