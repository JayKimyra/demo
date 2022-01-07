<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.hibernate.City" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="./js/chosen/chosen.css">
    <meta charset="utf-8" />
    <title>Главная</title>
</head>
<body>

<form id="upload-container" method="POST" action="${pageContext.request.contextPath}/pdf-request" enctype="multipart/form-data">
    <div>
        <input id="file-input" type="file" name="files" multiple>
        <label for="file-input">Выберите файл</label>
    </div>
    <br>
    <div>
        <select class="chosen-select" name="location-street-id" tabindex="2" id="location-street-id" data-placeholder="Выберите город">
            <option value=""></option>
            <option value="367">Абаза</option>
            <%
                List<City> cities = com.example.demo.hibernate.CityHelper.getFullList();
                for (City city:cities) {
                    out.print("<option value=\" " + city.getId() + "\"> "+ city.getName() + "</option>");
                }
            %>
        </select>
        <label for="location-street-id">Улица</label>
    </div>
    <div>
        <input id="location-home" type="text" name="location-home">
        <label for="location-home">Дом</label>
    </div>
    <div>
        <input id="location-flat" type="text" name="location-flat">
        <label for="location-flat">Квартира</label>
    </div>



    <script src="./js/chosen/docsupport/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="./js/chosen/chosen.jquery.js" type="text/javascript"></script>
    <script src="./js/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
    <script src="./js/chosen/docsupport/init.js" type="text/javascript" charset="utf-8"></script>
    <button type="submit">SEND!</button>
</form>
</body>
</html>