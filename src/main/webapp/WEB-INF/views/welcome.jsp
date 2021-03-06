<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <style>
        <%@include file='/WEB-INF/views/css/style.css' %>
    </style>
    <title>Welcome</title>

</head>

<body>

<span style="float: right">
    <a href="?lang=en">en</a>
    <a href="?lang=ru">ru</a>
    </span>


<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><spring:message code="welcome"/> ${pageContext.request.userPrincipal.name} |
            <a onclick="document.forms['logoutForm'].submit()" style="cursor: pointer"><spring:message code="logout"/></a>
        </h2>
    </c:if>

    <c:if test="${isAdmin}">
        <a href="${pageContext.request.contextPath}/admin"><spring:message code="adminPage"/></a>
    </c:if>
    <p>
        <label><spring:message code="yourMoney"/>: ${getMoney}</label>
    </p>
    <p><spring:message code="menu"/></p>

    <c:if test="${!empty getAllDishes}">
        <table>
            <tr>
                <th width="100" align="center"><spring:message code="dish1"/></th>
                <th width="100" align="center"><spring:message code="price1"/></th>
                <th width="100" align="center"><spring:message code="make"/></th>
            </tr>
            <c:forEach items="${getAllDishes}" var="dish">
                <tr>
                    <td width="100" align="center">${dish.name}</td>
                    <td width="100" align="center">${dish.price}</td>
                    <td width="100" align="center"><a href="<c:url value='/add/${dish.id}'/>"><spring:message code="order2"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


    <c:if test="${!empty orderList and getStatus eq 'NOT_ORDERED'}">
        <p><spring:message code="order2"/></p>
        <table>
            <tr>
                <th width="100" align="center"><spring:message code="dish1"/></th>
                <th width="100" align="center"><spring:message code="price1"/></th>
                <th width="100" align="center"><spring:message code="delete"/></th>
            </tr>
            <c:forEach items="${orderList}" var="dish">
                <tr>
                    <td width="100" align="center">${dish.name}</td>
                    <td width="100" align="center">${dish.price}</td>
                    <td width="100" align="center"><a href="/remove/${dish.id}"><spring:message code="cancel"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <p></p>

    <c:choose>
        <c:when test="${getStatus eq 'NOT_ORDERED'}">
            <c:url var="addAction" value="/welcome/make"/>
            <form:form action="${addAction}">
                <c:if test="${!empty orderList}">
                    <c:if test="${canPay}">
                        <input type="submit" value="<spring:message code="order1"/>"/>
                        <label><spring:message code="price1"/>: ${getPrice}</label>
                    </c:if>
                    <c:if test="${!canPay}">
                        <p><spring:message code="noMoney"/></p>
                        <label><spring:message code="price1"/>: ${getPrice}</label>
                    </c:if>
                </c:if>
            </form:form>
        </c:when>
        <c:otherwise> <label><spring:message code="wait"/> <spring:message code="price1"/>: ${getPrice}</label> </c:otherwise>
    </c:choose>
    <p></p>
    <%--<p style="color: red">Status: ${getStatus}</p>--%>
    <p><spring:message code="bill"/></p>

    <form:form action="/welcome/pay">
        <c:if test="${getStatus eq 'ACCEPTED'}">
            <label><spring:message code="price1"/>: ${getPrice}</label>
            <input type="submit" value="<spring:message code="pay"/>">
        </c:if>
    </form:form>
</div>
</body>
</html>