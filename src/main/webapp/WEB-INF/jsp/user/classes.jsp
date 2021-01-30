<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anda
  Date: 27.01.2021
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moje zajęcia</title>
</head>
<body>
<h1> Moje zajęcia</h1>
<c:forEach items="${userClasses}" var="class">
    <h3>
        ${class.name} <br>
           Poziom: ${class.level} <br>
        ${class.beginTime} <br>
        Miejsce: ${class.place} <br>


    </h3>
</c:forEach>

</body>
</html>
