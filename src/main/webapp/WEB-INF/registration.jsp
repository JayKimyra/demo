<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Отправка</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
  <link rel="stylesheet" href="./style.css">
  <link rel="stylesheet" href="./select2/dist/css/select2.min.css">
</head>
<body class="w-100">
<%@include file="header.jsp" %>
<hr class="my-1">
<div class="container-fluid">
  <div class="row main">
    <div class="col-md-3"></div>
    <div class="col-md-6">
      <div class="cont-log-form">
        <form method="post" id="form">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-2 d-flex justify-content-center align-items-center">Имя</div>
              <div class="col-md-10 d-flex align-items-center">
                <div class="form-group w-100">
                  <input name="first-name" type="text" class="form-control field" id="firstName" placeholder="Имя">
                </div>
              </div>
            </div>
          </div>
          <br>
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-2 d-flex justify-content-center align-items-center">Фамилия</div>
              <div class="col-md-10 d-flex align-items-center">
                <div class="form-group w-100">
                  <input name="last-name" type="text" class="form-control field" id="lastName" placeholder="Фамилия">
                </div>
              </div>
            </div>
          </div>
          <br>
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-2 d-flex justify-content-center align-items-center">Логин</div>
              <div class="col-md-10 d-flex align-items-center">
                <div class="form-group w-100">
                  <input name="login" type="text" class="form-control field" id="login" placeholder="Логин">
                </div>
              </div>
            </div>
          </div>
          <br>
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-2 d-flex justify-content-center align-items-center">Пароль</div>
              <div class="col-md-10 d-flex align-items-center">
                <div class="form-group w-100">
                  <input name="password" type="text" class="form-control field" id="password" placeholder="Пароль">
                </div>
              </div>
            </div>
          </div>
          <br>
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-2 d-flex justify-content-center align-items-center">Доступ</div>
              <div class="col-md-10 d-flex align-items-center">
                <div class="form-group w-100">
                  <div class="container-fluid">
                    <select class="select-role js-select2 w-100 form-control field" name="role" placeholder="Доступ" id="role">
                      <option value=""></option>
                      <option value="ADMIN">Админ</option>
                      <option value="MANAGER">Редактор</option>
                      <option value="WORKER">Сотрудник</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <br>
          <div class="d-flex justify-content-center">
            <button onclick="registration()" type="submit" class="btn btn-send">Отправить</button>
          </div>
        </form>
        <br>
        <div class="w-100 d-flex justify-content-center err-style" id="error-field"></div>
      </div>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>
<hr class="my-1">
<%@include file="footer.jsp" %>
<script src="./js/functions.js"></script>
<script src="./select2/dist/js/select2.min.js"></script>
<script src="./select2/dist/js/i18n/ru.js"></script>
</body>
</html>