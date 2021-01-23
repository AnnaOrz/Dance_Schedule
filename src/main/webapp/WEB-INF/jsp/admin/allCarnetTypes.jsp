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
    <h3> ${carnetType.description} </h3>
    <a href='<c:url value="/article/delete/${carnetType.id}"/>'
    onclick="return confirm('Czy na pewno chcesz usunąć typ karnetów?')">usuń typ karnetu</a>
    <a href='<c:url value="/article/edit/${carnetType.id}"/>'>Edytuj</a> <br/>
</c:forEach>
</body>
</html>
