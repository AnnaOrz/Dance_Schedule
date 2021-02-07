<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Zajęcia</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>

<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>

<h1> Zajęcia </h1>
${message}

<a href='<c:url value="/dance/admin/lessons/add"/>'> Dodaj nowe zajęcia</a>
<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col">Id zajęć</th>
        <th scope="col">Nazwa zajęć</th>
        <th scope="col">Data i godzina rozpoczęcia</th>
        <th scope="col">Status</th>
        <th scope="col">Poziom wejścia</th>
        <th scope="col">Poziom zajęć</th>
        <th scope="col">Miejsce</th>
        <th scope="col">Trener</th>
        <th scope="col">Ilość uczestników</th>
        <th scope="col">Ilość miejsc</th>
    </tr>
    </thead>

    <c:forEach items="${allLessons}" var="lesson">
    <tr>
        <td> <a href='<c:url value="/dance/admin/lessons/cancel/${lesson.id}"/>'
                onclick="return confirm('Czy na pewno chcesz odwołać lekcję?')">Odwołaj</a></td>
        <td><a href='<c:url value="/dance/admin/lessons/edit/${lesson.id}"/>'>Edytuj</a></td>
        <th scope="row">${lesson.id}</th>
        <td>${lesson.name}</td>
        <td>${lesson.beginTime.toLocalDate()} ${lesson.beginTime.toLocalTime()} </td>
        <td>${lesson.state}</td>
        <td>${lesson.accessNumber}</td>
        <td>${lesson.level}</td>
        <td>${lesson.place}</td>
        <td>${lesson.trainer.lastName}</td>
        <td>${lesson.participants.size()}</td>
        <td>${lesson.slots}</td>
    </tr>
    </c:forEach>

</body>
</html>