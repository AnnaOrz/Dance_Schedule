<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Dodaj karnet</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>
<body>

<form:form method="post" modelAttribute="carnetType">
<dl class="row">
    <dt class="col-sm-3">Opis</dt>
    <dd class="col-sm-9">
        <form:input path="description" value="${carnetType.description}"/>
        <form:errors path="description"/></dd>

    <dt class="col-sm-3"> Liczba wejść</dt>
    <dd class="col-sm-9">
        <form:input path="entrances" type="number" value="${carnetType.entrances}"/>
        <form:errors path="entrances"/></dd>


    <dt class="col-sm-3">Poziom wejścia</dt>
    <dd class="col-sm-9">
        <form:input path="accessNumber" type="number" value="${carnetType.accessNumber}"/>
        <form:errors path="accessNumber"/></dd>

    <dt class="col-sm-3">Cena</dt>
    <dd class="col-sm-9">
        <form:input path="price" type="number" value="${carnetType.price}"/>
        <form:errors path="price"/></dd>
    <dt class="col-sm-3">Dostępny w sprzedaży</dt>
    <dd class="col-sm-9">
        <form:checkbox path="available" name="available " value="${carnetType.available}"/>
        <form:errors path="available"/></dd>

    <dt class="col-sm-3">
        <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz"></dt>
    </form:form>
</body>

</html>
