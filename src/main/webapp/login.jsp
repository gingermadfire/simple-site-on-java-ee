<%--
  Created by IntelliJ IDEA.
  User: Компьютер
  Date: 05.05.2023
  Time: 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
    <form action="login" method="post" >
        <label>Логин: </label>
        <input name="username" type="text" placeholder="Введите ваш логин">
        <label>Пароль: </label>
        <input name="password" type="password" placeholder="Введите ваш пароль">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
