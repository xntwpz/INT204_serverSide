<%--
  Created by IntelliJ IDEA.
  User: talay
  Date: 10/29/2023
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Registered</title>
</head>
<body style="margin-left: 100px ; background-color: ${cookie.bg_color_cookie.value}">
<h3>Course Register History</h3>
<hr>
<c:set var="link" value="index.jsp"/>
<c:if test="${message != null}">
    <span style="color: #781212 ; font-size: larger">${message}</span><br>
    <c:set var="link" value="course-list"/>
</c:if>
<c:if test="${message==null}">
    <c:forEach items="${courseRegistered.history}" var="entry">
        <h3>${semesters[entry.key]}</h3>
        <hr>
        <c:forEach items="${entry.value}" var="subject">
            ${subject.subjectId} , ${subject.title},${subject.credit}<br>
        </c:forEach>
        ------------------------------------<br><br>
    </c:forEach>
</c:if>
<hr>
<a href="${link}">
    <button> OK</button>
</a>
</body>
</html>
