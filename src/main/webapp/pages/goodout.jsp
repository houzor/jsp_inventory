<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form action="${pageContext.request.contextPath }/GoodServlet">
        <input name="method" value="out"  type="hidden">
        <label for="goodId">库存号</label><br>
        <input type="number" id="goodId" name="goodId" placeholder="请输入库存号" required>
        <br>
        <br>
        <label for="amount">商品数量</label><br>
        <input type="number" id="amount" name="amount" placeholder="请输入出库数量" required>
        <br>
        <button type="submit">提交出库</button>
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
