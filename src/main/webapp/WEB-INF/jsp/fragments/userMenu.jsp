<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
<sec:authentication var="principal" property="principal"/>
<html>

<body>
<nav class="navbar navbar-nav navbar-dark bg-dark">
    <div>
        <a  href="<c:url value="/"/>">Strona główna</a>
        <div >
            <ul>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/schedule"/>">Grafik</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/dance/user/buy"/>">Kup
                        karnet</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/dance/user/carnets"/>">Moje
                        karnety</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/dance/user/classes"/>">Moje zajęcia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/dance/user/data"/>">Moje
                        dane</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"  href="<c:url value="/logout"/>">Wyloguj</a>
                </li>
                <li class="nav-item">Zalogowany jako ${principal.username} </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
