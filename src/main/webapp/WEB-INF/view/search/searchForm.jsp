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
<!-- <h3>Search Open Movies Database <a href="http://omdbapi.com" target="_blank">omdbapi.com</a></h3>  -->
<h3>Search Open Movies Database</h3>

<form:form action="searchOmdb" modelAttribute="searchResult" method="post">

	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger col-md-4">
			${errorMessage}
		</div>
	</c:if>

	<form:hidden path="search" />
	
	<form:hidden path="totalResults" />
	
	<form:hidden path="response" value="True" />
		
	<div style="margin-bottom: 15px">
		<form:input path="searchTerm" placeholder="Enter movie title search term" class="form-control col-md-4" />
	</div>

	<button type="submit" class="btn btn-success btn-sm">Search OMDB</button>

	<a href="${pageContext.request.contextPath}/movie/list" class="btn btn-warning btn-sm">Cancel</a>

</form:form>    
    
<c:choose>
    <c:when test="${response}">    	
    	<p>&nbsp;</p>
    	<p>Top ten search results:</p>
		<table class="table table-bordered table-striped col-md-4">
			<c:forEach var="tempMovie" items="${search}">
				<c:url var="imdbLink" value="https://www.imdb.com/title/"></c:url>
				<tr>
					<td>
						<img src="${tempMovie.poster}" alt="Poster link is not available" class="font-weight-italic">
						<p class="font-weight-bold">${tempMovie.title}
							&nbsp;
							<small class="font-italic">${tempMovie.year}</small>
							<br />
							<br />
							<a href="${pageContext.request.contextPath}/movie/addForm?title=${tempMovie.title}"
								class="btn btn-primary btn-sm">
								Suggest this movie!</a>
							&nbsp;
							<a href="${imdbLink}${tempMovie.imdbId}" target="_blank">IMDb movie page</a>
						</p>
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