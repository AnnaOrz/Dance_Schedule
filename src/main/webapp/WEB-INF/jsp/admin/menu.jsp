<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Menu</title>

</head>
<body>
<a class="nav-link" href="<c:url value="/dance/admin/Users"/>">
    <span>Użytkownicy</span>
</a> <br/>

<a class="nav-link" href="<c:url value="/dance/admin/Carnets"/>">
    <span>Karnety użytkowników</span>
</a> <br/>

<a class="nav-link" href="<c:url value="/dance/admin/CarnetTypes"/>">
    <span>Typy karnetów</span>
</a> <br/>
<a class="nav-link" href="<c:url value="xxxxxxx"/>">
    <span>Zajęcia</span>
</a> <br/>
<a class="nav-link" href="<c:url value="xxxxxxx"/>">
    <span>Grafik</span>
</a> <br/>

</body>
</html>
