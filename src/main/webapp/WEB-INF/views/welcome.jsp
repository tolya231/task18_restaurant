<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Welcome</title>

</head>

<body>


<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><p>Welcome</p> ${pageContext.request.userPrincipal.name} | <a
                onclick="document.forms['logoutForm'].submit()" style="color: green">Logout </a>
        </h2>
    </c:if>
</div>

<h1>Dish List</h1>

<c:if test="${!empty getAllDishes}">
    <table>
        <tr>
            <th width="100" align="center">Dish</th>
            <th width="100" align="center">Price</th>
            <th width="100" align="center">Make order</th>
        </tr>
        <c:forEach items="${getAllDishes}" var="dish">
            <tr>
                <td width="100" align="center">${dish.name}</td>
                <td width="100" align="center">${dish.price}</td>
                <td width="100" align="center"><a href="<c:url value='/add/${dish.id}'/>">order</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Order List</h1>



<%--<c:if test="${!empty orderList}">
    <table>
        <tr>
            <th width="100" align="center">Dish</th>
            <th width="100" align="center">Price</th>
        </tr>
        <c:forEach items="${orderList}" var="dish">
            <tr>
                <td width="100" align="center">${dish.name}</td>
                <td width="100" align="center">${dish.price}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>--%>

</body>
</html>