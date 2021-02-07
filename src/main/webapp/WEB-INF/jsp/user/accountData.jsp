
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Moje dane</title>
    <link href='<c:url value="/style.css"/>' rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../fragments/topMenu.jsp" %>
<h1> Moje dane </h1>
<dl class="row">
    <dt class="col-sm-3">Imię</dt>
    <dd class="col-sm-9"> ${user.firstName}</dd>
    <dt class="col-sm-3">Nazwisko:</dt>
    <dd class="col-sm-9">${user.lastName}</dd>
    <dt class="col-sm-3">Email</dt>
    <dd class="col-sm-9">${user.email} </dd>
    <dt class="col-sm-3"> <a href='<c:url value="/dance/user/data/edit/${user.id}"/>'>Edytuj</a> </dt>
</dl>

</body>
</html>

