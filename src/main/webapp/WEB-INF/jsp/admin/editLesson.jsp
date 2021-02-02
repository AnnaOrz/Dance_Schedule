<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../fragments/topMenu.jsp" %>
<%@ include file="menu.jsp" %>

<form:form method="post" modelAttribute="lesson" >
    <dl class="row">
        <dt class="col-sm-3">Nazwa</dt>
        <dd class="col-sm-9">
            <form:input path="name" value="${lesson.name}"/>
            <form:errors path="name" /></dd>
        <dt class="col-sm-3">Data i godzina rozpoczęcia</dt>
        <dd class="col-sm-9">
            <form:input path="beginTime" type="datetime-local"  value="${lesson.beginTime}"/>
            <form:errors path="beginTime" /> </dd>
        <dt class="col-sm-3">Poziom</dt>
        <dd class="col-sm-9">
            <form:input path="level" value="${lesson.level}" />
            <form:errors path="level" />  </dd>

        <dt class="col-sm-3">Miejsce</dt>
        <dd class="col-sm-9">
            <form:input path="place" value="${lesson.place}" />
            <form:errors path="place" /> </dd>
        <dt class="col-sm-3">Trener/dt>
        <dd class="col-sm-9">
            <form:input path="trainer" value="${lesson.trainer}"/>
            <form:errors path="trainer" /></dd>
        <dt class="col-sm-3">Poziom wejścia</dt>
        <dd class="col-sm-9">
            <form:input path="accessNumber" value="${lesson.accessNumber}" />
            <form:errors path="accessNumber" /> </dd>
        <dt class="col-sm-3">
            <input class="btn btn-outline-primary btn-lg" type="submit" value="Zapisz zmiany"> </dt>
</form:form>