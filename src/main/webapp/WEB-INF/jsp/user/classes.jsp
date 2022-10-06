<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moje zajęcia</title>
    <%@ include file="../include.jsp" %>
</head>
<body>
<%@ include file="../fragments/userMenu.jsp" %>
<h1> Moje zajęcia</h1>
<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col"></th>
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
    <c:forEach items="${userClasses}" var="lesson">
    <tr>
        <td><a href='<c:url value="/dance/user/optOut/${lesson.id}"/>'
               onclick="return confirm('Czy na pewno chcesz wypisać się z lekcji?')">Wypisz się z lekcji</a>
        </td>
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
