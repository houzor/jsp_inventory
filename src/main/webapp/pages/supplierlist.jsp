<%--
  Created by IntelliJ IDEA.
  User: A bin
  Date: 2023/3/28
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="content">
  <div class="search-bar">
    <form action="${pageContext.request.contextPath }/SupplierServlet" method="GET">
      <input name="method" value="querybyname"  type="hidden">
      <input type="text" placeholder="请输入供应商名称" name="supplierName">
      <button type="submit">查询</button>
    </form>
    <button onclick="window.location.href='${pageContext.request.contextPath }/pages/supplieradd.jsp'">添加供应商</button>
  </div>
  <table>
    <thead>
    <tr>
      <th>供应商号</th>
      <th>供应商名称</th>
      <th>联系人</th>
      <th>手机号</th>
      <th>地址</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${supplierlist}" var="supplier">
      <tr>
        <td>${supplier.supplierId}</td>
        <td>${supplier.supplierName}</td>
        <td>${supplier.contacts}</td>
        <td>${supplier.phone}</td>
        <td>${supplier.address}</td>
        <td>
          <span><button onclick="window.location.href='${pageContext.request.contextPath }/SupplierServlet?method=querybyid&supplierId=${supplier.supplierId}'">修改</button></span>
          <span><button onclick="confirmDel(${supplier.supplierId})">删除</button></span>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

</div>

</body>
<script type="text/javascript">
  function confirmDel(param){
    if(window.confirm("确定删除？此操作会顺带删除该供应商下的所有商品和该商品的出入库记录！！！")){
      document.location="${pageContext.request.contextPath }/SupplierServlet?method=delete&supplierId="+param;
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
