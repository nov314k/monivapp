<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>
	<title>List movies</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
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
				User: <security:authentication property="principal.username" />, Role(s): <security:authentication property="principal.authorities" />
			</p>
		

			<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
			
				<!-- put new button: Add movie -->
			
				<input type="button" value="Add movie"
					   onclick="window.location.href='showFormForAdd'; return false;"
					   class="add-button"
				/>
			
			</security:authorize>
	
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>Title</th>
					<th>Votes</th>
					
					<%-- Only show "Action" column for managers or admin --%>
					<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
					
						<th>Action</th>
					
					</security:authorize>
					
				</tr>
				
				<!-- loop over and print our movies -->
				<c:forEach var="tempMovie" items="${movies}">
				
					<!-- construct an "update" link with movie id -->
					<c:url var="updateLink" value="/movie/showFormForUpdate">
						<c:param name="movieId" value="${tempMovie.id}" />
					</c:url>					

					<!-- construct an "delete" link with movie id -->
					<c:url var="deleteLink" value="/movie/delete">
						<c:param name="movieId" value="${tempMovie.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempMovie.title} </td>
						<td> ${tempMovie.votes} </td>
						

						<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
						
							<td>
								<security:authorize access="hasAnyRole('MAINTAINER', 'ADMIN')">
									<!-- display the update link -->
									<a href="${updateLink}">Update</a>
								</security:authorize>
	
								<security:authorize access="hasAnyRole('ADMIN')">
									<a href="${deleteLink}"
									   onclick="if (!(confirm('Are you sure you want to delete this movie?'))) return false">Delete</a>
								</security:authorize>
							</td>

						</security:authorize>
												
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	
	<p></p>
		
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" class="add-button" />
	
	</form:form>

</body>

</html>









