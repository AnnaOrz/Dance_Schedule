<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Rejestracja</title>
    <%@ include file="../include.jsp" %>
</head>
<body>

<%@ include file="../fragments/topMenu.jsp" %>

<div class="alert-warning"> ${message} </div>

<form:form method="post" modelAttribute="userRegistrationDto">
<dl class="row">
    <dt class="col-sm-3">Imię</dt>
    <dd class="col-sm-9">
        <form:input path="firstName" value="${userRegistrationDto.firstName}"/>
        <form:errors path="firstName"/></dd>
    <dt class="col-sm-3">Nazwisko:</dt>
    <dd class="col-sm-9">
        <form:input path="lastName" value="${userRegistrationDto.lastName}"/>
        <form:errors path="lastName"/></dd>
    <dt class="col-sm-3">Email</dt>
    <dd class="col-sm-9">
        <form:input path="email" value="${userRegistrationDto.email}"/>
        <form:errors path="email"/></dd>

    <dt class="col-sm-3">Hasło</dt>
    <dd class="col-sm-9">
        <form:input path="password" type="password"/>
        <form:errors path="password"/></dd>

    <dt class="col-sm-3">
        <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz"></dt>
    </form:form>

</body>
</html>
