<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <style>
        <%@include file='/WEB-INF/views/css/style.css' %>
    </style>
    <title>Thank you</title>
</head>
<body>
<h2>Thank you for using our restaurant service.</h2>
<a href="${pageContext.request.contextPath}/welcome">Make another order</a>
</body>
</html>
