<%@ page import="com.example.demo.hibernate.entities.User" %>
<%@page import="java.lang.String"%>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.demo.hibernate.entities.Role" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="header-footer">
    <div>
        <% Role role;
            User user = ((User) request.getSession().getAttribute("user"));
            role = user != null ? user.getRole() : null;
        %>
        <% if (Objects.equals(role, Role.WORKER) || Objects.equals(role, Role.ADMIN)) {%>
        <a href="/get" type="button" class="btn btn-outline-secondary ms-2">Просмотр записей</a>
        <a href="/send" type="button" class="btn btn-outline-secondary ms-2">Отправить файлы</a>
        <% } %>

        <% if (Objects.equals(role, Role.ADMIN)) { %>
        <a href="/registration" type="button" class="btn btn-outline-secondary ms-2">Регистрация сотрудника</a>
        <% } %>
    </div>
</div>