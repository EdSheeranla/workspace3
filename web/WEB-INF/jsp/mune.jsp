<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/8
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%--这是一个被包含的jsp的页面，由于每个jsp页面上都存在着需要进行跳转的需求，将他们抽离出来--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="span10 last">
    <div class="topNav clearfix">
        <ul>
            <c:if test="${empty loginUser}">
            <li id="headerLogin" class="headerLogin" style="display: list-item;">
                <a href="${pageContext.request.contextPath}/user_loginPage.action">登录</a>|
            </li>
            <li id="headerRegister" class="headerRegister" style="display: list-item;">
                <a href="${pageContext.request.contextPath}/user_registerPage.action">注册</a>|
            </li>
            </c:if>
            <c:if test="${not empty loginUser}">
            <li id="headerLogin" class="headerLogin" style="display: list-item;">
                <a href="#">${loginUser.username}</a>|
            </li>
                <li id="headerRegister" class="headerRegister" style="display: list-item;">
                    <a href="${pageContext.request.contextPath}/order_showMyOrder.action?pageNow=1">我的订单</a>|
                </li>
            <li id="headerRegister" class="headerRegister" style="display: list-item;">
                <a href="${pageContext.request.contextPath}/user_logout.action">退出</a>|
            </li>
            </c:if>
            <li id="headerUsername" class="headerUsername"></li>
            <li id="headerLogout" class="headerLogout">
                <a>[退出]</a>|
            </li>
            <li>
                <a>会员中心</a>
                |
            </li>
            <li>
                <a>购物指南</a>
                |
            </li>
            <li>
                <a>关于我们</a>

            </li>
        </ul>
    </div>
    <div class="cart">
        <a  href="${pageContext.request.contextPath}/shop_cartPage.action">购物车</a>
    </div>
    <div class="phone">
        客服热线:
        <strong>96008/53277764</strong>
    </div>
</div>
<div class="span24">
    <ul class="mainNav">
        <li>
            <a href="${pageContext.request.contextPath}/index.action">首页</a>
            |
        </li>
        <c:forEach items="${categoryList}" var="category">
        <li>
            <a href="${pageContext.request.contextPath}/categorySecond.action?cid=${category.cid}&pageNow=1">${category.cname}</a>
            |
        </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
