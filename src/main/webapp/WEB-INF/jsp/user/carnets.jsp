<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moje karnety</title>
</head>
<body>
<h1> Moje karnety</h1>
<c:forEach items="${allUserCarnets}" var="carnet">
    <h3>
            Pozostałe wejścia: ${carnet.entrances}
            <%--${carnet.startDate}--%>
           Koniec karneu: ${carnet.expireDate}
           Poziom dostępu: ${carnet.accessNumber}

    </h3>
</c:forEach>
</body>
</html>