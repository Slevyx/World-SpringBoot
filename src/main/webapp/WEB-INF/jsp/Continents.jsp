<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Continents</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<h1>CONTINENTS PAGE</h1>
	<h2>Welcome, ${loggedUser}!</h2>
	<a href="/city"><button type="button" class="btn btn-primary">City</button></a>
	<a href="/countries"><button type="button" class="btn btn-primary">Countries</button></a>
	<h3>Find information about countries and cities.</h3>
	<c:choose>
	<c:when test="${empty continentsList}">
		 <h4>Nothing was found.</h4>
		</c:when>
		<c:otherwise>
			<table class="table">
				<tr>
					<th scope="col">Continent Name:</th>
				</tr>
				<c:forEach items="${continentsList}" var="continent">
				<tr>
					<td><a href="/${continent}/countries">${continent}</a></td>
				</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>