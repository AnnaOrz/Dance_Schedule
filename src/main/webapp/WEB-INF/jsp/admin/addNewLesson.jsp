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
<form:form method="post" modelAttribute="lesson" >
<dl class="row">
    <dt class="col-sm-3">Nazwa </dt>
    <dd class="col-sm-9">
        <form:input path="name" value="${lesson.name}"/>
        <form:errors path="name" /></dd>
    <dt class="col-sm-3">Data i godzina rozpoczęcia</dt>
    <dd class="col-sm-9">
        <form:input path="beginTime" type="datetime-local"  value="${lesson.beginTime}"/>
        <form:errors path="beginTime" /> </dd>
    <dt class="col-sm-3">Poziom </dt>
    <dd class="col-sm-9">
        <form:input path="level" value="${lesson.level}" />
        <form:errors path="level" />  </dd>
    <dt class="col-sm-3">Miejsce </dt>
    <dd class="col-sm-9">
        <form:input path="place" value="${lesson.place}" />
        <form:errors path="place" /> </dd>
    <dt class="col-sm-3">Trener </dt>
    <dd class="col-sm-9">
        <form:select items="${allTrainers}" path="trainer" value="${lesson.trainer.lastName}"/>
        <form:errors path="trainer" /></dd>
    <dt class="col-sm-3">Poziom wejścia</dt>
    <dd class="col-sm-9">
        <form:input path="accessNumber" type="number" value="${lesson.accessNumber}" />
        <form:errors path="accessNumber" /> </dd>
    <dt class="col-sm-3">
        <form:hidden path="state" value="active" /> <br/>
        <form:hidden path="id" value="${lesson.id}"/>
        <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz zmiany"> </dt>
    </form:form>
</dl>
</body>
</html>