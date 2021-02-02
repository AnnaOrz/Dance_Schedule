<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<sec:authentication var="principal" property="principal" />

<sec:authorize access="hasAuthority('ADMIN')">
    <%@ include file="./admin/menu.jsp" %>
</sec:authorize>

<html>
<head>
    <title>DanceSchedule</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>
<body>
<sec:authorize access="isAnonymous()">
    <%@ include file="fragments/topMenu.jsp" %>
</sec:authorize>


<sec:authorize access="isAuthenticated()">

    <%@ include file="fragments/userMenu.jsp" %>
</sec:authorize>


<h1> ZAWARTOŚĆ STRONY GŁÓWNEJ</h1>



</body>
</html>
