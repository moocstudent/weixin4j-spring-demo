<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微信支付测试jsp</title>
    <!--暂且不用jweixin-->
    <!--<script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>-->
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>

</head>
<body>
<p>微信支付测试jsp</p>

<button onclick="payThis()">支付测试01</button>

<form id="payForm" method="POST">
    <input type="text" value="测试物品1" name="body"/>
    <input type="text" name="total_fee"/>
    <input type="text" name="fee_type" value="CNY"/>
    <button id="testBtn">进行测试</button>
</form>

<script>

    var appId;
    var timeStamp;
    var nonceStr;
    var package1;
    var signType;
    var paySign;

    $("#testBtn").click(testPay);

    //测试支付 1.将商品信息,网页授权code传送到后台
    function testPay() {
        // alert("testPay ajax opened ...");
        // alert($('#payForm').serializeJSON());
        //将商品订单主体信息发送给统一下单方法
        $.ajax({
            url: "/payOne/buyNew",
            type: "POST",
            data: $("#payForm").serializeJSON(),//只传送商品参数,后面路径通过其它方式获取openid
            dataType: "json",
            success: function (data) {
                alert("data");
                alert("data:"+JSON.stringfiy(data));
                appId = data.appId;
                timeStamp = data.WCPAY.timeStamp;
                nonceStr = data.WCPAY.nonceStr;
                package1 = data.WCPAY.package;
                signType = data.WCPAY.signType;
                paySign = data.WCPAY.paySign;
                payThis();
            }

        });
    }

    function payThis() {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    }

    function onBridgeReady() {
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId": appId,//公众号名称，由商户传入
                "timeStamp": timeStamp, //时间戳，自1970年以来的秒数,下单时间
                "nonceStr": nonceStr, //随机串
                "package": package1, //预支付id
                "signType": signType, //微信签名方式：
                "paySign": paySign //微信签名
                // "appId": "wx6d79ecc42bc067c7",//公众号名称，由商户传入
                // "timeStamp": "1545462894", //时间戳，自1970年以来的秒数,下单时间
                // "nonceStr": "3pML9LqcFggc1CxCD3RIk08EIEhbfBcJ", //随机串
                // "package": "prepay_id=wx22151456369686f87d355f541996530009", //预支付id
                // "signType": "MD5", //微信签名方式：
                // "paySign": "A5AB3AC994CA9B9C545A3638C034C0D1" //微信签名
            },
            function (res) {
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    alert("get_brand_wcpay_request:ok");
                    // 使用以上方式判断前端返回,微信团队郑重提示：
                    //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                }
            });
    }


</script>

</body>
</html>
