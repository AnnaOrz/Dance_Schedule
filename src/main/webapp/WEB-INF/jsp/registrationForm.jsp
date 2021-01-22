
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Rejestracja</title>
    <meta charset="UTF-8">
</head>
<body>
<%@ include file="fragments/sideMenu.jsp" %>

<form:form method="post" modelAttribute="user" >

    FirstName: <form:input path="firstName" /> <br />
    <form:errors path="firstName" /> <br/>
    UserName: <form:input path="lastName" /> <br />
    <form:errors path="lastName" /> <br/>
    Email: <form:input path="email" /> <br />
    <form:errors path="email" /> <br/>
    Password: <form:input path="password" /> <br/>
    <form:errors path="password" /> <br/>
    <input type="submit" value="save" />

</form:form>
</body>
</html>
