<%--
  Created by IntelliJ IDEA.
  User: talay
  Date: 10/28/2023
  Time: 11:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MultiplicationTable</title>
    <style>
        td {
            border-spacing: 16px;
            padding: 5px;
            height: 16px;
            text-align: center;
        }

        table {
            border: 2px solid darkcyan;
        }

        .number {
            text-align: right;
        }
    </style>
</head>
<body>
<h3>Multiplication Table :: </h3>
<hr>

<c:if test="${error == null}">
    <table>
        <tr>
            <td colspan="5">MT for ${param.number} </td>
        </tr>
        <c:forEach begin="1" end="12" var="n">
            <c:choose>
                <c:when test="${n%3 == 1}">
                    <c:set var="bg" scope="page" value="white"/>
                </c:when>
                <c:when test="${n%3 == 2}">
                    <c:set var="bg" scope="page" value="lightgray"/>
                </c:when>
                <c:otherwise>
                    <c:set var="bg" scope="page" value="gray"/>
                </c:otherwise>
            </c:choose>
            <tr style="background-color: ${bg} ">
                <td class="number"> ${param.number}</td>
                <td> x</td>
                <td class="number"> ${n} </td>
                <td> =</td>
                <td class="number"> ${n * param.number}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${error != null}">
    <h3 style="color: red">
        Error : ${requestScope.error}
        (${param.number!=null || param.number=='' ? "Null/Empty" : param.number})<%--httpserver is in servlet request --%>
    </h3>
</c:if>

<hr>
Your Browser : ${header['User-Agent']}
</body>
</html>
