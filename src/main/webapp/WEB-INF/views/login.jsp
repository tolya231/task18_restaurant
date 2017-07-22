<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="<c:url value="/WEB-INF/views/css/style.css" />" rel="stylesheet">
    <title>Log in with your account</title>
</head>

<body>

<div class="container">

    <form method="POST" action="${contextPath}/login">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <p>Login:</p>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <p>Password:</p>
            <input name="password" type="password" placeholder="Password"/>
            <br>
            <span>${error}</span>
            <br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>

    </form>

</div>
<!-- /container -->

</body>
</html>