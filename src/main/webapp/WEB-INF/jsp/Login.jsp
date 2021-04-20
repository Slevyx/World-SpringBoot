<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<h1>LOGIN PAGE</h1>
	<form autocomplete="off" action="/login" method="POST">
		<label for="username">Username:</label>
		<input type="text" name="username" placeholder="Username" /> 
		<input type="submit" value="LOGIN" />
	</form>
	<c:if test="${not empty error}">
		<h4>${error}</h4>
	</c:if>
</body>
</html>