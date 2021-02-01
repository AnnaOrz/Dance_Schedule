
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edycja użytkownika</title>
    <meta charset="UTF-8">
</head>
<body>
<%@ include file="../fragments/topMenu.jsp" %>

<form:form method="post" modelAttribute="user" >

    Imię: <form:input path="firstName" value="${user.firstName}"/> <br />
    <form:errors path="firstName" /> <br/>
    Nazwisko: <form:input path="lastName" value="${user.lastName}"/> <br />
    <form:errors path="lastName" /> <br/>
    Email: <form:input path="email" value="${user.email}" /> <br />
    <form:errors path="email" /> <br/>
    Hasło: <form:input path="password" type="password" value="${user.password}" /> <br/>
    <form:errors path="password" /> <br/>
    Rola: <form:input path="role" value="${user.role}"/>
    <form:errors path="role" />
    Aktywowany <form:input path="enabled" value="${user.enabled}" />
    <input type="submit" value="save" />

</form:form>
</body>
</html>
