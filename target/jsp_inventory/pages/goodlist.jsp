<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="content">
    <div class="search-bar">
        <form action="${pageContext.request.contextPath }/GoodServlet" method="GET">
            <input name="method" value="querybyname"  type="hidden">
            <input type="text" placeholder="请输入商品名称" name="goodName">
            <button type="submit">查询</button>
        </form>

        <button onclick="window.location.href='${pageContext.request.contextPath }/SupplierServlet?method=ruku'">入库</button>
        <button onclick="window.location.href='${pageContext.request.contextPath }/pages/goodout.jsp'">出库</button>
    </div>
    <table>
        <thead>
        <tr>
            <th>库存编号</th>
            <th>商品名称</th>
            <th>商品进价</th>
            <th>生产日期</th>
            <th>库存数量</th>
            <th>保质期</th>
            <th>供应商</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${goodlist}" var="good">
            <tr>
                <td>${good.goodId}</td>
                <td>${good.goodName}</td>
                <td>${good.goodPrice}</td>
                <td>${good.createDate}</td>
                <td>${good.amount}</td>
                <td>${good.life}</td>
                <td>${good.supplierName}</td>
                <td>
                    <span><button onclick="window.location.href='${pageContext.request.contextPath }/GoodServlet?method=querybyid&goodId=${good.goodId}'">修改</button></span>
                    <span><button onclick="confirmDel(${good.goodId})">删除</button></span>

                </td>
            </tr>

        </c:forEach>

        </tbody>
    </table>

</div>

</body>
<script type="text/javascript">
    function confirmDel(param){
        if(window.confirm("确定删除？，此操作会连带其出入库记录一起删除")){
            document.location="${pageContext.request.contextPath }/GoodServlet?method=delete&goodId="+param;
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
