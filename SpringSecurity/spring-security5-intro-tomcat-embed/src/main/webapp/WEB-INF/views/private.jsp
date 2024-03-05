<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Private Page</title>
</head>
<body>

<div>
    Your username is: <strong>${username}</strong>
</div>

<sec:authorize access="hasRole('ADMIN')">
    <div>User with ADMIN Role</div>
</sec:authorize>

<sec:authorize access="hasRole('USER')">
    <div>User with USER Role</div>
</sec:authorize>

<a href="<c:url value="/logout" />">Logout</a>

</body>
</html>