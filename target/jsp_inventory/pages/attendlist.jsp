<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="content">
    <div class="search-bar">
        <form action="${pageContext.request.contextPath }/AttendServlet" method="GET">
            <input name="method" value="querybyId"  type="hidden">
            <input type="number" placeholder="请输入员工账号" name="userId">
            <button type="submit">查询</button>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>考勤编号</th>
            <th>账号</th>
            <th>考勤类型</th>
            <th>考勤时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${attendlist}" var="attend">
            <tr>
                <td>${attend.attendId}</td>
                <td>${attend.userId}</td>
                <td>${attend.attendType}</td>
                <td>${attend.attendTime}</td>
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
