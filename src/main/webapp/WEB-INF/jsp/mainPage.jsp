<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication var="principal" property="principal" />
<sec:authorize access="isAuthenticated()">
    ${principal.username}  </sec:authorize>
<sec:authorize access="hasAuthority('ADMIN')">
    <a class="nav-link" href="<c:url value="/dance/admin/"/>">
    <span>Idź do menu Admina</span>
    </a>
</sec:authorize>

<html>
<head>
    <title>DanceSchedule</title>
</head>
<body>
<div>Lets Rock!</div>
<%@ include file="fragments/sideMenu.jsp" %>
<%--tu chcę mieć imię widoczne dla zalogowanych--%>

<a class="nav-link" href="<c:url value="/dance/carnets/buy"/>">
    <span>Kup karnet</span>
</a>
<br/>

</body>
</html>
