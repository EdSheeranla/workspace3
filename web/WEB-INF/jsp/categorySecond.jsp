<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/11
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>传智网上商城</title>
    <link href="./css/common.css" rel="stylesheet" type="text/css"/>
    <link href="./css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="http://localhost:8080/mango/">
                <img src="./image/r___________renleipic_01/logo.gif" alt="传智播客">
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="./image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
        </div>	</div>

    <%@include file="mune.jsp"%>

</div>
<div class="container productList">
    <div class="span6">
        <div class="hotProductCategory">
            <c:forEach items="${categoryList}" var="category">
            <dl>
                <dt>
                    <a href="./image/蔬菜 - Powered By Mango Team.htm">${category.cname}</a>
                </dt>
                <c:forEach items="${category.categorySeconds}" var="categorySecond">
                    <dd>
                        <a >${categorySecond.csname}</a>
                    </dd>
                </c:forEach>
            </dl>
            </c:forEach>


        </div>
    </div>
    <div class="span18 last">

        <form id="productForm" action="./image/蔬菜 - Powered By Mango Team.htm" method="get">
            <input type="hidden" id="brandId" name="brandId" value="">
            <input type="hidden" id="promotionId" name="promotionId" value="">
            <input type="hidden" id="orderType" name="orderType" value="">
            <input type="hidden" id="pageNumber" name="pageNumber" value="1">
            <input type="hidden" id="pageSize" name="pageSize" value="20">

            <div id="result" class="result table clearfix">
                <ul>
                    <c:forEach items="${pageBena.List}" var="product">
                    <li>
                        <a href="./京华商城分页面.htm">
                            <img src="./${product.image}" width="170" height="170"  style="display: inline-block;">

                            <span style='color:green'>
											 ${product.pname}
											</span>

                            <span class="price">
												商城价： ￥${product.market_price}/件
											</span>

                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="pagination">
                <span class="firstPage">&nbsp;</span>
                <span class="previousPage">&nbsp;</span>
                <span class="currentPage">1</span>
                <a href="javascript: $.pageSkip(2);">2</a>
                <a class="nextPage" href="javascript: $.pageSkip(2);">&nbsp;</a>

                <a class="lastPage" href="javascript: $.pageSkip(2);">&nbsp;</a>
            </div>
        </form>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
        </div>	</div>
    <div class="span24">
        <ul class="bottomNav">
            <li>
                <a >关于我们</a>
                |
            </li>
            <li>
                <a>联系我们</a>
                |
            </li>
            <li>
                <a >诚聘英才</a>
                |
            </li>
            <li>
                <a >法律声明</a>
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
                <a  target="_blank">配送方式</a>
                |
            </li>
            <li>
                <a >官网</a>
                |
            </li>
            <li>
                <a >论坛</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
    </div>
</div>
</body></html>
