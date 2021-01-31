<%--
  Created by IntelliJ IDEA.
  User: anda
  Date: 21.01.2021
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html lang="pl">

<body>
<header>
    <div >
        <ul >
            <li class="nav-item">
                <a class="nav-link color-header" href="<c:url value="/registration"/>">Rejestracja</a>
            </li>
            <li class="nav-item">
                <a class="nav-link color-header"  href="<c:url value="/login"/>">Logowanie</a>
            </li>
            <li class="nav-item">
                <a class="nav-link color-header"  href="<c:url value="/schedule"/>">Grafik zajęć</a>
            </li>
            <li class="nav-item">
                <a class="nav-link color-header"  href="<c:url value="/contact"/>">Kontakt</a>
            </li>
        </ul>
    </div>
</header>
<hr>
</body>