<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<html>
<head>
    <title>Karnety użytkowników</title>
</head>
<body>
<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>
<h1> Karnety użytkowników</h1>
${message}
<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col">Id karnetu</th>
        <th scope="col">Wejścia</th>
        <th scope="col">Data rozpoczęcia</th>
        <th scope="col">Data zakończenia</th>
        <th scope="col">Poziom karnetu</th>
        <th scope="col">Email właściciela</th>
    </tr>
    </thead>

    <c:forEach items="${allCarnets}" var="carnet">
    <tr>
        <th scope="row">${carnet.id}</th>
        <td>${carnet.entrances}</td>
        <td>${carnet.startDate}</td>
        <td>${carnet.expireDate}</td>
        <td>${carnet.accessNumber}</td>
        <td>${carnet.user.email}</td>
        <td><a href='<c:url value="/dance/admin/carnets/delete/${carnet.id}"/>'
               onclick="return confirm('Czy na pewno chcesz usunąć ten karnet?')">Usuń</a></td>
        <td><a href='<c:url value="/dance/admin/carnets/edit/${carnet.id}"/>'>Edytuj</a></td>

    </tr>


    </c:forEach>


</body>
</html>
