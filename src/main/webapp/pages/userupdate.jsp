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
        <input name="method" value="update"  type="hidden">
        <label for="userId">用户账号</label><br>
        <input type="text" id="userId" name="userId" placeholder="账号" value="${user.userId}" required readonly unselectable="on">
        <br>
        <br>
        <label for="userName">用户名</label><br>
        <input type="text" id="userName" name="userName" placeholder="请输入用户名" value="${user.userName}" required>
        <br>
        <br>
        <label for="userPhone">手机号</label><br>
        <input type="number" id="userPhone" name="userPhone" placeholder="请输入手机号" value="${user.userPhone}"  required onblur="isnum(userPhone)">
        <br>
        <br>
        <label for="userPwd">新的密码</label><br>
        <input type="text" id="userPwd" name="userPwd" placeholder="请输入新的密码" value="${user.userPwd}" required>
        <br>
        <br>
        <label for=>用户类型</label>
        <br>
        <br>
        <select name="userType" class="form-control">
                <%--                这里默认选中的操作--%>
                <option value="1" <c:if test="${user.userType=='1'}">selected</c:if>>超级管理员</option>
                    <option value="2" <c:if test="${user.userType=='2'}">selected</c:if>>普通员工</option>

        </select>
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