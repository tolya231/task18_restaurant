<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <style>
        <%@include file='/WEB-INF/views/css/style.css' %>
    </style>
    <title>Orders</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/admin">Back to admin page</a>
<p></p>
<c:if test="${!empty getAcceptedOrders}">
    <table>
        <tr>
            <th width="100" align="center">Client</th>
            <th width="100" align="center">Accept</th>
        </tr>
        <c:forEach items="${getAcceptedOrders}" var="order">
            <tr>
                <td width="100" align="center">${order.user.username}</td>
                <td width="100" align="center"><a href="/accept/${order.id}">Accept</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty getAcceptedOrders}">No orders</c:if>
</body>
</html>
