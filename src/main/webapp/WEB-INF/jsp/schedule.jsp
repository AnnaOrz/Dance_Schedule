<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication var="principal" property="principal"/>

<html>
<head>
    <title>Grafik zajęć</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>

<body>
<sec:authorize access="hasAuthority('ADMIN')">
    <%@ include file="./admin/menu.jsp" %>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <%@ include file="fragments/userMenu.jsp" %>
</sec:authorize>

<sec:authorize access="isAnonymous()">
    <%@ include file="fragments/topMenu.jsp" %>
</sec:authorize>

${message.toString()}
<form class="d-flex" method="post">
    <div>
        <input class="form-control me-2" type="date" placeholder="Search" aria-label="Search" name="date">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <button class="btn btn-outline-success" type="submit"> Wybierz zajęcia według daty</button>
    </div>
</form>

<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col"></th>
        <th scope="col">Nazwa zajęć</th>
        <th scope="col">Data i godzina rozpoczęcia</th>
        <th scope="col">Status</th>
        <th scope="col">Poziom karnetu</th>
        <th scope="col">Poziom zajęć</th>
        <th scope="col">Sala</th>
        <th scope="col">Trener</th>
        <th scope="col">Miejsca/Zajęte</th>
    </tr>
    </thead>

    <c:forEach items="${classes}" var="lesson">
        <tr>
            <td>
                <sec:authorize access="isAuthenticated()">
                <a href='<c:url value="/dance/user/signUp/${lesson.id}"/>'
                   onclick="return confirm('Czy chesz zapisać się na zajęcia?')">Zapisz się</a></td>
            </sec:authorize>
            <th scope="row">${lesson.name}</th>
            <td>${lesson.beginTime.toLocalDate()} ${lesson.beginTime.toLocalTime()} </td>
            <td>${lesson.state}</td>
            <td>${lesson.accessNumber}</td>
            <td>${lesson.level}</td>
            <td>${lesson.place}</td>
            <td>${lesson.trainer.lastName}</td>
            <td>${lesson.slots}/${lesson.participants.size()}</td>
            <td></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
