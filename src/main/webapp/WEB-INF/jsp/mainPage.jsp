<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authentication var="principal" property="principal"/>


<html>
<head>
    <title>DanceSchedule</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="include.jsp" %>
</head>
<body>
<%@ include file="fragments/topMenu.jsp" %>
<h1 class="bg-dark text-light text-center">Aplikacja DanceSchedule </h1>


<%--<sec:authorize access="hasAuthority('ADMIN')">
    <%@ include file="./admin/menu.jsp" %>
</sec:authorize>--%>




<sec:authorize access="isAuthenticated()">

    <%@ include file="fragments/userMenu.jsp" %>
</sec:authorize>



</body>
</html>
