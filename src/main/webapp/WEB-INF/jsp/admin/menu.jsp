<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<html>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/dance/admin/users"/>">Użytkownicy</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/dance/admin/carnets"/>">Karnety
                        użytkowników</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/dance/admin/carnetTypes"/>">Typy
                        karnetów</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="<c:url value="/dance/admin/lessons"/>">Zajęcia</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
