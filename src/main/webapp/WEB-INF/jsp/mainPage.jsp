<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication var="principal" property="principal" />

<sec:authorize access="hasAuthority('ADMIN')">
    <a class="nav-link" href="<c:url value="/dance/admin/"/>">
    <span>Idź do menu Admina</span>
    </a>
</sec:authorize>

<html>
<head>
    <title>DanceSchedule</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <div> Zalogowany jako ${principal.username} </div>    </sec:authorize>

<%@ include file="fragments/topMenu.jsp" %>

<sec:authorize access="isAuthenticated()">

<a class="nav-link" href="<c:url value="/dance/user/buy"/>">
    <span>Kup karnet</span>
</a>
<br/>
<a class="nav-link" href="<c:url value="/dance/user/carnets"/>">
    <span>Moje karnety</span>
</a> <br/>

<a class="nav-link" href="<c:url value="/dance/user/classes"/>">
    <span>Moje zajęcia</span>
</a> <br/>
</sec:authorize>

</body>
</html>
