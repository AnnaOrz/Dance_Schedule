<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<html>
<head>
    <title>Lista użytkowników</title>
</head>
<body>
<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>
<h1> Lista użytkowników </h1>
${message}
<table class="table table-light table-striped">
    <thead>
    <tr>
        <th scope="col">Edycja użytkownika</th>
        <th scope="col">Id w bazie</th>
        <th scope="col">Email</th>
        <th scope="col">Imię</th>
        <th scope="col">Nazwisko</th>
        <th scope="col">Rola</th>
        <th scope="col">Aktywowany</th>
    </tr>
    </thead>
<c:forEach items="${allUsers}" var="user">
    <tr>
        <td><a href='<c:url value="/dance/admin/users/edit/${user.id}"/>'>Edytuj</a></td>
        <th scope="row">${user.id}</th>
        <td>${user.email}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.role}</td>
        <td>${user.enabled}</td>
    </tr>


</c:forEach>


</body>
</html>
