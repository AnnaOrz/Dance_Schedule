<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">

<html>
<head>
    <title>Dodaj karnet</title>
    <meta charset="UTF-8">
</head>
<body>

<form:form method="post" modelAttribute="carnetType" >
<dl class="row">
    <dt class="col-sm-3">Opis</dt>
    <dd class="col-sm-9">
        <form:input path="description" value="${carnetType.description}"/>
        <form:errors path="description" /></dd>

    <dt class="col-sm-3"> Liczba wejść </dt>
    <dd class="col-sm-9">
        <form:input path="entrances" value="${carnetType.entrances}" />
        <form:errors path="entrances" />  </dd>


    <dt class="col-sm-3">Poziom wejścia</dt>
    <dd class="col-sm-9">
        <form:input path="accessNumber" value="${carnetType.accessNumber}" />
        <form:errors path="accessNumber" />  </dd>

    <dt class="col-sm-3">Cena</dt>
    <dd class="col-sm-9">
        <form:input path="price" value="${carnetType.price}" />
        <form:errors path="price" /> </dd>
    <dt class="col-sm-3">Dostępny w sprzedaży </dt>
    <dd class="col-sm-9">
        <form:select items="${boolean}" path="available" value="${carnetType.available}"/>
        <form:errors path="available" /></dd>

    <dt class="col-sm-3">
        <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz"> </dt>
    </form:form>
</body>

</html>