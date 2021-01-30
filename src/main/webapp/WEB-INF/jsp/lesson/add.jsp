<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Miejsca" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Utwórz lekcję</title>
</head>
<body>


<form:form method="post" modelAttribute="lesson" >
    <div>To jest form dla admina do tworzenia lekcji</div>

    Nazwa: <form:input path="name" /> <br />
    <form:errors path="name" />
    Liczba uczestników <form:input path="slots" /> <br />
    <form:errors path="slots" /> <br/>
    Poziom cenowy: <form:input path="accessNumber" /> <br /> <%--OPCJE DO WYBORU 1 2 --%>
    <form:errors path="accessNumber" /> <br/>
    Czas rozpoczęcią: <form:input type="datetime-local" path="beginTime" /> <br />
    <form:errors path="beginTime" /> <br/>
    Miejsce: <form:input path="place" /> <br />
    <form:errors path="place" /> <br/>
    Trener: <form:input path="trainer" /> <br /> <%--:LISTA ROZWIJANA!!!--%>
    <form:errors path="trainer" /> <br/>
    Poziom: <form:input path="level" /> <br /> <%--Lista rozwijana z 4 opcjami--%>
    <form:errors path="level" /> <br/>
    <input type="submit" value="save" />
    <form:hidden path="state" value="active" /> <br/>
    <form:hidden path="id" value="${lesson.id}"/>


</form:form>

</body>
</html>
