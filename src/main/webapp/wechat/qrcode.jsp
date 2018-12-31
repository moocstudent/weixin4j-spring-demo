<%--
微信扫码登录 openid版本
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>微信扫码，关注并登录</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <style>a {
        outline: 0
    }

    h1, h2, h3, h4, h5, h6, p {
        margin: 0;
        font-weight: 400
    }

    a img, fieldset {
        border: 0
    }

    body {
        font-family: "Microsoft Yahei";
        color: #fff;
        background: 0 0
    }

    .impowerBox {
        display: inline-block;
        vertical-align: middle;
        line-height: 1.6;
        position: relative;
        width: 100%;
        z-index: 1;
        text-align: center
    }

    .impowerBox .title {
        text-align: center;
        font-size: 20px
    }

    .impowerBox .qrcode {
        width: 280px;
        height: 280px;
        margin-top: 15px;
        border: 1px solid #E2E2E2
    }

    .impowerBox .info {
        width: 280px;
        margin: 0 auto
    }

    .impowerBox .status {
        padding: 7px 14px;
        text-align: left
    }

    .impowerBox .status.normal {
        margin-top: 15px;
        background-color: #232323;
        border-radius: 100px;
        -moz-border-radius: 100px;
        -webkit-border-radius: 100px;
        box-shadow: inset 0 5px 10px -5px #191919, 0 1px 0 0 #444;
        -moz-box-shadow: inset 0 5px 10px -5px #191919, 0 1px 0 0 #444;
        -webkit-box-shadow: inset 0 5px 10px -5px #191919, 0 1px 0 0 #444
    }

    .impowerBox .status.status_browser {
        text-align: center
    }

    .impowerBox .status p {
        font-size: 13px
    }</style>
    <script src="http://www.jq22.com/jquery/jquery-3.3.1.js"></script>
</head>
<body style="background-color: rgb(51, 51, 51); padding: 50px;">
<div class="main impowerBox">
    <div class="loginPanel normalPanel">
        <div class="title">微信登录</div>
        <div class="waiting panelContent">
            <div class="wrp_code">
                <img class="qrcode lightBorder" src="${qrcodeUrl}"/>
            </div>
            <div class="info">
                <div class="status status_browser js_status normal" id="wx_default_tip">
                    <p>请使用微信扫描二维码登录</p>
                    <p>白鸽惠递</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        setInterval("wechatCheckLogin()", 2000);
    });

    function wechatCheckLogin() {
        $.post("/WeixinContact/wechat/checkLogin", {scene_str: "${scene_str}"}, function (data) {
            console.log("请求/wechat/checkLogin方法中...");
            //数据1, 成功获取用户信息
            if (data.code===1) {
                window.location.href="http://ngrok.ykmimi.com/wechat/callback";
            } else if(data.code===0) {
                //虽然有用户openid,但是无法获取用户信息,可能是用户取消了关注
                //需要跳转到重新扫码生成界面
                window.location.href="http://ngrok.ykmimi.com/wechat/wechatLogin";
            }else if(data.code===-1){
                //如果场景字符串为空 -1 检查场景字符串时间戳生成是否正确
                window.location.href="http://ngrok.ykmimi.com/error";
            }else if(data.code===-2){
                //检查openid为什么没正确传入
                window.location.href="http://ngrok.ykmimi.com/error";
            }else if(data.code===-3){
                //-3
                //两者都为空,系统挂了吗
                window.location.href="http://ngrok.ykmimi.com/error";
            }
        }, "JSON");
    }
</script>
</body>
</html>