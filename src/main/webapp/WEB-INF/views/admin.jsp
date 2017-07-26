<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <%@include file='/WEB-INF/views/css/style.css' %>
    </style>
    <title>Admin</title>

</head>

<body>


<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a
                onclick="document.forms['logoutForm'].submit()" style="color: green">Logout </a>
        </h2>
    </c:if>

    <a href="${pageContext.request.contextPath}/welcome">Go to client page</a>
    <p></p>
    <form:form action="/admin/seeUsers">
        <input type="submit" value="See users">
    </form:form>
    <br/>
    <form:form action="/admin/seeOrders">
        <input type="submit" value="See orders">
    </form:form>

</div>
</body>
</html>