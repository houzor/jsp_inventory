<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>
<style>
    input{
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ddd;
        font-size: 16px;
        margin-right: 10px;
    }
</style>
<div class="content">
    <form action="${pageContext.request.contextPath }/UserServlet">
        <input name="method" value="infoupdate"  type="hidden">
        <label for="userId">用户账号</label><br>
        <input type="text" id="userId" name="userId" placeholder="账号" value="${usersession.userId}" readonly unselectable="on">
        <br>
        <br>
        <label for="userName">用户名</label><br>
        <input type="text" id="userName" name="userName" placeholder="请输入用户名" value="${usersession.userName}" required >
        <br>
        <br>
        <label for="userPhone">手机号</label><br>
        <input type="number" id="userPhone" name="userPhone" placeholder="请输入手机号" value="${usersession.userPhone}"  required onblur="isnum(userPhone)">
        <br>
        <br>
        <label for="userPwd">新的密码</label><br>
        <input type="text" id="userPwd" name="userPwd" placeholder="请输入新的密码" value="${usersession.userPwd}" required>
        <br>
        <br>
        <label for=>用户类型</label>
        <br>
        <br>
        <c:if test="${usersession.userType==1}">
            <select name="userType" class="form-control">
                <option value="1" >超级管理员</option>
                <option value="2">普通员工</option>
            </select>
        </c:if>

        <c:if test="${usersession.userType=='2'}">
            <b><c:if test="${usersession.userType=='1'}">超级管理员</c:if></b>
            <b><c:if test="${usersession.userType=='2'}">普通员工</c:if></b>
            <%--        这里我用了hidden方便提交--%>
            <input type="hidden" id="userType" name="userType" placeholder="用户类型" value="${usersession.userType}" readonly unselectable="on">
        </c:if>

        <br>
        <br>
        <button type="submit">提交修改</button>
    </form>
</div>
</body>
<script type="text/javascript">
    function confirmLogout(param){
        if(window.confirm("确定要下班吗？")){
            document.location="${pageContext.request.contextPath }/AttendServlet?method="+param;
        }
    }
    function isnum(obj){
        var reg=/^1[0-9]{10}/;
        if(!reg.test(obj.value)){
            alert("请正确填写手机号！");
            obj.value="";
        }
    }
</script>
</html>