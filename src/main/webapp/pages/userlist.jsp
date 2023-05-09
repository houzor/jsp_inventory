<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 银龙背上的医生
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="content">
    <div class="search-bar">
        <form action="${pageContext.request.contextPath }/UserServlet" method="GET">
            <input name="method" value="querybyname"  type="hidden">
            <input type="text" placeholder="请输入用户名称" name="userName">
            <button type="submit">查询</button>
        </form>
        <button onclick="window.location.href='${pageContext.request.contextPath }/pages/useradd.jsp'">添加用户</button>
    </div>
    <table>
                <thead>
                <tr>
                    <th>用户账号</th>
                    <th>用户名称</th>
                    <th>用户密码</th>
                    <th>联系方式</th>
                    <th>用户组别</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${userlist}" var="user">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.userName}</td>
                        <td>${user.userPwd}</td>
                        <td>${user.userPhone}</td>
                        <td>
                            <b><c:if test="${user.userType==1}">超级管理员</c:if></b>
                            <b><c:if test="${user.userType==2}">普通员工</c:if></b>
                        </td>
                        <td>
                            <span><button onclick="window.location.href='${pageContext.request.contextPath}/UserServlet?method=querybyid&userId=${user.userId}'">修改用户</button></span>
                            <span><button onclick="confirmDel(${user.userId})">删除用户</button></span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
    </table>
</div>

</body>
<script type="text/javascript">
    function confirmDel(param){
        if(window.confirm("确定删除该用户？此操作会删除该用户下的所有考勤记录和操作记录")){
            document.location="${pageContext.request.contextPath}/UserServlet?method=delete&userId="+param;
        }
    }
</script>
<script type="text/javascript">
    function confirmLogout(param){
        if(window.confirm("确定要下班吗？")){
            document.location="${pageContext.request.contextPath }/AttendServlet?method="+param;
        }
    }
</script>
</html>
