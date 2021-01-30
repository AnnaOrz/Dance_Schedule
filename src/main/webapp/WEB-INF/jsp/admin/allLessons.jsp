<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zajęcia</title>
</head>
<body>
<h1> Zajęcia </h1>
<a href='<c:url value="/dance/admin/lessons/add"/>'> Dodaj nowe zajęcia</a>
<c:forEach items="${allLessons}" var="lesson">
    <h3> ${lesson.name}
        <a href='<c:url value="/dance/admin/lessons/cancel${lesson.id}"/>'
           onclick="return confirm('Czy na pewno chcesz odwołać lekcję?')">Odwołaj</a>
        <a href='<c:url value="/dance/admin//lessons/edit/${lesson.id}"/>'>Edytuj</a>
    </h3>
</c:forEach>
${message}
</body>
</html>