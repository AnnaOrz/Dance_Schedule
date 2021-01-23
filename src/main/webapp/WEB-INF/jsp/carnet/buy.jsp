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
<form:form method="post" modelAttribute="carnet" >
Data rozpoczęcia: <form:input type="date" path="startDate" /> <br />
<form:errors path="startDate" /> <br/>
Typ karnetu: <form:select path="carnetType.id" items="${allCarnetTypes}" itemLabel="description" itemValue="id"/> <br />
    <%--//carnetType to obiekt a daję samo id..--%>

<input type="submit" value="save" />

</form:form>
</body>
</html>
