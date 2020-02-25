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

<form:form action="searchOmdb" modelAttribute="searchResult" method="post">

	<form:hidden path="search" />
	
	<form:hidden path="totalResults" />
	
	<form:hidden path="response" value="True" />
		
	<div style="margin-bottom: 15px">
		<form:input path="searchTerm" placeholder="Enter movie title search term" class="form-control" />
	</div>

	<button type="submit" class="btn btn-success btn-sm">Search OMDB</button>

	<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning btn-sm">Cancel</a>

</form:form>    
    
<c:choose>
    <c:when test="${response}">
		<table class="table table-bordered table-striped">
 			<p></p>
 			<p>Top ten search results:</p>	
			<c:forEach var="tempMovie" items="${search}">
				
				<c:url var="previewLink" value="/movie/preview">
					<c:param name="imdbId" value="${tempMovie.imdbId}" />
				</c:url>
			
				<tr>
					<td>
						<img src="${tempMovie.poster}" alt="Poster link is not available" class="font-weight-italic">
					</td>
					<td>
						<div class="font-weight-bold">
							<!-- <a href="${previewLink}">${tempMovie.title}</a>  -->
							${tempMovie.title}
						</div>
						
						<div class="font-weight-light">${tempMovie.year}</div>
					</td>
				</tr>
			</c:forEach>					
		</table>			
	</c:when>
	<c:otherwise>
        <p></p>
    </c:otherwise>
</c:choose>

</div>
</body>
</html>