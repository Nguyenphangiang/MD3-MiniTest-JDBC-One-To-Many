<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/2/2022
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>School Manager</title>
    <link rel="stylesheet" href="mycss.css">
</head>
<body>
    <div id="div1"><h1>School Manager</h1>
    </div>
    <div>
        <form method="post">
            <table>
                <caption><h2>Open New Class</h2></caption>
                <tr>
                    <th>Class Name</th>
                    <td><input type="text" name="className" placeholder="enter class name"/></td>
                </tr>
                <tr>
                    <th>Class Description</th>
                    <td><input type="text" name="classDescription" placeholder="enter class description"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="button" onclick="location.href='/school?action=class'">Back</button>
                        <input type="submit" value="Open">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
