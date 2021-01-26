
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Wybierz karnet</title>
</head>
<body>
<form:form method="post" modelAttribute="carnetDto" >
Data rozpoczęcia: <form:input type="date" path="startDate" /> <br />
<form:errors path="startDate" /> <br/>
Typ karnetu: <form:select path="carnetTypeId" items="${allAvailableCarnetTypes}" itemLabel="description" itemValue="id"/> <br />

<input type="submit" value="save" />

</form:form>
</body>
</html>
