<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/admin">Back to admin page</a>
<br/>
<c:if test="${!empty getAllUsers}">
    <table>
        <tr>
            <th width="100" align="center">User</th>
            <th width="100" align="center">Delete</th>
            <th width="100" align="center">Make admin</th>
        </tr>
        <c:forEach items="${getAllUsers}" var="user">
            <tr>
                <td width="100" align="center">${user.username}</td>
                <td width="100" align="center"><a href="/delete/${user.id}">Delete</a></td>
                <td width="100" align="center"><a href="/makeAdmin/${user.id}">Make admin</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
