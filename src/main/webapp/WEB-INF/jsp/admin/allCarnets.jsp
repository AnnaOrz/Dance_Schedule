<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Karnety użytkowników</h1>
<c:forEach items="${allCarnets}" var="carnet">
    <h3>    ${carnet.id}
            ${carnet.entrances}
            ${carnet.startDate}
            ${carnet.expireDate}
            ${carnet.accessNumber}
            ${carnet.userId}
        <a href='<c:url value="/dance/admin/carnets/delete/${carnet.id}"/>'  <%--czy rzeczywiście chcę je usuwać?--%>
           onclick="return confirm('Czy na pewno chcesz usunąć ten karnet?')">Usuń</a>
        <a href='<c:url value="/dance/admin/carnets/edit/${carnet.id}"/>'>Edytuj</a>
    </h3>
</c:forEach>
${message}
</body>
</html>
