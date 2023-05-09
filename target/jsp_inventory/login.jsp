<%--
  Created by IntelliJ IDEA.
  User: Houzer
  Date: 2023/3/27
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>超市管理系统-登录</title>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
<h1>超市库存管理系统</h1>
<div class="container">
  <form action="${pageContext.request.contextPath }/LoginServlet" method="POST">
    <div class="form-group">
      <label for="userid">账号:</label>
      <input type="text" id="userid" name="userid" placeholder="请输入账号" required>
    </div>
    <div class="form-group">
      <label for="password">密码:</label>
      <input type="password" id="password" name="password" placeholder="请输入密码" required>
    </div>
    <input type="submit" value="登录">
    <div class="info">${error}</div>
  </form>
</div>
</body>
</html>
