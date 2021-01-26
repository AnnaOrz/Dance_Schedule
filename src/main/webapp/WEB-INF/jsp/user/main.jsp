<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="principal" property="principal" />

<html>
<head>
    <title>DanceSchedule</title>
</head>
<body>
<div><sec:authorize access="isAuthenticated()">
    Zalogowany jako ${principal.username}  </sec:authorize></div>
<%@ include file="../fragments/sideMenu.jsp" %>


<a class="nav-link" href="<c:url value="/dance/carnets/buy"/>">
    <span>Kup karnet</span>
</a> <br/>
<a class="nav-link" href="<c:url value="/dance/user/carnets"/>">
    <span>Moje karnety</span>
</a> <br/>

<a class="nav-link" href="<c:url value="/dance/user/classes"/>">
    <span>Moje zajęcia</span>
</a> <br/>


</body>
</html>
