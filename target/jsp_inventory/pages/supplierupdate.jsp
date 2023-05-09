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
        <input name="method" value="update"  type="hidden">
        <label for="supplierId">供应商号</label><br>
        <input type="text" id="supplierId" name="supplierId" placeholder="请输入供应商号" value="${supplier.supplierId}" readonly unselectable="on">
        <br>
        <br>
        <label for="supplierName">供应商名称</label><br>
        <input type="text" id="supplierName" name="supplierName" placeholder="请输入供应商名称" value="${supplier.supplierName}" required>
        <br>
        <br>
        <label for="contacts">联系人</label><br>
        <input type="text" id="contacts" name="contacts" placeholder="请输入联系人" value="${supplier.contacts}"  required>
        <br>
        <br>
        <label for="phone">手机号</label><br>
        <input type="number" id="phone" name="phone" placeholder="请输入手机号" value="${supplier.phone}" required onblur="isnum(phone)">
        <br>
        <br>
        <label for="address">地址</label><br>
        <input type="text" id="address" name="address" placeholder="请输入地址" value="${supplier.address}" required>
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
