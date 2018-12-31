<%--
  二维码展示页面 (其中要修改的为回调域名,但也是用/callback后续路径)
  TODO (如果做为iframe嵌入时)点击弹出二维码时,将URL链接进行更改,不然每次都会立马生成二维码.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login_qrcode</title>
    <script src="http://www.jq22.com/jquery/jquery-3.3.1.js"></script>
    <!--微信登录二维码插件js-->
    <script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
</head>
<body>

<div id="login_container"></div>
<script>

    //开放平台参数
    //开放平台appid
    var APPID = "wx05d97061446e7f33";
    //开放平台请求作用域
    var SCOPE = "snsapi_login";
    //回调页面 URL 进行utf cncode
    var REDIRECT_URI = encodeURIComponent("http://ngrok.ykmimi.com/callback");
    //开放平台appsecret

    //
    var STATE = "state";


    //快捷方式
    var obj = new WxLogin({
        self_redirect: false,
        id: "login_container",
        appid: APPID,
        scope: SCOPE,
        redirect_uri: REDIRECT_URI,
        state: Math.ceil(Math.random()*1000), //随机防黑
        style: "",
        href: ""
    });






</script>


</body>
</html>
