package com.baigehuidi.demo.weixin4j.util.wxpay;


import com.baigehuidi.demo.weixin4j.http.HttpClient;

/**
 * 微信支付常量类
 */
public class WXPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    public static final String DOMAIN_API = "api.mch.weixin.qq.com";
    public static final String DOMAIN_API2 = "api2.mch.weixin.qq.com";
    public static final String DOMAIN_APIHK = "apihk.mch.weixin.qq.com";
    public static final String DOMAIN_APIUS = "apius.mch.weixin.qq.com";


    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";

    public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
    public static final String USER_AGENT = WXPAYSDK_VERSION +
            " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") +
            ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();

    //提交付款码支付 https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
    public static final String MICROPAY_URL_SUFFIX     = "/pay/micropay";
    //统一下单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
    public static final String UNIFIEDORDER_URL_SUFFIX = "/pay/unifiedorder";
    //查询订单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_2
    public static final String ORDERQUERY_URL_SUFFIX   = "/pay/orderquery";
    //撤销订单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_11
    public static final String REVERSE_URL_SUFFIX      = "/secapi/pay/reverse";
    //关闭订单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_3
    public static final String CLOSEORDER_URL_SUFFIX   = "/pay/closeorder";
    //申请退款 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4
    public static final String REFUND_URL_SUFFIX       = "/secapi/pay/refund";
    //查询退款 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_5
    public static final String REFUNDQUERY_URL_SUFFIX  = "/pay/refundquery";
    //下载对账单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_6
    public static final String DOWNLOADBILL_URL_SUFFIX = "/pay/downloadbill";
    //交易保障 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_8&index=9
    public static final String REPORT_URL_SUFFIX       = "/payitil/report";
    //转换短链接 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_9&index=10
    public static final String SHORTURL_URL_SUFFIX     = "/tools/shorturl";
    //授权码查询openid https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9
    public static final String AUTHCODETOOPENID_URL_SUFFIX = "/tools/authcodetoopenid";

    // sandbox 沙盒测试
    public static final String SANDBOX_GETSIGNKEY_URL_SUFFIX   = "/sandboxnew/pay/getsignkey";
    public static final String SANDBOX_MICROPAY_URL_SUFFIX     = "/sandboxnew/pay/micropay";
    public static final String SANDBOX_UNIFIEDORDER_URL_SUFFIX = "/sandboxnew/pay/unifiedorder";
    public static final String SANDBOX_ORDERQUERY_URL_SUFFIX   = "/sandboxnew/pay/orderquery";
    public static final String SANDBOX_REVERSE_URL_SUFFIX      = "/sandboxnew/secapi/pay/reverse";
    public static final String SANDBOX_CLOSEORDER_URL_SUFFIX   = "/sandboxnew/pay/closeorder";
    public static final String SANDBOX_REFUND_URL_SUFFIX       = "/sandboxnew/secapi/pay/refund";
    public static final String SANDBOX_REFUNDQUERY_URL_SUFFIX  = "/sandboxnew/pay/refundquery";
    public static final String SANDBOX_DOWNLOADBILL_URL_SUFFIX = "/sandboxnew/pay/downloadbill";
    public static final String SANDBOX_REPORT_URL_SUFFIX       = "/sandboxnew/payitil/report";
    public static final String SANDBOX_SHORTURL_URL_SUFFIX     = "/sandboxnew/tools/shorturl";
    public static final String SANDBOX_AUTHCODETOOPENID_URL_SUFFIX = "/sandboxnew/tools/authcodetoopenid";

}

