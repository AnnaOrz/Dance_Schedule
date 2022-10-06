<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Moje karnety</title>
    <%@ include file="../include.jsp" %>
</head>
<body>
<%@ include file="../fragments/userMenu.jsp" %>
<h2> Karnety Aktywne</h2>

${message}
<table class="table table-light table-striped">
    <thead>
    <tr>

        <th scope="col">Wejścia</th>
        <th scope="col">Data rozpoczęcia</th>
        <th scope="col">Data zakończenia</th>
        <th scope="col">Poziom karnetu</th>

    </tr>
    </thead>

    <c:forEach items="${userActiveCarnets}" var="carnet">
        <tr>
            <td>${carnet.entrances}</td>
            <td>${carnet.startDate}</td>
            <td>${carnet.expireDate}</td>
            <td>${carnet.accessNumber}</td>
        </tr>
    </c:forEach>
</table>
<h2> Karnety Przeszłe</h2>

<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col">Wejścia</th>
        <th scope="col">Data rozpoczęcia</th>
        <th scope="col">Data zakończenia</th>
        <th scope="col">Poziom karnetu</th>

    </tr>
    </thead>

    <c:forEach items="${userFormerCarnets}" var="carnet">
        <tr>
            <td>${carnet.entrances}</td>
            <td>${carnet.startDate}</td>
            <td>${carnet.expireDate}</td>
            <td>${carnet.accessNumber}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>