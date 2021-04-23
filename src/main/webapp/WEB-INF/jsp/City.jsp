<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>City</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<h1>CITY PAGE</h1>
	<c:choose>
		<c:when test="${not empty loggedUser}">
			<h2>Welcome, ${loggedUser}!</h2>
		</c:when>
		<c:otherwise>
			<h2>Welcome, Guest!</h2>
		</c:otherwise>
	</c:choose>
	<a href="/countries"><button type="button" class="btn btn-warning">Countries</button></a>
	<a href="/continents"><button type="button" class="btn btn-dark">Continents</button></a>
	<h3>Find any information about your preferred city.</h3>
	<form autocomplete="off" action="/city" method="POST">
		<label for="cityName">City Name:</label>
		<input type="text" name="cityName" />
		<button type="submit" class="btn btn-primary">Search</button>
	</form>
	<c:choose>
	<c:when test="${empty city && empty error}">
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
					<th scope="col">District:</th>
					<th scope="col">Population:</th>
				</tr>
				<tr>
					<td>${city.name}</td>
					<td>${city.countryCode}</td>
					<td>${city.district}</td>
					<td>${city.population}</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>