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
            <table class="table table-light table-striped">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Nazwa zajęć</th>
                    <th scope="col">Data i godzina rozpoczęcia</th>
                    <th scope="col">Status</th>
                    <th scope="col">Poziom wejścia</th>
                    <th scope="col">Poziom zajęć</th>
                    <th scope="col">Miejsce</th>
                    <th scope="col">Trener</th>
                    <th scope="col">Ilość uczestników</th>
                    <th scope="col">Ilość miejsc</th>


                </tr>
                </thead>
                <c:forEach items="${userClasses}" var="lesson">
                <tr>
                    <td> <a href='<c:url value="/dance/user/optOut/${lesson.id}"/>'
                            onclick="return confirm('Czy na pewno chcesz wypisać się z lekcji?')">Wypisz się z lekcji</a></td></td>
                    <td>${lesson.name}</td>
                    <td>${lesson.beginTime.toLocalDate()} ${lesson.beginTime.toLocalTime()} </td>
                    <td>${lesson.state}</td>
                    <td>${lesson.accessNumber}</td>
                    <td>${lesson.level}</td>
                    <td>${lesson.place}</td>
                    <td>${lesson.trainer}</td>
                    <td>${lesson.participants.size()}</td>
                    <td>${lesson.slots}</td>

                </tr>
                </c:forEach>

</body>
</html>
