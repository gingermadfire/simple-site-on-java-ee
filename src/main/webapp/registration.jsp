<%--
  Created by IntelliJ IDEA.
  User: Компьютер
  Date: 11.05.2023
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Registration</title>
  </head>
    <body>
      <form action="registration" method="post">
        <label>Имя:</label>
        <input name="firstname" type="text" placeholder="Введите ваше имя">
        <label>Фамилия:</label>
        <input name="lastname" type="text" placeholder="Введите вашу фамилию"/>
        <label>Имя пользователя:</label>
        <input name="username" type="text" placeholder="Придумайте никнейм"/>
        <label>Пароль:</label>
        <input name="password" type="text" placeholder="Придумайте пароль"/>
        <input type="submit" value="Submit">
      </form>
    </body>
</html>
