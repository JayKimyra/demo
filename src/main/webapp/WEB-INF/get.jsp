<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@ page import="com.example.demo.hibernate.entityHelpers.StreetHelper" %>
<%@ page import="com.example.demo.hibernate.entityHelpers.UserHelper" %>
<%@ page import="com.example.demo.hibernate.entities.Street" %>
<% List<Street> streets = StreetHelper.getFullList(); List<User> users = UserHelper.getFullList(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Получение</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="resources/style.css">
    <link rel="stylesheet" href="resources/select2/dist/css/select2.min.css">
</head>
<body class="w-100">
<%@include file="header.jsp" %>
<hr class="my-1">
<div class="container-fluid main">
    <div class="row padding h-100">
        <div class="col-md-9 winds-right winds">
            <table class="table table-bordered">
                <thead>
                <tr class="tr-text-center">
                    <th scope="col">#</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Улица</th>
                    <th scope="col">Дом</th>
                    <th scope="col">Квартира</th>
                    <th scope="col">ФИО работника</th>
                    <th scope="col">Действие</th>
                </tr>
                </thead>
                <tbody id="posts">
                </tbody>
            </table>
        </div>

        <div class="col-md-3 winds-left winds">
            <div class="container-fluid text-center">
                <h6>Фильтры</h6>
            </div>
            <br>

            <form method="post" id="form">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-2 d-flex justify-content-center text-center">Выберите дату начала: </div>
                        <div class="col-md-10 d-flex align-items-center">
                            <div class="form-group w-100">
                                <div class="container-fluid">
                                    <input class="form-control" type="date" name="date-begin" id="date-begin">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-2 d-flex justify-content-center text-center">Выберите дату окончания: </div>
                        <div class="col-md-10 d-flex align-items-center">
                            <div class="form-group w-100">
                                <div class="container-fluid">
                                    <input class="form-control" type="date" name="date-end" id="date-end">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-2 d-flex justify-content-center align-items-center">Улица</div>
                        <div class="col-md-10 d-flex align-items-center">
                            <div class="form-group w-100">
                                <div class="container-fluid">
                                    <select class="select-street js-select2 w-100 form-control" name="street-id" placeholder="Выберите улицу" id="street-id">
                                        <option value=""></option>
                                        <option value="all">Все</option>
                                        <% for (int i = 0; i < streets.size(); i++) { %>
                                        <option value="<%= i %>"><%= streets.get(i).getName() %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-2 d-flex justify-content-center text-center">ФИО работника</div>
                        <div class="col-md-10 d-flex align-items-center">
                            <div class="form-group w-100">
                                <select class="select-employee js-select2 w-100 form-control" name="user-id" placeholder="Выберите сотрудника" id="user-id">
                                    <option value=""></option>
                                    <option value="all">Все</option>
                                    <% for (int i = 0; i < users.size(); i++) { %>
                                    <option value="<%= i %>"><%= users.get(i).getFirstname() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="d-flex justify-content-center">
                    <button onclick="getPosts()" type="submit" class="btn btn-send">Отправить</button>
                </div>
            </form>
        </div>
    </div>
</div>
<hr class="my-1">
<%@include file="footer.jsp" %>
<script src="./js/functions.js"></script>
<script src="./select2/dist/js/select2.min.js"></script>
<script src="./select2/dist/js/i18n/ru.js"></script>
</body>
</html>