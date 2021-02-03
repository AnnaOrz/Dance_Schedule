<%--
  Created by IntelliJ IDEA.
  User: anda
  Date: 19.01.2021
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Dodaj karnet</title>
    <meta charset="UTF-8">
</head>
<body>

<form:form method="post" modelAttribute="carnet" >
    <h1>Utwórz typ karnetu</h1>

    Wejścia: <form:input path="entrances" /> <br />
    <form:errors path="entrances" /> <br/>
    Poziom cenowy: <form:input path="accessNumber" /> <br />
    <form:errors path="accessNumber" /> <br/>
    Cena: <form:input path="price" /> <br />
    <form:errors path="price" /> <br/>
    Opis: <form:input path="description" /> <br />
    <form:errors path="description" /> <br/>
    <input type="submit" value="save" />
    <form:hidden path="id" value="${carnet.id}"/> <%--będzie ważne do edycji--%>
    <form:hidden path="available" value="true"/>

</form:form>
</body>

</html>
