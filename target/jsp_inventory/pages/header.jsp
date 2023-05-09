<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>超市库存管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" />
</head>
<body>
<header>
    <div class="logo">
        <a href="#">超市库存管理系统</a>
    </div>
    <div class="welcome">
        <a href="#">${usersession.userName}</a>
    </div>
</header>
<nav class="main-nav">
    <ul>
        <li><a href="${pageContext.request.contextPath }/pages/index.jsp" id="shouye">首页</a></li>

        <li><a href="${pageContext.request.contextPath }/pages/infoupdate.jsp" >个人信息修改</a></li><%--这里因为session有了，所以直接拿jsp就好了--%>

        <c:if test="${usersession.userType=='1'}">
            <li><a href="${pageContext.request.contextPath }/UserServlet?method=query">用户管理</a></li>
        </c:if>

        <li><a href="${pageContext.request.contextPath }/GoodServlet?method=query">库存管理</a></li>

        <c:if test="${usersession.userType=='1'}">
            <li><a href="${pageContext.request.contextPath }/SupplierServlet?method=query">供应商管理</a></li>
        </c:if>

        <c:if test="${usersession.userType=='1'}">
            <li><a href="${pageContext.request.contextPath }/AttendServlet?method=query">考勤记录</a></li>
        </c:if>

        <li><a href="${pageContext.request.contextPath }/RecordServlet?method=query">出入库记录</a></li>

        <li><a href="javascript:void(0)" onclick="confirmLogout('exit')">下班打卡></a></li>
    </ul>
</nav>
