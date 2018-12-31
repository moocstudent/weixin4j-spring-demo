<%--
 登录集页面 (仿照一号店 https://passport.yhd.com/passport/login_input.do)
 TODO 完善登录
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>

    <!--BootStrap的样式文件-->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="https://code.jquery.com/jquery.js"></script>

    <!-- Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <script src="http://www.jq22.com/jquery/jquery-3.3.1.js"></script>
    <!--微信登录二维码插件js-->
    <script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
</head>
<body>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
该页面的目的不限于微信登录,还应该包含其它方式登录.

            snsUser
            昵称:${snsUser.nickname}
            <br>

            三种登录方式<br>
            <div class="center-block">
            <button class="btn" id="btn1">用微信登录 全屏二维码</button>
            <button id="modal-742328" href="#modal-container-742328" role="button" class="btn" data-toggle="modal">微信登录iframe</button>
            <a href="/qrcode">登录/qrcode</a>
            </div>
            <hr>

            <h3>微信扫码获取openid版本(非开放平台扫码)</h3>
            <a href="/wechat/wechatLogin" target="_blank">扫码登录/wechat/wechatLogin</a>


            <hr>
            之下为测试
            <p>获取code</p>
            <button id="btn2">获取code</button>
            token:<p id="token"></p>
            openid:<p id="openid"></p>
            <p>获取用户信息</p>
            <button id="btn3">获取用户信息</button>
            <P>姓名：</P>
            <p id="name"></p>
            <p>头像：</p><img src="" id="img">

            <button id="btn4">直接从code获取用户信息</button>
            <p id="nickname"></p>


            <!--<div id="login_container"></div>-->



            <div class="modal fade" id="modal-container-742328" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                               微信扫码登录
                            </h4>
                        </div>
                        <div class="modal-body" style="width:500px;height:500px">
                         <!--<div id="login_container" style="width:400px;height:400px"></div>-->
                            <iframe class="center" src="qrcode.jsp" style="width:400px;height:400px"></iframe>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>





login.jsp

<%--<% String code = request.getParameter("code");%>--%>
<%--<%= code %>--%>
<%--<% Weixin weixin = new Weixin();%>--%>

<%--<% SnsUser snsUser = weixin.sns().getSnsUserByCode(code);%>--%>
<%--<%=snsUser.getNickname()%>--%>

<!-- <button id="btn">btn</button> -->
<!-- 通过ajax获取的用户信息封装在data中,可以根据需要获取不同的参数,如 -->





<script>

    //not use
    // $("#btn").click(getSnsUser);
    function getSnsUser() {
        //     alert("getSnsUser begin")
        $.ajax({
            url: "/user/getSnsUserInfoByCode",
            data: "<%=request.getParameter("code")%>",
            contentType: "application/json",
            dataType: "json",
            method: "POST",
            success: function (data) {
                alert(JSON.stringify(data));
                //将数据反填到html或jsp页面上
            }
        });
    }


    //not use
    function getOpenSnsUser() {
        //     alert("getSnsUser begin")
        $.ajax({
            url: "/user/getOpenSnsUserInfoByCode",
            data: "<%=request.getParameter("code")%>",
            contentType: "application/json",
            dataType: "json",
            method: "POST",
            success: function (data) {
                window.location.href=window.location.host;
                alert(JSON.stringify(data));
                //将数据反填到html或jsp页面上
            }
        });
    }




</script>


<script>

    // var obj = new WxLogin({
    //     self_redirect: true,
    //     id: "login_container",
    //     appid: APPID,
    //     scope: "",
    //     redirect_uri: "",
    //     state: "",
    //     style: "",
    //     href: ""
    // });

</script>


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


    // 快捷方式
    // var obj = new WxLogin({
    //     self_redirect: true,
    //     id: "login_container",
    //     appid: APPID,
    //     scope: SCOPE,
    //     redirect_uri: REDIRECT_URI,
    //     state: STATE,
    //     style: "",
    //     href: ""
    // });






    //第一步,获取code
    //用微信登录按钮
    var btn1 = $("#btn1");




    //微信登录
    btn1.click(getOpenCode);


    function getOpenCode() {
        window.open("https://open.weixin.qq.com/connect/qrconnect?appid=" + APPID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code&scope=" + SCOPE + "&state=STATE#wechat_redirect");
    }

    //第二步,获取access_token
    var btn2 = $("#btn2");

    var btn4 = $("#btn4");


    btn4.click(getUserInfoByCode);
    function getUserInfoByCode(){
            alert("getUserInfoByCode begin");
            $.ajax({
                url: "/user/getOpenSnsUserInfoByCode",
                // url: "/callBackLogin",
                data: "<%=request.getParameter("code")%>",
                contentType: "application/json",
                dataType: "json",
                method: "POST",
                success: function (data) {
                    alert(JSON.stringify(data));
                    var nickname = data.SnsUser.nickname;
                    $("#nickname").html(nickname);
                    alert(data.SnsUser.nickname);
                    //将数据反填到html或jsp页面上
                }
            });

    }

    btn2.click(getAccessToken);

    function getAccessToken() {
        var CODE = "<%=request.getParameter("code")%>";
        var tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + SECRET + "&code=" + CODE + "&grant_type=authorization_code";
        $.ajax({
            url: tokenUrl,
            type: "POST",
            data:{"":1},
            dataType: "json",
            success: function (data) {
                alert(JSON.stringify(data));
                var token = data.access_token;
                var openid = data.openid;
                $("#token").html(token);
                $("#openid").html(openid);
            }

        })
    }

    //第三步,获取用户信息
    var btn3 = $("#btn3");
    btn3.click(getUserInfo);

    function getUserInfo() {
        var token3 = $("#token").html();
        var openid3 = $("#openid").html();
        var ajaxUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token3 + "&openid=" + openid3;
        $.ajax({
            url: ajaxUrl,
            type: "get",
            dataType: "JSONP",
            success: function (data) {
                alert(JSON.stringify(data));
                var name = $("#name");
                var img = $("#img");
                $(name).html(data.nickname);
                $(img).attr("src", data.headimgurl);
            }
        })
    }


</script>

</body>
</html>
