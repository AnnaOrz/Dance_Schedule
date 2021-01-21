<%--
  Created by IntelliJ IDEA.
  User: anda
  Date: 19.01.2021
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Wybierz karnet</title>
</head>
<body>
Entrances: <form:input path="entrances" /> <br />
<form:errors path="entrances" /> <br/>
startDate: <form:input type="date" path="startDate" /> <br />
<form:errors path="startDate" /> <br/>
accessNumber: <form:input path="accessNumber" /> <br />
<form:errors path="accessNumber" /> <br/>
price: <form:input path="price" /> <br />
<form:errors path="price" /> <br/>
<input type="submit" value="save" />


</body>
</html>
