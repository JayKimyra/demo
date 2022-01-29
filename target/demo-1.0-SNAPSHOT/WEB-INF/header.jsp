<%@page import="java.lang.String"%>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.demo.hibernate.entities.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="header-footer">

    <% String role = "";
        try {
            User user = ((User) request.getSession().getAttribute("user"));
            role = user.getRole();
        } catch (Exception e)
        {
            System.out.println(e);
        }%>
    <% if (Objects.equals(role, "admin")) { %>
    <div>
        <a href="/get" type="button" class="btn btn-outline-secondary ms-2">Просмотр записей</a>
        <a href="/send" type="button" class="btn btn-outline-secondary ms-2">Отправить файлы</a>
        <a href="/registration" type="button" class="btn btn-outline-secondary ms-2">Регистрация сотрудника</a>
    </div>
    <% }%>
</div>