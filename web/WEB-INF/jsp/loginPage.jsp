<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/8
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>会员登录</title>

<link href="./css/common.css" rel="stylesheet" type="text/css"/>
<link href="./css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script language="JavaScript" type="text/javascript">
    function change(){
        var captchaImage=document.getElementById("captchaImage");
        captchaImage.src="${pageContext.request.contextPath}/checkcode.action?time="+new Date();
    }
</script>
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

</div>	<div class="container login">
    <div class="span12">
        <div class="ad">
            <img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
        </div>		</div>
    <div class="span12 last">
        <div class="wrap">
            <div class="main">
                <div class="title">
                    <strong>会员登录</strong>USER LOGIN
                </div>
                <form id="loginForm"  method="post" novalidate="novalidate"   action="${pageContext.request.contextPath}/user_login.action">
                    <table>
                        <tbody><tr>
                            <th>
                                用户名/E-mail:
                            </th>
                            <td>
                                <c:if test="${cookie.namecookie not eq null}">
                                    <input type="text" id="username" name="username" class="text" maxlength="20">

                                </c:if>


                            </td>
                        </tr>
                        <tr>
                            <th>
                                密&nbsp;&nbsp;码:
                            </th>
                            <td>
                                <input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <th>
                                验证码:
                            </th>
                            <td>
										<span class="fieldSet">
											<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off"><img onclick="change()" id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/checkcode.action" title="点击更换验证码">
										</span>
                                <span><s:actionerror/></span>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <label>
                                    <input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true">记住用户名
                                </label>
                                <label>
                                    &nbsp;&nbsp;<a >找回密码</a>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <input type="submit" class="submit" value="登 录">
                            </td>
                        </tr>
                        <tr class="register">
                            <th>&nbsp;

                            </th>
                            <td>
                                <dl>
                                    <dt>还没有注册账号？</dt>
                                    <dd>
                                        立即注册即可体验在线购物！
                                        <a href="会员注册.htm">立即注册</a>
                                    </dd>
                                </dl>
                            </td>
                        </tr>
                        </tbody></table>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd"><img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势" /></div>
    </div>
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
                <a>招贤纳士</a>
                |
            </li>
            <li>
                <a>法律声明</a>
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
                <a>服务声明</a>
                |
            </li>
            <li>
                <a>广告声明</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
    </div>
</div>
</body>
</html>
