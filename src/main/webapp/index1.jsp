<%@ page import="com.baigehuidi.demo.weixin4j.model.user.User" %>
<%@ page import="com.baigehuidi.demo.weixin4j.model.sns.SnsUser" %><%--
测试首页 将登录做为单独的页面 (也可以在该页面中进行弹出[或iframe形式或遮罩窗体形式])
TODO 每页(包括子页面)都应有登录或者展示用户昵称的头部bar

该页面为PC端.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微信扫码登录</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.3.1.min.js"></script>
    <style>
        .link{display: none;}
        .show{display: block;}
    </style>
</head>
<body>
TODO :
index.jsp  该页面也为最终用户登录后的页面,登录后,不再展示登录集,而展示用户名 <br>
<p id="nickname"></p>

用户登录后设定session时间 <br>
<a href="login">登录集</a> <br>
<a href="/menuCreate">自定义菜单</a> <br>
<a href="/wxpay/notify">支付测试</a> <br>

测试号二维码<br>
<img src="img/0.jpg" style="height:300px;width:300px">

${snsUser.nickname}
<%if(session.getAttribute("user")!=null){%>
<%User user = (User)session.getAttribute("user");%>
<%=user.getNickname()%>
<%}%>


<%--<%if(session.getAttribute("SnsUser")!=null){%>--%>
<%--<%SnsUser snsUser = (SnsUser)session.getAttribute("SnsUser");%>--%>
<%--<%=snsUser.getNickname()%>--%>
<%--<%}%>--%>

<a href="" class="link a2 show">不是微信端</a>
<a href="" class="link a1">微信端</a>


<p>该页面通过判定是否是微信内置浏览器,执行/非执行网页授权获取用户信息.</p>



<script>
    function isWeiXin() {
        var ua = window.navigator.userAgent.toLowerCase();
        console.log(ua);//mozilla/5.0 (iphone; cpu iphone os 9_1 like mac os x) applewebkit/601.1.46 (khtml, like gecko)version/9.0 mobile/13b143 safari/601.1
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true;
        }
        else {
            return false;
        }
    }
    if(isWeiXin()){
        console.log(" 是来自微信内置浏览器");
        $(".a1").addClass("show");
        $(".a2").removeClass("show");
        //是微信内置浏览器执行通过code获取用户信息
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
    }
    else{
        console.log("不是来自微信内置浏览器");
        $(".a2").addClass("show");
        $(".a1").removeClass("show");
    }
</script>

</body>

</html>
