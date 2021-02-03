<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Typy karnetów</title>
</head>
<body>
<h1> Typy karnetów </h1>

<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>

${message}
<a href='<c:url value="/dance/admin/addCarnetType"/>'> Dodaj nowy karnet</a>
<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col">Id typu</th>
        <th scope="col">Opis</th>
        <th scope="col">Cena</th>
        <th scope="col">Wejścia</th>
        <th scope="col">Poziom karnetu</th>
        <th scope="col">Możliwy do kupienia</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>

    <c:forEach items="${carnetTypes}" var="carnetType">
    <tr>
        <th scope="row">${carnetType.id}</th>
        <td>${carnetType.description}</td>
        <td>${carnetType.price}</td>
        <td>${carnetType.entrances}</td>
        <td>${carnetType.accessNumber}</td>
        <td>${carnetType.available}</td>
        <td><a href='<c:url value="/dance/admin/carnetTypes/delete/${carnetType.id}"/>'
               onclick="return confirm('Czy na pewno chcesz usunąć typ karnetów?')">Usuń</a></td>
        <td><a href='<c:url value="/dance/admin/carnetTypes/edit/${carnet.id}"/>'>Edytuj</a></td>

    </tr>


    </c:forEach>
</body>
</html>
