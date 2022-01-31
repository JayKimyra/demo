<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
    <link rel="stylesheet" href="./style.css">
</head>
<body class="w-100">
<%@include file="./header.jsp" %>
<hr class="my-1">
<div class="container-fluid">
    <div class="row main">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="cont-log-form">

                    <form method="post" id="form">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2 d-flex justify-content-center">
                                    <img src="./images/login.png" alt="" height="50px">
                                </div>
                                <div class="col-md-10 d-flex align-items-center">
                                    <div class="form-group w-100">
                                        <input name="login" type="text" class="form-control" id="login" aria-describedby="emailHelp" placeholder="Логин">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2 d-flex justify-content-center">
                                    <img src="./images/lock_0.png" id="lock" height="50px">
                                </div>
                                <div class="col-md-10 d-flex align-items-center">
                                    <div class="form-group w-100 password">
                                        <input name="password" type="password" class="form-control field" id="pass-input" placeholder="Пароль">
                                        <a href="#" class="pass-control" id="pass-control"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="d-flex justify-content-center">
                            <button onclick="auth()" type="submit" class="btn btn-send">Отправить</button>
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
<%@include file="./footer.jsp" %>
<script src="./js/functions.js"></script>
</body>
</html>