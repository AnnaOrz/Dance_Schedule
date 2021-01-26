<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Typy karnetów</title>
</head>
<body>
<h1> Typy karnetów </h1>
<a href='<c:url value="/dance/admin/addCarnetType"/>'> Dodaj nowy karnet</a>
<c:forEach items="${allCarnetTypes}" var="carnetType">
    <h3> ${carnetType.description}
    <a href='<c:url value="/dance/admin/deleteCarnetType/${carnetType.id}"/>'
    onclick="return confirm('Czy na pewno chcesz usunąć typ karnetów?')">Usuń</a>
    <a href='<c:url value="/dance/admin/edit/${carnetType.id}"/>'>Edytuj</a>
    </h3>
</c:forEach>
${message}
</body>
</html>
