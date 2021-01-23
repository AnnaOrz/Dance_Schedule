<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication var="principal" property="principal" />

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
${principal}
</body>
</html>
