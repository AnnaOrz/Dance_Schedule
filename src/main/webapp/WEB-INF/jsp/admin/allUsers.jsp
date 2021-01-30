<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klienci</title>
</head>
<body>
<h1> Zajęcia </h1>
<c:forEach items="${allUsers}" var="user">
    <h3> ${user.name}
        <a href='<c:url value="/dance/trainer/users/active${user.id}"/>' >Aktywuj</a>
        <a href='<c:url value="/dance/admin//users/edit/${user.id}"/>'>Edytuj</a>
    </h3>
</c:forEach>
${message}

</body>
</html>
