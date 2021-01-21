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
    <div>Uwaga to jest form dla admina do tworzenia karnetów!
    Docelowo ten form nie musi mieć daty rozpoczęcia i zakończenia</div>

    Entrances: <form:input path="entrances" /> <br />
    <form:errors path="entrances" /> <br/>
    accessNumber: <form:input path="accessNumber" /> <br />
    <form:errors path="accessNumber" /> <br/>
    price: <form:input path="price" /> <br />
    <form:errors path="price" /> <br/>
    <input type="submit" value="save" />
    <form:hidden path="id" value="${carnet.id}"/>

</form:form>
</body>

</html>
