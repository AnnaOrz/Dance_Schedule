<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html lang="pl">
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/"/>">Strona główna</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/registration"/>">Rejestracja</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/login"/>">Logowanie</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/schedule"/>">Grafik zajęć</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/contact"/>">Kontakt</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
