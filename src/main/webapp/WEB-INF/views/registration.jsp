<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        <%@include file='/WEB-INF/views/css/style.css' %>
    </style>
    <title>Create an account</title>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <script type="text/javascript">
        function doAjax() {
            $.ajax({
                url: 'checkStrength',
                data: ({password: $('#password').val()}),
                success: function (data) {
                    $('#strengthValue').html(data);
                }
            });
        }
    </script>

</head>

<body>

<div class="container" align="center">

    <form:form method="POST" modelAttribute="userForm">
        <h2 align="center"> Create your account </h2>
        <p></p>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" placeholder="Username"
                            autofocus="true"/>
                <form:errors path="username"/>
            </div>
        </spring:bind>


        <p></p>
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:password path="password" placeholder="Password" onkeyup="doAjax()"/>
                <form:errors path="password"/>
            </div>
        </spring:bind>

        <p></p>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:password path="confirmPassword"
                               placeholder="Confirm your password"/>
                <form:errors path="confirmPassword"/>
            </div>
        </spring:bind>

        <span style="align-content: center" id="strengthValue"></span>
        <button class="btn btn-lg btn-primary btn-block" align=" center">Submit</button>
    </form:form>

</div>
<!-- /container -->
</body>
</html>