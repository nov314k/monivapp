<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Save Movie</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-movie-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - movie Relationship Manager</h2>
		</div>
	</div>
	<div id="container">
		<h3>Save movie</h3>
		<form:form action="saveMovie" modelAttribute="movie" method="POST">
			<!-- need to associate this data with movie id -->
			<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>Title:</label></td>
						<td><form:input path="title" /></td>
					</tr>
					<tr>
						<td><label>Votes:</label></td>
						<td><form:input path="votes" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<div style="clear; both;"></div>
		<p>
			<a href="${pageContext.request.contextPath}/movie/list">Back to List</a>
		</p>
	</div>
</body>
</html>