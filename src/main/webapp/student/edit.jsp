<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/2/2022
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student Information</title>
    <link rel="stylesheet" href="mycss.css">
</head>
<body>
    <div id="div1">
        <h1>Update Student Information</h1>
    </div>
    <div>
        <form method="post">
            <table>
                <caption><h2>Update Information</h2></caption>
                <c:if test="${student != null}">
                    <input type="hidden" name="id" value="<c:out value = '${student.studentId}'/>"/>
                </c:if>
                <tr>
                    <th>First Name:</th>
                    <td>
                        <input type="text" name="firstName" value="<c:out value='${student.firstName}'/>"/>
                    </td>
                </tr>
                <tr>
                    <th>Last Name:</th>
                    <td>
                        <input type="text" name="lastName" value="<c:out value='${student.lastName}'/>">
                    </td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>
                        <input type="text" name="address" value="<c:out value='${student.address}'/>"/>
                    </td>
                </tr>
                <tr>
                    <th>Class Name:</th>
                    <td>
                        <select name="className">
                            <option value="Java">Java</option>
                            <option value="Music">Music</option>
                            <option value="Communication">Communication</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="button" onclick="location.href='/school?action=school'">Back</button>
                    </td>
                    <td >
                        <input type="submit" value="Update"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
