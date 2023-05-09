<%--
  Created by IntelliJ IDEA.
  User: 银龙背上的医生
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <form action="${pageContext.request.contextPath}/UserServlet">
          <input name="method" value="add" type="hidden">
          <label for="userId">用户账号</label>
          <br>
          <input type="number" id="userId" name="userId" placeholder="请输入用户账号" required>
          <br>
          <br>
          <label for="userName">用户名称</label><br>
          <input type="text" id="userName" name="userName" placeholder="请输入用户名称" required><br><br>
          <label for="userPwd">用户密码</label><br>
          <input type="text" id="userPwd" name="userPwd" placeholder="请输入用户密码" required><br><br>
          <label for="userPhone">联系方式</label><br>
          <input type="number" id="userPhone" name="userPhone" placeholder="请输入联系方式" required onblur="isnum(userPhone)"><br><br>
          <label >用户组别</label><br>
          <select name="userType" class="form-control">
            <option value="1" >超级管理员</option>
            <option value="2" >普通员工</option>
          </select>
          <br><br>
          <button type="submit">提交</button>
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
