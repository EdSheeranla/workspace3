<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/8
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%--<% Cookie c1=new Cookie("demo1","demo1");--%>
<%--//    Cookie c2=new Cookie("demo2","demo2");--%>
  <%--%>--%>

  <%--${cookie.demo1}--%>
  <div id="popupInfo">
    选项框
  </div>
  <center  >
    <input id="clickme" type="button" onclick="save()" value="点击">
  </center>

  <%--<jsp:forward page="admin_loginPage.action"/>--%>
  <script>
    function save(){
        alert("保存");
    }
//    function Autoclick(){
//        alert("插件启动");
////        $("#clickme").click();
//        $("#popupInfo").siblings("center").children("input").click();
//        //alert("插件结束");
//    }
//    setInterval(Autoclick,1000);
//    $(function(){
//
//    });
  </script>
  </body>
</html>
