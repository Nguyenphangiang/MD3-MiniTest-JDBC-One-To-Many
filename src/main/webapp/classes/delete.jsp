<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/2/2022
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>School Manager</title>
    <link rel="stylesheet" href="mycss.css">
</head>
<body>
<div id="div1">
    <h1>School Manager</h1>
</div>
<div>
    <form method="post">
        <table>
            <caption><h2>Are You Sure?</h2></caption>
            <c:if test="${classes != null}">
                <input type="hidden" name="classId" value="<c:out value = '${classes.classesId}'/>"/>
            </c:if>
            <tr>
                <th>Class Name:</th>
                <td>${classes.className}</td>
            </tr>
            <tr>
                <th>Description:</th>
                <td>${classes.classDescription}</td>
            </tr>
            <tr>
                <td>
                    <button type="button" onclick="location.href='/school?action=class'">Back</button>
                </td>
                <td>
                    <input type="submit" value="Delete">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
