
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">

<html>
<head>
    <title>Zakup karnetu</title>
</head>
<body>
<%@ include file="../fragments/userMenu.jsp" %>

<form:form method="post" modelAttribute="carnetDto" >
<dl class="row">
    <dt class="col-sm-3">Data rozpoczęcia</dt>
    <dd class="col-sm-9">
        <form:input type="date" path="startDate" />
        <form:errors path="startDate" /> </dd>
        <dt class="col-sm-3">Typ karnetu</dt>
        <dd class="col-sm-9">
            <form:select path="carnetTypeId" items="${allAvailableCarnetTypes}" itemLabel="description" itemValue="id"/>
            <form:errors path="carnetTypeId" /> </dd>
    <dd class="col-sm-9"> <input type="submit" value="Zapisz" /> </dd>

</form:form>
</body>
</html>
