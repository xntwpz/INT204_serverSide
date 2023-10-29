<%--
  Created by IntelliJ IDEA.
  User: talay
  Date: 10/19/2023
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Subject List::</title>
</head>
<body>
    <table>
        <tr>
            <td>Subject Code</td>
            <td>Title</td>
            <td>Credit</td>
        </tr>
        <c:forEach items="${subjects}" var="subject">

        <tr>
            <td>${subject.id}</td>
            <td>${subject.title}</td>
            <td>${subject.credit}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>