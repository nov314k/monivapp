<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>List movies</title>
<link type="text/css" rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h2>CRM - movie Relationship Manager</h2>
	</div>
</div>
<div id="container">
	<div id="content">
<p>
User: <security:authentication property="principal.username" /><br />
You have ${numofRemainingVotes} votes, and you can add ${numofRemainingAdditions} movies.
</p>
<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
	<c:choose>
		<c:when test="${numofRemainingAdditions > 0}">
	    	<input type="button" value="Add movie"
			   onclick="window.location.href='showFormForAdd'; return false;"
			   class="add-button" />
	    </c:when>
	    <c:otherwise>
	    	You cannot add movies.
	    </c:otherwise>
	</c:choose>
</security:authorize>
<table>
	<tr>
		<th>Title</th>
		<th>Votes</th>
		<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
			<th>Action</th>
		</security:authorize>
	</tr>
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
					<a href="${deleteLink}"
				   		onclick="if (!(confirm('Are you sure you want to delete this movie?'))) return false">Delete</a>
				</security:authorize>
				<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
					<a href="${updateLink}">Update</a>
				</security:authorize>
				<security:authorize access="hasAnyRole('VOTER', 'MAINTAINER', 'ADMIN')">
					<c:choose>
						<c:when test="${numofRemainingVotes > 0}">
	    					<a href="${voteLink}">Vote</a>		
	    				</c:when>
	    				<c:otherwise>
	    					You have no votes remaining!
	    				</c:otherwise>
					</c:choose>
				</security:authorize>
			</td>
		</tr>
	</c:forEach>					
</table>
</div>
</div>
<p>
Roles: <security:authentication property="principal.authorities" /><br />
</p>
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	<input type="submit" value="Logout" class="add-button" />
</form:form>
</body>
</html>