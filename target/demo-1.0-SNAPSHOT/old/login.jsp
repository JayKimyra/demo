<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body class="w-100">
<%@include file="header.jsp" %>
<hr class="my-1">
<div class="container-fluid">
    <div class="row main">
            <div class="col-md-3"></div>
            <div class="col-md-6" style="background-color: aquamarine">
                <div class="cont-log-form">
                    <form action="/auth" method="post">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2 d-flex justify-content-center">
                                    <img src="./images/login.png" alt="" height="50px">
                                </div>
                                <div class="col-md-10 d-flex align-items-center">
                                    <div class="form-group w-100">
                                        <input type="text" class="form-control" id="login" aria-describedby="emailHelp" placeholder="Логин" name="login">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2 d-flex justify-content-center">
                                    <img src="images/lock_0.png" id="lock" alt="" height="50px" onclick="lock_unlock()">
                                </div>
                                <div class="col-md-10 d-flex align-items-center">
                                    <div class="form-group w-100">
                                        <input type="password" class="form-control" id="password" placeholder="Пароль" name="password">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-light">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-3"></div>
    </div>
</div>
<hr class="my-1">
<%@include file="footer.jsp" %>
<script>
    function lock_unlock() {
        var image = document.getElementById("lock");
        var passInp = document.getElementById("password");

        if (passInp.getAttribute('type') == 'password'){
            image.src = "./images/lock_1.png";
            passInp.setAttribute('type', 'text');
        } else {
            image.src = "./images/lock_0.png";
            passInp.setAttribute('type', 'password');
        }
        return false;
    }
</script>
</body>
</html>