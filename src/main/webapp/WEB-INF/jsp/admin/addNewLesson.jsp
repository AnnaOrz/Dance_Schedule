<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">


<html>
<head>
    <title>Utwórz lekcję</title>
</head>
<body>
<h2>Dodanie nowej lekcji</h2>
<form:form method="post" modelAttribute="lessonDto" >
<dl class="row">
    <dt class="col-sm-3">Nazwa </dt>
    <dd class="col-sm-9">
        <form:input path="name" value="${lessonDto.name}"/>
        <form:errors path="name" /></dd>
    <dt class="col-sm-3">Ilość miejsc </dt>
    <dd class="col-sm-9">
        <form:input path="slots" type="number" value="${lessonDto.slots}"/>
        <form:errors path="slots" /></dd>
    <dt class="col-sm-3">Data i godzina rozpoczęcia</dt>
    <dd class="col-sm-9">
        <form:input path="beginTime" type="datetime-local"  value="${lessonDto.beginTime}"/>
        <form:errors path="beginTime" /> </dd>
    <dt class="col-sm-3">Poziom </dt>
    <dd class="col-sm-9">
        <form:input path="level" value="${lessonDto.level}" />
        <form:errors path="level" />  </dd>
    <dt class="col-sm-3">Miejsce </dt>
    <dd class="col-sm-9">
        <form:input path="place" value="${lessonDto.place}" />
        <form:errors path="place" /> </dd>
    <dt class="col-sm-3">Trener </dt>
    <dd class="col-sm-9">
        <form:input path="trainerId" type="number"/>
        <form:errors path="trainerId" /></dd>
    <dt class="col-sm-3">Poziom wejścia</dt>
    <dd class="col-sm-9">
        <form:input path="accessNumber" type="number" value="${lessonDto.accessNumber}" />
        <form:errors path="accessNumber" /> </dd>
    <dt class="col-sm-3">
        <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz zmiany"> </dt>
    </form:form>
</dl>
</body>
</html>