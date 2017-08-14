<%--
  Created by IntelliJ IDEA.
  User: Jeremy
  Date: 2017/7/26
  Time: 下午3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="md5.js" type="text/javascript"></script>
  </head>
  <body>
  <form action="login.do" method="post">
    <p>username: <input type="text" name="username" /></p>
    <p>password: <input type="password" name="password" /></p>
    <input type="submit" value="登陆">
  </form>
  <form action="register.jsp" method="post">
    <input type="submit" value="注册">
  </form>
  </body>
</html>