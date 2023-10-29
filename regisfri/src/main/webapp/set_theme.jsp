<%--
  Created by IntelliJ IDEA.
  User: talay
  Date: 10/30/2023
  Time: 12:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select Your Theme</title>
</head>
<body>
    <h2>Select Your Theme::</h2><hr>
    <form action="set-theme" method="get">
        <p>
            <input type="radio" name="bgColor" value="orange">
        <button style="background-color: orange">orange</button>
        </p>
        <p>
            <input type="radio" name="bgColor" value="palevioletred">
        <button style="background-color: palevioletred">palevioletred</button>
        </p>
        <p>
            <input type="radio" name="bgColor" value="darkolivegreen">
        <button style="background-color: darkolivegreen">darkolivegreen</button>
        </p>
        <p>
            <input type="radio" name="bgColor" value="palegoldenrod">
        <button style="background-color: palegoldenrod">palegoldenrod</button>
        </p>
        <hr>
        <input type="submit" value="OK">
    </form>
</body>
</html>
