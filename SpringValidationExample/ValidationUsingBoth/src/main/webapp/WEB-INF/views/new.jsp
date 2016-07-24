<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Task Manager | New</title>
    <style>
        .ferror{
            color: red;
        }
    </style>
</head>
<body>
    <h3><fmt:message key="task.title"/></h3>
    <h4>ValidationUsingBoth</h4>
    <s:hasBindErrors name="task">
        <h3><s:message code="error.title"/></h3>
        <ul>
            <c:forEach items="${errors.globalErrors}" var="errorMessage">
                <li class="ferror"><s:message message="${errorMessage}"/></li>
            </c:forEach>
        </ul>
    </s:hasBindErrors>
    <form:form method="post" commandName="task">
        <table>
            <tr>
                <td>Title:</td>
                <td>
                    <form:input path="taskTitle"/><form:errors path="taskTitle" cssClass="ferror"/>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:textarea path="taskDescription"/></td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><form:input path="startDate"/>dd/mm/yyyy</td>
            </tr>
            <tr>
                <td>End Date:</td>
                <td><form:input path="endDate"/>dd/mm/yyyy</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>

</body>
</html>

