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
    <form action="${pageContext.request.contextPath }/GoodServlet">
        <input name="method" value="update"  type="hidden">
        <label for="goodId">库存号</label><br>
        <input type="text" id="goodId" name="goodId" placeholder="库存号" value="${good.goodId}" readonly unselectable="on">
        <br>
        <br>
        <label for="goodName">商品名</label><br>
        <input type="text" id="goodName" name="goodName" placeholder="请输入商品名" value="${good.goodName}" required>
        <br>
        <br>
        <label for="goodPrice">商品进价</label><br>
        <input type="text" id="goodPrice" name="goodPrice" placeholder="请输入商品进价" value="${good.goodPrice}"  required>
        <br>
        <br>
        <label for="createDate">商品生产日期</label><br>
        <input type="date" id="createDate" name="createDate" placeholder="请输入商品生产日期" value="${good.createDate}" required>
        <br>
        <br>
        <label for="amount">商品数量</label><br>
        <input type="number" id="amount" name="amount" placeholder="请输入进货数量" value="${good.amount}" required
               onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
               onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}">
        <br>
        <br>
        <label for="life">商品保质期</label><br>
        <input type="number" id="life" name="life" placeholder="请输入商品保质期" value="${good.life}" required
               onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
               onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}">
        <br>
        <br>
        <label>供应商</label>
        <br>
        <select name="supplierId" class="form-control">
            <c:forEach items="${supplierlist}" var="supplier">
<%--                这里默认选中的操作--%>
                <option value="${supplier.supplierId}" <c:if test="${good.supplierId==supplier.supplierId}">selected</c:if>>${supplier.supplierName}</option>
            </c:forEach>
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
</script>
</html>
