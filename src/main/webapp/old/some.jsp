<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>SO question 4112686</title>
  <meta charset="utf-8" />
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>
    $(document).on("click", "#somebutton", function() {
      $.get("someservlet", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
        $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
      });
    });
  </script>
  <script>
    $(document).on("click", "#somebutton2", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
      $.get("someservlet2", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
        $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
      });
    });
  </script>
</head>
<body>
<button id="somebutton">сюда!</button>
<button id="somebutton2">press here</button>
<div id="somediv"></div>
</body>
</html>