<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: anda
  Date: 19.01.2021
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication var="principal" property="principal" />

<html>
<head>
    <title>DanceSchedule</title>
</head>
<body>
<div>Lets Rock!</div>
<%@ include file="fragments/sideMenu.jsp" %>
${principal} - to jest string principal  <%--tu chcę mieć imię widoczne dla zalogowanych--%>

<a class="nav-link" href="<c:url value="/dance/carnets/buy"/>">
    <span>Kup pan cegłę</span>
    <i class="fas fa-angle-right"></i>
</a>
</body>
</html>
