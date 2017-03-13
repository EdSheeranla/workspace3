<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/12
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>我的订单</title>
    <link href="./css/common.css" rel="stylesheet" type="text/css"/>
    <link href="./css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="./网上商城/index.htm">
                <img src="./image/r___________renleipic_01/logo.gif" alt="啦啦啦"/>
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="./image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
        </div>
    </div>
    <%@include file="mune.jsp"%>

</div>

<div class="container cart">

    <div class="span24">

        <div class="step step1">
            <ul>

                <li  class="current"></li>
                <li  >生成订单成功</li>
            </ul>
        </div>


        <table>
            <tbody>
            <c:forEach items="${pageBean.list}" var="order">
            <tr>
                <td colspan="5">订单编号：${order.oid }&nbsp;&nbsp;&nbsp;&nbsp;订单状态:
                <c:choose>
                    <c:when test="${order.state==1}">
                        <font color="red">未付款</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/order_goPay?oid=${order.oid}">前去付款</a>
                    </c:when>
                    <c:when test="${order.state==2}">
                        已付款
                    </c:when>
                    <c:when test="${order.state==3}">
                        已发货
                    </c:when>
                    <c:otherwise>
                        已收货
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
            <tr>
                <th>图片</th>
                <th>商品</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>

            </tr>

            <tr>
                <c:forEach items="${order.orderItemSet}" var="orderItem">
                <td width="60">
                    <input type="hidden" name="id" value="22"/>
                    <img src="./${orderItem.product.image}"/>
                </td>
                <td>
                    <a target="_blank">${orderItem.product.pname}</a>
                </td>
                <td>
                    ${orderItem.product.shop_price}
                </td>
                <td class="quantity" width="60">
                    <input type="text" name="count" value="${orderItem.count}" maxlength="4" onpaste="return false;"/>
                    <div>
                        <span id="increase" class="increase" onclick="increaseNum()">&nbsp;</span>
                        <span id="decrease" class="decrease" onclick="decreaseNum()">&nbsp;</span>
                    </div>
                </td>
                <td width="140">
                    <span class="subtotal">￥${orderItem.subtotal}</span>
                </td>

            </tr>
            </c:forEach>
            </c:forEach>
            </tbody>
        </table>
        <%--导航条--%>
        <div class="pagination">
            <a class="firstPage" href="${pageContext.request.contextPath}/order_showMyOrder.action?pageNow=1">&nbsp;</a>
            <c:if test="${pageBean.pageNow>1}">
                <a class="previousPage" href="${pageContext.request.contextPath}/order_showMyOrder.action?pageNow=${pageBean.pageNow-1}">&nbsp;</a>
            </c:if>

            <c:forEach  begin="1" end="${pageBean.pageCount}" var="i">
                <%--<span class="currentPage">1</span>--%>
                <a href="${pageContext.request.contextPath}/order_showMyOrder.action?pageNow=${i}">${i}</a>
            </c:forEach>

            <c:if test="${pageBean.pageNow<pageBean.pageCount}">
                <a class="nextPage" href="${pageContext.request.contextPath}/order_showMyOrder.action?pageNow=${pageBean.pageNow+1}">&nbsp;</a>
            </c:if>

            <a class="lastPage" href="${pageContext.request.contextPath}/order_showMyOrder.action?pageNow=${pageBean.pageCount}">&nbsp;</a>
        </div>
        <dl id="giftItems" class="hidden" style="display: none;">
        </dl>

    </div>

</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
        </div>
    </div>
    <div class="span24">
        <ul class="bottomNav">
            <li>
                <a href="#">关于我们</a>
                |
            </li>
            <li>
                <a href="#">联系我们</a>
                |
            </li>
            <li>
                <a href="#">诚聘英才</a>
                |
            </li>
            <li>
                <a href="#">法律声明</a>
                |
            </li>
            <li>
                <a>友情链接</a>
                |
            </li>
            <li>
                <a target="_blank">支付方式</a>
                |
            </li>
            <li>
                <a target="_blank">配送方式</a>
                |
            </li>
            <li>
                <a >SHOP++官网</a>
                |
            </li>
            <li>
                <a>SHOP++论坛</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
    </div>
</div>
<script >
    function increaseNum(){
        var count = document.getElementById("count");
        var num=parseInt(count.value);
        num=num+1;
        count.value=num;
    }
    function decreaseNum(){
        var count = document.getElementById("count");
        var num=parseInt(count.value);
        if(num>1){
            num=num-1;
        }

        count.value=num;
    }
</script>
</body>
</html>
