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




</body>
</html>