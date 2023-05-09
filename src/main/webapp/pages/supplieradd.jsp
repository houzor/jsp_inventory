
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
    <form action="${pageContext.request.contextPath }/SupplierServlet">
        <input name="method" value="add"  type="hidden">
        <label for="supplierName">供应商名称</label><br>
        <input type="text" id="supplierName" name="supplierName" placeholder="请输入供应商名称" required>
        <br>
        <br>
        <label for="contacts">联系人</label><br>
        <input type="text" id="contacts" name="contacts" placeholder="请输入联系人" required>
        <br>
        <br>
        <label for="phone">手机号</label><br>
        <input type="number" id="phone" name="phone" placeholder="请输入手机号" required onblur="isnum(phone)">
        <br>
        <br>
        <label for="address">地址</label><br>
        <input type="text" id="address" name="address" placeholder="请输入供应商地址" required>
        <br>
        <br>
        <button type="submit">添加供应商</button>
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