<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/2/2022
  Time: 2:47 PM
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
        <h1>School Management</h1>
    </div>
    <div>
        <form method="post">
            <table>
                <caption><h2>Add New Student</h2></caption>
                <tr>
                    <th>First Name</th>
                    <td><input type="text" name="firstName" placeholder="enter first name"/></td>
                </tr>
                <tr>
                    <th>Last Name</th>
                    <td><input type="text" name="lastName" placeholder="enter last name"/></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><input type="text" name="address" placeholder="enter address"/></td>
                </tr>
                <tr>
                    <th>Class Name</th>
                    <td>
                        <select name="className">
                            <option value="1">Java</option>
                            <option value="2">Music</option>
                            <option value="3">Communication</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="button" onclick="location.href='/school?action=school'">Back</button>
                    </td>
                    <td>
                        <input type="submit" value="Add"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
