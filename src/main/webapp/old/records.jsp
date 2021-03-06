<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    <script>
        $(document).on("click", "#test", function() {
            $.post("someservlet", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                $("#info").html(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
            });
        });
    </script>
</head>
<button id="somebutton">сюда!</button>
<body class="container-fluid">
<div class="container-fluid" style="margin-top: 20px">
    <div class="row padding">
        <div class="col-md-9 container winds">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Улица</th>
                    <th scope="col">Дом</th>
                    <th scope="col">Квартира</th>
                    <th scope="col">ФИО работника</th>
                    <th scope="col">Действие</th>
                </tr>
                </thead>
                <tbody id ="info" >
                </tbody>
            </table>
        </div>

        <div class="col-md-2 container winds">
            <form id="upload-container" method="POST" action="/someservlet" enctype="multipart/form-data">
                <br>
                <div>
                    <p>Выберите дату: <input type="date" name="calendar">
                </div>
                <br>
                <div>
                    <label for="location-street-id">Улица</label>
                    <select class="chosen-select" name="location-street-id" tabindex="2" id="location-street-id" data-placeholder="Выберите улицу">
                        <option value=""></option>
                        <option value="367">Абаза</option>
                        <option value="340">Абакан</option>
                        <option value="369">Абдулино</option>
                        <option value="370">Абинск</option>
                        <option value="371">Агидель</option>
                    </select>
                </div>
                <br>
                <div>
                    <label for="worker-name">ФИО работника</label>
                    <select class="chosen-select-worker" name="location-street-id" tabindex="2" id="worker-name" data-placeholder="Выберите работника">
                        <option value=""></option>
                        <option value="367">Иванов</option>
                        <option value="340">Петров</option>
                        <option value="369">Габов</option>
                        <option value="370">Егоров</option>
                        <option value="371">Шараускас</option>
                    </select>
                </div>


                <br>

                <button id="test" type="submit">Показать</button>
            </form>
        </div>
    </div>
</div>
s
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen_v1.8.7/chosen.min.css">
<script src="${pageContext.request.contextPath}/chosen_v1.8.7/chosen.jquery.min.js"></script>

<script src="../js/chosen/docsupport/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="../js/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="../js/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/chosen/docsupport/init.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
