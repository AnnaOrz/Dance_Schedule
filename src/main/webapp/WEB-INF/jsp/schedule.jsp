<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<sec:authentication var="principal" property="principal" />
<html>
<head>
    <title>Grafik</title>
</head>
<body>
<sec:authorize access="hasAuthority('ADMIN')">
    <%@ include file="./admin/menu.jsp" %>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <%@ include file="fragments/userMenu.jsp" %>
</sec:authorize>

<sec:authorize access="isAnonymous()">
    <%@ include file="fragments/topMenu.jsp" %>
</sec:authorize>

${message.toString()}
</body>
</html>
