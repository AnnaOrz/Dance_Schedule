<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Edycja użytkownika</title>
    <%@ include file="../include.jsp" %>
</head>
<body>

<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>

<form:form method="post" modelAttribute="userDto">
<dl>
    <dt >Imię</dt>
    <dd>
        <form:input path="firstName" value="${oldFirstName}"/>
        <form:errors path="firstName"/></dd>
    <dt>Nazwisko:</dt>
    <dd>
        <form:input path="lastName" value="${oldLastName}"/>
        <form:errors path="lastName"/></dd>
    <dt>Email</dt>
    <dd>
        <form:input path="email" value="${oldEmail}"/>
        <form:errors path="email"/></dd>


    <dt>Aktywowany</dt>
    <dd>
        <form:select items="${enabled}" path="enabled" value="true"/>
        <form:errors path="enabled"/></dd>
    <dt>
        <input type="submit" value="Zapisz zmiany">
    </dt>

    </form:form>
</body>
</html>
