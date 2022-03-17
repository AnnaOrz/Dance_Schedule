<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<html>

<body>
<nav class="adminMenu">
        <div>
            <ul>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/dance/admin/users"/>">Użytkownicy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/dance/admin/carnets"/>">Karnety użytkowników</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/dance/admin/carnetTypes"/>">Typy karnetów</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/dance/admin/lessons"/>">Zajęcia</a>
                </li>
            </ul>
        </div>
</nav>

</body>
</html>
