<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/1/2022
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome To School Manager</title>
    <link rel="stylesheet" href="mycss.css">
  </head>
  <body>
  <div id="div1">
    <h1> Welcome To School Manager</h1>
  </div>
  <button type="button" id="button1" onclick="location.href='/school?action=school'">Student List</button>
  <button type="button" id="button2" onclick="location.href='/school?action=class'">Class List</button>
  </body>
</html>
