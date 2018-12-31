<%--
 缺少样式 (正在跳转)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>正在跳转...</title>
    <script src="http://www.jq22.com/jquery/jquery-3.3.1.js"></script>
</head>
<body>
<p>该页面为回调页面,文本内容可为空.</p>
欢迎您,<%=session.getAttribute("nickname")%>.
正在跳转...

<script>
    $.ajax({
        // url: "/user/getOpenSnsUserInfoByCode",
        url: "/WeixinContact/wechat/callbackAndGetUserInfo",
        // data: "${user.openid}",
        contentType: "application/json",
        dataType: "json",
        method: "POST",
        success: function (data) {

            if(data===1){
                //right code -> to index
                window.location.href="http://ngrok.ykmimi.com/";
            }else{
                //wrong code -> show error page
                window.location.href="http://ngrok.ykmimi.com/error";
            }

        }
    });
</script>
</body>
</html>
