<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
    <c:set var="host" value="http://localhost:8080" />
    <h2>Welcome Page</h2>
    <ul>
        <li><a href="${host}/sv1/task/new">ValidationUsingSpring</a></li>
        <li><a href="${host}/sv2/task/new">ValidationUsingJSR303</a></li>
        <li><a href="${host}/sv3/task/new">ValidationUsingBoth</a></li>
        <li><a href="${host}/sv4/task/new">ValidationUsingBothWithCustomMessage</a></li>
    </ul>
</body>
</html>
