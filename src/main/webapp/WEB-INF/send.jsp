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
<%@include file="./header.jsp" %>
<hr class="my-1">
<div class="container-fluid">
    <div class="row main">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="cont-log-form">
                <form method="post" id="form" enctype="multipart/form-data">
                    <div class="container-fluid">
                        <div class="form-group w-100">
                            <input name="files" type="file" class="form-control" id="files" multiple>
                        </div>
                    </div>
                    <br>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-2 d-flex justify-content-center align-items-center">Улица</div>
                            <div class="col-md-10 d-flex align-items-center">
                                <div class="form-group w-100">
                                    <div class="container-fluid">
                                        <select class="js-select2 w-100" name="street" placeholder="Выберите улицу" id="street">
                                            <option value=""></option>
                                            <option value="1">Абитуриентский переулок</option>
                                            <option value="2">Абрамовский переулок</option>
                                            <option value="3">Аввакумовский сквер</option>
                                            <option value="4">микрорайон Авиагородок</option>
                                            <option value="5">Авиаторный проулок</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-2 d-flex justify-content-center align-items-center">Дом</div>
                            <div class="col-md-10 d-flex align-items-center">
                                <div class="form-group w-100">
                                    <input name="home" type="text" class="form-control" id="home" placeholder="Дом">
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-2 d-flex justify-content-center align-items-center">Квартира</div>
                            <div class="col-md-10 d-flex align-items-center">
                                <div class="form-group w-100">
                                    <input name="flat" type="text" class="form-control" id="flat" placeholder="Квартира">
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="d-flex justify-content-center">
                        <button onclick="send()" type="submit" class="btn btn-send">Отправить</button>
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
<script src="./select2/dist/js/select2.min.js"></script>
<script src="./select2/dist/js/i18n/ru.js"></script>
</body>
</html>