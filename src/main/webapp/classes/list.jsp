<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/2/2022
  Time: 7:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Class In School</title>
    <link rel="stylesheet" href="mycss.css">
</head>
<body>
<div id="div1">
    <h1>All Class In School</h1>
</div>
<h2>
    <button id="button2" type="button" onclick="location.href='/classes/add.jsp'">Open New Class</button>
</h2>
<h2>
    <button id="button1" type="button" onclick="location.href='/school?action=school'">Go to Student List</button>
</h2>
<div>
    <table>
        <caption><h2>Class List</h2></caption>
        <tr>
            <th>Class Name</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${classesList}" var="classes">
            <tr>
                <td><c:out value="${classes.className}"/></td>
                <td><c:out value="${classes.classDescription}"/></td>
                <td>
                    <a href="/school?action=editClass&classId=${classes.classesId}">Edit</a>
                    <a href="/school?action=deleteClass&classId=${classes.classesId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
