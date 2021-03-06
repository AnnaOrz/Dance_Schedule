<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Edycja użytkownika</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>
<body>

<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>

<form:form method="post" modelAttribute="user">
<dl class="row">
    <dt class="col-sm-3">Imię</dt>
    <dd class="col-sm-9">
        <form:input path="firstName" value="${user.firstName}"/>
        <form:errors path="firstName"/></dd>
    <dt class="col-sm-3">Nazwisko:</dt>
    <dd class="col-sm-9">
        <form:input path="lastName" value="${user.lastName}"/>
        <form:errors path="lastName"/></dd>
    <dt class="col-sm-3">Email</dt>
    <dd class="col-sm-9">
        <form:input path="email" value="${user.email}"/>
        <form:errors path="email"/></dd>

    <dt class="col-sm-3">Hasło</dt>
    <dd class="col-sm-9">
        <form:input path="password" type="password" value="${user.password}"/>
        <form:errors path="password"/></dd>
    <dt class="col-sm-3">Rola</dt>
    <dd class="col-sm-9">
        <form:input path="role" value="${user.role}"/>
        <form:errors path="role"/></dd>

    <dt class="col-sm-3">Aktywowany</dt>
    <dd class="col-sm-9">
        <form:select items="${enabled}" path="enabled" value="${user.enabled}"/>
        <form:errors path="enabled"/></dd>
    <dt class="col-sm-3">
        <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz zmiany"></dt>
    </form:form>
</body>
</html>
