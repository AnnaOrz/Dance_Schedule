<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Menu</title>

</head>
<body>
<ul>
    <li>
<a class="nav-link" href="<c:url value="/dance/admin/users"/>">
    <span>Użytkownicy</span>
</a> </li>
    <li>
<a class="nav-link" href="<c:url value="/dance/admin/carnets"/>">
    <span>Karnety użytkowników</span>
</a> <br/>
    <li>
<a class="nav-link" href="<c:url value="/dance/admin/carnetTypes"/>">
    <span>Typy karnetów</span>
</a> </li>
    <li>
<a class="nav-link" href="<c:url value="/dance/admin/lessons"/>">
    <span>Zajęcia</span>
</a> </li>
    <li>
<a class="nav-link" href="<c:url value="/schedule"/>">
    <span>Grafik</span>
</a> </li>
</ul>
</body>
</html>
