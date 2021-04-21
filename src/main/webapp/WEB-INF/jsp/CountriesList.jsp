<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Countries</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<h1>COUNTRIES PAGE</h1>
	<h2>Welcome, ${loggedUser}!</h2>
	<a href="/city"><button type="button" class="btn btn-danger">City</button></a>
	<a href="/continents"><button type="button" class="btn btn-dark">Continents</button></a>
	<h3>Find information about countries by country name, continent name or both.</h3>
	<form autocomplete="off" action="/countries" method="POST">
		<label for="countryName">Country Name:</label>
		<input type="text" name="countryName" />
		<label for="continentName">Continent Name:</label>
		<input type="text" name="continentName" /> 
		<button type="submit" class="btn btn-primary">Search</button>
	</form>
	<c:choose>
	<c:when test="${empty countriesList && empty error}">
		 <h4>Search for cities Data.</h4>
		</c:when>
		<c:when test="${not empty error}">
		 <h4>${error}</h4>
		</c:when>
		<c:otherwise>
			<table class="table">
				<tr>
					<th scope="col">Name:</th>
					<th scope="col">Country Code:</th>
					<th scope="col">Continent:</th>
					<th scope="col">Population:</th>
					<th scope="col">Surface Area:</th>
				</tr>
				<c:forEach items="${countriesList}" var="country">
				<tr>
					<td>${country.name}</td>
					<td>${country.code}</td>
					<td>${country.continent}</td>
					<td>${country.population}</td>
					<td>${country.surfaceArea}</td>
				</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>