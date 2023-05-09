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
        <form action="${pageContext.request.contextPath}/RecordServlet" method="get">
            <input name="method" value="querybyname" type="hidden">
            <input type="text" placeholder="请输入操作人" name="recordName">
            <button type="submit">查询</button>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <td>记录编号</td>
            <td>操作类型</td>
            <td>操作账号</td>
            <td>操作人</td>
            <td>操作时间</td>
            <td>操作数量</td>
            <td>操作库存号</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${recordlist}" var="record">
            <tr>
                <td>${record.recordId}</td>
                <td>${record.recordType}</td>
                <td>${record.userId}</td>
                <td>${record.recordName}</td>
                <td>${record.recordTime}</td>
                <td>${record.amount}</td>
                <td>${record.goodId}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
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
