<%@ page import="com.baigehuidi.demo.weixin4j.model.user.User" %>
<%@ page import="com.baigehuidi.demo.weixin4j.model.sns.SnsUser" %><%--
测试首页 将登录做为单独的页面 (也可以在该页面中进行弹出[或iframe形式或遮罩窗体形式])
TODO 每页(包括子页面)都应有登录或者展示用户昵称的头部bar

该页面为手机端,微信端,仿APP.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微信扫码登录</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
TODO :
index.jsp  该页面也为最终用户登录后的页面,登录后,不再展示登录集,而展示用户名 <br>
用户登录后设定session时间 <br>
<a href="login">登录集</a>

${snsUser.nickname}
<%if(session.getAttribute("user")!=null){%>
<%User user = (User)session.getAttribute("user");%>
<%=user.getNickname()%>
<%}%>


<!--当前使用jsp语法,判定当前页面session不存在用户时,调取用户信息-->
<!--当PC端访问该页面,因为没有code所以会报错,除非是使用微信内置浏览器-->
<!--也就是说webapp其实可以判定一下当前浏览器是否是微信内置浏览器-->
<%if(session.getAttribute("SnsUser")!=null){%>
<%SnsUser snsUser = (SnsUser)session.getAttribute("SnsUser");%>
<%=snsUser.getNickname()%>
<%}else{%> <!--else,通过ajax获取用户信息-->
 <script>
     alert("get...byCode in script");
     getSnsUserInfoByCode();
     function getSnsUserInfoByCode(){

         alert("getSnsUserInfoByCode in method");
         //下面页面为手机端页面获取
         $.ajax({
             url: "/user/getSnsUserInfoByCode",
             // url: "/callBackLogin",
             data: "<%=request.getParameter("code")%>",
             contentType: "application/json",
             dataType: "json",
             method: "POST",
             success: function (data) {
                 alert(JSON.stringify(data));
                 var nickname = data.SnsUser.nickname;
                 $("#nickname").html(nickname+"同学");
                 alert("你好啊,"+data.SnsUser.nickname+"同学,好久不见你瘦了一大圈.");
                 //将数据反填到html或jsp页面上
             }
         });
     }

 </script>
<%}%>

<p id="nickname"></p>
<a href="/wxpay/notify">支付测试</a>
</body>


<script>


</script>
</html>
