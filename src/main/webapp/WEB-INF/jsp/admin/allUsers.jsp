<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klienci</title>
</head>
<body>
<h1> Klienci </h1>
<c:forEach items="${allUsers}" var="user">
    <h3> ${user.email}  ${user.firstName} ${user.lastName}
        <a href='<c:url value="/dance/user/users/activate/${user.id}"/>' >Aktywuj</a>
        <a href='<c:url value="/dance/admin/users/edit/${user.id}"/>'>Edytuj</a>
    </h3>
</c:forEach>
${message}

</body>
</html>
