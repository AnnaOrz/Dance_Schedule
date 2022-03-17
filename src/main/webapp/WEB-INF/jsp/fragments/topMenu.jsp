<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html lang="pl">
<body>
<nav class="navbar topMenu">

        <div>
            <ul>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/"/>">Strona główna</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/registration"/>">Rejestracja</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/login"/>">Logowanie</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/schedule"/>">Grafik zajęć</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/contact"/>">Kontakt</a>
                </li>
            </ul>
        </div>
</nav>
</body>


</html>
