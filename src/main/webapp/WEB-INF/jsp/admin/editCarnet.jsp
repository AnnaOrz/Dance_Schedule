<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edycja karnetu</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>
<body>

<h1> Edycja karnetu </h1>

<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>

<form:form method="post" modelAttribute="carnet">
    <dl class="row">
        <dt class="col-sm-3">Wejścia</dt>
        <dd class="col-sm-9">
            <form:input path="entrances" value="${carnet.entrances}"/>
            <form:errors path="entrances"/></dd>

        <dt class="col-sm-3">Data rozpoczęcia</dt>
        <dd class="col-sm-9">
            <form:input type="date" path="startDate" value="${carnet.startDate}"/>
            <form:errors path="startDate"/></dd>

        <dt class="col-sm-3">Data zakończenia</dt>
        <dd class="col-sm-9">
            <form:input type="date" path="expireDate" value="${carnet.expireDate}"/>
            <form:errors path="expireDate"/></dd>

        <dt class="col-sm-3">Poziom wejścia</dt>
        <dd class="col-sm-9">
            <form:input path="accessNumber" type="number" value="${carnet.accessNumber}"/>
            <form:errors path="accessNumber"/></dd>

        <dt class="col-sm-3">
            <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz zmiany"></dt>

            <form:input path="price" value="${carnet.price}" type="hidden"/>
            <form:input path="user" value="${carnet.user.id}" type="hidden"/>
</form:form>
    </dl>
</body>
</html>